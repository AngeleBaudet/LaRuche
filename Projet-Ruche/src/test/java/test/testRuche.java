package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Gestionnaire;
import model.Production;
import model.Produit;
import model.Recolteur;
import model.Ruche;
import model.User;


public class testRuche {
	static LocalDate date = LocalDate.now();
	static User connected;


	public static String saisieString(String msg)
	{
		Scanner monScanner = new Scanner(System.in);
		System.out.println(msg);
		return monScanner.nextLine();
	}

	public static int saisieInt(String msg)
	{
		Scanner monScanner = new Scanner(System.in);
		System.out.println(msg);
		return monScanner.nextInt();
	}

	public static double saisieDouble(String msg)
	{
		Scanner monScanner = new Scanner(System.in);
		System.out.println(msg);
		return monScanner.nextDouble();
	}

	public static boolean saisieBoolean(String msg)
	{
		Scanner monScanner = new Scanner(System.in);
		System.out.println(msg);
		return monScanner.nextBoolean();
	}

	//Accueil de l'appli
	public static void menuPrincipal() 
	{
		System.out.println("Application Votre Ruche");
		System.out.println("1 - Se connecter");
		System.out.println("2 - Fermer");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : seConnecter();break;
		case 2 : System.exit(0);
		}

		menuPrincipal();
	}

	//Se connecter
	public static void seConnecter() {

		String vousEtes = saisieString("Etes vous gestionnaire ou recolteur ?");
		String login = saisieString("Saisir votre login");
		String password = saisieString("Saisir votre password");

		//parce qu'on a pas de dao !!-------------
		//	connected = daoC.findByLoginAndPassword(login, password);

		if (vousEtes.equalsIgnoreCase("gestionnaire")) {
			connected = new Gestionnaire(login, password);
		}
		else {
			connected = new Recolteur(login, password);
		}
		//----------------------------------------


		if(connected == null) 
		{
			System.out.println("Identifiants invalides");
		}
		else if(connected instanceof Gestionnaire) 
		{
			menuGestionnaire();
		}
		else if(connected instanceof Recolteur) 
		{
			menuRecolteur();
		}
	}

	//Celui a accès les fonctionnalités
	public static void menuGestionnaire() {
		System.out.println("Menu Gestionnaire");
		System.out.println("1 - ");
		System.out.println("2 - Se déconnecter");

	}

	//Récolter, voir ses récoltes/ rentrée récoltes /  
	public static void menuRecolteur() {
		System.out.println("Menu Recolteur");
		System.out.println("1 - Voir les récoltes");
		System.out.println("1 - Saisir une récolte");
		System.out.println("2 - Se déconnecter");
	}

	public static void Recolte() {
		System.out.println("Votre Récolte:");

	}


	public static void main(String[] args) {

		Recolteur r1 = new Recolteur("rec1", "rec1");
		Recolteur r2 = new Recolteur("rec2", "rec2");
		
		Gestionnaire g1 = new Gestionnaire("ges1", "ges1");

		Ruche ruche1 = new Ruche(2,false, r1);
		Ruche ruche2 = new Ruche(2,false, r2);
		
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

		
//		r2.getProduction().add(p1);
//		r2.getProduction().add(p2);
//		r2.getListeRuche().add(ruche1);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projet-Ruche");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(r1);
		em.persist(r2);
		em.persist(ruche1);
		em.persist(ruche2);
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(g1);


		em.getTransaction().commit();
		em.close();
		emf.close();



		

	}


}
