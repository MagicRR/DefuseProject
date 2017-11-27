package data;

public class Enigme {
	private String nameEnigme;
	private int difficulty;
	private Indice indice;
	
	public Enigme(String nameEnigme, int difficulty, Indice indice) {
		super();
		this.nameEnigme = nameEnigme;
		this.difficulty = difficulty;
		this.indice = indice;
	}

	public String getNameEnigme() {
		return nameEnigme;
	}

	public void setName(String nameEnigme) {
		this.nameEnigme = nameEnigme;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public Indice getIndice() {
		return indice;
	}

	public void setIndice(Indice indice) {
		this.indice = indice;
	}
	
}
