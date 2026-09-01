package emprestes.ds.linkedlist.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Mutable singly linked list built from explicit nodes.
 *
 * <p>Each node stores a value and a reference to its successor. The list keeps
 * references to both the head and tail, so insertion at either end is O(1).
 * Reaching an arbitrary position and removing the tail are O(n), because links
 * only point forward.</p>
 *
 * <p>The structural invariants are:</p>
 * <ul>
 *   <li>an empty list has a {@code null} head and tail;</li>
 *   <li>a non-empty list has non-null head and tail references;</li>
 *   <li>the tail always points to {@code null};</li>
 *   <li>exactly {@link #size()} nodes are reachable from the head.</li>
 * </ul>
 *
 * <p>Null values are supported. Consequently, a {@code null} returned by
 * {@link #removeFirst()} or {@link #removeLast()} may mean either an empty list
 * or a stored null; call {@link #isEmpty()} when that distinction matters.</p>
 *
 * @param <E> type of values stored in the list
 */
public final class SinglyLinkedList<E> implements LinkedList<E> {
    private static final class Node<E> {
        private final E value;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    /** {@inheritDoc} Complexity: O(1). */
    @Override
    public SinglyLinkedList<E> addFirst(E value) {
        var node = new Node<>(value);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = node;
        }
        size++;
        return this;
    }

    /** {@inheritDoc} Complexity: O(1). */
    @Override
    public SinglyLinkedList<E> addLast(E value) {
        var node = new Node<>(value);
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
        return this;
    }

    /** {@inheritDoc} Complexity: O(n), except insertion at either end. */
    @Override
    public SinglyLinkedList<E> add(int index, E value) {
        checkPositionIndex(index);
        if (index == 0) {
            return addFirst(value);
        }
        if (index == size) {
            return addLast(value);
        }
        var previous = nodeAt(index - 1);
        var node = new Node<>(value);
        node.next = previous.next;
        previous.next = node;
        size++;
        return this;
    }

    /** {@inheritDoc} Complexity: O(1). */
    @Override
    public E removeFirst() {
        if (head == null) {
            return null;
        }
        var removed = head;
        head = removed.next;
        removed.next = null;
        size--;
        if (size == 0) {
            tail = null;
        }
        return removed.value;
    }

    /** {@inheritDoc} Complexity: O(n), except for an empty or one-element list. */
    @Override
    public E removeLast() {
        if (tail == null) {
            return null;
        }
        if (size == 1) {
            return removeFirst();
        }
        var previous = nodeAt(size - 2);
        var removed = tail;
        previous.next = null;
        tail = previous;
        size--;
        return removed.value;
    }

    /** {@inheritDoc} Complexity: O(n), except removal at the head. */
    @Override
    public E removeAt(int index) {
        checkElementIndex(index);
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        var previous = nodeAt(index - 1);
        var removed = previous.next;
        previous.next = removed.next;
        removed.next = null;
        size--;
        return removed.value;
    }

    /** {@inheritDoc} Complexity: O(n). */
    @Override
    public boolean remove(E value) {
        Node<E> previous = null;
        var current = head;
        while (current != null) {
            if (Objects.equals(current.value, value)) {
                if (previous == null) {
                    removeFirst();
                } else {
                    previous.next = current.next;
                    if (current == tail) {
                        tail = previous;
                    }
                    current.next = null;
                    size--;
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /** {@inheritDoc} Complexity: O(n). */
    @Override
    public boolean contains(E value) {
        for (var current = head; current != null; current = current.next) {
            if (Objects.equals(current.value, value)) {
                return true;
            }
        }
        return false;
    }

    /** {@inheritDoc} Complexity: O(n). */
    @Override
    public E get(int index) {
        checkElementIndex(index);
        return nodeAt(index).value;
    }

    /** {@inheritDoc} Complexity: O(1). */
    @Override
    public int size() {
        return size;
    }

    /** {@inheritDoc} Complexity: O(1). */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** {@inheritDoc} Complexity: O(n). */
    @Override
    public void clear() {
        var current = head;
        while (current != null) {
            var next = current.next;
            current.next = null;
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    /** {@inheritDoc} Complexity: O(n). */
    @Override
    public List<E> toList() {
        var result = new ArrayList<E>(size);
        for (var current = head; current != null; current = current.next) {
            result.add(current.value);
        }
        return result;
    }

    /** Returns an iterator that visits values from head to tail. */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> next = head;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                var value = next.value;
                next = next.next;
                return value;
            }
        };
    }

    private Node<E> nodeAt(int index) {
        var current = head;
        for (int position = 0; position < index; position++) {
            current = current.next;
        }
        return current;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }
}
