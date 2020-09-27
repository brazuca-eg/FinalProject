package ua.kharkov.repairagency.db.entity;

public class User extends Entity{

	private static final long serialVersionUID = 1L;
	

	private String login;
	private String password;
	private String surname;
	private int role_id;
	public User() {
		
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() { 
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", surname=" + surname + ", role_id=" + role_id
				+ "]";
	}
	
	
}
