package fr.ruche.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ruche.model.Production;

public interface IDAOProduction extends JpaRepository<Production, Integer> {
	
}
