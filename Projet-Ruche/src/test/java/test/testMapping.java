package test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testMapping {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projet-Ruche");


		emf.close();
	}
}
