package structures.auxiliary;

import java.util.Iterator;
import java.util.StringJoiner;

public abstract class ChainedIndexedDataStructure<T> extends ChainedDataStructure<T> implements IndexedDataStructure<T> {

    /**
     * Get Node at a specified index
     *
     * @param index Specified index
     * @return Node pointer at specified index
     */
    protected abstract Node<T> getNode(int index);

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
        return index < 0 || index >= this.size() ? false : true;
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
            throw new IndexOutOfBoundsException("size: " + this.size() + " index: " + index);
        }
    }

//------------------------------------------------------------------------------

    /**
     * Returns a String representation of the list
     * @return String version of the list
     */
    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ");

        // Iterate over list, appending
        // values with StringJoiner
        for (T value : this) {

            // Check for null values
            if (value == null) {
                stringJoiner.add("null");

            } else {
                stringJoiner.add(value.toString());
            }
        }

        return stringJoiner.toString();
    }

    /**
     * Returns the value at a specified index in the list
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
     * @param index The specified to retrieve the value from
     * @return The value of the node at the specified index
     */
    public T get(int index) {
        return this.getNode(index).value();
    }

//------------------------------------------------------------------------------

    /**
     * Append a specified value  to the back of the list
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
     * @param value Specified value to be inserted into the list
     */
    public void insert(T value) {

        // If the list is empty, insert in the front
        if (this.empty()) {
            this.insertFirst(value);

            // Otherwise insert in the back
        } else {
            this.insertLast(value);
        }
    }

}


