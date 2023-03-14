package fr.ruche.request;

import fr.ruche.model.Adresse;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {
	
	@NotBlank
	private String password; 
	@NotBlank
	private String login ; 
	@NotBlank
	private String type ; //gestionnaire, recolteur, client

	
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	
	
}
