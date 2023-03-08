package fr.ruche.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Adresse {

	@Column(length = 4)
	private String numero;
	@Column(length = 20)
	private String rue;
	@Column(length = 20)
	private String ville;
	@Column(length = 6)
	private String cp;

	public Adresse() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Adresse(String numero, String rue, String ville, String cp) {
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.cp = cp;
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

	@Override
	public String toString() {
		return "Adresse [numero=" + numero + ", rue=" + rue + ", ville=" + ville + ", cp=" + cp + "]";
	}	


	
}
