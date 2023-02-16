package model;

import java.time.LocalDate;

public class Achat {
	private LocalDate dateAchat ; 
	private Client client; 
	private Production production ;
	
	public Achat(LocalDate dateAchat, Client client, Production production) {
		this.dateAchat = dateAchat;
		this.client = client;
		this.production = production;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	@Override
	public String toString() {
		return "Achat [dateAchat=" + dateAchat + ", client=" + client + ", production=" + production + "]";
	} 
	
	
}
