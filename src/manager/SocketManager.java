package manager;

import java.io.IOException;
import java.net.UnknownHostException;

import listener.OnMessageListener;
import tcp.Server;
import udp.Client;

public class SocketManager {

	private static SocketManager socketManager = null;
	private OnMessageListener mListener;
	private SocketType currentSocketType;
	private udp.Server udpServer;
	private tcp.Server tcpServer;

	public enum SocketType {
		TCP(10), UDP(20), UNDEFINED(-1);
		private int type;

		private SocketType(int socketType) {
			this.type = socketType;
		}

		public int getType() {
			return type;
		}

		public static SocketType getSocketType(int socketType) {

			if (TCP.getType() == socketType) {
				return TCP;
			}

			if (UDP.getType() == socketType) {
				return TCP;
			}

			return UNDEFINED;
		}

	}

	private SocketManager() {

	}

	public static synchronized SocketManager getInstance() {

		if (socketManager == null) {
			socketManager = new SocketManager();
		}

		return socketManager;
	}

	public void startServer(SocketType type) {

		currentSocketType = type;

		switch (currentSocketType) {

		case TCP:
			startTcpServer();
			break;
		case UDP:
			startUdpServer();

			break;
		default:
			System.out.println("UNDEFINED");
			break;
		}
	}

	private void startTcpServer() {
		stopSocket();

		tcpServer = new Server();
		tcpServer.startServer();
	}

	private void stopTcpServer() {
		if (tcpServer == null) {
			return;
		}

		tcpServer.stopServer();
		tcpServer = null;
	}

	private void startUdpServer() {
		stopSocket();

		try {
			udpServer = new udp.Server();
			udpServer.startServer();
		} catch (UnknownHostException e) {
			System.err.println("FAIL");
			return;
		}

		udpServer.setMessageListener(mListener);

	}

	private void stopUdpServer() {
		if (udpServer != null) {
			udpServer.stopServer();
			udpServer = null;
		}
	}

	public void stopSocket() {
		switch (currentSocketType) {
		case TCP:
			stopTcpServer();
			break;

		case UDP:
			stopUdpServer();
			break;

		default:
			System.out.println("UNDEFINED");
			break;
		}

	}

	public void setMessageListener(OnMessageListener listener) {
		this.mListener = listener;
	}

	public void sendMessage(String msg) {
		switch (currentSocketType) {
		case TCP:
			sendTcpMessage(msg);
			break;
		case UDP:
			sendUdpMessage(msg);
			break;

		default:
			System.out.println("UNDEFINED");
			break;
		}
	}

	public void sendMessage(String msg, String ip, int port) {
		switch (currentSocketType) {
		case TCP:
			sendTcpMessage(msg, ip, port);
			break;
		case UDP:
			sendUdpMessage(msg, ip, port);
			break;

		default:
			System.out.println("UNDEFINED");
			break;
		}
	}

	private void sendUdpMessage(String msg) {

		udp.Client client = new Client();
		client.sendMessage(msg);

	}

	private void sendUdpMessage(String msg, String ip, int port) {

		udp.Client client = new udp.Client();
		client.sendMessage(msg, ip, port);

	}

	private void sendTcpMessage(String msg) {
		try {
			tcp.Client client = new tcp.Client();
			client.sendMessage(msg);
		} catch (IOException e) {
			System.out.println("ERR tcp send message");
		}

	}

	private void sendTcpMessage(String msg, String ip, int port) {
		try {
			tcp.Client client = new tcp.Client(ip, port);
			client.sendMessage(msg);
		} catch (IOException e) {
			System.out.println("ERR tcp send message");
		}

	}

}
