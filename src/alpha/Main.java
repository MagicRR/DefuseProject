/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package alpha;

import data.Data;
import engine.Engine;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import specifications.DataService;
import specifications.EngineService;
import specifications.ViewerService;
import tools.HardCodedParameters;
import javafx.stage.Screen;
import userInterface.Viewer;
import userInterface.Viewer_accueil;
import userInterface.Viewer_instruction;

public class Main extends Application{
	  //---HARD-CODED-PARAMETERS---//
	  private static String fileName = HardCodedParameters.defaultParamFileName;

	  //---VARIABLES---//
	  private static DataService data;
	  private static EngineService engine;
	  private static ViewerService viewer;
	  private static Viewer_accueil viewer_accueil;
	  private static Viewer_instruction viewer_instruction;
	  private static AnimationTimer timer;

	  //---EXECUTABLE---//
	  public static void main(String[] args) {
	    //readArguments(args);

	    data = new Data();
	    engine = new Engine();
	    viewer = new Viewer();
	    viewer_accueil = new Viewer_accueil();
	    viewer_instruction = new Viewer_instruction();

	    ((Engine)engine).bindDataService(data);
	    ((Viewer)viewer).bindReadService(data);
	    ((Viewer_accueil)viewer_accueil).bindReadService(data);
	    ((Viewer_instruction)viewer_instruction).bindReadService(data);

	    data.init();
	    engine.init();
	    viewer.init();
	    viewer_accueil.init();
	    viewer_instruction.init();

	    launch(args);
	  }

	  @Override 
	  public void start(Stage stage) {
		  
		  	//Récupère la taille de l'écran de l'utilisateur et adapte les HardCodedParameters
		    Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			stage.setX(bounds.getMinX());
			stage.setY(bounds.getMinY());
			HardCodedParameters.defaultWidth=bounds.getWidth();
			HardCodedParameters.defaultHeight=bounds.getHeight();
			stage.setWidth(HardCodedParameters.defaultWidth);
			stage.setHeight(HardCodedParameters.defaultHeight);			
		  
		    final Scene scene_accueil = new Scene(((Viewer_accueil)viewer_accueil).getPanel());
		    final Scene scene_instruction = new Scene(((Viewer_instruction)viewer_instruction).getPanel());
		    final Scene scene = new Scene(((Viewer)viewer).getPanel());
		    		    
			//Définis un titre à la fenetre + empeche de resize la fenetre et définis le fullscreen mode au démarrage
		    stage.setTitle("Defuse Project");
		    stage.setResizable(false);
		    //stage.setFullScreen(true);
		    
		    stage.setScene(scene_accueil);
		    stage.setWidth(HardCodedParameters.defaultWidth);
		    stage.setHeight(HardCodedParameters.defaultHeight);
		    
		    //Envoi de accueil au jeu
		    viewer_accueil.getJouer().setOnMousePressed(new EventHandler<MouseEvent>(){
			      @Override
			        public void handle(MouseEvent event) {			    	  
			    		    stage.setScene(scene);			                 
			    	  }
			    });
		    
		    //Envoi de accueil à instruction
		    viewer_accueil.getInstruction().setOnMousePressed(new EventHandler<MouseEvent>(){
			      @Override
			        public void handle(MouseEvent event) {
			    		    stage.setScene(scene_instruction);
			    	  }
			    });
		    
		    //Envoi de instruction au jeu
		    viewer_instruction.getJouer().setOnMousePressed(new EventHandler<MouseEvent>(){
			      @Override
			        public void handle(MouseEvent event) {
			    		    stage.setScene(scene);
			    	  }
			    });
		        
		    stage.setOnShown(new EventHandler<WindowEvent>() {
		      @Override
		        public void handle(WindowEvent event) {
		          engine.start();
		        }
		    });
		    
		    //Gère la fermeture de la fenetre lorsque l'on clique sur la croix en stoppant le programme
		    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		        @Override
		        public void handle(WindowEvent event) {
		            //event.consume();
			          engine.stop();
		        }
		    });
		    stage.show();
		    
		    timer = new AnimationTimer() {
		      @Override
		        public void handle(long l) {
		          scene.setRoot(((Viewer)viewer).getPanel());
		        }
		    };
		    
		    timer.start();
	  }
}
