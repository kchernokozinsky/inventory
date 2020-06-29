package inventory.shared.Dto;

public class GroupDto {
	private int id;

	private String name;

	public GroupDto(String name) {
		this.name = name;
	}

	public GroupDto(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public GroupDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return name;
	}
}
