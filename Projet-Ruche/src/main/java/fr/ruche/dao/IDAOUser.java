package fr.ruche.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ruche.model.*;

public interface IDAOUser extends JpaRepository<User, Integer>{

	
	public Optional<Recolteur> findRecolteurById(Integer id);
	
	public Optional<Gestionnaire> findGestionnaireById(Integer id);
	
	public List<Recolteur> findAllByRecolteur();
	
	public List<Gestionnaire> findAllByGestionnaire();
	
	public User findByLoginAndPassword(String login,String password);



	
}
