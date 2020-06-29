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
	private String passwordHash;

	public User(UserDto user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.passwordHash = user.getPasswordHash();
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", login='" + login + '\'' + ", passwordHash='" + passwordHash + '\'' + '}';
	}
}