package test;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Embedded;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import com.mysql.cj.Query;

import context.Singleton;
import dao.*;
import fr.ruche.model.*;

public class App {
	static LocalDate date = LocalDate.now();
	static User connected = null;
	static IDAOUser daoUser = Singleton.getInstance().getDaoUser();
	static IDAOProduction daoProduction = Singleton.getInstance().getDaoProduction();
	static IDAORuche daoRuche = Singleton.getInstance().getDaoRuche();

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




	//-------------------------------------Menus----------------------------------------------

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

		connected = daoUser.findByLoginAndPassword(login, password);

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
		System.out.println("1 - Afficher les récoltes");
		System.out.println("2 - Gérer les utilisateurs");
		System.out.println("3 - Evaluer les ruches");
		System.out.println("4 - Se déconnecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : afficherListeProduction();break;
		case 2 : menuGestionUtilisateurs();break;
		case 3 : menuEvaluation();break;
		case 4 : Singleton.getInstance().getEmf().close(); System.exit(0);
		}

		menuGestionnaire();
	}

	public static void menuGestionUtilisateurs() {
		System.out.println("Menu Gestion des utilisateurs");
		System.out.println("1 - Afficher la liste des récolteurs");
		System.out.println("2 - Ajouter un récolteur");
		System.out.println("3 - Afficher la liste des clients");
		System.out.println("4 - Ajouter un client");
		System.out.println("5 - Retour");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : afficherRecolteurs();break;
		case 2 : ajouterRecolteurs();break;
		case 3 : afficherClients();break;
		case 4 : ajouterClients();break;
		case 5 : menuGestionnaire();break;
		}

		menuGestionUtilisateurs();
	}

	private static void menuEvaluation() {
		// TODO Auto-generated method stub
		System.out.println("Evaluons les ruches");
		System.out.println("1 - Afficher les ruches");
		System.out.println("2 - Evaluer le nourrissage");
		System.out.println("3 - Evaluer les populations");
		System.out.println("4 - Retour");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : afficherRuches();break;
		case 2 : determinerLaFamine();break;
		case 3 : determinerDivision();break;
		case 4 : menuGestionnaire();break;
		}

		menuEvaluation();		
	}

	//Récolter, voir ses récoltes/ rentrée récoltes /  
	public static void menuRecolteur() {

		System.out.println("Menu Recolteur");
		System.out.println("1 - Afficher les récoltes");
		System.out.println("2 - Saisir une récolte");
		System.out.println("3 - Saisir une vulnerabilité");
		System.out.println("4 - Mes ruches");
		System.out.println("5 - To-do list ! ");
		System.out.println("6 - Se déconnecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : afficherListeProduction();break;
		case 2 : saisieRecolte();break;
		case 3 : saisieVulnerabilite();break;
		case 4 : mesRuches();break;
		case 5 : toDoList();break;
		case 6 : Singleton.getInstance().getEmf().close(); System.exit(0);
		}

		menuRecolteur();
	}


	//-------------------------------------Fonctions----------------------------------------------



	//--------Gestionnaires
	//lina
	private static void determinerLaFamine() {
		
		List<Ruche> ruches = daoRuche.findRucheByNoussirage();
		for(Ruche r : ruches) 
		{
			System.out.println(r);
		}
	}


	//julien
	private static void determinerDivision() {
		// TODO Auto-generated method stub
		//idem que famine mais pour les divisions
		
		
		
	}

	//angele
	private static void afficherRuches() {
		List<Ruche> ruches = daoRuche.findAll();
		if(ruches.isEmpty()) 
		{
			System.out.println("Pas de ruche");
		}
		for(Ruche r : ruches) 
		{
			System.out.println(r);
		}

	}

	//julien
	private static void afficherClients() {
		String type = "client";
		List<User> clients = daoUser.findAllByType(type);

		if(clients.isEmpty()) 
		{
			System.out.println("Pas de clients");
		}
		for(User c : clients) 
		{
			System.out.println(c);
		}

	}

	//chloe
	private static void afficherRecolteurs() {
		String type = "recolteur";
		List<User> users = daoUser.findAllByType(type);

		if(users.isEmpty()) 
		{
			System.out.println("Pas de récolteurs");
		}
		else {
			for(User tmp : users) 
			{
				System.out.println(tmp);
			}
		}

	}

	//angele
	private static void ajouterClients() {	
		System.out.println("Création d'un nouveau client :");
		String password = saisieString("Saisir password");
		String login = saisieString("Saisir login");
		String nom = saisieString("Saisir nom");
		String prenom = saisieString("Saisir prenom");
		
		String numero = saisieString("Saisir numéro");
		String voie = saisieString("Saisir voie");
		String ville = saisieString("Saisir ville");
		String cp = saisieString("Saisir cp");
		Adresse adresse = new Adresse(numero,voie,ville,cp);
		Client c = new Client(password,login,nom, prenom,adresse);
		daoUser.save(c);
		//System.out.println("Le client "+c+" a été ajouté en BDD");


	}

	//lina
	private static void ajouterRecolteurs() {


		System.out.println("Creation d'un nouveau récolteur :");
		String login = saisieString("Saisir login");
		String password = saisieString("Saisir mot de passe");		
		
		
		
		if (daoUser.findByLoginAndPassword(login, password)!=null) {
			
			System.out.println("Ce récolteur existe déjà !");
			
		} else {
			
		Recolteur r = new Recolteur(login, password);
		daoUser.save(r);
		System.out.println("Le récolteur "+r+" a ete ajoute en bdd");
		}

		menuGestionnaire();
	}




	//--------Communs
	//lina OK
	private static void afficherListeProduction() {
		
		List<Production> productions =  daoProduction.findAll();
		if(productions.isEmpty()) 
		{
			System.out.println("Vous n'avez aucune récolte en données");
		}

		for(Production r : productions) 
		{
			System.out.println("Type de récolte : "+ r.getProduit()+" \t Quantité : "+r.getQuantite()+"kg \t ID de la ruche : "+ r.getRuche().getId() +"\t Récolteur : "+r.getRecolteur().getLogin()+ "\t Année de récolte : "+r.getAnnee().getYear());
		}

	}





	//--------Récolteurs

	//chloe
	private static void mesRuches() {
		
		List<Ruche> ruches = daoRuche.findRucheByRecolteur(connected.getId());
		
		for (Ruche r : ruches) {
			System.out.println(r);
		}
	}

	//chloe
	private static void toDoList() {
		// TODO Auto-generated method stub

	}

	//angele
	private static void saisieVulnerabilite() {
		int idV = saisieInt("Saisir l'id de la ruche vulnérable");
		Ruche r = daoRuche.findById(idV);
		String choixVulnerabilite = saisieString("Saisir le nom de la vulnérabilite : Parasites, Pesticides, Predateurs, Loques");
	
		Vulnerabilite v = Vulnerabilite.valueOf(choixVulnerabilite);

		daoRuche.save(r);

	}

	//julien
	private static void saisieRecolte() {
		
//		Recolteur r = (Recolteur)connected;
//		
//		int idRecolteur = r.getId();
//		
//		Production p = DAOProduction.findById(id);
//		
	}









	//-------------------------------------MAIN----------------------------------------------

	public static void main(String[] args) {
		Recolteur r1 = new Recolteur("test", "test");
		Ruche ruche = new Ruche(5, false, r1);
		Production p1 = new Production(5.8, ruche, Produit.Miel, r1);
		
		determinerLaFamine();		
		

		menuPrincipal();
		
	//	afficherRecolteurs() ;
	//	Singleton.getInstance().getEmf().close();
	/*	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projet-Ruche");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(r1);
		em.persist(ruche);
		em.persist(p1);



		em.getTransaction().commit();

		em.close();
		emf.close();
		ajouterRecolteurs();*/
		
		
		
	}
}
