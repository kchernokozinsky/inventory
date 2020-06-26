package inventory.server;

import inventory.orm.entity.Goods;
import inventory.orm.entity.Group;
import inventory.orm.entity.User;
import inventory.orm.services.GoodsService;
import inventory.orm.services.GroupService;
import inventory.orm.services.UserService;
import inventory.shared.Dto.*;
import inventory.shared.api.IProccesor;
import inventory.shared.api.ISender;
import inventory.shared.impl.JsonConverter;
import inventory.shared.impl.Message;
import inventory.shared.impl.Packet;
import io.jsonwebtoken.Claims;

import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;

public class Processor implements IProccesor {
	private ISender sender;

	public Processor(ISender sender) {
		this.sender = sender;
	}

	public Processor() {}

	@Override
	public void process(Packet request) {
		String requestJson = request.getbMsq().getMessage();
		RequestDto requestDto = (RequestDto) JsonConverter.jsonToObj(requestJson, ResponseDto.class);
		RequestResponseType requestType = requestDto.getRequestType();
		ResponseDto responseDto = null;

		switch (requestType){
			case AUTH:
				responseDto = authorisation(requestDto);
				break;
			case FIND_GROUPS:
				responseDto = findGroups(requestDto);
				break;
			case FIND_GOODS:
				responseDto = findGoods(requestDto);
				break;
			case GET_ALL_GOODS:
				responseDto = getAllGoods(requestDto);
				break;
			case GET_ALL_GROUPS:
				responseDto = getAllGroups(requestDto);
				break;
			case ADD_GROUP:
				responseDto = addGroup(requestDto);
				break;
			case ADD_GOODS:
				responseDto = addGoods(requestDto);
				break;
			case ADD_GOODS_BY_GROUP_NAME:
				responseDto = addGoodsByGroupName(requestDto);
				break;
			case ADD_USER:
				responseDto = addUser(requestDto);
				break;
			case REMOVE_GROUP:
				responseDto = removeGroup(requestDto);
				break;
			case REMOVE_GOODS:
				responseDto = removeGoods(requestDto);
				break;
			case CHANGE_GOODS_QUANTITY:
				responseDto = changeGoodsQuantity(requestDto);
				break;
			case FIND_GROUP:
				responseDto = findGroup(requestDto);
				break;
			case FIND_GOOD:
				responseDto = findGood(requestDto);
				break;

		}
		Packet response = createResponsePacket(responseDto, request);
		sender.sendMessage(response, response.getClientAddress());
	}

	private ResponseDto findGood(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		String name = (String) requestDto.getData();
		try {
			GoodsService.getGoodsService().getByName(name);
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}
		return responseDto;
	}

	private ResponseDto findGroup(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		String name = (String) requestDto.getData();
		try {
			GroupService.getGroupService().getByName(name);
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}
		return responseDto;
	}

	private ResponseDto changeGoodsQuantity(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		ChangeGoodsQuantityDto d = (ChangeGoodsQuantityDto) responseDto.getData();
		try {
			if (d.getGoodsDto().getId() != 0)
				GoodsService.getGoodsService().pmNumber(d.getGoodsDto(), d.getQuantity());
			else
				GoodsService.getGoodsService().pmNumber(GoodsService.getGoodsService().getByName(d.getGoodsDto().getName()), d.getQuantity());
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}
		return responseDto;
	}

	private ResponseDto removeGoods(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		GoodsDto goodsDto = (GoodsDto) requestDto.getData();
		try {
			if (goodsDto.getId() != 0)
				GoodsService.getGoodsService().delete(goodsDto);
			else
				GoodsService.getGoodsService().delete(GoodsService.getGoodsService().getByName(goodsDto.getName()));
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}
		return responseDto;
	}

	private ResponseDto removeGroup(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		GroupDto groupDto = (GroupDto) requestDto.getData();
		try {
			if (groupDto.getId() != 0)
				GroupService.getGroupService().delete(groupDto);
			else
				GroupService.getGroupService().delete(GroupService.getGroupService().getByName(groupDto.getName()));
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}
		return responseDto;
	}

	public ResponseDto addUser(RequestDto requestDto) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setRequestResponseType(requestDto.getRequestType());
		UserDto userDto = (UserDto) requestDto.getData();
		try {
			UserService.getUserService().save(userDto);
		}catch (EntityExistsException e){
			responseDto.setResponseErrorType(ResponseErrorType.ALREADY_EXIST);
			return responseDto;
		}
		responseDto.setResponseErrorType(ResponseErrorType.OK);
		String s = JwtTokenUtil.createJWT("inventory", userDto.getLogin(), 999999999);
		System.out.println(s);
		responseDto.setJwt(s);
		return responseDto;
	}

	private ResponseDto addGoods(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		try {
			GoodsService.getGoodsService().save((GoodsDto) requestDto.getData());
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (EntityExistsException e){
			responseDto.setResponseErrorType(ResponseErrorType.ALREADY_EXIST);
		}
		return responseDto;
	}

	private ResponseDto addGoodsByGroupName(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		AddGoodsByGroupNameDto d = (AddGoodsByGroupNameDto) requestDto.getData();
		try {
			d.getGoodsDto().setGroupId(GroupService.getGroupService().getByName(d.getGroupName()).getId());
			GoodsService.getGoodsService().save(d.getGoodsDto());
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (NoSuchElementException e){
			//no such group name in DB
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}
		catch (EntityExistsException e){
			responseDto.setResponseErrorType(ResponseErrorType.ALREADY_EXIST);
		}
		return responseDto;
	}

	private ResponseDto addGroup(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		try {
			GroupService.getGroupService().save((GroupDto) requestDto.getData());
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (EntityExistsException e){
			responseDto.setResponseErrorType(ResponseErrorType.ALREADY_EXIST);
		}
		return responseDto;
	}

	private ResponseDto getAllGroups(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		GroupDto[] groupDtos = (GroupDto[]) GoodsService.getGoodsService().getAll().toArray();
		responseDto.setData(groupDtos);
		return responseDto;
	}

	private ResponseDto getAllGoods(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		GoodsDto[] goodsDto = (GoodsDto[]) GoodsService.getGoodsService().getAll().toArray();
		responseDto.setData(goodsDto);
		return responseDto;
	}

	private ResponseDto findGoods(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		String name = (String) requestDto.getData();
		// not sure
		GoodsDto[] goodsDto = (GoodsDto[])GroupService.getGroupService().getByNameLike(name).toArray();
		responseDto.setData(goodsDto);
		return responseDto;
	}

	private ResponseDto findGroups(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH)
			return responseDto;
		String name = (String) requestDto.getData();
		GroupDto[] groupDtos = (GroupDto[])GroupService.getGroupService().getByNameLike(name).toArray();
		responseDto.setData(groupDtos);
		return responseDto;
	}

	private ResponseDto checkJwt(RequestDto requestDto){
		ResponseDto responseDto = new ResponseDto();
		responseDto.setRequestResponseType(requestDto.getRequestType());
		try{
			Claims jwt = JwtTokenUtil.decodeJWT(requestDto.getJwt());
			UserService.getUserService().getByLogin(jwt.getSubject());
		}catch (NoSuchElementException e){
			System.out.println("lol");
			responseDto.setResponseErrorType(ResponseErrorType.FAILED_AUTH);
			return responseDto;
		}catch (Exception ex){
			responseDto.setResponseErrorType(ResponseErrorType.JWT_CHECK_FAILED);
			return responseDto;
		}
		return responseDto;
	}


	private ResponseDto authorisation(RequestDto requestDto) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setRequestResponseType(requestDto.getRequestType());
		AuthDto authDto = (AuthDto) requestDto.getData();
		UserDto userDto;
		try {
			userDto = UserService.getUserService().validate(authDto.getLogin(), authDto.getPassword());
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.FAILED_AUTH);
			return responseDto;
		}catch (Exception ex){
			responseDto.setResponseErrorType(ResponseErrorType.JWT_CHECK_FAILED);
			return responseDto;
		}
		responseDto.setResponseErrorType(ResponseErrorType.OK);
		responseDto.setJwt(JwtTokenUtil.createJWT("inventory", userDto.getLogin(), 999999999));
		return responseDto;
	}

	Packet createResponsePacket(ResponseDto responseDto, Packet requestPacket){
		String responseJson = JsonConverter.objToJson(responseDto);
		Message.Builder mBuilder = new Message.Builder();
		mBuilder.setCType(requestPacket.getbMsq().getcType()).setBUserId(requestPacket.getbMsq().getbUserId()).setMessage(responseJson);

		Message message = mBuilder.build();
		Packet.Builder pBuilder = new Packet.Builder();
		pBuilder.setBMagic(requestPacket.getBMagic()).setBSrc(requestPacket.getBSrc()).setBPktId(requestPacket.getBPktId())
				.setBMsq(message);
		Packet response = pBuilder.build();
		response.setClientAddress(requestPacket.getClientAddress());
		response.setClientPort(requestPacket.getClientPort());
		return response;
	}

}
