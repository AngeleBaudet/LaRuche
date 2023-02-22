package dao;

import java.util.List;

import model.User;

public interface IDAOUser extends IDAO<User,Integer> {
	
	public User findByLoginAndPassword(String login,String password);

	public List<User> findAllByType(String type);
}
