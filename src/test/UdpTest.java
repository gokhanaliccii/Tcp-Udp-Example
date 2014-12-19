package test;

import manager.SocketManager;

public class UdpTest {

	public UdpTest(int testQuantity) {

		for (int i = 0; i < testQuantity; i++) {
			UdpDummyClient client = new UdpDummyClient();
			client.start();

		}

	}

	private class UdpDummyClient extends Thread {

		private static final long LATENCY = 2000;
		String message;

		public UdpDummyClient() {
			message = "Udp Random text" + System.currentTimeMillis();
		}

		public UdpDummyClient(String msg) {
			this.message = msg;
		}

		@Override
		public void run() {

			try {
				sleep(LATENCY);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			SocketManager manager = SocketManager.getInstance();
			manager.sendMessage(message);

		}
	}

}
