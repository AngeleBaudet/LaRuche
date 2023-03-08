package dao;

import java.util.List;

import javax.persistence.EntityManager;

import context.Singleton;
import fr.ruche.model.Production;

public class DAOProduction implements IDAOProduction{

	@Override
	public Production findById(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Production production = em.find(Production.class,id);
		em.close();
		return production;
	}

	@Override
	public List<Production> findAll() {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();

		List<Production> productions = em.createQuery("from Production").getResultList();
		em.close();
		return productions;
	}

	@Override
	public Production save(Production production) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		production = em.merge(production);
		em.getTransaction().commit();
		em.close();
		return production;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = Singleton.getInstance().getEmf().createEntityManager();
		Production production = em.find(Production.class,id);
		em.getTransaction().begin();
		em.remove(production);
		em.getTransaction().commit();
		em.close();	

	}

}
