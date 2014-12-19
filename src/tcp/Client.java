package tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private int serverPort = 10;
	private InetAddress inetAddress = null;
	private Socket mSocket = null;
	private BufferedWriter mWriter = null;

	public Client() throws IOException {
		inetAddress = InetAddress.getLocalHost();
		initSocket();
	}

	public Client(String ip, int port) throws IOException {
		this.serverPort = port;
		inetAddress = InetAddress.getByName(ip);
		initSocket(true);
	}

	private void initSocket(boolean b) throws IOException {
		mSocket = new Socket(inetAddress, serverPort);
		mWriter = new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream()));
	}
	
	private void initSocket() throws IOException {
		mSocket = new Socket(InetAddress.getLocalHost(), serverPort);
		mWriter = new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream()));
	}

	public void sendMessage(String message) throws IOException {
		if (mWriter == null || mSocket.isClosed()) {
			System.out.println("fail sendMessage");
			return;
		}
		mWriter.write(message);
		
		System.out.println(message);
		// mWriter.flush();
	}

	public void test() throws UnknownHostException, IOException {
		Socket socket = new Socket(inetAddress, serverPort);
		DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		outToServer.writeBytes("merhaba");
		System.out.println(inFromServer.readLine());
//		socket.close();
	}

	public void closeConnection() throws IOException {
		if (mSocket == null) {
			System.out.println("fail socket null");
			return;
		}

		mSocket.close();
	}
}
