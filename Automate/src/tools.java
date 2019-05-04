import java.util.ArrayList;

public class tools {
    static ArrayList<Level> getCellList(Cell q_init){
        ArrayList<Level> res = new ArrayList<>();
        ArrayList<Cell> current = new ArrayList<>();
        ArrayList<Cell> next = new ArrayList<>();
        current.add(q_init);
        int lvl=0;
        while(!current.isEmpty()){
            next=new ArrayList<>();
            for (Cell cell : current) {
                res.add(new Level(cell, lvl));
                for (Cell c : cell.nextCell()) {
                    next.add(c);
                }
            }
            current=next;
            lvl++;
        }
        return res;
    }
}
