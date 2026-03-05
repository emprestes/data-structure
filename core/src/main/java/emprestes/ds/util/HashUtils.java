package emprestes.ds.util;

import java.util.Objects;

/**
 * Utility helpers for working with hash values.
 */
public final class HashUtils {

    /**
     * Utility class constructor.
     */
    private HashUtils() {
    }

    /**
     * Converte o {@link String#hashCode()} em uma representação hexadecimal com 8 dígitos.
     *
     * @param value texto de origem (não pode ser {@code null})
     * @return hash em hexadecimal, zero‑padded, ex.: {@code "05e918d2"}
     */
    public static String toHexHash(String value) {
        Objects.requireNonNull(value, "value must not be null");
        int hash = value.hashCode();
        return String.format("%x", hash);
    }
}
