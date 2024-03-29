package fr.ruche.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ruche.model.*;

public interface IDAOUser extends JpaRepository<User, Integer>{

	
	public Optional<Recolteur> findRecolteurById(Integer id);
	
	public Optional<Gestionnaire> findGestionnaireById(Integer id);
	
	public Optional<Client> findClientById(Integer id);
	
	@Query("from Recolteur")
	public List<Recolteur> findAllByRecolteur();
	
	@Query("from Gestionnaire")
	public List<Gestionnaire> findAllByGestionnaire();
	
	@Query("from Client")
	public List<Client> findAllByClient();
	
	public Optional<User> findByLoginAndPassword(String login,String password);

	@Query("select c from Client c left join fetch c.achats where c.id = ?1")
	public Optional<Client> findByIdWithAchat(Integer id) ;

	public Optional<User> findByLogin(String login);

	
}
