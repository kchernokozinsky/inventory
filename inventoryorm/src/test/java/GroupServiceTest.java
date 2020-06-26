import inventory.orm.entity.Group;
import inventory.orm.services.GoodsService;
import inventory.orm.services.GroupService;
import inventory.shared.Dto.GoodsDto;
import inventory.shared.Dto.GroupDto;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class GroupServiceTest {
	@BeforeClass
	public static void saveTest() {
		GroupDto groupDto = GroupService.getGroupService().save(new GroupDto("anme"));
		GoodsDto goodsDto = GoodsService.getGoodsService().save(new GoodsDto("testgoods", 1, groupDto.getId()));
		GroupService.getGroupService().save(new GroupDto("pugro"));
	}

	@AfterClass
	public static void afterTest() {
		assertEquals(GroupService.getGroupService().getById(GroupService.getGroupService().getByName("name").getId())
				.getName(), "name");
		GroupService.getGroupService().getByName("group");
		GroupService.getGroupService().delete(GroupService.getGroupService().getByName("name"));
		GroupService.getGroupService().delete(GroupService.getGroupService().getByName("group"));
	}

	@Test
	public void changeTest() {
		GroupDto GroupDTO = GroupService.getGroupService().getByName("pugro");
		GroupService.getGroupService().changeName(GroupDTO, "group");
	}

	@Test
	public void getListTest() {
		assert (GroupService.getGroupService().getAll().size() > 0);
		List<GroupDto> groupsDTO = GroupService.getGroupService().getByNameLike("n");
		assert (groupsDTO.size() > 0);
		for (GroupDto group : groupsDTO) {
			assert (group.getName().contains("n"));
		}
	}

	@Test
	public void updateTest() {
		GroupDto GroupDTO = GroupService.getGroupService().getByName("anme");
		GroupService.getGroupService().update(new GroupDto(GroupDTO.getId(), "name"));
	}

    @Ignore
    public void deleteTest() {
        try {
            GroupService.getGroupService().delete(GroupService.getGroupService().getByName("name"));
        } catch (Exception e) {
        }
        try {
            GroupService.getGroupService().delete(GroupService.getGroupService().getByName("group"));
        } catch (Exception e) {
        }
        try {
            GroupService.getGroupService().delete(GroupService.getGroupService().getByName("anme"));
        } catch (Exception e) {
        }
        try {
            GroupService.getGroupService().delete(GroupService.getGroupService().getByName("pugro"));
        } catch (Exception e) {
        }
    }

}
