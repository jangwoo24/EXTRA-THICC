import javax.swing.*;
import javax.swing.event.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;

class GUI extends JFrame
{
    public GUI()
    {
        //Creates the Frame
        super("Chat Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Message");
        JTextField textInput = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton draw = new JButton("Draw");

        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(textInput);
        panel.add(send);
        panel.add(draw);

        // Text Area at the Center
        JTextArea display = new JTextArea();
        display.setEditable(false);

        //Creating the MenuBar and adding components
        JMenuBar menubar = new JMenuBar();
        JMenu connections = new JMenu("Connections");

        JMenuItem connect = new JMenuItem(new AbstractAction("Connect") {
            public void actionPerformed(ActionEvent e) {
                display.append("Connecting...\n");
            }
        });
        connections.add(connect);
        JMenuItem quit = new JMenuItem(new AbstractAction("Quit") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Quit clicked");
            }
        });
        connections.add(quit);

        menubar.add(connections);

        //Adding Components to the frame.
        Container content = getContentPane();
        content.add(BorderLayout.SOUTH, panel);
        content.add(BorderLayout.NORTH, menubar);
        content.add(BorderLayout.CENTER, display);
        setVisible(true);
    }
    public static void main(String args[])
    {
        GUI instantMessage = new GUI();
        
    }
}
