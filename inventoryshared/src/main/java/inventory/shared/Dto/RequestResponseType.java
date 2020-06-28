package inventory.shared.Dto;

public enum RequestResponseType {
	// [] returns with id so it`s not necessary to use something by name
	// (but if it will be useful)
	FIND_GROUPS(String.class, GroupDto[].class),
	FIND_GOODS(String.class, GoodsDto[].class),
	GET_ALL_GOODS(null, GoodsDto[].class),
	GET_ALL_GROUPS(null,GroupDto[].class),
	AUTH(AuthDto.class, null),
	ADD_GROUP(GroupDto.class, GoodsDto[].class),
	ADD_USER(UserDto.class, null),
	ADD_GOODS(GoodsDto.class, GoodsDto[].class),
	ADD_GOODS_BY_GROUP_NAME(AddGoodsByGroupNameDto.class, GoodsDto[].class),
	REMOVE_GROUP(GroupDto.class, GroupDto[].class),
	REMOVE_GOODS(GoodsDto.class, GoodsDto[].class),
	CHANGE_GOODS_QUANTITY(ChangeGoodsQuantityDto.class, null),
	FIND_GROUP(String.class, GroupDto.class),
	FIND_GOOD(String.class, GoodsDto.class),
	// refresh and access token - response
	// refresh token - request
	REFRESH_JWT(null, null);

	private Class<?> requestKlass;
	private Class<?> responseKlass;

	public Class<?> getRequestKlass() {
		return requestKlass;
	}

	public Class<?> getResponseKlass() {
		return responseKlass;
	}

	RequestResponseType(Class<?> requestKlass, Class<?> responseKlass) {
		this.requestKlass = requestKlass;
		this.responseKlass = responseKlass;
	}

}


