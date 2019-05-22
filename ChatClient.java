import java.net.*;
import java.io.*;
import javax.swing.*;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
// import java.awt.Event;

public class ChatClient extends JFrame
{
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	private ChatClientThread client = null;
	private JTextArea display = new JTextArea();
	private JTextField textInput = new JTextField();
	private JButton	send = new JButton("Send"),
					connect = new JButton("Connect"),
					quit = new JButton("Bye");
	private String serverName = "localhost";
	private int serverPort = 4200;

	public ChatClient()
	{
		send.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				send();
				textInput.requestFocus();
			}
		});
		connect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				connect(serverName, serverPort);
			}
		});
		quit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				textInput.setText(".bye");
				send();
				quit.disable();
				send.disable();
				connect.enable();
			}
		});

		JPanel keys = new JPanel();
			keys.setLayout(new GridLayout(1,2));
			keys.add(quit);
			keys.add(connect);
		JPanel south = new JPanel();
			south.setLayout(new BorderLayout());
			south.add("West", keys);
			south.add("Center", textInput);
			south.add("East", send);
		JLabel title = new JLabel("Simple Chat Client GUI", SwingConstants.CENTER);
			title.setFont(new Font("Helvetica", Font.BOLD, 14));
		setLayout(new BorderLayout());
		add("North", title);
		add("Center", display);
		add("South", south);
		quit.disable();
		send.disable();
		getParameters();
	}
	/*public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Send")) {
			send();
			textInput.requestFocus();
		} else if(e.getActionCommand().equals("Connect")) {
			connect(serverName, serverPort);
		} else if(e.getActionCommand().equals("Quit")) {
			textInput.setText(".bye");
			send();
			quit.disable();
			send.disable();
			connect.enable();
		}
	}*/
	/*public boolean action(ActionEvent e, Object o)
	{
		if(e.target == quit) {
			textInput.setText(".bye");
			send();
			quit.disable();
			send.disable();
			connect.enable();
		} else if(e.target == send) {
			send();
			textInput.requestFocus();
		}
		return true;
	}*/
	public void connect(String serverName, int serverPort)
	{
		println("Establishing connection. Please wait...");
		try {
			socket = new Socket(serverName, serverPort);
			println("Connected: " + socket);
			open();
			send.enable();
			connect.disable();
			quit.enable();
		} catch(UnknownHostException uhe) {
			println("Host unknown: " + uhe.getMessage());
		} catch(IOException ioe) {
			println("Unexpected exception: " + ioe.getMessage());
		}
	}
	private void send()
	{
		try {
			output.writeUTF(textInput.getText());
			output.flush();
			textInput.setText("");
		} catch(IOException ioe) {
			println("Sending error: " + ioe.getMessage());
			close();
		}
	}
	public void handle(String msg)
	{
		if(msg.equals(".bye")) {
			println("Good bye. Press RETURN to exit...");
			close();
		} else {
			println(msg);
		}
	}
	public void open()
	{
		try {
			output = new DataOutputStream(socket.getOutputStream());
			client = new ChatClientThread(this, socket);
		} catch(IOException ioe) {
			println("Error opening output stream: " + ioe);
		}
	}
	public void close()
	{
		try {
			if(output != null)
				output.close();
			if(socket != null)
				socket.close();
		} catch(IOException ioe) {
			println("Error closing...");
		}
		client.close();
		client.stop();
	}
	private void println(String msg)
	{
		display.append(msg + "\n");
	}
	public void getParameters()
	{
		serverName = //getParameter("host");
						"localhost";
		serverPort = //Integer.parseInt(getParameter("port"));
						4200;
	}
	public static void main(String[] args)
	{
		ChatClient client = new ChatClient();
		client.setExtendedState(JFrame.MAXIMIZED_BOTH);
		client.setVisible(true);
	}
}