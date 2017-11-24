package data;

public class Enigme {
	private String name;
	private int difficulty;
	private Indice indice;
	
	public Enigme(String name, int difficulty, Indice indice) {
		super();
		this.name = name;
		this.difficulty = difficulty;
		this.indice = indice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
