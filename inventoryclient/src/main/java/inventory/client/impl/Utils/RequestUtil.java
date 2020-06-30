package inventory.client.impl.Utils;

import inventory.shared.Dto.*;
import inventory.shared.impl.JsonConverter;
import inventory.shared.impl.Packet;

public class RequestUtil {

	public static ResponseDto packetToResponse(Packet packet) {
		ResponseDto responseDto =
				(ResponseDto) JsonConverter.jsonToObj(packet.getbMsq().getMessage(), ResponseDto.class);
		JsonConverter.fixResponse(responseDto);
		return responseDto;
	}

	public static RequestDto changeGoodsQuantity(ChangeGoodsQuantityDto changeGoodsQuantityDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(changeGoodsQuantityDto);
		requestDto.setJwtAccess(jwt);
		requestDto.setRequestType(RequestResponseType.CHANGE_GOODS_QUANTITY);
		return requestDto;
	}

	public static RequestDto removeGoods(GoodsDto goodsDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(goodsDto);
		requestDto.setJwtAccess(jwt);
		requestDto.setRequestType(RequestResponseType.REMOVE_GOODS);
		return requestDto;
	}

	public static RequestDto removeGroup(GroupDto groupDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(groupDto);
		requestDto.setJwtAccess(jwt);
		requestDto.setRequestType(RequestResponseType.REMOVE_GROUP);
		return requestDto;
	}

	public static RequestDto addUser(UserDto userDto) {
		RequestDto requestDto = new RequestDto();
		requestDto.setData(userDto);
		requestDto.setRequestType(RequestResponseType.ADD_USER);
		return requestDto;
	}

	public static RequestDto addGoods(GoodsDto goodsDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwtAccess(jwt);
		requestDto.setData(goodsDto);
		requestDto.setRequestType(RequestResponseType.ADD_GOODS);
		return requestDto;

	}

	public static RequestDto addGoodsByGroupName(AddGoodsByGroupNameDto addGoodsByGroupNameDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwtAccess(jwt);
		requestDto.setData(addGoodsByGroupNameDto);
		requestDto.setRequestType(RequestResponseType.ADD_GOODS_BY_GROUP_NAME);
		return requestDto;
	}

	public static RequestDto addGroup(GroupDto groupDto, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwtAccess(jwt);
		requestDto.setData(groupDto);
		requestDto.setRequestType(RequestResponseType.ADD_GROUP);
		return requestDto;
	}

	public static RequestDto getAllGroups(String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwtAccess(jwt);
		requestDto.setRequestType(RequestResponseType.GET_ALL_GROUPS);
		return requestDto;
	}

	public static RequestDto getAllGoods(String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwtAccess(jwt);
		requestDto.setRequestType(RequestResponseType.GET_ALL_GOODS);
		return requestDto;
	}

	public static RequestDto findGoods(String substring, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwtAccess(jwt);
		requestDto.setData(substring);
		requestDto.setRequestType(RequestResponseType.FIND_GOODS);
		return requestDto;

	}

	public static RequestDto findGroups(String substring, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwtAccess(jwt);
		requestDto.setData(substring);
		requestDto.setRequestType(RequestResponseType.FIND_GROUPS);
		return requestDto;
	}

	public static RequestDto findGood(String substring, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwtAccess(jwt);
		requestDto.setData(substring);
		requestDto.setRequestType(RequestResponseType.FIND_GOOD);
		return requestDto;

	}

	public static RequestDto findGroup(String substring, String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setJwtAccess(jwt);
		requestDto.setData(substring);
		requestDto.setRequestType(RequestResponseType.FIND_GROUP);
		return requestDto;
	}

	public static RequestDto authorisation(AuthDto authDto) {
		RequestDto requestDto = new RequestDto();
		requestDto.setRequestType(RequestResponseType.AUTH);
		requestDto.setData(authDto);
		return requestDto;
	}

	public static RequestDto refreshJwt(String jwt) {
		RequestDto requestDto = new RequestDto();
		requestDto.setRequestType(RequestResponseType.REFRESH_JWT);
		requestDto.setJwtRefresh(jwt);
		return requestDto;
	}
}
