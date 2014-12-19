package listener;

public interface ServerListener {
	public void serverStartListening();
	public void reConnect();
	public void handledException(Exception exception);
}
