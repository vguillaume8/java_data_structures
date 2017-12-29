package structures.vectors;

import java.util.Arrays;

/**
 * Basic implementation of a generic ArrayList
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class ArrayList<T> implements structures.commons.IndexedDataStructure<T> {

    private final int DEFAULT_SIZE = 4;

    private int size;
    protected T[] values;

    /**
     * Constructs empty list.
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
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    /**
     * Constructs ArrayList from array of values.
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
        this();
        insert(values);
    }

    /**
     * If the internal array is full, it's size will be doubled plus 1
     */
    public  void alloc() {
        if (this.full()) {
            this.alloc(this.size() * 2 + 1);
        }
    }

    /**
     * Allocates (or de-allocates) space in the internal array
     *
     * @param slots How many new slots to make
     */
    public void alloc(int slots) {
        int length;

        length = values.length + slots;

        // If the length of the internal
        // array would become less than 0
        if (length < 0) {
            throw new RuntimeException("");
        }

        // If the new array is shorter, only copy
        // up until the new array is full
        // Otherwise, the new array is either the same
        // length, or longer, so copy everything from the
        // old array, but do not loop off the end

        T[] temp = (T[]) new Object[length];


        // TODO - Should not shrink smaller than the # of elements
        int l = length < values.length ? temp.length : values.length;

        System.arraycopy(values, 0, temp, 0, l);

        this.values = temp;

//        System.arraycopy(values, 0, temp,0, temp.length);
    }


    /**
     * Determines whether or not this ArrayList is equal to
     * a provided object.
     *
     * @param object Object to compare this ArrayList with.
     * @return True if and only if their types are the same,
     * lengths are the same, and the contain all the same values.
     */
    @Override
    public boolean equals(Object object) {

        // Object must be an ArrayList, and all values must be equal, or object
        // must be this ArrayList itself
        return this == object || (object instanceof ArrayList && equivalentTo(object));
    }

    /**
     * Determines whether or not the internal array is full
     *
     * @return True if the internal array has run out of space for new elements
     */
    public boolean full() {
        T[] vals = values;

        return vals == null || this.size() < vals.length;
    }

    /**
     * Returns the value at a specified index.
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
     * Inserts a value at the end of the list.
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
     * Inserts a value at a specified index.
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
        this.size++;                    // Increment size of list
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

        System.arraycopy(values, 0, values, 1, index);
    }

    /**
     * Performs a partial shift left on the array.
     * Values starting from to the end of the list,
     * and not including the index will be shifted
     * over to the left. The value at the specified
     * index will be overridden by the value to its left.
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

        // TODO - Use Java API to complete this shift left
//        System.arraycopy(values, 1, values, 0, index);
    }

    /**
     * Inserts a value into the front of the list.
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
     * Inserts an element to the back of the list.
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
     * Retrieves and removes the value at a specified index.
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
        this.size--;           // Decrement size of array
        return value;                   // Return the stored value
    }

    /**
     * Retrieves and removes the first value in the list.
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
     * Retrieves and removes the last value in the list.
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
     * Retrieves and removes the last value in the list.
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

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns data structure as an array of specified type.
     *
     * @param array Array that specifies the type of the array to return.
     * @return Array representation of the data structure.
     */
    public T[] toArray(T[] array) {

        try {
            array = (T[]) Arrays.copyOf(values, this.size(), array.getClass());

        } catch (ArrayStoreException exception) {

            throw new IllegalArgumentException("Array type " + array.getClass().getSimpleName() + " is invalid");
        }

        return array;
    }

    /**
     * Returns a String representation of the list
     *
     * @return String version of the list
     */
    @Override
    public String toString() {

        return Arrays.toString(this.toArray());
    }

}