package structures.auxiliary;

/**
 * Abstract class for use with DoublyLinkedListNodes and
 * SinglyLinkedListNodes
 *
 * @param <T> Generic type
 */
public abstract class LinkedListNode<T> {
    private T value;

    /**
     * Inserts a specified value into the chain of nodes
     *
     * @param value Specified value to be inserted
     */
    public abstract void insert(T value);

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

        if (this.value == null) {
            return "null";

        } else {
            return this.value.toString();
        }
    }
}
