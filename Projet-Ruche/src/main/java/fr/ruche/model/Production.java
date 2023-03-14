package fr.ruche.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import fr.ruche.api.Views;
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
	@JsonView(Views.Common.class)
	private Integer id;
	
	@Column(columnDefinition = "DECIMAL(4,2)")
	@JsonView(Views.Production.class)
	private double quantite ; //en kg!!
	
	@JsonView(Views.Production.class)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate annee ; 
	
	@ManyToOne
	@JsonView(Views.Production.class)
	private Ruche ruche ;
	
	@Column(name="produit",columnDefinition = "ENUM('Miel','Pollen','Cire','Gelee_Royale')")
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Production.class)
	private Produit produit ; 
	
	@ManyToOne
	@JsonView(Views.Production.class)
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
