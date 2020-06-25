package inventory.orm.entity;

import inventory.shared.Dto.GoodsDto;

import javax.persistence.*;

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

	public Goods(GoodsDto goods) {
		this.id = goods.getId();
		this.name = goods.getName();
		this.number = goods.getNumber();
		this.groupId = goods.getGroupId();
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

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "inventory.orm.entity.Goods{" + "id=" + id + ", name='" + name + '\'' + ", number=" + number + '}';
	}
}
