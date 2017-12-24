package structures.auxiliary.classes.concrete;

import structures.auxiliary.interfaces.Value;

/**
 * Node class for chaining together values in a LinkedList
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public class Node<T> implements Value<T> {
    private T value;        // Value of the node
    private Node<T> prev;   // Pointer to previous node in chain
    private Node<T> next;   // Pointer to next node in chain

//------------------------------------------------------------------------------

    /**
     * Constructs a new node with a specified value, and pointers to its
     * previous and its next nodes
     *
     * @param value The specified value of teh node
     * @param prev Pointer to the node that comes before this node in the chain
     * @param next Pointer to the node that comes after this node in the chain
     */
    public Node(T value, Node<T> prev, Node<T> next) {
        this.value(value);
        this.prev(prev);
        this.next(next);
    }

//------------------------------------------------------------------------------

    /**
     *
     *
     * @param value
     * @return
     */
    public boolean contains(T value) {
        return this.contains(this, value);
    }

    /**
     * Perform linear search for value
     *
     * @param node
     * @param value
     * @return
     */
    protected boolean contains(Node<T> node, T value) {

        // If the node passed in is null
        // then we have reached a dead-end
        if (node == null) {
            return false;

        // Check if this Node is the value
        } else if (this.equals(node)) {
            return true;

        } else {
            return this.contains(this.next(), value);
        }
    }

//------------------------------------------------------------------------------

    /**
     * Inserts a data value into the chained nodes
     *
     * @param value Specified value to be inserted into the chain
     */
    public boolean insert(T value) {

        // If the following node is empty
        // we have reached the end of the list
        if (this.next() == null) {
            this.next(new Node<T>(value, this, null));

            // Otherwise, we must recurse to the end
        } else {
            this.next().insert(value);
        }

        return true;
    }

//------------------------------------------------------------------------------

    /**
     * Returns the pointer to the next node that follows this node
     *
     * @return Pointer to the following node
     */
    public Node<T> next() {
        return this.next;
    }

//------------------------------------------------------------------------------

    /**
     * Sets the pointer to the next node
     *
     * @param next Designated node to be the next of this node
     */
    public void next(Node<T> next) {
        this.next = next;
    }

//------------------------------------------------------------------------------

    /**
     * Returns the pointer to the previous node that precedes this node
     *
     * @return Pointer to the previous node
     */
    public Node<T> prev() {
        return this.prev;
    }

//------------------------------------------------------------------------------

    /**
     * Sets the pointer to the prev node
     *
     * @param prev Designated node to be the prev of this node
     */
    public void prev(Node<T> prev) {
        this.prev = prev;
    }

    /**
     *
     * @param value Specified value to be compared to
     * @return
     */
    @Override
    public boolean equals(Value<T> value) {
        return this.equals(value.value());
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

    /**
     *
     * @return
     */
    public T value() {
        return this.value;
    }

    /**
     *
     * @param value New value of the node
     */
    public void value(T value) {
        this.value = value;
    }
}
