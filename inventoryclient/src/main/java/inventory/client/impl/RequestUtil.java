package inventory.client.impl;

import inventory.shared.Dto.*;
import java.util.NoSuchElementException;

public class RequestUtil {



	private RequestDto changeGoodsQuantity(ChangeGoodsQuantityDto changeGoodsQuantityDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(changeGoodsQuantityDto);
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.CHANGE_GOODS_QUANTITY);
		return requestDto;
	}

	private RequestDto removeGoods(GoodsDto goodsDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(goodsDto);
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.REMOVE_GOODS);
		return requestDto;
	}

	private RequestDto removeGroup(GroupDto groupDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(groupDto);
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.REMOVE_GROUP);
		return requestDto;
	}

	public RequestDto addUser(UserDto userDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(userDto);
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.ADD_USER);
		return requestDto;
	}

	private RequestDto addGoods(GoodsDto goodsDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setData(goodsDto);
		requestDto.setRequestType(RequestResponseType.ADD_GOODS);
		return requestDto;

	}

	private RequestDto addGoodsByGroupName(AddGoodsByGroupNameDto addGoodsByGroupNameDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setData(addGoodsByGroupNameDto);
		requestDto.setRequestType(RequestResponseType.ADD_GOODS_BY_GROUP_NAME);
		return requestDto;
	}

	private RequestDto addGroup(GroupDto groupDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setData(groupDto);
		requestDto.setRequestType(RequestResponseType.ADD_GROUP);
		return requestDto;
	}

	private RequestDto getAllGroups(String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.GET_ALL_GROUPS);
		return requestDto;
	}

	private RequestDto getAllGoods(String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.GET_ALL_GOODS);
		return requestDto;
	}

	private RequestDto findGoods(String substring, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setData(substring);
		requestDto.setRequestType(RequestResponseType.FIND_GOODS);
		return requestDto;

	}

	private RequestDto findGroups(String substring, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setData(substring);
		requestDto.setRequestType(RequestResponseType.FIND_GROUPS);
		return requestDto;
	}




	private RequestDto authorisation(AuthDto authDto) {
		RequestDto requestDto = new RequestDto();
		requestDto.setRequestType(RequestResponseType.AUTH);
		requestDto.setData(authDto);
		return requestDto;
	}
}
