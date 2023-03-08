package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Achat;
import model.Adresse;
import model.Client;
import model.Gestionnaire;
import model.Production;
import model.Produit;
import model.Recolteur;
import model.Ruche;
import model.User;


public class testRuche {
	
	public static void main(String[] args) {

		Recolteur r1 = new Recolteur("rec1", "rec1");
		Recolteur r2 = new Recolteur("rec2", "rec2");
		Recolteur r3 = new Recolteur("rec3", "rec3");
		
		Gestionnaire g1 = new Gestionnaire("ges1", "ges1");

		Ruche ruche1 = new Ruche(2,false, r1);
		Ruche ruche2 = new Ruche(2,false, r2);
		Ruche ruche3 = new Ruche(2,false, r2);
		Ruche ruche4 = new Ruche(2,false, r2);
		Ruche ruche5 = new Ruche(2,false, r2);
		Ruche ruche6 = new Ruche(2,false, r2);
		Ruche ruche7 = new Ruche(2,false, r2);
		
		Adresse a1 = new Adresse("3", "Rue de la Paix", "Paris", "75000");
		
		Client c1 = new Client("c1", "c1","paul", "tartampion", a1);
		
		
		
		/*
		String typeProduit= saisieString("Saisir le type de récolte : "+Arrays.toString(Produit.values()));
		Produit choixProduit = Produit.valueOf(typeProduit);

		double kg = saisieDouble("Saisir la quantité en kg : ");

		Production.donneeRecolte(kg, choixProduit);
*/

	//	Production p0 = new Production(kg, date, ruche1, choixProduit);
	//	System.out.println(p1.getRuche().getRecolteur());
	//	System.out.println(p1.getQuantite());

		//	List<Production> = new ArrayList();
	//	List<Production> listeProd= new ArrayList();
	//	listeProd.add(p1);

	//	ruche1.setProductions(listeProd);


	//	System.out.println(ruche1.getProductions());
		
		Production p1 = new Production(2,ruche1, Produit.Miel,r1);
		Production p2 = new Production(1, ruche1, Produit.Gelee_Royale,r1);
		Production p3 = new Production(0.5, ruche1, Produit.Gelee_Royale,r2);

		Achat achat1 = new Achat(c1, p1);
		
//		r2.getProduction().add(p1);
//		r2.getProduction().add(p2);
//		r2.getListeRuche().add(ruche1);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projet-Ruche");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(r1);
		em.persist(r2);
		em.persist(r3);
		em.persist(ruche1);
		em.persist(ruche2);
		em.persist(ruche3);
		em.persist(ruche4);
		em.persist(ruche5);
		em.persist(ruche6);
		em.persist(ruche7);
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(g1);
		em.persist(c1);
		em.persist(achat1);

		em.getTransaction().commit();
		em.close();
		emf.close();



		

	}


}
