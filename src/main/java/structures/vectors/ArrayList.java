package structures.vectors;

import java.util.Arrays;

/**
 * Basic implementation of a generic ArrayList
 *
 * @author Jabari Dash
 * @param <K> Generic type
 */
public final class ArrayList<K> implements List<K> {

    private static final double RESIZE_THRESHOLD = 0.9;
    private static final int DEFAULT_SIZE = 10;

    private int size;
    private K[] keys;
    private int allocations;

    /**
     *
     * @param length
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int length) {

        // Create a new array no smaller than the default size
        keys = (K[]) new Object[length < DEFAULT_SIZE ? DEFAULT_SIZE : length];
    }

    /**
     * Constructs empty list.
     */
    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructs ArrayList from array of keys.
     *
     * @param values Array of keys to construct the list from
     */
    public ArrayList(K[] values) {
        this();
        insert(values);
    }

    /**
     * If the internal array is full, it's size will be doubled plus 1
     */
    private boolean alloc() {

        // If the ratio of elements in the DataStructure exceeds the
        // threshold (default 90%), then we need to allocate more space
        // in the internal array
        return (size / keys.length) > RESIZE_THRESHOLD && alloc(size/2);
    }

    /**
     * Allocates (or de-allocates) space in the internal array
     *
     * @param slots How many new slots to make
     */
    private boolean alloc(int slots) {
        int length;

        length = keys.length + slots;

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

        // Cast is safe, all objects of type
        // K extend java.lang.Object
        @SuppressWarnings("unchecked")
        K[] temp = (K[]) new Object[length];

        // Pick the shorter of the two lengths
        // to avoid running of the end and having
        // an IndexOutOfBounds exception thrown
        System.arraycopy(keys,
                        0, temp,
                        0,
                        length < keys.length ? temp.length : keys.length);

        keys = temp;

        allocations++;

        return true;
    }

    /**
     *
     * @return
     */
    public int allocations() {
        return allocations;
    }


    /**
     * Determines whether or not this ArrayList is equal to
     * a provided object.
     *
     * @param object Object to compare this ArrayList with.
     * @return True if and only if their types are the same,
     * lengths are the same, and the contain all the same keys.
     */
    @Override
    public boolean equals(Object object) {

        // Object must be an ArrayList, and all keys must be equal, or object
        // must be this ArrayList itself
        return this == object || (object instanceof ArrayList && equivalentTo(object));
    }

    /**
     * Returns the value at a specified index.
     *
     * @param index Specified index
     * @return Value at specified index
     */
    @Override
    public K get(int index) {
       verifyIndex(index);
       return keys[index];
    }

    /**
     * Inserts a key at the end of the list.
     *
     * @param key The specified key to insert
     */
    @Override
    public boolean insert(K key) {
        return insertLast(key);

    }

    /**
     * Inserts a value at a specified index.
     *
     * @param value Value to be inserted
     * @param index Specified index to insert value at
     */
    @Override
    public boolean insert(K value, int index) {
        if (!empty() && index < size) {
            verifyIndex(index);    // Verify that the index is a valid index
            shiftRight(index);     // Shift all keys up one index, starting at designated index
        }

        alloc();                   // Potentially alloc the internal array before insertion
        keys[index] = value;     // Insert the new value into the designated index
        size++;                    // Increment size of list

        return true;
    }

    /**
     *
     * @return
     */
    public int internalSize() {
        return keys.length;
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

        System.arraycopy(keys, 0, keys, 1, index);
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
            keys[i] = keys[i+1];
        }

        // TODO - Use Java API to complete this shift left
//        System.arraycopy(keys, index+1, keys, index+2, size);
    }

    /**
     * Inserts a value into the front of the list.
     *
     * @param value Specified value to insert
     */
    @Override
    public boolean insertFirst(K value) {
        return insert(value, 0);
    }

    /**
     * Inserts an element to the back of the list.
     * @param value Specified value to insert
     */
    @Override
    public boolean insertLast(K value) {
        return insert(value, size);
    }

    /**
     * Retrieves and removes the value at a specified index.
     *
     * @param index Index to remove value from
     * @return Value at specified index
     */
    @Override
    public K remove(int index) {
        K value;

        if (empty()) {
            throw new EmptyDataStructureException("Cannot remove from an empty ArrayList");
        }

        verifyIndex(index);   // Verify that the index is valid
        value = keys[index];  // Store the value are the given index
        shiftLeft(index);     // Shift all keys up from the right of index over one to the left
        size--;               // Decrement size of array
        return value;         // Return the stored value
    }

    /**
     * Retrieves and removes the first value in the list.
     *
     * @return The first value in the list
     */
    @Override
    public K removeFirst() {
        return remove(0);
    }

    /**
     * Retrieves and removes the last value in the list.
     *
     * @return Last value in the list
     */
    @Override
    public K removeLast() {
        return remove(size - 1);
    }

    /**
     * Retrieves and removes the last value in the list.
     *
     * @return The last value in the list
     */
    @Override
    public K remove() {
        return removeLast();
    }

    /**
     *
     * @return
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
    public K[] toArray(K[] array) {

        try {
            array = (K[]) Arrays.copyOf(keys, size(), array.getClass());

        } catch (ArrayStoreException exception) {

            throw new IllegalArgumentException("Array type " + array.getClass().getSimpleName() + " is invalid");
        }

        return array;
    }

    /**
     * Returns a String representation of the list
     *
     * @return String version of the list
     */
    @Override
    public String toString() {

        return Arrays.toString(toArray());
    }

}