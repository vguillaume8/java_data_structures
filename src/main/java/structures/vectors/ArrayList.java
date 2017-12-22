package structures.vectors;

import structures.vectors.auxiliary.DynamicallySizedArray;

/**
 * Basic implementation of a generic ArrayList
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public class ArrayList<T> extends DynamicallySizedArray<T> {

    /**
     * Constructs an empty ArrayList
     */
    public ArrayList() {
        this.init();
    }

    /**
     * Constructs an ArrayList from a provided array of integer
     * @param values Specified array of integers to instantiate ArrayList with
     */
    public ArrayList(T[] values) {
        this.init(values);
    }

    /**
     * Constructs an ArrayList of a specified length with a specified
     * default value
     *
     * @param length Specified length
     * @param value Specified default value
     */
    public ArrayList(int length, T value) {
        this.init(length, value);
    }

    /**
     * Returns the value at a specified index
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
     * Retrieves and removes the value at a specified index
     *
     * @param index Index to remove value from
     * @return Value at specified index
     */
    @Override
    public T remove(int index) {
        T value;

        this.verifyIndex(index);        // Verify that the index is valid
        value = this.values[index];     // Store the value are the given index
        this.shiftLeft(index);          // Shift all values up from the right of index over one to the left
        this.decrementSize();           // Decrement size of array
        return value;                   // Return the stored value
    }

    /**
     * Inserts a value at the end of the list
     *
     * @param value The specified value to insert
     */
    @Override
    public void insert(T value) {
        this.insertLast(value);
    }

    /**
     * Inserts a value at a specified index
     *
     * @param value Value to be inserted
     * @param index Specified index to insert value at
     */
    @Override
    public void insert(T value, int index) {
        this.verifyIndex(index);        // Verify that the index is a valid index
        this.alloc();                    // Potentially alloc the internal array before insertion
        this.shiftRight(index);         // Shift all values up one index, starting at designated index
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
     * @param value Specified value to insert
     */
    @Override
    public void insertFirst(T value) {
        if (this.empty()) {
            this.insertLast(value);
        } else {
            this.insert(value, 0);
        }
    }

    /**
     * Inserts an element to the back of the list
     *
     * @param value Specified value to insert
     */
    @Override
    public void insertLast(T value) {
        this.alloc();                        // Potentially alloc the internal array if we need space
        this.values[this.size()] = value;   // Append the value to the back of the array
        this.incrementSize();               // Increment the size of the list

    }

    /**
     * Retrieves and removes the first value in the list
     *
     * @return The first value in the list
     */
    @Override
    public T removeFirst() {
        return this.remove(0);
    }

    /**
     * Retrieves and removes the last value in the list
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
     * @return The last value in the list
     */
    @Override
    public T remove() {
        return this.removeLast();
    }
}