package udp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import listener.OnMessageListener;
import listener.ServerListener;

public class Server extends Thread {
	private int serverPort = 70;
	private int bufferSize = 255;
	private boolean listening = false;
	private InetAddress inetAddress;

	private OnMessageListener mMessageListener = null;
	private ServerListener mServerListener = null;

	public Server() throws UnknownHostException {
		inetAddress = InetAddress.getLocalHost();
	}

	public Server(String ip, int port) throws UnknownHostException {
		this.serverPort = port;
		inetAddress = InetAddress.getByName(ip);
	}

	@Override
	public void run() {
		DatagramSocket socket = null;
		try {
			socket = createSocket();
		} catch (SocketException e) {
			System.err.println("Datagram couldnt create " + serverPort);
		} catch (UnknownHostException e) {

		}
		byte[] buffer = new byte[bufferSize];
		DatagramPacket packet = new DatagramPacket(buffer, 0, bufferSize);

		if (mServerListener != null) {
			mServerListener.serverStartListening();
		}

		while (listening) {
			try {
				socket.receive(packet);
			} catch (IOException e) {
				socket.disconnect();
				socket.close();

				if (mServerListener != null) {
					mServerListener.reConnect();
				}

				return;
			}

			String message = null;
			try {
				message = new String(buffer, "UTF-8");
			} catch (UnsupportedEncodingException e) {

			}

			if (mMessageListener != null && message != null) {
				mMessageListener.onMessageReceived(message);
			}

		}

	}

	protected DatagramSocket createSocket() throws SocketException, UnknownHostException {
		return new DatagramSocket(serverPort, inetAddress);
	}

	public void startServer() {
		this.listening = true;
		start();
	}

	public void stopServer() {
		this.listening = false;
		try {
			currentThread().join();
		} catch (InterruptedException e) {

		}
	}

	public void setMessageListener(OnMessageListener messageListener) {
		this.mMessageListener = messageListener;
	}

	public void setServerListener(ServerListener mServerListener) {
		this.mServerListener = mServerListener;
	}

}