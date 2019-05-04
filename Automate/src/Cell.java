import java.util.ArrayList;

public class Cell {
    private String id;
    private boolean isStart;
    private boolean isFinal;
    private ArrayList<Path> input = new ArrayList<>();
    private ArrayList<Path> output = new ArrayList<>();

    public Cell(String id, boolean isStart, boolean isFinal) {
        this.id = id;
        this.isStart = isStart;
        this.isFinal = isFinal;
    }
    
    public void addInput(Cell q, String alphabet){
        input.add(new Path(q, alphabet));
    }
    
    public void addOutput(Cell q, String alphabet){
        output.add(new Path(q, alphabet));
    }

    public boolean isFinal() {
        return isFinal;
    }

    public boolean isStart() {
        return isStart;
    }
    
    public String getId() {
        return id;
    }
    
    public ArrayList<Cell> nextCell(){
        ArrayList<Cell> tmp = new ArrayList<>();
        for (Path p : this.output) {
            tmp.add(p.q);
        }
        return tmp;
    }
}

class Path{
    Cell q;
    String alphabet;

    public Path(Cell q, String alphabet) {
        this.q = q;
        this.alphabet = alphabet;
    }
}

class Level{
    Cell q;
    int lvl;

    public Level(Cell q, int lvl) {
        this.q = q;
        this.lvl = lvl;
    }
}
