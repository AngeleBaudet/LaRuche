package fr.ruche.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;

import fr.ruche.api.Views;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="achat")
public class Achat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private Integer id;
	
	@JsonView(Views.Common.class)
	private LocalDate dateAchat ; 
	
	@ManyToOne
	@JoinColumn(name="client")
	@JsonView(Views.Achat.class)
	private Client client; 
	
	@ManyToOne
	@JoinColumn(name="production")
	@JsonView(Views.Achat.class)
	private Production production ;
	
	public Achat() {}

	public Achat(Client client, Production production) {
		this.dateAchat = LocalDate.now();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Achat [dateAchat=" + dateAchat + ", client=" + client + ", production=" + production + "]";
	} 
	
	
}
