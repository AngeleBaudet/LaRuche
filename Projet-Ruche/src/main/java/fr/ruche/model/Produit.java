package fr.ruche.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import fr.ruche.api.Views;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Produit {
Miel("Miel",10), Pollen("Pollen", 12), Cire("Cire", 15), Gelee_Royale("Gelée Royale", 20);

	@JsonView(Views.Achat.class)
	private String typeProduit ;
	
	@JsonView(Views.Achat.class)
	private double prix ;

	private Produit(double prix) {
		this.prix = prix;
	}

	Produit(String typeProduit, int prix) {
		this.prix = prix;
		this.typeProduit = typeProduit;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	} 
	
	
}
