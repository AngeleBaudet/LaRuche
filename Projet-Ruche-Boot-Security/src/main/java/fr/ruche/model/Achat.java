package fr.ruche.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import fr.ruche.api.Views;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	@JsonView({Views.Achat.class, Views.Client.class})
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateAchat ; 
	
	@ManyToOne
	@JoinColumn(name="client")
	@JsonView(Views.Achat.class)
	private Client client; 
	
	@Column(name="typeProduit", columnDefinition = "ENUM('Miel','Pollen','Cire','Gelee_Royale')")
	@Enumerated(EnumType.STRING)
	@JsonView({Views.Achat.class,Views.Client.class})
	private Produit produit;
	
	public Achat() {}

	public Achat(Client client, Produit produit) {
		this.dateAchat = LocalDate.now();
		this.client = client;
		this.produit = produit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@Override
	public String toString() {
		return "Achat [id=" + id + ", dateAchat=" + dateAchat + ", client=" + client + "]";
	}


	
	
}
