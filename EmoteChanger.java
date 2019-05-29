import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
 
public class EmoteChanger extends JTextPane {
    String[] emoticons = {":)", ":D", ":(", ">:(", "<3"};
    /*static ImageIcon SMILE_IMG = createImage(":)");
    static ImageIcon ANGER_IMG = createImage(">:(");
    static ImageIcon FROWN_IMG = createImage(":(");
    static ImageIcon[] emojis = {SMILE_IMG, ANGER_IMG, FROWN_IMG};*/
    static List<ImageIcon> emojis = new ArrayList<>();
 
    public static void main(String[] args) {
        JFrame frame = new JFrame("Autoreplace :) with Smiles images example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final EmoteChanger app = new EmoteChanger();
        app.setEditorKit(new StyledEditorKit());
        app.initListener();
        JScrollPane scroll = new JScrollPane(app);
        frame.getContentPane().add(scroll);
 
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 
    public EmoteChanger() {
        super();
        for(String emote: emoticons) {
        	emojis.add(createImage(emote));
        }
    }
 
    protected void initListener() {
        getDocument().addDocumentListener(new DocumentListener(){
            public void insertUpdate(DocumentEvent event) {
                final DocumentEvent e=event;
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (e.getDocument() instanceof StyledDocument) {
                            try {
                                StyledDocument doc=(StyledDocument)e.getDocument();
                                int start= Utilities.getRowStart(EmoteChanger.this,Math.max(0,e.getOffset()-1));
                                int end=Utilities.getWordStart(EmoteChanger.this,e.getOffset()+e.getLength());
                                String text=doc.getText(start, end-start);

                                for(int index = 0; index < emoticons.length; index++) {
	                                int i=text.indexOf(emoticons[index]);
	                                while(i>=0) {
	                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
	                                       doc.getCharacterElement(start+i).getAttributes());
	                                    if (StyleConstants.getIcon(attrs)==null) {
	                                        StyleConstants.setIcon(attrs, emojis.get(index));
	                                        doc.remove(start+i, emoticons[index].length());
	                                        doc.insertString(start+i,emoticons[index], attrs);
	                                    }
	                                    i=text.indexOf(emoticons[index], i+emoticons[index].length());
	                                }
	                            }
                            } catch (BadLocationException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
            }
            public void removeUpdate(DocumentEvent e) {
            }
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
 
    static ImageIcon createImage(String icon) {
        BufferedImage res=new BufferedImage(17, 17, BufferedImage.TYPE_INT_ARGB);
        Graphics g=res.getGraphics();
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(":)".equals(icon)) {
        	g.setColor(Color.yellow);
	        g.fillOval(0,0,16,16);
	 
	        g.setColor(Color.black);
	        g.drawOval(0,0,16,16);
	 
	        g.drawLine(4,5, 6,5);
	        g.drawLine(4,6, 6,6);
	 
	        g.drawLine(12,5, 10,5);
	        g.drawLine(12,6, 10,6);
	 
	        // g.drawLine(4,10, 8,12);
	        // g.drawLine(8,12, 12,10);
	        g.drawArc(3,3,11,10,225,90);
	    } else if(":D".equals(icon)) {//open smile
	    	g.setColor(Color.yellow);
	        g.fillOval(0,0,16,16);
	 
	        g.setColor(Color.black);
	        g.drawOval(0,0,16,16);
	 
	        g.drawLine(4,5, 6,5);//left eye
	        g.drawLine(4,6, 6,6);
	 
	        g.drawLine(12,5, 10,5);//right eye
	        g.drawLine(12,6, 10,6);

	        g.fillArc(3,4,11,10,180,180);
	        //g.drawArc(3,4,11,10,180,180);
	        //g.drawLine(3,11, 14,11);
	    } else if(":(".equals(icon)) {//simple frown
	    	g.setColor(Color.yellow);
	        g.fillOval(0,0,16,16);
	 
	        g.setColor(Color.black);
	        g.drawOval(0,0,16,16);
	 
	        g.drawLine(4,5, 6,5);//left eye
	        g.drawLine(4,6, 6,6);
	 
	        g.drawLine(12,5, 10,5);//right eye
	        g.drawLine(12,6, 10,6);
	 
	        // g.drawLine(4,12, 8,10);//mouth
	        // g.drawLine(8,10, 12,12);
	        g.drawArc(3,11,11,10,45,90);
	    } else if(">:(".equals(icon)) {//angry frown
	    	g.setColor(Color.red);
	        g.fillOval(0,0,16,16);
	 
	        g.setColor(Color.black);
	        g.drawOval(0,0,16,16);
	 
	        g.drawLine(4,5, 5,5);//left eye
	        g.drawLine(4,6, 6,6);
	 
	        g.drawLine(12,5, 11,5);//right eye
	        g.drawLine(12,6, 10,6);
	 
	        g.drawLine(7,9, 11,9); //mouth
	        g.drawLine(7,14, 11,14);

	        g.drawLine(5,10, 6,10);
	        g.drawLine(5,13, 6,13);
	        g.drawLine(10,10, 11,10);
	        g.drawLine(10,13, 11,13);

	        g.drawLine(4,11, 4,12);
	        g.drawLine(12,11, 12,12);

	        g.drawLine(6,11, 6,13);
	        g.drawLine(8,11, 8,13);
	        g.drawLine(10,11, 10, 13);

	        g.setColor(Color.white);
	        g.drawLine(5,11, 5,12);
	        g.drawLine(7,9, 7,13);
	        g.drawLine(9,9, 9,13);
	        g.drawLine(11,11, 11,12);
	    } else if("<3".equals(icon)) {//heart
	    	g.setColor(Color.black);
	    	g.drawLine(8,15, 15,8);
	    	g.drawLine(15,8, 15,5);
	    	g.drawLine(15,5, 12,2);
	    	g.drawLine(12,2, 10,2);
	    	g.drawLine(10,2, 8,4);
	    	g.drawLine(8,4, 6,2);
	    	g.drawLine(6,2, 4,2);
	    	g.drawLine(4,2, 1,5);
	    	g.drawLine(1,5, 1,8);
	    	g.drawLine(1,8, 8,15);

	    	g.setColor(Color.red);
	    	g.drawLine(2,7, 2,5);
	    	g.drawLine(2,5, 3,5);
	    	g.drawLine(4,3, 6,3);
	    	g.drawLine(3,4, 7,4);
	    	g.drawLine(5,5, 14,5);
	    	g.drawLine(4,6, 14,6);
	    	g.drawLine(4,7, 14,7);
	    	g.drawLine(2,8, 14,8);
	    	g.drawLine(3,9, 13,9);
	    	g.drawLine(4,10, 12,10);
	    	g.drawLine(5,11, 11,11);
	    	g.drawLine(6,12, 10,12);
	    	g.drawLine(7,13, 9,13);
	    	g.drawLine(8,14, 8,14);
	    	g.drawLine(9,4, 13,4);
	    	g.drawLine(10,3, 12,3);
	    }
        g.dispose();
 
        return new ImageIcon(res);
    }
}