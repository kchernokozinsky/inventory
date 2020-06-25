package services;

import DTO.GroupDTO;
import DTO.UserDTO;
import entity.Group;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GroupService {

    private static GroupService groupService;

    public static GroupService getGroupService(){
        if (groupService == null){
            groupService = new GroupService();
        }
        return groupService;
    }

    private GroupService(){}

    public GroupDTO save(GroupDTO groupDTO) {
        Group group = new Group(groupDTO);
        try {
            getByName(group.getName());
            throw new EntityExistsException("Group with this name alreaty exict");
        }catch (NoSuchElementException e){
            GroupDao.getGroupDao().save(group);
            return new GroupDTO(group.getId(), group.getName());
        }
    }

    public void delete(GroupDTO groupDTO)
    {
        Group group = GroupDao.getGroupDao().findById(groupDTO.getId());
        GroupDao.getGroupDao().delete(group);
    }

    public void update(GroupDTO groupDTO)
    {
        GroupDao.getGroupDao().findById(groupDTO.getId());
        Group group = new Group(groupDTO);
        GroupDao.getGroupDao().update(group);
    }

    public List<GroupDTO> getAll() {
        List<Group> groups;
        List<GroupDTO> groupsDTO = new ArrayList<GroupDTO>();
        groups = GroupDao.getGroupDao().findAll();
        for (Group group: groups){
            groupsDTO.add(new GroupDTO(group.getId(), group.getName()));
        }
        return groupsDTO;
    }

    public GroupDTO getById(int id)
    {
        Group group = GroupDao.getGroupDao().findById(id);
        return new GroupDTO(group.getId(), group.getName());
    }

    public GroupDTO getByName(String name) {
        Group group = GroupDao.getGroupDao().findByName(name);
        return new GroupDTO(group.getId(), group.getName());
    }

    public List<GroupDTO> getByNameLike(String name) {
        List<Group> groups;
        List<GroupDTO> groupsDTO = new ArrayList<GroupDTO>();
        groups = GroupDao.getGroupDao().findByNameLike(name);
        for (Group group: groups){
            groupsDTO.add(new GroupDTO(group.getId(), group.getName()));
        }
        return groupsDTO;
    }

    public GroupDTO changeName(GroupDTO goodDTO, String name){
        Group group = GroupDao.getGroupDao().findById(goodDTO.getId());
        group.setName(name);
        GroupDao.getGroupDao().update(group);
        return new GroupDTO(group.getId(), group.getName());
    }

}
