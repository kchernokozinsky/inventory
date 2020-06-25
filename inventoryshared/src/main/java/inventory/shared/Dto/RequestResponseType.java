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
	AUTH(UserDto.class, null),
	ADD_GROUP(GroupDto.class, null),
	ADD_GOODS(GoodsDto.class, null),
	REMOVE_GROUP(GroupDto.class, null),
	REMOVE_GOODS(GoodsDto.class, null),
	CHANGE_GOODS_QUANTITY(ChangeGoodsQuantityDto.class, null);

	private Class<?> requestKlass;
	private Class<?> responseKlass;

	RequestResponseType(Class<?> requestKlass, Class<?> responseKlass) {
		this.requestKlass = requestKlass;
		this.responseKlass = responseKlass;
	}

}




