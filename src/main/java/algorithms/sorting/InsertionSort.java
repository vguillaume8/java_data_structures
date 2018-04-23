package algorithms.sorting;

import algorithms.commons.Output;

/**
 * Implementation of insertion sort.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public final class InsertionSort<E extends Comparable<E>> extends SortingAlgorithm<E> {

    /**
     * Constructs InsertionSort object.
     */
    public InsertionSort() {
        super();

        functions.put(DEFAULT, InsertionSort::insertionSort);
    }

    /**
     * Insertion sort for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    public static <E extends Comparable<E>> Output insertionSort(E[] a, Output output) {

        output.methodCalls++;

        int j;

        for (int i = 0; i < a.length; i++) {

            j = i;

            while (j > 0 && greaterThan(a[j-1], a[j], output)) {

                swap(a, j-1, j, output);
                j--;
            }
        }

        return output;
    }

}
