/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package engine;

import tools.HardCodedParameters;
import tools.User;
import tools.Position;

import specifications.EngineService;
import specifications.DataService;
import specifications.RequireDataService;

import java.util.Timer;
import java.util.TimerTask;

import java.util.Random;

public class Engine implements EngineService, RequireDataService{
  private Timer engineClock;
  private DataService data;
  private User.COMMAND command;
  private Random gen;

  public Engine(){}

  @Override
  public void bindDataService(DataService service){
    data=service;
  }
  
  @Override
  public void init(){
    engineClock = new Timer();
    command = User.COMMAND.NONE;
    gen = new Random();
  }

  @Override
  public void start(){
    engineClock.schedule(new TimerTask(){
      public void run() {
        //System.out.println("Game step #"+data.getStepNumber()+": checked.");
      }
    },0,100);
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
