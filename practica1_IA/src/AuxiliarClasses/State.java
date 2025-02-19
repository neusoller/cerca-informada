package AuxiliarClasses;

import java.util.ArrayList;

public class State {
    private Position pos;
    private State parent;
    private ArrayList<Position> path;
    private float heuristic;
    private float accumulate;

    public State getParent() {
        return parent;
    }

    public ArrayList<Position> getPath() {
        return path;
    }

    public Position getPos(){
        return pos;
    }

    public float getHeuristic(){
        return heuristic;
    }

    public float getAccumulate() { return accumulate; }

    public boolean samePosition(Position p){
        return pos.compare(p);
    }

    public void penalizar(){
        heuristic *= 2;
    }

    public void accumulate(float g){
        accumulate += g;
    }

    public State() {
        /* Only for first state */
        path = new ArrayList<>();
        accumulate = 0;
    }

    public State(Position pos, State p, float h) {
        this.pos = pos;
        parent = p;
        path = (ArrayList<Position>) p.getPath().clone();
        path.add(pos);
        heuristic = h;
        accumulate = 0;
    }
}
