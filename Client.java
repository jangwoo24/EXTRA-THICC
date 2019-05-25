import java.net.Socket;

public class Client {

	private Socket socket;
	private int id;
	
	public Client(Socket socket, int id) {
		this.socket = socket;
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
}
