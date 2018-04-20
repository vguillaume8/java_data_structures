package structures.commons;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Abstract class that classes that use an underlying
 * dynamic array will extend. Note, even though this
 * class provides an iterator, it purposely does not
 * implement Iterable because when we use this class with
 * more complex types such as Trees, or potentially hash maps,
 * we will not be iterating over the same generic type as
 * the object. Example, BinarySearchTree&lt; K, V&gt; should give
 * back an Iterator&lt;Pair&lt;K,V&gt;&gt; not an Iterator&lt; K,V&gt;. The
 * latter is not possible. So to avoid this conflict, we
 * allow simpler classes to inherit an iterator() method,
 * such as Stack, List, and Queue, but we do not want all
 * implementing classes to be bound to the contract of
 * supplying an iterator that may not be useful for that
 * given class and it's use-case.
 *
 * @author Jabari Dash
 * @param <E> Generic data type
 */
public abstract class DynamicArray<E> implements DataStructure<E> {

    /**
     * Default resize threshold for internal array.
     * 0.85 represents 85%.
     */
    private final double DEFAULT_RESIZE_THRESHOLD = 0.85;

    /**
     * Default initialize size of internal array
     */
    private final int    DEFAULT_INITIAL_SIZE     = 10;

    /**
     * When the array reaches this threshold
     * the array will double in size.
     */
    private final double RESIZE_THRESHOLD;

    /**
     * When the array must grow, it
     * will grow by this factor.
     */
    private final int GROW_FACTOR = 2;

    /**
     * Initial size of internal array
     */
    private final int INITIAL_SIZE;

    /**
     * Number of elements present in the ArrayList.
     */
    protected int size;

    /**
     * The internal array containing the elements in the ArrayList.
     */
    public E[] elements;

    /**
     * The amount of times the array needed to be resized.
     * We use this value for analytic purposes. It has no
     * effect on the structures.performance or behavior of the ArrayList.
     */
    private int allocations;

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
     * Integer that points to the index of the beginning
     * of the sequence within the internal array. For queues,
     * this value will change. For Stacks, and LinkedList,
     * it will remain the 0.
     */
    protected int front = 0;

    /**
     * Integer that points to the index of the
     * end of the sequence within the internal array.
     * Essentially, this will always be size() - 1,
     * but for cleaner syntax we will use this variable.
     */
    protected int back = 0;

    /**
     * Constructs default DynamicArray with resize
     * threshold 90% and a specified initial size.
     *
     * @param initialSize Specified initial size of internal array.
     */
    protected DynamicArray(int initialSize) {

        this.RESIZE_THRESHOLD = DEFAULT_RESIZE_THRESHOLD;
        this.INITIAL_SIZE     = initialSize >= DEFAULT_INITIAL_SIZE ? initialSize : DEFAULT_INITIAL_SIZE;

        // Create a new array no smaller than the default size
        elements = (E[]) new Object[this.INITIAL_SIZE];
    }

    /**
     * Constructs default DynamicArray with resize
     * threshold 90% and initial size 10.
     */
    protected DynamicArray() {

        this.RESIZE_THRESHOLD = DEFAULT_RESIZE_THRESHOLD;
        this.INITIAL_SIZE     = DEFAULT_INITIAL_SIZE;
        elements              = (E[]) new Object[this.INITIAL_SIZE];
    }

    /**
     * Construct a DynamicArray from
     * an array of values.
     *
     * @param values Array of values.
     */
    protected DynamicArray(E[] values) {

        this(values.length);
        insert(values);
    }

    /**
     * Construct a DynamicArray from
     * a collection of values.
     *
     * @param values Collection of values.
     */
    protected DynamicArray(Collection<E> values) {

        this(values.size());
        insert(values);
    }

    /**
     * Returns the number of memory
     * re-allocations the internal array
     * has undergone such that the dynamic
     * array is in it's present state.
     *
     * @return Number of underwent re-allocations
     */
    public int allocations() {

        return this.allocations;
    }

    /**
     * Returns the value at a
     * specified index in in
     * dynamic array (not the
     * actual internal array)
     *
     * @param index Specified index
     * @return Value at specified index
     */
    protected E access(int index) {

        verifyIndex(index);

        return elements[index];
    }

    /**
     * Insert a value at a
     * specified index
     *
     * @param value Specified value
     * @param index Specified index
     * @return True if the insertion was successful
     */
    protected boolean add (E value, int index) {

        // If the ArrayList is empty, simply insert
        // into the front of the internal array
        if (empty()) {

            elements[front] = value;
            size++;
            return true;
        }

        // Make sure we are in bounds
        verifyIndex(index);

        if (full()) {

            // Allocate a new array double the
            // size of the current internal array
            @SuppressWarnings("unchecked")
            E[] temp = grow();

            // Copy the whole array with an offset of 1,
            // then overwrite the first value
            if (index == 0) {

                copy(front, size-1, 1, elements, temp);

                temp[0] = value;

                // Copy up until the desired index, preserving index
                // Place the new value in its designated location
                // Copy the rest of the array with an offset of 1
            } else {

                copy(front, index, 0, elements, temp);

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
     * Inserts an element to the back of the list.
     * @param value Specified value to insert
     * @return True to indicate the append was successful.
     */
    protected boolean append(E value) {

        // Check if the internal
        // array is empty
        if (empty()) {

            elements[front] = value;
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
            E[] temp = grow();

            // Copy the old array to a new array
            copy(front, size-1, 0, elements, temp);


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
     * Returns the number of individual
     * data copies (from one location to another)
     * that the dynamic array has undergone such
     * that it can be in it's current state.
     *
     * @return Number of underwent copies.
     */
    public int copies() {

        return copies;
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
    private void copy(int start, int stop, int offset, E[] src, E[] dst) {

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
        // NOTE - If front is not 0, then
        // there is an additional offset that
        // we must take into account. Since we
        // are copying to a new array, we eliminate
        // the empty space in front of "front" by
        // shifting down to the left by "front"
        // number of spaces.
        for (int i = start; i <= stop; i++) {

            dst[i + offset] = src[i];

            copies++;
        }

    }

    /**
     * Removes and returns a value
     * at a specified index from
     * data structure.
     *
     * @param index Specified index
     * @return The value at specified index.
     */
    protected E delete(int index) {
        E value;

        // Cannot remove from nothing
        if (empty()) {

            throw new EmptyDataStructureException("Cannot remove from an empty ArrayList");
        }

        verifyIndex(index);

        value = elements[index];

        shiftLeft(index);

        size--;

        return value;
    }

    /**
     * Determines whether or not this DynamicArray is equal to
     * a provided object.
     *
     * @param object Object to compare this ArrayList with.
     * @return True if and only if their types are the same,
     * lengths are the same, and the contain all the same elements.
     */
    @Override
    public boolean equals(Object object) {

        // Object must be an ArrayList, and
        // all elements must be equal, or object
        // must be this ArrayList itself
        return equivalentTo(object);
    }


    /**
     * If the number of elements in the ArrayList exceeds a specified
     * threshold (90%), we consider the internal array "full".
     *
     * @return True if and only if the capacity threshold is passed.
     */
    private boolean full() {

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
     * Returns an that has a capacity
     * that is some scalar multiple of
     * the current size of the data structure,
     * where the scalar multiple is greater than 2.
     *
     * @return New empty array of larger capacity
     */
    private E[] grow() {

        E[] temp = (E[]) new Object[size * GROW_FACTOR];

        return temp;
    }

    /**
     *
     * @return
     */
    private E[] shrink() {
        return null;
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
     * Returns iterator for dynamic array.
     *
     * @return Iterator
     */
    public Iterator<E> iterator() {

        return new DynamicArrayIterator<>(true);
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
    private void shiftLeft(int index) {

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
    private void shiftRight(int index) {

        for (int i = size; i > index; i--) {

            elements[i] = elements[i-1];

            shifts++;
        }
    }
    
    /**
     * Overwrites a value at a specified
     * index with a new value.
     *
     * @param index Specified index
     * @param value New value
     * @return True if the overwrite was successful.
     */
    protected boolean update(int index, E value) {

        verifyIndex(index);

        elements[index] = value;

        return true;
    }

    /**
     * Iterator for iterating over any class that implements
     * the DynamicArray interface. The iterator has a mandatory
     * boolean argument that specifies whether or not to iterate
     * over the array be ascending indicies or descending. This way
     * Stacks, Queues, and Lists that us the DynamicArray interface
     * can all use the same iterator and iterate over the array
     * in O(n) time and O(1) space.
     *
     * @param <E>
     */
    protected class DynamicArrayIterator<E> implements Iterator<E> {

        /**
         * Index of next item to return in iteration.
         */
        int cursor;

        /**
         * Boolean flag that says whether or not we
         * should iterate going from left to right
         * (ascending indicies) or right to left
         * (descending).
         */
        final boolean ascending;

        /**
         * Constructor for iterator over
         * dynamic arrays. Requires boolean
         * flag to determine whether to
         * iterate from high to low, or
         * low to high indicies.
         *
         * @param ascending Boolean flag.
         */
        public DynamicArrayIterator(boolean ascending) {

           this.ascending = ascending;

            // If ascending, start at first index,
            // otherwise start at last index
            cursor = ascending ? front : size - 1;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {

            return ascending ? cursor < size : cursor >= front;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {

            E element = (E) elements[cursor];

            // If ascending, increase cursor,
            // otherwise decrease cursor
            cursor += ascending ? 1 : -1;

            return element;
        }
    }
}
