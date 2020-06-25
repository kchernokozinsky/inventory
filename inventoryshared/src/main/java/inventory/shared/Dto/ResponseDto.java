package inventory.shared.Dto;

public class ResponseDto {
	private ResponseErrorType responseErrorType;
	private RequestResponseType requestResponseType;
	private Object data;


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
}
