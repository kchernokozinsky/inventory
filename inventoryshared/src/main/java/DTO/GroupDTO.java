package DTO;

public class GroupDTO {
    private int id;

    private String name;

    public GroupDTO(String name) {
        this.name = name;
    }

    public GroupDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

//    public GroupDTO() {}

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
