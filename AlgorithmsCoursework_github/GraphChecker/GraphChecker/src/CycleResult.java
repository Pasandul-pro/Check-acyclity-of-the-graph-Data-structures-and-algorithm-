/**
 * Omisha Pasandul Wickramaarachchi
 * 20232456/w2153411
 */
import java.util.List;

/** Simple result type for DFS cycle search. */
public class CycleResult {
    public final boolean hasCycle;
    public final List<Integer> cycle;

    public CycleResult(boolean hasCycle, List<Integer> cycle) {
        this.hasCycle = hasCycle;
        this.cycle = cycle;
    }
}
