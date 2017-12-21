package structures.auxiliary;

/**
 * Generic Value class to be extended from by classes
 * such as Nodes in LinkedLists or Edges and vertexes in
 * Graphs. This is a wrapper object around values
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public abstract class Value<T> {
    private T value;

    /**
     * Initializes the value of this object to null
     */
    public void init() {
        this.value = null;
    }

    /**
     * Initializes the value of this object with
     * a specified value
     *
     * @param value Specified value
     */
    public void init(T value) {
        this.value = value;
    }

    /**
     * Returns the value of the node
     *
     * @return The value of the node
     */
    public T value() {
        return this.value;
    }

    /**
     * Updates the value of the node with a specified value
     *
     * @param value New value of the node
     */
    public void value(T value) {
        this.value = value;
    }

    /**
     * Returns a String representation of the node
     *
     * @return String version of the node's value
     */
    @Override
    public String toString() {

        if (this.value() == null) {
            return "null";

        } else {
            return this.value().toString();
        }
    }
}


