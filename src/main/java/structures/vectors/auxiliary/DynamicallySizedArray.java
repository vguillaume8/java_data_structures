package structures.vectors.auxiliary;

import structures.auxiliary.DynamicallySizedDataStructure;
import java.util.Iterator;
import java.util.StringJoiner;

/**
 *
 * @param <T> Generic type
 */
public abstract class DynamicallySizedArray<T> extends DynamicallySizedDataStructure<T> implements IndexedDataStructure<T> {

    protected T[] values;

    /**
     * Determines whether or not the internal array is full
     *
     * @return
     */
    protected boolean full() {
        return this.size() < this.values.length;
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param index
     * @return
     */
    public boolean indexOutOfBounds(int index) {
        return index < 0 || index >= this.size();
    }

//------------------------------------------------------------------------------

    /**
     * Initialize the structure as empty
     */
    @SuppressWarnings("unchecked")
    public void init() {
        super.init();
        this.values = (T[]) new Object[4];
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param values Values to be inserted into the IndexedDataStructure
     */
    public void init(T[] values) {
        this.init();

        for (T value : values) {
            this.insert(value);
        }
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param length Specified length of IndexedDataStructure
     * @param value Specified default value
     */
    public void init(int length, T value) {
        this.init();

        for (int i = 0; i < length; i++) {
            this.insert(value);
        }
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param value
     * @return
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
     *
     */
    protected void grow() {
        if (this.empty() || this.full()) {
            this.grow(this.size() * 2 + 1);
        }
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param growBy
     */
    @SuppressWarnings("unchecked")
    protected void grow(int growBy) {
        int length;

        length = this.values.length + growBy;

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

    @Override
    public Iterator<T> iterator() {
        return new IndexedDataStructureIterator<>(this);
    }

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

//------------------------------------------------------------------------------

    /**
     * Shrinks the internal array by a given number of elements
     *
     * @param shrinkBy Number of elements to shrink the list by
     */
    protected void shrink(int shrinkBy) {
        if (shrinkBy < 0) {
            throw new RuntimeException("");
        }

        this.grow(-1 * shrinkBy);
    }

    public void verifyIndex(int index) {
        if (this.indexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("size: " + this.size() + " index: " + index);
        }
    }
}
