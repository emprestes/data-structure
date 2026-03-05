package emprestes.ds.domain.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinHeapTest {

    private MinHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new MinHeap<>();
    }

    @Test
    void shouldReturnNullWhenEmpty() {
        assertNull(heap.peek());
        assertNull(heap.poll());
        assertTrue(heap.isEmpty());
    }

    @Test
    void shouldKeepMinAtTop() {
        heap.insert(9).insert(4).insert(7).insert(1).insert(5);

        assertEquals(1, heap.peek());
        assertEquals(1, heap.poll());
        assertEquals(4, heap.poll());
        assertEquals(5, heap.poll());
        assertEquals(7, heap.poll());
        assertEquals(9, heap.poll());
    }

    @Test
    void shouldIgnoreNullInsert() {
        heap.insert(2).insert(null).insert(1);

        assertEquals(2, heap.size());
        assertEquals(1, heap.peek());
    }
}
