import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

	public static final int port = 4321;
	private ServerSocket server;
	private Socket client;
	private int currentId = 0;
	private InetAddress address;
	private List<ClientThread> clients;
	
	public Server()
	{
		Thread thread = new Thread(this);
		thread.start();
	}
	public ServerSocket getServer() {
		return server;
	}
	
	public Socket getClient() {
		return client;
	}
	
	public InetAddress getAddress() {
		return address;
	}
	
	public void listen() {
		clients = new ArrayList<ClientThread>();
		try {
			System.out.println("Listening for clients.");
			server = new ServerSocket(port);
			address = server.getInetAddress();
		} catch (Exception e) {
			System.out.println("Listening failed on Port " + port);
			System.exit(-1);
			//e.printStackTrace();
		}
		while (true) {
			ClientThread w;
			try {
				Socket socket = server.accept();
				w = new ClientThread(new Client(socket, currentId), this);
				clients.add(w);
				Thread thread = new Thread(w);
				thread.start();
				System.out.println(">> Created new Client Thread (" + w.getClient().getID() + ")");
				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
				output.println("You have joined the Server! Welcome!!!");
				output.println("Your Client ID is: " + currentId);
				currentId++;
			} catch (Exception e) {
				System.out.println("ClientThread could not be initialized on " + port);
				System.exit(-1);
			}
		}
	}
	
	public void globalMessage(String s, ClientThread exclude, boolean open) {
		if (open) {
			PrintWriter outputCT = null;
			//if (s == null) {
			//	return;
			//}
			for (ClientThread ct : clients) {
				if (!ct.equals(exclude) && ct.isOpen()) {
					try {
						//if (ct.getClient().getSocket() != null && ct.getClient().getSocket().getOutputStream() != null) {
						outputCT = new PrintWriter(ct.getClient().getSocket().getOutputStream(), true);
						outputCT.println(s);
						System.out.println(s);

						//}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public void run()
	{
		listen();
	}

	public static void main(String[] args)
	{
		Server server = new Server();
	}
}
