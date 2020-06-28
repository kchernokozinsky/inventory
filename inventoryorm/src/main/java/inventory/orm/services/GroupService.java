package inventory.orm.services;

import inventory.orm.entity.Goods;
import inventory.orm.entity.Group;
import inventory.shared.Dto.GroupDto;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GroupService {

	private static GroupService groupService;

	private GroupService() {
	}

	public static GroupService getGroupService() {
		if (groupService == null) {
			groupService = new GroupService();
		}
		return groupService;
	}

	public GroupDto save(GroupDto groupDTO) {
		Group group = new Group(groupDTO);
		try {
			getByName(group.getName());
			throw new EntityExistsException("Group with this name already exist");
		} catch (NoSuchElementException e) {
			GroupDao.getGroupDao().save(group);
			return new GroupDto(group.getId(), group.getName());
		}
	}

	public GroupDto changeName(GroupDto goodDTO, String name) {
		Group group = GroupDao.getGroupDao().findById(goodDTO.getId());
		group.setName(name);
		GroupDao.getGroupDao().update(group);
		return new GroupDto(group.getId(), group.getName());
	}

	public void delete(GroupDto groupDTO) {
		Group group = GroupDao.getGroupDao().findById(groupDTO.getId());
		List<Goods> goods = GoodsDao.getGoodsDao().getListByGroupId(groupDTO.getId());
		for (Goods g:goods) {
			GoodsDao.getGoodsDao().delete(g);
		}
		GroupDao.getGroupDao().delete(group);
	}

	public void update(GroupDto groupDTO) {
		GroupDao.getGroupDao().findById(groupDTO.getId());
		Group group = new Group(groupDTO);
		GroupDao.getGroupDao().update(group);
	}

	public List<GroupDto> getAll() {
		List<Group> groups;
		List<GroupDto> groupsDTO = new ArrayList<GroupDto>();
		groups = GroupDao.getGroupDao().findAll();
		for (Group group : groups) {
			groupsDTO.add(new GroupDto(group.getId(), group.getName()));
		}
		return groupsDTO;
	}

	public GroupDto getById(int id) {
		Group group = GroupDao.getGroupDao().findById(id);
		return new GroupDto(group.getId(), group.getName());
	}

	public GroupDto getByName(String name) {
		Group group = GroupDao.getGroupDao().findByName(name);
		return new GroupDto(group.getId(), group.getName());
	}

	public List<GroupDto> getByNameLike(String name) {
		List<Group> groups;
		List<GroupDto> groupsDTO = new ArrayList<GroupDto>();
		groups = GroupDao.getGroupDao().findByNameLike(name);
		for (Group group : groups) {
			groupsDTO.add(new GroupDto(group.getId(), group.getName()));
		}
		return groupsDTO;
	}

}
