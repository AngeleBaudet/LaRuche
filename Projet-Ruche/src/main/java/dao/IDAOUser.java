package dao;

import java.util.List;

import model.Client;
import model.Gestionnaire;
import model.Recolteur;
import model.User;

public interface IDAOUser extends IDAO<User,Integer> {
	
	public Recolteur findRecolteurById(Integer id);
	public Gestionnaire findGestionnaireById(Integer id);
	public List<Recolteur> findAllRecolteur();
	public List<Gestionnaire> findAllGestionnaire();
	public User findByLoginAndPassword(String login,String password);

}
