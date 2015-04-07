package br.feevale.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by jonasflesch on 4/7/15.
 */
public class RequestProcessor extends Thread {

	public static final Logger LOGGER = LoggerFactory.getLogger(RequestProcessor.class);

	private final Socket socket;

	public RequestProcessor(final Socket socket){
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				String firstHeaderLine = in.readLine();

				HttpMethod httpMethod = HttpMethod.parseFromHeader(firstHeaderLine);

				String requestedPage = parseRequestedPage(firstHeaderLine);

				LOGGER.info("Feita uma requisição " + httpMethod + " da página " + requestedPage);

				ContentRetriever contentRetriever = new ContentRetriever(requestedPage);

				socket.getOutputStream().write(contentRetriever.getContent());
			} finally {
				socket.close();
			}
		} catch (Exception e){
			LOGGER.error("Erro não tratado", e);
		}

	}

	private String parseRequestedPage(final String firstHeaderLine){
		return firstHeaderLine.substring(firstHeaderLine.indexOf(' ') + 1, firstHeaderLine.lastIndexOf(' '));
	}

}
