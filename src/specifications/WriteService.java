/* ******************************************************
 * Defuse Project - Composants logiciels 2017.
 * Copyright (C) 2017 S. MINES, A. ORDOGH, R. RICHARD
 * ******************************************************/
package specifications;

import java.util.Timer;

public interface WriteService {
	public void setStepNumber(int n);
	public Timer setCountdown(Timer t);
}
