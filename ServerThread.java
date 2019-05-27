import javax.swing.*;
import java.awt.*;

public class ServerThread implements Runnable
{
	private Server server;

	public ServerThread()
	{
		server = new Server();
	}

	@Override
	public void run()
	{
		server.listen();
	}

	public static void main(String[] args)
	{
		ServerThread run = new ServerThread();
		Thread thread = new Thread(run);
		thread.start();
	}
}