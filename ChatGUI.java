import javax.swing.*;
import javax.swing.event.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatGUI extends JFrame implements Runnable, ActionListener, AutoCloseable
{
	private JTextField textInput;
    private JTextArea display;

	private String host;
	private String name;
	private static Socket socket;
	private int id;

	private boolean sendable = false;

	// private void connect(String host, )

	@Override
	public void run() {
		System.out.println("Runnable Created");
		try {
			if (socket instanceof Socket) {
				//socket = new Socket(host, Server.port);
				while (true) {

					//if (socket != null && socket.getInputStream() != null) {
						Scanner sockscan = new Scanner(socket.getInputStream());
						//if (sockscan.nextLine() instanceof String) {
						//if (sockscan.hasNextLine()) {
					String s = sockscan.nextLine();
					if (s.contains("Your Client ID")) {
						id = Integer.valueOf(s.replace("Your Client ID is: ",""));
					} else {
						//System.out.println(s);
						// output.println(s);
						println(s);
					}
						//sockscan.close();
					//}
				}
			}
		} catch(UnknownHostException uhe) {
			println("Host Unknown: " + uhe.getMessage());
		} catch(IOException ioe) {
			println("Unexpected exception: " + ioe.getMessage());
		}
	}

	/*@Override
	protected void finalize() {
		try {
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			output.println("CLOSE CLIENT ID: " + id);
			socket.close();
		} catch (Exception e) {
			System.out.println("Failed to close socket");
			System.exit(-1);
		}
	}*/

	@Override
	public void close()
	{
		try {
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			output.println("CLOSE CLIENT ID: " + id);
			socket.close();
		} catch (Exception e) {
			System.out.println("Failed to close socket");
			System.exit(-1);
		}
	}

	public ChatGUI(String host, String name)
	{
		this();
		this.host = host;
		this.name = name;
	}
	public ChatGUI()
	{//Creates the Frame
        super("Chat Frame");

        System.out.println("CHATGUI OBJECT CREATED");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Message");
        textInput = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        send.setActionCommand("Send");
        send.addActionListener(this);
        JButton draw = new JButton("Draw");

        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(textInput);
        panel.add(send);
        panel.add(draw);

        // Text Area at the Center
        display = new JTextArea();
        display.setEditable(false);

        // Scroll bar for display
        JScrollPane scroll = new JScrollPane(display);

        //Creating the MenuBar and adding components
        JMenuBar menubar = new JMenuBar();
        JMenu connections = new JMenu("Connections");

        JMenuItem connect = new JMenuItem(new AbstractAction("Connect") {
            public void actionPerformed(ActionEvent e) {
                println("Connecting...");
            }
        });
        connections.add(connect);
        JMenuItem quit = new JMenuItem(new AbstractAction("Quit") {
            public void actionPerformed(ActionEvent e) {
                println("Quitting...");
            }
        });
        connections.add(quit);

        menubar.add(connections);

        //Adding Components to the frame.
        Container content = getContentPane();
        content.add(BorderLayout.SOUTH, panel);
        content.add(BorderLayout.NORTH, menubar);
        // content.add(BorderLayout.CENTER, display);
        content.add(BorderLayout.CENTER, scroll);
        setVisible(true);		
	}
    public void println(String text)
    {
        display.append(text+"\n");
    }

    public boolean sendIt()
    {
        String textToSend = getTextInput();
        if(!("".equals(textToSend)) && sendable) {
    		textInput.setText("");
	        //println(name + ": " + textToSend);
	        sendable = false;
	        return true;
        }
        return false;
    }
    public String getTextInput()
    {
    	return textInput.getText();
    }
    public String getName()
    {
    	return name;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
    	String command = e.getActionCommand();
        if("Send".equals(command)) {
        	/*if(!connected) {
        		connected = true;
        		hostname = sendIt();
        		addTextToDisplay("Hostname: " + hostname);
        	} else if(!hasName) {
        		hasName = true;
        		username = sendIt();
        		addTextToDisplay("Username: " + username);
        	}*/

        	sendable = true;
        } else if("Quit".equals(e.getActionCommand())) {
        	println("Quitting...");
        	//add code to disconnect here
        }
    }
	public static void main(String[] args) throws IOException
	{
		/*ChatGUI chatBox = new ChatGUI();
		chatBox.addTextToDisplay("Enter Hostname address");
		while(!chatBox.getConnected()) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		chatBox.addTextToDisplay("Enter username");*/
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter a hostname Address:");
		String hostname = keyboard.nextLine();
		System.out.println("Enter a Username:");
		String username = keyboard.nextLine();

		keyboard.close();

		if(!(hostname instanceof String)) {
			System.out.println("Invalid Hostname Arguments");
			// connected = false;
			// hasName = false;
			return;
		}

		socket = new Socket(hostname, Server.port);
		PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

		ChatGUI chat = new ChatGUI(hostname, username);
		Thread t = new Thread(chat);
		t.start();

		while(true) {
			String send = chat.getTextInput();
			if(chat.sendIt()) {
				output.println(chat.getName() + ": " + send);
			}
		}

		/*socket = new Socket(hostname, Server.port);
		PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
		ChatGUI chat = new ChatGUI(hostname, username);
		Thread t = new Thread(chat);
		t.start();
		while(true) {
			if(true == sendable) {
				// System.out.println(chat.sendIt());
				// output.println(chat.sendIt());
				// output.flush();
				sendable = false;
				break;
			}
		}
		output.println("Broken while loop");*/
		//output.flush();
	}
}