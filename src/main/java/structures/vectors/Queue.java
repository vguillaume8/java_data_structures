package structures.vectors;

import structures.commons.ChainedDataStructure;

/**
 * Basic FIFO Queue implementation
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class Queue<T> extends ChainedDataStructure<T> {

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
        super(values);
    }

    /**
     * Constructs Queue of specified length where
     * all values have a specified default value.
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
     * @param length Specified length of list
     * @param value Specified default value
     */
    public Queue(int length, T value) {
        super(length, value);
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

        return this.head().value();
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
            this.head(new Node<T>(value, null, null));
        } else {
            this.head().insert(value);
        }

        this.incrementSize();

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

        T value = this.head().value();  // Get value from head
        this.head(this.head().next());  // Set head equal to head's next
        this.decrementSize();           // Decrement size of Queue

        return value;
    }
}