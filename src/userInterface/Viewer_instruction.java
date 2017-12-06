/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package userInterface;

import tools.HardCodedParameters;

import specifications.ReadService;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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
	  
	  ColumnConstraints column1 = new ColumnConstraints(HardCodedParameters.defaultWidth, HardCodedParameters.defaultWidth, HardCodedParameters.defaultWidth);
	  column1.setHgrow(Priority.ALWAYS);
	  
	  gridpane_accueil.getColumnConstraints().addAll(column1);	  
	
	  BackgroundImage fond_accueil= new BackgroundImage(new Image("/images/explosion.jpeg",HardCodedParameters.defaultWidth,HardCodedParameters.defaultHeight,false,true),
	  	        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	  	          BackgroundSize.DEFAULT);
	  
	  window.setBackground(new Background(fond_accueil));
	 
	  //Image du jeu
	  
	  //Logs
	  TextArea textInstruction = new TextArea("NOW,\n DO IT BY YOURSELF.");
	  textInstruction.setCursor(Cursor.DEFAULT);
	  textInstruction.setEditable(false);
	  textInstruction.autosize();
	  textInstruction.setPrefHeight(1*HardCodedParameters.defaultWidth/4);
	  textInstruction.setPrefWidth(1*HardCodedParameters.defaultWidth/3);
	  textInstruction.setLayoutX(1*HardCodedParameters.defaultWidth/3);
	  textInstruction.setLayoutY(1*HardCodedParameters.defaultHeight/2);
	  textInstruction.setStyle("-fx-text-fill: green; -fx-control-inner-background: black;");
	  
	  GridPane.setMargin(textInstruction, new Insets(1*HardCodedParameters.defaultHeight/4, 1*HardCodedParameters.defaultWidth/3, 0, 1*HardCodedParameters.defaultWidth/3));
	  gridpane_accueil.add(textInstruction,0,0);

	  //Button retour
	  retour.arm();
	  GridPane.setMargin(retour, new Insets(50, 0, 0, -60+(1*HardCodedParameters.defaultWidth/2)));
	  gridpane_accueil.add(retour,0,1);
	
	  
	  //Ajoute le gridpane à la fenetre
	  window.getChildren().addAll(gridpane_accueil);
	   
	  return window;
  }

	public Button getRetour() {
		return retour;
	}

}
