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

public class Main extends Application{
	  //---HARD-CODED-PARAMETERS---//
	  private static String fileName = HardCodedParameters.defaultParamFileName;

	  //---VARIABLES---//
	  private static DataService data;
	  private static EngineService engine;
	  private static ViewerService viewer;
	  private static Viewer_accueil viewer_accueil;
	  private static AnimationTimer timer;

	  //---EXECUTABLE---//
	  public static void main(String[] args) {
	    //readArguments(args);

	    data = new Data();
	    engine = new Engine();
	    viewer = new Viewer();
	    viewer_accueil = new Viewer_accueil();

	    ((Engine)engine).bindDataService(data);
	    ((Viewer)viewer).bindReadService(data);
	    ((Viewer_accueil)viewer_accueil).bindReadService(data);

	    data.init();
	    engine.init();
	    viewer.init();
	    viewer_accueil.init();

	    launch(args);
	  }

	  @Override 
	  public void start(Stage stage) {
		  
		  	//R�cup�re la taille de l'�cran de l'utilisateur et adapte les HardCodedParameters
		    Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			stage.setX(bounds.getMinX());
			stage.setY(bounds.getMinY());
			HardCodedParameters.defaultWidth=bounds.getWidth();
			HardCodedParameters.defaultHeight=bounds.getHeight();
			stage.setWidth(HardCodedParameters.defaultWidth);
			stage.setHeight(HardCodedParameters.defaultHeight);			
		  
		    final Scene scene_accueil = new Scene(((Viewer_accueil)viewer_accueil).getPanel());
		    final Scene scene = new Scene(((Viewer)viewer).getPanel());
		    		    
			//D�finis un titre � la fenetre + empeche de resize la fenetre et d�finis le fullscreen mode au d�marrage
		    stage.setTitle("Defuse Project");
		    stage.setResizable(false);
		    //stage.setFullScreen(true);
		    
		    stage.setScene(scene_accueil);
		    stage.setWidth(HardCodedParameters.defaultWidth);
		    stage.setHeight(HardCodedParameters.defaultHeight);
		    
		    viewer_accueil.getJouer().setOnMousePressed(new EventHandler<MouseEvent>(){
			      @Override
			        public void handle(MouseEvent event) {
			    	  
			            if(event.getSource().equals(scene_accueil.lookup("#jouer"))) {
			    		    stage.setScene(scene);
			            }			            
			    	  }
			    });
		        
		    stage.setOnShown(new EventHandler<WindowEvent>() {
		      @Override
		        public void handle(WindowEvent event) {
		          engine.start();
		        }
		    });
		    
		    //G�re la fermeture de la fenetre lorsque l'on clique sur la croix en stoppant le programme
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
