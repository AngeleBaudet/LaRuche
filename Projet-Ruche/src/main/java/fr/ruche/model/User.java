package fr.ruche.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="user")
//PENSER A CHANGER SI CA NE CONVIENT PAS
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_personne",columnDefinition = "ENUM('recolteur','gestionnaire','client')")
public abstract class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;
	@Column(length = 50 ,nullable = false)
	protected String password ; 
	@Column(length = 50 ,nullable = false)
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
