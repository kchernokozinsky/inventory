package inventory.shared.Dto;

import inventory.shared.Dto.ChangeGoodsQuantityDto;
import inventory.shared.Dto.GoodsDto;
import inventory.shared.Dto.GroupDto;
import inventory.shared.Dto.UserDto;



public enum RequestResponseType {
	FIND_GROUPS(String.class, GroupDto[].class),
	FIND_GOODS(String.class, GoodsDto[].class),
	GET_ALL_GOODS(null, GoodsDto[].class),
	GET_ALL_GROUPS(null,GroupDto[].class),
	AUTH(UserDto.class, null), // to do
	ADD_GROUP(GroupDto.class, GoodsDto[].class),
	ADD_GOODS(GoodsDto.class, GoodsDto[].class),
	REMOVE_GROUP(GroupDto.class, GroupDto[].class),
	REMOVE_GOODS(GoodsDto.class, GoodsDto[].class),
	CHANGE_GOODS_QUANTITY(ChangeGoodsQuantityDto.class, null);

	private Class<?> requestKlass;
	private Class<?> responseKlass;

	RequestResponseType(Class<?> requestKlass, Class<?> responseKlass) {
		this.requestKlass = requestKlass;
		this.responseKlass = responseKlass;
	}

}




