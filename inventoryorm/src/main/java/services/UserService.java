package services;

import DTO.UserDTO;
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

    public void saveUser(UserDTO user) {
//        UserDao.getUserDao().save(user);
    }

    public void deleteUser(UserDTO user) {
//        UserDao.getUserDao().delete(user);
    }

    public void updateUser(UserDTO user) {
//        UserDao.getUserDao().update(user);
    }
    public List<UserDTO> findAllUser() {
//        return UserDao.getUserDao().findAll();
        return null;
    }

    public UserDTO findUserById(int id) {
//        return UserDao.getUserDao().findUserById(id);
        return null;
    }

    public UserDTO findUserByName(String name) {
//        return UserDao.getUserDao().findUserByName(name);
        return null;
    }

    public List<UserDTO> findUserByNameLike(String name)
    {
//        return UserDao.getUserDao().findUserByName(name);
        return null;
    }
}
