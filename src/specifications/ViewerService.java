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

public interface ViewerService{
	public void init();
	  public Parent getPanel();
	  
	  public ReadService getData();
	  public void setData(ReadService data);
	  
	  public GridPane getGridpane_malette();
	  public void setGridpane_malette(GridPane gridpane_malette);
  
	  public TextField getTimer();
	  public void setTimer(TextField timer);
	  
	  public Button getBoutton_rouge();
	  public void setBoutton_rouge(Button boutton_rouge);
	  
	  public Button getBouton_up1();
	  public void setBouton_up1(Button bouton_up1);
	  
	  public Button getBouton_up2();
	  public void setBouton_up2(Button bouton_up2);
	  
	  public Button getBouton_up3();
	  public void setBouton_up3(Button bouton_up3);
	  
	  public Button getBouton_up4();
	  public void setBouton_up4(Button bouton_up4);
	  
	  public TextField getLettre1();
	  public void setLettre1(TextField lettre1);
	  
	  public TextField getLettre2();
	  public void setLettre2(TextField lettre2);

	  public TextField getLettre3();
	  public void setLettre3(TextField lettre3);
	  
	  public TextField getLettre4();
	  public void setLettre4(TextField lettre4);

	  public Button getBouton_down1();
	  public void setBouton_down1(Button bouton_down1);
	  
	  public Button getBouton_down2();
	  public void setBouton_down2(Button bouton_down2);
	  
	  public Button getBouton_down3();
	  public void setBouton_down3(Button bouton_down3);
	  
	  public Button getBouton_down4();
	  public void setBouton_down4(Button bouton_down4);

	  
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
	  
	  public Image getCamembert_img();
	  public void setCamembert_img(Image camembert_img);

}
