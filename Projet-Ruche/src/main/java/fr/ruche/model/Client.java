package fr.ruche.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("client")
public class Client extends User {
	
	@Column(length = 20)
	private String nom; 
	@Column(length = 20)
	private String prenom;
	
	@OneToMany (mappedBy = "client")
	private List<Achat> achats;
	
	@Embedded
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
		return "Client [nom=" + nom + ", prenom=" + prenom + ", achats=" + achats + ", adresse=" + adresse + "]";
	}

	
	
}
