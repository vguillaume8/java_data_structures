package structures.commons;

/**
 * Incomplete class that should be implemented
 * by classes that have growing arrays in the
 * back. For example, Heaps or ArrayLists
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public interface DynamicallySizedArray<T> extends IndexedDataStructure<T> {

    /**
     * Determines whether or not the internal array is full
     *
     * @return True if the internal array has run out of space for new elements
     */
    boolean full();

//------------------------------------------------------------------------------

    /**
     * If the internal array is full, it's size will be doubled plus 1
     */
    default void alloc() {
        if (this.full()) {
            this.alloc(this.size() * 2 + 1);
        }
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param length
     */
    void alloc(int length);

//------------------------------------------------------------------------------

    /**
     * Returns an array representation of this object.
     *
     * @return Array version of this object.
     */
    default Object[] toArray() {

        // Cast is ok, all T objects and java.langObject subclasses
        @SuppressWarnings("unchecked")
        Object[] objects = this.toArray((T[]) new Object[0]);

        return objects;
    }

//------------------------------------------------------------------------------

    /**
     * Returns data structure as an array of specified type.
     *
     * @param array Array that specifies the type of the array to return.
     * @return Array representation of the data structure.
     */
    T[] toArray(T[] array);
}
