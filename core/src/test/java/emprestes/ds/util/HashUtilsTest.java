package emprestes.ds.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HashUtilsTest {

    @Test
    void deveConverterHashParaHexadecimalComZerosAEsquerda() {
        String hex = HashUtils.toHexHash("hello");
        assertEquals("05e918d2", hex);
    }

    @Test
    void testURL() {
        String hex = HashUtils.toHexHash("https://medium.com/@noahblogwriter2025/the-12-data-structures-every-developer-should-master-before-an-interview-a5be06a5c71d");
        assertEquals("05e918d2", hex);
    }

    @Test
    void deveLidarComValorVazio() {
        String hex = HashUtils.toHexHash("");
        assertEquals("00000000", hex);
    }

    @Test
    void deveLancarExcecaoQuandoValorForNulo() {
        assertThrows(NullPointerException.class, () -> HashUtils.toHexHash(null));
    }
}
