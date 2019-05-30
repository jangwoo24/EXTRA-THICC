import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
 
public class EmoteChanger extends JTextPane {
    String[] emoticons = {":)", ":D", ":(", ">:(", "<3", "-_-", ":P",
    	":think:", ":thonk:", ":eggplant:", ":peach:", ":sweat:",
    	"jeff"
    	};
    /*static ImageIcon SMILE_IMG = createImage(":)");
    static ImageIcon ANGER_IMG = createImage(">:(");
    static ImageIcon FROWN_IMG = createImage(":(");
    static ImageIcon[] emojis = {SMILE_IMG, ANGER_IMG, FROWN_IMG};*/
    static List<ImageIcon> emojis = new ArrayList<>();
 
    public static void main(String[] args) throws IOException
    {
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
 
    public EmoteChanger() throws IOException
    {
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
 	static ImageIcon createImage(String icon) throws IOException
 	{
 		File imageFile = null;
 		if(":)".equals(icon)) {
 			imageFile = new File("Emojis/smile.png");
 		} else if(":D".equals(icon)) {
 			imageFile = new File("Emojis/grinning.png");
 		} else if(":(".equals(icon)) {
 			imageFile = new File("Emojis/frown.png");
 		} else if(">:(".equals(icon)) {
 			imageFile = new File("Emojis/rage.png");
 		} else if("<3".equals(icon)) {
 			imageFile = new File("Emojis/heart.png");
 		} else if("-_-".equals(icon)) {
 			imageFile = new File("Emojis/expressionless.png");
 		} else if(":P".equals(icon)) {
 			imageFile = new File("Emojis/stuck_out_tongue.png");
 		} else if(":think:".equals(icon)) {
 			imageFile = new File("Emojis/think.png");
 		} else if(":thonk:".equals(icon)) {
 			imageFile = new File("Emojis/thonk.png");
 		} else if(":eggplant:".equals(icon)) {
 			imageFile = new File("Emojis/eggplant.png");
 		} else if(":peach:".equals(icon)) {
 			imageFile = new File("Emojis/peach.png");
 		} else if(":sweat:".equals(icon)) {
 			imageFile = new File("Emojis/sweat.png");
 		} else if("jeff".equals(icon)) {
 			imageFile = new File("Emojis/jeff.png");
 		} else {
 			imageFile = new File("VideoScreencap.png");
 		}
 		return openFileAsImage(imageFile);
 	}
 	static ImageIcon openFileAsImage(File file) throws IOException
 	{
 		BufferedImage in = ImageIO.read(file);

 		BufferedImage newImage = new BufferedImage(
 		    in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

 		Graphics2D g = newImage.createGraphics();
 		g.drawImage(in, 0, 0, null);
 		g.dispose();
 		return new ImageIcon(newImage);
 	}
}