package fr.ruche.request;

import com.fasterxml.jackson.annotation.JsonView;

import fr.ruche.api.Views;
import fr.ruche.model.Vulnerabilite;
import jakarta.validation.constraints.NotNull;

public class RucheRequest {
	
	@NotNull
	@JsonView(Views.Ruche.class)
	private int cadre;
	
	@NotNull
	@JsonView(Views.Ruche.class)
	private transient boolean limite;
	
	@JsonView(Views.Ruche.class)
	private Vulnerabilite vulnerabilite;
	
	@NotNull
	@JsonView(Views.Ruche.class)
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
