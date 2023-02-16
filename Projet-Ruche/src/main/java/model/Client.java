package model;

public class Client extends User {
	
	private String nom; 
	private String prenom;

	public Client(String password, String login, String prenom, String nom) {
		super(password, login);
		this.nom = nom ; 
		this.prenom = prenom ;
	}

}
