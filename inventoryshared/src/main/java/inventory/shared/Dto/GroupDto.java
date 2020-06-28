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

    public GroupDto() {}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}
}
