package algorithms.sorting;

import algorithms.commons.Output;
import util.Util;

import java.util.HashMap;
import java.util.function.BiFunction;
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
    protected HashMap< String, BiFunction< E[], Output, Output>> functions;

    /**
     *
     */
    public SortingAlgorithm() {

        functions = new HashMap<>();
    }

    /**
     *
     * @param first
     * @param second
     * @param output
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> boolean lessThan(E first, E second, Output output) {

        output.comparisons++;

        return first.compareTo(second) < 0;
    }

    /**
     *
     * @param first
     * @param second
     * @param output
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> boolean greaterThan(E first, E second, Output output) {

        Util.x++;

        output.comparisons++;

        return first.compareTo(second) > 0;
    }

    /**
     *
     * @param a
     * @param version
     * @return
     */
    public Output sort(E[] a, String version) {

        Output output = new Output();

        return functions.get(version).apply(a, output);
    }

    /**
     * Performs default sorting method
     * on array.
     *
     * @param a Array to sort
     * @return Number of comparisons required to complete sort.
     */
    public Output sort(E[] a) {

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
    protected static <E extends Comparable> Output swap(E[] a, int i, int j, Output output) {
        E t;

        output.dataSwaps++;

        t    = a[i];
        a[i] = a[j];
        a[j] = t;

        return output;
    }
}
