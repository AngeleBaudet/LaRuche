package test;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.ruche.config.AppConfig;
import fr.ruche.dao.IDAOProduction;
import fr.ruche.dao.IDAORuche;
import fr.ruche.dao.IDAOUser;
import fr.ruche.exception.RecolteNotFoundException;
import fr.ruche.exception.RucheNotFoundException;
import fr.ruche.model.Adresse;
import fr.ruche.model.Client;
import fr.ruche.model.Gestionnaire;
import fr.ruche.model.Production;
import fr.ruche.model.Produit;
import fr.ruche.model.Recolteur;
import fr.ruche.model.Ruche;
import fr.ruche.model.User;
import fr.ruche.model.Vulnerabilite;


public class App {
	static LocalDate date = LocalDate.now();
	static User connected = null;

	static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	static IDAOUser daoUser = context.getBean(IDAOUser.class);
	static IDAOProduction daoProduction = context.getBean(IDAOProduction.class);
	static IDAORuche daoRuche = context.getBean(IDAORuche.class);

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


	//Celui a acc??s les fonctionnalit??s
	public static void menuGestionnaire() {
		System.out.println("Menu Gestionnaire");
		System.out.println("1 - Afficher les r??coltes");
		System.out.println("2 - G??rer les utilisateurs");
		System.out.println("3 - Evaluer les ruches");
		System.out.println("4 - Se d??connecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : afficherListeProduction();break;
		case 2 : menuGestionUtilisateurs();break;
		case 3 : menuEvaluation();break;
		case 4 : System.exit(0);
		}

		menuGestionnaire();
	}

	public static void menuGestionUtilisateurs() {
		System.out.println("Menu Gestion des utilisateurs");
		System.out.println("1 - Afficher la liste des r??colteurs");
		System.out.println("2 - Ajouter un r??colteur");
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

	//R??colter, voir ses r??coltes/ rentr??e r??coltes /  
	public static void menuRecolteur() {

		System.out.println("Menu Recolteur");
		System.out.println("1 - Afficher les r??coltes");
		System.out.println("2 - Saisir une r??colte");
		System.out.println("3 - Saisir une vulnerabilit??");
		System.out.println("4 - Mes ruches");
		System.out.println("5 - To-do list ! ");
		System.out.println("6 - Se d??connecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : afficherListeProduction();break;
		case 2 : saisieRecolte();break;
		case 3 : saisieVulnerabilite();break;
		case 4 : mesRuches();break;
		case 5 : toDoList();break;
		case 6 : System.exit(0);
		}

		menuRecolteur();
	}


	//-------------------------------------Fonctions----------------------------------------------



	//--------Gestionnaires
	//lina
	private static void determinerLaFamine() {

		List<Ruche> ruches = daoRuche.findRucheByNourissage();
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

		List<Client> clients = daoUser.findAllByClient();

		if(clients.isEmpty()) 
		{
			System.out.println("Pas de clients");
		}else {
			for(User c : clients) 
			{
				System.out.println(c);
			}
		}
	}

	//chloe
	private static void afficherRecolteurs() {

		List<Recolteur> recolteurs = daoUser.findAllByRecolteur();

		if(recolteurs.isEmpty()) 
		{
			System.out.println("Pas de r??colteurs");
		}
		else {
			for(User tmp : recolteurs) 
			{
				System.out.println(tmp);
			}
		}

	}

	//angele
	private static void ajouterClients() {	
		System.out.println("Cr??ation d'un nouveau client :");
		String password = saisieString("Saisir password");
		String login = saisieString("Saisir login");
		String nom = saisieString("Saisir nom");
		String prenom = saisieString("Saisir prenom");

		String numero = saisieString("Saisir num??ro");
		String voie = saisieString("Saisir voie");
		String ville = saisieString("Saisir ville");
		String cp = saisieString("Saisir cp");
		Adresse adresse = new Adresse(numero,voie,ville,cp);
		Client c = new Client(password,login,nom, prenom,adresse);
		daoUser.save(c);
		//System.out.println("Le client "+c+" a ??t?? ajout?? en BDD");


	}

	//lina
	private static void ajouterRecolteurs() {


		System.out.println("Creation d'un nouveau r??colteur :");
		String login = saisieString("Saisir login");
		String password = saisieString("Saisir mot de passe");		



		if (daoUser.findByLoginAndPassword(login, password)!=null) {

			System.out.println("Ce r??colteur existe d??j?? !");

		} else {

			Recolteur r = new Recolteur(login, password);
			daoUser.save(r);
			System.out.println("Le r??colteur "+r+" a ete ajoute en bdd");
		}

		menuGestionnaire();
	}




	//--------Communs
	//lina OK
	private static void afficherListeProduction() {

		Integer idRecolteur = connected.getId();


		List<Production> productions =  daoProduction.findProductionByRecolteur(idRecolteur);
		if(productions.isEmpty()) 
		{
			System.out.println("Vous n'avez aucune r??colte en donn??es");
		}

		for(Production r : productions) 
		{
			System.out.println("Type de r??colte : "+ r.getProduit()+" \t Quantit?? : "+r.getQuantite()+"kg \t ID de la ruche : "+ r.getRuche().getId() +"\t R??colteur : "+r.getRecolteur().getLogin()+ "\t Ann??e de r??colte : "+r.getAnnee().getYear());
		}

	}





	//--------R??colteurs

	//chloe
	private static void mesRuches() {

		List<Ruche> ruches = daoRuche.findRucheByRecolteur(connected.getId());

		for (Ruche r : ruches) {
			System.out.println(r);
		}
	}

	//chloe
	private static void toDoList() {
		List<Ruche> ruches = daoRuche.findRucheByNourissage();

		if (ruches.isEmpty()) {
			System.out.println("\nPas de ruches ?? nourrir");
		}
		else {
			System.out.println("\nLes ruches suivantes doivent ??tre nourries :");
			determinerLaFamine();
		}
		
//		ruches = daoRuche.findRucheByDivision();
//		
//		if (ruches.isEmpty()) {
//			System.out.println("\nPas de ruches ?? diviser");
//		}
//		else {
//			System.out.println("\nLes ruches suivantes doivent ??tre divis??es :");
//			determinerDivision();
//		}
		
		
		menuRecolteur();
	}

	//angele
	private static void saisieVulnerabilite() {

		Integer idRecolteur = connected.getId();

		int idV = saisieInt("Saisir l'id de la ruche vuln??rable");

		try {
			//orElseThrow : uniquement pour lancer une exeption, ici une exeption personalis??e dans RucheNotFound 
			// ::new pour r??cup??rer une construction de RucheNot Found
			//			Ruche r = daoRuche.findByIdAndRecolteurId(idV, idRecolteur).orElseThrow(() -> new RucheNotFoundException());
			Ruche r = daoRuche.findByIdAndRecolteurId(idV, idRecolteur).orElseThrow(RucheNotFoundException::new);


			String choixVulnerabilite = saisieString("Saisir le nom de la vuln??rabilite : Parasites, Pesticides, Predateurs, Loques");

			Vulnerabilite v = Vulnerabilite.valueOf(choixVulnerabilite);

			r.setVulnerabilite(v);

			daoRuche.save(r);
		}

		catch (RucheNotFoundException e) {
			System.out.println("L'identifiant de la ruche n'existe pas");
		}

		String recommence = saisieString("Voulez-vous saisir une nouvelle vuln??rabilit?? ? y/n");
		if(recommence.equalsIgnoreCase("y")) {

			saisieVulnerabilite();

		}else {

			menuRecolteur();
		}
	}


	//julien
	private static void saisieRecolte() {

		Recolteur recolteurRecolte = (Recolteur)connected;
		int idRecolteur = recolteurRecolte.getId();

		int idRuche = saisieInt("Saisir l'id de la ruche ?? r??colter");

		try {
			Ruche rucheRecolte = daoRuche.findByIdAndRecolteurId(idRuche,idRecolteur).orElseThrow(RecolteNotFoundException::new);

			Produit produitRecolte = Produit.valueOf(saisieString("Saisir type de produit r??colt??"));
			double quantiteRecolte = saisieDouble("Saisir la quantite recolt??e");

			Production p = new Production(quantiteRecolte, rucheRecolte, produitRecolte, recolteurRecolte);

			daoProduction.save(p);

		} catch (RecolteNotFoundException e) {
			System.out.println("L'identifiant de la ruche n'existe pas");

		}


		String recommence = saisieString("Voulez-vous saisir une nouvelle r??colte ? y/n");
		if(recommence.equalsIgnoreCase("y")) {

			saisieRecolte();

		}else {

			menuRecolteur();
		}
	}









	//-------------------------------------MAIN----------------------------------------------

	public static void main(String[] args) {
		//		Recolteur r1 = new Recolteur("test", "test");
		//		Ruche ruche = new Ruche(5, false, r1);
		//		Production p1 = new Production(5.8, ruche, Produit.Miel, r1);



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
