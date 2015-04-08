package br.feevale.httpserver;

/**
 * Created by jonasflesch on 4/7/15.
 */
public enum HttpResponseCode {

	OK(200, "OK"), NOT_FOUND(404, "Not found"), NOT_IMPLEMENTED(501, "Not Implemented");

	private final int statusCode;
	private final String statusText;

	HttpResponseCode(final int statusCode, final String statusText){
		this.statusCode = statusCode;
		this.statusText = statusText;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public String getStatusText(){
		return statusText;
	}

}
