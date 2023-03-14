package fr.ruche.request;

import fr.ruche.model.Adresse;
import jakarta.validation.constraints.NotBlank;

public class ClientRequest extends UserRequest{

	@NotBlank
	private String nom;
	@NotBlank
	private String prenom;
	
	@NotBlank
	private String numero ;
	@NotBlank
	private String rue ;
	@NotBlank
	private String ville; 
	@NotBlank
	private String cp;
	
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}


	

}
