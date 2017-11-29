/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package specifications;

import javafx.scene.Parent;
import javafx.scene.control.Button;

public interface ViewerService{
  public void init();
  public Parent getPanel();
  public Button getJouer();
  public Button getInstruction();

}
