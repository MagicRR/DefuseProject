package data;

import java.util.ArrayList;

import specifications.MalletteService;

public class Mallette implements MalletteService {
	private String matricule;
	public ArrayList<Module> modules;
	
	public Mallette(String matricule, ArrayList<Module> modules) {
		this.matricule = matricule;
		this.modules = modules;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public ArrayList<Module> getModules() {
		return modules;
	}

	public void setModules(ArrayList<Module> modules) {
		this.modules = modules;
	}

	@Override
	public String getNameEnigme() {
		return null;
	}

	@Override
	public void init() {}
	
	
}
