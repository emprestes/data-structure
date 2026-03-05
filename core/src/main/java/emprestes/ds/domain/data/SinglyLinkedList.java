package emprestes.ds.domain.data;

import emprestes.ds.domain.LinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Singly linked list implementation.
 *
 * @param <T> stored value type
 */
public class SinglyLinkedList<T> implements LinkedList<T> {

    /**
     * Node type for list entries.
     *
     * @param <T> node value type
     */
    private static class Entry<T> {
        private final T value;
        private Entry<T> next;

        /**
         * Creates an entry with a value.
         *
         * @param value entry value
         */
        private Entry(T value) {
            this.value = value;
        }
    }

    private Entry<T> head;
    private Entry<T> tail;
    private int size;

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<T> addFirst(T value) {
        if (value == null) {
            return this;
        }

        var entry = new Entry<>(value);
        entry.next = head;
        head = entry;
        if (tail == null) {
            tail = entry;
        }
        size++;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<T> addLast(T value) {
        if (value == null) {
            return this;
        }

        var entry = new Entry<>(value);
        if (tail == null) {
            head = entry;
            tail = entry;
        } else {
            tail.next = entry;
            tail = entry;
        }
        size++;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeFirst() {
        if (head == null) {
            return null;
        }

        var value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeLast() {
        if (tail == null) {
            return null;
        }

        if (head == tail) {
            var value = tail.value;
            head = null;
            tail = null;
            size--;
            return value;
        }

        var current = head;
        while (current.next != tail) {
            current = current.next;
        }
        var value = tail.value;
        current.next = null;
        tail = current;
        size--;
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(T value) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(head.value, value)) {
            removeFirst();
            return true;
        }

        var previous = head;
        var current = head.next;
        while (current != null) {
            if (Objects.equals(current.value, value)) {
                previous.next = current.next;
                if (current == tail) {
                    tail = previous;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T value) {
        var current = head;
        while (current != null) {
            if (Objects.equals(current.value, value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        int i = 0;
        var current = head;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> toList() {
        var list = new ArrayList<T>(size);
        var current = head;
        while (current != null) {
            list.add(current.value);
            current = current.next;
        }
        return list;
    }
}
