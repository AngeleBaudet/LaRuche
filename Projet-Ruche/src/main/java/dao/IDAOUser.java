package dao;

import model.User;

public interface IDAOUser extends IDAO<User,Integer> {
	
	public User findByLoginAndPassword(String login,String password);

}
