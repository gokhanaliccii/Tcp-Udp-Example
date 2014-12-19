package udp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	public void sendMessage(String message) {
		if (message == null) {
			return;
		}

		byte[] a = null;
		try {
			a = message.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}

		DatagramSocket socket = null;
		InetAddress inet = null;
		try {
			inet = InetAddress.getLocalHost();
			socket = new DatagramSocket();
		} catch (SocketException e1) {
			System.out.println("socket null");
			return;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("unknown");
		}
		DatagramPacket packet = null;
		packet = new DatagramPacket(a, a.length, inet, 70);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.out.println("Message [" + message + "] couldnt send");
		}
		socket.close();
	}

	public void sendMessage(String message,String ip,int port) {
		if (message == null) {
			return;
		}

		byte[] a = null;
		try {
			a = message.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}

		DatagramSocket socket = null;
		InetAddress inet = null;
		try {
			inet = InetAddress.getByName(ip);
			socket = new DatagramSocket();
		} catch (SocketException e1) {
			System.out.println("socket null");
			return;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("unknown");
		}
		DatagramPacket packet = null;
		packet = new DatagramPacket(a, a.length, inet, 70);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.out.println("Message [" + message + "] couldnt send");
		}
		socket.close();
	}

}
