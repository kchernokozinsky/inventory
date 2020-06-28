package inventory.shared.api;

import inventory.shared.Dto.GoodsDto;
import inventory.shared.Dto.GroupDto;

import java.util.ArrayList;

public interface IProxyService {

	public ArrayList<GroupDto> getGroups();

	public ArrayList<GoodsDto> getGoods();}

	public ArrayList<GroupDto> findGroups(String substring);

	public ArrayList<GoodsDto> findGoods(String substring);

	public void addGroup(GroupDto groupDto);

	public void addGoods(GoodsDto goodsDto);

	public void addQuantity(GoodsDto goodsDto, int quantity);

	public void subQuantity(GoodsDto goodsDto, int quantity);

	public boolean findGroup(String name);

	public boolean findGood(String name);

	public void removeGroup(GroupDto groupDto);

	public void removeGoods(GoodsDto goodsDto);
}
