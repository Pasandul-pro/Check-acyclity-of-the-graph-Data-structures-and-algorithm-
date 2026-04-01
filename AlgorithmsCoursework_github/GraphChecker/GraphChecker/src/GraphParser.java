/**
 * Omisha Pasandul Wickramaarachchi
 * 20232456/w2153411
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** Parses a graph from a text file. */
public class GraphParser {

    /** Reads the file and builds a Graph. */
    public static Graph parse(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // First line: vertex count.
        String firstLine = reader.readLine();
        if (firstLine == null) {
            reader.close();
            throw new IOException("File is empty: " + filePath);
        }

        int numVertices = Integer.parseInt(firstLine.trim());
        Graph graph = new Graph(numVertices);

        // Remaining lines are edges: "u v".
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            if (parts.length < 2) continue;

            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            graph.addEdge(u, v);
        }

        reader.close();
        return graph;
    }
}
