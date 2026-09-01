package emprestes.ds.domain.util;

import lombok.experimental.UtilityClass;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Utility to encode and decode URLs using a custom Base64 URL-safe alphabet,
 * optional DEFLATE compression (applied only when it shrinks), and small prefix
 * tokens for common schemes.
 */
@UtilityClass
public class URLHelper {

    private static final char[] BASE64_URL_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
    private static final int[] BASE64_URL_REVERSE = new int[128];

    static {
        for (int i = 0; i < BASE64_URL_REVERSE.length; i++) {
            BASE64_URL_REVERSE[i] = -1;
        }
        for (int i = 0; i < BASE64_URL_ALPHABET.length; i++) {
            BASE64_URL_REVERSE[BASE64_URL_ALPHABET[i]] = i;
        }
    }

    /**
     * Encodes a URL into a compact Base64 URL-safe string without padding.
     * Applies light normalization and compression when it helps.
     *
     * @param url original URL
     * @return encoded representation
     */
    public static String encode(String url) {
        Objects.requireNonNull(url, "url must not be null");
        String trimmed = url.strip();
        validateUrl(trimmed);

        String normalized = normalize(trimmed);
        String shortened = shortenPrefix(normalized);

        byte[] rawBytes = shortened.getBytes(StandardCharsets.UTF_8);
        byte[] compressed = deflate(rawBytes);
        boolean useCompressed = compressed.length < rawBytes.length;
        byte[] payload = useCompressed ? compressed : rawBytes;

        // prepend flag byte: 1 = compressed, 0 = plain
        byte[] withFlag = new byte[payload.length + 1];
        withFlag[0] = (byte) (useCompressed ? 1 : 0);
        System.arraycopy(payload, 0, withFlag, 1, payload.length);

        return base64UrlEncode(withFlag);
    }

    /**
     * Decodes the encoded string back into the original URL.
     *
     * @param encoded encoded representation
     * @return decoded URL string
     */
    public static String decode(String encoded) {
        Objects.requireNonNull(encoded, "encoded value must not be null");
        String trimmed = encoded.strip();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("encoded value must not be blank");
        }

        String decoded;
        try {
            byte[] allBytes = base64UrlDecode(trimmed);
            if (allBytes.length == 0) {
                throw new IllegalArgumentException("encoded value is not valid Base64");
            }
            boolean compressed = allBytes[0] == 1;
            byte[] payload = new byte[allBytes.length - 1];
            System.arraycopy(allBytes, 1, payload, 0, payload.length);

            byte[] inflated = compressed ? inflate(payload) : payload;
            decoded = expandPrefix(new String(inflated, StandardCharsets.UTF_8));
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException("encoded value is not valid Base64", e);
        }

        validateUrl(decoded);
        return decoded;
    }

    /**
     * Validates URL syntax and required components.
     *
     * @param value URL string to validate
     */
    private static void validateUrl(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("url must not be blank");
        }

        try {
            URI uri = new URI(value);
            if (uri.getScheme() == null || uri.getHost() == null) {
                throw new IllegalArgumentException("url must contain scheme and host");
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("url is not syntactically valid", e);
        }
    }

    /**
     * Normalizes scheme, host and default ports.
     *
     * @param url source URL
     * @return normalized URL
     */
    private static String normalize(String url) {
        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme().toLowerCase(Locale.ROOT);
            String host = uri.getHost() != null ? uri.getHost().toLowerCase(Locale.ROOT) : null;

            int port = uri.getPort();
            boolean isDefaultPort = (scheme.equals("http") && port == 80) || (scheme.equals("https") && port == 443);
            String portPart = (port == -1 || isDefaultPort) ? "" : ":" + port;

            String path = uri.getRawPath() == null ? "" : uri.getRawPath();
            String query = uri.getRawQuery() == null ? "" : "?" + uri.getRawQuery();

            return scheme + "://" + host + portPart + path + query;
        } catch (URISyntaxException e) {
            return url;
        }
    }

    /**
     * Replaces common schemes with short one-character tokens.
     *
     * @param url source URL
     * @return URL with compact prefix
     */
    private static String shortenPrefix(String url) {
        if (url.startsWith("https://")) {
            return "~" + url.substring("https://".length());
        }
        if (url.startsWith("http://")) {
            return "`" + url.substring("http://".length());
        }
        return url;
    }

    /**
     * Restores compact prefix tokens to full URL schemes.
     *
     * @param shortened compact URL
     * @return URL with full scheme
     */
    private static String expandPrefix(String shortened) {
        if (shortened.startsWith("~")) {
            return "https://" + shortened.substring(1);
        }
        if (shortened.startsWith("`")) {
            return "http://" + shortened.substring(1);
        }
        return shortened;
    }

    /**
     * Compresses payload bytes using raw DEFLATE.
     *
     * @param input source bytes
     * @return compressed bytes
     */
    private static byte[] deflate(byte[] input) {
        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION, true);
        deflater.setInput(input);
        deflater.finish();

        byte[] buffer = new byte[1024];
        int read;
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream(input.length);
        while (!deflater.finished()) {
            read = deflater.deflate(buffer);
            bos.write(buffer, 0, read);
        }
        return bos.toByteArray();
    }

    /**
     * Decompresses raw DEFLATE payload bytes.
     *
     * @param input compressed bytes
     * @return decompressed bytes
     */
    private static byte[] inflate(byte[] input) {
        Inflater inflater = new Inflater(true);
        inflater.setInput(input);
        byte[] buffer = new byte[1024];
        int read;
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream(input.length * 2);
        try {
            while (!inflater.finished()) {
                try {
                    read = inflater.inflate(buffer);
                } catch (DataFormatException e) {
                    throw new IllegalArgumentException("compressed payload is invalid", e);
                }
                if (read == 0) {
                    break;
                }
                bos.write(buffer, 0, read);
            }
            return bos.toByteArray();
        } finally {
            inflater.end();
        }
    }

    /**
     * Encodes bytes with a custom URL-safe Base64 alphabet without padding.
     *
     * @param data bytes to encode
     * @return encoded text
     */
    private static String base64UrlEncode(byte[] data) {
        StringBuilder sb = new StringBuilder((data.length * 4 + 2) / 3);
        for (int i = 0; i < data.length; ) {
            int rem = data.length - i;
            int b0 = data[i++] & 0xFF;
            int b1 = rem > 1 ? data[i++] & 0xFF : 0;
            int b2 = rem > 2 ? data[i++] & 0xFF : 0;

            int chunk = (b0 << 16) | (b1 << 8) | b2;
            sb.append(BASE64_URL_ALPHABET[(chunk >> 18) & 0x3F]);
            sb.append(BASE64_URL_ALPHABET[(chunk >> 12) & 0x3F]);
            if (rem > 1) {
                sb.append(BASE64_URL_ALPHABET[(chunk >> 6) & 0x3F]);
            }
            if (rem > 2) {
                sb.append(BASE64_URL_ALPHABET[chunk & 0x3F]);
            }
        }
        return sb.toString();
    }

    /**
     * Decodes text encoded by {@link #base64UrlEncode(byte[])}.
     *
     * @param value encoded text
     * @return decoded bytes
     */
    private static byte[] base64UrlDecode(String value) {
        int len = value.length();
        int buffer = 0;
        int bits = 0;
        byte[] out = new byte[(len * 6 + 7) / 8];
        int outIdx = 0;

        for (int i = 0; i < len; i++) {
            int val = charToSixBits(value.charAt(i));
            buffer = (buffer << 6) | val;
            bits += 6;
            while (bits >= 8) {
                bits -= 8;
                out[outIdx++] = (byte) (buffer >> bits);
                buffer &= (1 << bits) - 1;
            }
        }

        byte[] result = new byte[outIdx];
        System.arraycopy(out, 0, result, 0, outIdx);
        return result;
    }

    /**
     * Maps one encoded character to its 6-bit value.
     *
     * @param c encoded character
     * @return 6-bit value for the character
     */
    private static int charToSixBits(char c) {
        if (c >= BASE64_URL_REVERSE.length || BASE64_URL_REVERSE[c] == -1) {
            throw new IllegalArgumentException("encoded value contains invalid Base64 characters");
        }
        return BASE64_URL_REVERSE[c];
    }
}
