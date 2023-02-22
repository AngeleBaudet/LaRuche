package test;

import java.time.LocalDate;
import java.util.Scanner;

import context.Singleton;
import dao.IDAOUser;
import model.Gestionnaire;
import model.Recolteur;
import model.User;

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
		System.out.println("2 - Afficher les récolteurs");
		System.out.println("3 - Afficher les clients");
		System.out.println("4 - Evaluer les ruches");
		System.out.println("5 - Se déconnecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : afficherRecoltes();break;
		case 2 : afficherRecolteurs();break;
		case 3 : afficherClients();break;
		case 4 : menuEvaluation();break;
		case 5 : Singleton.getInstance().getEmf().close(); System.exit(0);
		}
		
		menuGestionnaire();
	}

	private static void menuEvaluation() {
		// TODO Auto-generated method stub
		System.out.println("Evaluons les ruches");
		System.out.println("1 - Afficher les ruches");
		System.out.println("2 - Evaluer le nourrissage");
		System.out.println("3 - Evaluer les populations");
		System.out.println("4 - Se déconnecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : afficherRuches();break;
		case 2 : determinerLaFamine();break;
		case 3 : determinerDivision();break;
		case 4 : Singleton.getInstance().getEmf().close(); System.exit(0);
		}
		
	}

	//Récolter, voir ses récoltes/ rentrée récoltes /  
	public static void menuRecolteur() {

		System.out.println("Menu Recolteur");
		System.out.println("1 - Afficher les récoltes");
		System.out.println("2 - Saisir une récolte");
		System.out.println("3 - Mes ruches");
		System.out.println("4 - To-do list ! ");
		System.out.println("5 - Se déconnecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : afficherRecoltes();break;
		case 2 : afficherRecolteurs();break;
		case 3 : afficherClients();break;
		case 4 : mesRuches();break;
		case 5 : toDoList();break;
		case 6 : Singleton.getInstance().getEmf().close(); System.exit(0);
		}
		
		menuRecolteur();
	}

	
	
	
	
	
	
	
	
	//-------------------------------------Fonctions----------------------------------------------
	
	//--------gestionnaires
	private static void determinerLaFamine() {
		// TODO Auto-generated method stub
		//utiliser la fonction nourrissage pour afficher si y'a besoin 
		//affecter un récolteur si besoin 
		//a voir si besoin de rediviser en 2 fonctions 

	}
	
	private static void determinerDivision() {
		// TODO Auto-generated method stub
		//idem que famine mais pour les divisions
	}
	
	private static void afficherRuches() {
		// TODO Auto-generated method stub
		
	}

	private static void afficherClients() {
		// TODO Auto-generated method stub
		
	}

	private static void afficherRecolteurs() {
		// TODO Auto-generated method stub
		
	}

	
	//--------communs
	private static void afficherRecoltes() {
		// TODO Auto-generated method stub
		
	}

	
	//--------recolteurs
	private static void mesRuches() {
		// TODO Auto-generated method stub
		System.out.println();
	}
	
	private static void toDoList() {
		// TODO Auto-generated method stub
		
	}

	public static void Recolte() {
		System.out.println("Votre Récolte:");

	}

	
	
	
	
	
	
	
	
	
	
	//-------------------------------------MAIN----------------------------------------------

	public static void main(String[] args) {


	}
}
