package context;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.DAOProduction;
import dao.DAORuche;
import dao.DAOUser;
import dao.IDAOProduction;
import dao.IDAORuche;
import dao.IDAOUser;

//Pour faire une migration : design pattern factory : fournit une fabrique (interface) pour faire la transition entre l'IDAO et le JDBC
//On prend l'IDAO intermédiaire spécifique à la classe qui nous interesse pour les modifs.
//


public class Singleton {

	private IDAOProduction daoProduction = new DAOProduction();
	private IDAORuche daoRuche = new DAORuche();
	private IDAOUser daoUser = new DAOUser();
	
	
	private static Singleton instance;
	
	
	private EntityManagerFactory emf= Persistence.createEntityManagerFactory("Projet-Ruche");
	
	
	private Singleton() {}


	public static Singleton getInstance() {
		if(instance==null) 
		{
			instance=new Singleton();
		}
		return instance;
	}


	public static void setInstance(Singleton instance) {
		Singleton.instance = instance;
	}


	public EntityManagerFactory getEmf() {
		return emf;
	}


	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}


	public IDAOProduction getDaoProduction() {
		return daoProduction;
	}


	public void setDaoProduction(IDAOProduction daoProduction) {
		this.daoProduction = daoProduction;
	}


	public IDAORuche getDaoRuche() {
		return daoRuche;
	}


	public void setDaoRuche(IDAORuche daoRuche) {
		this.daoRuche = daoRuche;
	}


	public IDAOUser getDaoUser() {
		return daoUser;
	}


	public void setDaoUser(IDAOUser daoUser) {
		this.daoUser = daoUser;
	}



	
	
	
	
}
