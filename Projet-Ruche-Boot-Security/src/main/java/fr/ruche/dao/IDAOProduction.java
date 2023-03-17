package fr.ruche.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ruche.model.Production;
import fr.ruche.model.Ruche;

public interface IDAOProduction extends JpaRepository<Production, Integer> {
	
	@Query("SELECT p FROM Production p JOIN p.recolteur u WHERE u.id = ?1")
	public List<Production> findProductionByRecolteur(Integer id);
}
