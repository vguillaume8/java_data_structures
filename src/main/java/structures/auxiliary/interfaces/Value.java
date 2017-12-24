package structures.auxiliary.interfaces;

/**
 * Generic Value class to be extended from by classes
 * such as Nodes in LinkedLists or Edges and vertexes in
 * Graphs. This is a wrapper object around values
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public interface Value<T> {

    /**
     * Determines whether or not the Value object
     * is equal to another value object
     *
     * @param value Specified value to be compared to
     * @return True if and only if they gave the same internal value
     */
    public abstract boolean equals(Value<T> value);

    /**
     * Returns the value of the node
     *
     * @return The value of the node
     */
    public abstract T value();

    /**
     * Updates the value of the node with a specified value
     *
     * @param value New value of the node
     */
    public abstract void value(T value);
}


