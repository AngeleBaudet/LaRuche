package fr.ruche.model;

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
	
	/*
	@OneToMany //unidirectionnel
	@JoinColumn(nullable=true)
	private List<Production> production = new ArrayList() ; //optionnel
	
	@OneToMany
	@JoinColumn(name="Recolteur")
	private List<Ruche> listeRuche = new ArrayList();
	*/
	
	
	
	// Constructeurs
	public Recolteur(String password, String login) {
		super(password, login);

	}
	
	public Recolteur() {
		// TODO Auto-generated constructor stub
	}

	
	// Getters et setters

/*
	public List<Ruche> getListeRuche() {
		return listeRuche;
	}

	public void setListeRuche(List<Ruche> listeRuche) {
		this.listeRuche = listeRuche;
	}

	public List<Production> getProduction() {
		return production;
	}

	public void setProduction(List<Production> production) {
		this.production = production;
	}

	*/
	
}
