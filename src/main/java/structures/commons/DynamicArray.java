package structures.commons;

import java.util.Arrays;

/**
 * Abstract class that classes that use an underlying
 * dynamic array will extend.
 *
 * @author Jabari Dash
 * @param <E> Generic data type
 */
public abstract class DynamicArray<E> implements DataStructure<E> {

    private final double DEFAULT_RESIZE_THRESHOLD = 0.9;
    private final int    DEFAULT_INITIAL_SIZE     = 10;


    /**
     *
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
    protected int shifts;

    /**
     * The number of times any element has been copied from one
     * array to another. This occurs when the {@code copy()} function is
     * called. If n values are copied, this value increases by n.
     */
    protected int copies;

    public DynamicArray() {
        this.RESIZE_THRESHOLD = DEFAULT_RESIZE_THRESHOLD;
        this.INITIAL_SIZE     = DEFAULT_INITIAL_SIZE;

        elements = (E[]) new Object[this.INITIAL_SIZE];
    }


    public DynamicArray(int length) {
        this.RESIZE_THRESHOLD = DEFAULT_RESIZE_THRESHOLD;
        this.INITIAL_SIZE     = length;

        // Create a new array no smaller than the default size
        elements = (E[]) new Object[length < DEFAULT_INITIAL_SIZE ? DEFAULT_INITIAL_SIZE : length];
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
