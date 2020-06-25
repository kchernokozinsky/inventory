import inventory.orm.services.GoodsService;
import inventory.orm.services.GroupService;
import inventory.shared.Dto.GoodsDto;
import inventory.shared.Dto.GroupDto;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class GoodsServiceTest {
	@BeforeClass
	public static void saveTest() {
		GroupService.getGroupService().save(new GroupDto("test"));
		GroupService.getGroupService().save(new GroupDto("testtest"));
		GoodsService.getGoodsService()
				.save(new GoodsDto("name", 20, GroupService.getGroupService().getByName("test").getId()));
		GoodsService.getGoodsService()
				.save(new GoodsDto("eman", 100, GroupService.getGroupService().getByName("testtest").getId()));
	}

	@AfterClass
	public static void afterTest() {
		GoodsDto name = GoodsService.getGoodsService().getByName("namename");
		assert (name.getNumber() == 100);
		assert (name.getGroupId() == GroupService.getGroupService().getByName("testtest").getId());
		GoodsService.getGoodsService().delete(name);
		GoodsDto goods = GoodsService.getGoodsService().getByName("goods");
		assert (goods.getNumber() == 60);
		assert (goods.getGroupId() == GroupService.getGroupService().getByName("test").getId());
		GoodsService.getGoodsService().delete(goods);
		GroupService.getGroupService().delete(GroupService.getGroupService().getByName("test"));
		GroupService.getGroupService().delete(GroupService.getGroupService().getByName("testtest"));
	}

	@Test
	public void changeTest() {
		GoodsDto goodsDTO = GoodsService.getGoodsService().getByName("name");
		goodsDTO = GoodsService.getGoodsService().changeNumber(goodsDTO, 100);
		goodsDTO = GoodsService.getGoodsService().plusNumber(goodsDTO, 4);
		assert (goodsDTO.getNumber() == 104);
		goodsDTO = GoodsService.getGoodsService().minusNumber(goodsDTO, 4);
		assert (goodsDTO.getNumber() == 100);
		goodsDTO = GoodsService.getGoodsService().changeName(goodsDTO, "namename");
		GoodsService.getGoodsService()
				.changeGroupId(goodsDTO, GroupService.getGroupService().getByName("testtest").getId());
	}

	@Test
	public void getListTest() {
		assert (GoodsService.getGoodsService().getAll().size() > 0);
		List<GoodsDto> nameLike = GoodsService.getGoodsService().getByNameLike("n");
		assert (nameLike.size() > 0);
		for (GoodsDto goods : nameLike) {
			assert (goods.getName().contains("n"));
		}
		assert (GoodsService.getGoodsService()
				.getListByGroupId(GroupService.getGroupService().getByName("test").getId()).size() > 0 ||
				GoodsService.getGoodsService()
						.getListByGroupId(GroupService.getGroupService().getByName("testtest").getId()).size() > 0);
		assert (GoodsService.getGoodsService().getListByNumber(100).size() > 0);
	}

	@Test
	public void updateTest() {
		GoodsDto goodsDTO = GoodsService.getGoodsService().getByName("eman");
		goodsDTO =
				new GoodsDto(goodsDTO.getId(), "goods", 60, GroupService.getGroupService().getByName("test").getId());
		GoodsService.getGoodsService().update(goodsDTO);
		assertEquals(GoodsService.getGoodsService().getById(goodsDTO.getId()).getName(), "goods");
		assert (GoodsService.getGoodsService().getByName("goods").getId() == goodsDTO.getId());
	}

//    @Test
//    public void deleteTest(){
//        GoodsService.getGoodsService().delete(GoodsService.getGoodsService().getByName("namename"));
//        GoodsService.getGoodsService().delete(GoodsService.getGoodsService().getByName("goods"));
//        GroupService.getGroupService().delete(GroupService.getGroupService().getByName("test"));
//        GroupService.getGroupService().delete(GroupService.getGroupService().getByName("testtest"));
//    }
}
