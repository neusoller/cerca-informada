package AuxiliarClasses;

public class Position {
    private int fil, col;

    public Position(int fil, int col) {
        this.fil = fil;
        this.col = col;
    }

    public int getFil() {
        return fil;
    }

    public int getCol() {
        return col;
    }

    public boolean compare(Position p){
        return fil==p.fil && col==p.col;
    }
}
