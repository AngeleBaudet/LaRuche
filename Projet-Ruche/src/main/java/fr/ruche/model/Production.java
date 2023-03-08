package fr.ruche.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="production")
public class Production {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "DECIMAL(4,2)")
	private double quantite ; //en kg!!
	
	private LocalDate annee ; 
	
	@ManyToOne
	private Ruche ruche ;
	
	@Column(name="produit",columnDefinition = "ENUM('Miel','Pollen','Cire','Gelee_Royale')")
	@Enumerated(EnumType.STRING)
	private Produit produit ; 
	
	@ManyToOne
	private Recolteur recolteur;

	public Production()
	{}
	
	public Production(double quantite, Ruche ruche, Produit produit, Recolteur recolteur) {
		this.quantite = quantite;
		this.annee = LocalDate.now();
		this.ruche = ruche;
		this.produit = produit;
		this.recolteur=recolteur;
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

	public static void donneeRecolte(double kg, Produit type) {
		
	}
	
	
	
}
