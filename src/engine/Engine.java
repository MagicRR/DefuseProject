/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: engine/Engine.java 2015-03-11 buixuan.
 * ******************************************************/
package engine;

import tools.HardCodedParameters;
import tools.User;
import tools.Position;
import tools.Sound;

import specifications.EngineService;
import specifications.DataService;
import specifications.RequireDataService;
import specifications.PhantomService;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import java.util.Random;
import java.io.File;
import java.util.ArrayList;

public class Engine implements EngineService, RequireDataService{
  private static final double friction=HardCodedParameters.friction,
                              heroesStep=HardCodedParameters.heroesStep,
                              phantomStep=HardCodedParameters.phantomStep;
  private Timer engineClock;
  private DataService data;
  private User.COMMAND command;
  private Random gen;
  private boolean moveLeft,moveRight,moveUp,moveDown;
  private double heroesVX,heroesVY;

  public Engine(){}

  @Override
  public void bindDataService(DataService service){
    data=service;
  }
  
  @Override
  public void init(){
	  
    engineClock = new Timer();
    command = User.COMMAND.NONE;
    gen = new Random();
    moveLeft = false;
    moveRight = false;
    moveUp = false;
    moveDown = false;
    heroesVX = 0;
    heroesVY = 0;
  }

  @Override
  public void start(){
	  playMusic();

	  
    engineClock.schedule(new TimerTask(){
      public void run() {
        //System.out.println("Game step #"+data.getStepNumber()+": checked.");
    	  
    	  //Supprime les objets sortant de la map
    	  //for(int i=0; i<data.getPhantoms().size(); i++) {
    	  //	  if(data.getPhantoms().get(i).getPosition().x<35) {
    	  //	  data.getPhantoms().remove(i);
    	  //  }
    	  //}
		  
    	  //Supprime les objets sortant de la map
    	  // for(int i=0; i<data.getObject().size(); i++) {
    	  //	  if(data.getObject().get(i).getPosition().x<35) {
    	  //	  data.getObject().remove(i);
    	  //  }
    	  //}
    	  
    	  
  		if(data.getHeroesHp() <= 0) {
  			stop();
  		}
        
    	//Coin
        if (gen.nextInt(100)<5+data.getStepNumber()/100) spawnPhantom();
        //Bombe
        if (gen.nextInt(100)<3+data.getStepNumber()/100) spawnObject();


        updateSpeedHeroes();
        updateCommandHeroes();
        updatePositionHeroes();

        ArrayList<PhantomService> phantoms = new ArrayList<PhantomService>();
        ArrayList<PhantomService> object = new ArrayList<PhantomService>();
        
        int score=0;

        data.setSoundEffect(Sound.SOUND.None);

        for (PhantomService p:data.getPhantoms()){
            if (p.getAction()==PhantomService.MOVE.LEFT) moveLeft(p);
            if (p.getAction()==PhantomService.MOVE.RIGHT) moveRight(p);
            if (p.getAction()==PhantomService.MOVE.UP) moveUp(p);
            if (p.getAction()==PhantomService.MOVE.DOWN) moveDown(p);
            if (p.getAction()==PhantomService.MOVE.LEFTUP) moveLeftUp(p);
            if (p.getAction()==PhantomService.MOVE.LEFTDOWN) moveLeftDown(p);
            if (p.getAction()==PhantomService.MOVE.RIGHTUP) moveRightUp(p);
            if (p.getAction()==PhantomService.MOVE.RIGHTDOWN) moveRightDown(p);   

          if (collisionHeroesPhantom(p)){
            data.setSoundEffect(Sound.SOUND.HeroesGotHit);
            score++;
          } else {
            if (p.getPosition().x>0) phantoms.add(p);
          }
        }
        
        for (PhantomService p:data.getObject()){
            if (p.getAction()==PhantomService.MOVE.LEFT) moveLeft(p);
            if (p.getAction()==PhantomService.MOVE.RIGHT) moveRight(p);
            if (p.getAction()==PhantomService.MOVE.UP) moveUp(p);
            if (p.getAction()==PhantomService.MOVE.DOWN) moveDown(p);
            if (p.getAction()==PhantomService.MOVE.LEFTUP) moveLeftUp(p);
            if (p.getAction()==PhantomService.MOVE.LEFTDOWN) moveLeftDown(p);
            if (p.getAction()==PhantomService.MOVE.RIGHTUP) moveRightUp(p);
            if (p.getAction()==PhantomService.MOVE.RIGHTDOWN) moveRightDown(p);            

            if (collisionHeroesObject(p)){
            	
              data.setSoundEffect(Sound.SOUND.PhantomDestroyed);
              score = score - 10;
              data.setHeroesHp(data.getHeroesHp() - 1);
              
              if(p.getAction() == PhantomService.MOVE.LEFT)
          	  	data.setHeroesPosition(new Position(data.getHeroesPosition().x - 20, data.getHeroesPosition().y));
          	  
          	  if(p.getAction() == PhantomService.MOVE.RIGHT)
        	  	data.setHeroesPosition(new Position(data.getHeroesPosition().x + 20, data.getHeroesPosition().y));
        	  
        	  if(p.getAction() == PhantomService.MOVE.UP)
          	  	data.setHeroesPosition(new Position(data.getHeroesPosition().x, data.getHeroesPosition().y - 20));
          	  
          	  if(p.getAction() == PhantomService.MOVE.DOWN)
        	  	data.setHeroesPosition(new Position(data.getHeroesPosition().x, data.getHeroesPosition().y + 20));
          	  
          	if(p.getAction() == PhantomService.MOVE.LEFTUP)
          	  	data.setHeroesPosition(new Position(data.getHeroesPosition().x - 20, data.getHeroesPosition().y - 20));
          	  
          	  if(p.getAction() == PhantomService.MOVE.RIGHTUP)
        	  	data.setHeroesPosition(new Position(data.getHeroesPosition().x + 20, data.getHeroesPosition().y -20));
        	  
        	  if(p.getAction() == PhantomService.MOVE.LEFTDOWN)
          	  	data.setHeroesPosition(new Position(data.getHeroesPosition().x - 20, data.getHeroesPosition().y + 20));
          	  
          	  if(p.getAction() == PhantomService.MOVE.RIGHTDOWN)
        	  	data.setHeroesPosition(new Position(data.getHeroesPosition().x + 20, data.getHeroesPosition().y + 20));

            } else {
              if (p.getPosition().x>0) object.add(p);
            }
          }

        data.addScore(score);

        data.setPhantoms(phantoms);
        
        data.setObject(object);

        data.setStepNumber(data.getStepNumber()+1);
      }
    },0,HardCodedParameters.enginePaceMillis);
  }

  @Override
  public void stop(){
    engineClock.cancel();
  }

  @Override
  public void setHeroesCommand(User.COMMAND c){
    if (c==User.COMMAND.LEFT) moveLeft=true;
    if (c==User.COMMAND.RIGHT) moveRight=true;
    if (c==User.COMMAND.UP) moveUp=true;
    if (c==User.COMMAND.DOWN) moveDown=true;
  }
  
  @Override
  public void releaseHeroesCommand(User.COMMAND c){
    if (c==User.COMMAND.LEFT) moveLeft=false;
    if (c==User.COMMAND.RIGHT) moveRight=false;
    if (c==User.COMMAND.UP) moveUp=false;
    if (c==User.COMMAND.DOWN) moveDown=false;
  }

  private void updateSpeedHeroes(){
    heroesVX*=friction;
    heroesVY*=friction;
  }

  private void updateCommandHeroes(){
    if (moveLeft) heroesVX-=heroesStep;
    if (moveRight) heroesVX+=heroesStep;
    if (moveUp) heroesVY-=heroesStep;
    if (moveDown) heroesVY+=heroesStep;
  }
  
  private void updatePositionHeroes(){
    data.setHeroesPosition(new Position(data.getHeroesPosition().x+heroesVX,data.getHeroesPosition().y+heroesVY));
    
    if (data.getHeroesPosition().x<HardCodedParameters.defaultMinWidth+50) {
    	data.setHeroesPosition(new Position(HardCodedParameters.defaultMinWidth+70, data.getHeroesPosition().y));
    }
    if (data.getHeroesPosition().x>HardCodedParameters.defaultWidth-50) {
    	data.setHeroesPosition(new Position(HardCodedParameters.defaultWidth-70, data.getHeroesPosition().y));
    }
    if (data.getHeroesPosition().y<HardCodedParameters.defaultMinHeight+50) {
    	data.setHeroesPosition(new Position(data.getHeroesPosition().x, HardCodedParameters.defaultMinHeight+70));
    }
    if (data.getHeroesPosition().y>HardCodedParameters.defaultHeight-150) {
    	data.setHeroesPosition(new Position(data.getHeroesPosition().x, HardCodedParameters.defaultHeight-200));
    }
    
  }

  private void spawnPhantom(){
    int x=(int)(HardCodedParameters.defaultWidth*.9);
    int y=0;
    boolean cont=true;
    while (cont) {
      y=(int)(gen.nextInt((int)(HardCodedParameters.defaultHeight*.6))+HardCodedParameters.defaultHeight*.1);
      cont=false;
      for (PhantomService p:data.getPhantoms()){
        if (p.getPosition().equals(new Position(x,y))) cont=true;
      }
    }
    if(data.getPhantoms().size() < 2 ) {
    	data.addPhantom(new Position(x,y));
    }
  }
  
  private void spawnObject(){
    int x=(int)(HardCodedParameters.defaultWidth*.9);
    int y=0;
    boolean cont=true;
    while (cont) {
      y=(int)(gen.nextInt((int)(HardCodedParameters.defaultHeight*.6))+HardCodedParameters.defaultHeight*.1);
      cont=false;
      for (PhantomService p:data.getObject()){
        if (p.getPosition().equals(new Position(x,y))) cont=true;
      }
    }
    if( data.getObject().size() < 6 )
    	data.addObject(new Position(x,y));
  }

  //Déplacement classique phantoms/object
  private void moveLeft(PhantomService p){
    
    if (p.getPosition().x<HardCodedParameters.defaultMinWidth+50) {
    	
    	switch (gen.nextInt(4)){
        case 0:
            moveRightUp(p);
        	p.setAction(PhantomService.MOVE.RIGHTUP);
          break;
        case 1:
            moveRightDown(p);
        	p.setAction(PhantomService.MOVE.RIGHTDOWN);
          break;
        case 2:
            moveRight(p);
        	p.setAction(PhantomService.MOVE.RIGHT);
          break;
        default:
            moveRight(p);
        	p.setAction(PhantomService.MOVE.RIGHT);
          break;
      }
   	
    } else {
        p.setPosition(new Position(p.getPosition().x-phantomStep,p.getPosition().y));
    }
  }

  private void moveRight(PhantomService p){
    
    if (p.getPosition().x>HardCodedParameters.defaultWidth-50) {
    	
    	switch (gen.nextInt(4)){
        case 0:
            moveLeftUp(p);
        	p.setAction(PhantomService.MOVE.LEFTUP);
          break;
        case 1:
            moveLeftDown(p);
        	p.setAction(PhantomService.MOVE.LEFTDOWN);
          break;
        case 2:
            moveLeft(p);
        	p.setAction(PhantomService.MOVE.LEFT);
          break;
        default:
            moveLeft(p);
        	p.setAction(PhantomService.MOVE.LEFT);
          break;
    	}
    	
    }else {
        p.setPosition(new Position(p.getPosition().x+phantomStep,p.getPosition().y));
    }
  }

  private void moveUp(PhantomService p){
    if (p.getPosition().y<HardCodedParameters.defaultMinHeight+50) {
    	
    	switch (gen.nextInt(4)){
        case 0:
            moveLeftDown(p);
        	p.setAction(PhantomService.MOVE.LEFTDOWN);
          break;
        case 1:
            moveRightDown(p);
        	p.setAction(PhantomService.MOVE.RIGHTDOWN);
          break;
        case 2:
            moveDown(p);
        	p.setAction(PhantomService.MOVE.DOWN);
          break;
        default:
            moveDown(p);
        	p.setAction(PhantomService.MOVE.DOWN);
          break;
    	}
    	
    }else {
        p.setPosition(new Position(p.getPosition().x,p.getPosition().y-phantomStep));
    }
  }

  private void moveDown(PhantomService p){
    if (p.getPosition().y>HardCodedParameters.defaultHeight-170) {
    	
    	switch (gen.nextInt(4)){
        case 0:
            moveLeftUp(p);
        	p.setAction(PhantomService.MOVE.LEFTUP);
          break;
        case 1:
            moveRightUp(p);
        	p.setAction(PhantomService.MOVE.RIGHTUP);
          break;
        case 2:
            moveUp(p);
        	p.setAction(PhantomService.MOVE.UP);
          break;
        default:
            moveUp(p);
        	p.setAction(PhantomService.MOVE.UP);
          break;
    	}
    }else {
        p.setPosition(new Position(p.getPosition().x,p.getPosition().y+phantomStep));
    }
  }

  //Déplacement diagonales phantoms/object
  private void moveLeftUp(PhantomService p){
	    
    if ( p.getPosition().x<HardCodedParameters.defaultMinWidth+50 || p.getPosition().y<HardCodedParameters.defaultMinHeight+35) {
    	
    	switch (gen.nextInt(4)){
        case 0:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.LEFTDOWN);
          break;
        case 1:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.DOWN);
          break;
        case 2:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.LEFT);
          break;
        default:
            moveLeft(p);
        	p.setAction(PhantomService.MOVE.LEFT);
          break;
    	}

    } else {
        p.setPosition(new Position(p.getPosition().x-phantomStep,p.getPosition().y-phantomStep));
    }
  }
  
  private void moveLeftDown(PhantomService p){
	    
    if (p.getPosition().x<HardCodedParameters.defaultMinWidth+50 || p.getPosition().y>HardCodedParameters.defaultHeight-130) {
    	
    	switch (gen.nextInt(4)){
        case 0:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.LEFTUP);
          break;
        case 1:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.UP);
          break;
        case 2:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.LEFT);
          break;
        default:
            moveLeft(p);
        	p.setAction(PhantomService.MOVE.LEFT);
          break;
    	}
    	
    } else {
        p.setPosition(new Position(p.getPosition().x-phantomStep,p.getPosition().y+phantomStep));
    }
  }

  private void moveRightUp(PhantomService p){
    
    if (p.getPosition().x>HardCodedParameters.defaultWidth-50 || p.getPosition().y<HardCodedParameters.defaultMinHeight+35) {
    	
    	switch (gen.nextInt(4)){
        case 0:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.RIGHTDOWN);
          break;
        case 1:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.DOWN);
          break;
        case 2:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.RIGHT);
          break;
        default:
            moveLeft(p);
        	p.setAction(PhantomService.MOVE.RIGHT);
          break;
    	}
    	
    }else {
        p.setPosition(new Position(p.getPosition().x+phantomStep,p.getPosition().y-phantomStep));
    }
  }
  
  private void moveRightDown(PhantomService p){
	    
    if (p.getPosition().x>HardCodedParameters.defaultWidth-50 || p.getPosition().y>HardCodedParameters.defaultHeight-130) {
    	
    	switch (gen.nextInt(4)){
        case 0:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.RIGHTUP);
          break;
        case 1:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.UP);
          break;
        case 2:
        	moveLeft(p);
         	p.setAction(PhantomService.MOVE.RIGHT);
          break;
        default:
            moveLeft(p);
        	p.setAction(PhantomService.MOVE.RIGHT);
          break;
    	}
    	
    }else {
        p.setPosition(new Position(p.getPosition().x+phantomStep,p.getPosition().y+phantomStep));
    }
  }

  private boolean collisionHeroesPhantom(PhantomService p){
	  return (

    		  (data.getHeroesPosition().x-p.getPosition().x)*(data.getHeroesPosition().x-p.getPosition().x)+
    		  (data.getHeroesPosition().y+35-p.getPosition().y)*(data.getHeroesPosition().y-p.getPosition().y) <
    		  0.25*(data.getHeroesWidth()+data.getPhantomWidth())*(data.getHeroesWidth()+data.getPhantomWidth()
           )
      ||
           (
    		  (data.getHeroesPosition().x-p.getPosition().x)*(data.getHeroesPosition().x-p.getPosition().x)+
    		  (data.getHeroesPosition().y+35-p.getPosition().y)*(data.getHeroesPosition().y-p.getPosition().y) <
    		  0.25*(data.getHeroesWidth()+data.getPhantomWidth()/3)*(data.getHeroesWidth()+data.getPhantomWidth()/3
           )
     )
    );
  }
  
  private boolean collisionHeroesPhantoms(){
    for (PhantomService p:data.getPhantoms()) if (collisionHeroesPhantom(p)) return true; return false;
  }
  
  private boolean collisionHeroesObject(PhantomService p){
    return (

    		  (data.getHeroesPosition().x-p.getPosition().x)*(data.getHeroesPosition().x-p.getPosition().x)+
    		  (data.getHeroesPosition().y+35-p.getPosition().y)*(data.getHeroesPosition().y-p.getPosition().y) <
    		  0.25*(data.getHeroesWidth()+data.getObjectWidth())*(data.getHeroesWidth()+data.getObjectWidth()
           )
      ||
           (
    		  (data.getHeroesPosition().x-p.getPosition().x)*(data.getHeroesPosition().x-p.getPosition().x)+
    		  (data.getHeroesPosition().y+35-p.getPosition().y)*(data.getHeroesPosition().y-p.getPosition().y) <
    		  0.25*(data.getHeroesWidth()+data.getObjectWidth()/3)*(data.getHeroesWidth()+data.getObjectWidth()/3
           )
     )
    );
  }
	  
  private boolean collisionHeroesObject(){
	 for (PhantomService p:data.getObject()) if (collisionHeroesObject(p)) return true; return false;
  }
  
  private void playMusic() {
	  try {
			File yourFile = new File("src/sound/The_Impossible_Game_-_Level_1_-_Fire_Aura.wav");
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;
			Clip clip;

			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
  }
}
