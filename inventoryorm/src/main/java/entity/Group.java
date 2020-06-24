package entity;

import javax.persistence.*;

@Entity
@Table(name="groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public Group(String name) {
        this.name = name;
    }

    public Group() {}

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "entity.Group{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
