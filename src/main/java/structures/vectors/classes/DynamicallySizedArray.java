package structures.vectors.classes;

import structures.vectors.interfaces.IndexedDataStructure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.StringJoiner;

/**
 * Incomplete class that should be implemented
 * by classes that have growing arrays in the
 * back. For example, Heaps or ArrayLists
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public abstract class DynamicallySizedArray<T> extends DynamicallySizedDataStructure<T> implements IndexedDataStructure<T> {

    private final int DEFAULT_SIZE = 4;
    protected T[] values;

    public DynamicallySizedArray() {
        super();
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    public DynamicallySizedArray(T[] values) {
        super();

        this.values = (T[]) new Object[DEFAULT_SIZE];

        for (T value : values) {
            this.insert(value);
        }
    }

    public DynamicallySizedArray(int length, T value) {
        super();

        this.values = (T[]) new Object[DEFAULT_SIZE];

        if (length < 0) {
            throw new IllegalArgumentException("Length must be at least 0");
        }

        for (int i = 0; i < length; i++) {
            this.insert(value);
        }
    }

    /**
     * Determines whether or not the internal array is full
     *
     * @return True if the internal array has run out of space for new elements
     */
    protected boolean full() {
        if (this.values == null) {
            return true;
        }

        return this.size() < this.values.length;
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not a specified index is within the bounds
     * of the DataStructure
     *
     * @param index Specified index to check
     * @return True if and only if the index is non-negative and less than the size of the structure
     */
    public boolean indexOutOfBounds(int index) {
        return index < 0 || index >= this.size();
    }


//------------------------------------------------------------------------------

    /**
     * Determines whether or a specified value is within
     * the Structure.
     *
     * @param value Specified value
     * @return  True if and only if the DataStrcture contains the value
     */
    @Override
    public boolean contains(T value) {
        boolean contains = false;
        int size = this.size();

        for (int i = 0; i < size; i++) {
            if (this.values[i].equals(value)) {
                contains = true;
                break;
            }
        }

        return contains;
    }

//------------------------------------------------------------------------------

    /**
     * If the internal array is full, it's size will be doubled plus 1
     */
    protected void alloc() {
        if (this.full()) {
            this.alloc(this.size() * 2 + 1);
        }
    }

//------------------------------------------------------------------------------

    /**
     * Allocates (or de-allocates) space in the interal array
     *
     * @param slots How many new slots to make
     */
    @SuppressWarnings("unchecked")
    protected void alloc(int slots) {
        int length;

        length = this.values.length + slots;

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

        if (length < this.values.length) {

            for (int i = 0; i < temp.length; i++) {
                temp[i] = this.values[i];
            }

        } else {

            for (int i = 0; i < values.length; i++) {
                temp[i] = values[i];
            }
        }

        this.values = temp;
    }

//------------------------------------------------------------------------------

    /**
     * Returns iterator (allows use with enhanced forloop)
     *
     * @return Iterator for iterating over Structure by index
     */
    @Override
    public Iterator<T> iterator() {
        return new IndexedDataStructureIterator<>(this);
    }

//------------------------------------------------------------------------------

    /**
     * Returns a String representation of the list
     *
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

//------------------------------------------------------------------------------

    /**
     * Shrinks the internal array by a given number of elements
     *
     * @param shrinkBy Number of elements to shrink the list by
     */
    protected void deloc(int shrinkBy) {
        if (shrinkBy < 0) {
            throw new RuntimeException("");
        }

        this.alloc(-1 * shrinkBy);
    }

    /**
     *
     * @return
     */
    public T[] toArray() {

        return (T[]) this.toArray(new Object[0]);
    }

    public <T> T[] toArray(T[] array) {

        return (T[]) Arrays.copyOf(this.values, this.size(), array.getClass());
    }

//------------------------------------------------------------------------------

    /**
     * Verifies if a provided index is within the DataStructure or not
     *
     * @param index Specified index to verify
     * @throws IndexOutOfBoundsException Exception thrown if the index is invalid
     */
    public void verifyIndex(int index) {
        if (this.indexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("size: " + this.size() + " index: " + index);
        }
    }
}
