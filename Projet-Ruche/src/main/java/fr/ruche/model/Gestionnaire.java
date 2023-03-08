package fr.ruche.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("gestionnaire")
public class Gestionnaire extends User {
	
	public Gestionnaire(String password, String login) {
		super(password, login);
		// TODO Auto-generated constructor stub
	}
	
	public Gestionnaire() {
		// TODO Auto-generated constructor stub
	}


	
}
