/**
 * Omisha Pasandul Wickramaarachchi
 * 20232456/w2153411
 */

import java.io.IOException;
import java.util.List;

/**
 * Entry point for the graph checker.
 */
public class Main {

    private static void printUsage() {
        System.out.println("Run this from the project root (the folder containing src and benchmarks).");
        System.out.println("Usage: java -cp src Main <path-to-graph-file>");
        System.out.println("Example: java -cp src Main benchmarks/cyclic/c_40_0.txt");
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            printUsage();
            return;
        }

        String filePath = args[0];

        // Load graph.
        Graph graph;
        try {
            graph = GraphParser.parse(filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.out.println("Current working directory: " + System.getProperty("user.dir"));
            printUsage();
            return;
        }

        System.out.println("Loaded graph from: " + filePath);
        System.out.println("Number of vertices: " + graph.getNumVertices());
        System.out.println();

        // Use a fresh copy because sink elimination mutates the graph.
        Graph graphForCheck;
        try {
            graphForCheck = GraphParser.parse(filePath);
        } catch (IOException e) {
            System.out.println("Error re-reading file: " + e.getMessage());
            return;
        }

        boolean acyclic = AcyclicChecker.isSinkAcyclic(graphForCheck);
        System.out.println();

        if (acyclic) {
            System.out.println("Answer: YES - the graph is acyclic.");
        } else {
            System.out.println("Answer: NO - the graph contains a cycle.");
            System.out.println();

            // Find and print one cycle from the original graph.
            System.out.println("--- Finding a Cycle ---");
            CycleResult result = AcyclicChecker.findCycle(graph);

            if (result.hasCycle) {
                List<Integer> cycle = result.cycle;
                System.out.print("Cycle found: ");
                for (int i = 0; i < cycle.size(); i++) {
                    System.out.print(cycle.get(i));
                    if (i < cycle.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            } else {
                // Defensive fallback.
                System.out.println("Unexpected: could not find a cycle despite graph being cyclic.");
            }
        }
    }
}
