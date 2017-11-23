package data;

import java.awt.Button;
import java.util.ArrayList;

public class EnigmeBoard {
	private String name;
	private Enigme enigme;
	private ArrayList<Cable> cables;
	private Simon simon;
	private PaveNum paveNum;
	private PaveAlpha paveAlpha;
	private Button button;
	
	public EnigmeBoard(String name, Enigme enigme, ArrayList<Cable> cables, Simon simon, PaveNum paveNum,
			PaveAlpha paveAlpha, Button button) {
		this.name = name;
		this.enigme = enigme;
		this.cables = cables;
		this.simon = simon;
		this.paveNum = paveNum;
		this.paveAlpha = paveAlpha;
		this.button = button;
	}

	public class Cable{
		String color;
		Boolean isCut;
	}
	
	public class Simon{
		Button boutonRouge;
		Button boutonBleu;
		Button boutonJaune;
		Button boutonVert;
	}
	
	public class PaveNum{
		Button bouton1;
		Button bouton2;
		Button bouton3;
		Button bouton4;
	}
	
	public class PaveAlpha{
		Button boutonA;
		Button boutonB;
		Button boutonC;
		Button boutonD;
		Button boutonE;
		Button boutonF;
	}
}
