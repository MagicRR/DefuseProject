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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import specifications.DataService;
import specifications.EngineService;
import specifications.ViewerService;
import tools.HardCodedParameters;
import tools.User;
import javafx.stage.Screen;
import userInterface.Viewer;

public class Main extends Application{
	  //---HARD-CODED-PARAMETERS---//
	  private static String fileName = HardCodedParameters.defaultParamFileName;

	  //---VARIABLES---//
	  private static DataService data;
	  private static EngineService engine;
	  private static ViewerService viewer;
	  private static AnimationTimer timer;

	  //---EXECUTABLE---//
	  public static void main(String[] args) {
	    //readArguments(args);

	    data = new Data();
	    engine = new Engine();
	    viewer = new Viewer();

	    ((Engine)engine).bindDataService(data);
	    ((Viewer)viewer).bindReadService(data);

	    data.init();
	    engine.init();
	    viewer.init();

	    launch(args);
	  }

	  @Override 
	  public void start(Stage stage) {
		  
		    final Scene scene = new Scene(((Viewer)viewer).getPanel());
	
		    scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
		      @Override
		        public void handle(KeyEvent event) {
		    	  		//
		    	  }
		    });
		    
		    //Récupère la taille de l'écran de l'utilisateur et adapte les HardCodedParameters
		    Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			stage.setX(bounds.getMinX());
			stage.setY(bounds.getMinY());
			HardCodedParameters.defaultWidth=bounds.getWidth();
			HardCodedParameters.defaultHeight=bounds.getHeight();
			stage.setWidth(bounds.getWidth());
			stage.setHeight(bounds.getHeight());			  
		    
			//Définis un titre à la fenetre + empeche de resize la fenetre et définis le fullscreen mode au démarrage
		    stage.setTitle("Defuse Project");
		    stage.setResizable(false);
		    //stage.setFullScreen(true);
		    
		    stage.setScene(scene);
		    stage.setWidth(HardCodedParameters.defaultWidth);
		    stage.setHeight(HardCodedParameters.defaultHeight);
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

	  //---ARGUMENTS---//
	  private static void readArguments(String[] args){
	    if (args.length>0 && args[0].charAt(0)!='-'){
	      System.err.println("Syntax error: use option -h for help.");
	      return;
	    }
	    for (int i=0;i<args.length;i++){
	      if (args[i].charAt(0)=='-'){
		if (args[i+1].charAt(0)=='-'){
		  System.err.println("Option "+args[i]+" expects an argument but received none.");
		  return;
		}
		switch (args[i]){
		  case "-inFile":
		    fileName=args[i+1];
		    break;
		  case "-h":
		    System.out.println("Options:");
		    System.out.println(" -inFile FILENAME: (UNUSED AT THE MOMENT) set file name for input parameters. Default name is"+HardCodedParameters.defaultParamFileName+".");
		    break;
		  default:
		    System.err.println("Unknown option "+args[i]+".");
		    return;
		}
		i++;
	      }
	    }
	  }
	}
