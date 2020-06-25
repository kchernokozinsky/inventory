import inventory.orm.services.UserService;
import inventory.shared.Dto.UserDto;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {
	@BeforeClass
	public static void saveTest() {
		UserService.getUserService().save(new UserDto("login", "password"));
		UserService.getUserService().save(new UserDto("loginlogin", "passwordpassword"));
	}

	@AfterClass
	public static void deleteTest() {
		UserService.getUserService().validate("login", "passwordd");
		UserService.getUserService().validate("lllogin", "pppassword");
		assert (UserService.getUserService().getAll().size() > 0);
		UserService.getUserService().delete(UserService.getUserService().getByLogin("login"));
		UserService.getUserService().delete(UserService.getUserService().getByLogin("lllogin"));
	}

	@Test
	public void changeTest() {
		UserDto userDTO = UserService.getUserService().getByLogin("loginlogin");
		userDTO = UserService.getUserService().changeLogin(userDTO, "lllogin");
		UserService.getUserService()
				.changePasswordCache(userDTO, BCrypt.hashpw("lllogin:pppassword", BCrypt.gensalt()));
	}

	@Test
	public void getByTest() {
		assertEquals(UserService.getUserService().getById(UserService.getUserService().getByLogin("login").getId())
				.getLogin(), "login");
	}

	@Test
	public void updateTest() {
		UserDto userDTO = UserService.getUserService().getByLogin("login");
		UserDto userDTOf = new UserDto(userDTO.getLogin(), "passwordd");
		userDTOf.setId(userDTO.getId());
		UserService.getUserService().update(userDTOf);
	}

	@Ignore
	public void delete() {
		UserService.getUserService().delete(UserService.getUserService().getByLogin("login"));
		UserService.getUserService().delete(UserService.getUserService().getByLogin("lllogin"));
	}

}
