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
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import data.Minuteur;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.ImagePattern;

public class Viewer_accueil implements ViewerService, RequireReadService{
	
  private ReadService data;

  public Viewer_accueil(){}

  @Override
  public void bindReadService(ReadService service){
    data=service;
  }

  @Override
  public void init(){
  }

  @Override
  public Parent getPanel(){
	  
	  //Groupe de l'interface global
	  Group window = new Group();
	  
	  //Malette	  	  
	  GridPane gridpane_accueil = new GridPane();
	  
	  //Gestion des contraintes de colonne style padding ..
	  gridpane_accueil.setPadding(new Insets(15,0,0,0));
	  gridpane_accueil.setHgap(15);
	  gridpane_accueil.setVgap(10);
	  
	  ColumnConstraints column1 = new ColumnConstraints(-10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3);
	  column1.setHgrow(Priority.ALWAYS);
	  
	  gridpane_accueil.getColumnConstraints().addAll(column1);	  
	 
	  //Background de l'accueil
	  BackgroundImage fond_accueil= new BackgroundImage(new Image("/images/bordure.png",2*HardCodedParameters.defaultWidth/3,1*HardCodedParameters.defaultHeight/3,false,true),
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
	  gridpane_accueil.setBackground(new Background(fond_accueil));
	 
	  Image accueil = new Image("/images/red-button.png");
	  ImageView accueil_titre = new ImageView();
	  accueil_titre.setFitWidth(2*HardCodedParameters.defaultWidth/3);
	  accueil_titre.setFitHeight(2*HardCodedParameters.defaultWidth/3);
	  accueil_titre.setImage(accueil);
	  gridpane_accueil.add(accueil_titre,0,0);
	  GridPane.setMargin(accueil_titre, new Insets(0, 0, 0, 13));
	 
	  //Ajoute le gridpane à la fenetre
	  window.getChildren().addAll(gridpane_accueil);
	   
	  return window;
  }
}
