package services;

import entity.User;

import java.util.List;

public class UserService {

    private static UserService userService;

    public static UserService getUserService(){
        if (userService == null){
            userService = new UserService();
        }
        return userService;
    }

    private UserService(){}

    public void saveUser(User user) {
        UserDao.getUserDao().save(user);
    }

    public void deleteUser(User user) {
        UserDao.getUserDao().delete(user);
    }

    public void updateUser(User user) {
        UserDao.getUserDao().update(user);
    }
    public List<User> findAllUser() {
        return UserDao.getUserDao().findAll();
    }

    public User findUserById(int id) {
        return UserDao.getUserDao().findUserById(id);
    }

    public User findUserByName(String name) {
        return UserDao.getUserDao().findUserByName(name);
    }

    public List<User> findUserByNameLike(String name)
    {
//        return UserDao.getUserDao().findUserByName(name);
        return null;
    }
}
