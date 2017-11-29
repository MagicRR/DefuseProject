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
	  GridPane gridpane_malette = new GridPane();
	  
	  //Gestion des contraintes de colonne style padding ..
	  gridpane_malette.setPadding(new Insets(15,0,0,0));
	  gridpane_malette.setHgap(15);
	  gridpane_malette.setVgap(10);
	  
	  ColumnConstraints column1 = new ColumnConstraints(-10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3);
	  ColumnConstraints column2 = new ColumnConstraints(-10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3);
	  ColumnConstraints column3 = new ColumnConstraints(-10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3);
	  column1.setHgrow(Priority.ALWAYS);
	  column2.setHgrow(Priority.ALWAYS);
	  column3.setHgrow(Priority.ALWAYS);
	  
	  gridpane_malette.getColumnConstraints().addAll(column1, column2, column3);	  
	 
	  //Background de la malette(du gridpane)
	  BackgroundImage fond_malette= new BackgroundImage(new Image("/images/bordure.png",3*HardCodedParameters.defaultWidth/4,30+3*HardCodedParameters.defaultHeight/4,false,true),
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
	  gridpane_malette.setBackground(new Background(fond_malette));
	  
	  
	 
	 
	  //Zone en haut à gauche
	  Image image_block6 = new Image("/images/block.png");
	  ImageView zone1 = new ImageView();
	  zone1.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)-20);
	  zone1.setFitHeight(((3*HardCodedParameters.defaultHeight/4)/2)-8);
	  zone1.setImage(image_block6);
	  gridpane_malette.add(zone1,0,0);
	  GridPane.setMargin(zone1, new Insets(0, 0, 0, 20));
	  
	  //Zone en bas à gauche
	  Image image_block5 = new Image("/images/block.png");
	  ImageView zone2 = new ImageView();
	  zone2.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)-20);
	  zone2.setFitHeight(((3*HardCodedParameters.defaultHeight/4)/2)-15);
	  zone2.setImage(image_block5);
	  gridpane_malette.add(zone2,0,1);
	  GridPane.setMargin(zone2, new Insets(-5, 0, 0, 20));
	  
	  //Zone en haut au milieu
	  Image boutton_rouge = new Image("/images/red-button.png");
	  ImageView zone3 = new ImageView();
	  zone3.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)-35);
	  zone3.setFitHeight(((3*HardCodedParameters.defaultHeight/4)/2)-8);
	  zone3.setImage(boutton_rouge);
	  gridpane_malette.add(zone3,1,0);
	  GridPane.setMargin(zone3, new Insets(0, 0, 0, 13));
	  
	  //Zone en bas au milieu, gridpane simon	  
	  //GridPane du simon
	  GridPane gridpane_simon = new GridPane();
	  
	  Rectangle rec_simon1 = new Rectangle(-33+((3*HardCodedParameters.defaultWidth/4)/3)/2, 5+((HardCodedParameters.defaultHeight/3)/2));
	  Image simon_rouge = new Image("/images/simon-rouge.png");
	  rec_simon1.setFill(new ImagePattern(simon_rouge));
	  
	  Rectangle rec_simon2 = new Rectangle(-33+((3*HardCodedParameters.defaultWidth/4)/3)/2, 5+((HardCodedParameters.defaultHeight/3)/2));
	  Image simon_vert = new Image("/images/simon-vert.png");
	  rec_simon2.setFill(new ImagePattern(simon_vert));
	  
	  Rectangle rec_simon3 = new Rectangle(-33+((3*HardCodedParameters.defaultWidth/4)/3)/2, 5+((HardCodedParameters.defaultHeight/3)/2));
	  Image simon_jaune = new Image("/images/simon-jaune.png");
	  rec_simon3.setFill(new ImagePattern(simon_jaune));
	  
	  Rectangle rec_simon4 = new Rectangle(-33+((3*HardCodedParameters.defaultWidth/4)/3)/2, 5+((HardCodedParameters.defaultHeight/3)/2));
	  Image simon_bleu = new Image("/images/simon-bleu.png");
	  rec_simon4.setFill(new ImagePattern(simon_bleu));

	 
	  Button but_simon1 = new Button();
	  but_simon1.setGraphic(rec_simon1);
	  gridpane_simon.add(but_simon1,0,0);
	  
	  Button but_simon2 = new Button();
	  but_simon2.setGraphic(rec_simon2);
	  gridpane_simon.add(but_simon2,1,0);
	  
	  Button but_simon3 = new Button();
	  but_simon3.setGraphic(rec_simon3);
	  gridpane_simon.add(but_simon3,1,1);
	  
	  Button but_simon4 = new Button();
	  but_simon4.setGraphic(rec_simon4);
	  gridpane_simon.add(but_simon4,0,1);
	  	  
	  gridpane_malette.add(gridpane_simon,1,1);	  
	  GridPane.setMargin(gridpane_simon, new Insets(5, 0, 0, 13));
	  
	  
	  //Zone en haut à droite
	  /*Image pose_cable = new Image("/images/pose-cable.png");
	  ImageView zone5 = new ImageView();
	  zone5.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)-20);
	  zone5.setFitHeight(((3*HardCodedParameters.defaultHeight/4)/2)-8);
	  zone5.setImage(pose_cable);
	  gridpane_malette.add(zone5,2,0);
	  GridPane.setMargin(zone5, new Insets(0, 0, 0, -10));*/
	  
	  //gridpane cable	  	  
	  GridPane gridpane_cable = new GridPane();
	  
	  //Gestion des contraintes de colonne style padding ..
	  gridpane_cable.setPadding(new Insets(15,0,0,0));
	  gridpane_cable.setHgap(15);
	  gridpane_cable.setVgap(10);
	  
	  ColumnConstraints colonne1 = new ColumnConstraints(-10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3, -10 + (3*HardCodedParameters.defaultWidth/4)/3);
	  colonne1.setHgrow(Priority.ALWAYS);

	  
	  gridpane_cable.getColumnConstraints().addAll(colonne1); 
	 
	  //Background des cables
	  BackgroundImage pose_cable= new BackgroundImage(new Image("/images/pose-cable.png",((3*HardCodedParameters.defaultWidth/4)/3)-20, ((3*HardCodedParameters.defaultHeight/4)/2)-8,false,true),
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
	  gridpane_cable.setBackground(new Background(pose_cable));
	  	
	  //Premier cable
	  Image cable_rouge = new Image("/images/cable-red.png");
	  ImageView zone5_1 = new ImageView();
	  zone5_1.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)-70);
	  zone5_1.setFitHeight((((3*HardCodedParameters.defaultHeight/4)/2)/4)-8);
	  zone5_1.setImage(cable_rouge);
	  gridpane_cable.add(zone5_1,0,0);
	  GridPane.setMargin(zone5_1, new Insets(-15, 0, 0, 40));
	  
	  //Deuxieme cable
	  Image cable_jaune = new Image("/images/cable-yellow-cut.png");
	  ImageView zone5_2 = new ImageView();
	  zone5_2.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)-70);
	  zone5_2.setFitHeight((((3*HardCodedParameters.defaultHeight/4)/2)/4)-8);
	  zone5_2.setImage(cable_jaune);
	  gridpane_cable.add(zone5_2,0,1);
	  GridPane.setMargin(zone5_2, new Insets(-30, 0, 0, 45));
	  
	  //Troisième cable
	  Image cable_vert = new Image("/images/cable-green.png");
	  ImageView zone5_3 = new ImageView();
	  zone5_3.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)-70);
	  zone5_3.setFitHeight((((3*HardCodedParameters.defaultHeight/4)/2)/4)-8);
	  zone5_3.setImage(cable_vert);
	  gridpane_cable.add(zone5_3,0,2);
	  GridPane.setMargin(zone5_3, new Insets(-20, 0, 0, 45));
	  
	  //Quatrieme cable
	  Image cable_bleu = new Image("/images/cable-blue.png");
	  ImageView zone5_4 = new ImageView();
	  zone5_4.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)-70);
	  zone5_4.setFitHeight((((3*HardCodedParameters.defaultHeight/4)/2)/4)-8);
	  zone5_4.setImage(cable_bleu);
	  gridpane_cable.add(zone5_4,0,3);
	  GridPane.setMargin(zone5_4, new Insets(-25, 0, 0, 50));
	  
	  gridpane_malette.add(gridpane_cable,2,0);

	  
	  
	  //Zone en bas à droite
	  /*Image image_block2 = new Image("/images/block.png");
	  ImageView zone6 = new ImageView();
	  zone6.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)-20);
	  zone6.setFitHeight(((3*HardCodedParameters.defaultHeight/4)/2)-15);
	  zone6.setImage(image_block2);
	  gridpane_malette.add(zone6,2,1);
	  GridPane.setMargin(zone6, new Insets(-5, 0, 0, -10));*/
	  	  
	  //gridpane numérique	  	  
	  GridPane gridpane_code = new GridPane();
	  
	  //Gestion des contraintes de colonne style padding ..
	  gridpane_code.setPadding(new Insets(((3*HardCodedParameters.defaultWidth/4)/3)/3,0,0,50));
	  gridpane_code.setHgap(15);
	  gridpane_code.setVgap(10);
	  
	  ColumnConstraints col1 = new ColumnConstraints(-10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5, -10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5, -10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5);
	  ColumnConstraints col2 = new ColumnConstraints(-10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5, -10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5, -10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5);
	  ColumnConstraints col3 = new ColumnConstraints(-10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5, -10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5, -10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5);
	  ColumnConstraints col4 = new ColumnConstraints(-10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5, -10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5, -10 + ((3*HardCodedParameters.defaultWidth/4)/3)/5); 
	  col1.setHgrow(Priority.ALWAYS);
	  col2.setHgrow(Priority.ALWAYS);
	  col3.setHgrow(Priority.ALWAYS);
	  col4.setHgrow(Priority.ALWAYS);
 
	  gridpane_code.getColumnConstraints().addAll(col1,col2,col3,col4);
	  	 
	  //Background du code numérique
	  BackgroundImage cadenas = new BackgroundImage(new Image("/images/Cadenas.png",((3*HardCodedParameters.defaultWidth/4)/3)-20, ((3*HardCodedParameters.defaultHeight/4)/2)-8,false,true),
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
	  gridpane_code.setBackground(new Background(cadenas));
	  	
	  //Premier chiffre
	  Image chiffre1 = new Image("/images/chiffre-1.png");
	  ImageView zone6_1 = new ImageView();
	  zone6_1.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)/6);
	  zone6_1.setFitHeight(((3*HardCodedParameters.defaultHeight/4)/2)/6);
	  zone6_1.setImage(chiffre1);
	  gridpane_code.add(zone6_1,0,0);
	  GridPane.setMargin(zone6_1, new Insets(((3*HardCodedParameters.defaultHeight/4)/2)/10, 0, 0, ((3*HardCodedParameters.defaultWidth/4)/3)/8));
	  
	  //Deuxieme chiffre
	  Image chiffre2 = new Image("/images/chiffre-2.png");
	  ImageView zone6_2 = new ImageView();
	  zone6_2.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)/6);
	  zone6_2.setFitHeight(((3*HardCodedParameters.defaultHeight/4)/2)/6);
	  zone6_2.setImage(chiffre2);
	  gridpane_code.add(zone6_2,1,0);
	  GridPane.setMargin(zone6_2, new Insets(((3*HardCodedParameters.defaultHeight/4)/2)/10, 0, 0, ((3*HardCodedParameters.defaultWidth/4)/3)/8));
	  
	  //Troisième chiffre
	  Image chiffre3 = new Image("/images/chiffre-3.png");
	  ImageView zone6_3 = new ImageView();
	  zone6_3.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)/6);
	  zone6_3.setFitHeight(((3*HardCodedParameters.defaultHeight/4)/2)/6);
	  zone6_3.setImage(chiffre3);
	  gridpane_code.add(zone6_3,0,1);
	  GridPane.setMargin(zone6_3, new Insets(0, 0, 0, ((3*HardCodedParameters.defaultWidth/4)/3)/8));
	  
	  //Quatrième chiffre
	  Image chiffre4 = new Image("/images/chiffre-4.png");
	  ImageView zone6_4 = new ImageView();
	  zone6_4.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)/6);
	  zone6_4.setFitHeight(((3*HardCodedParameters.defaultHeight/4)/2)/6);
	  zone6_4.setImage(chiffre4);
	  gridpane_code.add(zone6_4,1,1);
	  GridPane.setMargin(zone6_4, new Insets(0, 0, 0, ((3*HardCodedParameters.defaultWidth/4)/3)/8));
	  
	  gridpane_malette.add(gridpane_code,2,1);

	  
	  
	  
	  
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
	  textLogs.autosize();
	  textLogs.setPrefHeight(HardCodedParameters.defaultHeight/4);
	  textLogs.setPrefWidth(3*HardCodedParameters.defaultWidth/4);
	  textLogs.setLayoutY(3*HardCodedParameters.defaultHeight/4);
	  textLogs.setAlignment(Pos.TOP_LEFT);
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
