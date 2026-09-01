package emprestes.ds.app;

import emprestes.ds.domain.data.Stack;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StackHistoryTest {
    @Test
    void coordinatesCommandsAndCapturesImmutableSnapshots() {
        var stack = new Stack<String>();
        var history = new StackHistory(stack, stack);

        history.capture("Empty stack");
        history.push(List.of(" A ", "", "B"), "Push A,B");
        var popped = history.pop("Pop");

        assertEquals("B", popped);
        assertEquals(3, history.snapshots().size());
        assertEquals(List.of(), history.snapshots().get(0).values());
        assertEquals(List.of("B", "A"), history.snapshots().get(1).values());
        assertEquals(List.of("A"), history.snapshots().get(2).values());
        assertThrows(UnsupportedOperationException.class,
                () -> history.snapshots().add(new StackSnapshot("invalid", List.of())));
    }

    @Test
    void capturesEmptyPopWithoutKnowingStackInternals() {
        var stack = new Stack<String>();
        var history = new StackHistory(stack, stack);

        assertNull(history.pop("Pop"));
        assertEquals(List.of(), history.snapshots().getFirst().values());
    }
}
