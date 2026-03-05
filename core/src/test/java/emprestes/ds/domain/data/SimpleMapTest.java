package emprestes.ds.domain.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleMapTest {

    private SimpleMap<String, Integer> map;

    @BeforeEach
    void setUp() {
        map = new SimpleMap<>();
    }

    @Test
    void shouldPutAndGetValues() {
        map.put("a", 1).put("b", 2);

        assertEquals(2, map.size());
        assertEquals(1, map.get("a"));
        assertTrue(map.containsKey("b"));
    }

    @Test
    void shouldOverwriteAndRemoveValues() {
        map.put("a", 1).put("a", 2);

        assertEquals(2, map.get("a"));
        assertEquals(2, map.remove("a"));
        assertFalse(map.containsKey("a"));
    }
}
