package emprestes.ds.domain.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class URLHelperTest {

    private static final String GOOGLE_URL = "https://www.google.com";
    private static final String GOOGLE_ENCODED = "AH53d3cuZ29vZ2xlLmNvbQ";
    private static final String NON_URL_ENCODED = "AG5vdC11cmw"; // decodes to "not-url"

    @Nested
    class Encode {
        @Test
        void encodesUrlWithCustomBase64() {
            String encoded = URLHelper.encode(GOOGLE_URL);
            assertEquals(GOOGLE_ENCODED, encoded);
        }

        @Test
        void trimsWhitespaceBeforeEncoding() {
            String encoded = URLHelper.encode("  " + GOOGLE_URL + "  ");
            assertEquals(GOOGLE_ENCODED, encoded);
        }

        @Test
        void rejectsNullInput() {
            assertThrows(NullPointerException.class, () -> URLHelper.encode(null));
        }

        @Test
        void rejectsBlankInput() {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> URLHelper.encode("   "));
            assertEquals("url must not be blank", ex.getMessage());
        }

        @Test
        void rejectsMissingSchemeOrHost() {
            assertThrows(IllegalArgumentException.class, () -> URLHelper.encode("www.google.com"));
            assertThrows(IllegalArgumentException.class, () -> URLHelper.encode("https:///"));
        }
    }

    @Nested
    class Decode {
        @Test
        void decodesBackToOriginalUrl() {
            var url = "AQ3LQQ4CIQxG4RPVmSFBt16l0F8hAWoKzMSNZ5fVy7d4vwrJs96i1u3ZlFMo-r4sD5jbnd9GAh2OhAdTHzbjmIZOOGFfkpWiHxj1pLMIVe5rpICXGogb5bZ8ZlzEPmC_s4-PQ_4";
            String decoded = URLHelper.decode(url);
            assertEquals("https://medium.com/@noahblogwriter2025/the-12-data-structures-every-developer-should-master-before-an-interview-a5be06a5c71d", decoded);
        }

        @Test
        void roundTripPreservesValue() {
            String encoded = URLHelper.encode(GOOGLE_URL);
            String decoded = URLHelper.decode(encoded);
            assertEquals(GOOGLE_URL, decoded);
        }

        @Test
        void rejectsNullInput() {
            assertThrows(NullPointerException.class, () -> URLHelper.decode(null));
        }

        @Test
        void rejectsBlankInput() {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> URLHelper.decode("   "));
            assertEquals("encoded value must not be blank", ex.getMessage());
        }

        @Test
        void rejectsInvalidBase64Characters() {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> URLHelper.decode("@@@"));
            assertEquals("encoded value contains invalid Base64 characters", ex.getMessage());
        }

        @Test
        void rejectsDecodedValueThatIsNotUrl() {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> URLHelper.decode(NON_URL_ENCODED));
            assertEquals("url must contain scheme and host", ex.getMessage());
        }
    }
}
