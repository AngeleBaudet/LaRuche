package fr.ruche.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ruche.model.Gestionnaire;
import fr.ruche.model.Recolteur;
import fr.ruche.model.User;

public interface IDAOUser extends JpaRepository<User, Integer>{

	
	public Optional<Recolteur> findRecolteurById(Integer id);
	
	public Optional<Gestionnaire> findGestionnaireById(Integer id);
	
	@Query("from Recolteur")
	public List<Recolteur> findAllByRecolteur();
	
	@Query("from Gestionnaire")
	public List<Gestionnaire> findAllByGestionnaire();
	
	public User findByLoginAndPassword(String login,String password);



	
}
