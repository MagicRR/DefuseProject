/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package specifications;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public interface ViewerService{
	public void init();
	  public Parent getPanel();
	  
	  public ReadService getData();
	  public void setData(ReadService data);
	  
	  public TextField getTimer();
	  public void setTimer(TextField timer);
	  
	  public Button getBoutton_rouge();
	  public void setBoutton_rouge(Button boutton_rouge);
	  
	  public Button getBut_simon1();
	  public void setBut_simon1(Button but_simon1);
	  
	  public Button getBut_simon2();
	  public void setBut_simon2(Button but_simon2);
	  
	  public Button getBut_simon3();
	  public void setBut_simon3(Button but_simon3);
	  
	  public Button getBut_simon4();
	  public void setBut_simon4(Button but_simon4);
	  
	  public Button getZone_cable_1();
	  public void setZone_cable_1(Button zone_cable_1);
	  
	  public Button getZone_cable_2();
	  public void setZone_cable_2(Button zone_cable_2);
	  
	  public Button getZone_cable_3();
	  public void setZone_cable_3(Button zone_cable_3);
	  
	  public Button getZone_cable_4();
	  public void setZone_cable_4(Button zone_cable_4);
	  
	  public Button getZone_chiffre_1();
	  public void setZone_chiffre_1(Button zone_chiffre_1);
	  
	  public Button getZone_chiffre_2();
	  public void setZone_chiffre_2(Button zone_chiffre_2);
	  
	  public Button getZone_chiffre_3();
	  public void setZone_chiffre_3(Button zone_chiffre_3);
	  
	  public Button getZone_chiffre_4();
	  public void setZone_chiffre_4(Button zone_chiffre_4);

	  public TextField getTextLogs();
	  public void setTextLogs(TextField textLogs);

}
