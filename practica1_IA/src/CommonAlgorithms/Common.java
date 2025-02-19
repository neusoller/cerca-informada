package CommonAlgorithms;

import AuxiliarClasses.Position;
import AuxiliarClasses.State;
import AuxiliarClasses.Heuristic;
import AuxiliarClasses.TimeCost;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Common {
    protected String[][] map;
    protected int size;
    public static HashMap<String, TimeCost> hashTypes = new HashMap<>();

    public static void setHash() {
        hashTypes.put("W", new TimeCost(10, 0));
        hashTypes.put("B", new TimeCost(3, 2));
        hashTypes.put("S", new TimeCost(5, 5));
        hashTypes.put("T", new TimeCost(1, 3));
    }

    protected boolean contains(ArrayList<State> list, State s){
        for (State e: list){
            if(e.samePosition(s.getPos()))
                return true;
        }
        return false;
    }

    private State getNeighbour(int fil, int col, State p, Heuristic H){
        State aux;
        switch (H){
            case COST: aux = new State(new Position(fil, col), p, hashTypes.get(map[fil][col]).getCost()); aux.accumulate(p.getAccumulate() + aux.getHeuristic()); return aux;
            case TIME: aux = new State(new Position(fil, col), p, hashTypes.get(map[fil][col]).getTime()); aux.accumulate(p.getAccumulate() + aux.getHeuristic()); return aux;
            case OTHER: aux = new State(new Position(fil, col), p, hashTypes.get(map[fil][col]).getOther()); aux.accumulate(p.getAccumulate() + aux.getHeuristic()); return aux;
            // Should add acc + real_cost, but I consider an ideal heuristic as there's no real data given.
        }
        return null; // CAN'T BE
    }

    protected ArrayList<State> lookNeighbours(State s, Heuristic H){
        int col = s.getPos().getCol();
        int fil = s.getPos().getFil();
        ArrayList<State> nb = new ArrayList<>();
        if (col + 1 < size) {
            nb.add(getNeighbour(fil, col+1, s, H));
        }
        if (fil + 1 < size) {
            nb.add(getNeighbour(fil+1, col, s, H));
        }
        if (col - 1 >= 0) {
            State aux = getNeighbour(fil, col-1, s, H);
            aux.penalizar();
            nb.add(aux);
        }
        if (fil - 1 >= 0) {
            State aux = getNeighbour(fil-1, col, s, H);
            aux.penalizar();
            nb.add(aux);
        }
        return nb;
    }
}
