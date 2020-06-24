package entity;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String passwordCache;

    public User(String login, String password) {
//        String pw = DigestUtils.sha256Hex(password);
        this.login = login;
//        this.password = MD5
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordCache() {
        return passwordCache;
    }

    @Override
    public String toString() {
        return "entity.User{" +
                "id=" + id +
                ", login='" + login +
                '}';
    }
}