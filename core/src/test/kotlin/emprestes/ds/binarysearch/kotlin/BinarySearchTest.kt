package emprestes.ds.binarysearch.kotlin

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BinarySearchTest {
    private val search = BinarySearch()

    @Test
    fun `finds values at beginning middle and end iteratively`() {
        val values = listOf(1, 3, 7, 9, 14)

        assertEquals(0, search.iterativeIndexOf(values, 1))
        assertEquals(2, search.iterativeIndexOf(values, 7))
        assertEquals(4, search.iterativeIndexOf(values, 14))
    }

    @Test
    fun `finds values at beginning middle and end recursively`() {
        val values = listOf("Ada", "Edsger", "Grace", "Linus", "Margaret")

        assertEquals(0, search.recursiveIndexOf(values, "Ada"))
        assertEquals(2, search.recursiveIndexOf(values, "Grace"))
        assertEquals(4, search.recursiveIndexOf(values, "Margaret"))
    }

    @Test
    fun `returns minus one for absent empty and null inputs`() {
        assertEquals(-1, search.iterativeIndexOf(listOf(1, 3, 5), 4))
        assertEquals(-1, search.recursiveIndexOf(listOf(1, 3, 5), 4))
        assertEquals(-1, search.iterativeIndexOf(emptyList<Int>(), 4))
        assertEquals(-1, search.recursiveIndexOf(emptyList<Int>(), 4))
        assertEquals(-1, search.iterativeIndexOf<Int>(null, 4))
        assertEquals(-1, search.recursiveIndexOf<Int>(null, 4))
        assertEquals(-1, search.iterativeIndexOf(listOf(1), null))
        assertEquals(-1, search.recursiveIndexOf(listOf(1), null))
    }

    @Test
    fun `returns an index containing the target when duplicates exist`() {
        val values = listOf(1, 2, 2, 2, 3)

        val iterativeIndex = search.iterativeIndexOf(values, 2)
        val recursiveIndex = search.recursiveIndexOf(values, 2)

        assertTrue(iterativeIndex in 1..3)
        assertTrue(recursiveIndex in 1..3)
        assertEquals(2, values[iterativeIndex])
        assertEquals(2, values[recursiveIndex])
    }

    @Test
    fun `handles single element and even length inputs`() {
        assertEquals(0, search.iterativeIndexOf(listOf(42), 42))
        assertEquals(0, search.recursiveIndexOf(listOf(42), 42))
        assertEquals(3, search.iterativeIndexOf(listOf(1, 2, 3, 4), 4))
        assertEquals(3, search.recursiveIndexOf(listOf(1, 2, 3, 4), 4))
    }
}
