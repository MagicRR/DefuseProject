/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package data;

import specifications.DataService;

public class Data implements DataService{
	
  int stepNumber;

  public Data(){
	  stepNumber = 0;
  }

  @Override
  public void init(){
	  
  }

  @Override
  public int getStepNumber(){ return stepNumber; }
  
  @Override
  public void setStepNumber(int n){ stepNumber=n; }
  
}
