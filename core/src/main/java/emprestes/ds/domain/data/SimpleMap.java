package emprestes.ds.domain.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Thin wrapper around {@link java.util.HashMap} for educational purposes.
 *
 * @param <K> key type
 * @param <V> value type
 */
public class SimpleMap<K, V> {

    private final Map<K, V> values = new HashMap<>();

    /**
     * Associates a value with a key.
     *
     * @param key map key
     * @param value map value
     * @return current map instance
     */
    public SimpleMap<K, V> put(K key, V value) {
        if (key != null) {
            values.put(key, value);
        }
        return this;
    }

    /**
     * Returns the value associated with a key.
     *
     * @param key map key
     * @return associated value, or {@code null} when absent
     */
    public V get(K key) {
        return values.get(key);
    }

    /**
     * Removes a mapping by key.
     *
     * @param key map key
     * @return removed value, or {@code null} when absent
     */
    public V remove(K key) {
        return values.remove(key);
    }

    /**
     * Checks whether the map contains the key.
     *
     * @param key map key
     * @return {@code true} when key exists
     */
    public boolean containsKey(K key) {
        return values.containsKey(key);
    }

    /**
     * Returns the current number of key-value pairs.
     *
     * @return map size
     */
    public int size() {
        return values.size();
    }

    /**
     * Indicates whether the map has no entries.
     *
     * @return {@code true} when empty
     */
    public boolean isEmpty() {
        return values.isEmpty();
    }

    /**
     * Returns an immutable view of keys.
     *
     * @return map keys
     */
    public Set<K> keys() {
        return Set.copyOf(values.keySet());
    }
}
