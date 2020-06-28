package inventory.server;

import inventory.orm.entity.Group;
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
import io.jsonwebtoken.ExpiredJwtException;
import org.hibernate.boot.model.naming.IllegalIdentifierException;

import javax.persistence.EntityExistsException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Processor implements IProccesor {
	private ISender sender;
	private static long JWT_ACCESS_TIME = 100;
	private static long JWT_REFRESH_TIME = Long.MAX_VALUE;

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
			case FIND_GROUPS_BY_NAME_LIKE:
				responseDto = findGroupsByNameLike(requestDto);
				break;
			case FIND_GOODS_BY_NAME_LIKE:
				responseDto = findGoodsByNameLike(requestDto);
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
			case FIND_GOODS_IN_GROUP_BY_NAME_LIKE:
				responseDto = findGoodsInGroupByNameLike(requestDto);
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
			case FIND_GROUP_BY_ID:
				responseDto = findGroupById(requestDto);
				break;
			case FIND_GOOD_BY_ID:
				responseDto = findGoodById(requestDto);
				break;
			case FIND_GROUP_BY_NAME:
				responseDto = findGroupByName(requestDto);
				break;
			case FIND_GOOD_BY_NAME:
				responseDto = findGoodByName(requestDto);
				break;
			case ADD_GOODS_BY_GROUP_NAME:
				responseDto = addGoodsByGroupName(requestDto);
				break;
			case REFRESH_JWT:
				responseDto = refreshJwt(requestDto);
				break;

		}
		Packet response = createResponsePacket(responseDto, request);
		sender.sendMessage(response, response.getClientAddress());
	}

	private ResponseDto findGoodByName(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		String name = (String) requestDto.getData();
		try {
			GoodsDto goodsDto = GoodsService.getGoodsService().getByName(name);
			responseDto.setResponseErrorType(ResponseErrorType.OK);
			responseDto.setData(goodsDto);
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}
		return responseDto;
	}

	private ResponseDto findGroupByName(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		String name = (String) requestDto.getData();
		try {
			GroupDto groupDto = GroupService.getGroupService().getByName(name);
			responseDto.setResponseErrorType(ResponseErrorType.OK);
			responseDto.setData(groupDto);
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}
		return responseDto;
	}

	private ResponseDto findGoodById(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		Integer id = (Integer) requestDto.getData();
		try {
			GoodsDto goodsDto = GoodsService.getGoodsService().getById(id);
			responseDto.setResponseErrorType(ResponseErrorType.OK);
			responseDto.setData(goodsDto);
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}
		return responseDto;
	}

	private ResponseDto findGroupById(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		Integer id = (Integer) requestDto.getData();
		try {
			GroupDto groupDto = GroupService.getGroupService().getById(id);
			responseDto.setResponseErrorType(ResponseErrorType.OK);
			responseDto.setData(groupDto);
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}
		return responseDto;
	}

	private ResponseDto changeGoodsQuantity(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		ChangeGoodsQuantityDto d = (ChangeGoodsQuantityDto) responseDto.getData();
		try {
			GoodsService.getGoodsService().getById(d.getGoodsDto().getId());
			GoodsService.getGoodsService().pmNumber(d.getGoodsDto(), d.getQuantity());
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}catch (IllegalArgumentException e){
			responseDto.setResponseErrorType(ResponseErrorType.ILLEGAL_ARGUMENT);
		}
		return responseDto;
	}

	private ResponseDto removeGoods(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		GoodsDto goodsDto = (GoodsDto) requestDto.getData();
		try {
			GoodsService.getGoodsService().getById(goodsDto.getId());
			GoodsService.getGoodsService().delete(goodsDto);
			GoodsDto[] goodsDtos = GoodsService.getGoodsService().getAll().toArray(new GoodsDto[0]);
			responseDto.setData(goodsDtos);
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.ENTITY_NOT_FOUND);
		}
		return responseDto;
	}

	private ResponseDto removeGroup(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		GroupDto groupDto = (GroupDto) requestDto.getData();
		try {
			GroupService.getGroupService().getById(groupDto.getId());
			GroupService.getGroupService().delete(groupDto);
			GroupDto[] groupsDto = GroupService.getGroupService().getAll().toArray(new GroupDto[0]);
			responseDto.setData(groupsDto);
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
		responseDto.setJwtAccess(JwtTokenUtil.createJWT("inventory", userDto.getLogin(), JWT_ACCESS_TIME, false));
		responseDto.setJwtRefresh(JwtTokenUtil.createJWT("inventory", userDto.getLogin(), JWT_REFRESH_TIME, true));
		return responseDto;
	}

	private ResponseDto addGoods(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		try {
			GoodsService.getGoodsService().save((GoodsDto) requestDto.getData());
			GoodsDto[] goodsDtos = GoodsService.getGoodsService().getAll().toArray(new GoodsDto[0]);
			responseDto.setData(goodsDtos);
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (EntityExistsException e){
			responseDto.setResponseErrorType(ResponseErrorType.ALREADY_EXIST);
		}
		return responseDto;
	}

	private ResponseDto addGoodsByGroupName(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		AddGoodsByGroupNameDto d = (AddGoodsByGroupNameDto) requestDto.getData();
		try {
			d.getGoodsDto().setGroup(new GroupDto(GroupService.getGroupService().getByName(d.getGroupName()).getId(), d.getGroupName()));
			GoodsService.getGoodsService().save(d.getGoodsDto());
			GoodsDto[] goodsDtos = GoodsService.getGoodsService().getListByGroupId(d.getGoodsDto().getGroup().getId()).toArray(new GoodsDto[0]);
			responseDto.setData(goodsDtos);
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

	public ResponseDto addGroup(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		try {
			GroupService.getGroupService().save((GroupDto) requestDto.getData());
			GroupDto[] groupsDtos = GroupService.getGroupService().getAll().toArray(new GroupDto[0]);
			responseDto.setData(groupsDtos);
			responseDto.setResponseErrorType(ResponseErrorType.OK);
		}catch (EntityExistsException e){
			responseDto.setResponseErrorType(ResponseErrorType.ALREADY_EXIST);
		}
		return responseDto;
	}

	private ResponseDto getAllGroups(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		GroupDto[] groupsDto = GroupService.getGroupService().getAll().toArray(new GroupDto[0]);
		responseDto.setData(groupsDto);
		responseDto.setResponseErrorType(ResponseErrorType.OK);
		return responseDto;
	}

	private ResponseDto getAllGoods(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		GoodsDto[] goodsDto = GoodsService.getGoodsService().getAll().toArray(new GoodsDto[0]);
		responseDto.setData(goodsDto);
		responseDto.setResponseErrorType(ResponseErrorType.OK);
		return responseDto;
	}

	private ResponseDto findGoodsByNameLike(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		String name = (String) requestDto.getData();
		GoodsDto[] goodsDto = GoodsService.getGoodsService().getByNameLike(name).toArray(new GoodsDto[0]);
		responseDto.setData(goodsDto);
		responseDto.setResponseErrorType(ResponseErrorType.OK);
		return responseDto;
	}

	private ResponseDto findGoodsInGroupByNameLike(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		FindGoodsInGroupByNameLike data = (FindGoodsInGroupByNameLike) requestDto.getData();
		GoodsDto[] goodsDto = GoodsService.getGoodsService().getListByGroupIdAndNameLike(data.getGoodsDto().getGroup().getId(), data.getName()).toArray(new GoodsDto[0]);
		responseDto.setData(goodsDto);
		responseDto.setResponseErrorType(ResponseErrorType.OK);
		return responseDto;
	}

	private ResponseDto findGroupsByNameLike(RequestDto requestDto) {
		ResponseDto responseDto = checkJwt(requestDto);
		if(responseDto.getResponseErrorType() == ResponseErrorType.FAILED_AUTH ||
				responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT)
			return responseDto;
		String name = (String) requestDto.getData();
		GroupDto[] groupsDto = GroupService.getGroupService().getByNameLike(name).toArray(new GroupDto[0]);
		responseDto.setData(groupsDto);
		responseDto.setResponseErrorType(ResponseErrorType.OK);
		return responseDto;
	}

	private ResponseDto checkJwt(RequestDto requestDto){
		System.out.println("checkJwt");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setRequestResponseType(requestDto.getRequestType());
		try{
			Claims jwt = JwtTokenUtil.decodeJWT(requestDto.getJwtAccess(), false);
			UserService.getUserService().getByLogin(jwt.getSubject());
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.FAILED_AUTH);
			return responseDto;
		}catch (ExpiredJwtException ee){
			responseDto.setResponseErrorType(ResponseErrorType.EXPIRED_JWT);
			return responseDto;
		}catch (Exception ex){
			ex.printStackTrace();
			responseDto.setResponseErrorType(ResponseErrorType.JWT_CHECK_FAILED);
			return responseDto;
		}
		return responseDto;
	}


	public ResponseDto refreshJwt(RequestDto requestDto) {
		System.out.println("refresh");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setRequestResponseType(requestDto.getRequestType());
		Claims jwtRefresh;
		try{
			jwtRefresh = JwtTokenUtil.decodeJWT(requestDto.getJwtRefresh(), true);
			UserService.getUserService().getByLogin(jwtRefresh.getSubject());
		}catch (NoSuchElementException e){
			responseDto.setResponseErrorType(ResponseErrorType.FAILED_AUTH);
			return responseDto;
		}catch (ExpiredJwtException ee){
			responseDto.setResponseErrorType(ResponseErrorType.EXPIRED_JWT);
			return responseDto;
		}catch (Exception ex){
			ex.printStackTrace();
			responseDto.setResponseErrorType(ResponseErrorType.JWT_CHECK_FAILED);
			return responseDto;
		}

		responseDto.setResponseErrorType(ResponseErrorType.OK);
		String setJwtAccess = JwtTokenUtil.createJWT("inventory", jwtRefresh.getSubject(), JWT_ACCESS_TIME, false);
		String setJwtRefresh = JwtTokenUtil.createJWT("inventory", jwtRefresh.getSubject(), JWT_REFRESH_TIME, true);
		responseDto.setJwtAccess(setJwtAccess);
		responseDto.setJwtRefresh(setJwtRefresh);
		return responseDto;
	}


	private ResponseDto authorisation(RequestDto requestDto) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setRequestResponseType(requestDto.getRequestType());
		AuthDto authDto = (AuthDto) requestDto.getData();
		UserDto userDto;
		try {
			userDto = UserService.getUserService().validate(authDto.getLogin(), authDto.getPassword());
		}catch (InputMismatchException e){
			responseDto.setResponseErrorType(ResponseErrorType.FAILED_AUTH);
			return responseDto;
		}
		responseDto.setResponseErrorType(ResponseErrorType.OK);
		responseDto.setJwtAccess(JwtTokenUtil.createJWT("inventory", userDto.getLogin(), JWT_ACCESS_TIME, false));
		responseDto.setJwtRefresh(JwtTokenUtil.createJWT("inventory", userDto.getLogin(), JWT_REFRESH_TIME, true));
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
