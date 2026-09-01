package emprestes.ds.linkedlist.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SinglyLinkedListTest {
    @Test
    void insertsAtHeadTailAndIndex() {
        var list = new SinglyLinkedList<Integer>();

        list.addLast(2).addFirst(1).add(2, 4).add(2, 3);

        assertEquals(List.of(1, 2, 3, 4), list.toList());
        assertEquals(4, list.size());
    }

    @Test
    void removesAtHeadTailAndIndex() {
        var list = new SinglyLinkedList<Integer>();
        list.addLast(1).addLast(2).addLast(3).addLast(4);

        assertEquals(1, list.removeFirst());
        assertEquals(4, list.removeLast());
        assertEquals(2, list.removeAt(0));
        assertEquals(List.of(3), list.toList());
    }

    @Test
    void removesFirstMatchingValueIncludingNull() {
        var list = new SinglyLinkedList<String>();
        list.addLast("A").addLast(null).addLast("B").addLast(null);

        assertTrue(list.remove(null));
        assertEquals(3, list.size());
        assertEquals("B", list.get(1));
        assertTrue(list.contains(null));
        assertFalse(list.remove("missing"));
    }

    @Test
    void resetsHeadAndTailAfterRemovingOnlyElement() {
        var list = new SinglyLinkedList<Integer>();
        list.addFirst(1);

        assertEquals(1, list.removeLast());
        assertTrue(list.isEmpty());

        list.addLast(2);
        assertEquals(List.of(2), list.toList());
    }

    @Test
    void returnsNullWhenRemovingFromEmptyList() {
        var list = new SinglyLinkedList<Integer>();

        assertNull(list.removeFirst());
        assertNull(list.removeLast());
    }

    @Test
    void clearsEveryNodeAndAllowsReuse() {
        var list = new SinglyLinkedList<Integer>();
        list.addLast(1).addLast(2).addLast(3);

        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertEquals(List.of(), list.toList());
        list.addFirst(4);
        assertEquals(List.of(4), list.toList());
    }

    @Test
    void iteratesFromHeadToTailAndRejectsPastTheEnd() {
        var list = new SinglyLinkedList<Integer>();
        list.addLast(1).addLast(2).addLast(3);
        var values = new ArrayList<Integer>();

        list.forEach(values::add);

        assertEquals(List.of(1, 2, 3), values);
        var iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void rejectsInvalidElementAndInsertionIndexes() {
        var list = new SinglyLinkedList<Integer>();
        list.addLast(1);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 2));
    }
}
