package fr.ruche.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ruche.model.Achat;
import fr.ruche.model.Production;

public interface IDAOAchat extends JpaRepository<Achat, Integer> {
	

}
