import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class Draw extends JFrame{
    int x=500;
    int y=500;
    public Draw(){
        setTitle("Automate");
        setSize(x, y);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void paint(Graphics g){
        g.setColor(Color.green);
        g.drawOval(x/4,y/4,x/2,y/2);
    }
    public static void main(String[] args) {
        Draw d = new Draw();
        d.paint((Graphics)null);
    }
}
