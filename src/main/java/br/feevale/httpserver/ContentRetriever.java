package br.feevale.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by jonasflesch on 4/7/15.
 */
public class ContentRetriever {

	public static final Logger LOGGER = LoggerFactory.getLogger(ContentRetriever.class);

	private final String requestedPath;

	public ContentRetriever(final String requestedPath){
		if("/".equals(requestedPath)){
			this.requestedPath = "/index.html";
		} else {
			this.requestedPath = requestedPath;
		}
	}

	public byte[] getContent(){
		try {
			String decodedRequestPath = URLDecoder.decode(requestedPath, "UTF-8");
			Path path = Paths.get(Configuration.getInstance().getBasePath() + decodedRequestPath);
			return Files.readAllBytes(path);
		} catch (final Exception e){
			LOGGER.info("Path " + requestedPath + " não encontrado");
			return null;
		}
	}

}
