package structures.commons;

/**
 * Interface for all linear DataStructures such as {@code LinkedList}, {@code ArrayList},
 * or any other vector-like data structure.
 *
 * @author Jabari Dash
 * @param <T> Generic Type
 */
public interface LinearDataStructure<T> extends DataStructure<T>,  Iterable<T> {

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
     * Returns the data structure as an Object array.
     *
     * @return Array representation of DataStructure
     */
    @SuppressWarnings("unused")
    T[] toArray();

//------------------------------------------------------------------------------

    /**
     * Returns the data structure as an array of a specified type.
     *
     * @param array Array that specified the type.
     * @return Array version of the data structure.
     */
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
    default boolean equivalentTo(Object object) {

        // If object is compared to itself
        if (this == object)
            return true;

        // Check that the object is an instance of LinearDataStructure
        if (!(object instanceof LinearDataStructure))
            return false;

        try {

            // Cast to LinearDataStructure, and compare the values
            @SuppressWarnings("unchecked")
            LinearDataStructure<T> vector = (LinearDataStructure<T>) object;

            // Check length
            if (vector.size() != this.size()) {
                return false;
            }

            // Check each value
            for (T value : this) {
                if (!vector.contains(value)) {
                    return false;
                }
            }

        // If a ClassCastException was thrown, then
        // the object could not be casted to a LinearDataStructure,
        // and thus, the objects cannot be equal to each other. Note,
        // this is a double check, after we checked instance of LinearDataStructure.
        // Also note, we do not want to catch all Exceptions because in the event
        // that the code that implements size(), or contains(), or the iterator()
        // used in the foreach loop throws an error, we want the developer to know.
        // This indicated their is a bug in their code, and we do not want to
        // handle those. We are only looking to handle Exceptions related to casting here.
        } catch (ClassCastException exception) {
            return false;
        }

        // If we made it to the bottom,
        // they are equal
        return true;
    }
}
