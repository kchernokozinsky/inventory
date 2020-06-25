package entity;
import DTO.GroupDTO;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public Group(GroupDTO group) {
        this.id = group.getId();
        this.name = group.getName();
    }

    public Group() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "entity.Group{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
