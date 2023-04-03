package fr.ruche.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;

import fr.ruche.api.Views;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
//PENSER A CHANGER SI CA NE CONVIENT PAS
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_personne",columnDefinition = "ENUM('recolteur','gestionnaire','client')")
@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME,
		  include = JsonTypeInfo.As.PROPERTY,
		  property = "type")
@JsonSubTypes({
	  @JsonSubTypes.Type(value = Gestionnaire.class, name = "gestionnaire"),
	  @JsonSubTypes.Type(value = Recolteur.class, name = "recolteur"),
	  @JsonSubTypes.Type(value = Client.class, name = "client")
	})
public abstract class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	protected Integer id;
	
	@Column(length = 50 ,nullable = false)
	@JsonView(Views.User.class)
	protected String password ; 
	
	@Column(length = 50 ,nullable = false)
	@JsonView(Views.User.class)
	protected String login; 
		
	
	public User(String password, String login) {
		this.password = password;
		this.login = login;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public int getId() {
		return id;
	}


	public void setId(int id_User) {
		this.id = id_User;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", login=" + login + "]";
	}
	

	
}
