package inventory.shared.Dto;

import org.mindrot.jbcrypt.BCrypt;

public class UserDto {

	private int id;

	private String login;

	private String passwordHash;

	public UserDto(String login, String password) {
		this.login = login;
		this.passwordHash = BCrypt.hashpw(login + ':' + password, BCrypt.gensalt());
	}

	public UserDto(int id, String login, String passwordHash) {
		this.id = id;
		this.login = login;
		this.passwordHash = passwordHash;
	}

	public UserDto(AuthDto authDto) {
		this.login = authDto.getLogin();
		this.passwordHash = BCrypt.hashpw(login + ':' + authDto.getPassword(), BCrypt.gensalt());
	}

	public UserDto() {
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
		return "UserDto{" + "id=" + id + ", login='" + login + '\'' + ", passwordHash='" + passwordHash + '\'' + '}';
	}
}