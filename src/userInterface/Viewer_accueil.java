/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package userInterface;

import tools.HardCodedParameters;

import specifications.ViewerService;
import specifications.ReadService;
import specifications.RequireReadService;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class Viewer_accueil implements ViewerService, RequireReadService{
	
  private ReadService data;
  private Button jouer;
  private Button instruction;

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
	  gridpane_accueil.setPadding(new Insets(50,0,0,0));
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
	  Image accueil = new Image("/images/logo.png");
	  ImageView accueil_image = new ImageView();
	  accueil_image.setFitWidth(4*HardCodedParameters.defaultWidth/5);
	  accueil_image.setFitHeight(HardCodedParameters.defaultWidth/2);
	  accueil_image.setImage(accueil);
	  GridPane.setMargin(accueil_image, new Insets(-1*HardCodedParameters.defaultHeight/15, 0, 0, 1*HardCodedParameters.defaultWidth/10));
	  gridpane_accueil.add(accueil_image,0,0);

	  //Button jouer
	  jouer = new Button("Commencer");
	  jouer.setId("jouer");
	  jouer.arm();
	  GridPane.setMargin(jouer, new Insets(-1*HardCodedParameters.defaultHeight/4, 0, 0, -50+1*HardCodedParameters.defaultWidth/2));
	  gridpane_accueil.add(jouer,0,1);
	  
	  jouer.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	jouer.setText("Accepted");
		    }
		});
	  
	  //Button instruction
	  instruction = new Button("Instruction");
	  instruction.setId("instruction");
	  instruction.arm();
	  GridPane.setMargin(instruction, new Insets(-1*HardCodedParameters.defaultHeight/8, 0, 0, -50+1*HardCodedParameters.defaultWidth/2));
	  gridpane_accueil.add(instruction,0,2);
	 
	  //Ajoute le gridpane à la fenetre
	  window.getChildren().addAll(gridpane_accueil);
	   
	  return window;
  }

@Override
public Button getJouer() {
	return jouer;
}

@Override
public Button getInstruction() {
	return instruction;
}
  
}
