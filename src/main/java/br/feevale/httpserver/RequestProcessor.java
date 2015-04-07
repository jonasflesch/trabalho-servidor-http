package br.feevale.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

				while (true) {
					String input = in.readLine();
					if (input == null || input.isEmpty()) {
						break;
					}
					LOGGER.info(input);
					//out.println(input.toUpperCase());
				}

				out.println("Só alegria");
			} finally {
				socket.close();
			}
		} catch (Exception e){
			LOGGER.error("Erro não tratado", e);
		}

	}
}
