package inventory.shared.Dto;

public class GoodsDto {

	private int id;

	private String name;

	private int number;

	private GroupDto group;


	public GoodsDto(String name, int number, GroupDto group) {
		this.name = name;
		this.number = number;
		this.group = group;
	}

	public GoodsDto(int id, String name, int number, GroupDto group) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.group = group;
	}
	public GoodsDto(String name, int number, int groupId) {
		this.name = name;
		this.number = number;
		GroupDto groupDto = new GroupDto();
		groupDto.setId(groupId);
		this.group = groupDto;
	}

	public GoodsDto(int id, String name, int number, int groupId) {
		this.id = id;
		this.name = name;
		this.number = number;
		GroupDto groupDto = new GroupDto();
		groupDto.setId(groupId);
		this.group = groupDto;
	}

    public GoodsDto() {}

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

	public GroupDto getGroup() {
		return group;
	}

	public void setGroup(GroupDto group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "GoodsDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", number=" + number +
				", group=" + group +
				'}';
	}
}
