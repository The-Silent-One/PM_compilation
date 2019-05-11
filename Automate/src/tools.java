
import java.util.ArrayList;

public class tools {

    static ArrayList<Level> getCellList(Cell q_init) {
        ArrayList<Level> res = new ArrayList<>();
        ArrayList<Cell> current = new ArrayList<>();
        ArrayList<Cell> next = new ArrayList<>();
        current.add(q_init);
        int lvl = 0;
        while (!current.isEmpty()) {
            next = new ArrayList<>();
            for (Cell cell : current) {
                res.add(new Level(cell, lvl));
                for (Cell c : cell.nextCell()) {
                    next.add(c);
                }
            }
            current = next;
            lvl++;
        }
        return res;
    }

    static boolean isvalid(Cell q0, String ch) {
        Cell current = q0;
        for (int i = 0; i < ch.length(); i++) {
            ArrayList<Arrow> tmp = current.getOutput();
            int j = 0;
            while (tmp.get(j).alphabet != ch.charAt(i)) {
                j++;
            }
            if (j < tmp.size()) {
                current = tmp.get(j).q_finish;
            }else{
                break;
            }
        }

        return current.isFinal();
    }

    static Cell build(Cell q0) {
        q0 = new Cell("q0", true, false);
        Cell q1 = new Cell("q1", false, false);
        Cell q2 = new Cell("q2", false, true);

        q0.addOutput(q1, 'a');
        q1.addInput(q0, 'a');

        q1.addOutput(q2, 'b');
        q2.addInput(q1, 'b');
        return q0;
    }
}
