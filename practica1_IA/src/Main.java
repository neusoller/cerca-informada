import AuxiliarClasses.Heuristic;
import AuxiliarClasses.Position;
import AuxiliarClasses.State;
import CommonAlgorithms.AStar;
import CommonAlgorithms.BestFirst;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static CommonAlgorithms.Common.hashTypes;
import static CommonAlgorithms.Common.setHash;

public class Main {
    private static int mapSize = 10;
    private static String[][] map = new String[mapSize][mapSize];
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    private static void readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./src/map2.txt"));
        String line;

        for(int i=0;(line = br.readLine()) != null; i++){
            StringTokenizer st = new StringTokenizer(line,",");
            for (int j=0; st.hasMoreTokens(); j++){
                switch (st.nextToken()){
                    case "w": map[i][j] = "W"; break;
                    case "s": map[i][j] = "S"; break;
                    case "t": map[i][j] = "T"; break;
                    case "b": map[i][j] = "B"; break;
                }
            }
        }
    }

    private static boolean contains(ArrayList<Position> list, Position p) {
        for(Position s: list)
            if(s.compare(p))
                return true;
        return false;
    }

    private static void printMap(ArrayList<Position> solution) {
        int cost = 0;
        int time = 0;
        int other = 0;
        int nodes = 0;
        for(int i=0; i < mapSize; i++) {
            System.out.println();
            for (int j = 0; j < mapSize; j++){
                if(contains(solution, new Position(i, j))) {
                    System.out.print(ANSI_RED + map[i][j] + ANSI_RESET);
                    cost += hashTypes.get(map[i][j]).getCost();
                    time += hashTypes.get(map[i][j]).getTime();
                    other += hashTypes.get(map[i][j]).getOther();
                    nodes++;
                }
                else
                    System.out.print(map[i][j]);
            }
        }
        System.out.println();
        System.out.println("Nodes: " + nodes + " Cost: " + cost + "\nTime: " + time + " Other: " + other);
    }

    public static void main(String[] args) {
        setHash();
        try {
            readFile();
            BestFirst bf = new BestFirst(map, mapSize);
            AStar as = new AStar(map, mapSize);
            System.out.print("\n*** BFS per COST ***");
            printMap(bf.BFSearch(Heuristic.COST));
            System.out.print("\n*** BFS per TIME ***");
            printMap(bf.BFSearch(Heuristic.TIME));
            System.out.print("\n*** BFS per OTHER ***");
            printMap(bf.BFSearch(Heuristic.OTHER));
            System.out.print("\n*** A* per COST ***");
            printMap(as.ASSearch(Heuristic.COST));
            System.out.print("\n*** A* per TIME ***");
            printMap(as.ASSearch(Heuristic.TIME));
            System.out.print("\n*** A* per OTHER ***");
            printMap(as.ASSearch(Heuristic.OTHER));
        } catch (IOException e) {
            System.out.println("Error reading from file");
        }
    }
}
