package emprestes.ds.linkedlist.kotlin

/**
 * Mutable singly linked list built from explicit nodes.
 *
 * Every node stores a value and a reference to its successor. Keeping both
 * [head] and [tail] makes insertion at either end O(1). Reaching an arbitrary
 * position and removing the tail remain O(n), because links only point forward.
 *
 * The structure maintains four invariants:
 * 1. an empty list has a null head and tail;
 * 2. a non-empty list has non-null head and tail references;
 * 3. the tail always points to null;
 * 4. exactly [size] nodes are reachable from the head.
 *
 * Null values are supported. Therefore, null returned by [removeFirst] or
 * [removeLast] may represent either an empty list or a stored null; inspect
 * [isEmpty] first when the distinction matters.
 *
 * @param E type of values stored in the list
 */
class SinglyLinkedList<E> : Iterable<E> {
    private class Node<E>(val value: E, var next: Node<E>? = null)

    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    /** Number of nodes currently stored. Complexity: O(1). */
    var size: Int = 0
        private set

    /** Whether this list contains no nodes. Complexity: O(1). */
    val isEmpty: Boolean
        get() = size == 0

    /** Adds [value] at the head and returns this list. Complexity: O(1). */
    fun addFirst(value: E): SinglyLinkedList<E> {
        val node = Node(value, head)
        head = node
        if (tail == null) tail = node
        size++
        return this
    }

    /** Adds [value] at the tail and returns this list. Complexity: O(1). */
    fun addLast(value: E): SinglyLinkedList<E> {
        val node = Node(value)
        if (tail == null) {
            head = node
        } else {
            tail?.next = node
        }
        tail = node
        size++
        return this
    }

    /**
     * Inserts [value] at [index] and returns this list.
     *
     * [index] may range from zero through [size]. Complexity: O(n), except at
     * either end.
     */
    fun add(index: Int, value: E): SinglyLinkedList<E> {
        checkPositionIndex(index)
        if (index == 0) return addFirst(value)
        if (index == size) return addLast(value)
        val previous = nodeAt(index - 1)
        previous.next = Node(value, previous.next)
        size++
        return this
    }

    /** Removes and returns the head value, or null when empty. Complexity: O(1). */
    fun removeFirst(): E? {
        val removed = head ?: return null
        head = removed.next
        removed.next = null
        size--
        if (size == 0) tail = null
        return removed.value
    }

    /** Removes and returns the tail value, or null when empty. Complexity: O(n). */
    fun removeLast(): E? {
        val removed = tail ?: return null
        if (size == 1) return removeFirst()
        val previous = nodeAt(size - 2)
        previous.next = null
        tail = previous
        size--
        return removed.value
    }

    /** Removes and returns the value at [index]. Complexity: O(n). */
    fun removeAt(index: Int): E {
        checkElementIndex(index)
        if (index == 0) return removeFirstValue()
        if (index == size - 1) return removeLastValue()
        val previous = nodeAt(index - 1)
        val removed = requireNotNull(previous.next)
        previous.next = removed.next
        removed.next = null
        size--
        return removed.value
    }

    /** Removes the first occurrence of [value]. Complexity: O(n). */
    fun remove(value: E): Boolean {
        var previous: Node<E>? = null
        var current = head
        while (current != null) {
            if (current.value == value) {
                if (previous == null) {
                    removeFirst()
                } else {
                    previous.next = current.next
                    if (current === tail) tail = previous
                    current.next = null
                    size--
                }
                return true
            }
            previous = current
            current = current.next
        }
        return false
    }

    /** Returns whether [value] occurs in the list. Complexity: O(n). */
    operator fun contains(value: E): Boolean {
        var current = head
        while (current != null) {
            if (current.value == value) return true
            current = current.next
        }
        return false
    }

    /** Returns the value at [index]. Complexity: O(n). */
    operator fun get(index: Int): E {
        checkElementIndex(index)
        return nodeAt(index).value
    }

    /** Removes every node while preserving an empty reusable list. Complexity: O(n). */
    fun clear() {
        var current = head
        while (current != null) {
            val next = current.next
            current.next = null
            current = next
        }
        head = null
        tail = null
        size = 0
    }

    /** Returns a read-only snapshot in head-to-tail order. Complexity: O(n). */
    fun toList(): List<E> = buildList(size) {
        var current = head
        while (current != null) {
            add(current.value)
            current = current.next
        }
    }

    /** Returns an iterator that visits values from head to tail. */
    override fun iterator(): Iterator<E> = object : Iterator<E> {
        private var next = head

        override fun hasNext(): Boolean = next != null

        override fun next(): E {
            val current = next ?: throw NoSuchElementException()
            next = current.next
            return current.value
        }
    }

    private fun removeFirstValue(): E {
        val removed = requireNotNull(head)
        head = removed.next
        removed.next = null
        size--
        if (size == 0) tail = null
        return removed.value
    }

    private fun removeLastValue(): E {
        val removed = requireNotNull(tail)
        if (size == 1) return removeFirstValue()
        val previous = nodeAt(size - 2)
        previous.next = null
        tail = previous
        size--
        return removed.value
    }

    private fun nodeAt(index: Int): Node<E> {
        var current = requireNotNull(head)
        repeat(index) { current = requireNotNull(current.next) }
        return current
    }

    private fun checkElementIndex(index: Int) {
        if (index !in 0 until size) {
            throw IndexOutOfBoundsException("index=$index, size=$size")
        }
    }

    private fun checkPositionIndex(index: Int) {
        if (index !in 0..size) {
            throw IndexOutOfBoundsException("index=$index, size=$size")
        }
    }
}
