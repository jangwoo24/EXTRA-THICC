import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Panel extends JPanel
{
  // PROPERTIES
  private final int DEFAULT_WIDTH  = 400;
  private final int DEFAULT_HEIGHT = 400;
  private final Color BACK_COLOR   = Color.WHITE;

  private int x1, y1, x2, y2;

  private MouseHandler handler;
  private Graphics g;
  public Panel()
  {
    setBackground( BACK_COLOR );
    setPreferredSize( new Dimension( DEFAULT_WIDTH, DEFAULT_HEIGHT ) );

    handler  = new MouseHandler();

    this.addMouseListener( handler );
    this.addMouseMotionListener( handler );
  }

  public static void main(String[] args)
  {
    JFrame frame = new JFrame( "Panel" );
    frame.setDefaultCloseOperation(3);
    JButton erase = new JButton("Erase");
    //frame.add(erase);
    //erase.addActionListener();

    Panel  panel = new Panel();
    frame.add( panel );

    frame.pack();
    frame.setVisible( true );
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
  }

  private void setUpDrawingGraphics()
  {
    g = getGraphics();
  }

  private class MouseHandler extends MouseAdapter
  {
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
