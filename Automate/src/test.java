import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        Cell q0 = new Cell("q0", true, false);
        Cell q1 = new Cell("q1", false, false);
        Cell q2 = new Cell("q2", false, true);
        
        q0.addOutput(q1, "a");
        q1.addInput(q0, "a");
        
        q1.addOutput(q2, "b");
        q2.addInput(q1, "b");
        
        ArrayList<Level> l = tools.getCellList(q0);
        for (Level level : l) {
            System.out.println(level.q.getId()+" "+level.lvl);
        }
        System.out.println("done");
    }
    
}
