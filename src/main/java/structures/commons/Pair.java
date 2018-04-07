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
     * Returns a JSON-like String representation of the pair. Note,
     * this is a wrapper around the toString() method that
     * will be called in implementing classes. The method cannot
     * directly override it because it is a default method, and this
     * is an interface. But, to avoid implementing {@code toString}
     * in every concrete class that implements this interface, we
     * have this auxiliary function.
     *
     * @return String version of the pair.
     */
    @SuppressWarnings("unused")
    default String asString() {
        String k;
        String v;

        // TODO - Potential change?
        // Convert null to string null. However, what
        // if the string contains the value "null?" There
        // will be no way to distinguish. Perhaps put the
        // strings in quotes, but don't put null in quotes
        // if it came from a null key or value
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
