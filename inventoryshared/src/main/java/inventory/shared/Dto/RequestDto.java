package inventory.shared.Dto;

public class RequestDto {
	private RequestResponseType requestType;
	private Object data;


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
		//To do
		return "";
	}
}
