import java.util.ArrayList;

public class Cell {
    private String id;
    private boolean isStart;
    private boolean isFinal;
    private ArrayList<Arrow> input = new ArrayList<>();
    private ArrayList<Arrow> output = new ArrayList<>();

    public ArrayList<Arrow> getOutput() {
        return output;
    }

    public Cell(String id, boolean isStart, boolean isFinal) {
        this.id = id;
        this.isStart = isStart;
        this.isFinal = isFinal;
    }
    
    public void addInput(Cell q, char alphabet){
        input.add(new Arrow(q,this, alphabet));
    }
    
    public void addOutput(Cell q, char alphabet){
        output.add(new Arrow(this,q, alphabet));
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
        for (Arrow p : this.output) {
            tmp.add(p.q_finish);
        }
        return tmp;
    }
}

class Arrow{
    Cell q_start;
    Cell q_finish;
    char alphabet;

    public Arrow(Cell q_start, Cell q_finish, char alphabet) {
        this.q_start = q_start;
        this.q_finish = q_finish;
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
