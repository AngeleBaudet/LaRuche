package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import context.Singleton;
import model.Client;
import model.Gestionnaire;
import model.Recolteur;
import model.User;

public class DAOUser implements IDAOUser{
	
	@Override
	public User findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		User user = em.find(User.class,id);
		em.close();
		return user;
	}

	@Override
	public List<User> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		
		List<User> users = em.createQuery("from User").getResultList();
		em.close();
		return users;
	}

	@Override
	public User save(User user) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		user = em.merge(user);
		em.getTransaction().commit();
		em.close();
		return user;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		User user = em.find(User.class,id);
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
		em.close();	
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		User connected = null;
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		
		try 
		{
			Query q = em.createQuery("from User c where c.login=:login and c.password=:password");
			q.setParameter("login", login);
			q.setParameter("password", password);
			connected = (User) q.getSingleResult();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		em.close();
		return connected;
	}

	@Override
	public Recolteur findRecolteurById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Recolteur recolteur = em.find(Recolteur.class,id);
		em.close();
		return recolteur;
	}

	@Override
	public Gestionnaire findGestionnaireById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Gestionnaire gestionnaire = em.find(Gestionnaire.class,id);
		em.close();
		return gestionnaire;
	}

	@Override
	public List<Recolteur> findAllRecolteur() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Recolteur> recolteur = em.createQuery("from Recolteur").getResultList();
		em.close();
		return recolteur;
	}

	@Override
	public List<Gestionnaire> findAllGestionnaire() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		List<Gestionnaire> gestionnaire = em.createQuery("from Gestionnaire").getResultList();
		em.close();
		return gestionnaire;
	}
	

	

}
