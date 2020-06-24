package entity;
import javax.persistence.*;
import services.GroupService;

@Entity
@Table(name="goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private int number;

    @Column(name = "grouo_id")
    private int groupId;

    public Goods(String name, int number, String groupName) {
        this.name = name;
        this.number = number;
        this.groupId = GroupService.getGroupService().findGroupByName(groupName).getId();
    }

    public Goods(String name, int number, int groupId) {
        this.name = name;
        this.number = number;
        this.groupId = groupId;
    }

    public Goods() {}

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "entity.Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
