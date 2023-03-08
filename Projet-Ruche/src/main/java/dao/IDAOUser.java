package dao;

import java.util.List;

import fr.ruche.model.Client;
import fr.ruche.model.Gestionnaire;
import fr.ruche.model.Recolteur;
import fr.ruche.model.User;

public interface IDAOUser extends IDAO<User,Integer> {
	
	public Recolteur findRecolteurById(Integer id);
	public Gestionnaire findGestionnaireById(Integer id);
	public List<Recolteur> findAllRecolteur();
	public List<Gestionnaire> findAllGestionnaire();
	public User findByLoginAndPassword(String login,String password);

	public List<User> findAllByType(String type);
}
