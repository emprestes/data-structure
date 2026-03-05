package emprestes.ds.domain.data;

import emprestes.ds.domain.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SinglyLinkedListTest {

    private LinkedList<Integer> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new SinglyLinkedList<>();
    }

    @Test
    void shouldAddAtBeginningAndEnd() {
        linkedList.addLast(2).addFirst(1).addLast(3);

        assertEquals(3, linkedList.size());
        assertEquals(java.util.List.of(1, 2, 3), linkedList.toList());
    }

    @Test
    void shouldRemoveFirstAndLast() {
        linkedList.addLast(1).addLast(2).addLast(3);

        assertEquals(1, linkedList.removeFirst());
        assertEquals(3, linkedList.removeLast());
        assertEquals(java.util.List.of(2), linkedList.toList());
    }

    @Test
    void shouldRemoveByValueAndContains() {
        linkedList.addLast(1).addLast(2).addLast(3);

        assertTrue(linkedList.remove(2));
        assertFalse(linkedList.contains(2));
        assertEquals(java.util.List.of(1, 3), linkedList.toList());
    }

    @Test
    void shouldReturnNullWhenRemovingFromEmptyList() {
        assertNull(linkedList.removeFirst());
        assertNull(linkedList.removeLast());
    }

    @Test
    void shouldThrowWhenIndexIsInvalid() {
        linkedList.addLast(1);

        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(1));
    }
}
