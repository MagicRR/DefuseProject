/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: userInterface/Viewer.java 2015-03-11 buixuan.
 * ******************************************************/
package userInterface;

import tools.HardCodedParameters;

import specifications.ViewerService;
import specifications.ReadService;
import specifications.RequireReadService;
import specifications.PhantomService;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

public class Viewer implements ViewerService, RequireReadService{
  private static final int spriteSlowDownRate=HardCodedParameters.spriteSlowDownRate;
  private static final double defaultMainWidth=HardCodedParameters.defaultWidth,
                              defaultMainHeight=HardCodedParameters.defaultHeight;
  private ReadService data;
  private ImageView heroesAvatar;
  private Image heroesSpriteSheet;
  private ArrayList<Rectangle2D> heroesAvatarViewports;
  private ArrayList<Integer> heroesAvatarXModifiers;
  private ArrayList<Integer> heroesAvatarYModifiers;
  private int heroesAvatarViewportIndex;
  private double xShrink,yShrink,shrink,xModifier,yModifier,heroesScale;

  public Viewer(){}
  
  @Override
  public void bindReadService(ReadService service){
    data=service;
  }

  @Override
  public void init(){
	  
	//final java.net.URL resource = getClass().getResource("/sound/The_Impossible_Game_Fire_Aura.mp3");
	//final Media media = new Media(resource.toString());
	//final MediaPlayer mediaPlayer1 = new MediaPlayer(media);
	//mediaPlayer1.play();
	//mediaPlayer1.setVolume(0.8);
	
    xShrink=1;
    yShrink=1;
    xModifier=0;
    yModifier=0;

    //Yucky hard-conding
    heroesSpriteSheet = new Image("file:src/images/modern soldier large.png");
    heroesAvatar = new ImageView(heroesSpriteSheet);
    heroesAvatarViewports = new ArrayList<Rectangle2D>();
    heroesAvatarXModifiers = new ArrayList<Integer>();
    heroesAvatarYModifiers = new ArrayList<Integer>();

    heroesAvatarViewportIndex=0;
    
    //TODO: replace the following with XML loader
    //heroesAvatarViewports.add(new Rectangle2D(301,386,95,192));
    heroesAvatarViewports.add(new Rectangle2D(570,194,115,190));
    heroesAvatarViewports.add(new Rectangle2D(398,386,133,192));
    heroesAvatarViewports.add(new Rectangle2D(155,194,147,190));
    heroesAvatarViewports.add(new Rectangle2D(785,386,127,194));
    heroesAvatarViewports.add(new Rectangle2D(127,582,135,198));
    heroesAvatarViewports.add(new Rectangle2D(264,582,111,200));
    heroesAvatarViewports.add(new Rectangle2D(2,582,123,198));
    heroesAvatarViewports.add(new Rectangle2D(533,386,115,192));
    //heroesAvatarViewports.add(new Rectangle2D(204,386,95,192));

    //heroesAvatarXModifiers.add(10);heroesAvatarYModifiers.add(-7);
    heroesAvatarXModifiers.add(6);heroesAvatarYModifiers.add(-6);
    heroesAvatarXModifiers.add(2);heroesAvatarYModifiers.add(-8);
    heroesAvatarXModifiers.add(1);heroesAvatarYModifiers.add(-10);
    heroesAvatarXModifiers.add(1);heroesAvatarYModifiers.add(-13);
    heroesAvatarXModifiers.add(5);heroesAvatarYModifiers.add(-15);
    heroesAvatarXModifiers.add(5);heroesAvatarYModifiers.add(-13);
    heroesAvatarXModifiers.add(0);heroesAvatarYModifiers.add(-9);
    heroesAvatarXModifiers.add(0);heroesAvatarYModifiers.add(-6);
    //heroesAvatarXModifiers.add(10);heroesAvatarYModifiers.add(-7);
    
  }

  @Override
  public Parent getPanel(){
	  
	    Group panel = new Group();

	    
		if(data.getHeroesHp() > 0) {
			shrink=Math.min(xShrink,yShrink);
		    xModifier=.01*shrink*defaultMainWidth;
		    yModifier=.01*shrink*defaultMainHeight;
	
		    //Yucky hard-conding
		    Rectangle map = new Rectangle(-2*xModifier+shrink*defaultMainWidth,
		                                  -.2*shrink*defaultMainHeight+shrink*defaultMainHeight);
		    Image fond = new Image("/images/fond.jpg");
		    map.setFill(new ImagePattern(fond));
		    map.setStroke(Color.DIMGRAY);
		    map.setStrokeWidth(.01*shrink*defaultMainHeight);
		    map.setArcWidth(.04*shrink*defaultMainHeight);
		    map.setArcHeight(.04*shrink*defaultMainHeight);
		    map.setTranslateX(xModifier);
		    map.setTranslateY(yModifier);
		    
		    Text vie = new Text(-0.1*shrink*defaultMainHeight+.5*shrink*defaultMainWidth,
		                           -0.1*shrink*defaultMainWidth+shrink*defaultMainHeight,
		                           "Vie: " + data.getHeroesHp());
		    vie.setFont(new Font(.05*shrink*defaultMainHeight));
		    
		    Text score = new Text(-0.1*shrink*defaultMainHeight+.5*shrink*defaultMainWidth,
		                           -0.05*shrink*defaultMainWidth+shrink*defaultMainHeight,
		                           "Score: "+data.getScore());
		    score.setFont(new Font(.05*shrink*defaultMainHeight));
		    
		    int index=heroesAvatarViewportIndex/spriteSlowDownRate;
		    heroesScale=data.getHeroesHeight()*shrink/heroesAvatarViewports.get(index).getHeight();
		    heroesAvatar.setViewport(heroesAvatarViewports.get(index));
		    heroesAvatar.setFitHeight(data.getHeroesHeight()*shrink);
		    heroesAvatar.setPreserveRatio(true);
		    heroesAvatar.setTranslateX(shrink*data.getHeroesPosition().x+
		                               shrink*xModifier+
		                               -heroesScale*0.5*heroesAvatarViewports.get(index).getWidth()+
		                               shrink*heroesScale*heroesAvatarXModifiers.get(index)
		                              );
		    heroesAvatar.setTranslateY(shrink*data.getHeroesPosition().y+
		                               shrink*yModifier+
		                               -heroesScale*0.5*heroesAvatarViewports.get(index).getHeight()+
		                               shrink*heroesScale*heroesAvatarYModifiers.get(index)
		                              );
		    heroesAvatarViewportIndex=(heroesAvatarViewportIndex+1)%(heroesAvatarViewports.size()*spriteSlowDownRate);
	
		    panel.getChildren().addAll(map,vie,score,heroesAvatar);
	
		    ArrayList<PhantomService> phantoms = data.getPhantoms();
		    ArrayList<PhantomService> object = data.getObject();
	
		    PhantomService p;
		    PhantomService o;
	
		    for (int i=0; i<phantoms.size();i++){
		      p=phantoms.get(i);
		      double radius=.5*Math.min(shrink*data.getPhantomWidth(),shrink*data.getPhantomHeight());
		      Circle phantomAvatar = new Circle(radius,Color.rgb(255,156,156));
		      Image coin = new Image("/images/coin.png");
		      phantomAvatar.setFill(new ImagePattern(coin));
		      phantomAvatar.setEffect(new Lighting());
		      phantomAvatar.setTranslateX(shrink*p.getPosition().x+shrink*xModifier-radius);
		      phantomAvatar.setTranslateY(shrink*p.getPosition().y+shrink*yModifier-radius);
		      panel.getChildren().add(phantomAvatar);
		    }
		    
		    for (int i=0; i<object.size();i++){
		        o=object.get(i);
		        double radius=.5*Math.min(shrink*data.getObjectWidth(),shrink*data.getObjectHeight());
		        Circle objectAvatar = new Circle(radius,Color.rgb(255,156,255));
		        Image bombe = new Image("/images/bombe.png");
		        objectAvatar.setFill(new ImagePattern(bombe));
		        objectAvatar.setEffect(new Lighting());
		        objectAvatar.setTranslateX(shrink*o.getPosition().x+shrink*xModifier-radius);
		        objectAvatar.setTranslateY(shrink*o.getPosition().y+shrink*yModifier-radius);
		        panel.getChildren().add(objectAvatar);
		      }
		}else {
			 Text game_over = new Text(-0.1*shrink*defaultMainHeight+.5*shrink*defaultMainWidth,
	                 -0.1*shrink*defaultMainWidth+shrink*defaultMainHeight,
	                 "You died ! Score = " + data.getScore() );
			 game_over.setFont(new Font(.05*shrink*defaultMainHeight));
		        panel.getChildren().add(game_over);
		}
   
    return panel;
  }

  @Override
  public void setMainWindowWidth(double width){
    xShrink=width/defaultMainWidth;
  }
  
  @Override
  public void setMainWindowHeight(double height){
    yShrink=height/defaultMainHeight;
  }
}
