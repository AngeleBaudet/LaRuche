package fr.ruche.request;

public class UserRequest {
	
	private String password; 
	private String login ; 
	private String type ; //gestionnaire, client, recolteur
	
	
	
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
