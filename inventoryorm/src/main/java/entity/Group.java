package entity;

import DTO.GroupDTO;

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

    public Group(GroupDTO group)
    {
        this.name = group.getName();
        this.id = group.getId();
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
        return "entity.GroupDTO{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
