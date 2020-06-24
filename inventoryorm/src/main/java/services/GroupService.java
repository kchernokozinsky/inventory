package services;

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

    public void saveGroup(Group group) {
        GroupDao.getGroupDao().save(group);
    }

    public void deleteGroup(Group group) {
        GroupDao.getGroupDao().delete(group);
    }

    public void updateGroup(Group group) {
        GroupDao.getGroupDao().update(group);
    }

    public List<Group> findAllGroup() {
        return GroupDao.getGroupDao().findAll();
    }

    public Group findGroupById(int id) {
        return GroupDao.getGroupDao().findGroupById(id);
    }

    public Group findGroupByName(String name) {
        return GroupDao.getGroupDao().findGroupByName(name);
    }

    public List<Group> findGroupByNameLike(String name) {
//        return GroupDao.getGroupDao().findGroupByName(name);
        return null;
    }

}
