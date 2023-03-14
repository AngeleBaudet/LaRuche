package fr.ruche.request;

import java.time.LocalDate;
import java.util.List;

import fr.ruche.model.Production;

//Classe qui sert à créer le "form" pour envoyer une requête (par exemple add)
public class AchatRequest {

	private LocalDate dateAchat;
	
	private int idClient;
	
	private List<Production> listeProduction;

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public List<Production> getListeProduction() {
		return listeProduction;
	}

	public void setListeProduction(List<Production> listeProduction) {
		this.listeProduction = listeProduction;
	}


	
	
}
