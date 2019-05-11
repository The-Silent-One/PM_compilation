import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Draw extends JFrame {

    public static int x = 500;
    public static int y = 500;

    public Draw() {
        setTitle("Automate");
        setSize(x, y);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        WindowUtilities.openInJFrame(new Shape(new Cell("q0", true, false)), x, y);
    }
}

class Shape extends JPanel {

    private ArrayList<State> qi = new ArrayList<>();
    ArrayList<Level> l;

    static int size = 20;
    static int x = 500;
    static int y = 500;
    static String symb_s = "";
    static String symb_f = "";

    Shape(Cell q0) {
        q0 = tools.build(q0);
        this.l = tools.getCellList(q0);
        int tmp = 0;
        int count = 1;
        this.show_cell();
        for (Level i : l) {
            if (i.lvl == tmp) {
                count++;
            } else {
                tmp = i.lvl;
                count = 1;
            }
            int pos_x = x / 10 * (tmp + 1);
            int pos_y = y / 10 * count;
            this.qi.add(new State(
                    new Ellipse2D.Double(pos_x, pos_y, size, size),
                    i.q,
                    pos_x, pos_y));

        }
        //System.out.println(tools.isvalid(q0, "ab"));
    }

    public void paintComponent(Graphics g) {
        clear(g);
        Graphics2D g2d = (Graphics2D) g;
        for (State tmp : qi) {
            g2d.fill(tmp.circle);
            if (tmp.q.isStart()) {
                g2d.drawString(symb_s + tmp.q.getId(), tmp.pos_x, tmp.pos_y);
            } else if (tmp.q.isFinal()) {
                g2d.drawString(symb_f + tmp.q.getId(), tmp.pos_x, tmp.pos_y);
            } else {
                g2d.drawString(tmp.q.getId(), tmp.pos_x, tmp.pos_y);
            }
            try {
                for (Arrow path : tmp.q.getOutput()) {
                    for (State state : qi) {
                        if(path.q_finish.equals(state.q)){
                            g2d.draw(new Line2D.Double(tmp.pos_x + size / 2,
                                    tmp.pos_y + size / 2,
                                    state.pos_x + size / 2,
                                    state.pos_y + size / 2));
                            g2d.drawString(path.alphabet+"",
                                    Math.abs(state.pos_x+tmp.pos_x)/2,
                                    Math.abs(state.pos_y+tmp.pos_y)/2);
                        }
                    }
                }
            } catch (NullPointerException ex) {
                //System.out.println("*");
            }
        }
    }

    protected void clear(Graphics g) {
        super.paintComponent(g);
    }

    public void show_cell() {
        for (Level level : l) {
            System.out.println(level.q.getId() + " " + level.lvl);
        }
        System.out.println("done");
    }

}

class State {

    Ellipse2D.Double circle;
    Cell q;
    int pos_x;
    int pos_y;

    public State(Ellipse2D.Double circle, Cell q, int pos_x, int pos_y) {
        this.circle = circle;
        this.q = q;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

}
