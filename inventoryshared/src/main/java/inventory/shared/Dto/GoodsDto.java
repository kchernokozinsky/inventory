package inventory.shared.Dto;

public class GoodsDto {

	private int id;

	private String name;

	private int number;

	private int groupId;

	public GoodsDto(String name, int number, int groupId) {
		this.name = name;
		this.number = number;
		this.groupId = groupId;
	}

	public GoodsDto(int id, String name, int number, int groupId) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.groupId = groupId;
	}

	public GoodsDto() {
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
		return "GoodsDto{" + "id=" + id + ", name='" + name + '\'' + ", number=" + number + ", groupId=" + groupId +
				'}';
	}
}
