package emprestes.ds.kotlin.array

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DynamicArrayTest {
    @Test
    fun `adds and reads elements by index`() {
        val array = DynamicArray<String>(initialCapacity = 2)

        array.add("A")
        array.add("B")

        assertEquals(2, array.size)
        assertEquals("A", array[0])
        assertEquals("B", array[1])
    }

    @Test
    fun `grows storage when capacity is exhausted`() {
        val array = DynamicArray<Int>(initialCapacity = 2)

        repeat(5) { array.add(it) }

        assertEquals(5, array.size)
        assertEquals(8, array.capacity)
        assertEquals(listOf(0, 1, 2, 3, 4), array.toList())
    }

    @Test
    fun `inserts at beginning middle and end`() {
        val array = DynamicArray<Int>(initialCapacity = 1)

        array.add(2)
        array.add(0, 1)
        array.add(2, 4)
        array.add(2, 3)

        assertEquals(listOf(1, 2, 3, 4), array.toList())
    }

    @Test
    fun `replaces and returns previous element`() {
        val array = DynamicArray<String>().apply { add("old") }

        assertEquals("old", array.set(0, "new"))
        assertEquals("new", array[0])
    }

    @Test
    fun `removes element and closes the gap`() {
        val array = DynamicArray<Int>().apply { repeat(4) { add(it) } }

        assertEquals(1, array.removeAt(1))
        assertEquals(listOf(0, 2, 3), array.toList())
    }

    @Test
    fun `supports nullable elements`() {
        val array = DynamicArray<String?>().apply { add(null) }

        assertNull(array[0])
    }

    @Test
    fun `clears elements but preserves allocated capacity`() {
        val array = DynamicArray<Int>(2).apply { repeat(3) { add(it) } }
        val allocatedCapacity = array.capacity

        array.clear()

        assertTrue(array.isEmpty)
        assertEquals(0, array.size)
        assertEquals(allocatedCapacity, array.capacity)
        assertFalse(array.iterator().hasNext())
    }

    @Test
    fun `rejects invalid capacities and indexes`() {
        assertFailsWith<IllegalArgumentException> { DynamicArray<Int>(0) }

        val array = DynamicArray<Int>()
        assertFailsWith<IndexOutOfBoundsException> { array[0] }
        assertFailsWith<IndexOutOfBoundsException> { array.add(1, 42) }
    }
}
