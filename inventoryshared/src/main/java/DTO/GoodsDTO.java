package DTO;

public class GoodsDTO {

    private int id;

    private String name;

    private int number;

    private int groupId;

    public GoodsDTO(String name, int number, int groupId) {
        this.name = name;
        this.number = number;
        this.groupId = groupId;
    }

    public GoodsDTO(int id, String name, int number, int groupId) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.groupId = groupId;
    }

//    public GoodsDTO() {}

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
        return "entity.GoodsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
