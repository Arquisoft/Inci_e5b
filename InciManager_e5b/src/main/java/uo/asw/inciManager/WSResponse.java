package uo.asw.inciManager;

public class WSResponse {

	private String message;
	
	public WSResponse() {
		
	}
	
	public WSResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
