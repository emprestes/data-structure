package emprestes.ds.linkedlist.kotlin

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SinglyLinkedListTest {
    @Test
    fun `inserts at head tail and index`() {
        val list = SinglyLinkedList<Int>()

        list.addLast(2).addFirst(1).add(2, 4).add(2, 3)

        assertEquals(listOf(1, 2, 3, 4), list.toList())
        assertEquals(4, list.size)
    }

    @Test
    fun `removes at head tail and index`() {
        val list = SinglyLinkedList<Int>()
            .addLast(1).addLast(2).addLast(3).addLast(4)

        assertEquals(1, list.removeFirst())
        assertEquals(4, list.removeLast())
        assertEquals(2, list.removeAt(0))
        assertEquals(listOf(3), list.toList())
    }

    @Test
    fun `removes first matching value including null`() {
        val list = SinglyLinkedList<String?>()
            .addLast("A").addLast(null).addLast("B").addLast(null)

        assertTrue(list.remove(null))
        assertEquals(3, list.size)
        assertEquals("B", list[1])
        assertTrue(null in list)
        assertFalse(list.remove("missing"))
    }

    @Test
    fun `resets head and tail after removing only element`() {
        val list = SinglyLinkedList<Int>().addFirst(1)

        assertEquals(1, list.removeLast())
        assertTrue(list.isEmpty)

        list.addLast(2)
        assertEquals(listOf(2), list.toList())
    }

    @Test
    fun `returns null when removing from empty list`() {
        val list = SinglyLinkedList<Int>()

        assertNull(list.removeFirst())
        assertNull(list.removeLast())
    }

    @Test
    fun `clears every node and allows reuse`() {
        val list = SinglyLinkedList<Int>().addLast(1).addLast(2).addLast(3)

        list.clear()

        assertTrue(list.isEmpty)
        assertEquals(0, list.size)
        assertEquals(emptyList(), list.toList())
        list.addFirst(4)
        assertEquals(listOf(4), list.toList())
    }

    @Test
    fun `iterates from head to tail and rejects past the end`() {
        val list = SinglyLinkedList<Int>().addLast(1).addLast(2).addLast(3)

        assertEquals(listOf(1, 2, 3), list.toList())
        val iterator = list.iterator()
        iterator.next()
        iterator.next()
        iterator.next()
        assertFailsWith<NoSuchElementException> { iterator.next() }
    }

    @Test
    fun `rejects invalid element and insertion indexes`() {
        val list = SinglyLinkedList<Int>().addLast(1)

        assertFailsWith<IndexOutOfBoundsException> { list[-1] }
        assertFailsWith<IndexOutOfBoundsException> { list[1] }
        assertFailsWith<IndexOutOfBoundsException> { list.removeAt(1) }
        assertFailsWith<IndexOutOfBoundsException> { list.add(2, 2) }
    }
}
