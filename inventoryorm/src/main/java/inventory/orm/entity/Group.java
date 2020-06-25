package inventory.orm.entity;

import inventory.shared.Dto.GroupDto;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	public Group(GroupDto group) {
		this.id = group.getId();
		this.name = group.getName();
	}

	public Group() {
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

	@Override
	public String toString() {
		return "inventory.orm.entity.Group{" + "id=" + id + ", name='" + name + '}';
	}
}
