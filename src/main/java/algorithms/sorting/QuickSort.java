package algorithms.sorting;

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
    public static <E extends Comparable> int quickSortHoare(E[] a) {

        return quickSortHoare(a, 0, a.length-1, 0);
    }

    /**
     *
     * @param <E>
     * @return
     */
    public static <E extends Comparable> int quickSortLumoto(E[] a) {

        return quickSortLumoto(a, 0, a.length-1, 0);
    }

    /**
     * Quick sort using Hoare partition
     * scheme for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    private static <E extends Comparable> int quickSortHoare(E[] a, int low, int high, int c) {

        if (low < high) {

            int i  = low;
            int j = high;

            E pivot = a[Util.rand(low, high)];

            while (i <= j) {

                while (a[i].compareTo(pivot) < 0) {

                    c++;

                    i++;
                }

                while (a[j].compareTo(pivot) > 0) {

                    c++;

                    j--;
                }

                if (i <= j) {

                    swap(a, i, j);

                    i++;
                    j--;
                }
            }


            if (low < j) {

                c+= quickSortHoare(a, low, j, 0);
            }

            if (i < high) {

                c+= quickSortHoare(a, i, high, 0);
            }

        }

        return c;
    }

    /**
     * Quick sort using Lumoto partition
     * scheme for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    private static <E extends Comparable> int quickSortLumoto(E[] a, int low, int r, int c) {

        if (low < r) {

            int pivot = r;
            int i     = low - 1;

            for (int j = low; j <= r-1; j++) {

                if (a[j].compareTo(a[pivot]) < 0) {

                    c++;

                    i++;
                    swap(a, i, j);
                }

            }

            if (a[r].compareTo(a[i+1]) < 0) {

                c++;

                swap(a, i+1, r);
            }

            pivot = i + 1;

            c += quickSortLumoto(a, low, pivot-1, 0);
            c += quickSortLumoto(a, pivot+1, r, 0);
        }

        return c;
    }
}
