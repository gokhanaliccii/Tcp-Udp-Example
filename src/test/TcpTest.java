package test;

import manager.SocketManager;

public class TcpTest {

	public TcpTest(int testQuantity) {

		for (int i = 0; i < testQuantity; i++) {
			TcpDummyClient client = new TcpDummyClient();
			client.start();

		}

	}

	private class TcpDummyClient extends Thread {

		private static final long LATENCY = 2000;
		String message;

		public TcpDummyClient() {
			message = "Tcp Random text" + System.currentTimeMillis();
		}

		public TcpDummyClient(String msg) {
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
