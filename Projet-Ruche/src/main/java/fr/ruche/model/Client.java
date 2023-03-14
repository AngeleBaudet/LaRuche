package fr.ruche.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import fr.ruche.api.Views;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("client")
public class Client extends User {
	
	@Column(length = 20)
	@JsonView(Views.Common.class)
	private String nom; 
	
	@Column(length = 20)
	@JsonView(Views.Common.class)
	private String prenom;
	
	@JsonView(Views.ClientDetails.class)
	@OneToMany (mappedBy = "client")
	private List<Achat> achats;
	
	@Embedded
	@JsonView(Views.Common.class)
	protected Adresse adresse;

	public Client(String password, String login, String prenom, String nom,Adresse adresse) {
		super(password, login);
		this.nom = nom ; 
		this.prenom = prenom ;
		this.adresse = adresse;
	}

	public Client() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}
	

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + "]";
	}

	
	
}
