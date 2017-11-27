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
import specifications.MalletteService;
import specifications.DataService;
import specifications.RequireDataService;
import specifications.RequireMalletteService;
import specifications.RequireEnigmeBoardService;
import specifications.RequireEnigmeService;

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

public class Engine implements EngineService, RequireDataService, RequireMalletteService, RequireEnigmeBoardService, RequireEnigmeService{
  private Timer engineClock;
  private Timer countdown;
  private DataService data;
  private MalletteService mallette;
  private EnigmeBoardService board;
  private EnigmeService enigme;
  private User.COMMAND command;
  private Random gen;

  public Engine(){}
  
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
  public void bindDataService(DataService service){
    data=service;
  }
  
  @Override
  public void init(){
    engineClock = new Timer();
    countdown = new Timer();
    command = User.COMMAND.NONE;
    gen = new Random();
  }

  @Override
  public void start(){
	  
	  System.out.println("Initialisation Malette...");
	  System.out.println("Initialisation Malette : CHECK");
	  System.out.println("Initialisation Modules...");
	  ArrayList<Module> modules = new ArrayList<Module>();
	  int i = 0;
	  
	  while(i < 6) {
		  modules.add(new Module(
				  i, 
				  new EnigmeBoard(
						  "Bouton", 
						  new Enigme(
								  "BoutonRouge",
								  1, 
								  new Indice(
										  "Tu dois appuyer sur le bouton.")
								  ), 
						  new JButton()
						  ),
				  false, 
				  false
				  )
		  );
		  System.out.println("Module numéro "+i+" initialisé. Ce module de type : "+modules.get(i).getEnigmeBoard().getNameBoard()+" comporte une énigme de type : "+modules.get(i).getEnigmeBoard().getEnigme().getNameEnigme()+".");
		  i++;
	  }
	  
	  System.out.println("Initialisation Modules : CHECK");
	  
	  System.out.println("Initialisation Minuteur...");
	  countdown.schedule(new TimerTask() {
		  public void run() {
			  System.out.println("Le timer est à : "+data.getStepNumber()+" secondes.");
			  data.setStepNumber(data.getStepNumber() + 1);
		  }
	  },0,1000);
	  System.out.println("Initialisation Minuteur : CHECK");
	  
	  
      engineClock.schedule(new TimerTask(){
    	  
    	  public void run() {
//    		  System.out.println("Game step #"+data.getStepNumber()+": checked.");
//    		  data.setStepNumber(data.getStepNumber() + 1);
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
