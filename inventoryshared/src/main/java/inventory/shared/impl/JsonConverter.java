package inventory.shared.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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

}
