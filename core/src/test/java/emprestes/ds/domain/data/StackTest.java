package emprestes.ds.domain.data;

import emprestes.ds.domain.IStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StackTest {

    private IStack<Character> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testEmptyStack() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPushOneElement() {
        var actual = stack.push('a').size();

        assertEquals(1, actual);
    }

    @Test
    public void testPopOneElement() {
        var value = 'a';

        var size = stack.push(value).size();
        var actual = stack.pop();

        assertEquals(1, size);
        assertEquals(value, actual);
    }

    @Test
    public void testPushManyElement() {
        var actual = stack.push('a', 'b', 'c', 'd', 'e', 'f').size();

        assertEquals(6, actual);
    }

    @Test
    public void testPopManyElement() {
        var values = new Character[]{'a', 'b', 'c', 'd', 'e', 'f'};
        var expected = List.of('f', 'e', 'd', 'c', 'b', 'a');

        var actual = stack.push(values).popAll();
        var size = stack.size();

        assertEquals(0, size);
        assertEquals(expected, actual);
    }

    @Test
    void snapshotDoesNotMutateTheStack() {
        stack.push('a', 'b', 'c');

        var firstSnapshot = stack.toList();
        var secondSnapshot = stack.toList();

        assertEquals(List.of('c', 'b', 'a'), firstSnapshot);
        assertEquals(firstSnapshot, secondSnapshot);
        assertEquals(3, stack.size());
        assertFalse(stack.isEmpty());
        assertEquals('c', stack.pop());
    }

    @Test
    void ignoresNullVarargsAndNullElementsWithoutChangingState() {
        stack.push('a');
        stack.push((Character[]) null);
        stack.push('b', null);

        assertEquals(List.of('b', 'a'), stack.toList());
        assertEquals(2, stack.size());
    }
}
