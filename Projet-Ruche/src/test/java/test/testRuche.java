package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
	
	public static void menuGestionnaire() {
		
	}
	
	public static void menuRecolteur() {
		
	}
	
	public static void Recolte() {
		System.out.println("Votre Récolte:");
		
	}
	
	
	public static void main(String[] args) {
		
		Recolteur r1 = new Recolteur("rec1", "rec1");
		Ruche ruche1 = new Ruche(2,false, r1);
		
		
		String typeProduit= saisieString("Saisir le type de récolte : "+Arrays.toString(Produit.values()));
		Produit choixProduit = Produit.valueOf(typeProduit);
		
		double kg = saisieDouble("Saisir la quantité en kg : ");
		
		Production.donneeRecolte(kg, choixProduit);
		
		
		Production p1 = new Production(kg, date, ruche1, choixProduit, r1);
		
		System.out.println(p1.getQuantite());
		
	//	List<Production> = new ArrayList();
		List<Production> listeProd= new ArrayList();
		listeProd.add(p1);
				
		ruche1.setProductions(listeProd);
		
		
		System.out.println(ruche1.getProductions());
		
	}

	
}
