package fr.ruche.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.ruche.model.Produit;

public class ProductionRequest {
	
	private double quantite ; 
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate annee ; 
	
	private int rucheId ;
	
	private Produit produit;
	//private int produitId ; 
	
	private int recolteurId;


	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
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

	public int getRucheId() {
		return rucheId;
	}

	public void setRucheId(int rucheId) {
		this.rucheId = rucheId;
	}

//	public int getProduitId() {
//		return produitId;
//	}
//
//	public void setProduitId(int produitId) {
//		this.produitId = produitId;
//	}

	public int getRecolteurId() {
		return recolteurId;
	}

	public void setRecolteurId(int recolteurId) {
		this.recolteurId = recolteurId;
	}
	
}
