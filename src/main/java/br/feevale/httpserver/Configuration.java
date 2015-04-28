package br.feevale.httpserver;

/**
 * Created by jonasflesch on 4/27/15.
 */
public class Configuration {

	private String basePath;

	private static Configuration INSTANCE;

	private Configuration(){}

	public static Configuration getInstance(){
		if(INSTANCE == null){
			INSTANCE = new Configuration();
		}
		return INSTANCE;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
