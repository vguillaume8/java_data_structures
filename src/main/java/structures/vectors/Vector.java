package structures.vectors;

import structures.commons.DataStructure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.StringJoiner;

/**
 * Interface for all linear DataStructures such as {@code LinkedList}, {@code ArrayList},
 * or any other vector-like data structure.
 *
 * @author Jabari Dash
 * @param <T> Generic Type
 */
public interface Vector<T> extends DataStructure<T>, Iterable<T> {

    /**
     * Returns a String representation of the Vector.
     * This function is named {@code asString()} rather than
     * {@code toString()} because this is an interface, and thus
     * {@code toString()} cannot be implemented as a default method
     * because the superclass {@code Object} already implements it.
     * Therefore, only a concrete class can override it.
     * However, the implementation for all Vectors will be
     * roughly the same. So we declare {@code asString()} as a wrapper
     * around {@code toString()} so we avoid having to implement {@code toString()}
     * in each subclass that implements this interface.
     *
     * @return String representation of the Vector
     */
    default String asString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");

        // Iterate through each object
        // and append it to the string joiner
        for (T element : this) {
            sj.add(element.toString());
        }

        return sj.toString();

    }

    /**
     * Determines whether or not a specified value is in the vector
     * via linear search.
     *
     * @param value Specified value to search for.
     * @return True if and only if the specified value if in the vector.
     */
    @Override
    default boolean contains(T value) {

        for (T v : this) {
            if (v.equals(value))
                return true;
        }

        return false;
    }

//------------------------------------------------------------------------------

    /**
     * Removes an element from the DataStructure. The order
     * in which the value comes out depends on the implementation
     * of implementing class
     *
     * @return Removed value from DataStructure
     * @see structures.commons.DataStructure
     */
    T remove();

//------------------------------------------------------------------------------

    /**
     * Returns the data structure as an {@code Object} array.
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
        T[] a = null;

        try {
            // Cast is safe, because we passed a T[] in, so we
            // get a T[] back out
            a = (T[]) Arrays.copyOf(array, this.size(), array.getClass());

            int i = 0;

            // Copy everything from this
            // vector to the new array
            for (T value : this) {
                a[i] = value;
                i++;
            }

        } catch (ArrayStoreException exception) {
            throw new IllegalArgumentException("Array type " + array.getClass().getSimpleName() + " is invalid");
        }

        return a;
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param object
     * @return
     */
    @Override
    default boolean sameContents(Object object) {
        try {
            Iterator<T> thisIterator;
            Iterator<T> thatIterator;

            // Cast to Vector, and compare the keys
            @SuppressWarnings("unchecked")
            Iterable<T> vector = (Iterable<T>) object;


            // Iterate over both lists
            // simultaneously
            thisIterator = this.iterator();
            thatIterator = vector.iterator();

            // This way we are ensuring that they both have the same
            // data and it is ordered in the same way
            while (thisIterator.hasNext() && thatIterator.hasNext()) {

                // Check that ith object of each iterator is equal
                if (!thisIterator.next().equals(thatIterator.next())) {
                    return false;
                }
            }

            // Make sure they ran out at the same time just to be sure
            // (even though we already checked size of the vector passed in)
            if (thisIterator.hasNext() || thatIterator.hasNext()) {
                return false;
            }

            // If a ClassCastException was thrown, then
            // the object could not be casted to a Vector,
            // and thus, the objects cannot be equal to each other. Note,
            // this is a double check, after we checked instance of Vector.
            // Also note, we do not want to catch all Exceptions because in the event
            // that the code that implements code size(), or contains(), or the iterator()
            // used in the foreach loop throws an error, we want the developer to know.
            // This indicated their is a bug in their code, and we do not want to
            // handle those. We are only looking to handle Exceptions related to casting here.
        } catch (ClassCastException exception) {
            return false;
        }

        return true;
    }

}
