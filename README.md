# Heuristic Search Algorithms
# Description
This project implements two heuristic search algorithms (Best-First Search and A* Search) applied to a pathfinding problem on a 10x10 grid. The goal is to find the optimal path from the starting cell (0,0) to the goal cell (9,9), using different heuristics to guide the search.

# Implemented Algorithms
-> Best-First Search (BFS)
Explores paths based solely on heuristic values, prioritizing nodes with the lowest heuristic cost.

-> A Search (A*)*
Combines the accumulated path cost with the heuristic estimate to find more optimal paths in terms of cost and time.

# Heuristics
1. Manhattan Distance
Calculates the distance between two cells allowing only horizontal and vertical moves. (Admissible)

2. Shortest Time in Straight Line
Estimates the minimum time to reach the goal assuming a direct line. (May be inadmissible)

3. Estimated Minimum Time
Considers the available transportation mode and estimates the minimum time to reach the goal. (Admissible)
