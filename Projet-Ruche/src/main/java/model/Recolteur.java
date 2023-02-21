package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="recolteur")
@DiscriminatorValue("recolteur")
public class Recolteur extends User {

	private Ruche ruche ; //optionnel
	private Production production ; //optionnel
	
	// Constructeurs
	public Recolteur(String password, String login) {
		super(password, login);

	}
	
	public Recolteur() {
		// TODO Auto-generated constructor stub
	}

	
	// Getters et setters
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

	//ToString
	@Override
	public String toString() {
		return "Recolteur [ruche=" + ruche + ", production=" + production + ", password=" + password + ", login="
				+ login + "]";
	}

	
	
}
