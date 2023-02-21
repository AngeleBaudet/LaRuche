package model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
