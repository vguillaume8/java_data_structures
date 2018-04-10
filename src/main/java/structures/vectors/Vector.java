package structures.vectors;

import structures.commons.DataStructure;
import java.util.Arrays;
import java.util.Iterator;

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
        return Arrays.toString(this.toArray());
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

    // TODO -
    // Might be able to abstract this to the DataStructure
    // class. The same code can be user for Set, and potentially
    // Maps, Trees, and Graphs. If we abstract it, the subclasses
    // will only be responsible for checking the equality of things
    // not covered by this function. As examples:

    /*
        The set class is fully compatible with this function. Just
        the class type equality would be verified when calling this
        function in the equals() method.

        In terms of Maps, if we are checking for equality amongst
        any type of map, then this function is also sufficient. Obviously
        this means we are not taking into account the organization of
        the data (internal structure, hashing method etc) and just the
        entries in the table itself.

        As for trees, this method is not sufficient. This method
        utilized the iterator. Meaning we are only verifying the
        presence of data. However, depending on how we define equal()
        for a tree, this may not be sufficient. example, the tree
        1 2 3 4 is not the same as 3 1 2 4, as the structure would be
        different. We can however, still abstract this method, and just
        not call it in the equals() method for trees.

        In terms of graphs, it is a similar case as trees. Their equality
        depends on both the presence of all elements, but also their
        organization. However, depending on how we implement Graph, this
        function may be sufficient. If we consider a Graph simply a list
        of Verticies, merely looping through and checking the equality of
        the list of verticies will verify the equality of the structure
        as well.
     */

    /**
     * Determines whether or not an Object is equal to
     * this {@code Vector}. Implementing classes can
     * override the java.lang.Object.equals() method, and
     * call this.
     *
     * @param object Object to compare this object with.
     * @return True if the objects are of the same type, their
     * sizes are the same, and they contain all the same keys.
     */
    default boolean equivalentTo(Object object) {
        Iterator<T> thisIterator;
        Iterator<T> thatIterator;

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

            // Iterate over both lists
            // simultaneously
            thisIterator = this.iterator();
            thatIterator = vector.iterator();

            while (thisIterator.hasNext() && thatIterator.hasNext()) {
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

        // If we made it to the bottom,
        // they are equal
        return true;
    }
}
