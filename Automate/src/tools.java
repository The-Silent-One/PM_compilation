
import java.util.ArrayList;
import javax.swing.table.TableModel;

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
            while (j<tmp.size() && tmp.get(j).alphabet != ch.charAt(i)) {
                j++;
            }
            if (j < tmp.size()) {
                current = tmp.get(j).q_finish;
            } else {
                return false;
            }
        }

        return current.isFinal();
    }

    static Cell buildFromTable(TableModel t) {
        ArrayList<Cell> tmp = new ArrayList<>();
        int ret = 0;
        for (int i = 1; i < t.getRowCount(); i++) {
            String name = (String) t.getValueAt(i,0);
//            System.out.println(name);
            boolean start = false;
            boolean finish = false;
            if (name.startsWith("->")) {
                start = true;
                name = name.substring(2);
                ret = i - 1;
            }
            if (name.startsWith("*")) {
                finish = true;
                name = name.substring(1);
            }
            tmp.add(new Cell(name, start, finish));
        }

        ArrayList<Character> alphabet = new ArrayList<>();
        for (int i = 1; i < t.getColumnCount(); i++) {
            String ts = (String) t.getValueAt(0,i);
//            System.out.println(ts);
            alphabet.add(ts.charAt(0));

        }
        for (int i = 1; i < t.getRowCount(); i++) {
            for (int j = 1; j < t.getColumnCount(); j++) {
//                System.out.println(i+" "+j);
                String line = (String) t.getValueAt(i,j);
                try {
                    String[] values = line.split(",");
                    for (int k = 0; k < values.length; k++) {
                        String ss = (String)t.getValueAt(i,0);
                        Cell qtmp_s = tmp.get(Integer.parseInt(ss.substring(ss.length()-1)));
                        Cell qtmp_f = tmp.get(Integer.parseInt(values[k].substring(values[k].length()-1)));
//                        System.out.println(qtmp_s.getId()+" "+qtmp_f.getId());
                        qtmp_s.addOutput(qtmp_f, alphabet.get(j - 1));
                    }
                } catch (Exception ex) {
//                    System.out.println(line);
                    continue;
                }
            }

        }
//        ArrayList<Level> l =getCellList(tmp.get(ret));
//        for (Level level : l) {
//            System.out.println(level.q.getId() + " " + level.lvl);
//        }
        return tmp.get(ret);
    }
}
