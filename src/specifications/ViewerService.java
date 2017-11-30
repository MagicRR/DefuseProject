/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package specifications;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public interface ViewerService{
	
	  public void init();
	  public Parent getPanel();	  
	  public ReadService getData();  
	  public GridPane getGridpane_malette();
	  public TextField getTimer();  
	  public Button getBoutton_rouge();  
	  public Button getBouton_up1();  
	  public Button getBouton_up2();  
	  public Button getBouton_up3();
	  public Button getBouton_up4();
	  public TextField getLettre1();
	  public TextField getLettre2();
	  public TextField getLettre3();
	  public TextField getLettre4();
	  public Button getBouton_down1();
	  public Button getBouton_down2();
	  public Button getBouton_down3();	  
	  public Button getBouton_down4();  
	  public Button getBut_simon1();	  
	  public Button getBut_simon2();	  
	  public Button getBut_simon3();	  
	  public Button getBut_simon4();	  
	  public Button getZone_cable_1();	  
	  public Button getZone_cable_2();	  
	  public Button getZone_cable_3();	  
	  public Button getZone_cable_4();	  
	  public Button getZone_chiffre_1();	  
	  public Button getZone_chiffre_2();	  
	  public Button getZone_chiffre_3();	  
	  public Button getZone_chiffre_4();
	  public TextField getTextLogs();	  
	  public Image getCamembert_img();
	  public Rectangle getRec_chiffre1();
	  public Rectangle getRec_chiffre2();
	  public Rectangle getRec_chiffre3();
	  public Rectangle getRec_chiffre4();
	  public Image getChiffre1();
	  public Image getChiffre2();
	  public Image getChiffre3();
	  public Image getChiffre4();
	  public Circle getCamembert();
	  public Boolean getModuleSimon();
	  public Boolean getModuleAlpha();
	  public Boolean getModuleNum();
	  public Boolean getModuleCable();
	  public Button getIndice();
	  public void setModuleAlpha(Boolean moduleAlpha);
	  public void setModuleNum(Boolean moduleNum);
	  public void setModuleCable(Boolean moduleCable);
	  public void setModuleSimon(Boolean moduleSimon);

}
