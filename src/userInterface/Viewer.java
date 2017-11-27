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
import javafx.scene.control.TextField;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.paint.ImagePattern;

public class Viewer implements ViewerService, RequireReadService{
  private ReadService data;

  public Viewer(){}

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
	  
	  //3 sous groupes
	  Group malette_group = new Group();
	  Group logs_group = new Group();
	  Group stats_group = new Group();

	  //Malette
	  //Rectangle malette = new Rectangle(3*HardCodedParameters.defaultWidth/4,3*HardCodedParameters.defaultHeight/4);
	  //Image img = new Image("/images/malette.png");
	  //malette.setFill(new ImagePattern(img));
	  //malette.setStroke(Color.RED);
	  
	  
	  	  
	  GridPane gridpane_malette = new GridPane();
	  
	  //Gestion des contraintes de colonne style padding ..
	  gridpane_malette.setPadding(new Insets(0,28,0,0));
	  //gridpane_malette.setHgap(5);
	  //gridpane_malette.setVgap(5);
	  
	  ColumnConstraints column1 = new ColumnConstraints(-10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3);
	  ColumnConstraints column2 = new ColumnConstraints(-10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3);
	  ColumnConstraints column3 = new ColumnConstraints(-10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3);
	  column1.setHgrow(Priority.ALWAYS);
	  column2.setHgrow(Priority.ALWAYS);
	  column3.setHgrow(Priority.ALWAYS);
	  gridpane_malette.getColumnConstraints().addAll(column1, column2, column3);	  
	 
	  //Background de la malette(du gridpane)
	  BackgroundImage fond_malette= new BackgroundImage(new Image("/images/bordure.png",3*HardCodedParameters.defaultWidth/4,3*HardCodedParameters.defaultHeight/4,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT);
	  gridpane_malette.setBackground(new Background(fond_malette));
	  
	  //Zone en haut à gauche
	  Image image_bouton_rouge = new Image("/images/red-button.png");
	  ImageView zone1 = new ImageView();
	  zone1.setFitWidth((3*HardCodedParameters.defaultWidth/4)/3);
	  zone1.setFitHeight((3*HardCodedParameters.defaultHeight/4)/2);
	  zone1.setImage(image_bouton_rouge);
	  gridpane_malette.add(zone1,0,0);
	  
	  //Zone en bas à gauche
	  Image image_bouton_vert = new Image("/images/green-button.png");
	  ImageView zone2 = new ImageView();
	  zone2.setFitWidth((3*HardCodedParameters.defaultWidth/4)/3);
	  zone2.setFitHeight((3*HardCodedParameters.defaultHeight/4)/2);
	  zone2.setImage(image_bouton_vert);
	  gridpane_malette.add(zone2,0,1);
	  
	  //Zone en haut au milieu
	  Image image_bouton_vert2 = new Image("/images/green-button.png");
	  ImageView zone3 = new ImageView();
	  zone3.setFitWidth((3*HardCodedParameters.defaultWidth/4)/3);
	  zone3.setFitHeight((3*HardCodedParameters.defaultHeight/4)/2);
	  zone3.setImage(image_bouton_vert2);
	  gridpane_malette.add(zone3,1,0);
	  
	  //Zone en bas au milieu
	  Image image_bouton_rouge2 = new Image("/images/red-button.png");
	  ImageView zone4 = new ImageView();
	  zone4.setFitWidth((3*HardCodedParameters.defaultWidth/4)/3);
	  zone4.setFitHeight((3*HardCodedParameters.defaultHeight/4)/2);
	  zone4.setImage(image_bouton_rouge2);
	  gridpane_malette.add(zone4,1,1);
	  
	  //Zone en haut à droite
	  Image image_bouton_vert3 = new Image("/images/green-button.png");
	  ImageView zone5 = new ImageView();
	  zone5.setFitWidth((3*HardCodedParameters.defaultWidth/4)/3);
	  zone5.setFitHeight((3*HardCodedParameters.defaultHeight/4)/2);
	  zone5.setImage(image_bouton_vert3);
	  gridpane_malette.add(zone5,2,0);
	  
	  //Zone en bas à droite
	  Image image_bouton_rouge3 = new Image("/images/red-button.png");
	  ImageView zone6 = new ImageView();
	  zone6.setFitWidth((3*HardCodedParameters.defaultWidth/4)/3);
	  zone6.setFitHeight((3*HardCodedParameters.defaultHeight/4)/2);
	  zone6.setImage(image_bouton_rouge3);
	  gridpane_malette.add(zone6,2,1);
	  
	  
	  
	  
	  //Stats
	  Rectangle stats = new Rectangle(HardCodedParameters.defaultWidth/4,HardCodedParameters.defaultHeight);
	  stats.setLayoutX(3*HardCodedParameters.defaultWidth/4);
	  stats.setStroke(Color.RED);
	  stats.setFill(Color.GREY);
	  
	  Circle camembert = new Circle(100,  Color.rgb(255,0,0));
      Image camembert_img = new Image("/images/camembert_img.png");
      camembert.setFill(new ImagePattern(camembert_img));
      camembert.setEffect(new Lighting());
      camembert.setLayoutX(7*HardCodedParameters.defaultWidth/8);
      camembert.setLayoutY(HardCodedParameters.defaultHeight/4);
      
      
      
      
	  
      //Logs
	  Rectangle logs = new Rectangle(3*HardCodedParameters.defaultWidth/4,HardCodedParameters.defaultHeight/4);
	  logs.setLayoutY(3*HardCodedParameters.defaultHeight/4);
	  logs.setStroke(Color.RED);
	  logs.setFill(Color.BLACK);
	  
	  TextField textLogs = new TextField(">> Bienvenue !");
	  textLogs.setCursor(Cursor.DEFAULT);
	  textLogs.setEditable(false);
	  textLogs.setPrefHeight(HardCodedParameters.defaultHeight/4);
	  textLogs.setPrefWidth(3*HardCodedParameters.defaultWidth/4);
	  textLogs.setLayoutY(3*HardCodedParameters.defaultHeight/4);
	  textLogs.setStyle("-fx-text-fill: green; -fx-control-inner-background: black;");
	  
	  //Ajoute les élèments principaux dans les 3sous groupes
	  malette_group.getChildren().addAll(gridpane_malette);
	  logs_group.getChildren().addAll(logs, textLogs);  
	  stats_group.getChildren().addAll(stats, camembert);
	  
	  

	  //Ajoute les 3sous groupes
	  window.getChildren().addAll(malette_group,stats_group,logs_group);
	   
	  return window;
  }
}
