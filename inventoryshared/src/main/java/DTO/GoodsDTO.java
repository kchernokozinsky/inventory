package DTO;

public class GoodsDTO {

    private int id;

    private String name;

    private int number;

    private int groupId;

//    public GoodsDTO(String name, int number, String groupName) {
//        this.name = name;
//        this.number = number;
//        this.groupId = GroupService.getGroupService().findGroupByName(groupName).getId();
//    }

    public GoodsDTO(String name, int number, int groupId) {
        this.name = name;
        this.number = number;
        this.groupId = groupId;
    }

    public GoodsDTO() {}

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

    @Override
    public String toString() {
        return "entity.GoodsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
