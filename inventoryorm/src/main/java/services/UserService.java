package services;

import DTO.UserDTO;
import entity.User;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserService {

    private static UserService userService;

    public static UserService getUserService(){
        if (userService == null){
            userService = new UserService();
        }
        return userService;

    }

    private UserService(){}

    public UserDTO save(UserDTO userDTO) {
        User user = new User(userDTO);
        try {
            getByLogin(user.getLogin());
            throw new EntityExistsException("User with this login alreaty exict");
        }catch (NoSuchElementException e){
            UserDao.getUserDao().save(user);
            return new UserDTO(user.getId(), user.getLogin(), user.getPasswordCache());
        }
    }

    public void delete(UserDTO userDTO) {
        User user = UserDao.getUserDao().findById(userDTO.getId());
        UserDao.getUserDao().delete(user);
    }

    public void update(UserDTO userDTO) {
        UserDao.getUserDao().findById(userDTO.getId());
        User user = new User(userDTO);
        UserDao.getUserDao().update(user);
    }

    public List<UserDTO> getAll() {
        List<User> users;
        List<UserDTO> usersDTO = new ArrayList<UserDTO>();
        users = UserDao.getUserDao().findAll();
        for (User user: users){
            usersDTO.add(new UserDTO(user.getId(), user.getLogin(), user.getPasswordCache()));
        }
        return usersDTO;
    }

    public UserDTO getById(int id) {
        User user = UserDao.getUserDao().findById(id);
        return new UserDTO(user.getId(), user.getLogin(), user.getPasswordCache());
    }

    public UserDTO getByLogin(String login) {
        User user = UserDao.getUserDao().findByLogin(login);
        return new UserDTO(user.getId(), user.getLogin(), user.getPasswordCache());
    }

    public UserDTO validate(String login, String password){
        User user = UserDao.getUserDao().validateUser(login, password);
        return new UserDTO(user.getId(), user.getLogin(), user.getPasswordCache());
    }

    public UserDTO changeLogin(UserDTO userDTO, String login){
        User user = UserDao.getUserDao().findById(userDTO.getId());
        user.setLogin(login);
        UserDao.getUserDao().update(user);
        return new UserDTO(user.getId(), user.getLogin(), user.getPasswordCache());
    }

    public UserDTO changePasswordCache(UserDTO userDTO, String passwordCache){
        User user = UserDao.getUserDao().findById(userDTO.getId());
        user.setPasswordCache(passwordCache);
        UserDao.getUserDao().update(user);
        return new UserDTO(user.getId(), user.getLogin(), user.getPasswordCache());
    }

}
