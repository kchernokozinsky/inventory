package inventory.client.impl;

import inventory.shared.Dto.*;
import inventory.shared.impl.JsonConverter;
import inventory.shared.impl.Packet;

import java.util.NoSuchElementException;

public class RequestUtil {

	public static ResponseDto packetToResponse(Packet packet){
		ResponseDto responseDto =
				(ResponseDto) JsonConverter.jsonToObj(packet.getbMsq().getMessage(), ResponseDto.class);
		return responseDto;
	}

	public static RequestDto changeGoodsQuantity(ChangeGoodsQuantityDto changeGoodsQuantityDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(changeGoodsQuantityDto);
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.CHANGE_GOODS_QUANTITY);
		return requestDto;
	}

	public static RequestDto removeGoods(GoodsDto goodsDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(goodsDto);
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.REMOVE_GOODS);
		return requestDto;
	}

	public static RequestDto removeGroup(GroupDto groupDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(groupDto);
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.REMOVE_GROUP);
		return requestDto;
	}

	public static RequestDto addUser(UserDto userDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(userDto);
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.ADD_USER);
		return requestDto;
	}

	public static RequestDto addGoods(GoodsDto goodsDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setData(goodsDto);
		requestDto.setRequestType(RequestResponseType.ADD_GOODS);
		return requestDto;

	}

	public static RequestDto addGoodsByGroupName(AddGoodsByGroupNameDto addGoodsByGroupNameDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setData(addGoodsByGroupNameDto);
		requestDto.setRequestType(RequestResponseType.ADD_GOODS_BY_GROUP_NAME);
		return requestDto;
	}

	public static RequestDto addGroup(GroupDto groupDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setData(groupDto);
		requestDto.setRequestType(RequestResponseType.ADD_GROUP);
		return requestDto;
	}

	public static RequestDto getAllGroups(String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.GET_ALL_GROUPS);
		return requestDto;
	}

	public static RequestDto getAllGoods(String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setRequestType(RequestResponseType.GET_ALL_GOODS);
		return requestDto;
	}

	public static RequestDto findGoods(String substring, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setData(substring);
		requestDto.setRequestType(RequestResponseType.FIND_GOODS);
		return requestDto;

	}

	public static RequestDto findGroups(String substring, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwt(jwt);
		requestDto.setData(substring);
		requestDto.setRequestType(RequestResponseType.FIND_GROUPS);
		return requestDto;
	}




	public static RequestDto authorisation(AuthDto authDto) {
		RequestDto requestDto = new RequestDto();
		requestDto.setRequestType(RequestResponseType.AUTH);
		requestDto.setData(authDto);
		return requestDto;
	}
}
