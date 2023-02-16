package model;

public class Recolteur extends User {

	private Ruche ruche ; //optionnel
	private Production production ; //optionnel
	
	public Recolteur(String password, String login) {
		super(password, login);

	}

	public Ruche getRuche() {
		return ruche;
	}

	public void setRuche(Ruche ruche) {
		this.ruche = ruche;
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	@Override
	public String toString() {
		return "Recolteur [ruche=" + ruche + ", production=" + production + ", password=" + password + ", login="
				+ login + "]";
	}

	
	
}
