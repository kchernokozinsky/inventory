package inventory.orm.entity;

import inventory.shared.Dto.UserDto;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String passwordCache;

	public User(UserDto user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.passwordCache = user.getPasswordCache();
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswordCache() {
		return passwordCache;
	}

	public void setPasswordCache(String passwordCache) {
		this.passwordCache = passwordCache;
	}

	@Override
	public String toString() {
		return "inventory.orm.entity.User{" + "id=" + id + ", login='" + login + '}';
	}
}