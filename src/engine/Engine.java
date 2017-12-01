/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package engine;

import specifications.EngineService;
import specifications.EnigmeBoardService;
import specifications.EnigmeService;
import specifications.IndiceService;
import specifications.MalletteService;
import specifications.DataService;
import specifications.RequireDataService;
import specifications.RequireMalletteService;
import userInterface.Viewer;
import specifications.RequireEnigmeBoardService;
import specifications.RequireEnigmeService;
import specifications.RequireIndiceService;

import java.util.Timer;
import java.util.TimerTask;

import data.Data;
import data.Enigme;
import data.EnigmeBoard;
import data.Indice;
import data.Minuteur;
import data.Module;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Engine implements EventHandler{
	
	private final Stage primaryStage;
	private final Viewer view = new Viewer(this);
	private final Data data = new Data();
	private Random gen = new Random();
    private Timer engineClock = new Timer();
//    private Timer countdown = new Timer();
	
	private int delaiMinuteur = 300;
	private int finalCountdown;
	private int finalCountdownFormatedMinutes;
	private int finalCountdownFormatedSeconds;
	
	private String lettre1 = "C";
	private String lettre2 = "O";
	private String lettre3 = "D";
	private String lettre4 = "E";
	
	private int a;
	
	// ACTIVER LES LOGS OU NON. 1 = DESACTIVE
	private int disableConsoleLogs = 0;
	
	public Engine(final Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void handle(Event event) {
		
		final Object source = event.getSource();
		System.out.println("In handler !!");
		
        if (source.equals(view.getBoutton_rouge())) {
            view.getRec_bouton_rouge().setFill(new ImagePattern(view.getButton_green_img()));
        }
        
        if (source.equals(view.getZone_cable_1())) {
        	view.getRec_cable1().setFill(new ImagePattern(view.getCable_rouge_cut()));
        }
        
        if (source.equals(view.getZone_cable_2())) {
        	view.getRec_cable2().setFill(new ImagePattern(view.getCable_jaune_cut()));
        }
        
        if (source.equals(view.getZone_cable_3())) {
        	view.getRec_cable3().setFill(new ImagePattern(view.getCable_vert_cut()));
        }
        
        if (source.equals(view.getZone_cable_4())) {
        	view.getRec_cable4().setFill(new ImagePattern(view.getCable_bleu_cut()));
        }
        
        if (source.equals(view.getIndice())) {
        	view.getTextLogs().setText(libelleDeLIndice);
        }
        
        if (source.equals(view.getBouton_up1())) {
        	
        	   boolean trouve = false;
        	   int i = 0;
        	   
               while(!trouve){  
            	
                  if(view.getAlphabet().get(i).equals(view.getLettre1().getText())) {
                	  
                	  if(view.getAlphabet().get(25).equals(view.getLettre1().getText())) {
                		  view.getLettre1().setText("A");
                	  }else {
                		  view.getLettre1().setText(new String((String)view.getAlphabet().get(i+1)));
                	  }
                	  
                	  checkIfCodeIsValidated();

                	  trouve = true;  
                  }
       
                  i++;
               } 
        }
        
        if (source.equals(view.getBouton_up2())) {
        	 boolean trouve = false;
      	   	 int i = 0;
      	   
             while(!trouve){  
          	
                if(view.getAlphabet().get(i).equals(view.getLettre2().getText())) {
              	  
              	  if(view.getAlphabet().get(25).equals(view.getLettre2().getText())) {
              		  view.getLettre2().setText("A");
              	  }else {
              		  view.getLettre2().setText(new String((String)view.getAlphabet().get(i+1)));
              	  }
              	  
              	  checkIfCodeIsValidated();

              	  trouve = true;  
                }
     
                i++;
             } 
        }
        
        if (source.equals(view.getBouton_up3())) {
        	 boolean trouve = false;
      	   	 int i = 0;
      	   
             while(!trouve){  
          	
                if(view.getAlphabet().get(i).equals(view.getLettre3().getText())) {
              	  
              	  if(view.getAlphabet().get(25).equals(view.getLettre3().getText())) {
              		  view.getLettre3().setText("A");
              	  }else {
              		  view.getLettre3().setText(new String((String)view.getAlphabet().get(i+1)));
              	  }
              	  
              	  checkIfCodeIsValidated();

              	  trouve = true;  
                }
     
                i++;
             } 
        }
        
        if (source.equals(view.getBouton_up4())) {
        	 boolean trouve = false;
      	     int i = 0;
      	   
             while(!trouve){  
          	
                if(view.getAlphabet().get(i).equals(view.getLettre4().getText())) {
              	  
              	  if(view.getAlphabet().get(25).equals(view.getLettre4().getText())) {
              		  view.getLettre4().setText("A");
              	  }else {
              		  view.getLettre4().setText(new String((String)view.getAlphabet().get(i+1)));
              	  }
              	  
              	  checkIfCodeIsValidated();

              	  trouve = true;  
                }
     
                i++;
             } 
        }
        
        if (source.equals(view.getBouton_down1())) {
        	 boolean trouve = false;
      	     int i = 0;
      	   
             while(!trouve){  
          	
                if(view.getAlphabet().get(i).equals(view.getLettre1().getText())) {
              	  
              	  if(view.getAlphabet().get(0).equals(view.getLettre1().getText())) {
              		  view.getLettre1().setText("Z");
              	  }else {
              		  view.getLettre1().setText(new String((String)view.getAlphabet().get(i-1)));
              	  }

              	  checkIfCodeIsValidated();

              	  trouve = true;  
                }
     
                i++;
             }  
        }

        if (source.equals(view.getBouton_down2())) {
        	boolean trouve = false;
     	    int i = 0;
     	   
            while(!trouve){  
         	
               if(view.getAlphabet().get(i).equals(view.getLettre2().getText())) {
             	  
             	  if(view.getAlphabet().get(0).equals(view.getLettre2().getText())) {
             		  view.getLettre2().setText("Z");
             	  }else {
             		  view.getLettre2().setText(new String((String)view.getAlphabet().get(i-1)));
             	  }
             	  
              	  checkIfCodeIsValidated();

             	  trouve = true;  
               }
    
               i++;
            }  
        }
        
        if (source.equals(view.getBouton_down3())) {
        	boolean trouve = false;
     	    int i = 0;
     	   
            while(!trouve){  
         	
               if(view.getAlphabet().get(i).equals(view.getLettre3().getText())) {
             	  
             	  if(view.getAlphabet().get(0).equals(view.getLettre3().getText())) {
             		  view.getLettre3().setText("Z");
             	  }else {
             		  view.getLettre3().setText(new String((String)view.getAlphabet().get(i-1)));
             	  }
             	  
              	  checkIfCodeIsValidated();

             	  trouve = true;  
               }
    
               i++;
            }  
        }
        
        if (source.equals(view.getBouton_down4())) {
        	boolean trouve = false;
     	    int i = 0;
     	   
            while(!trouve){  
         	
               if(view.getAlphabet().get(i).equals(view.getLettre4().getText())) {
             	  
             	  if(view.getAlphabet().get(0).equals(view.getLettre4().getText())) {
             		  view.getLettre4().setText("Z");
             	  }else {
             		  view.getLettre4().setText(new String((String)view.getAlphabet().get(i-1)));
             	  }
             	  
              	  checkIfCodeIsValidated();

             	  trouve = true;  
               }
    
               i++;
            }  
        }
        
        if (source.equals(view.getBoutton_rouge())) {
        	
            if(modules.get(1).isResolved() == true && modules.get(2).isResolved == true && modules.get(3).isResolved == true && modules.get(4).isResolved == true && modules.get(5).isResolved == true) {
            	victory();
            }
            else {
            	defeat();
            }
        }
        
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public Parent getView() {
        return view.getPanel();
    }
  
  
  //CREATION DE LA MALETTE, DES MODULES, DES BOARDS, DES ENIGMES ET DES INDICES
  private ArrayList<Module> modules = new ArrayList<Module>();
  private int i = 0;
  
  private ArrayList<String> listeEnigmeBoards = new ArrayList<String>();
  private ArrayList<String> listeEnigmesCables = new ArrayList<String>();
  private ArrayList<String> couleursSimon = new ArrayList<String>();
  private ArrayList<Integer> touchesPaveNumerique = new ArrayList<Integer>();
  private ArrayList<String> listeEnigmesPaveNumerique = new ArrayList<String>();
  
  private ArrayList<String> listeEnigmesBouton = new ArrayList<String>();
  
  
  private String choixDuBoard;
  private String nomDuBoard;
  private String nomDeLEnigme;
  private String libelleDeLIndice;
  private int ordreDesTouchesPaveNumerique;
  
  // FONCTION PURGE ARRAYS
  public void purgeArrays() {
	  
	  listeEnigmeBoards.clear();	  
	  listeEnigmesCables.clear();
	  couleursSimon.clear();	 
	  touchesPaveNumerique.clear();	  
	  listeEnigmesPaveNumerique.clear();
	  listeEnigmesBouton.clear();
  }

  //FONCTION INITIALISATION ARRAYS
  public void initialisationArrays() {
	  
	  listeEnigmeBoards.add("Bouton");
	  listeEnigmeBoards.add("C�bles");
	  listeEnigmeBoards.add("Simon");
	  listeEnigmeBoards.add("Pav� Num�rique");
	  listeEnigmeBoards.add("Pav� Alphab�tique");
	  
	  listeEnigmesCables.add("Couper le c�ble rouge");
	  listeEnigmesCables.add("Couper le c�ble vert");
	  listeEnigmesCables.add("Couper le c�ble bleu");
	  listeEnigmesCables.add("Couper le c�ble jaune");
	  
	  couleursSimon.add("Rouge");
	  couleursSimon.add("Bleu");
	  couleursSimon.add("Jaune");
	  couleursSimon.add("Vert");
	 
	  touchesPaveNumerique.add(0);
	  touchesPaveNumerique.add(1);
	  touchesPaveNumerique.add(2);
	  touchesPaveNumerique.add(3);
	  touchesPaveNumerique.add(4);
	  touchesPaveNumerique.add(5);
	  touchesPaveNumerique.add(6);
	  touchesPaveNumerique.add(7);
	  touchesPaveNumerique.add(8);
	  touchesPaveNumerique.add(9);
	  
	  listeEnigmesPaveNumerique.add("D�couverte de l'Amerique par Christophe Colomb.");
	  listeEnigmesPaveNumerique.add("Fin de la Seconde Guerre Mondiale.");
	  listeEnigmesPaveNumerique.add("L'Homme a march� sur la Lune.");
	  listeEnigmesPaveNumerique.add("La France est championne du monde de football.");
	  listeEnigmesPaveNumerique.add("Suite de quatre chiffres de 1 � 4.");
	  
	  listeEnigmesBouton.add("Appuyer sur le bouton");
  	  listeEnigmesBouton.add("N'appuie pas sur le bouton");
  	  
  }	  
  
//  // BIND DE TOUS LES SERVICES
//  @Override
//  public void bindMalletteService(MalletteService service) {
//	  mallette = service;
//  }
//  
//  @Override
//  public void bindEnigmeBoardService(EnigmeBoardService service) {
//	  board = service;
//  }
//  
//  @Override
//  public void bindEnigmeService(EnigmeService service) {
//	  enigme = service;
//  }
//  
//  @Override
//  public void bindIndiceService(IndiceService service) {
//	  indice = service;
//  }
//
  
  
//  @Override
//  public void init(){
//	// INITIALISATION, CREATION DES TIMERS
//    engineClock = new Timer();
//    countdown = new Timer();
//    gen = new Random();
//  }

  public void initializingBoard(){
	  
	  // CHECK-LIST
	  if(disableConsoleLogs != 1) {
		  System.out.println("Initialisation Malette...");
		  System.out.println("Initialisation Malette : CHECK");
		  System.out.println("Initialisation Modules...");
	  }
	  
	  // ON INITIALISE TOUS LES ARRAYS
	  initialisationArrays();
	  
	  
	  // NOMBRE DE MODULES
	  while(i < 6) {
		  
		  // LE PREMIER MODULE (0) EST TOUJOURS LE MINUTEUR
		  if(i > 1 && i < 6) {
			  generationEnigmeBoard();
			  
	    	  // ENIGME BOUTON
		      if(nomDuBoard == "Bouton") {
		    	  generationEnigmeBouton();
		      }
		      
		      // ENIGME CABLES
		      else if(nomDuBoard == "C�bles") {
		    	  generationEnigmeCables();
		      }
		      
		      // ENIGME SIMON
		      else if(nomDuBoard == "Simon") {
		    	  generationEnigmeSimon();

		      }
		      
		      // ENIGME PAVE NUMERIQUE
		      else if(nomDuBoard == "Pav� Num�rique") {
		    	  generationEnigmePaveNumerique();
		      }
		      
		      // ENIGME PAVE ALPHABETIQUE
		      else{
		    	  generationEnigmePaveAlphabetique();
		      }
		      
		      // GENERATION MODULE UNE FOIS TOUTES LES INFOS DISPOS
		      initiatingModule();
		      
		      // GENERATION DES LOGS
		      printingLogs();
			  
			  
		  }
		  // ELSE --> MINUTEUR
		  else if (i == 0){
			  initiatingModuleMinuteur();
//			  System.out.println("Minuteur");
//			  printingMinuteurLog();
		  }
		  else {
			  initiatingModuleAbortButton();
//			  System.out.println("Abort");
		  }
		  i++;
	  }
	  
	  if(disableConsoleLogs != 1) {
		// CHECK-LIST MODULES, INITIALISATION MINUTEUR
		  System.out.println("Initialisation Modules : CHECK");
		  System.out.println("Initialisation Minuteur...");
	  }
	  
	  
	  // INITIALISATION MINUTEUR
	  startingCountdown();
	  
	  //CHECK-LIST MINUTEUR
	  if(disableConsoleLogs != 1) {
		  System.out.println("Initialisation Minuteur : CHECK");
	  }
	  
	  //GAME STEP, TOUJOURS UTILE
//      engineClock.schedule(new TimerTask(){
//    	  
//    	  public void run() {
////    		  System.out.println("Game step #"+data.getStepNumber()+": checked.");
////    		  data.setStepNumber(data.getStepNumber() + 1);
//    	  }
//      },0,100);
  }
  
  	  
 
	  private void generationEnigmeBoard() {
		  
		  purgeArrays();
		  initialisationArrays();
		  
		  choixDuBoard = listeEnigmeBoards.get(gen.nextInt(listeEnigmeBoards.size()));
	      nomDuBoard = choixDuBoard;
	      
	  }
  
  	  private void generationEnigmeBouton() {
  		  
  		  purgeArrays();
  		  initialisationArrays();
  		  
  		  String choixDeLEnigme = listeEnigmesBouton.get(gen.nextInt(listeEnigmesBouton.size()));
	  	  nomDeLEnigme = choixDeLEnigme;
	  	  if(nomDeLEnigme == "Appuyer sur le bouton") {
	  		libelleDeLIndice = "Vous devez appuyer sur le bouton";
	  	  }
	  	  else {
	  		libelleDeLIndice = "Vous ne devez pas appuyer sur le bouton";
	  	  }
  	  }
  	  
  	private void generationEnigmeCables() {
  		
  		purgeArrays();
		initialisationArrays();
  		
  		String choixDeLEnigme = listeEnigmesCables.get(gen.nextInt(listeEnigmesCables.size()));
	  	nomDeLEnigme = choixDeLEnigme;
	  	if(nomDeLEnigme == "Couper le c�ble rouge") {
	  		libelleDeLIndice = "Vous devez couper le c�ble rouge";
	  	}
	  	else if(nomDeLEnigme == "Couper le c�ble vert") {
	  		libelleDeLIndice = "Vous devez couper le c�ble vert";
	  	}
	  	else if(nomDeLEnigme == "Couper le c�ble bleu") {
	  		libelleDeLIndice = "Vous devez couper le c�ble bleu";
	  	}
	  	else {
	  		libelleDeLIndice = "Vous devez couper le c�ble jaune";
	  	}
  	}
  	
  	private void generationEnigmeSimon() {
  		
  		purgeArrays();
		initialisationArrays();

	  	nomDeLEnigme = "Simon";
			ArrayList<String> ordreDesTouchesSimon = new ArrayList<String>();
			libelleDeLIndice = "Vous devez toucher les cases dans le bon ordre";
	  	  
	  	for(int x = 0; x < 4; x++) {
	  		ordreDesTouchesSimon.add(couleursSimon.get(gen.nextInt(couleursSimon.size())));
	  	}
	  	if(disableConsoleLogs != 1) {
		  	System.out.println(ordreDesTouchesSimon);
	  	}
  	}
  	
  	private void generationEnigmePaveNumerique() {
		purgeArrays();
		initialisationArrays();
  	    nomDeLEnigme = "Pav� Num�rique";
  	  
  	    String choixDeLEnigme = listeEnigmesPaveNumerique.get(gen.nextInt(listeEnigmesPaveNumerique.size()));
  	    nomDeLEnigme = choixDeLEnigme;
  	  
  	    if(nomDeLEnigme == "D�couverte de l'Amerique par Christophe Colomb.") {
  		    ordreDesTouchesPaveNumerique = 1492;
  	    }
  	    else if(nomDeLEnigme == "Fin de la Seconde Guerre Mondiale.") {
  	    	ordreDesTouchesPaveNumerique = 1945;
  	    }
  	    else if(nomDeLEnigme == "L'Homme a march� sur la Lune.") { 
  	    	ordreDesTouchesPaveNumerique = 1969;
  	    }
  	    else if(nomDeLEnigme == "La France est championne du monde de football.") {
  	    	ordreDesTouchesPaveNumerique = 1998;
  	    }
  	    else{
  	    	ordreDesTouchesPaveNumerique = 1234;
  	    }
  	    if(disableConsoleLogs != 1) {
  	    	System.out.println(ordreDesTouchesPaveNumerique);
  	  }
  	}
  	
  	private void generationEnigmePaveAlphabetique() {
		purgeArrays();
		initialisationArrays();

	  	nomDeLEnigme = "Pav� Alphab�tique";
	  	libelleDeLIndice = "Appuyez sur ABC";
  	}
  	
  	private void initiatingModule() {

  		modules.add(new Module(
				  i, 
				  new EnigmeBoard(
						  nomDuBoard, 
						  new Enigme(
								  nomDeLEnigme,
								  1, 
								  new Indice(
										  libelleDeLIndice)
								  ), 
						  new Button()
						  ),
				  false, 
				  false
				  )
		  );
  	}
  	
  	private void initiatingModuleMinuteur() {
  		modules.add(new Module(
				  i, 
				  new EnigmeBoard(
						  "Minuteur", 
						  new Enigme(
								  "Pas d'�nigme",
								  1, 
								  new Indice(
										  "Pas d'indice")
								  ), 
						  new Button()
						  ),
				  true, 
				  true
				  )
		  );
  	}
  	
  	private void initiatingModuleAbortButton() {
  		modules.add(new Module(
				  i, 
				  new EnigmeBoard(
						  "Abort", 
						  new Enigme(
								  "Pas d'�nigme",
								  1, 
								  new Indice(
										  "Pas d'indice")
								  ), 
						  new Button()
						  ),
				  true, 
				  true
				  )
		  );
  	}
  	
  	private void printingLogs(){
  		if(disableConsoleLogs != 1) {
  			System.out.println("Module num�ro "+i+" initialis�. Ce module de type : '"+modules.get(i).getEnigmeBoard().getNameBoard()+
  				  "' comporte une �nigme de type : '"+modules.get(i).getEnigmeBoard().getEnigme().getNameEnigme()+
  				  "' ainsi que l'indice suivant associ� : "+modules.get(i).getEnigmeBoard().getEnigme().getIndice().getIndiceText()+
  				  ".");
  		}
  	}
  	
  	private void printingMinuteurLog() {
  		if(disableConsoleLogs != 1) {
  			System.out.println("Module num�ro "+i+" initialis�. Ce module de type : 'Minuteur' ne comporte pas d'�nigme.");
  		}
  	}
  	
  	// COMPTE A REBOURS, GAME STEPS
  	private void startingCountdown() {
  		
	  	// INSTANCIATION DU MINUTEUR
	  	Minuteur deathClock = new Minuteur(delaiMinuteur);
	  	
		engineClock.schedule(new TimerTask() {
	  		  public void run() {
	  			  data.setStepNumber(data.getStepNumber() + 1);
//				  System.out.println("Le timer est � : "+data.getStepNumber()+" secondes.");
//				  System.out.println("La deathClock est � : "+deathClock.getCompteARebours()+" secondes.");
	  			  
	  			  // CALCUL DES MINUTES ET SECONDES
	  			  finalCountdown = deathClock.getCompteARebours() - data.getStepNumber();
	  			  finalCountdownFormatedMinutes = finalCountdown/60;
	  			  finalCountdownFormatedSeconds = finalCountdown%60;
	  			  
	  			  if(finalCountdownFormatedMinutes < 10) {
	  				 if(finalCountdownFormatedSeconds < 10) {
			  				view.getTimer().setText("0"+finalCountdownFormatedMinutes+":0"+finalCountdownFormatedSeconds);
	  				 }
	  				 else {
			  			  view.getTimer().setText("0"+finalCountdownFormatedMinutes+":"+finalCountdownFormatedSeconds);
	  				 }
	  			  }
	  			  else {
	  				if(finalCountdownFormatedSeconds < 10) {
		  				view.getTimer().setText(finalCountdownFormatedMinutes+":0"+finalCountdownFormatedSeconds);
	  				}
	  				else {
			  			  view.getTimer().setText(finalCountdownFormatedMinutes+":"+finalCountdownFormatedSeconds);
	  				}
	  			  }
	  			  
	  			  
	  			  
	  			  // PRINT, IF COUNTDOWN ECOULE = VOUS ETES MORT
	  			  if(finalCountdownFormatedMinutes > 0 || finalCountdownFormatedSeconds > -1) {
	  				  if(disableConsoleLogs != 1) {
	  					System.out.println("Le final countdown format� est � : "+finalCountdownFormatedMinutes+" minutes et "+finalCountdownFormatedSeconds+" secondes.");
	  				  }
	  				
	  			  }
	  			  else {
	  				if(disableConsoleLogs != 1) {
	  	  			  view.getTimer().setText("BOOM");
	  					System.out.println("Partie termin�e, vous �tes mort.");
	  				}
	  			  }
	  			  
	  			    
	  			  
	  		  }
	  	  },0,1000);
	  }
  	
  	  
	  
  	  public void abortMission() {
  		if(disableConsoleLogs != 1) {
			System.out.println("Partie termin�e, vous avez choisi la mort.");
		}
  	  }
  	  
  	  public void initializingSimon() {
  		  System.out.println("C'est bien un Simon");
	  }
  	  
  	  public void initializingPaveNumerique() {
  		  System.out.println("C'est bien un Pav� Num�rique");
	  }
  	  
  	  public void initializingPaveAlphabetique() {
		  System.out.println("C'est bien un Pav� Alphab�tique");
	  }
  	  
  	  public void initializingCables() {
		  System.out.println("C'est bien des c�bles");
	  }
  	  
  	  public void revealModule(int i) {
  		  if(modules.get(i).isActive = true && i > 1) {
  			  if(modules.get(i+1) != null) {
  	  			  modules.get(i+1).isActive();
  	  			  a = i + 1;
  			  }
  		  }
  		  if (modules.get(a).getEnigmeBoard().getNameBoard().equals("Simon")) {
  			  initializingSimon();
		  }
  		  if (modules.get(a).getEnigmeBoard().getNameBoard().equals("Pav� Num�rique")) {
			  initializingPaveNumerique();
		  }
  		  if (modules.get(a).getEnigmeBoard().getNameBoard().equals("Pav� Alphab�tique")) {
  			initializingPaveAlphabetique();
		  }
  		  if (modules.get(a).getEnigmeBoard().getNameBoard().equals("C�bles")) {
  			initializingCables();
		  }
  		  
		  
	  }
	  
	  public void resolvedModule(int a) {
		  modules.get(a).isResolved = true;
	  }

	  public void checkIfCodeIsValidated(){
		  
	  	  if(view.getLettre1().getText().equals(lettre1) && view.getLettre2().getText().equals(lettre2) && view.getLettre3().getText().equals(lettre3) && view.getLettre4().getText().equals(lettre4)){
	  		  System.out.println("L'op�rateur a pass� le pav� alphab�tique avec succ�s.");
	  		  revealModule(a);
	  	  }
	  }
	  
	  public void victory() {
		  engineClock.cancel();
		  view.getTimer().setText("VICTOIRE");
		  System.out.println("L'op�rateur a d�samorc� la bombe avec succ�s.");
	  }
	  
	  public void checkIfDefeat() {
		  
	  }
	  
	  public void defeat() {
		  engineClock.cancel();
		  view.getTimer().setText("BOOM");
		  System.out.println("Partie termin�e, l'op�rateur a �t� tu� par l'explosion.");
	  }
}
