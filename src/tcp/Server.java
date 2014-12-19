package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import listener.OnMessageListener;
import listener.ServerListener;

public class Server extends Thread {

	private static int port = 10;
	private String ip = null;
	private boolean running = false;
	private OnMessageListener mMessageListener = null;
	private ServerListener mServerListener = null;

	public Server() {

	}

	public Server(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			if (mServerListener != null) {
				mServerListener.handledException(e);
			}
		}

		while (running) {

			try {

				System.out.println("server adim0");
				Socket socket = serverSocket.accept();
				System.out.println("server adim1.1");
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
				System.out.println("server adim1");
				outToClient.writeBytes("ACK");
				System.out.println("server adim2");
				System.out.println(inFromClient.readLine());
				String msg = null;
				System.out.println("server adim3");
				while ((msg = inFromClient.readLine()) != null) {
					System.out.println(msg);
				}
				System.out.println("server adim4");

				if (mMessageListener != null) {
					mMessageListener.onMessageReceived(msg);
				}

				socket.close();
			} catch (IOException e) {
				System.out.println("ServerSocket accpet fail");
				if (mServerListener != null) {
					mServerListener.handledException(e);
				}
			}

		}

	}

	public void stopServer() {
		running = false;
		try {
			join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void startServer() {
		running = true;
		start();
	}

	public void setMessageListener(OnMessageListener messageListener) {
		this.mMessageListener = messageListener;
	}

	public void setServerListener(ServerListener mServerListener) {
		this.mServerListener = mServerListener;
	}

}
