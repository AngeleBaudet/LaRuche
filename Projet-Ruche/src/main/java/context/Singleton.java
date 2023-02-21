package context;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Pour faire une migration : design pattern factory : fournit une fabrique (interface) pour faire la transition entre l'IDAO et le JDBC
//On prend l'IDAO intermédiaire spécifique à la classe qui nous interesse pour les modifs.
//


public class Singleton {

	/*private IDAODemande daoDemande = new DAODemande();
	private IDAODictateur daoDictateur = new DAODictateur();
	private IDAOEnfant daoEnfant = new DAOEnfant();
	private IDAOJouet daoJouet = new DAOJouet();
	private IDAOEsclave daoEsclave = new DAOEsclave();
	private IDAOTraineau daoTraineau = new DAOTraineau();*/
	private static Singleton instance;
	
	
	private EntityManagerFactory emf= Persistence.createEntityManagerFactory("demoJPA");
	
	
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



	
	
	
	
}
