package inventory.shared.Dto;

public class ChangeGoodsQuantityDto {
	private GoodsDto goodsDto;
	private Integer quantity;

	public ChangeGoodsQuantityDto(GoodsDto goodsDto, Integer quantity) {
		this.goodsDto = goodsDto;
		this.quantity = quantity;
	}

	public ChangeGoodsQuantityDto() {
	}

	public GoodsDto getGoodsDto() {
		return goodsDto;
	}

	public void setGoodsDto(GoodsDto goodsDto) {
		this.goodsDto = goodsDto;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ChangeGoodsQuantityDto{" + "goodsDto=" + goodsDto + ", quantity=" + quantity + '}';
	}
}
