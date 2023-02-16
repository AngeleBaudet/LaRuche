package model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Production {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer id;
	private double quantite ; //en kg!!
	private LocalDate annee ; 
	private Ruche ruche ; 
	private Produit produit ; 
	private Recolteur recolteur ; 

	public Production()
	{
	}
	
	public Production(double quantite, LocalDate annee, Ruche ruche, Produit produit, Recolteur recolteur) {
		this.quantite = quantite;
		this.annee = annee;
		this.ruche = ruche;
		this.produit = produit;
		this.recolteur = recolteur;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
	@Override
	public String toString() {
		return "Production [id=" + id + ", quantite=" + quantite + ", annee=" + annee + ", ruche=" + ruche
				+ ", produit=" + produit + ", recolteur=" + recolteur + "]";
	}

	public static void donneeRecolte(double kg, Produit type) {
		
	}
	
	
	
}
