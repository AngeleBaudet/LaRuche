package fr.ruche.request;

import java.time.LocalDate;
import java.util.List;

import fr.ruche.model.Produit;

//Classe qui sert à créer le "form" pour envoyer une requête (par exemple add)
public class AchatRequest {

	private LocalDate dateAchat;
	
	private int idClient;
	
	private Produit produit;
	
//	private Produit produit;


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

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

}
