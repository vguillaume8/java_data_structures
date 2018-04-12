package structures.commons;

import java.util.Arrays;
import java.util.Collection;

/**
 * Abstract class that classes that use an underlying
 * dynamic array will extend.
 *
 * @author Jabari Dash
 * @param <E> Generic data type
 */
public abstract class DynamicArray<E> implements DataStructure<E> {

    /**
     * Default resize threshold for internal array.
     * 0.9 represents 90%.
     */
    private final double DEFAULT_RESIZE_THRESHOLD = 0.9;

    /**
     * Default initialize size of internal array
     */
    private final int    DEFAULT_INITIAL_SIZE     = 10;

    /**
     * When the array reaches this threshold
     * the array will double in size.
     */
    protected final double RESIZE_THRESHOLD;

    /**
     *
     */
    protected final int INITIAL_SIZE;

    /**
     * Number of elements present in the ArrayList.
     */
    protected int size;

    /**
     * The internal array containing the elements in the ArrayList.
     */
    protected E[] elements;

    /**
     * The amount of times the array needed to be resized.
     * We use this value for analytic purposes. It has no
     * effect on the structures.performance or behavior of the ArrayList.
     */
    protected int allocations;

    /**
     * The number of times an element has been shifted in the array.
     * This goes for individual elements. So a shift left when the ArrayList
     * of size 10 will increase shifts by 10. This variable is
     * used for analytics purposes. It has not effect on
     * the structures.performance or behavior of the ArrayList.
     */
    private int shifts;

    /**
     * The number of times any element has been copied from one
     * array to another. This occurs when the {@code copy()} function is
     * called. If n values are copied, this value increases by n.
     */
    private int copies;

    /**
     * Constructs default DynamicArray with resize
     * threshold 90% and initial size 10.
     */
    public DynamicArray() {
        this.RESIZE_THRESHOLD = DEFAULT_RESIZE_THRESHOLD;
        this.INITIAL_SIZE     = DEFAULT_INITIAL_SIZE;
        elements              = (E[]) new Object[this.INITIAL_SIZE];
    }

    /**
     * Constructs default DynamicArray with resize
     * threshold 90% and a specified initial size.
     *
     * @param initialSize Specified initial size of internal array.
     */
    public DynamicArray(int initialSize) {
        this.RESIZE_THRESHOLD = DEFAULT_RESIZE_THRESHOLD;
        this.INITIAL_SIZE     = initialSize;

        // Create a new array no smaller than the default size
        elements = (E[]) new Object[initialSize < DEFAULT_INITIAL_SIZE ? DEFAULT_INITIAL_SIZE : initialSize];
    }

    public DynamicArray(E[] values) {
        this(values.length);
        insert(values);
    }

    public DynamicArray(Collection<E> values) {
        this(values.size());
        insert(values);
    }

    /**
     *
     * @return
     */
    public int allocations() {
        return this.allocations;
    }

    /**
     *
     *
     * @param value
     * @param index
     * @return
     */
    protected boolean add (E value, int index) {
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

            // Allocate a new array double the
            // size of the current internal array
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

        //
        size++;

        //
        allocations++;

        return true;
    }

    /**
     * Inserts an element to the back of the list.
     * @param value Specified value to insert
     * @return True to indicate the append was successful.
     */
    protected boolean append(E value) {

        // Check if the internal
        // array is empty
        if (empty()) {
            elements[0] = value;
            size++;
            return true;
        }

        // The internal array is full
        if (full()) {

            // Increment the number of
            // allocations performed
            allocations++;

            // Create a new array with double the size
            @SuppressWarnings("unchecked")
            E[] temp = (E[]) new Object[size * 2];

            // Copy the old array to a new array
            copy(0, size-1, 0, elements, temp);

            // Place a new value at the
            // back of the new array
            temp[size] = value;

            // Use the new array as the
            // internal array of this
            // ArrayList (get rid of the old one)
            elements = temp;

            // There's space, so insert
            // at the back of new array
        } else {
            elements[size] = value;
        }

        // Increment the number of
        // elements in the ArrayList
        size++;

        return true;
    }

    /**
     *
     * @return
     */
    public int copies() {
        return this.copies;
    }

    /**
     * Copy a specified range of elements from one
     * array to another.
     *
     * @param start First index in range from which to copy
     * @param stop Last index in range from which to copy
     * @param src Source array from which to copy
     * @param dst Destination array to copy to
     */
    protected void copy(int start, int stop, int offset, E[] src, E[] dst) {

        // Must be within bounds
        if (start < 0 || start >= src.length) {
            throw new IllegalArgumentException("Start index must be in bounds or source array");
        }

        // If start index exceeds stop index
        if (start > stop) {
            throw new IllegalArgumentException("Start index must be less than or equal to stop index");
        }

        // Must be within bounds
        // TODO - IntelliJ says stop always < 0, maybe if-else can be simplified
        if (stop < 0 || stop >= src.length) {
            throw new IllegalArgumentException("Stop index must be in bounds or src array");
        }

        // destination array must be at least size of source
        if (src.length >= dst.length) {
            throw new IllegalArgumentException("Destination array must be at least the length of the source array");
        }

        // Copy the array
        for (int i = start; i <= stop; i++){

            dst[i+offset] = src[i];
            copies++;
        }

    }

    /**
     *
     * @param index
     * @return
     */
    protected E delete(int index) {
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
     * If the number of elements in the ArrayList exceeds a specified
     * threshold (90%), we consider the internal array "full".
     *
     * @return True if and only if the capacity threshold is passed.
     */
    protected boolean full() {

        // Convert size and length to doubles
        // to perform division with decimals
        double s = (double) size;
        double l = (double) elements.length;

        // If the ratio of elements in the DataStructure exceeds the
        // threshold (default 90%), then we need to allocate more space
        // in the internal array
        return (s / l) > RESIZE_THRESHOLD;
    }

    /**
     * Returns the size of the internal list. Note, this
     * does not specify the number of elements in the list,
     * rather the number of spaces that the dynamically
     * allocated array that is used to contain the elements
     * has at a given point in time.
     *
     * @return Length of internal array that contains the elements
     */
    public int internalSize() {
        return elements.length;
    }

    /**
     * Inserts a value into the front of the list.
     *
     * @param value Specified value to insert
     * @return True to indicate the prepend was successful.
     */
    protected boolean prepend(E value) {

        return add(value, 0);
    }

    /**
     * Returns total number of values that
     * have been shifted to the left or right
     * such that the ArrayList is in its present state.
     *
     * @return Total number of shifts.
     */
    @SuppressWarnings("unused")
    public int shifts() {
        return shifts;
    }

    /**
     * Returns the number of elements in the ArrayList.
     *
     * @return Number of elements in the ArrayList.
     */
    @Override
    public int size() {
        return size;
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
    protected void shiftLeft(int index) {
//        verifyIndex(index);

        // Partial rotation
        for (int i = index; i < size; i++) {
            elements[i] = elements[i+1];
            shifts++;
        }
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
    protected void shiftRight(int index) {
//        verifyIndex(index);

        for (int i = size; i > index; i--) {

            elements[i] = elements[i-1];
            shifts++;
        }
    }

    /**
     * Returns data structure as an array of specified type.
     *
     * @param array Array that specifies the type of the array to return.
     * @return Array representation of the data structure.
     */
    public E[] toArray(E[] array) {

        try {

            array = (E[]) Arrays.copyOf(elements, size(), array.getClass());

        } catch (ArrayStoreException exception) {

            throw new IllegalArgumentException("Array type " + array.getClass().getSimpleName() + " is invalid");
        }

        return array;
    }

}
