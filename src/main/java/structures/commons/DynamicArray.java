package structures.commons;

/**
 * Interface that classes that use an underlying
 * dynamic array will implement.
 *
 * @author Jabari Dash
 * @param <E> Generic data type
 */
public interface DynamicArray<E> extends DataStructure<E> {

    // TODO -
    // Need to use Java 9 compiler to implement private methods here
    // in interface that will make this interface useful. That is, shiftLeft(),
    // shiftRight(), and copy(). We want these methods to be private,
    // but we want to reuse it in other classes such as Graph, and potentially
    // Maps depending on the type of hashing we use.

    /**
     *
     * @return
     */
    int copies();

    /**
     *
     * @return
     */
    int allocations();

    private static void func() {

    }
}
