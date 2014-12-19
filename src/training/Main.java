package training;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import tcp.Client;
import tcp.Server;

public class Main {
	public static void main(String[] args) {

		Server server = new Server();
		server.start();

		try {
			Client client = new Client("30.10.22.84",10 );
			client.test();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Scanner scanner = new Scanner(System.in);
		// String text=scanner.next();
		// Client client = null;
		// try {
		// client = new Client();
		// System.out.println("2");
		// client.sendMessage(text);
		// System.out.println("3");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

}
