package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import context.Singleton;
import dao.*;
import model.*;

public class App {
	static LocalDate date = LocalDate.now();
	static User connected = null;
	static IDAOUser daoUser = Singleton.getInstance().getDaoUser();


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
		case 1 : afficherRecoltes();break;
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
		case 1 : afficherRecoltes();break;
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
		// TODO Auto-generated method stub
		//utiliser la fonction nourrissage pour afficher si y'a besoin 
		//affecter un récolteur si besoin 
		//a voir si besoin de rediviser en 2 fonctions 

	}

	//julien
	private static void determinerDivision() {
		// TODO Auto-generated method stub
		//idem que famine mais pour les divisions
		
		
		
	}

	//angele
	private static void afficherRuches() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	//lina
	private static void ajouterRecolteurs() {
		// TODO Auto-generated method stub

	}




	//--------Communs
	//lina
	private static void afficherRecoltes() {
		// TODO Auto-generated method stub

	}





	//--------Récolteurs

	//chloe
	private static void mesRuches() {
		// TODO Auto-generated method stub
		System.out.println();
	}

	//chloe
	private static void toDoList() {
		// TODO Auto-generated method stub

	}

	//angele
	private static void saisieVulnerabilite() {
		// TODO Auto-generated method stub

	}

	//julien
	private static void saisieRecolte() {
		
		Recolteur r = (Recolteur)connected;
		
		int idRecolteur = r.getId();
		
		Production p = DAOProduction.findById(id);
	}









	//-------------------------------------MAIN----------------------------------------------

	public static void main(String[] args) {

	//	afficherRecolteurs() ;
	//	Singleton.getInstance().getEmf().close();

	}
}
