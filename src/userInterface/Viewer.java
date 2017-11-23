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

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;

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
	  Rectangle malette = new Rectangle(3*HardCodedParameters.defaultWidth/4,3*HardCodedParameters.defaultHeight/4);
	  Image img = new Image("/images/malette.png");
	  malette.setFill(new ImagePattern(img));
	  malette.setStroke(Color.RED);
	  
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
	  logs.setFill(Color.GREY);
	  
	  TextArea textLogs = new TextArea("Logs");
	  textLogs.setPrefHeight(HardCodedParameters.defaultHeight/4);
	  textLogs.setPrefWidth(3*HardCodedParameters.defaultWidth/4);
	  textLogs.setEditable(false);
	  textLogs.setLayoutY(3*HardCodedParameters.defaultHeight/4);
	  
	  //Ajoute les élèments principaux dans les 3sous groupes
	  malette_group.getChildren().addAll(malette);
	  logs_group.getChildren().addAll(logs, textLogs);  
	  stats_group.getChildren().addAll(stats, camembert);  

	  //Ajoute les 3sous groupes
	  window.getChildren().addAll(malette_group,stats_group,logs_group);
	   
	  return window;
  }
}
