package br.feevale.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;

/**
 * Created by jonasflesch on 4/7/15.
 */
public class HttpServer {

	public static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

	public static void main(String... args){
		try {
			LOGGER.info("Iniciando o servidor");
			ServerSocket serverSocket = new ServerSocket(8080);
			try {
				while(true){
					new RequestProcessor(serverSocket.accept()).start();
				}
			} finally {
				serverSocket.close();
			}

		} catch (Exception e){
			LOGGER.error("Erro não tratado", e);
		}

	}

}
