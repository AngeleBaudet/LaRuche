package dao;

import java.util.List;

import javax.persistence.EntityManager;

import context.Singleton;
import model.Ruche;
import javax.persistence.EntityManager;


public class DAORuche implements IDAORuche {

	@Override
	public Ruche findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Ruche ruche = em.find(Ruche.class,id);
		em.close();
		return ruche;
	}

	@Override
	public List<Ruche> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		
		List<Ruche> ruches = em.createQuery("from Patient").getResultList();
		em.close();
		return ruches;
	}

	@Override
	public Ruche save(Ruche ruche) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		ruche = em.merge(ruche);
		em.getTransaction().commit();
		em.close();
		return ruche;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Ruche ruche = em.find(Ruche.class,id);
		em.getTransaction().begin();
		em.remove(ruche);
		em.getTransaction().commit();
		em.close();	
	}


	public List<Ruche> findRucheByRecolteur(Integer id) {
		//SELECT * FROM `ruche` join user on recolteur_id=user.id having recolteur_id=1;
		
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		
		List<Ruche> ruches = em.createQuery("SELECT r FROM Ruche r JOIN r.recolteur u WHERE u.id = :id").setParameter("id", id).getResultList();
		em.close();
		
		return ruches;
	}

}
