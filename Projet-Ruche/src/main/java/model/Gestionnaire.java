package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gestionnaire")
@DiscriminatorValue("gestionnaire")
public class Gestionnaire extends User {

	private Ruche ruche ; //optionnel
	private Production production ; //optionnel
	
	
	public Gestionnaire(String password, String login) {
		super(password, login);
		// TODO Auto-generated constructor stub
	}
	
	public Gestionnaire() {
		// TODO Auto-generated constructor stub
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
		return "Gestionnaire [ruche=" + ruche + ", production=" + production + "]";
	}

	
}
