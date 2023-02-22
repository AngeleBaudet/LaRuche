package model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="achat")
public class Achat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDate dateAchat ; 
	
	@ManyToOne
	@JoinColumn(name="client")
	private Client client; 
	
	@ManyToOne
	@JoinColumn(name="production")
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
