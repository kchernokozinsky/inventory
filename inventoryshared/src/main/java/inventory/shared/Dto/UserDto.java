package inventory.shared.Dto;

import org.mindrot.jbcrypt.BCrypt;

public class UserDto {

	private int id;

	private String login;

	private String passwordCache;

	public UserDto(String login, String password) {
		this.login = login;
		this.passwordCache = BCrypt.hashpw(login + ':' + password, BCrypt.gensalt());
	}

	public UserDto(int id, String login, String passwordCache) {
		this.id = id;
		this.login = login;
		this.passwordCache = passwordCache;
	}

//    public UserDto() {}

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
		return "inventory.orm.entity.UserDto{" + "id=" + id + ", login='" + login + '}';
	}
}