package algorithms.sorting;

import algorithms.commons.Output;
import util.Util;

/**
 * Implementations of quick sort.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public final class QuickSort<E extends Comparable> extends SortingAlgorithm<E> {

    /**
     * Constructs bubble sort object.
     */
    public QuickSort() {
        super();

        functions.put(DEFAULT, QuickSort::quickSortHoare);
        functions.put("hoare", QuickSort::quickSortHoare);
        functions.put("lumoto", QuickSort::quickSortLumoto);
    }

    /**
     *
     * @param <E>
     * @return
     */
    public static <E extends Comparable> Output quickSortHoare(E[] a, Output output) {

        return quickSortHoare(a, 0, a.length-1, output);
    }

    /**
     *
     * @param <E>
     * @return
     */
    public static <E extends Comparable> Output quickSortLumoto(E[] a, Output output) {

        return quickSortLumoto(a, 0, a.length-1, output);
    }

    /**
     * Quick sort using Hoare partition
     * scheme for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    private static <E extends Comparable> Output quickSortHoare(E[] a, int low, int high, Output output) {

        output.methodCalls++;

        if (low < high) {

            int i  = low;
            int j = high;

            E pivot = a[Util.rand(low, high)];

            while (i <= j) {

                while (lessThan(a[i], pivot, output)) {

                    i++;
                }

                while (greaterThan(a[j], pivot, output)) {

                    j--;
                }

                if (i <= j) {

                    swap(a, i, j, output);

                    i++;
                    j--;
                }
            }


            if (low < j) {

                quickSortHoare(a, low, j, output);
            }

            if (i < high) {

                quickSortHoare(a, i, high, output);
            }

        }

        return output;
    }

    /**
     * Quick sort using Lumoto partition
     * scheme for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    private static <E extends Comparable> Output quickSortLumoto(E[] a, int low, int r, Output output) {

        output.methodCalls++;

        if (low < r) {

            int pivot = r;
            int i     = low - 1;

            for (int j = low; j <= r-1; j++) {

                if (lessThan(a[j], a[pivot], output)) {

                    i++;
                    swap(a, i, j, output);
                }

            }

            if (lessThan(a[r], a[i+1], output)) {

                swap(a, i+1, r, output);
            }

            pivot = i + 1;

            quickSortLumoto(a, low, pivot-1, output);
            quickSortLumoto(a, pivot+1, r, output);
        }

        return output;
    }
}
