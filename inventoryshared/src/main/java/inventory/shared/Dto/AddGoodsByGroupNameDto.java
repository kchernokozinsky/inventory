package inventory.shared.Dto;

public class AddGoodsByGroupNameDto {
    private GoodsDto goodsDto;
    private String groupName;

    public AddGoodsByGroupNameDto(GoodsDto goodsDto, String groupName) {
        this.goodsDto = goodsDto;
        this.groupName = groupName;
    }

    public GoodsDto getGoodsDto() {
        return goodsDto;
    }

    public void setGoodsDto(GoodsDto goodsDto) {
        this.goodsDto = goodsDto;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
