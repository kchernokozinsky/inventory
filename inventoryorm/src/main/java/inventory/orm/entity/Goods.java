package inventory.orm.entity;

import inventory.shared.Dto.GoodsDto;
import javax.persistence.*;
import javax.persistence.Entity;

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

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;

	public Goods(GoodsDto goods) {
		this.id = goods.getId();
		this.name = goods.getName();
		this.number = goods.getNumber();
		this.group = new Group(goods.getGroup());
	}

	public Goods() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "inventory.orm.entity.Goods{" + "id=" + id + ", name='" + name + '\'' + ", number=" + number + '}';
	}
}
