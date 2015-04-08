package br.feevale.httpserver;

/**
 * Created by jonasflesch on 4/7/15.
 */
public enum HttpMethod {

	GET, POST, HEAD;

	public static HttpMethod parseFromHeader(String line){
		return valueOf(line.substring(0, line.indexOf(' ')));
	}

}
