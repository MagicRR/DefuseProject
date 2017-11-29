package data;

import java.awt.Button;
import java.util.ArrayList;

import javax.swing.JButton;

import specifications.EnigmeBoardService;

public class EnigmeBoard implements EnigmeBoardService{

	private String nameBoard;
	private Enigme enigme;
	private ArrayList<Cable> cables;
	private Simon simon;
	private PaveNum paveNum;
	private PaveAlpha paveAlpha;
	private Button button;
	
	@Override
	public void init() {}
	
	public EnigmeBoard(String nameBoard, Enigme enigme, Button button) {
		this.nameBoard = nameBoard;
		this.enigme = enigme;
		this.button = button;
	}

	
//	public EnigmeBoard(String nameBoard, Enigme enigme, ArrayList<Cable> cables) {
//		this.name = nameBoard;
//		this.enigme = enigme;
//		this.cables = cables;
//	}
//	
//	public EnigmeBoard(String nameBoard, Enigme enigme, Simon simon) {
//		this.name = nameBoard;
//		this.enigme = enigme;
//		this.simon = simon;
//	}
//	
//	public EnigmeBoard(String nameBoard, Enigme enigme, PaveNum paveNum) {
//		this.name = nameBoard;
//		this.enigme = enigme;
//		this.paveNum = paveNum;
//	}
//	
//	public EnigmeBoard(String nameBoard, Enigme enigme, PaveAlpha paveAlpha) {
//		this.name = nameBoard;
//		this.enigme = enigme;
//		this.paveAlpha = paveAlpha;
//	}
	
	public String getNameBoard() {
		return nameBoard;
	}

	public void setName(String nameBoard) {
		this.nameBoard = nameBoard;
	}
	
	public Enigme getEnigme() {
		return enigme;
	}

	public void setEnigme(Enigme enigme) {
		this.enigme = enigme;
	}

	public ArrayList<Cable> getCables() {
		return cables;
	}

	public void setCables(ArrayList<Cable> cables) {
		this.cables = cables;
	}

	public Simon getSimon() {
		return simon;
	}

	public void setSimon(Simon simon) {
		this.simon = simon;
	}

	public PaveNum getPaveNum() {
		return paveNum;
	}

	public void setPaveNum(PaveNum paveNum) {
		this.paveNum = paveNum;
	}

	public PaveAlpha getPaveAlpha() {
		return paveAlpha;
	}

	public void setPaveAlpha(PaveAlpha paveAlpha) {
		this.paveAlpha = paveAlpha;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
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
