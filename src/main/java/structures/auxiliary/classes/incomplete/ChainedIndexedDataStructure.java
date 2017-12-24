package structures.auxiliary.classes.incomplete;

import structures.auxiliary.classes.concrete.Node;
import structures.auxiliary.interfaces.IndexedDataStructure;

import java.util.Iterator;

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
    @Override
    public Iterator<T> iterator() {
        return new ChainedDataStructureIterator<T>(this.head());
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not a specified index is within the bounds of the list
     *
     * @param index Specified index
     * @return True if and only if the index is less then the length of the list, and positive
     */
    public boolean indexOutOfBounds(int index) {
        return index < 0 || index >= this.size();
    }

//------------------------------------------------------------------------------

    /**
     * Throws an Exception if a specified index is out of bounds
     *
     * @param index Specified index
     */
    public void verifyIndex(int index) {

        // Throw an Exception if the index is out of bounds
        if (this.indexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("size: " + this.size() + " index: " + index);
        }
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
    @Override
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
    @Override
    public boolean insert(T value) {

        // If the list is empty, insert in the front
        if (this.empty()) {
            this.insertFirst(value);

            // Otherwise insert in the back
        } else {
            this.insertLast(value);
        }

        return true;
    }

    public T remove(int index) {
        return null;
    }

}


