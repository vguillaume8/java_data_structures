package structures.vectors.classes;

import structures.util.interfaces.DataStructure;

/**
 * All dynamically sized data types should extend this class
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public abstract class DynamicallySizedDataStructure<T> implements DataStructure<T> {

    private int size;   // Tracks the number of values in the structure

    public DynamicallySizedDataStructure() {
        this.size = 0;
    }

    public DynamicallySizedDataStructure(T[] values) {
        this.size = 0;

        for (T value : values) {
            this.insert(value);
        }
    }

    public DynamicallySizedDataStructure(int length, T value) {
        this.size = 0;

        if (length < 0) {
            throw new IllegalArgumentException("Length must be at least 0");
        }

        for (int i = 0; i < length; i++) {
            this.insert(value);
        }
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the data structure is empty
     *
     * @return True if and only if the size of the structure is 0
     */
    public boolean empty() {
        return this.size() == 0 ? true : false;
    }

//------------------------------------------------------------------------------

    /**
     * Decrements the size of the structure by 1
     */
    protected void decrementSize() {
        this.decrementSize(1);
    }

//------------------------------------------------------------------------------

    /**
     * Decrements the size of the structure by a specified amount
     *
     * @param decrements Number of times to decrement
     */
    protected void decrementSize(int decrements) {
        while (decrements > 0) {
            this.size--;
            decrements--;
        }
    }

//------------------------------------------------------------------------------

    /**
     * Increments the size of the structure by 1
     */
    protected void incrementSize() {
        this.incrementSize(1);
    }

//------------------------------------------------------------------------------

    /**
     * Increments the size of the structure by a specified amount
     *
     * @param increments Number of times to increment
     */
    protected void incrementSize(int increments) {
        while (increments > 0) {
            this.size++;
            increments--;
        }
    }

//------------------------------------------------------------------------------

    /**
     * Returns the size of the structure
     *
     * @return The number of elements in the structure
     */
    public int size() {
        return this.size;
    }
}
