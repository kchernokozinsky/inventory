package inventory.shared.Dto;

public class FindGoodsInGroupByNameLike {
    private GoodsDto goodsDto;
    private String name;

    public GoodsDto getGoodsDto() {
        return goodsDto;
    }

    public void setGoodsDto(GoodsDto goodsDto) {
        this.goodsDto = goodsDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
