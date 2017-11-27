/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package specifications;

import tools.User;

public interface EngineService{
  public void init();
  public void start();
  public void stop();
  public void setHeroesCommand(User.COMMAND c);
}
