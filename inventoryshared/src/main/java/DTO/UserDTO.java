package DTO;

public class UserDTO {

    private int id;

    private String login;

    private String passwordCache;

    public UserDTO(String login, String password) {
//        String pw = DigestUtils.sha256Hex(password);
        this.login = login;
//        this.password = MD5
    }

    public UserDTO() {
    }

    public String getLogin() {
        return login;
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