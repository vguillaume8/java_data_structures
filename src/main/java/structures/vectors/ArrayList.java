package structures.vectors;

import structures.vectors.classes.DynamicallySizedArray;

/**
 * Basic implementation of a generic ArrayList
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class ArrayList<T> extends DynamicallySizedArray<T> {

    /**
     * Constructs empty list
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
    public ArrayList() {
        super();
    }

    /**
     * Constructs ArrayList from array of values
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
     * @param values Array of values to construct the list from
     */
    public ArrayList(T[] values) {
        super(values);
    }

    /**
     * Constructs LinkedList of specified length where
     * all values have a specified default value
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
    public ArrayList(int length, T value) {
        super(length, value);
    }

    /**
     * Returns the value at a specified index
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
     * @param index Specified index
     * @return Value at specified index
     */
    @Override
    public T get(int index) {
       this.verifyIndex(index);
       return this.values[index];
    }

    /**
     * Inserts a value at the end of the list
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
     * @param value The specified value to insert
     */
    @Override
    public boolean insert(T value) {
        this.insertLast(value);

        return true;
    }

    /**
     * Inserts a value at a specified index
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
     * @param value Value to be inserted
     * @param index Specified index to insert value at
     */
    @Override
    public void insert(T value, int index) {
        if (!this.empty() && index < this.size()) {
            this.verifyIndex(index);    // Verify that the index is a valid index
            this.shiftRight(index);     // Shift all values up one index, starting at designated index
        }

        this.alloc();                   // Potentially alloc the internal array before insertion
        this.values[index] = value;     // Insert the new value into the designated index
        this.incrementSize();           // Increment size of list
    }

    /**
     * Performs a partial shift right on the array.
     * All elements to right of the index, and the
     * index itself will be shifted to the right one
     * spot for the purpose of making space for a new element
     * to be inserted. This is an auxiliary function
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
     * @param index Index to start shifting from
     */
    private void shiftRight(int index) {
        this.verifyIndex(index);

        for (int i = values.length-2; i >= index; i--) {
            values[i+1] = values[i];
        }
    }

    /**
     * Performs a partial shift left on the array.
     * Values starting from to the end of the list,
     * and not including the index will be shifted
     * over to the left. The value at the specified
     * index will be overriden by the value to its left.
     * This essentially removes the value from that spot
     * from the array. This is an auxiliary function for
     * use with the remove functionality
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
     * @param index Specified index to shift left into
     */
    private void shiftLeft(int index) {
        this.verifyIndex(index);

        for (int i = index; i < this.size(); i++) {
            values[i] = values[i+1];
        }
    }

    /**
     * Inserts a value into the front of the list
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
     * @param value Specified value to insert
     */
    @Override
    public void insertFirst(T value) {
        this.insert(value, 0);
    }

    /**
     * Inserts an element to the back of the list
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
     * @param value Specified value to insert
     */
    @Override
    public void insertLast(T value) {
        this.insert(value, this.size());
    }

    /**
     * Retrieves and removes the value at a specified index
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
     * @param index Index to remove value from
     * @return Value at specified index
     */
    @Override
    public T remove(int index) {
        T value;

        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot remove from an empty ArrayList");
        }

        this.verifyIndex(index);        // Verify that the index is valid
        value = this.values[index];     // Store the value are the given index
        this.shiftLeft(index);          // Shift all values up from the right of index over one to the left
        this.decrementSize();           // Decrement size of array
        return value;                   // Return the stored value
    }

    /**
     * Retrieves and removes the first value in the list
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
     * @return The first value in the list
     */
    @Override
    public T removeFirst() {
        return this.remove(0);
    }

    /**
     * Retrieves and removes the last value in the list
     * <br>
     *
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Best: </strong>&Omega;(1)<br>
     * <strong>Worst: </strong>O(n)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * @return Last value in the list
     */
    @Override
    public T removeLast() {
        return this.remove(this.size() - 1);
    }

    /**
     * Retrieves and removes the last value in the list
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
     * @return The last value in the list
     */
    @Override
    public T remove() {
        return this.removeLast();
    }
}