import DTO.GroupDTO;
import entity.Group;
import org.junit.*;
import services.GroupService;

import java.util.List;

import static junit.framework.TestCase.*;

public class GroupServiceTest {
    @BeforeClass
    public static void saveTest() {
        GroupService.getGroupService().save(new GroupDTO("anme"));
        GroupService.getGroupService().save(new GroupDTO("pugro"));
    }

    @AfterClass
    public static void afterTest() {
        assertEquals(GroupService.getGroupService().getById(GroupService.getGroupService().getByName("name").getId()).getName(), "name");
        GroupService.getGroupService().getByName("group");
        GroupService.getGroupService().delete(GroupService.getGroupService().getByName("name"));
        GroupService.getGroupService().delete(GroupService.getGroupService().getByName("group"));
    }

    @Test
    public void changeTest(){
        GroupDTO GroupDTO = GroupService.getGroupService().getByName("pugro");
        GroupService.getGroupService().changeName(GroupDTO, "group");
    }

    @Test
    public void getListTest(){
        assert(GroupService.getGroupService().getAll().size() > 0);
        List<GroupDTO> groupsDTO = GroupService.getGroupService().getByNameLike("n");
        assert(groupsDTO.size() > 0);
        for (GroupDTO group: groupsDTO) {
            assert(group.getName().contains("n"));
        }
    }

    @Test
    public void updateTest() {
        GroupDTO GroupDTO = GroupService.getGroupService().getByName("anme");
        GroupService.getGroupService().update(new GroupDTO(GroupDTO.getId(), "name"));
    }

    @Ignore
    public void deleteTest(){
        GroupService.getGroupService().delete(GroupService.getGroupService().getByName("name"));
        GroupService.getGroupService().delete(GroupService.getGroupService().getByName("group"));
    }

}
