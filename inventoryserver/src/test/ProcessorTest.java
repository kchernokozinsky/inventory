import inventory.orm.services.UserService;
import inventory.server.Processor;
import inventory.shared.Dto.RequestDto;
import inventory.shared.Dto.RequestResponseType;
import inventory.shared.Dto.ResponseDto;
import inventory.shared.Dto.UserDto;
import org.junit.*;

public class ProcessorTest {
    @Test
    public void TestAddUser(){
        Processor p = new Processor();
        RequestDto requestDto = new RequestDto(RequestResponseType.ADD_USER, new UserDto("userlogin", "userpassword"));
        ResponseDto responseDto = p.addUser(requestDto);
        System.out.println(responseDto.getRequestResponseType());
        System.out.println(responseDto.getResponseErrorType());
        System.out.println(responseDto.getData());
        System.out.println(responseDto.getJwt());
        UserService.getUserService().delete(UserService.getUserService().getByLogin("userlogin"));
    }

}
