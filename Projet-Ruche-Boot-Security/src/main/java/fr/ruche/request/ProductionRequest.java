package fr.ruche.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.ruche.model.Produit;

public class ProductionRequest {
	
	private double stock ; 
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate annee ; 
	
	private int rucheId ;
	
	private Produit produit;

	private double prixKg;
	
	private int recolteurId;
	
	public double getPrixKg() {
		return prixKg;
	}

	public void setPrixKg(double prixKg) {
		this.prixKg = prixKg;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
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

	public int getRecolteurId() {
		return recolteurId;
	}

	public void setRecolteurId(int recolteurId) {
		this.recolteurId = recolteurId;
	}
	
}
