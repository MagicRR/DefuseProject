/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package userInterface;

import tools.HardCodedParameters;

import specifications.ReadService;
import specifications.RequireReadService;
import specifications.ViewerAccueilService;
import specifications.ViewerInstructionService;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import engine.ControllerMain;
import engine.Engine;
import engine.InstruEngine;
import javafx.geometry.Insets;

public class Viewer_instruction extends HBox{
	
  private ReadService data;
  private final Button retour = new Button("Retour à l'accueil");

  public Viewer_instruction(InstruEngine engine){
	  retour.setOnAction(event -> {
		  final Stage primaryStage = engine.getPrimaryStage();
		  final ControllerMain controllerMain = new ControllerMain(primaryStage);
		  final Scene scene = new Scene(controllerMain.getView());
		  primaryStage.setScene(scene);
	  });
  }

  public Parent getPanel(){
	  
	  //Groupe de l'interface global
	  Pane window = new Pane();
	  
	  //Malette	  	  
	  GridPane gridpane_accueil = new GridPane();
	  
	  //Gestion des contraintes de colonne style padding ..
	  gridpane_accueil.setPadding(new Insets(0,0,0,0));
	  gridpane_accueil.setHgap(15);
	  gridpane_accueil.setVgap(10);
	  
	  ColumnConstraints column1 = new ColumnConstraints(HardCodedParameters.defaultWidth,HardCodedParameters.defaultWidth, HardCodedParameters.defaultWidth);
	  column1.setHgrow(Priority.ALWAYS);
	  
	  gridpane_accueil.getColumnConstraints().addAll(column1);	  
	
	  BackgroundImage fond_accueil= new BackgroundImage(new Image("/images/explosion.jpeg",HardCodedParameters.defaultWidth,HardCodedParameters.defaultHeight,false,true),
	  	        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	  	          BackgroundSize.DEFAULT);
	  
	  window.setBackground(new Background(fond_accueil));
	 
	  //Image du jeu
	  Image accueil = new Image("/images/logo.png");
	  ImageView accueil_image = new ImageView();
	  accueil_image.setFitWidth(4*HardCodedParameters.defaultWidth/5);
	  accueil_image.setFitHeight(HardCodedParameters.defaultWidth/2);
	  accueil_image.setImage(accueil);
	  GridPane.setMargin(accueil_image, new Insets(-1*HardCodedParameters.defaultHeight/15, 0, 0, 1*HardCodedParameters.defaultWidth/10));
	  gridpane_accueil.add(accueil_image,0,0);

	  //Button retour
	  retour.arm();
	  GridPane.setMargin(retour, new Insets(-1*HardCodedParameters.defaultHeight/5, 0, 0, -50+1*HardCodedParameters.defaultWidth/2));
	  gridpane_accueil.add(retour,0,1);
	
	  
	  //Ajoute le gridpane à la fenetre
	  window.getChildren().addAll(gridpane_accueil);
	   
	  return window;
  }

	public Button getRetour() {
		return retour;
	}

}
