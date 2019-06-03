import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Random;


public class ChatGUI extends JFrame implements Runnable, ActionListener, AutoCloseable
{
	private JTextField textInput;
    private EmoteChanger display;

    private JMenuItem connect;

	private String host;
	private String name;
	private static Socket socket;
	private int id;

    private Thread t;
    private PrintWriter output;

	// private boolean sendable = false;

	private SimpleAttributeSet keyword;
	
	private Color color;
	/////////////////////////////////////////////////////////////////////////need help integrating 
	/*int r = (int)(Math.random()*256);
	int g = (int)(Math.random()*256);
	int b = (int)(Math.random()*256);

	Color color = new Color(r, g, b);*/ //random color, but can be bright or dull
	
	////////////////////////////////////////////////////////////////////////////
	

	@Override
	public void run() {
		System.out.println("Runnable Created");
		try {
			if (socket instanceof Socket) {
				while (true) {
					Scanner sockscan = new Scanner(socket.getInputStream());
					String s = sockscan.nextLine();
					if (s.contains("Your Client ID")) {
						id = Integer.valueOf(s.replace("Your Client ID is: ",""));
					} else {
						println(s);
					}
				}
			}
		} catch(UnknownHostException uhe) {
			println("Host Unknown: " + uhe.getMessage());
		} catch(IOException ioe) {
			println("Unexpected exception: " + ioe.getMessage());
		}
	}


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

	public ChatGUI(String host, String name) throws IOException
	{
		this(host, name, Color.black);
	}
	public ChatGUI(String host, String name, Color c) throws IOException
	{
		this();
		this.host = host;
		this.name = name;
		setColor(c);

	}
	public ChatGUI() throws IOException
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
        send.setActionCommand("SEND");
        send.addActionListener(this);
        JButton draw = new JButton("Draw");
        draw.setActionCommand("DRAW");
        draw.addActionListener(this);

        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(textInput);
        panel.add(send);
        panel.add(draw);

        // Text Area at the Center
        // display = new JTextPane();
        display = new EmoteChanger();
        display.setEditorKit(new StyledEditorKit());
        display.initListener();
        display.setEditable(false);

        keyword = new SimpleAttributeSet();
        StyleConstants.setForeground(keyword, Color.black);
        // Scroll bar for display
        JScrollPane scroll = new JScrollPane(display);

        //Creating the MenuBar and adding components
        JMenuBar menubar = new JMenuBar();

        JMenu connections = new JMenu("Connections");

        connect = new JMenuItem("Connect");
        connect.setActionCommand("CONNECT");
        connect.addActionListener(this);
        connections.add(connect);

        /*JMenuItem quit = new JMenuItem("Quit");
        quit.setActionCommand("QUIT");
        quit.addActionListener(this);
        connections.add(quit);*/

        JMenu colorPicker = new JMenu("Colors");

        JMenuItem redColor = new JMenuItem("RED");
        redColor.setOpaque(true);
        // redColor.setForeground(Color.red);
        redColor.setBackground(Color.red);
        redColor.setActionCommand("RED");
        redColor.addActionListener(this);
        colorPicker.add(redColor);

        JMenuItem greenColor = new JMenuItem("GREEN");
        greenColor.setOpaque(true);
        // redColor.setForeground(Color.red);
        greenColor.setBackground(Color.green);
        greenColor.setActionCommand("GREEN");
        greenColor.addActionListener(this);
        colorPicker.add(greenColor);

        JMenuItem blueColor = new JMenuItem("BLUE");
        blueColor.setOpaque(true);
        // redColor.setForeground(Color.red);
        blueColor.setBackground(Color.blue);
        blueColor.setActionCommand("BLUE");
        blueColor.addActionListener(this);
        colorPicker.add(blueColor);

        JMenuItem blackColor = new JMenuItem("BLACK");
        blackColor.setOpaque(true);
        // redColor.setForeground(Color.red);
        blackColor.setBackground(Color.black);
        blackColor.setActionCommand("BLACK");
        blackColor.addActionListener(this);
        colorPicker.add(blackColor);

        menubar.add(connections);
        menubar.add(colorPicker);

        //Adding Components to the frame.
        Container content = getContentPane();
        content.add(BorderLayout.SOUTH, panel);
        content.add(BorderLayout.NORTH, menubar);
        content.add(BorderLayout.CENTER, scroll);
        setVisible(true);

        setName(JOptionPane.showInputDialog(this, "What do your want your username to be?"));
        println("Your username is " + getName());
	}
    public void println(String text)
    {
        try {
			StyledDocument doc = display.getStyledDocument();
	    	// StyleConstants.setForeground(keyword, Color.black);
	    	int colonIndex = text.indexOf(":") + 1;
	    	doc.insertString(doc.getLength(), text.substring(0, colonIndex), null);
			doc.insertString(doc.getLength(), text.substring(colonIndex) + "\n", keyword);
		} catch(BadLocationException exc) {
			exc.printStackTrace();
		}
    }

    /*public boolean sendIt()
    {
        String textToSend = getTextInput();
        if(!("".equals(textToSend))) {
    		textInput.setText("");
	        return true;
        }
        return false;
    }*/
    public String getTextInput()
    {
    	return textInput.getText();
    }
    public String getName()
    {
    	return name;
    }

    public String getColorRGB()
    {
    	String output = "";
    	int RGB = getColor().getRGB();
    	int alpha = (RGB >> 24) & 0xff;
	    int red = (RGB >> 16) & 0xff;
	    int green = (RGB >> 8) & 0xff;
	    int blue = (RGB) & 0xff;
	    return alpha + " " + red + " " + green + " " + blue;
    }
    public Color getColor()
    {
    	return color;
    }
    public void setColor(Color c)
    {
    	color = c;
    	StyleConstants.setForeground(keyword, color);
    }

    public String getHost()
    {
        return host;
    }
    public void setHost(String hostname)
    {
        host = hostname;
    }
    public void setName(String username)
    {
        name = username;
    }
    public void openDrawPane()
    {
    	DrawPane dp = new DrawPane();
    }

    /*@Override
    public void start()
    {

        while(true) {
            //String send = getTextInput();
            if (sendIt()) {
                // output.println(getName() + ": " + send);
                output.println(getName() + ": " + getTextInput());
            }
        }
    }*/
    
    public void connect(String hostname, String username) throws IOException
    {
        socket = new Socket(hostname, Server.port);
        output = new PrintWriter(socket.getOutputStream(), true);

        // ChatGUI chat = new ChatGUI(getHost(), getName());
        t = new Thread(this);
        t.start();



        /*Thread outputThread = new Thread(new Runnable() {
            public void run() {
                while(true) {
                    String send = getTextInput();
                    if (sendIt()) {
                        output.println(username + ": " + send);
                    }
                }
            }
        });
        inputThread.start();*/
        
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
    	String command = e.getActionCommand();
        if ("SEND".equals(command)) {
        	/*sendable = true;
            sendIt()*/
            // System.out.println("Sending...");
            if(!("".equals(getTextInput()))) {
                try {
                    output.println(getName() + ": " + getTextInput());
                    textInput.setText("");
                } catch(NullPointerException npe) {
                    println("Silly " + getName() + ", you can't send messages to nobody!");
                }
            }
            // textInput.setText("");
        } else if ("DRAW".equals(command)) {
        	openDrawPane();
        } else if ("CONNECT".equals(command)) {
        	// println("Connecting...");
        	setHost(JOptionPane.showInputDialog(this, "Where do you want to connect?"));
            
            // println("host is " + getHost());
            try {
                connect(getHost(), getName());
                connect.setText("Quit");
                connect.setActionCommand("QUIT");
            } catch (IOException ioe) {
                println("Failed to connect to " + getHost());
            }
        } else if ("QUIT".equals(command)) {
        	println("Quitting...");
        	setColor(Color.blue);
            System.exit(0);
        } else  { // colors
        	if("RED".equals(command))
        		setColor(Color.red);
        	else if("GREEN".equals(command))
        		setColor(Color.green);
        	else if("BLUE".equals(command))
        		setColor(Color.blue);
        	else if("BLACK".equals(command))
        		setColor(Color.black);
        	
        	println("Text color set to: " + command);
        }
    }
	public static void main(String[] args) throws IOException
	{
        ChatGUI chat = new ChatGUI();
		/*Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter a hostname Address:");
		String hostname = keyboard.nextLine();
		
		System.out.println("Enter a Username:");
		String username = keyboard.nextLine();

		keyboard.close();

		if(!(hostname instanceof String)) {
			System.out.println("Invalid Hostname Arguments");
			return;
		}

		socket = new Socket(hostname, Server.port);
		PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

		ChatGUI chat = new ChatGUI(hostname, username);
		Thread t = new Thread(chat);
		t.start();

		while(true) {
			String send = chat.getTextInput();
			if (chat.sendIt()) {
				output.println(chat.getName() + ": " + send);
			}
		}*/
	}
}
