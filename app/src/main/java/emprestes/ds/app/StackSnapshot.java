package emprestes.ds.app;

import java.util.List;

/**
 * Immutable stack state captured after an operation.
 *
 * @param label human-readable operation label
 * @param values values from top to bottom
 */
public record StackSnapshot(String label, List<String> values) {
    /** Creates a defensive immutable copy of snapshot values. */
    public StackSnapshot {
        values = List.copyOf(values);
    }
}
