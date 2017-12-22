package structures.vectors;

import structures.vectors.auxiliary.ChainedDataStructure;
import java.util.EmptyStackException;

/**
 *  Basic implementation of a Stack class using a Linked List
 *
 *  All basic operations are O(n), except for
 *  contains() O(n), which we shouldn't be using with
 *  a Stack anyways.
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public class Stack<T>  extends ChainedDataStructure<T> {

    /**
     * Constructs an empty Stack
     */
    public Stack() {
        this.init();
    }

//------------------------------------------------------------------------------

    /**
     * Constructs a Stack from a specified array of values
     *
     * @param values Specified array of values to instantiate the Stack from
     */
    public Stack(T[] values) {
        this.init(values);
    }

//------------------------------------------------------------------------------

    public Stack(int length, T value) {
        this.init(length, value);
    }

//------------------------------------------------------------------------------

    @Override
    public void insert(T value) {
        if (this.empty()) {
            this.head(new Node(value, null, null));
        } else {
            Node node = new Node(value, null, this.head());
            this.head().prev(node);
            this.head(node);
        }

        this.incrementSize();
    }

//------------------------------------------------------------------------------

    @Override
    public T remove() {
        if (this.empty()) {
            throw new EmptyStackException();
        }

        T value = this.head().value();  // Get value from head
        this.head(this.head().next());  // Set head equal to head's next
        this.decrementSize();           // Decrement size of Queue

        return value;
    }

//------------------------------------------------------------------------------

    /**
     * Pushes a specified value onto the Stack
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
     * Retrieves and removes the topmost value from the Stack
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
     * Retrieves but does not remove the topmost value from the Stack
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
            throw new EmptyStackException();
        }

        return this.head().value();
    }
}