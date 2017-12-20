package vectors;

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
public class Stack<T> {
    DoublyLinkedList<T> stack;

    /**
     * Constructs an empty Stack
     */
    public Stack() {
        this.stack = new DoublyLinkedList<T>();
    }

//------------------------------------------------------------------------------

    /**
     * Constructs a Stack from a specified array of values
     *
     * @param values Specified array of values to instantiate the Stack from
     */
    public Stack(T[] values) {
        this.stack = new DoublyLinkedList<>();

        for (int i = 0; i < values.length; i++) {
            this.push(values[i]);
        }
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the Stack is empty
     *
     * @return True if an only if there are 0 values on the Stack
     */
    public boolean empty() {
        return this.stack.empty();
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the Stack contains a designated value
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
     * @param value Designated value to look for
     * @return True if and only if the Stack contains the specified value
     */
    public boolean contains(T value) {
        return this.stack.contains(value);
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
        this.stack.insertFirst(value);
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
        T value = null;

        try {
            value = this.stack.removeFirst();

        } catch (IndexOutOfBoundsException exception) {
            throw new EmptyStackException();
        }

        return value;
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
        T value = null;

        try {
            value = this.stack.get(0);

        } catch (IndexOutOfBoundsException exception) {
            throw new EmptyStackException();
        }

        return value;
    }

//------------------------------------------------------------------------------

    /**
     * Returns the number of elements on the Stack
     *
     * @return The number of elements on the Stack
     */
    public int size() {
        return this.stack.length();
    }

//------------------------------------------------------------------------------

    /**
     * Returns String representation of the Stack.
     * Note, the top is at the left, and the String
     * is meant to be read from top down (left to right)
     *
     * @return String version of the Stack
     */
    @Override
    public String toString() {
        return this.stack.toString();
    }
}