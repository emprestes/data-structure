package emprestes.ds.domain.data;

import emprestes.ds.domain.ISort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CharacterQuickSortTest {

    private final ISort engine = new QuickSort();

    @Test
    void testNullInputReturnsNull() {
        assertNull(engine.sort((Character[]) null));
    }

    @Test
    void testEmptyArrayStaysEmpty() {
        var values = new Character[] {};
        var result = engine.sort(values);
        assertArrayEquals(new Character[] {}, result);
    }

    @Test
    void testSingleElementUnchanged() {
        var values = new Character[] { 'a' };
        var result = engine.sort(values);
        assertArrayEquals(new Character[] { 'a' }, result);
    }

    @Test
    void testLowercaseAlphabetSort() {
        var values = new Character[] {
                'm','a','z','b','y','c','x','d','w','e','v','f','u','g','t','h','s','i','r','j','q','k','p','l','o','n'
        };
        var expected = new Character[] {
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
        };

        var result = engine.sort(values);

        assertArrayEquals(expected, result);
    }

    @Test
    void testUppercaseAlphabetSort() {
        var values = new Character[] {
                'Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H','G','F','E','D','C','B','A'
        };
        var expected = new Character[] {
                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
        };

        var result = engine.sort(values);

        assertArrayEquals(expected, result);
    }

    @Test
    void testCharacterWithDuplicates() {
        var values = new Character[] { 'b', 'a', 'c', 'b', 'a', 'd', 'c' };
        var expected = new Character[] { 'a', 'a', 'b', 'b', 'c', 'c', 'd' };

        var result = engine.sort(values);

        assertArrayEquals(expected, result);
    }
}
