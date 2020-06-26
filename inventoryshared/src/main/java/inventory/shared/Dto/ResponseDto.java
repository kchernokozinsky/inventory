package inventory.shared.Dto;

import inventory.shared.impl.JsonConverter;

public class ResponseDto {
	private ResponseErrorType responseErrorType;
	private RequestResponseType requestResponseType;
	private Object data;
	private String jwtAccess;
	private String jwtRefresh;

	public String getJwtAccess() {
		return jwtAccess;
	}

	public void setJwtAccess(String jwtAccess) {
		this.jwtAccess = jwtAccess;
	}

	public RequestResponseType getRequestResponseType() {
		return requestResponseType;
	}

	public void setRequestResponseType(RequestResponseType requestResponseType) {
		this.requestResponseType = requestResponseType;
	}

	public ResponseErrorType getResponseErrorType() {
		return responseErrorType;
	}

	public void setResponseErrorType(ResponseErrorType responseErrorType) {
		this.responseErrorType = responseErrorType;
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

	public String getJwtRefresh() {
		return jwtRefresh;
	}

	public void setJwtRefresh(String jwtRefresh) {
		this.jwtRefresh = jwtRefresh;
	}
}
