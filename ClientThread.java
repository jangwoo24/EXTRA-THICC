import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JTextArea;

public class ClientThread implements Runnable {

	private Client client;
	private JTextArea text;
	private boolean open;
	private Server server;
	
	public ClientThread (Client client, JTextArea text, Server server) {
		this.client = client;
		this.text = text;
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
				text.append(s + "\n");
			} catch (Exception e) {
				e.printStackTrace();
				finalize();
			}
		}
	}
	
	protected void finalize() {
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
