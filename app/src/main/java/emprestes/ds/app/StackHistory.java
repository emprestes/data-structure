package emprestes.ds.app;

import emprestes.ds.domain.MutableStack;
import emprestes.ds.domain.StackView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Application model that coordinates stack commands and immutable history.
 *
 * <p>The model depends on role-specific abstractions rather than a concrete
 * stack. JavaFX observes this model and never navigates stack internals.</p>
 */
@RequiredArgsConstructor
public final class StackHistory {
    @NonNull
    private final MutableStack<String> commands;
    @NonNull
    private final StackView<String> view;
    private final List<StackSnapshot> snapshots = new ArrayList<>();

    /** Pushes cleaned values and captures the resulting state. */
    public void push(List<String> values, String label) {
        java.util.Objects.requireNonNull(values, "values").stream()
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .forEach(value -> commands.push(value));
        capture(label);
    }

    /** Pops one value, captures the resulting state, and returns the value. */
    public String pop(String label) {
        var value = commands.pop();
        capture(label);
        return value;
    }

    /** Captures the current state without changing the stack. */
    public void capture(String label) {
        snapshots.add(new StackSnapshot(label, view.toList()));
    }

    /** Returns an immutable history snapshot. */
    public List<StackSnapshot> snapshots() {
        return List.copyOf(snapshots);
    }
}
