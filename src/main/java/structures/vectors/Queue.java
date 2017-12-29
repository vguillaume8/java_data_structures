package structures.vectors;

import structures.vectors.LinkedList.Node;
import structures.commons.LinearDataStructure;

import java.util.Iterator;

/**
 * Basic FIFO Queue implementation
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class Queue<T> implements LinearDataStructure<T> {

    /**
     *
     */
    protected int size;

    /**
     * Pointer to the first node in the chain
     */
    protected Node<T> head;

    /**
     * Pointer to the last node in the chain
     *
     * TODO - Use the tail in implementing classes such as Linkedlist so insert last is O(1)
     */
    protected Node<T> tail;

    /**
     * Constructs empty Queue.
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
    public Queue() {
        super();
    }

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
    public Queue(T[] values) {
        insert(values);
    }

    /**
     * Retrieves, but does not remove the front-most
     * element in the Queue.
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
     * @return Front-most value from Queue
     */
    public T peek() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot peek() empty Queue");
        }

        return this.head.value;
    }

    /**
     * Inserts value at back of Queue.
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
            this.head = new Node<>(value);
        } else {
            this.head.insert(value);
        }

        this.size++;

        return true;
    }

    /**
     * Retrieve and remove front-most value from Queue.
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
     * @return Front most value from Queue
     */
    @Override
    public T remove() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot remove() from empty Queue");
        }

        T value = this.head.value;  // Get value from head
        this.head = this.head.next;  // Set head equal to head's next
        this.size--;           // Decrement size of Queue

        return value;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.size;
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

    @Override
    public Iterator<T> iterator() {

        // Need to get the head node

        return LinkedList.iterator(head);
    }
}