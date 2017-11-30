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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import data.Minuteur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.ImagePattern;

public class Viewer implements ViewerService, RequireReadService{
	
  private ReadService data;
  private GridPane gridpane_malette;
  private TextField timer;
  private Button boutton_rouge;
  private Button but_simon1;
  private Button but_simon2;
  private Button but_simon3;
  private Button but_simon4;
  private Button zone_cable_1;
  private Button zone_cable_2;
  private Button zone_cable_3;
  private Button zone_cable_4;
  private Button zone_chiffre_1;
  private Button zone_chiffre_2;
  private Button zone_chiffre_3;
  private Button zone_chiffre_4;
  private ImageView code_alpha;
  private Image camembert_img;
  private Circle camembert;
  private TextField textLogs;


  public Viewer(){}

  @Override
  public void bindReadService(ReadService service){
    data=service;
  }

  @Override
  public void init(){}

  @Override
  public Parent getPanel(){
	  
	  //Groupe de l'interface global
	  Group window = new Group();
	  
	  //3 sous groupes
	  Group malette_group = new Group();
	  Group logs_group = new Group();
	  Group stats_group = new Group();	  
	  
	  //Malette	  	  
	  gridpane_malette = new GridPane();
	  
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
	  timer = new TextField("01:00");
	  timer.setCursor(Cursor.DEFAULT);
	  timer.setEditable(false);
	  timer.setPrefHeight((3*HardCodedParameters.defaultHeight/4)/2-8);
	  timer.setAlignment(Pos.CENTER);
	  timer.setPrefColumnCount(5);
	  timer.setFont(javafx.scene.text.Font.loadFont("file:src/fonts/DS-DIGI.TTF", ((3*HardCodedParameters.defaultWidth/4)/3)/5));
	  timer.setStyle("-fx-text-fill: red; -fx-control-inner-background: black;");
	  gridpane_malette.add(timer,0,0);
	  GridPane.setMargin(timer, new Insets(0, 0, 0, 20));
	  
	  //Zone en bas à gauche
	  Image image_alpha = new Image("/images/block.png");
	  ImageView code_alpha = new ImageView();
	  code_alpha.setFitWidth(((3*HardCodedParameters.defaultWidth/4)/3)-20);
	  code_alpha.setFitHeight(((3*HardCodedParameters.defaultHeight/4)/2)-15);
	  code_alpha.setImage(image_alpha);
	  gridpane_malette.add(code_alpha,0,1);
	  GridPane.setMargin(code_alpha, new Insets(-5, 0, 0, 20));
	  
	  //Zone en haut au milieu
	  Image red_button = new Image("/images/red-button.png");
	  Rectangle rec_bouton_rouge = new Rectangle(((3*HardCodedParameters.defaultWidth/4)/4), ((3*HardCodedParameters.defaultHeight/4)/3));
	  rec_bouton_rouge.setFill(new ImagePattern(red_button));
	  boutton_rouge = new Button();
	  boutton_rouge.setGraphic(rec_bouton_rouge);
	  boutton_rouge.setStyle("-fx-background-color: transparent;");
	  gridpane_malette.add(boutton_rouge,1,0);
	  GridPane.setMargin(boutton_rouge, new Insets(0, 0, 0, ((3*HardCodedParameters.defaultWidth/4)/3)/11));
	  
	  boutton_rouge.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    		System.out.println("plop, ca appui");
		    }
	  });
	  
	  
	  
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

	 
	  but_simon1 = new Button();
	  but_simon1.setGraphic(rec_simon1);
	  but_simon1.setStyle("-fx-background-color: transparent;");
	  gridpane_simon.add(but_simon1,0,0);
	  
	  but_simon2 = new Button();
	  but_simon2.setGraphic(rec_simon2);
	  but_simon2.setStyle("-fx-background-color: transparent;");
	  gridpane_simon.add(but_simon2,1,0);
	  
	  but_simon3 = new Button();
	  but_simon3.setGraphic(rec_simon3);
	  but_simon3.setStyle("-fx-background-color: transparent;");
	  gridpane_simon.add(but_simon3,1,1);
	  
	  but_simon4 = new Button();
	  but_simon4.setGraphic(rec_simon4);
	  but_simon4.setStyle("-fx-background-color: transparent;");
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
	  zone_cable_1 = new Button();
	  Rectangle rec_cable1 = new Rectangle(((3*HardCodedParameters.defaultWidth/4)/3)-70, (((3*HardCodedParameters.defaultHeight/4)/2)/4)-8);
	  Image cable_rouge = new Image("/images/cable-red.png");
	  rec_cable1.setFill(new ImagePattern(cable_rouge));
	  zone_cable_1.setGraphic(rec_cable1);
	  zone_cable_1.setStyle("-fx-background-color: transparent;");
	  gridpane_cable.add(zone_cable_1,0,0);
	  GridPane.setMargin(zone_cable_1, new Insets(-20, 0, 0, 40));
	  
	  //Deuxieme cable
	  zone_cable_2 = new Button();
	  Rectangle rec_cable2 = new Rectangle(((3*HardCodedParameters.defaultWidth/4)/3)-70, (((3*HardCodedParameters.defaultHeight/4)/2)/4)-8);
	  Image cable_jaune = new Image("/images/cable-yellow-cut.png");
	  rec_cable2.setFill(new ImagePattern(cable_jaune));
	  zone_cable_2.setGraphic(rec_cable2);
	  zone_cable_2.setStyle("-fx-background-color: transparent;");
	  gridpane_cable.add(zone_cable_2,0,1);
	  GridPane.setMargin(zone_cable_2, new Insets(10, 0, 0, 45));
	  
	  //Troisième cable
	  zone_cable_3 = new Button();
	  Rectangle rec_cable3 = new Rectangle(((3*HardCodedParameters.defaultWidth/4)/3)-70, (((3*HardCodedParameters.defaultHeight/4)/2)/4)-8);
	  Image cable_vert = new Image("/images/cable-green.png");
	  rec_cable3.setFill(new ImagePattern(cable_vert));
	  zone_cable_3.setGraphic(rec_cable3);
	  zone_cable_3.setStyle("-fx-background-color: transparent;");
	  gridpane_cable.add(zone_cable_3,0,2);
	  GridPane.setMargin(zone_cable_3, new Insets(-25, 0, 0, 45));
	  
	  //Quatrieme cable
	  zone_cable_4 = new Button();
	  Rectangle rec_cable4 = new Rectangle(((3*HardCodedParameters.defaultWidth/4)/3)-70, (((3*HardCodedParameters.defaultHeight/4)/2)/4)-8);
	  Image cable_bleu = new Image("/images/cable-blue.png");
	  rec_cable4.setFill(new ImagePattern(cable_bleu));
	  zone_cable_4.setGraphic(rec_cable4);
	  zone_cable_4.setStyle("-fx-background-color: transparent;");
	  gridpane_cable.add(zone_cable_4,0,3);
	  GridPane.setMargin(zone_cable_4, new Insets(-30, 0, 0, 50));
	  
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
	  zone_chiffre_1 = new Button();
	  Rectangle rec_chiffre1 = new Rectangle(((3*HardCodedParameters.defaultWidth/4)/3)/6, ((3*HardCodedParameters.defaultHeight/4)/2)/6);
	  Image chiffre1 = new Image("/images/chiffre-1.png");
	  rec_chiffre1.setFill(new ImagePattern(chiffre1));
	  zone_chiffre_1.setGraphic(rec_chiffre1);
	  zone_chiffre_1.setStyle("-fx-background-color: transparent;");
	  gridpane_code.add(zone_chiffre_1,0,0);
	  GridPane.setMargin(zone_chiffre_1, new Insets(((3*HardCodedParameters.defaultHeight/4)/2)/10, 0, 0, ((3*HardCodedParameters.defaultWidth/4)/3)/8));
	  
	  //Deuxieme chiffre
	  zone_chiffre_2 = new Button();
	  Rectangle rec_chiffre2 = new Rectangle(((3*HardCodedParameters.defaultWidth/4)/3)/6, ((3*HardCodedParameters.defaultHeight/4)/2)/6);
	  Image chiffre2 = new Image("/images/chiffre-2.png");
	  rec_chiffre2.setFill(new ImagePattern(chiffre2));
	  zone_chiffre_2.setGraphic(rec_chiffre2);
	  zone_chiffre_2.setStyle("-fx-background-color: transparent;");
	  gridpane_code.add(zone_chiffre_2,1,0);
	  GridPane.setMargin(zone_chiffre_2, new Insets(((3*HardCodedParameters.defaultHeight/4)/2)/10, 0, 0, ((3*HardCodedParameters.defaultWidth/4)/3)/8));
	  
	  //Troisième chiffre
	  zone_chiffre_3 = new Button();
	  Rectangle rec_chiffre3 = new Rectangle(((3*HardCodedParameters.defaultWidth/4)/3)/6, ((3*HardCodedParameters.defaultHeight/4)/2)/6);
	  Image chiffre3 = new Image("/images/chiffre-3.png");
	  rec_chiffre3.setFill(new ImagePattern(chiffre3));
	  zone_chiffre_3.setGraphic(rec_chiffre3);
	  zone_chiffre_3.setStyle("-fx-background-color: transparent;");
	  gridpane_code.add(zone_chiffre_3,0,1);
	  GridPane.setMargin(zone_chiffre_3, new Insets(0, 0, 0, ((3*HardCodedParameters.defaultWidth/4)/3)/8));
	  
	  //Quatrième chiffre
	  zone_chiffre_4 = new Button();
	  Rectangle rec_chiffre4 = new Rectangle(((3*HardCodedParameters.defaultWidth/4)/3)/6, ((3*HardCodedParameters.defaultHeight/4)/2)/6);
	  Image chiffre4 = new Image("/images/chiffre-4.png");
	  rec_chiffre4.setFill(new ImagePattern(chiffre4));
	  zone_chiffre_4.setGraphic(rec_chiffre4);
	  zone_chiffre_4.setStyle("-fx-background-color: transparent;");
	  gridpane_code.add(zone_chiffre_4,1,1);
	  GridPane.setMargin(zone_chiffre_4, new Insets(0, 0, 0, ((3*HardCodedParameters.defaultWidth/4)/3)/8));
	  
	  gridpane_malette.add(gridpane_code,2,1);

	  
	  
	  
	  
	  //Stats
	  Rectangle stats = new Rectangle(HardCodedParameters.defaultWidth/4,HardCodedParameters.defaultHeight);
	  stats.setLayoutX(3*HardCodedParameters.defaultWidth/4);
	  stats.setStroke(Color.RED);
	  stats.setFill(Color.GREY);
	  
	  camembert = new Circle(100,  Color.rgb(255,0,0));
      Image camembert_img = new Image("/images/camembert_img.png");
      camembert.setFill(new ImagePattern(camembert_img));
      camembert.setEffect(new Lighting());
      camembert.setLayoutX(7*HardCodedParameters.defaultWidth/8);
      camembert.setLayoutY(HardCodedParameters.defaultHeight/4);
      
      
      
      
	  
      //Logs
	  textLogs = new TextField(">> Bienvenue !\n");
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
	  logs_group.getChildren().addAll( textLogs);  
	  stats_group.getChildren().addAll(stats, camembert);
	  
	  

	  //Ajoute les 3sous groupes
	  window.getChildren().addAll(malette_group,stats_group,logs_group);
	   
	  return window;
  }

	public ReadService getData() {
		return data;
	}
	
	public void setData(ReadService data) {
		this.data = data;
	}
	
	public GridPane getGridpane_malette() {
		return gridpane_malette;
	}

	public void setGridpane_malette(GridPane gridpane_malette) {
		this.gridpane_malette = gridpane_malette;
	}

	public TextField getTimer() {
		return timer;
	}
	
	public void setTimer(TextField timer) {
		this.timer = timer;
	}
	
	public Button getBoutton_rouge() {
		return boutton_rouge;
	}
	
	public void setBoutton_rouge(Button boutton_rouge) {
		this.boutton_rouge = boutton_rouge;
	}
	
	public Button getBut_simon1() {
		return but_simon1;
	}
	
	public void setBut_simon1(Button but_simon1) {
		this.but_simon1 = but_simon1;
	}
	
	public Button getBut_simon2() {
		return but_simon2;
	}
	
	public void setBut_simon2(Button but_simon2) {
		this.but_simon2 = but_simon2;
	}
	
	public Button getBut_simon3() {
		return but_simon3;
	}
	
	public void setBut_simon3(Button but_simon3) {
		this.but_simon3 = but_simon3;
	}
	
	public Button getBut_simon4() {
		return but_simon4;
	}
	
	public void setBut_simon4(Button but_simon4) {
		this.but_simon4 = but_simon4;
	}
	
	public Button getZone_cable_1() {
		return zone_cable_1;
	}
	
	public void setZone_cable_1(Button zone_cable_1) {
		this.zone_cable_1 = zone_cable_1;
	}
	
	public Button getZone_cable_2() {
		return zone_cable_2;
	}
	
	public void setZone_cable_2(Button zone_cable_2) {
		this.zone_cable_2 = zone_cable_2;
	}
	
	public Button getZone_cable_3() {
		return zone_cable_3;
	}
	
	public void setZone_cable_3(Button zone_cable_3) {
		this.zone_cable_3 = zone_cable_3;
	}
	
	public Button getZone_cable_4() {
		return zone_cable_4;
	}
	
	public void setZone_cable_4(Button zone_cable_4) {
		this.zone_cable_4 = zone_cable_4;
	}
	
	public Button getZone_chiffre_1() {
		return zone_chiffre_1;
	}
	
	public void setZone_chiffre_1(Button zone_chiffre_1) {
		this.zone_chiffre_1 = zone_chiffre_1;
	}
	
	public Button getZone_chiffre_2() {
		return zone_chiffre_2;
	}
	
	public void setZone_chiffre_2(Button zone_chiffre_2) {
		this.zone_chiffre_2 = zone_chiffre_2;
	}
	
	public Button getZone_chiffre_3() {
		return zone_chiffre_3;
	}
	
	public void setZone_chiffre_3(Button zone_chiffre_3) {
		this.zone_chiffre_3 = zone_chiffre_3;
	}
	
	public Button getZone_chiffre_4() {
		return zone_chiffre_4;
	}
	
	public void setZone_chiffre_4(Button zone_chiffre_4) {
		this.zone_chiffre_4 = zone_chiffre_4;
	}
	
	public ImageView getCode_alpha() {
		return code_alpha;
	}
	
	public void setCode_alpha(ImageView code_alpha) {
		this.code_alpha = code_alpha;
	}
	
	public Circle getCamembert() {
		return camembert;
	}
	
	public void setCamembert(Circle camembert) {
		this.camembert = camembert;
	}
	
	public TextField getTextLogs() {
		return textLogs;
	}
	
	public void setTextLogs(TextField textLogs) {
		this.textLogs = textLogs;
	}

	public Image getCamembert_img() {
		return camembert_img;
	}

	public void setCamembert_img(Image camembert_img) {
		this.camembert_img = camembert_img;
	}

}
