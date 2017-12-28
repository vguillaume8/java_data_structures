package structures.commons;

/**
 * Key-value pair object for use in Objects such as Maps and Trees.
 *
 * @author Jabari Dash
 * @param <K> Key parameter type.
 * @param <V> Value parameter type.
 */
public abstract class Pair<K, V> {


    /**
     * Override the equals() method, simple check that
     * the object is of type pair, and its data members match.
     *
     * @param o Object that this Pair will be compared to.
     * @return True if and only if the above conditions are met.
     */
    @Override
    public boolean equals(Object o) {

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
    public abstract K key();

    /**
     * Sets the key.
     *
     * @param key The new key.
     */
    @SuppressWarnings("unused")
    public abstract void key(K key);

    /**
     * Returns a JSON String representation of the pair.
     *
     * @return String version of the pair.
     */
    @SuppressWarnings("unused")
    public String toString() {
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
    public abstract V value();

    /**
     * Sets the value.
     *
     * @param value The new value.
     */
    @SuppressWarnings("unused")
    public abstract void value(V value);
}
