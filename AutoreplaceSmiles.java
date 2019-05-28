import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.image.BufferedImage;
import java.awt.*;
 
public class AutoreplaceSmiles extends JEditorPane {
    static ImageIcon SMILE_IMG=createImage();
 
    public static void main(String[] args) {
        JFrame frame = new JFrame("Autoreplace :) with Smiles images example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final AutoreplaceSmiles app = new AutoreplaceSmiles();
        app.setEditorKit(new StyledEditorKit());
        app.initListener();
        JScrollPane scroll = new JScrollPane(app);
        frame.getContentPane().add(scroll);
 
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 
    public AutoreplaceSmiles() {
        super();
    }
 
    private void initListener() {
        getDocument().addDocumentListener(new DocumentListener(){
            public void insertUpdate(DocumentEvent event) {
                final DocumentEvent e=event;
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (e.getDocument() instanceof StyledDocument) {
                            try {
                                StyledDocument doc=(StyledDocument)e.getDocument();
                                int start= Utilities.getRowStart(AutoreplaceSmiles.this,Math.max(0,e.getOffset()-1));
                                int end=Utilities.getWordStart(AutoreplaceSmiles.this,e.getOffset()+e.getLength());
                                String text=doc.getText(start, end-start);
 
                                int i=text.indexOf(":)");
                                while(i>=0) {
                                    final SimpleAttributeSet attrs=new SimpleAttributeSet(
                                       doc.getCharacterElement(start+i).getAttributes());
                                    if (StyleConstants.getIcon(attrs)==null) {
                                        StyleConstants.setIcon(attrs, SMILE_IMG);
                                        doc.remove(start+i, 2);
                                        doc.insertString(start+i,":)", attrs);
                                    }
                                    i=text.indexOf(":)", i+2);
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
 
    static ImageIcon createImage() {
        BufferedImage res=new BufferedImage(17, 17, BufferedImage.TYPE_INT_ARGB);
        Graphics g=res.getGraphics();
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.yellow);
        g.fillOval(0,0,16,16);
 
        g.setColor(Color.black);
        g.drawOval(0,0,16,16);
 
        g.drawLine(4,5, 6,5);
        g.drawLine(4,6, 6,6);
 
        g.drawLine(11,5, 9,5);
        g.drawLine(11,6, 9,6);
 
        g.drawLine(4,10, 8,12);
        g.drawLine(8,12, 12,10);
        g.dispose();
 
        return new ImageIcon(res);
    }
}