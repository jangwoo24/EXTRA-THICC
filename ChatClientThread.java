import java.net.*;
import java.io.*;

public class ChatClientThread extends Thread
{
	private Socket socket = null;
	private ChatClient client = null;
	private DataInputStream input = null;

	public ChatClientThread(ChatClient _client, Socket _socket)
	{
		client = _client;
		socket = _socket;
		open();
		start();
	}
	public void open()
	{
		try {
			input = new DataInputStream(socket.getInputStream());
		} catch(IOException ioe) {
			System.out.println("Error getting input stream: " + ioe);
			//client.stop();
		}
	}
	public void close()
	{
		try {
			if(input != null)
				input.close();
		} catch(IOException ioe) {
			System.out.println("Error closing input stream: " + ioe);
		}
	}
	public void run()
	{
		while(true) {
			try {
				client.handle(input.readUTF());
			} catch(IOException ioe) {
				System.out.println("Listening eror: " + ioe.getMessage());
				//client.stop();
			}
		}
	}
}