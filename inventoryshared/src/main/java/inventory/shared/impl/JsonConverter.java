package inventory.shared.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import inventory.shared.Dto.RequestDto;
import inventory.shared.Dto.RequestResponseType;

import java.io.IOException;

public class JsonConverter {

	public static String objToJson(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		String res = null;
		try {
			res = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static Object jsonToObj(String json, Class<?> klass){

		ObjectMapper objectMapper = new ObjectMapper();
		Object value = null;
		try {
			 value = objectMapper.readValue(json, klass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static void fixRequest(Object obj) {
		if (obj instanceof RequestDto) {
			RequestDto requestDto = (RequestDto)obj;
			Object data = requestDto.getData();
			RequestResponseType requestResponseType = requestDto.getRequestType();
			if (data == null || requestResponseType == null) return;
			String dataJson = objToJson(data);
			data = jsonToObj(dataJson, requestResponseType.getRequestKlass());
			requestDto.setData(data);
		}
	}

}
