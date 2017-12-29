package structures.vectors;

import structures.commons.DataStructure;

import java.util.Arrays;

/**
 * Interface for all linear DataStructures such as {@code LinkedList}, {@code ArrayList},
 * or any other vector-like data structure.
 *
 * @author Jabari Dash
 * @param <T> Generic Type
 */
public interface Vector<T> extends DataStructure<T>,  Iterable<T> {

    /**
     *
     * @return
     */
    default String asString() {
        return Arrays.toString(this.toArray());
    }

    /**
     * Determines whether or not a specified value is in the vector
     *
     * <br>
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Best: </strong>&Omega;(1)<br>
     * <strong>Worst: </strong>O(n)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * @param value Specified value to search for
     * @return True if and only if the specified value if in the vector
     */
    @Override
    default boolean contains(T value) {

        for (T v : this) {
            if (v.equals(value))
                return true;
        }

        return false;
    }

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
    default Object[] toArray() {
        // All objects of type T extend java.lang.Object
        @SuppressWarnings("unchecked")
        Object[] objects = this.toArray((T[]) new Object[0]);

        return objects;
    }

//------------------------------------------------------------------------------

    /**
     * Returns the data structure as an array of a specified type.
     *
     * @param array Array that specified the type.
     * @return Array version of the data structure.
     */
    default T[] toArray(T[] array) {
        // Cast is safe, because we passed a T[] in, so we
        // get a T[] back out
        @SuppressWarnings("unchecked")
        T[] a = (T[]) Arrays.copyOf(array, this.size(), array.getClass());

        int i = 0;

        for (T value : this) {
            a[i] = value;
            i++;
        }

        return a;
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not an Object is equal to
     * this Vector. Implementing classes can
     * override the java.lang.Object.equals() method, and
     * call this.
     *
     * @param object Object to compare this object with.
     * @return True if the objects are of the same type, their
     * sizes are the same, and they contain all the same keys.
     */
    default boolean equivalentTo(Object object) {

        // If object is compared to itself
        if (this == object)
            return true;

        // Check that the object is an instance of Vector
        if (!(object instanceof Vector))
            return false;

        try {

            // Cast to Vector, and compare the keys
            @SuppressWarnings("unchecked")
            Vector<T> vector = (Vector<T>) object;

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
        // the object could not be casted to a Vector,
        // and thus, the objects cannot be equal to each other. Note,
        // this is a double check, after we checked instance of Vector.
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
