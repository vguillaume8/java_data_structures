package algorithms.sorting;

import java.util.HashMap;
import java.util.function.Function;

/**
 * Template class for
 * sorting algorithm classes
 * to follow.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public abstract class SortingAlgorithm<E extends Comparable> {

    /**
     *
     */
    protected static final String DEFAULT = "default";

    /**
     * HashMaps of sorting algorithms; Default size 5;
     */
    protected HashMap< String, Function< E[], Integer> > functions;

    /**
     *
     */
    public SortingAlgorithm() {

        functions = new HashMap<>();
    }

    /**
     *
     * @param a
     * @param version
     * @return
     */
    public int sort(E[] a, String version) {

        return functions.get(version).apply(a);
    }

    /**
     * Performs default sorting method
     * on array.
     *
     * @param a Array to sort
     * @return Number of comparisons required to complete sort.
     */
    public int sort(E[] a) {

        return sort(a, DEFAULT);
    }

    /**
     * Swaps objects that are in ith and jth
     * position of an array.
     *
     * @param a Array of objects.
     * @param i First index
     * @param j Second index
     */
    protected static <E extends Comparable> void swap(E[] a, int i, int j) {
        E t;

        t    = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
