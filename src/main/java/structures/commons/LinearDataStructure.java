package structures.commons;

public interface LinearDataStructure<T> extends DataStructure<T>,  Iterable<T> {


//------------------------------------------------------------------------------

    /**
     * Removes an element from the DataStructure. The order
     * in which the value comes out depends on the implementation
     * of implementing class
     *
     * @return Removed value from DataStructure
     */
    T remove();

//------------------------------------------------------------------------------

    /**
     * Returns the data structure as an array
     *
     * @return Array representation of DataStructure
     */
    T[] toArray();

//------------------------------------------------------------------------------

    T[] toArray(T[] array);

//------------------------------------------------------------------------------

    /**
     * Determines whether or not an Object is equal to
     * this LinearDataStructure. Implementing classes can
     * override the java.lang.Object.equals() method, and
     * call this.
     *
     * @param object Object to compare this object with.
     * @return True if the objects are of the same type, their
     * sizes are the same, and they contain all the same values.
     */
    @SuppressWarnings("unchecked")
    default boolean equivalentTo(Object object) {

        // If object is compared to itself
        if (this == object)
            return true;

        // Check that the object is an instance of LinearDataStructure
        if (!(object instanceof LinearDataStructure))
            return false;

        // Cast to LinearDataStructure, and compare the values
        LinearDataStructure vector = (IndexedDataStructure) object;

        // Check length
        if (vector.size() != this.size()) {
            return false;
        }

        // Check class name
        if (!this.getClass().getSimpleName().equals(vector.getClass().getSimpleName())) {
            return false;
        }

        // Check each value
        for (T value : this) {
            if (!vector.contains(value)) {
                return false;
            }
        }

        // If we made it to the bottom,
        // they are equal
        return true;
    }
}
