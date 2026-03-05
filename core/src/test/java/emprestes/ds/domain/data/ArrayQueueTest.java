package emprestes.ds.domain.data;

import emprestes.ds.domain.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayQueueTest {

    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new ArrayQueue<>();
    }

    @Test
    void shouldStartEmpty() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void shouldEnqueueAndDequeueInFifoOrder() {
        queue.enqueue(1, 2, 3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertNull(queue.dequeue());
    }

    @Test
    void shouldIgnoreNullsOnEnqueue() {
        queue.enqueue(1, null, 2);
        assertEquals(List.of(1, 2), queue.toList());
    }
}
