package util.iterators;

import structures.vectors.Vector;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Static Iterator class so that the Vector can be iterated on via
 * the enhance for loop
 *
 * @param <T> Generic type
 */
public class VectorIterator<T> implements Iterator<T> {
    private Vector<T> list;   // List to iterate over
    private int cursor;           // Cursor to keep track of position in iteration

    /**
     * Constructor, initialize cursor to index 0, and the list
     * variable equal to the passed in list
     *
     * @param list The list to be iterated on
     */
    public VectorIterator(Vector<T> list) {
        this.list = list;
        this.cursor = 0;
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the iterator still has values left
     *
     * @return True if and only if the cursor has not reached the end of the list
     */
    public boolean hasNext() {
        return this.cursor < this.list.length();
    }

//------------------------------------------------------------------------------

    /**
     * Returns the next value in iteration
     *
     * @return The next value in the iteration
     */
    public T next() {

        // If there are no more values left, throw an Exception
        if (!hasNext()) {
            throw new NoSuchElementException("No element");
        }

        int current = this.cursor;    // Store the current cursor value
        this.cursor++;                // Increment the cursor
        return list.get(current);     // Return the value at index cursor (before it was incremented)
    }

}