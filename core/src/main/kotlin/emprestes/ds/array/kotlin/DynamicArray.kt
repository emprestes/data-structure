package emprestes.ds.array.kotlin

/**
 * Resizable array implemented on top of a contiguous block of references.
 *
 * The structure separates two concepts that are often confused: [size] is the
 * number of visible elements, while [capacity] is the number of slots reserved
 * in the internal storage. When an insertion does not fit, the capacity doubles
 * and the elements are copied into a new block. Therefore, appending costs
 * amortized O(1), although the operation that grows the array costs O(n).
 *
 * This implementation accepts null values and does not use [MutableList]
 * internally, so the mechanics of the data structure remain explicit.
 *
 * @param E type of the stored elements.
 * @property initialCapacity initial number of reserved slots; must be positive.
 */
class DynamicArray<E>(initialCapacity: Int = DEFAULT_CAPACITY) : Iterable<E> {
    private var elements: Array<Any?>

    /** Number of elements currently stored. */
    var size: Int = 0
        private set

    /** Number of elements that fit without another reallocation. */
    val capacity: Int
        get() = elements.size

    /** Whether the structure contains no elements. */
    val isEmpty: Boolean
        get() = size == 0

    init {
        require(initialCapacity > 0) { "initialCapacity must be greater than zero" }
        elements = arrayOfNulls(initialCapacity)
    }

    /**
     * Appends [element] to the end.
     *
     * Complexity: amortized O(1), and O(n) in the worst case when storage grows.
     */
    fun add(element: E) {
        ensureCapacity(size + 1)
        elements[size++] = element
    }

    /**
     * Inserts [element] at [index], shifting successors one position to the right.
     *
     * [index] may equal [size], in which case this operation is equivalent to [add].
     * Complexity: O(n).
     */
    fun add(index: Int, element: E) {
        checkPositionIndex(index)
        ensureCapacity(size + 1)
        elements.copyInto(elements, destinationOffset = index + 1, startIndex = index, endIndex = size)
        elements[index] = element
        size++
    }

    /** Returns the element at [index]. Complexity: O(1). */
    operator fun get(index: Int): E {
        checkElementIndex(index)
        return elementAt(index)
    }

    /** Replaces and returns the previous value at [index]. Complexity: O(1). */
    operator fun set(index: Int, element: E): E {
        checkElementIndex(index)
        val previous = elementAt(index)
        elements[index] = element
        return previous
    }

    /**
     * Removes and returns the element at [index], closing the gap left behind.
     *
     * Complexity: O(n), because successors must be shifted.
     */
    fun removeAt(index: Int): E {
        checkElementIndex(index)
        val removed = elementAt(index)
        val lastIndex = size - 1
        if (index < lastIndex) {
            elements.copyInto(elements, destinationOffset = index, startIndex = index + 1, endIndex = size)
        }
        elements[lastIndex] = null
        size--
        return removed
    }

    /** Removes every element without shrinking reserved capacity. Complexity: O(n). */
    fun clear() {
        elements.fill(null, fromIndex = 0, toIndex = size)
        size = 0
    }

    /** Creates a read-only list with the current elements. Complexity: O(n). */
    fun toList(): List<E> = List(size) { elementAt(it) }

    /** Iterates in index order, from zero through [size] - 1. */
    override fun iterator(): Iterator<E> = object : Iterator<E> {
        private var cursor = 0

        override fun hasNext(): Boolean = cursor < size

        override fun next(): E {
            if (!hasNext()) throw NoSuchElementException()
            return elementAt(cursor++)
        }
    }

    private fun ensureCapacity(requiredCapacity: Int) {
        if (requiredCapacity <= capacity) return
        var newCapacity = capacity
        while (newCapacity < requiredCapacity) newCapacity *= GROWTH_FACTOR
        elements = elements.copyOf(newCapacity)
    }

    @Suppress("UNCHECKED_CAST")
    private fun elementAt(index: Int): E = elements[index] as E

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

    private companion object {
        const val DEFAULT_CAPACITY = 10
        const val GROWTH_FACTOR = 2
    }
}
