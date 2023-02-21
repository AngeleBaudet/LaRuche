package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
	

	public Production()
	{}
	
	public Production(double quantite, LocalDate annee, Ruche ruche, Produit produit) {
		this.quantite = quantite;
		this.annee = annee;
		this.ruche = ruche;
		this.produit = produit;
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

	
	@Override
	public String toString() {
		return "Production [id=" + id + ", quantite=" + quantite + ", annee=" + annee + ", ruche=" + ruche
				+ ", produit=" + produit + "]";
	}

	public static void donneeRecolte(double kg, Produit type) {
		
	}
	
	
	
}
