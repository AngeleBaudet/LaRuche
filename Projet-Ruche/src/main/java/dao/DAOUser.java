package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import context.Singleton;
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
	

}
