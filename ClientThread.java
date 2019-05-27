import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.SocketException;

public class ClientThread implements Runnable, AutoCloseable {

	private Client client;
	private boolean open;
	private Server server;
	
	public ClientThread (Client client, Server server) {
		this.client = client;
		open = true;
		this.server = server;
	}
	
	public Client getClient() {
		return client;
	}

	public boolean isOpen() {
		return open;
	}
	
	@Override
	public void run() {
		String s;
		while (open) {
			BufferedReader input = null;
			PrintWriter output = null;
			try {
				input = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
				output = new PrintWriter(client.getSocket().getOutputStream(), true);
				s = input.readLine();
				output.println(s);
				server.globalMessage(s, this, open);
			} catch (SocketException se) {
				System.out.println("Client closed with ID: " + client.getID());
				close();
			} catch (Exception e) {
				e.printStackTrace();
				close();
			}
		}
	}
	
	public void close() {
		if (open) {
			try {
				open = false;
				System.out.println(">> Closed Socket || Client ID: " + client.getID());
				client.getSocket().close();
			} catch (Exception e) {
				System.out.println("Failed to close socket || Client ID: " + client.getID());
				System.exit(-1);
			}
		}
	}
	
}
