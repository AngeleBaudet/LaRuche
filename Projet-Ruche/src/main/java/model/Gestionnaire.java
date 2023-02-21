package model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("gestionnaire")
public class Gestionnaire extends User {

	@OneToMany(mappedBy="ruche")
	private List<Ruche> ruches; //optionnel
	
	@OneToMany
	@JoinColumn(nullable=true)
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
