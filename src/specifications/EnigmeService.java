/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package specifications;

public interface EnigmeService extends EnigmeReadService, EnigmeWriteService{
  public void init();
}