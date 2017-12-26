package structures.commons;

/**
 * Key-value pair object for use in Objects such as Maps and Trees.
 *
 * @author Jabari Dash
 * @param <K> Key parameter type.
 * @param <V> Value parameter type.
 */
public class Pair<K, V> {

    /**
     * Key.
     */
    private K key;

    /**
     * Value.
     */
    private V value;

    /**
     * Constructs a new key-value pair.
     *
     * @param key Key
     * @param value Value
     */
    @SuppressWarnings("unused")
    public Pair(K key, V value) {
        this.key(key);
        this.value(value);
    }

    /**
     * Returns the key.
     *
     * @return The key.
     */
    @SuppressWarnings("unused")
    public K key() {
        return this.key;
    }

    /**
     * Sets the key.
     *
     * @param key The new key.
     */
    @SuppressWarnings("unused")
    public void key(K key) {
        this.key = key;
    }

    /**
     * Returns a JSON String representation of the pair.
     *
     * @return String version of the pair.
     */
    @SuppressWarnings("unused")
    public String toString() {
        String k;
        String v;

        k = key == null ? "null" : key.toString();
        v = value == null ? "null" : value.toString();

        return "{key: " + k + ", value: " + v + "}";
    }

    /**
     * Returns the value.
     *
     * @return The value.
     */
    @SuppressWarnings("unused")
    public V value() {
        return this.value;
    }

    /**
     * Sets the value.
     *
     * @param value The new value.
     */
    @SuppressWarnings("unused")
    public void value(V value) {
        this.value = value;
    }
}
