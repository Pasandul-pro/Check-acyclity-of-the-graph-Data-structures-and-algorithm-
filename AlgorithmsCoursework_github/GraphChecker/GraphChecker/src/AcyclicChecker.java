/**
 * Omisha Pasandul Wickramaarachchi
 * 20232456/w2153411
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Acyclicity checks: sink elimination and DFS cycle detection.
 */
public class AcyclicChecker {

    // DFS colors.
    private static final int WHITE = 0;
    private static final int GREY  = 1;
    private static final int BLACK = 2;

    // Sink elimination.

    /**
     * Checks acyclicity with sink elimination.
     *
     * @param graph graph to check (mutated during the run)
     * @return true if acyclic, false if cyclic
     */
    public static boolean isSinkAcyclic(Graph graph) {
        System.out.println("--- Sink Elimination Algorithm ---");
        int step = 1;

        while (true) {
            // If nothing remains, the graph is acyclic.
            if (graph.isEmpty()) {
                System.out.println("Graph is now empty.");
                System.out.println("Result: ACYCLIC (yes)");
                return true;
            }

            // Pick any active sink.
            int sink = graph.findSink();

            if (sink == -1) {
                // No sink in the remaining graph means there is a cycle.
                System.out.println("Step " + step + ": No sink found in remaining graph.");
                System.out.println("Result: CYCLIC (no)");
                return false;
            }

            System.out.println("Step " + step + ": Sink found -> vertex " + sink
                    + "  (outDegree = 0). Removing it.");
            graph.removeVertex(sink);
            step++;
        }
    }

    // Find a cycle with DFS.

    /**
     * Finds one cycle using recursive DFS and color marking.
     *
     * @param graph original unmodified graph
     */
    public static CycleResult findCycle(Graph graph) {
        int n = graph.getNumVertices();
        int[] colour = new int[n];  // defaults to WHITE

        // Current DFS path.
        List<Integer> path = new ArrayList<>();

        // Start DFS from each component.
        for (int v = 0; v < n; v++) {
            if (colour[v] == WHITE) {
                CycleResult result = dfsVisit(v, graph, colour, path);
                if (result != null) {
                    return result;
                }
            }
        }

        return new CycleResult(false, null);
    }

    /** Recursive DFS from v. Returns cycle info or null. */
    private static CycleResult dfsVisit(int v, Graph graph,
                                         int[] colour, List<Integer> path) {
        colour[v] = GREY;   // currently on recursion stack
        path.add(v);

        for (int w : graph.getNeighbours(v)) {
            if (colour[w] == GREY) {
                // Back edge to an active path vertex.
                List<Integer> cycle = extractCycle(w, path);
                return new CycleResult(true, cycle);
            }
            if (colour[w] == WHITE) {
                CycleResult result = dfsVisit(w, graph, colour, path);
                if (result != null) {
                    return result;
                }
            }
        }

        // Backtrack.
        path.remove(path.size() - 1);
        colour[v] = BLACK;
        return null;
    }

    /** Extracts the cycle segment from the DFS path. */
    private static List<Integer> extractCycle(int cycleStart, List<Integer> path) {
        List<Integer> cycle = new ArrayList<>();

        int startIndex = path.lastIndexOf(cycleStart);
        for (int i = startIndex; i < path.size(); i++) {
            cycle.add(path.get(i));
        }
        cycle.add(cycleStart);  // close cycle

        return cycle;
    }
}
