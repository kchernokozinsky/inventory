package inventory.orm.services;

import inventory.orm.entity.Goods;
import inventory.orm.entity.Group;
import inventory.shared.Dto.GoodsDto;
import inventory.shared.Dto.GroupDto;
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
		goods.setId(0);
		try {
			getByName(goods.getName());
			throw new EntityExistsException("Goods with this name alreaty exict");
		} catch (NoSuchElementException e) {
			try {
				GoodsDao.getGoodsDao().save(goods);
			} catch (HibernateException ex) {
				ex.printStackTrace();
			}
			GroupDto groupDto = new GroupDto(goods.getGroup().getId(), goods.getGroup().getName());
			return new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), groupDto);
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

	public GoodsDto changeName(GoodsDto goodsDTO, String name) {
		Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
		goods.setName(name);
		GoodsDao.getGoodsDao().update(goods);
		GroupDto groupDto = new GroupDto(goods.getGroup().getId(), goods.getGroup().getName());
		return new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), groupDto);
	}


	public GoodsDto changeGroup(GoodsDto goodsDTO, GroupDto groupDto) {
		Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
		Group group = GroupDao.getGroupDao().findById(groupDto.getId());
		goods.setGroup(group);
		GoodsDao.getGoodsDao().update(goods);
		goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
		return new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), groupDto);
	}

	public GoodsDto changeNumber(GoodsDto goodsDTO, int number) {
		Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
		goods.setNumber(number);
		GoodsDao.getGoodsDao().update(goods);
		GroupDto groupDto = new GroupDto(goods.getGroup().getId(), goods.getGroup().getName());
		return new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), groupDto);
	}

	public GoodsDto pmNumber(GoodsDto goodsDTO, int number) {
		if (number == 0)
			return goodsDTO;
		Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
		if (goods.getNumber() + number < 0){
			throw new IllegalArgumentException("Illegal number");
		}
		goods.setNumber(goods.getNumber() + number);
		GroupDto groupDto = new GroupDto(goods.getGroup().getId(), goods.getGroup().getName());
		return new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), groupDto);
	}

	public GoodsDto plusNumber(GoodsDto goodsDTO, int number) {
		if (number < 0) {
			throw new IllegalArgumentException("number must be >= 0");
		}
		return pmNumber(goodsDTO, number);
	}

	public GoodsDto minusNumber(GoodsDto goodsDTO, int number) {
		if (number < 0 || goodsDTO.getNumber() < number) {
			throw new IllegalArgumentException("number must be >= 0");
		}
		return pmNumber(goodsDTO, -number);
	}

	public GoodsDto getById(int id) {
		Goods goods = GoodsDao.getGoodsDao().findById(id);
		GroupDto groupDto = new GroupDto(goods.getGroup().getId(), goods.getGroup().getName());
		return new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), groupDto);
	}

	public GoodsDto getByName(String name) {
		Goods goods = GoodsDao.getGoodsDao().findByName(name);
		GroupDto groupDto = new GroupDto(goods.getGroup().getId(), goods.getGroup().getName());
		return new GoodsDto(goods.getId(), goods.getName(), goods.getNumber(), groupDto);
	}

	public List<GoodsDto> getByNameLike(String name) {
		if ("".equals(name)) {
			return getAll();
		}
		List<Goods> goods;
		List<GoodsDto> goodsDTO = new ArrayList<GoodsDto>();
		goods = GoodsDao.getGoodsDao().findByNameLike(name);
		for (Goods g : goods) {
			GroupDto groupDto = new GroupDto(g.getGroup().getId(), g.getGroup().getName());
			goodsDTO.add(new GoodsDto(g.getId(), g.getName(), g.getNumber(), groupDto));
		}
		return goodsDTO;
	}

	public List<GoodsDto> getListByNumber(int number) {
		List<Goods> goods;
		List<GoodsDto> goodsDTO = new ArrayList<GoodsDto>();
		goods = GoodsDao.getGoodsDao().getListByNumber(number);
		for (Goods g : goods) {
			GroupDto groupDto = new GroupDto(g.getGroup().getId(), g.getGroup().getName());
			goodsDTO.add(new GoodsDto(g.getId(), g.getName(), g.getNumber(), groupDto));
		}
		return goodsDTO;
	}

	public List<GoodsDto> getListByGroupId(int id) {
		List<Goods> goods;
		List<GoodsDto> goodsDTO = new ArrayList<GoodsDto>();
		goods = GoodsDao.getGoodsDao().getListByGroupId(id);
		for (Goods g : goods) {
			GroupDto groupDto = new GroupDto(g.getGroup().getId(), g.getGroup().getName());
			goodsDTO.add(new GoodsDto(g.getId(), g.getName(), g.getNumber(), groupDto));
		}
		return goodsDTO;
	}

	public List<GoodsDto> getListByGroupIdAndNameLike(int id, String name) {
		List<Goods> goods;
		List<GoodsDto> goodsDTO = new ArrayList<GoodsDto>();
		goods = GoodsDao.getGoodsDao().getListByGroupIdAndNameLike(id, name);
		for (Goods g : goods) {
			GroupDto groupDto = new GroupDto(g.getGroup().getId(), g.getGroup().getName());
			goodsDTO.add(new GoodsDto(g.getId(), g.getName(), g.getNumber(), groupDto));
		}
		return goodsDTO;
	}

	public List<GoodsDto> getAll() {
		List<Goods> goods;
		List<GoodsDto> goodsDTO = new ArrayList<GoodsDto>();
		goods = GoodsDao.getGoodsDao().findAll();
		for (Goods g : goods) {
			GroupDto groupDto = new GroupDto(g.getGroup().getId(), g.getGroup().getName());
			goodsDTO.add(new GoodsDto(g.getId(), g.getName(), g.getNumber(), groupDto));
		}
		return goodsDTO;
	}

}