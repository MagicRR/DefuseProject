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
    
    private Timer simonClock = new Timer();

    private boolean showSimonOrder = false;
    private int fourSteps;
    private int fourStepsPlusFour;
	private int s;
    
	private int delaiMinuteur = 300;
	private int finalCountdown;
	private int finalCountdownFormatedMinutes;
	private int finalCountdownFormatedSeconds;
	
	private String lettre1 = "G";
	private String lettre2 = "I";
	private String lettre3 = "G";
	private String lettre4 = "N";
	
	private int length_logs;
	private int	nb_click_num = 0;
	private int	nb_click_simon = 0;
	
	private ArrayList<Boolean> checkSimons = new <Boolean>ArrayList();	
	
	private boolean checkSimon1 = false;
	private boolean checkSimon2 = false;
	private boolean checkSimon3 = false;
	private boolean checkSimon4 = false;
	private boolean checkSimon5 = false;
	private boolean checkSimon6 = false;
	
	private boolean cablesResolved = false;
	private boolean defeat = false;
	private boolean victory = false;
	private boolean pressAbortToWin = false;
	private boolean cable_fait = false;
	private boolean alpha_fait = false;
	private boolean simon_fait = false;
	private int moduleEnCours;
	
	private ArrayList<String> ordreSimon = new ArrayList<String>();
	
	// ACTIVER LES LOGS OU NON. 1 = DESACTIVE
	private int disableConsoleLogs = 1;
	
	public Engine(final Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void handle(Event event) {
		
		if(true == defeat) {
    		  event.consume();
		}else {
			final Object source = event.getSource();
//			System.out.println("Module Actif : " +modules.get(moduleEnCours).getEnigmeBoard().getEnigme().getNameEnigme());
			

			if (source.equals(view.getBut_simon1())) {
				
				if(simon_fait) {
					event.consume();
				}else {
					if(modules.get(5).isActive() == true) {
						
						System.out.println(nb_click_simon);
						System.out.println(ordreSimon.get(nb_click_simon));
						
						if (ordreSimon.get(nb_click_simon).equals("Rouge")) {
							
							view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge_light()));
							view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
	    					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
	    					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
	    					
							nb_click_simon++;
							checkSimons.set(nb_click_simon, true);
							
							if(checkSimons.get(0) == true && checkSimons.get(1) == true && checkSimons.get(2) == true && checkSimons.get(3) == true && checkSimons.get(4) == true && checkSimons.get(5) == true) {
								view.getRec_check4().setFill(new ImagePattern(view.getCheck()));
								view.getCamembert().setFill(new ImagePattern(view.getCamembert_img5()));
								victoryIsNear();
							}
						}
						else {
							defeat();
			        		view.getRec_check4().setFill(new ImagePattern(view.getNoCheck()));
							nb_click_simon = 0;
						}
					}	
				}
			}
			
			if (source.equals(view.getBut_simon2())) {
				
				if(simon_fait) {
					event.consume();
				}else {
					if(modules.get(5).isActive() == true) {
						
						System.out.println(nb_click_simon);
						System.out.println(ordreSimon.get(nb_click_simon));
						
						if (ordreSimon.get(nb_click_simon).equals("Vert")) {
							
							view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert_light()));
							view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
	    					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
	    					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
							
							nb_click_simon++;
							checkSimons.set(nb_click_simon, true);
							
							if(checkSimons.get(0) == true && checkSimons.get(1) == true && checkSimons.get(2) == true && checkSimons.get(3) == true && checkSimons.get(4) == true && checkSimons.get(5) == true) {
								view.getRec_check4().setFill(new ImagePattern(view.getCheck()));
								view.getCamembert().setFill(new ImagePattern(view.getCamembert_img5()));
								victoryIsNear();
							}
						}
						else {
							defeat();
			        		view.getRec_check4().setFill(new ImagePattern(view.getNoCheck()));
							nb_click_simon = 0;
						}
					}
				}
			}
			
			if (source.equals(view.getBut_simon3())) {
				
				if(simon_fait) {
					event.consume();
				}else {
					if(modules.get(5).isActive() == true) {
						
						System.out.println(nb_click_simon);
						System.out.println(ordreSimon.get(nb_click_simon));
						
						if (ordreSimon.get(nb_click_simon).equals("Jaune")) {
							
							view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune_light()));
							view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
	    					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
	    					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
							
							nb_click_simon++;
							checkSimons.set(nb_click_simon, true);
							
							if(checkSimons.get(0) == true && checkSimons.get(1) == true && checkSimons.get(2) == true && checkSimons.get(3) == true && checkSimons.get(4) == true && checkSimons.get(5) == true) {
								view.getRec_check4().setFill(new ImagePattern(view.getCheck()));
								view.getCamembert().setFill(new ImagePattern(view.getCamembert_img5()));
								victoryIsNear();
							}
						}
						else {
							defeat();
			        		view.getRec_check4().setFill(new ImagePattern(view.getNoCheck()));
							nb_click_simon = 0;
						}
					}
				}		
			}
			
			if (source.equals(view.getBut_simon4())) {
				

				if(simon_fait) {
					event.consume();
				}else {
					if(modules.get(5).isActive() == true) {
						
						System.out.println(nb_click_simon);
						System.out.println(ordreSimon.get(nb_click_simon));
						
						if (ordreSimon.get(nb_click_simon).equals("Bleu")) {
							
							view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu_light()));
							view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
	    					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
	    					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
							
							nb_click_simon++;
							checkSimons.set(nb_click_simon, true);
							
							if(checkSimons.get(0) == true && checkSimons.get(1) == true && checkSimons.get(2) == true && checkSimons.get(3) == true && checkSimons.get(4) == true && checkSimons.get(5) == true) {
				        		view.getRec_check4().setFill(new ImagePattern(view.getCheck()));
				        		view.getCamembert().setFill(new ImagePattern(view.getCamembert_img5()));
								victoryIsNear();
							}
						}
						else {
							defeat();
			        		view.getRec_check4().setFill(new ImagePattern(view.getNoCheck()));
							nb_click_simon = 0;
						}
					}
				}		
			}
			
			
	        if (source.equals(view.getZone_cable_1())) {
	        	
	        	if(false == cable_fait) {
	        		//Vérifie si c'est le bon cable à couper ou non
		        	if(modules.get(moduleEnCours).getEnigmeBoard().getEnigme().getNameEnigme()=="Cut red cable") {
		        		view.getRec_check1().setFill(new ImagePattern(view.getCheck()));
		        		view.getCamembert().setFill(new ImagePattern(view.getCamembert_img2()));
		        		view.setModule_alpha(true);
		        		view.refreshAlpha();
		        		// IF CABLES RESOLUS : SIMON
			        	modules.get(2).setActive(false);
			        	modules.get(3).setActive(true);
			        	modules.get(2).setResolved(true);
		        	}else {
		        		view.getRec_check1().setFill(new ImagePattern(view.getNoCheck()));
		      		  	view.getTextLogs().setText(view.getTextLogs().getText()+">> Vous avez échoué.\n");

		        		defeat();
		        	}
		        	cable_fait = true;
		        	
		        	view.getRec_cable1().setFill(new ImagePattern(view.getCable_rouge_cut()));
	        	}else {
	      		  event.consume();
	        	}
	        	
	        }
	        
	        if (source.equals(view.getZone_cable_2())) {
	        	
	        	if(false == cable_fait) {
	        		//Vérifie si c'est le bon cable à couper ou non
		        	if(modules.get(moduleEnCours).getEnigmeBoard().getEnigme().getNameEnigme()=="Cut yellow cable") {
		        		view.getRec_check1().setFill(new ImagePattern(view.getCheck()));
		        		view.getCamembert().setFill(new ImagePattern(view.getCamembert_img2()));
		        		view.setModule_alpha(true);
		        		view.refreshAlpha();
		        		// IF CABLES RESOLUS : SIMON
		        		modules.get(2).setActive(false);
		        		modules.get(3).setActive(true);
			        	modules.get(2).setResolved(true);
			        	
		        	}else {
		        		view.getRec_check1().setFill(new ImagePattern(view.getNoCheck()));
		      		  	view.getTextLogs().setText(view.getTextLogs().getText()+">> Vous avez échoué.\n");

		        		defeat();
		        	}
		        	cable_fait = true;

		        	view.getRec_cable2().setFill(new ImagePattern(view.getCable_jaune_cut()));
	        	}else {
	      		  event.consume();
	        	}
	        	
	        }
	        
	        if (source.equals(view.getZone_cable_3())) {
	        	
	        	if(false == cable_fait) {
	        		//Vérifie si c'est le bon cable à couper ou non
		        	if(modules.get(moduleEnCours).getEnigmeBoard().getEnigme().getNameEnigme()=="Cut green cable") {
		        		view.getRec_check1().setFill(new ImagePattern(view.getCheck()));
		        		view.getCamembert().setFill(new ImagePattern(view.getCamembert_img2()));
		        		view.setModule_alpha(true);
		        		view.refreshAlpha();
		        		// IF CABLES RESOLUS : SIMON
		        		modules.get(2).setActive(false);
		        		modules.get(3).setActive(true);
			        	modules.get(2).setResolved(true);
		        	}else {
		        		view.getRec_check1().setFill(new ImagePattern(view.getNoCheck()));
		      		  	view.getTextLogs().setText(view.getTextLogs().getText()+">> Vous avez échoué.\n");

		        		defeat();
		        	}
		        	cable_fait = true;

		        	view.getRec_cable3().setFill(new ImagePattern(view.getCable_vert_cut()));
	        	}else {
	      		  event.consume();
	        	}
	        	
	        }
	        
	        if (source.equals(view.getZone_cable_4())) {
	        	
	        	if(false == cable_fait) {
	        		//Vérifie si c'est le bon cable à couper ou non
		        	if(modules.get(moduleEnCours).getEnigmeBoard().getEnigme().getNameEnigme()=="Cut blue cable") {
		        		view.getRec_check1().setFill(new ImagePattern(view.getCheck()));
		        		view.getCamembert().setFill(new ImagePattern(view.getCamembert_img2()));
		        		view.setModule_alpha(true);
		        		view.refreshAlpha();
		        		// IF CABLES RESOLUS : SIMON
		        		modules.get(2).setActive(false);
		        		modules.get(3).setActive(true);
			        	modules.get(2).setResolved(true);
		        	}else {
		        		view.getRec_check1().setFill(new ImagePattern(view.getNoCheck()));
		      		  	view.getTextLogs().setText(view.getTextLogs().getText()+">> Vous avez échoué.\n");

		        		defeat();
		        	}
		        	cable_fait = true;

		        	view.getRec_cable4().setFill(new ImagePattern(view.getCable_bleu_cut()));
	        	}else {
	      		  event.consume();
	        	}
	        	
	        }
	        
	        if (source.equals(view.getIndice())) {
	        	if(!victory) {
	        		if(modules.get(2).isActive == true){
		        		view.getTextLogs().setText(view.getTextLogs().getText()+">> "+modules.get(2).getEnigmeBoard().getEnigme().getIndice().getIndiceText()+".\n");
		        	}
		        	if(modules.get(3).isActive == true){
		        		view.getTextLogs().setText(view.getTextLogs().getText()+">> "+modules.get(3).getEnigmeBoard().getEnigme().getIndice().getIndiceText()+".\n");
		        	}
		        	if(modules.get(4).isActive == true){
		        		view.getTextLogs().setText(view.getTextLogs().getText()+">> "+modules.get(4).getEnigmeBoard().getEnigme().getIndice().getIndiceText()+".\n");
		        	}
		        	if(modules.get(5).isActive == true){
		        		launchingSimon();
		        		view.getTextLogs().setText(view.getTextLogs().getText()+"\n>> "+modules.get(5).getEnigmeBoard().getEnigme().getIndice().getIndiceText()+".\n");
		        	}
	        	}else {
	        		event.consume();
	        	}
	        }
	        
	        if (source.equals(view.getBouton_up1())) {
	        	
	        	if(false == alpha_fait) {

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
	        	}else {
	        		event.consume();
	        	}
	        }
	        
	        if (source.equals(view.getBouton_up2())) {
	        	
	        	if(false == alpha_fait) {
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
	        	}else {
	        		event.consume();
	        	}
	        }
	        
	        if (source.equals(view.getBouton_up3())) {
	        	
	        	if(false == alpha_fait) {
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
	        	}else {
	        		event.consume();
	        	}
	        }
	        
	        if (source.equals(view.getBouton_up4())) {
	        	if(false == alpha_fait) {
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
	        	}else {
	        		event.consume();
	        	}
	        }
	        
	        if (source.equals(view.getBouton_down1())) {
	        	if(false == alpha_fait) {
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
	        	}else {
	        		event.consume();
	        	}
	        }

	        if (source.equals(view.getBouton_down2())) {
	        	if(false == alpha_fait) {
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
	        }
	        
	        if (source.equals(view.getBouton_down3())) {
	        	if(false == alpha_fait) {
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
	        }
	        
	        if (source.equals(view.getBouton_down4())) {
	        	if(false == alpha_fait) {
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
	        }
	        
	        if (source.equals(view.getZone_chiffre_1())) {
	        	
	        	if(!modules.get(4).isResolved()) {
	        		
	        		length_logs = view.getTextLogs().getText().length();
		        	
		        	if(view.getTextLogs().getText(length_logs-6, length_logs).contains(">>")) {
		        		if(view.getTextLogs().getText(length_logs-1, length_logs).equals("1") || 
			        			(view.getTextLogs().getText(length_logs-1, length_logs).equals("2")) ||
			        					(view.getTextLogs().getText(length_logs-1, length_logs).equals("3")) ||
			        							(view.getTextLogs().getText(length_logs-1, length_logs).equals("4"))) {
		        			if(0 == nb_click_num%4) {
					        	view.getTextLogs().setText(view.getTextLogs().getText()+"1\n");
				        	}else {
					        	view.getTextLogs().setText(view.getTextLogs().getText()+"1");
				        	}			        	}
		        	}	        	
		        	else {
			        	view.getTextLogs().setText(view.getTextLogs().getText()+">> 1");
		        	}
		        	
		        	nb_click_num++;
		        	
		        	if(0 == nb_click_num%4) {
			        	checkIfNumIsValidated();
		        	}
	        	}else {
		        	event.consume();
		        }  	
	        }
	        
	        if (source.equals(view.getZone_chiffre_2())) {
	        	
	        	if(!modules.get(4).isResolved()) {
	        	
		        	length_logs = view.getTextLogs().getText().length();
		        	
		        	if(view.getTextLogs().getText(length_logs-6, length_logs).contains(">>")) {
		        		if(view.getTextLogs().getText(length_logs-1, length_logs).equals("1") || 
			        			(view.getTextLogs().getText(length_logs-1, length_logs).equals("2")) ||
			        					(view.getTextLogs().getText(length_logs-1, length_logs).equals("3")) ||
			        							(view.getTextLogs().getText(length_logs-1, length_logs).equals("4"))) {
		        			if(0 == nb_click_num%4) {
					        	view.getTextLogs().setText(view.getTextLogs().getText()+"2\n");
				        	}else {
					        	view.getTextLogs().setText(view.getTextLogs().getText()+"2");
				        	}			        	}
		        	}	        	
		        	else {
			        	view.getTextLogs().setText(view.getTextLogs().getText()+">> 2");
		        	}
		        	
		        	nb_click_num++;
		        	
		        	if(0 == nb_click_num%4) {
			        	checkIfNumIsValidated();  	
		        	}
		        }else {
		        	event.consume();
		        }
	        }

	        if (source.equals(view.getZone_chiffre_3())) {
	        	
	        	if(!modules.get(4).isResolved()) {

	        	
		        	length_logs = view.getTextLogs().getText().length();
		        	
		        	if(view.getTextLogs().getText(length_logs-6, length_logs).contains(">>")) {
		        		if(view.getTextLogs().getText(length_logs-1, length_logs).equals("1") || 
			        			(view.getTextLogs().getText(length_logs-1, length_logs).equals("2")) ||
			        					(view.getTextLogs().getText(length_logs-1, length_logs).equals("3")) ||
			        							(view.getTextLogs().getText(length_logs-1, length_logs).equals("4"))) {
		        			if(0 == nb_click_num%4) {
					        	view.getTextLogs().setText(view.getTextLogs().getText()+"3\n");
				        	}else {
					        	view.getTextLogs().setText(view.getTextLogs().getText()+"3");
				        	}			        	}
		        	}	        	
		        	else {
			        	view.getTextLogs().setText(view.getTextLogs().getText()+">> 3");
		        	}
		        	
		        	nb_click_num++;
		        	
		        	if(0 == nb_click_num%4) {
			        	checkIfNumIsValidated();  	
		        	}
		        }else {
		        	event.consume();
		        }
	        }

	        if (source.equals(view.getZone_chiffre_4())) {
	        	
	        	if(!modules.get(4).isResolved()) {
	        	
		        	length_logs = view.getTextLogs().getText().length();
		        	
		        	if(view.getTextLogs().getText(length_logs-6, length_logs).contains(">>")) {
		        		if(view.getTextLogs().getText(length_logs-1, length_logs).equals("1") || 
			        			(view.getTextLogs().getText(length_logs-1, length_logs).equals("2")) ||
			        					(view.getTextLogs().getText(length_logs-1, length_logs).equals("3")) ||
			        							(view.getTextLogs().getText(length_logs-1, length_logs).equals("4"))) {
				        	if(0 == nb_click_num%4) {
					        	view.getTextLogs().setText(view.getTextLogs().getText()+"4\n");
				        	}else {
					        	view.getTextLogs().setText(view.getTextLogs().getText()+"4");
				        	}
			        	}
		        	}	        	
		        	else {
			        	view.getTextLogs().setText(view.getTextLogs().getText()+">> 4");
		        	}	 
		        	
		        	nb_click_num++;
		        	
		        	if(0 == nb_click_num%4) {
			        	checkIfNumIsValidated();  	
		        	}
		        }else {
		        	event.consume();
		        }
	        }
	        
	        if (source.equals(view.getBoutton_rouge())) {
	        	
	            view.getRec_bouton_rouge().setFill(new ImagePattern(view.getButton_green_img()));
	            view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
				view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
				view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
				view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));

	            if(pressAbortToWin == true) {
	            	victory();
	            }
	            else {
	            	defeat();
	            }
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
  private ArrayList<String> listeEnigmesPaveAlphabetique = new ArrayList<String>();
  
  private ArrayList<String> listeEnigmesBouton = new ArrayList<String>();
  
  
  private String choixDuBoard;
  private String nomDuBoard;
  private String nomDeLEnigme;
  private String libelleDeLIndice;
  private String ordreDesTouchesPaveNumerique;
  
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
	  listeEnigmeBoards.add("Câbles");
	  listeEnigmeBoards.add("Simon");
	  listeEnigmeBoards.add("Pavé Numérique");
	  listeEnigmeBoards.add("Pavé Alphabétique");
	  
	  listeEnigmesCables.add("Cut red cable");
	  listeEnigmesCables.add("Cut green cable");
	  listeEnigmesCables.add("Cut blue cable");
	  listeEnigmesCables.add("Cut yellow cable");
	  
	  couleursSimon.add("Rouge");
	  couleursSimon.add("Bleu");
	  couleursSimon.add("Jaune");
	  couleursSimon.add("Vert");
	 
	  touchesPaveNumerique.add(1);
	  touchesPaveNumerique.add(2);
	  touchesPaveNumerique.add(3);
	  touchesPaveNumerique.add(4);
	  
	  listeEnigmesPaveNumerique.add("Traité d'Amiens");
	  listeEnigmesPaveNumerique.add("8500/60+1000-2/3");
	  listeEnigmesPaveNumerique.add("Suite arithmétique N, avec i = 1 et quatre itérations à n = i + 1.");
	  
	  listeEnigmesPaveAlphabetique.add("GIGN");
	  listeEnigmesPaveAlphabetique.add("CODE");
	  listeEnigmesPaveAlphabetique.add("PUSH");
	  listeEnigmesPaveAlphabetique.add("PULL");
	  listeEnigmesPaveAlphabetique.add("ALAN");

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
//		  if(i > 1 && i < 6) {
//			  generationEnigmeBoard();
//			  
//	    	  // ENIGME BOUTON
//		      if(nomDuBoard == "Bouton") {
//		    	  generationEnigmeBouton();
//		      }
//		      
//		      // ENIGME CABLES
//		      else if(nomDuBoard == "Câbles") {
//		    	  generationEnigmeCables();
//		      }
//		      
//		      // ENIGME SIMON
//		      else if(nomDuBoard == "Simon") {
//		    	  generationEnigmeSimon();
//
//		      }
//		      
//		      // ENIGME PAVE NUMERIQUE
//		      else if(nomDuBoard == "Pavé Numérique") {
//		    	  generationEnigmePaveNumerique();
//		      }
//		      
//		      // ENIGME PAVE ALPHABETIQUE
//		      else{
//		    	  generationEnigmePaveAlphabetique();
//		      }
//		      
		      // GENERATION MODULE UNE FOIS TOUTES LES INFOS DISPOS
//		      initiatingModule();
		      
		      // GENERATION DES LOGS
//		      printingLogs();
//			  
//			  
//		  }
		  
		  if(i == 2) {
			  generationEnigmeCables();
			  initiatingModule();
		  }
		  else if(i == 3) {
			  generationEnigmePaveAlphabetique();
			  initiatingModule();
		  }
		  else if(i == 4) {
			  generationEnigmePaveNumerique();
			  initiatingModule();
		  }
		  else if(i == 5) {
			  generationEnigmeSimon();
			  initiatingModule();
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
	  
	  modules.get(2).setActive(true);

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
	  	if(nomDeLEnigme == "Cut red cable") {
	  		libelleDeLIndice = "Indice: (255,0,0)";
	  	}
	  	else if(nomDeLEnigme == "Cut green cable") {
	  		libelleDeLIndice = "Indice: (0,255,0)";
	  	}
	  	else if(nomDeLEnigme == "Cut blue cable") {
	  		libelleDeLIndice = "Indice: (0,0,255)";
	  	}
	  	else {
	  		libelleDeLIndice = "Indice: (255,255,0)";
	  	}
	  	
	  	modules.get(moduleEnCours).getEnigmeBoard().getEnigme().setName(nomDeLEnigme);
  		modules.get(moduleEnCours).getEnigmeBoard().getEnigme().getIndice().setIndiceText(libelleDeLIndice);

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
	  	//modules.get(moduleEnCours).getEnigmeBoard().getEnigme().setName(nomDeLEnigme);
  	}
  	
  	private void generationEnigmePaveNumerique() {
		purgeArrays();
		initialisationArrays();

		libelleDeLIndice = "Suite arithmétique N, avec i = 1 et quatre itérations à n = i + 1.";
  	  
  	    
  	    String choixDeLEnigme = listeEnigmesPaveNumerique.get(gen.nextInt(listeEnigmesPaveNumerique.size()));
  	    nomDeLEnigme = choixDeLEnigme;
  	  
  	    if(nomDeLEnigme == "Traité d'Amiens") {
  	  	    libelleDeLIndice = "Traité d'Amiens";
  		    ordreDesTouchesPaveNumerique = "1423";
  	    }
  	    else if(nomDeLEnigme == "8500/60+1000-2/3") {
  	  	    libelleDeLIndice = "8500/60+1000-2/3.";
  	    	ordreDesTouchesPaveNumerique = "1141";
  	    }
  	    else{
  	  	    libelleDeLIndice = "Suite arithmétique N, avec i = 1 et quatre itérations à n = i + 1.";
  	    	ordreDesTouchesPaveNumerique = "1234";
  	    }
  	    if(disableConsoleLogs != 1) {
  	    	System.out.println(ordreDesTouchesPaveNumerique);
  	  }
  	  //modules.get(moduleEnCours).getEnigmeBoard().getEnigme().setName(nomDeLEnigme);
  	}
  	
  	private void generationEnigmePaveAlphabetique() {
		purgeArrays();
		initialisationArrays();

	  	libelleDeLIndice = "Unité d'élite de la gendarmerie francaise";  	  
  	    
  	    String choixDeLEnigme = listeEnigmesPaveAlphabetique.get(gen.nextInt(listeEnigmesPaveAlphabetique.size()));
  	    nomDeLEnigme = choixDeLEnigme;
  	  
  	    if(nomDeLEnigme == "PUSH") {
  	  	    libelleDeLIndice = "PUSH";
  		    lettre1 = "P";
  		    lettre2 = "U";
  		    lettre3 = "S";
  		    lettre4 = "H";
  		  	libelleDeLIndice = "Envoi des commits sur GitHub";  	  

  	    }
  	    else if(nomDeLEnigme == "GIGN") {
  	  	    libelleDeLIndice = "GIGN";
  	  	    lettre1 = "G";
		    lettre2 = "I";
		    lettre3 = "G";
		    lettre4 = "N";
		  	libelleDeLIndice = "Unité d'élite de la gendarmerie francaise";  	  

  	    }
  	    else if(nomDeLEnigme == "ALAN") {
	  	    libelleDeLIndice = "ALAN";
	  	    lettre1 = "A";
		    lettre2 = "L";
		    lettre3 = "A";
		    lettre4 = "N";
		  	libelleDeLIndice = "Aventurier perdu de Jumanji";  	  

	    }
  	    else if(nomDeLEnigme == "PULL") {
	  	    libelleDeLIndice = "PULL";
	  	    lettre1 = "P";
		    lettre2 = "U";
		    lettre3 = "L";
		    lettre4 = "L";
		  	libelleDeLIndice = "Récupération des commits de GitHub";  	  

	    }
  	    else{
  	  	    libelleDeLIndice = "CODE";
  	  	    lettre1 = "C";
		    lettre2 = "O";
		    lettre3 = "D";
		    lettre4 = "E";
		  	libelleDeLIndice = "Un mot de passe est un ...";
		  	
  	    }
  	    if(disableConsoleLogs != 1) {
  	    	System.out.println(ordreDesTouchesPaveNumerique);
  	  }
	  	
	  	//modules.get(moduleEnCours).getEnigmeBoard().getEnigme().setName(nomDeLEnigme);
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
								  "Pas d'énigme",
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
								  "Pas d'énigme",
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
  			System.out.println("Module numéro "+i+" initialisé. Ce module de type : '"+modules.get(i).getEnigmeBoard().getNameBoard()+
  				  "' comporte une énigme de type : '"+modules.get(i).getEnigmeBoard().getEnigme().getNameEnigme()+
  				  "' ainsi que l'indice suivant associé : "+modules.get(i).getEnigmeBoard().getEnigme().getIndice().getIndiceText()+
  				  ".");
  		}
  	}
  	
  	private void printingMinuteurLog() {
  		if(disableConsoleLogs != 1) {
  			System.out.println("Module numéro "+i+" initialisé. Ce module de type : 'Minuteur' ne comporte pas d'énigme.");
  		}
  	}
  	
  	// COMPTE A REBOURS, GAME STEPS
  	private void startingCountdown() {
  		
	  	// INSTANCIATION DU MINUTEUR
	  	Minuteur deathClock = new Minuteur(delaiMinuteur);
	  	
		engineClock.schedule(new TimerTask() {
	  		  public void run() {
	  			  data.setStepNumber(data.getStepNumber() + 1);
//				  System.out.println("Le timer est à : "+data.getStepNumber()+" secondes.");
//				  System.out.println("La deathClock est à : "+deathClock.getCompteARebours()+" secondes.");
	  			  
	  			  // CALCUL DES MINUTES ET SECONDES
	  			  finalCountdown = deathClock.getCompteARebours() - data.getStepNumber();
	  			  finalCountdownFormatedMinutes = finalCountdown/60;
	  			  finalCountdownFormatedSeconds = finalCountdown%60;
	  			  
	  			  if(finalCountdownFormatedMinutes < 10) {
	  				 if(finalCountdownFormatedSeconds < 10) {
	  					if(finalCountdownFormatedMinutes > -1 && finalCountdownFormatedSeconds > -1) {
			  				view.getTimer().setText("0"+finalCountdownFormatedMinutes+":0"+finalCountdownFormatedSeconds);
	  					}
	  				 }
	  				 else {
	  					 if(finalCountdownFormatedMinutes > -1 && finalCountdownFormatedSeconds > -1) {
				  			  view.getTimer().setText("0"+finalCountdownFormatedMinutes+":"+finalCountdownFormatedSeconds);
	  					 }
	  				 }
	  			  }
	  			  else {
	  				if(finalCountdownFormatedSeconds < 10) {
	  					if(finalCountdownFormatedMinutes > -1 && finalCountdownFormatedSeconds > -1) {
	  						view.getTimer().setText(finalCountdownFormatedMinutes+":0"+finalCountdownFormatedSeconds);
	  					}
		  				
	  				}
	  				else {
	  					if(finalCountdownFormatedMinutes > -1 && finalCountdownFormatedSeconds > -1) {
	  						view.getTimer().setText(finalCountdownFormatedMinutes+":"+finalCountdownFormatedSeconds);
	  					}
			  			  
	  				}
	  			  }
	  			  		  
	  			  // PRINT, IF COUNTDOWN ECOULE = VOUS ETES MORT
	  			  if(finalCountdownFormatedMinutes > 0 || finalCountdownFormatedSeconds > -1) {
	  				  if(disableConsoleLogs != 1) {
	  					System.out.println("Le final countdown formaté est à : "+finalCountdownFormatedMinutes+" minutes et "+finalCountdownFormatedSeconds+" secondes.");
	  				  }
	  				
	  			  }
	  			  else {
	  				if(disableConsoleLogs != 1) {
	  	  			  view.getTimer().setText("BOOM");
	  					System.out.println("Partie terminée, vous êtes mort.");
	  				}
	  			  }
	  			  
	  			    
	  			  
	  		  }
	  	  },0,1000);
	  }
  	
  	  
	  
  	  public void abortMission() {
  		if(disableConsoleLogs != 1) {
			System.out.println("Partie terminée, vous avez choisi la mort.");
		}
  	  }
  	  
  	  public void initializingSimon() {
  		  System.out.println("C'est bien un Simon");
	  }
  	  
  	  public void initializingPaveNumerique() {
  		  System.out.println("C'est bien un Pavé Numérique");
	  }
  	  
  	  public void initializingPaveAlphabetique() {
		  System.out.println("C'est bien un Pavé Alphabétique");
	  }
  	  
  	  public void initializingCables() {
		  System.out.println("C'est bien des câbles");
	  }
  	  
  	  public void revealModule(int i) {
  		  if(modules.get(i).isActive == true && i > 1) {
  			  if(modules.get(i+1) != null) {
  	  			  modules.get(i+1).isActive();
  	  			  moduleEnCours = i + 1;
  			  }
  		  }
  		  if (modules.get(moduleEnCours).getEnigmeBoard().getNameBoard().equals("Simon")) {
  			  initializingSimon();
		  }
  		  if (modules.get(moduleEnCours).getEnigmeBoard().getNameBoard().equals("Pavé Numérique")) {
			  initializingPaveNumerique();
		  }
  		  if (modules.get(moduleEnCours).getEnigmeBoard().getNameBoard().equals("Pavé Alphabétique")) {
  			initializingPaveAlphabetique();
		  }
  		  if (modules.get(moduleEnCours).getEnigmeBoard().getNameBoard().equals("Câbles")) {
  			initializingCables();
		  }
		  
	  }
	  
	  public void resolvedModule(int moduleEnCours) {
		  modules.get(moduleEnCours).isResolved = true;
	  }

	  public void checkIfCodeIsValidated(){
		  

	  	  if(view.getLettre1().getText().equals(lettre1) && view.getLettre2().getText().equals(lettre2) && view.getLettre3().getText().equals(lettre3) && view.getLettre4().getText().equals(lettre4)){
	  		  System.out.println("L'opérateur a passé le pavé alphabétique avec succès.");
	  		  view.getTextLogs().setText(view.getTextLogs().getText()+">> L'opérateur a passé le pavé alphabétique avec succès.\n");
	  		  view.getRec_check2().setFill(new ImagePattern(view.getCheck()));
	  		  view.getCamembert().setFill(new ImagePattern(view.getCamembert_img3()));
	  		  alpha_fait = true;
	  		  view.refreshCadenas();
//	  		  revealModule(moduleEnCours);
	  		  modules.get(3).setActive(false);
	  		  modules.get(3).setResolved(true);
	  		  modules.get(4).setActive(true);
	  	  }
	  }
	  
	  public void checkIfNumIsValidated(){

		      String logs = new String(view.getTextLogs().getText(length_logs-3,length_logs+1));

		      if(ordreDesTouchesPaveNumerique.equals(logs)) {
	        		view.getRec_check3().setFill(new ImagePattern(view.getCheck()));
	  	  		  	view.getCamembert().setFill(new ImagePattern(view.getCamembert_img4()));
	  	  		  	modules.get(4).setActive(false);
	  	  		  	modules.get(4).setResolved(true);
	  	  		  	modules.get(5).setActive(true);
	  	  		  	view.refreshSimon();
	  	  		  	launchingSimon();
		      }else {
		    	  defeat();
	        	  view.getRec_check3().setFill(new ImagePattern(view.getNoCheck()));
		      }
	  }
	  
//	  public void launchingSimon() {
//        	fourSteps = data.getStepNumber();
//        	fourStepsPlusFour = fourSteps + 4;
//        	
//        	if(showSimonOrder == false) {
//        		simonClock.schedule(new TimerTask(){
//	        		public void run() {
//	        				if(fourSteps == fourStepsPlusFour - 4) {
//	        					System.out.println("Simon rouge");
//	        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge_light()));
//	        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
//	        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
//	        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
//	        				}
//	        				if(fourSteps == fourStepsPlusFour - 3) {
//	        					System.out.println("Simon vert");	
//	        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert_light()));
//	        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
//	        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
//	        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
//	        				}
//	        				if(fourSteps == fourStepsPlusFour - 2) {
//	        					System.out.println("Simon jaune");
//	        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune_light()));
//	        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
//	        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
//	        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
//	        				}
//	        				if(fourSteps == fourStepsPlusFour - 1) {
//	        					System.out.println("Simon bleu");	
//	        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu_light()));
//	        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
//	        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
//	        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
//	        				}
//	        				if(fourSteps == fourStepsPlusFour) {
//	        					System.out.println("Aucun Simon");	
//	        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
//	        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
//	        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
//	        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
//	        				}
//		        			fourSteps++;
//	        		}
//	        	},0,1000);
//        	}
//        	else {
//        		System.out.println("Le simon a déjà été montré.");	
//        	}
//      		
//      		showSimonOrder = true;
//	  }
	  
	  public void launchingSimon() {
		  
		  	checkSimons.clear();
			checkSimons.add(checkSimon1);
			checkSimons.add(checkSimon2);
			checkSimons.add(checkSimon3);
			checkSimons.add(checkSimon4);
			checkSimons.add(checkSimon5);
			checkSimons.add(checkSimon6);
		  
	      	fourSteps = data.getStepNumber();
	      	fourStepsPlusFour = fourSteps + 6;
	      	
	      	ordreSimon.clear();
	      	
	      	for(int s = 0; s < 6; s++){
	      		ordreSimon.add(couleursSimon.get(gen.nextInt(couleursSimon.size())));
	      	}

			view.getTextLogs().setText(view.getTextLogs().getText()+">> Ordre du Simon : "+ordreSimon+".\n");
	      	
			i = 0;
	      	
	      	if(showSimonOrder == false) {
	      		
	      		simonClock.schedule(new TimerTask(){
		        		public void run() {	        			
		        			
		        			if(data.getStepNumber()%2 == 0) {
		        				
		        				if(fourSteps == fourStepsPlusFour - 6) {
		        					
		        					if(ordreSimon.get(i) == "Rouge") {
			        					System.out.println("Simon rouge");
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		    	        			}
		        					if(ordreSimon.get(i) == "Vert") {
		        						System.out.println("Simon vert");	
		        						view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert_light()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Jaune") {
		        						System.out.println("Simon jaune");
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Bleu") {
		        						System.out.println("Simon bleu");	
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
		        					}
		        					i++;
		        				}
		        				if(fourSteps == fourStepsPlusFour - 5) {
		        					if(ordreSimon.get(i) == "Rouge") {
			        					System.out.println("Simon rouge");
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		    	        			}
		        					if(ordreSimon.get(i) == "Vert") {
		        						System.out.println("Simon vert");	
		        						view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert_light()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Jaune") {
		        						System.out.println("Simon jaune");
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Bleu") {
		        						System.out.println("Simon bleu");	
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
		        					}
		        					i++;
		        					
		        				}
		        				if(fourSteps == fourStepsPlusFour - 4) {
		        					if(ordreSimon.get(i) == "Rouge") {
			        					System.out.println("Simon rouge");
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		    	        			}
		        					if(ordreSimon.get(i) == "Vert") {
		        						System.out.println("Simon vert");	
		        						view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert_light()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Jaune") {
		        						System.out.println("Simon jaune");
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Bleu") {
		        						System.out.println("Simon bleu");	
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
		        					}
		        					i++;
		        				}
		        				if(fourSteps == fourStepsPlusFour - 3) {
		        					if(ordreSimon.get(i) == "Rouge") {
			        					System.out.println("Simon rouge");
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		    	        			}
		        					if(ordreSimon.get(i) == "Vert") {
		        						System.out.println("Simon vert");	
		        						view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert_light()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Jaune") {
		        						System.out.println("Simon jaune");
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Bleu") {
		        						System.out.println("Simon bleu");	
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
		        					}
		        					i++;
		        				}
		        				if(fourSteps == fourStepsPlusFour - 2) {
		        					if(ordreSimon.get(i) == "Rouge") {
			        					System.out.println("Simon rouge");
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		    	        			}
		        					if(ordreSimon.get(i) == "Vert") {
		        						System.out.println("Simon vert");	
		        						view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert_light()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Jaune") {
		        						System.out.println("Simon jaune");
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Bleu") {
		        						System.out.println("Simon bleu");	
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
		        					}
		        					i++;
		        				}
		        				if(fourSteps == fourStepsPlusFour - 1) {
		        					if(ordreSimon.get(i) == "Rouge") {
			        					System.out.println("Simon rouge");
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		    	        			}
		        					if(ordreSimon.get(i) == "Vert") {
		        						System.out.println("Simon vert");	
		        						view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert_light()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Jaune") {
		        						System.out.println("Simon jaune");
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					}
		        					if(ordreSimon.get(i) == "Bleu") {
		        						System.out.println("Simon bleu");	
			        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu_light()));
			        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
			        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
			        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
		        					}
		        					i++;
		        				}
		        				if(fourSteps == fourStepsPlusFour) {
		        					System.out.println("Aucun Simon");	
		        					view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
		        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
		        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
		        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
		        				}
			        			fourSteps++;
		        			}
		        			else {
		        				view.getRec_simon4().setFill(new ImagePattern(view.getSimon_bleu()));
	        					view.getRec_simon2().setFill(new ImagePattern(view.getSimon_vert()));
	        					view.getRec_simon3().setFill(new ImagePattern(view.getSimon_jaune()));
	        					view.getRec_simon1().setFill(new ImagePattern(view.getSimon_rouge()));
		        			}
		        		}
		        	},0,1000);
	      	}
	      	else {
	      		System.out.println("Le simon a déjà été montré.");	
	      	}
	    		
	    		showSimonOrder = true;
		  }
	  
	  
	  public void victory() {
		  engineClock.cancel();
		  view.getTimer().setText("VICTOIRE");
		  System.out.println("L'opérateur a désamorcé la bombe avec succès.");
		  view.getTextLogs().setText(view.getTextLogs().getText()+">> L'opérateur a désamorcé la bombe avec succès.\n");
	  }
	  
	  public void victoryIsNear() {
		  victory = true;
		  simon_fait = true;
		  modules.get(5).setResolved(true);
		  pressAbortToWin = true;
		  view.getTextLogs().setText(view.getTextLogs().getText()+"\n>> La victoire est proche, le dénouement imminent...\n");
	  }
	  
	  public void defeat() {
		  engineClock.cancel();
		  view.getTimer().setText("BOOM");
          view.getTextLogs().setText(view.getTextLogs().getText()+"\n>> Partie terminée, l'opérateur a été tué par l'explosion.");
		  defeat = true;
	  }
}
