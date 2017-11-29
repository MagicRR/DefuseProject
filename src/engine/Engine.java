/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package engine;

import tools.HardCodedParameters;
import tools.User;
import tools.Position;

import specifications.EngineService;
import specifications.EnigmeBoardService;
import specifications.EnigmeService;
import specifications.IndiceService;
import specifications.MalletteService;
import specifications.DataService;
import specifications.RequireDataService;
import specifications.RequireMalletteService;
import specifications.RequireEnigmeBoardService;
import specifications.RequireEnigmeService;
import specifications.RequireIndiceService;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

import java.awt.Button;

import data.Enigme;
import data.EnigmeBoard;
import data.Indice;
import data.Minuteur;
import data.Module;

import java.util.ArrayList;
import java.util.Random;

public class Engine implements EngineService, RequireDataService, RequireMalletteService, RequireEnigmeBoardService, RequireEnigmeService, RequireIndiceService{
  private Timer engineClock;
  private Timer countdown;
  private DataService data;
  private MalletteService mallette;
  private EnigmeBoardService board;
  private EnigmeService enigme;
  private IndiceService indice;
  private User.COMMAND command;
  private Random gen;
  private int delaiMinuteur = 300;
  private Minuteur deathClock;
  private int finalCountdown;
  private int finalCountdownFormatedMinutes;
  private int finalCountdownFormatedSeconds;
  
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

  public Engine(){}
  
  // BIND DE TOUS LES SERVICES
  @Override
  public void bindMalletteService(MalletteService service) {
	  mallette = service;
  }
  
  @Override
  public void bindEnigmeBoardService(EnigmeBoardService service) {
	  board = service;
  }
  
  @Override
  public void bindEnigmeService(EnigmeService service) {
	  enigme = service;
  }
  
  @Override
  public void bindIndiceService(IndiceService service) {
	  indice = service;
  }

  @Override
  public void bindDataService(DataService service){
    data=service;
  }
  
  @Override
  public void init(){
	// INITIALISATION, CREATION DES TIMERS
    engineClock = new Timer();
    countdown = new Timer();
    command = User.COMMAND.NONE;
    gen = new Random();
  }

  @Override
  public void start(){
	  
	  // CHECK-LIST
	  System.out.println("Initialisation Malette...");
	  System.out.println("Initialisation Malette : CHECK");
	  System.out.println("Initialisation Modules...");
	  
	  
	  // ON INITIALISE TOUS LES ARRAYS
	  initialisationArrays();
	  
	  
	  // NOMBRE DE MODULES
	  while(i < 6) {
		  
		  // LE PREMIER MODULE (0) EST TOUJOURS LE MINUTEUR
		  if(i > 0 && i < 6) {
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
		  else {
			  initiatingModuleMinuteur();
//			  printingMinuteurLog();
		  }
		  i++;
	  }
	  
	  // CHECK-LIST MODULES, INITIALISATION MINUTEUR
	  System.out.println("Initialisation Modules : CHECK");
	  System.out.println("Initialisation Minuteur...");
	  
	  // INITIALISATION MINUTEUR
	  startingCountdown();
	  
	  //CHECK-LIST MINUTEUR
	  System.out.println("Initialisation Minuteur : CHECK");
	  
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
	  	  
	  	System.out.println(ordreDesTouchesSimon);
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
  	    System.out.println(ordreDesTouchesPaveNumerique);
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
						  new JButton()
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
						  new JButton()
						  ),
				  false, 
				  false
				  )
		  );
  	}
  	
  	private void printingLogs(){
  		System.out.println("Module num�ro "+i+" initialis�. Ce module de type : '"+modules.get(i).getEnigmeBoard().getNameBoard()+
				  "' comporte une �nigme de type : '"+modules.get(i).getEnigmeBoard().getEnigme().getNameEnigme()+
				  "' ainsi que l'indice suivant associ� : "+modules.get(i).getEnigmeBoard().getEnigme().getIndice().getIndiceText()+
				  ".");
  		
  	}
  	
  	private void printingMinuteurLog() {
		  System.out.println("Module num�ro "+i+" initialis�. Ce module de type : 'Minuteur' ne comporte pas d'�nigme.");
  	}
  	
  	// COMPTE A REBOURS, GAME STEPS
  	private void startingCountdown() {
  		
  	  // INSTANCIATION DU MINUTEUR
  	  Minuteur deathClock = new Minuteur(delaiMinuteur);
  	  countdown.schedule(new TimerTask() {
  		  public void run() {
  			  
  			  data.setStepNumber(data.getStepNumber() + 1);
//			  System.out.println("Le timer est � : "+data.getStepNumber()+" secondes.");
//			  System.out.println("La deathClock est � : "+deathClock.getCompteARebours()+" secondes.");
  			  
  			  // CALCUL DES MINUTES ET SECONDES
  			  finalCountdown = deathClock.getCompteARebours() - data.getStepNumber();
  			  finalCountdownFormatedMinutes = finalCountdown/60;
  			  finalCountdownFormatedSeconds = finalCountdown%60;
  			  
  			  // PRINT, IF COUNTDOWN ECOULE = VOUS ETES MORT
  			  if(finalCountdownFormatedMinutes > 0 || finalCountdownFormatedSeconds > -1) {
  				System.out.println("Le final countdown format� est � : "+finalCountdownFormatedMinutes+" minutes et "+finalCountdownFormatedSeconds+" secondes.");
  			  }
  			  else {
  				System.out.println("Partie termin�e, vous �tes mort.");
  			  }
  			  
  			    
  			  
  		  }
  	  },0,1000);
  	}
	  

	  @Override
	  public void stop(){
	    engineClock.cancel();
	  }
	
	  @Override
	  public void setHeroesCommand(User.COMMAND c){
	    command=c;
	  }



}
