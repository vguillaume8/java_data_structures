package structures.vectors;

import structures.commons.DynamicArray;

import java.util.Arrays;
import java.util.Collection;

/**
 * Basic implementation of a generic ArrayList
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public final class ArrayList<E> extends DynamicArray<E> implements List<E> {


    /**
     * Instantiates an ArrayList with an internal array of
     * a specified length. This constructor should be used
     * when the number of elements that the ArrayList will
     * contain is known beforehand. This avoids the dynamic memory
     * re-allocations required to continuously make space for
     * new elements. If a length smaller than the default
     * capacity (10) is passed, the ArrayList's capacity will
     * default to 10.
     *
     * @param length Specific initial capacity of ArrayList.
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int length) {
        super(length);
    }

    /**
     * Constructs empty list. The default capacity
     * of the ArrayList is 10.
     */
    public ArrayList() {
        super();
    }

    /**
     * Constructs ArrayList from array of elements.
     *
     * @param values Array of elements to construct the list from
     */
    public ArrayList(E[] values) {
        this(values.length);
        insert(values);
    }

    /**
     * Construct array list from Java Collection of values.
     *
     * @param values Collection of values to construct list from.
     * @see java.util.Collection
     */
    public ArrayList(Collection<E> values) {
        this(values.size());
        insert(values);
    }

    /**
     * Determines whether or not this ArrayList is equal to
     * a provided object.
     *
     * @param object Object to compare this ArrayList with.
     * @return True if and only if their types are the same,
     * lengths are the same, and the contain all the same elements.
     */
    @Override
    public boolean equals(Object object) {

        // Object must be an ArrayList, and all elements must be equal, or object
        // must be this ArrayList itself
        return this == object || (object instanceof ArrayList && equivalentTo(object));
    }


    /**
     * Returns the value at a specified index.
     *
     * @param index Specified index.
     * @return Value at specified index.
     */
    @Override
    public E get(int index) {
       verifyIndex(index);
       return elements[index];
    }

    /**
     * Inserts a key at the end of the list.
     *
     * @param key The specified key to insert
     * @return True to indicate the insertion was successful.
     */
    @Override
    public boolean insert(E key) {
        return append(key);
    }

    /**
     * Inserts a value at a specified index.
     *
     * @param value Value to be inserted
     * @param index Specified index to insert value at
     * @return True to indicate the insertion was successful.
     */
    @Override
    public boolean insert(E value, int index) {

        // If the ArrayList is empty, simply insert
        // into the front of the internal array
        if (empty()) {
            elements[0] = value;
            size++;
            return true;
        }

        // Make sure we are in bounds
        verifyIndex(index);

        if (full()) {

            @SuppressWarnings("unchecked")
            E[] temp = (E[]) new Object[size * 2];

            // Copy the whole array with an offset of 1,
            // then overwrite the first value
            if (index == 0) {
                copy(0, size-1, 1, elements, temp);
                temp[0] = value;

            // Copy up until the desired index, preserving index
            // Place the new value in its designated location
            // Copy the rest of the array with an offset of 1
            } else {
                copy(0, index, 0, elements, temp);
                temp[index] = value;

                // If we are not already at the end
                // copy the remaining values over
                if (index < size-1) {
                    copy(index+1, size-1, 1, elements, temp);
                }

            }

            // Use temp as our new elements array
            elements = temp;

        // There is space in the array
        } else {

            // Make room for new value
            shiftRight(index);

            // Insert into vacant spot
            elements[index] = value;
        }

        size++;
        allocations++;

        return true;
    }



    /**
     * Inserts a value into the front of the list.
     *
     * @param value Specified value to insert
     * @return True to indicate the prepend was successful.
     */
    @Override
    public boolean prepend(E value) {

        return insert(value, 0);
    }

    /**
     * Inserts an element to the back of the list.
     * @param value Specified value to insert
     * @return True to indicate the append was successful.
     */
    @Override
    public boolean append(E value) {

        if (empty()) {
            elements[0] = value;
            size++;
            return true;
        }

        // The internal array is full
        if (full()) {

            allocations++;

            @SuppressWarnings("unchecked")
            E[] temp = (E[]) new Object[size * 2];

            // Copy the old array to a new array
            copy(0, size-1, 0, elements, temp);

            // Place a new value at the back
            // of the new array
            temp[size] = value;

            // Use the new array as the
            // internal array of this
            // ArrayList
            elements = temp;

        // There's space, so insert
        } else {
            elements[size] = value;
        }


        size++;

        return true;
    }

    /**
     * Retrieves and removes the value at a specified index.
     *
     * @param index Index to remove value from.
     * @return Value at specified index.
     */
    @Override
    public E remove(int index) {
        E value;

        // Cannot remove from nothing
        if (empty()) {
            throw new EmptyDataStructureException("Cannot remove from an empty ArrayList");
        }

        verifyIndex(index);       // Verify that the index is valid
        value = elements[index];  // Store the value are the given index
        shiftLeft(index);         // Shift all elements up from the right of index over one to the left
        size--;                   // Decrement size of array
        return value;             // Return the stored value
    }

    /**
     * Retrieves and removes the first value in the list.
     *
     * @return The first value in the list
     */
    @Override
    public E removeFirst() {
        return remove(0);
    }

    /**
     * Retrieves and removes the last value in the list.
     *
     * @return Last value in the list
     */
    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * Retrieves and removes the last value in the list.
     *
     * @return The last value in the list
     */
    @Override
    public E remove() {
        return removeLast();
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
        verifyIndex(index);

        for (int i = size; i > index; i--) {

           elements[i] = elements[i-1];
           shifts++;
        }
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
     * @param index Specified index to shift left into
     */
    private void shiftLeft(int index) {
        verifyIndex(index);

        // Partial rotation
        for (int i = index; i < size; i++) {
            elements[i] = elements[i+1];
            shifts++;
        }
    }


    /**
     * Returns a String representation of the list.
     *
     * @return String version of the list
     */
    @Override
    public String toString() {

        return Arrays.toString(toArray());
    }

    /**
     * Overwrites a value at a specified index
     * with a new value.
     *
     * @param value New value.
     * @param index Specified index.
     */
    public void update(E value, int index) {
        verifyIndex(index);
        elements[index] = value;
    }

}