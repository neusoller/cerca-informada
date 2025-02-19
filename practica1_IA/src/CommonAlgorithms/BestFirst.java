package CommonAlgorithms;

import AuxiliarClasses.Heuristic;
import AuxiliarClasses.Position;
import AuxiliarClasses.State;

import java.util.ArrayList;

public class BestFirst extends Common{

    public BestFirst(String[][] map, int size) {
        super.map = map;
        super.size = size;
    }

    public ArrayList<Position> BFSearch(Heuristic H) {
        Position finalPos = new Position(9,9);
        ArrayList<State> remainings = new ArrayList<>();
        remainings.add(new State(new Position(0,0), new State(), 0));
        ArrayList<State> processeds = new ArrayList<>();
        boolean found = false;
        while (!found && !remainings.isEmpty()){
            State s = remainings.get(0);
            remainings.remove(0);
            if(s.samePosition(finalPos))
                return s.getPath();
            for(State n : lookNeighbours(s, H)){
                if(!contains(processeds, n) && !contains(remainings, n)){
                    remainings.add(n);
                    remainings.sort((o1, o2) -> {
                        if(o1.getHeuristic() > o2.getHeuristic())
                            return 1;
                        if(o1.getHeuristic() < o2.getHeuristic())
                            return -1;
                        return 0;
                    });
                }
                processeds.add(n);
            }
        }
        return null;
    }
}
