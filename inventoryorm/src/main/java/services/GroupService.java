package services;

import DTO.GroupDTO;
import entity.Group;

import java.util.List;

public class GroupService {

    private static GroupService groupService;

    public static GroupService getGroupService(){
        if (groupService == null){
            groupService = new GroupService();
        }
        return groupService;
    }

    private GroupService(){}

    public void saveGroup(GroupDTO group) {

//        GroupDao.getGroupDao().save(new Group(group));
    }

    public void deleteGroup(GroupDTO group)
    {
//        GroupDao.getGroupDao().delete(group);
    }

    public void updateGroup(GroupDTO group)
    {
//        GroupDao.getGroupDao().update(group);
    }

    public List<GroupDTO> findAllGroup() {
//        return GroupDao.getGroupDao().findAll();
        return null;
    }

    public GroupDTO findGroupById(int id)
    {
//        return GroupDao.getGroupDao().findGroupById(id);
        return null;
    }

    public GroupDTO findGroupByName(String name) {
//        return GroupDao.getGroupDao().findGroupByName(name);
        return null;
    }

    public List<GroupDTO> findGroupByNameLike(String name) {
//        return GroupDao.getGroupDao().findGroupByName(name);
        return null;
    }

}
