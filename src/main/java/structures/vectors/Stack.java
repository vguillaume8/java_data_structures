package structures.vectors;

import structures.commons.LinearDataStructure;
import structures.commons.Node;

import java.util.Iterator;

/**
 *  Basic implementation of a Stack class using a Linked List.
 *
 *  All basic operations are O(n), except for
 *  contains() O(n), which we shouldn't be using with
 *  a Stack anyways.
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class Stack<T>  implements LinearDataStructure<T> {

    /**
     *
     */
    protected int size;

    /**
     * Pointer to the first node in the chain
     */
    protected Node<T> head;

    /**
     * Constructs empty Stack
     *
     * <br>
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     */
    public Stack() {
        super();
    }

//------------------------------------------------------------------------------

    /**
     * Constructs Queue from array of values.
     *
     * <br>
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(n)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * @param values Array of values to instatiate Queue from
     */
    public Stack(T[] values) {
        insert(values);
    }


    @Override
    public Iterator<T> iterator() {

        // Need to get the head node

        return new Node.NodeIterator<T>(head);
    }
//------------------------------------------------------------------------------

    /**
     * Inserts value at top of Stack.
     *
     * <br>
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Best: </strong>&Omega;(1)<br>
     * <strong>Worst: </strong>O(n)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * @param value The specified value to insert
     */
    @Override
    public boolean insert(T value) {
        if (this.empty()) {
            this.head = new Node<T>(value);
        } else {
            Node<T> node = new Node<T>(value, null, this.head);
            this.head.prev = node;
            this.head = node;
        }

        this.size++;

        return true;
    }

//------------------------------------------------------------------------------

    /**
     * Retrieves and removes top of Stack.
     *
     * <br>
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * @return The topmost value on the Stack
     *
     * @return Value form top of Stack
     */
    @Override
    public T remove() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot remove from an empty Stack");
        }

        T value = this.head.value;  // Get value from head
        this.head = this.head.next;  // Set head equal to head's next
        this.size--;           // Decrement size of Queue

        return value;
    }

//------------------------------------------------------------------------------

    /**
     * Pushes a specified value onto the Stack.
     *
     * <br>
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * @param value Specified value to be pushed onto the Stack
     */
    public void push(T value) {
        this.insert(value);
    }

//------------------------------------------------------------------------------

    /**
     * Retrieves and removes the topmost value from the Stack.
     *
     * <br>
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * @return The topmost value on the Stack
     */
    public T pop() {
        return this.remove();
    }

//------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Retrieves but does not remove the topmost value from the Stack.
     *
     * <br>
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * @return The topmost value on the Stack
     */
    public T top() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot top() and empty Stack");
        }

        return this.head.value;
    }

    /**
     * Returns a String representation of the list
     *
     * @return String version of the list
     */
    @Override
    public String toString() {
        return asString();
    }
}