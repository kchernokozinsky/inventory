package inventory.shared.Dto;

import inventory.shared.impl.JsonConverter;

public class RequestDto {
	private RequestResponseType requestType;
	private Object data;
	private String jwt;


	public RequestDto(RequestResponseType requestType, Object data) {
		this.requestType = requestType;
		this.data = data;
	}

	public RequestResponseType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestResponseType requestType) {
		this.requestType = requestType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toJson(){
		return JsonConverter.objToJson(this);
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
}
