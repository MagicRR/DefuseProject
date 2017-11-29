/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
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
	  
	  ColumnConstraints column1 = new ColumnConstraints(HardCodedParameters.defaultWidth,HardCodedParameters.defaultWidth, HardCodedParameters.defaultWidth);
	  column1.setHgrow(Priority.ALWAYS);
	  
	  gridpane_accueil.getColumnConstraints().addAll(column1);	  
	 
	  //Background de l'accueil
	  BackgroundImage fond_accueil= new BackgroundImage(new Image("/images/explosion.jpeg",HardCodedParameters.defaultWidth,HardCodedParameters.defaultHeight,false,true),
	  	        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	  	          BackgroundSize.DEFAULT);
	  gridpane_accueil.setBackground(new Background(fond_accueil));
	 
	  //Image du jeu
	  Image accueil = new Image("/images/logo.jpg");
	  ImageView accueil_image = new ImageView();
	  accueil_image.setFitWidth(HardCodedParameters.defaultWidth);
	  accueil_image.setFitHeight(HardCodedParameters.defaultWidth/2);
	  accueil_image.setImage(accueil);
	  GridPane.setMargin(accueil_image, new Insets(0, 0, 0, 13));
	  gridpane_accueil.add(accueil_image,0,0);

	  //Button jouer
	  Button jouer = new Button("Commencer");
	  GridPane.setMargin(jouer, new Insets(0, 0, 0, 13));
	  gridpane_accueil.add(jouer,0,1);
	  
	  //Button instruction
	  Button instruction = new Button("Instruction");
	  GridPane.setMargin(instruction, new Insets(0, 0, 0, 13));
	  gridpane_accueil.add(instruction,0,2);
	 
	  //Ajoute le gridpane à la fenetre
	  window.getChildren().addAll(gridpane_accueil);
	   
	  return window;
  }
}
