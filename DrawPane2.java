import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class DrawPane2 extends JFrame {

  private final int DEFAULT_WIDTH = 400;
  private final int DEFAULT_HEIGHT = 400;
  private final Color BACK_COLOR = Color.WHITE;

  private Color brush;
  private MouseHandler handler;
  public BufferedImage image;
  private Graphics g;

  public JPanel panel;

  public DrawPane2() {
    super("Draw Pane");
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    panel = new JPanel() 
    {
      public void paintComponent(Graphics g) 
      {
        super.paintComponent(g);
      }
    };

    image = new BufferedImage(DEFAULT_WIDTH,DEFAULT_HEIGHT, BufferedImage.TYPE_INT_RGB);
    handler = new MouseHandler();
    JMenuBar menubar = new JMenuBar();

    JButton erase = new JButton("Erase");
    erase.setActionCommand("ERASE");
    menubar.add(erase);

    JMenu colorPicker = new JMenu("Colors");

    JMenuItem redColor = new JMenuItem("RED");
    redColor.setOpaque(true);
    redColor.setBackground(Color.red);
    redColor.setActionCommand("RED");
    redColor.addActionListener(handler);
    colorPicker.add(redColor);

    JMenuItem greenColor = new JMenuItem("GREEN");
    greenColor.setOpaque(true);
    greenColor.setBackground(Color.green);
    greenColor.setActionCommand("GREEN");
    greenColor.addActionListener(handler);
    colorPicker.add(greenColor);

    JMenuItem blueColor = new JMenuItem("BLUE");
    blueColor.setOpaque(true);
    blueColor.setBackground(Color.blue);
    blueColor.setActionCommand("BLUE");
    blueColor.addActionListener(handler);
    colorPicker.add(blueColor);

    JMenuItem blackColor = new JMenuItem("BLACK");
    blackColor.setOpaque(true);
    blackColor.setBackground(Color.black);
    blackColor.setActionCommand("BLACK");
    blackColor.addActionListener(handler);
    colorPicker.add(blackColor);
    menubar.add(colorPicker);

    panel.add(menubar);

    panel.setBackground(BACK_COLOR);
    panel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

    panel.addMouseListener(handler);
    panel.addMouseMotionListener(handler);

    add(panel);
    pack();
    setVisible(true);
  }

  public static void main(String[] args) {
    DrawPane dp = new DrawPane();
  }

  private void setUpDrawingGraphics() {
    g = getGraphics();
  }

  public int getWidth()
  {
    return DEFAULT_WIDTH;
  }

  public int getHeight()
  {
    return DEFAULT_HEIGHT;
  }

  public String help()
  {
    String result = "";
    int rgb;
    for(int i = 0; i < getWidth(); i++)
    {
      for(int j = 0; j < getHeight(); j++)
      {
      }
    }
    System.out.println(result);
    return result;
  }

  private class MouseHandler extends MouseAdapter implements ActionListener {

    private int x1, y1, x2, y2;

    public void mousePressed(MouseEvent e) {

      x1 = e.getX();
      y1 = e.getY();

      g.drawRect(x1, y1, 1, 1);

      setUpDrawingGraphics();

      x2 = x1;
      y2 = y1;
    }

    public void mouseDragged(MouseEvent e) {
      x1 = e.getX();
      y1 = e.getY();

      g.drawLine(x1,y1,x2,y2);

      x2 = x1;
      y2 = y1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      String command = e.getActionCommand();
    }

  }

}
