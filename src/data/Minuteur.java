package data;

import java.util.Timer;

public class Minuteur {
	
	private Timer countdown;
	
	public Minuteur(Timer countdown) {
		this.countdown = countdown;
	}

	public Timer getCountdown() {
		return countdown;
	}

	public void setCountdown(Timer countdown) {
		this.countdown = countdown;
	}
}
