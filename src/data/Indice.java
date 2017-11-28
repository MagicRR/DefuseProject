package data;

import specifications.IndiceService;

public class Indice implements IndiceService {
	
	private String indiceText;
	
	public Indice(String indiceText) {
		this.indiceText = indiceText;
	}

	public String getIndiceText() {
		return indiceText;
	}

	public void setIndiceText(String indiceText) {
		this.indiceText = indiceText;
	}

	@Override
	public void init() {}
	
}
