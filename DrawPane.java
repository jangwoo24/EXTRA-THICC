import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawPane extends JFrame
{
  // PROPERTIES
  private final int DEFAULT_WIDTH  = 400;
  private final int DEFAULT_HEIGHT = 400;
  private final Color BACK_COLOR   = Color.WHITE;

  private MouseHandler handler;
  private Graphics g;

  public JPanel panel;

  public DrawPane()
  {
    super("Draw Pane");
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    //JButton erase = new JButton("Erase");

    panel = new JPanel() {
      public void paintComponent(Graphics g)
      {
        super.paintComponent(g);
      }
    };

    panel.setBackground( BACK_COLOR );
    panel.setPreferredSize( new Dimension( DEFAULT_WIDTH, DEFAULT_HEIGHT ) );

    handler  = new MouseHandler();

    panel.addMouseListener( handler );
    panel.addMouseMotionListener( handler );

    add( panel );

    pack();
    setVisible( true );
  }

  public static void main(String[] args)
  {
    DrawPane dp = new DrawPane();
  }

  

  private void setUpDrawingGraphics()
  {
    g = panel.getGraphics();
  }

  private class MouseHandler extends MouseAdapter
  {
    private int x1, y1, x2, y2;
    public void mousePressed( MouseEvent e )
    {
      x1 = e.getX();
      y1 = e.getY();

      setUpDrawingGraphics();

      x2=x1;
      y2=y1;
    }
    
    public void mouseDragged( MouseEvent e )
    {
      x1 = e.getX();
      y1 = e.getY();

      g.drawLine(x1,y1,x2,y2);

      x2=x1;
      y2=y1;
    }
  }
}
