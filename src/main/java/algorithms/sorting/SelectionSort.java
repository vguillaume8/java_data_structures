package algorithms.sorting;

import algorithms.commons.Output;

public final class SelectionSort<E extends Comparable <E>> extends SortingAlgorithm<E> {

    /**
     * Constructs InsertionSort object.
     */
    public SelectionSort() {
        super();

        functions.put(DEFAULT, SelectionSort::selectionSort);
    }

    /**
     * Selection sort for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    public static <E extends Comparable> Output selectionSort(E[] a, Output output) {
        int min;

        output.methodCalls++;

        for (int i = 0; i < a.length; i++) {

            min = i;

            for (int j = i; j < a.length; j++) {

                if (lessThan(a[j], a[min], output)) {

                    min = j;
                }
            }

            if (min != i) {

                swap(a, min, i, output);
            }
        }

        return output;
    }
}
