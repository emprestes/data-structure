package emprestes.ds.array.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DynamicArrayTest {

    private DynamicArray<String> array;

    @BeforeEach
    void setUp() {
        array = new DynamicArray<>();
    }

    @Test
    void shouldAddAndGetElements() {
        array.add("a").add("b").add("c");

        assertEquals(3, array.size());
        assertEquals("b", array.get(1));
    }

    @Test
    void shouldSetAndRemoveByIndex() {
        array.add("a").add("b").add("c");
        array.set(1, "x");

        assertEquals("x", array.get(1));
        assertEquals("x", array.removeAt(1));
        assertEquals(java.util.List.of("a", "c"), array.toList());
    }

    @Test
    void shouldResizeAutomatically() {
        for (int i = 0; i < 20; i++) {
            array.add("v" + i);
        }

        assertEquals(20, array.size());
        assertEquals("v19", array.get(19));
    }

    @Test
    void shouldHandleContainsAndInvalidIndex() {
        array.add("a");

        assertTrue(array.contains("a"));
        assertFalse(array.contains("z"));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(1));
    }
}
