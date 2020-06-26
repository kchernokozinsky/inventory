package inventory.orm.services;

import inventory.orm.entity.User;
import inventory.shared.Dto.UserDto;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserService {

	private static UserService userService;

	private UserService() {
	}

	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;

	}

	public UserDto save(UserDto userDTO) {
		User user = new User(userDTO);
		try {
			getByLogin(user.getLogin());
			throw new EntityExistsException("User with this login alreaty exict");
		} catch (NoSuchElementException e) {
			UserDao.getUserDao().save(user);
			return new UserDto(user.getId(), user.getLogin(), user.getPasswordHash());
		}
	}

	public void delete(UserDto userDTO) {
		User user = UserDao.getUserDao().findById(userDTO.getId());
		UserDao.getUserDao().delete(user);
	}

	public void update(UserDto userDTO) {
		UserDao.getUserDao().findById(userDTO.getId());
		User user = new User(userDTO);
		UserDao.getUserDao().update(user);
	}

	public List<UserDto> getAll() {
		List<User> users;
		List<UserDto> usersDTO = new ArrayList<UserDto>();
		users = UserDao.getUserDao().findAll();
		for (User user : users) {
			usersDTO.add(new UserDto(user.getId(), user.getLogin(), user.getPasswordHash()));
		}
		return usersDTO;
	}

	public UserDto getById(int id) {
		User user = UserDao.getUserDao().findById(id);
		return new UserDto(user.getId(), user.getLogin(), user.getPasswordHash());
	}

	public UserDto getByLogin(String login) {
		User user = UserDao.getUserDao().findByLogin(login);
		return new UserDto(user.getId(), user.getLogin(), user.getPasswordHash());
	}

	public UserDto validate(String login, String password) {
		User user = UserDao.getUserDao().validateUser(login, password);
		return new UserDto(user.getId(), user.getLogin(), user.getPasswordHash());
	}

	public UserDto changeLogin(UserDto userDTO, String login) {
		User user = UserDao.getUserDao().findById(userDTO.getId());
		user.setLogin(login);
		UserDao.getUserDao().update(user);
		return new UserDto(user.getId(), user.getLogin(), user.getPasswordHash());
	}

	public UserDto changePasswordCache(UserDto userDTO, String passwordCache) {
		User user = UserDao.getUserDao().findById(userDTO.getId());
		user.setPasswordHash(passwordCache);
		UserDao.getUserDao().update(user);
		return new UserDto(user.getId(), user.getLogin(), user.getPasswordHash());
	}

}
