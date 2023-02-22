package test;

import java.time.LocalDate;
import java.util.Scanner;

import model.Gestionnaire;
import model.Recolteur;
import model.User;

public class App {
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
	
	
}
}
