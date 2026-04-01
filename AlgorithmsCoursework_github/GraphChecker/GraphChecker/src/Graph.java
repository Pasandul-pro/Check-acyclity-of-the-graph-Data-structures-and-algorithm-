/**
 * Omisha Pasandul Wickramaarachchi
 * 20232456/w2153411
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Directed graph with adjacency lists and per-vertex out-degree.
 */
public class Graph {

    // Vertices are indexed 0..n-1.
    private final int numVertices;

    // adjacencyList.get(v) contains v's outgoing neighbors.
    private final List<List<Integer>> adjacencyList;

    // outDegree[v] is the number of outgoing edges from v.
    private final int[] outDegree;

    // active[v] is false after logical removal.
    private final boolean[] active;

    // Number of active vertices.
    private int remaining;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.remaining = numVertices;
        this.outDegree = new int[numVertices];
        this.active = new boolean[numVertices];

        // All vertices start active.
        for (int i = 0; i < numVertices; i++) {
            active[i] = true;
        }

        // Initialize an empty adjacency list for each vertex.
        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    /** Adds a directed edge u -> v. */
    public void addEdge(int u, int v) {
        adjacencyList.get(u).add(v);
        outDegree[u]++;
    }

    /** Returns true when no active vertices remain. */
    public boolean isEmpty() {
        return remaining == 0;
    }

    /** Returns an active sink vertex, or -1 if none exists. */
    public int findSink() {
        for (int v = 0; v < numVertices; v++) {
            if (active[v] && outDegree[v] == 0) {
                return v;
            }
        }
        return -1;
    }

    /**
     * Logically removes v and updates out-degrees of vertices that pointed to it.
     */
    public void removeVertex(int v) {
        active[v] = false;
        remaining--;

        // Reduce out-degree for each edge u -> v from active vertices.
        for (int u = 0; u < numVertices; u++) {
            if (active[u]) {
                for (int neighbor : adjacencyList.get(u)) {
                    if (neighbor == v) {
                        outDegree[u]--;
                    }
                }
            }
        }
    }

    /** Returns outgoing neighbors of v. */
    public List<Integer> getNeighbours(int v) {
        return adjacencyList.get(v);
    }

    public boolean isActive(int v) {
        return active[v];
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getOutDegree(int v) {
        return outDegree[v];
    }
}
