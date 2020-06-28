package inventory.shared.impl;

import inventory.shared.Dto.GoodsDto;
import inventory.shared.Dto.GroupDto;
import inventory.shared.api.IProxyService;

import java.util.ArrayList;

public class ProxyServiceMock implements IProxyService {
	private ArrayList<GroupDto> groups;
	private ArrayList<GoodsDto> goods;

	public ProxyServiceMock() {
		groups = new ArrayList<>();
		goods = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			GoodsDto goodsDto = new GoodsDto(i, "Goods" + i, i, i);
			goods.add(goodsDto);
		}

		for (int i = 0; i < 10; i++) {
			GroupDto groupDto = new GroupDto( i,"Group" + i);
			groups.add(groupDto);
		}
	}



	public ArrayList<GroupDto> getGroups() {
		return groups;
	}

	public ArrayList<GoodsDto> getGoods() {
		return  goods;
	}

	public ArrayList<GroupDto> findGroups(String substring) {
		ArrayList<GroupDto> res = new ArrayList<>();
		for (GroupDto groupDto : groups) {
			if (groupDto.getName().toLowerCase().contains(substring.toLowerCase()))
			{
				res.add(groupDto);
			}

		}
		return res;
	}

	public ArrayList<GoodsDto> findGoods(String substring) {
		ArrayList<GoodsDto> res = new ArrayList<>();
		for (GoodsDto goodsDto : goods) {
			if (goodsDto.getName().toLowerCase().contains(substring.toLowerCase()))
			{
				res.add(goodsDto);
			}

		}
		return  res;
	}

	public ArrayList<GoodsDto> findGoods(GroupDto groupDto) {
		ArrayList<GoodsDto> res = new ArrayList<>();
		for (GoodsDto goodsDto : goods) {
			if (goodsDto.getGroupId() == groupDto.getId())
			{
				res.add(goodsDto);
			}

		}
		return  res;
	}

	public ArrayList<GoodsDto> findGoods(GroupDto groupDto, String substring) {
		ArrayList<GoodsDto> res = new ArrayList<>();
		for (GoodsDto goodsDto : goods) {
			if (goodsDto.getGroupId() == groupDto.getId() && goodsDto.getName().toLowerCase().contains(substring.toLowerCase()))
			{
				res.add(goodsDto);
			}

		}
		return  res;
	}

	public void addGroup(GroupDto groupDto){
		groupDto.setId(groups.size());
		groups.add(groupDto);
	}

	public void addGoods(GoodsDto goodsDto){
		System.out.println(goodsDto);
		goodsDto.setId(goods.size());
		goods.add(goodsDto);
	}

	public void addQuantity(GoodsDto goodsDto, int quantity){
		goodsDto.setNumber(quantity + goodsDto.getNumber());
	}

	public void subQuantity(GoodsDto goodsDto, int quantity){
		goodsDto.setNumber(goodsDto.getNumber() - quantity);
	}

	public boolean findGroup(String name){
		for (GroupDto groupDto : groups) {
			if (groupDto.getName().equals(name))
				return true;
		}
		return false;
	}

	public boolean findGood(String name){
		for (GoodsDto goodsDto : goods) {
			if (goodsDto.getName().equals(name))
				return true;
		}
		return false;
	}

	public void removeGroup(GroupDto groupDto){
		int groupId = groupDto.getId();
			for (int i = 0; i < goods.size(); i++) {
				if(goods.get(i).getGroupId() == groupId) {
					removeGoods(goods.get(i));
					i--;
				}
		}
		groups.remove(groupDto);
	}

	public void removeGoods(GoodsDto goodsDto){
		goods.remove(goodsDto);
	}





}
