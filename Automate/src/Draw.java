import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Draw {

    public static int x = 500;
    public static int y = 500;

    /*public Draw() {
        setTitle("Automate");
        setSize(x, y);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }*/

    public static void main(String[] args) {
        JFrame f = new Input(x/2, y/2);
        f.setVisible(true);
//        WindowUtilities.openInJFrame(new Automate(new Cell("q0", true, false)), x, y);
    }
}

class Automate extends JPanel {

    private ArrayList<State> qi = new ArrayList<>();
    ArrayList<Level> l;

    static int size = 20;
    static int x = 500;
    static int y = 500;
    static String symb_s = "";
    static String symb_f = "";

    Automate(Cell q0) {
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

class Input extends JFrame{
    
    private String[] ch;
    private int nb;
    private GridLayout layout = new GridLayout(5, 0);
    int x;
    int y;
    Input(int x ,int y){
        super("Input");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.x=x;
        this.y=y;
        
        JTextArea txt1 = new JTextArea("enter alphabet:\nexample : 0,1");
        txt1.setEditable(false);
        JTextField field1 = new JTextField();
//        field1.setPreferredSize(new Dimension(x, y/2));
        
                
        JTextArea txt2 = new JTextArea("enter number of states");
        txt2.setEditable(false);
        JTextField field2 = new JTextField();
        //field2.setPreferredSize(new Dimension(x, y/2));
        
        Button bt1 = new Button("confirm");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ch = field1.getText().split(",");
                nb = Integer.parseInt(field2.getText());
                Component tmp = (Component) e.getSource();
                Input test = (Input) SwingUtilities.getRoot(tmp);
                JFrame f = new Table(test.x, test.y, nb);
                f.setVisible(true);
                test.dispose();
            }
        });
        this.setLayout(layout);
        this.add(txt1);
        this.add(field1);
        this.add(txt2);
        this.add(field2);
        this.add(bt1);
        this.setSize(x, y);
        this.setResizable(false);
    }
    
    public int getNb() {
        return nb;
    }

    public String[] getCh() {
        return ch;
    }
}

class Table extends JFrame{
    String[] colNames = {"","",""};
    Table(int x, int y,int nb) {
        super("Table");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[][] data = new String[nb][3];
        JTable t = new JTable(data, colNames);
        this.add(t);
        this.setSize(x, y);
        this.setResizable(false);
    }
    
}