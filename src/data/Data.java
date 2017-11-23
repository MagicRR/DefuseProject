/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: data/Data.java 2015-03-11 buixuan.
 * ******************************************************/
package data;

import tools.HardCodedParameters;
import tools.Position;
import tools.Sound;

import specifications.DataService;
import specifications.PhantomService;

import data.ia.MoveLeftPhantom;

import java.util.ArrayList;

public class Data implements DataService{
  //private Heroes hercules;
  private Position heroesPosition;
  private int heroesHp;
  private int stepNumber, score;
  private ArrayList<PhantomService> phantoms;
  private ArrayList<PhantomService> object;
  private double heroesWidth,heroesHeight,phantomWidth,phantomHeight, objectWidth, objectHeight;
  private Sound.SOUND sound;

  public Data(){}

  @Override
  public void init(){
    //hercules = new Heroes;
    heroesPosition = new Position(HardCodedParameters.heroesStartX,HardCodedParameters.heroesStartY);
    phantoms = new ArrayList<PhantomService>();
    object = new ArrayList<PhantomService>();
    heroesHp = 3;

    stepNumber = 0;
    score = 0;
    heroesWidth = HardCodedParameters.heroesWidth;
    heroesHeight = HardCodedParameters.heroesHeight;
    phantomWidth = HardCodedParameters.phantomWidth;
    phantomHeight = HardCodedParameters.phantomHeight;
    objectWidth = HardCodedParameters.objectWidth;
    objectHeight = HardCodedParameters.objectHeight;

    sound = Sound.SOUND.None;
  }

  public int getHeroesHp() {
		return heroesHp;
	}

  public void setHeroesHp(int hp) {
	  heroesHp = hp;
	  }

  	public void addObject(Position p) {
  		object.add(new MoveLeftPhantom(p));
  	}

  	public ArrayList<PhantomService> getObject() {
  		return object;
  	}

	public void setObject(ArrayList<PhantomService> object) {
		this.object = object;
	}
	
	public double getObjectWidth() {
		return objectWidth;
	}
	
	public void setObjectWidth(double objectWidth) {
		this.objectWidth = objectWidth;
	}
	
	public double getObjectHeight() {
		return objectHeight;
	}
	
	public void setObjectHeight(double objectHeight) {
		this.objectHeight = objectHeight;
	}
	
	public Sound.SOUND getSound() {
		return sound;
	}
	
	public void setSound(Sound.SOUND sound) {
		this.sound = sound;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setHeroesWidth(double heroesWidth) {
		this.heroesWidth = heroesWidth;
	}
	
	public void setHeroesHeight(double heroesHeight) {
		this.heroesHeight = heroesHeight;
	}
	
	public void setPhantomWidth(double phantomWidth) {
		this.phantomWidth = phantomWidth;
	}
	
	public void setPhantomHeight(double phantomHeight) {
		this.phantomHeight = phantomHeight;
	}
	
	@Override
	public Position getHeroesPosition(){ return heroesPosition; }
	  
	@Override
	public double getHeroesWidth(){ return heroesWidth; }
	  
	@Override
	public double getHeroesHeight(){ return heroesHeight; }
	  
	@Override
	public double getPhantomWidth(){ return phantomWidth; }
	  
	@Override
	public double getPhantomHeight(){ return phantomHeight; }
	
	@Override
	public int getStepNumber(){ return stepNumber; }
	  
	@Override
	public int getScore(){ return score; }
	
	@Override
	public ArrayList<PhantomService> getPhantoms(){ return phantoms; }
	  
	@Override
	public Sound.SOUND getSoundEffect() { return sound; }
	
	@Override
	public void setHeroesPosition(Position p) { heroesPosition=p; }
	  
	@Override
	public void setStepNumber(int n){ stepNumber=n; }
	  
	@Override
	public void addScore(int score){ this.score+=score; }
	
	@Override
	public void addPhantom(Position p) { phantoms.add(new MoveLeftPhantom(p)); }
	  
	@Override
	public void setPhantoms(ArrayList<PhantomService> phantoms) { this.phantoms=phantoms; }
	  
	@Override
	public void setSoundEffect(Sound.SOUND s) { sound=s; }

}
