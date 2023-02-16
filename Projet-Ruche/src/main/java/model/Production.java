package model;

import java.time.LocalDate;

public class Production {
	private double quantite ; //en kg!!
	private LocalDate annee ; 
	private Ruche ruche ; 
	private Produit produit ; 
	private Recolteur recolteur ; 
	private static int id = 0;
	
	public Production(double quantite, LocalDate annee, Ruche ruche, Produit produit, Recolteur recolteur) {
		this.quantite = quantite;
		this.annee = annee;
		this.ruche = ruche;
		this.produit = produit;
		this.recolteur = recolteur;
		this.id++ ;
	}

	@Override
	public String toString() {
		return "Production [quantite=" + quantite + ", annee=" + annee + ", produit=" + produit
				+ ", recolteur=" + recolteur + "]";
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public LocalDate getAnnee() {
		return annee;
	}

	public void setAnnee(LocalDate annee) {
		this.annee = annee;
	}

	public Ruche getRuche() {
		return ruche;
	}

	public void setRuche(Ruche ruche) {
		this.ruche = ruche;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Recolteur getRecolteur() {
		return recolteur;
	}

	public void setRecolteur(Recolteur recolteur) {
		this.recolteur = recolteur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id_Production) {
		this.id = id_Production;
	} 
	
	public static void donneeRecolte(double kg, Produit type) {
		
	}
	
	
	
}
