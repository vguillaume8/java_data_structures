package structures.vectors;

import structures.DataStructure;
import util.iterators.VectorIterator;

import java.util.Iterator;

/**
 * Abstract Vector structure. All vector-like structures (linear structures)
 * will extend this abstract class
 *
 * @param <T> Generic type
 */
public abstract class Vector<T> extends DataStructure implements Iterable<T> {

    /**
     * Returns the value located at a specified index
     *
     * @param index Specified index
     * @return Item at the specified index
     */
    public abstract T get(int index);

    /**
     * Inserts a specified value into the Vector
     *
     * @param value The specified value to insert
     */
    public abstract void insert(T value);

    /**
     * Retrieves and removes a value from the Vector
     *
     * @return The value that was removed from the Vector
     */
    public abstract T remove();

//------------------------------------------------------------------------------

    protected void init(T[] values) {
        super.init();

        for (int i = 0; i < values.length; i++) {
            this.insert(values[i]);
        }
    }

    protected void init(int length, T value) {
        super.init();

        for (int i = 0; i < length; i++) {
            this.insert(value);
        }
    }

    /**
     * Determines whether or not a specified value is in the vector
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
     * @param value Specified value to search for
     * @return True if and only if the specified value if in the vector
     */
    public boolean contains(T value) {

        // Iterate over the list
        for (T v : this) {

            // Return true if we find a match
            if (v == value) {
                return true;
            }
        }

        // Otherwise, by default return false
        return false;
    }

    //------------------------------------------------------------------------------

    /**
     * Returns an iterator so the list can be iterated on with the enhanced for loop
     *
     * @return Iterator for iterating over the list
     */
    public Iterator<T> iterator() {
        return new VectorIterator<>(this);
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not a specified index is within the bounds of the list
     *
     * @param index Specified index
     * @return True if and only if the index is less then the length of the list, and positive
     */
    protected boolean indexInBounds(int index) {
        return index < 0 || index >= this.length() ? false : true;
    }

//------------------------------------------------------------------------------

    /**
     * Throws an Exception if a specified index is out of bounds
     *
     * @param index Specified index
     */
    protected void checkIndex(int index) {

        // Throw an Exception if the index is out of bounds
        if (!this.indexInBounds(index)) {
            throw new IndexOutOfBoundsException("size: " + this.length() + " index: " + index);
        }
    }

//------------------------------------------------------------------------------

    /**
     * Decrements the length of the Vector
     */
    protected void decrementLength() {
        this.decrementSize();
    }

//------------------------------------------------------------------------------

    /**
     * Incrementes the length of the Vector
     */
    protected void incrementLength() {
        this.incrementSize();
    }

//------------------------------------------------------------------------------

    /**
     * Returns the length of the vector
     *
     * @return Number of values in the vector
     */
    public int length() {
        return this.size();
    }
}
