package model;

public abstract class User {

	protected String password ; 
	protected String login; 
	protected static int id =0 ;
	
	
	public User(String password, String login) {
		this.password = password;
		this.login = login;
		this.id ++ ;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public int getId() {
		return id;
	}


	public void setId(int id_User) {
		this.id = id_User;
	}
	

	
}
