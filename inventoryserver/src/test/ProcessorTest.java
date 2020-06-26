import inventory.orm.services.GroupService;
import inventory.orm.services.UserService;
import inventory.server.Processor;
import inventory.shared.Dto.*;
import org.junit.*;

public class ProcessorTest {

    @Ignore
    public void Test() throws InterruptedException {
        String jwtAccess;
        String jwtRefresh;
        Processor p = new Processor();
        RequestDto requestDto = new RequestDto(RequestResponseType.ADD_USER, new UserDto("userlogin", "userpassword"));
        ResponseDto responseDto = p.addUser(requestDto);
        System.out.println(responseDto.getRequestResponseType());
        System.out.println(responseDto.getResponseErrorType());
        System.out.println(responseDto.getData());
        System.out.println(responseDto.getJwtAccess());
        System.out.println(responseDto.getJwtRefresh());
        jwtAccess = responseDto.getJwtAccess()+"";
        jwtRefresh = responseDto.getJwtRefresh()+"";
        Thread.sleep(10000);


        requestDto = new RequestDto(RequestResponseType.ADD_GROUP, new GroupDto("groupname"));
        requestDto.setJwtAccess(jwtAccess);
        responseDto = p.addGroup(requestDto);

        while(responseDto.getResponseErrorType() == ResponseErrorType.EXPIRED_JWT){
            RequestDto requestDtoRefresh = new RequestDto(RequestResponseType.REFRESH_JWT, null);
            requestDtoRefresh.setJwtRefresh(jwtRefresh);
            System.out.println("sure" + jwtRefresh);
            responseDto = p.refreshJwt(requestDtoRefresh);
            jwtRefresh = responseDto.getJwtRefresh()+"";
            System.out.println("jwtRefresh " + jwtRefresh);
            jwtAccess = responseDto.getJwtAccess()+"";
            System.out.println("jwtAccess " + jwtAccess);
            requestDto.setJwtAccess(jwtAccess);
            responseDto = p.addGroup(requestDto);
        }
        System.out.println(responseDto.getRequestResponseType());
        System.out.println(responseDto.getResponseErrorType());
        System.out.println(responseDto.getData());
        System.out.println(responseDto.getJwtAccess());
        GroupService.getGroupService().delete(GroupService.getGroupService().getByName("groupname"));
        UserService.getUserService().delete(UserService.getUserService().getByLogin("userlogin"));
    }
}
