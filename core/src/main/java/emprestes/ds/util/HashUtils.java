package emprestes.ds.util;

import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * Utility helpers for working with hash values.
 */
@UtilityClass
public class HashUtils {

    /**
     * Converts {@link String#hashCode()} to an eight-digit hexadecimal value.
     *
     * @param value source text; must not be {@code null}
     * @return zero-padded hexadecimal hash, for example {@code "05e918d2"}
     */
    public static String toHexHash(String value) {
        Objects.requireNonNull(value, "value must not be null");
        int hash = value.hashCode();
        return String.format("%08x", hash);
    }
}
