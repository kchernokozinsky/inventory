package inventory.orm.services;

import inventory.orm.entity.Goods;
import inventory.orm.entity.Group;
import inventory.shared.Dto.GoodsDto;
import org.hibernate.HibernateException;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GoodsService {

	private static GoodsService goodsService;

	private GoodsService() {
	}

	public static GoodsService getGoodsService() {
		if (goodsService == null) {
			goodsService = new GoodsService();
		}
		return goodsService;
	}

	public GoodsDto save(GoodsDto goodsDTO) {
		Goods goods = new Goods(goodsDTO);
		try {
			getByName(goods.getName());
			throw new EntityExistsException("Goods with this name already exist");
		} catch (NoSuchElementException e) {
			try {
				GoodsDao.getGoodsDao().save(goods);
			} catch (HibernateException ex) {
				ex.printStackTrace();
			}
			GoodsDto goodsDto = new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
			goodsDto.setGroupName(GroupService.getGroupService().getById(goods.getGroupId()).getName());
			return goodsDto;
		}
	}

	public void delete(GoodsDto goodsDTO) {
		Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
		GoodsDao.getGoodsDao().delete(goods);
	}

	public void update(GoodsDto goodsDTO) {
		GoodsDao.getGoodsDao().findById(goodsDTO.getId());
		Goods goods = new Goods(goodsDTO);
		GoodsDao.getGoodsDao().update(goods);
	}

	public List<GoodsDto> getAll() {
		List<Goods> goods;
		List<GoodsDto> goodsDTO = new ArrayList<GoodsDto>();
		goods = GoodsDao.getGoodsDao().findAll();
		for (Goods g : goods) {
			GoodsDto gDto = new GoodsDto(g.getId(), g.getName(), g.getNumber(), g.getGroupId());
			gDto.setGroupName(GroupService.getGroupService().getById(g.getGroupId()).getName());
			goodsDTO.add(gDto);
		}
		return goodsDTO;
	}

	public GoodsDto getById(int id) {
		Goods goods = GoodsDao.getGoodsDao().findById(id);
		GoodsDto goodsDto = new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
		goodsDto.setGroupName(GroupService.getGroupService().getById(goods.getGroupId()).getName());
		return goodsDto;
	}

	public GoodsDto getByName(String name) {
		Goods goods = GoodsDao.getGoodsDao().findByName(name);
		GoodsDto goodsDto = new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
		goodsDto.setGroupName(GroupService.getGroupService().getById(goods.getGroupId()).getName());
		return goodsDto;
	}

	public List<GoodsDto> getByNameLike(String name) {
		if ("".equals(name)) {
			return getAll();
		}
		List<Goods> goods;
		List<GoodsDto> goodsDTO = new ArrayList<GoodsDto>();
		goods = GoodsDao.getGoodsDao().findByNameLike(name);
		for (Goods g : goods) {
			GoodsDto gDto = new GoodsDto(g.getId(), g.getName(), g.getNumber(), g.getGroupId());
			gDto.setGroupName(GroupService.getGroupService().getById(g.getGroupId()).getName());
			goodsDTO.add(gDto);
		}
		return goodsDTO;
	}

	public List<GoodsDto> getListByNumber(int number) {
		List<Goods> goods;
		List<GoodsDto> goodsDTO = new ArrayList<GoodsDto>();
		goods = GoodsDao.getGoodsDao().getListByNumber(number);
		for (Goods g : goods) {
			GoodsDto gDto = new GoodsDto(g.getId(), g.getName(), g.getNumber(), g.getGroupId());
			gDto.setGroupName(GroupService.getGroupService().getById(g.getGroupId()).getName());
			goodsDTO.add(gDto);
		}
		return goodsDTO;
	}

	public List<GoodsDto> getListByGroupId(int id) {
		List<Goods> goods;
		List<GoodsDto> goodsDTO = new ArrayList<GoodsDto>();
		goods = GoodsDao.getGoodsDao().getListByGroupId(id);
		for (Goods g : goods) {
			GoodsDto gDto = new GoodsDto(g.getId(), g.getName(), g.getNumber(), g.getGroupId());
			gDto.setGroupName(GroupService.getGroupService().getById(g.getGroupId()).getName());
			goodsDTO.add(gDto);
		}
		return goodsDTO;
	}

	public GoodsDto plusNumber(GoodsDto goodsDTO, int number) {
		if (number <= 0 || goodsDTO.getNumber() < number) {
			throw new IllegalArgumentException("number must be > 0");
		}
		return pmNumber(goodsDTO, number);
	}

	public GoodsDto minusNumber(GoodsDto goodsDTO, int number) {
		if (number <= 0 || goodsDTO.getNumber() < number) {
			throw new IllegalArgumentException("number must be > 0");
		}
		return pmNumber(goodsDTO, -number);
	}

	public GoodsDto pmNumber(GoodsDto goodsDTO, int number) {
		Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
		goods.setNumber(goods.getNumber() + number);
		GoodsDao.getGoodsDao().update(goods);
		GoodsDto goodsDto = new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
		goodsDto.setGroupName(GroupService.getGroupService().getById(goods.getGroupId()).getName());
		return goodsDto;
	}

	public GoodsDto changeGroupId(GoodsDto goodsDTO, int group_id) {
		Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
		goods.setGroupId(group_id);
		GoodsDao.getGoodsDao().update(goods);
		GoodsDto goodsDto = new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
		goodsDto.setGroupName(GroupService.getGroupService().getById(goods.getGroupId()).getName());
		return goodsDto;
	}

	public GoodsDto changeName(GoodsDto goodsDTO, String name) {
		Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
		goods.setName(name);
		GoodsDao.getGoodsDao().update(goods);
		GoodsDto goodsDto = new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
		goodsDto.setGroupName(GroupService.getGroupService().getById(goods.getGroupId()).getName());
		return goodsDto;
	}

	public GoodsDto changeNumber(GoodsDto goodsDTO, int number) {
		if (number < 0) {
			throw new IllegalArgumentException("number must be >= 0");
		}
		Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
		goods.setNumber(number);
		GoodsDao.getGoodsDao().update(goods);
		GoodsDto goodsDto = new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
		goodsDto.setGroupName(GroupService.getGroupService().getById(goods.getGroupId()).getName());
		return goodsDto;
	}

}