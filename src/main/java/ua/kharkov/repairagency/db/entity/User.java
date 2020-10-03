package ua.kharkov.repairagency.db.entity;

import java.util.Objects;

public class User extends Entity{

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private String surname;
	private int role_id;
	private String name;
	private String email;
	public User() {
		
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "User{" +
				"login='" + login + '\'' +
				", password='" + password + '\'' +
				", surname='" + surname + '\'' +
				", role_id=" + role_id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return login.equals(user.login) &&
				password.equals(user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, password);
	}
}
