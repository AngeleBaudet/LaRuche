package fr.ruche.request;

import fr.ruche.model.Vulnerabilite;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RucheRequest {
	
	@NotNull
	private int cadre;
	@NotNull
	private transient boolean limite;
	
	private Vulnerabilite vulnerabilite;
	@NotNull
	private int recolteurId;
	
	public int getCadre() {
		return cadre;
	}
	public void setCadre(int cadre) {
		this.cadre = cadre;
	}
	public boolean isLimite() {
		return limite;
	}
	public void setLimite(boolean limite) {
		this.limite = limite;
	}
	public Vulnerabilite getVulnerabilite() {
		return vulnerabilite;
	}
	public void setVulnerabilite(Vulnerabilite vulnerabilite) {
		this.vulnerabilite = vulnerabilite;
	}
	public int getRecolteurId() {
		return recolteurId;
	}
	public void setRecolteurId(int recolteurId) {
		this.recolteurId = recolteurId;
	}
	
	

}
