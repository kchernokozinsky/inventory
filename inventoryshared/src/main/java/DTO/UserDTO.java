package DTO;

import org.mindrot.jbcrypt.BCrypt;

public class UserDTO {

    private int id;

    private String login;

    private String passwordCache;

    public UserDTO(String login, String password) {
        this.login = login;
        this.passwordCache = BCrypt.hashpw(login+':'+password, BCrypt.gensalt());
    }

    public UserDTO(int id, String login, String passwordCache) {
        this.id = id;
        this.login = login;
        this.passwordCache = passwordCache;
    }

//    public UserDTO() {}

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPasswordCache(String passwordCache) {
        this.passwordCache = passwordCache;
    }

    public String getPasswordCache() {
        return passwordCache;
    }

    @Override
    public String toString() {
        return "entity.UserDTO{" +
                "id=" + id +
                ", login='" + login +
                '}';
    }
}