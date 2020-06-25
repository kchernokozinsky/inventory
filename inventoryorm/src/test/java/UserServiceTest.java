import DTO.UserDTO;
import org.junit.*;
import org.mindrot.jbcrypt.BCrypt;
import services.UserService;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {
    @BeforeClass
    public static void saveTest() {
        UserService.getUserService().save(new UserDTO("login", "password"));
        UserService.getUserService().save(new UserDTO("loginlogin", "passwordpassword"));
    }

    @AfterClass
    public static void deleteTest() {
        UserService.getUserService().validate("login", "passwordd");
        UserService.getUserService().validate("lllogin", "pppassword");
        assert(UserService.getUserService().getAll().size()>0);
        UserService.getUserService().delete(UserService.getUserService().getByLogin("login"));
        UserService.getUserService().delete(UserService.getUserService().getByLogin("lllogin"));
    }

    @Test
    public void changeTest(){
        UserDTO userDTO = UserService.getUserService().getByLogin("loginlogin");
        userDTO = UserService.getUserService().changeLogin(userDTO, "lllogin");
        UserService.getUserService().changePasswordCache(userDTO, BCrypt.hashpw("lllogin:pppassword", BCrypt.gensalt()));
    }

    @Test
    public void getByTest(){
        assertEquals(UserService.getUserService().getById(UserService.getUserService().getByLogin("login").getId()).getLogin(), "login");
    }

    @Test
    public void updateTest() {
        UserDTO userDTO = UserService.getUserService().getByLogin("login");
        UserDTO userDTOf = new UserDTO(userDTO.getLogin(), "passwordd");
        userDTOf.setId(userDTO.getId());
        UserService.getUserService().update(userDTOf);
    }

    @Ignore
    public void delete(){
        UserService.getUserService().delete(UserService.getUserService().getByLogin("login"));
        UserService.getUserService().delete(UserService.getUserService().getByLogin("lllogin"));
    }

}
