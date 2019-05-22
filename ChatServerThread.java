import java.net.*;
import java.io.*;

public class ChatServerThread extends Thread
{
	private ChatServer server = null;
	private Socket socket = null;
	private int ID = -1;
	private DataInputStream input = null;
	private DataOutputStream output = null;

	public ChatServerThread(ChatServer _server, Socket _socket)
	{
		super();
		server = _server;
		socket = _socket;
		ID = socket.getPort();
	}
	public void send(String msg)
	{
		try {
			output.writeUTF(msg);
			output.flush();
		} catch(IOException ioe) {
			System.out.println(ID + " ERROR sending: " + ioe.getMessage());
			server.remove(ID);
			stop();
		}
	}
	public int getID()
	{
		return ID;
	}
	public void run()
	{
		System.out.println("Server Thread " + ID + " running.");
		while(true) {
			try {
				server.handle(ID, input.readUTF());
			} catch(IOException ioe) {
				System.out.println(ID + " ERROR reading: " + ioe.getMessage());
				server.remove(ID);
				stop();
			}
		}
	}
	public void open() throws IOException
	{
		input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}
	public void close() throws IOException
	{
		if(socket != null)
			socket.close();
		if(input != null)
			input.close();
		if(output != null)
			output.close();
	}
}