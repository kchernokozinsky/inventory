package inventory.client.impl;

import inventory.client.impl.Utils.RequestPacketsUtil;
import inventory.client.impl.Utils.RequestUtil;
import inventory.shared.Dto.*;
import inventory.shared.api.IProxyService;
import inventory.shared.impl.Packet;
import inventory.shared.impl.SettingsConst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProxyService implements IProxyService {
	private InventoryClient inventoryClient;
	private ArrayList<GoodsDto> goods;
	private ArrayList<GroupDto> groups;

	public ProxyService() {

	}

	@Override
	public void start() {
		this.inventoryClient = new InventoryClient();
		try {
			inventoryClient.startConnection(SettingsConst.LOCALHOST, SettingsConst.TCP_SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<GroupDto> getGroups() {
		if (groups == null) {
			setAllGroups();
		}
		return groups;
	}

	@Override
	public ArrayList<GoodsDto> getGoods() {
		if (goods == null) {
			setAllGoods();
		}
		return goods;
	}

	private void setAllGroups() {
		RequestDto requestDto = RequestUtil.getAllGroups(inventoryClient.getJwtAccess());
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
			checkJwtAccess();
			setAllGroups();
		}else {
			groups = new ArrayList<GroupDto>(Arrays.asList((GroupDto[]) responseDto.getData()));
		}
	}

	private void setAllGoods() {
		RequestDto requestDto = RequestUtil.getAllGoods(inventoryClient.getJwtAccess());
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT) {
			checkJwtAccess();
			setAllGoods();
		}else {
			goods = new ArrayList<GoodsDto>(Arrays.asList((GoodsDto[]) responseDto.getData()));
		}
	}

	@Override
	public ArrayList<GroupDto> findGroups(String substring) {
		RequestDto requestDto = RequestUtil.findGroups(substring, inventoryClient.getJwtAccess());
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
			checkJwtAccess();
			return findGroups(substring);
		}else {
			return new ArrayList<GroupDto>(Arrays.asList((GroupDto[]) responseDto.getData()));
		}
	}

	@Override
	public ArrayList<GoodsDto> findGoods(String substring) {
		RequestDto requestDto = RequestUtil.findGoods(substring, inventoryClient.getJwtAccess());
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
			checkJwtAccess();
			return findGoods(substring);
		}else {
			return new ArrayList<GoodsDto>(Arrays.asList((GoodsDto[]) responseDto.getData()));
		}
	}

	@Override
	public ArrayList<GoodsDto> findGoods(GroupDto groupDto) {
		ArrayList<GoodsDto> res = new ArrayList<>();
		for (GoodsDto goodsDto : goods)
			if (goodsDto.getGroupId() == groupDto.getId())
				res.add(goodsDto);
		return res;
	}

	@Override
	public ArrayList<GoodsDto> findGoods(GroupDto groupDto, String substring) {
		ArrayList<GoodsDto> res = new ArrayList<>();
		for (GoodsDto goodsDto : goods) {
			if (goodsDto.getGroupId() == groupDto.getId() &&
					goodsDto.getName().toLowerCase().contains(substring.toLowerCase())) {
				res.add(goodsDto);
			}
		}
		return res;
	}

	@Override
	public void addGroup(GroupDto groupDto) throws Exception {
		RequestDto requestDto = RequestUtil.addGroup(groupDto, inventoryClient.getJwtAccess());
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
			checkJwtAccess();
			addGroup(groupDto);
		}else {
			if (responseDto.getResponseErrorType() == ResponseErrorType.OK)
				groups = new ArrayList<GroupDto>(Arrays.asList((GroupDto[]) responseDto.getData()));
			else
				throw new Exception("This group already exist");
		}
	}

	@Override
	public void addGoods(GoodsDto goodsDto) throws Exception {
		RequestDto requestDto = RequestUtil.addGoods(goodsDto, inventoryClient.getJwtAccess());
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
			checkJwtAccess();
			addGoods(goodsDto);
		}else {
			if (responseDto.getResponseErrorType() == ResponseErrorType.OK)
				goods = new ArrayList<GoodsDto>(Arrays.asList((GoodsDto[]) responseDto.getData()));
			else
				throw new Exception("This goods already exist");
		}

	}

	@Override
	public void addQuantity(GoodsDto goodsDto, int quantity) {
		ChangeGoodsQuantityDto changeGoodsQuantityDto = new ChangeGoodsQuantityDto(goodsDto, quantity);
		RequestDto requestDto = RequestUtil.changeGoodsQuantity(changeGoodsQuantityDto, inventoryClient.getJwtAccess());
		System.out.println("Request: " + requestDto.getData());
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
			checkJwtAccess();
			addQuantity(goodsDto, quantity);
		}else {
			goods = new ArrayList<GoodsDto>(Arrays.asList((GoodsDto[]) responseDto.getData()));
		}
	}

	@Override
	public void subQuantity(GoodsDto goodsDto, int quantity) {
		ChangeGoodsQuantityDto changeGoodsQuantityDto = new ChangeGoodsQuantityDto(goodsDto, -1 * quantity);
		RequestDto requestDto = RequestUtil.changeGoodsQuantity(changeGoodsQuantityDto, inventoryClient.getJwtAccess());
		System.out.println("Request: " + requestDto);
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
			checkJwtAccess();
			subQuantity(goodsDto, quantity);
		}else {
			goods = new ArrayList<GoodsDto>(Arrays.asList((GoodsDto[]) responseDto.getData()));
		}
	}

	@Override
	public boolean findGroup(String name) {
		RequestDto requestDto = RequestUtil.findGroup(name, inventoryClient.getJwtAccess());
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
			checkJwtAccess();
			return findGroup(name);
		}else {
			return responseDto.getData() != null;
		}
	}

	@Override
	public boolean findGood(String name) {
		RequestDto requestDto = RequestUtil.findGood(name, inventoryClient.getJwtAccess());
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
			checkJwtAccess();
			return findGood(name);
		}else {
			return responseDto.getData() != null;
		}
	}

	@Override
	public void removeGroup(GroupDto groupDto) {
		RequestDto requestDto = RequestUtil.removeGroup(groupDto, inventoryClient.getJwtAccess());
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
			checkJwtAccess();
			removeGroup(groupDto);
		}else {
			groups = new ArrayList<GroupDto>(Arrays.asList((GroupDto[]) responseDto.getData()));
			setAllGoods();
		}
	}

	@Override
	public void removeGoods(GoodsDto goodsDto) {
		RequestDto requestDto = RequestUtil.removeGoods(goodsDto, inventoryClient.getJwtAccess());
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
			checkJwtAccess();
			removeGoods(goodsDto);
		}else {
			goods = new ArrayList<GoodsDto>(Arrays.asList((GoodsDto[]) responseDto.getData()));
		}

	}

	private void checkJwtAccess() {
		RequestDto requestDtoForJwt = RequestUtil.refreshJwt(inventoryClient.getJwtRefresh());

		Packet requestPacketForJwt = RequestPacketsUtil
				.createRequestPacket(requestDtoForJwt, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDtoForJwt = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacketForJwt.encode()));
		inventoryClient.setJwtAccess(responseDtoForJwt.getJwtAccess());
		inventoryClient.setJwtRefresh(responseDtoForJwt.getJwtRefresh());
	}


	@Override
	public boolean auth(AuthDto authDto) {
		RequestDto requestDto = RequestUtil.authorisation(authDto);
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if (responseDto.getResponseErrorType() == ResponseErrorType.OK) {
			inventoryClient.setJwtAccess(responseDto.getJwtAccess());
			inventoryClient.setJwtRefresh(responseDto.getJwtRefresh());
			return true;
		}
		return false;
	}

	@Override
	public void addUser(UserDto userDto) throws Exception {
		RequestDto requestDto = RequestUtil.addUser(userDto);
		Packet requestPacket = RequestPacketsUtil
				.createRequestPacket(requestDto, inventoryClient.getClientSocket().getInetAddress(),
						inventoryClient.getClientSocket().getPort());
		ResponseDto responseDto = RequestUtil.packetToResponse(inventoryClient.sendMessage(requestPacket.encode()));
		if (responseDto.getResponseErrorType() != ResponseErrorType.OK) {
			throw new Exception("This user already exists");
		}
	}

	@Override
	public void logOut() {
		inventoryClient.setJwtAccess(null);
		inventoryClient.setJwtRefresh(null);
		goods = null;
		groups = null;
	}


}
