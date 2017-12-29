package structures.commons;

/**
 * Key-value pair object for use in Objects such as Maps and Trees.
 *
 * @author Jabari Dash
 * @param <K> Key parameter type.
 * @param <V> Value parameter type.
 */
public interface Pair<K, V> {

    /**
     * Override the equals() method, simple check that
     * the object is of type pair, and its data members match.
     *
     * @param o Object that this Pair will be compared to.
     * @return True if and only if the above conditions are met.
     */
    default boolean equivalentTo(Object o) {

        // Is the object being compared to itself?
        if (o == this)
            return true;

        // Is o an instance of Pair?
        if (!(o instanceof Pair)) {
            return false;
        }

        // Cast to pair, so we can check data members
        @SuppressWarnings("unchecked")
        Pair<K, V> p = (Pair<K, V>) o;

        // Compare the inner members
        return p.key().equals(this.key()) && p.value().equals(this.value());
    }

    /**
     * Returns the key.
     *
     * @return The key.
     */
    @SuppressWarnings("unused")
    K key();

    /**
     * Sets the key.
     *
     * @param key The new key.
     */
    @SuppressWarnings("unused")
    void key(K key);

    /**
     * Returns a JSON String representation of the pair. Note,
     * this is a wrapper around the toString() method that
     * will be called in implementing classes. The method cannot
     * directly override it because it is a default method, and this
     * is an interface.
     *
     * @return String version of the pair.
     */
    @SuppressWarnings("unused")
    default String asString() {
        String k;
        String v;

        k = key() == null ? "null" : key().toString();
        v = value() == null ? "null" : value().toString();

        return "{key: " + k + ", value: " + v + "}";
    }

    /**
     * Returns the value.
     *
     * @return The value.
     */
    @SuppressWarnings("unused")
    V value();

    /**
     * Sets the value.
     *
     * @param value The new value.
     */
    @SuppressWarnings("unused")
    void value(V value);
}
