package gui;

import java.util.Scanner;

import test.UdpTest;
import listener.OnMessageListener;
import manager.SocketManager;
import manager.SocketManager.SocketType;

public class Main implements OnMessageListener {

	public static void main(String[] args) {

		SocketManager socketManager = SocketManager.getInstance();
		socketManager.setMessageListener(new Main());

		socketManager.startServer(SocketType.UDP);
		
		socketManager.sendMessage("deneme");
		UdpTest udpTest = new UdpTest(4);
		
		
	}

	@Override
	public void onMessageReceived(String data) {
		System.out.println("Reached msg:" + data);
	}
}
