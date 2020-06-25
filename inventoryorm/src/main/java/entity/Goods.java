package entity;
import javax.persistence.*;
import javax.persistence.Entity;
import DTO.GoodsDTO;
import services.GroupService;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private int number;


    @Column(name = "group_id")
    private int groupId;

    public Goods(GoodsDTO goods) {
        this.id = goods.getId();
        this.name = goods.getName();
        this.number = goods.getNumber();
        this.groupId = goods.getGroupId();
    }

    public Goods() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
