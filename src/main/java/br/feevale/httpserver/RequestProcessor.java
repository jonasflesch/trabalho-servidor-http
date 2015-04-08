package br.feevale.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
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

				if(firstHeaderLine != null && !firstHeaderLine.isEmpty()){
					HttpMethod httpMethod = HttpMethod.parseFromHeader(firstHeaderLine);

					if(httpMethod == null || httpMethod != HttpMethod.GET){
						writeHeaders(HttpResponseCode.NOT_IMPLEMENTED);
					} else {
						get(firstHeaderLine, httpMethod);
					}
				}

			} finally {
				socket.close();
			}
		} catch (Exception e){
			LOGGER.error("Erro não tratado", e);
		}

	}

	private void get(String firstHeaderLine, HttpMethod httpMethod) throws IOException {
		String requestedPage = parseRequestedPage(firstHeaderLine);

		LOGGER.info("Feita uma requisição " + httpMethod + " da página " + requestedPage);

		ContentRetriever contentRetriever = new ContentRetriever(requestedPage);

		byte[] content = contentRetriever.getContent();

		HttpResponseCode httpResponseCode;

		if(content == null){
			httpResponseCode = HttpResponseCode.NOT_FOUND;
		} else {
			httpResponseCode = HttpResponseCode.OK;
		}

		writeHeaders(httpResponseCode);

		if(content != null){
			socket.getOutputStream().write(content);
		}
	}

	public void writeHeaders(final HttpResponseCode httpResponseCode) throws IOException {
		//TODO imprimir todos os headers
		socket.getOutputStream().write(("HTTP/1.1 " + httpResponseCode.getStatusCode() + "  " + httpResponseCode.getStatusText() + "\n").getBytes("UTF-8"));
		socket.getOutputStream().write("\n".getBytes());
	}

	private String parseRequestedPage(final String firstHeaderLine){
		return firstHeaderLine.substring(firstHeaderLine.indexOf(' ') + 1, firstHeaderLine.lastIndexOf(' '));
	}

}
