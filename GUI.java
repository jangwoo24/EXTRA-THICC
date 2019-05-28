import javax.swing.*;
import javax.swing.event.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;

class GUI extends JFrame //implements ActionListener
{
    private JTextField textInput;
    private JTextArea display;
    public GUI()
    {
        //Creates the Frame
        super("Chat Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); //panel isn't visible in output
        JLabel label = new JLabel("Enter Message");
        textInput = new JTextField(10); // accepts up to 10 characters
        JButton send = new JButton("Send");
        //send.addActionListener(new ActionListener(this));
        //JButton draw = new JButton("Draw"); <-- will get added if Pictionary idea gets implemented

        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(textInput);
        panel.add(send);
        //panel.add(draw);

        // Text Area at the Center
        display = new JTextArea();
        display.setEditable(false);

        //Creating the MenuBar and adding components
        JMenuBar menubar = new JMenuBar();
        JMenu connections = new JMenu("Connections");

        JMenuItem connect = new JMenuItem(new AbstractAction("Connect") {
            public void actionPerformed(ActionEvent e) {
                addTextToDisplay("Connecting...");
            }
        });
        connections.add(connect);
        JMenuItem quit = new JMenuItem(new AbstractAction("Quit") {
            public void actionPerformed(ActionEvent e) {
                addTextToDisplay("Quitting...");
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
    public void addTextToDisplay(String text)
    {
        display.append(text+"\n");
    }
    public void sendIt()
    {
        String textToSend = textInput.getText();
        textInput.setText("");
        addTextToDisplay(textToSend);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if("Send".equals(e.getActionCommand())) {

        }
    }

    public static void main(String args[])
    {
        GUI instantMessage = new GUI();
        
    }
}
