package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("recolteur")
public class Recolteur extends User {
	
	@OneToMany
	@JoinColumn(nullable=true)
	private Production production ; //optionnel
	
	@OneToMany (mappedBy = "ruche")
	private static List<Ruche> listeRuche;
	
	// Constructeurs
	public Recolteur(String password, String login) {
		super(password, login);

	}
	
	public Recolteur() {
		// TODO Auto-generated constructor stub
	}

	
	// Getters et setters

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public static List<Ruche> getListeRuche() {
		return listeRuche;
	}

	public static void setListeRuche(List<Ruche> listeRuche) {
		Recolteur.listeRuche = listeRuche;
	}


	
	
}
