package inventory.shared.Dto;

public class ResponseDto {
	private ResponseErrorType responseErrorType;
	private Object data;

	public ResponseDto(ResponseErrorType responseErrorType, Object data) {
		this.responseErrorType = responseErrorType;
		this.data = data;
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
