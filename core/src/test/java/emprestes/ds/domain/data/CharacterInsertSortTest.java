package emprestes.ds.domain.data;

import emprestes.ds.domain.ISort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharacterInsertSortTest {

    private final ISort engine = new InsertSort();

    @Test
    void testLowercaseAlphabetSort() {
        var values = new Character[] {
                'm','a','z','b','y','c','x','d','w','e','v','f','u','g','t','h','s','i','r','j','q','k','p','l','o','n'
        };
        var expected = new Character[] {
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
        };

        var result = engine.sort(values);

        Assertions.assertArrayEquals(expected, result);
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

        Assertions.assertArrayEquals(expected, result);
    }
}
