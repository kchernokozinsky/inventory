import com.fasterxml.jackson.databind.ObjectMapper;
import inventory.shared.Dto.GoodsDto;
import inventory.shared.impl.JsonConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class JsonConverterTest {

	@Test
	public void jsonConvertTest(){
		GoodsDto goodsDto = new GoodsDto(1,"Moloko", 1,1);
		String json = JsonConverter.objToJson(goodsDto);
		GoodsDto convertedBack = (GoodsDto) JsonConverter.jsonToObj(json, GoodsDto.class);
		Assert.assertEquals(goodsDto.getGroup().getId(), convertedBack.getGroup().getId());
		Assert.assertEquals(goodsDto.getId(), convertedBack.getId());
		Assert.assertEquals(goodsDto.getNumber(), convertedBack.getNumber());
		Assert.assertEquals(goodsDto.getName(), convertedBack.getName());
	}
}
