package emprestes.ds.domain.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleSetTest {

    private SimpleSet<Integer> set;

    @BeforeEach
    void setUp() {
        set = new SimpleSet<>();
    }

    @Test
    void shouldAddUniqueValues() {
        set.add(1).add(1).add(2);

        assertEquals(2, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
    }

    @Test
    void shouldRemoveValues() {
        set.add(1).add(2);

        assertTrue(set.remove(1));
        assertFalse(set.contains(1));
        assertEquals(1, set.size());
    }
}
