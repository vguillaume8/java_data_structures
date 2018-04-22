package algorithms.sorting;

import util.Util;

/**
 * Simple sorting algorithms for arrays.
 *
 * @author Jabari Dash
 */
public final class Sorting {

    /**
     * Constant for telling quick sort to use Lumoto partition scheme.
     */
    public static final String LUMOTO_PARTITION_SCHEME = "lumoto";

    /**
     * Constant for telling quick sort to use Hoare partition scheme.
     */
    public static final String HOARE_PARITION_SCHEME = "hoare";

    /**
     * Swaps objects that are in ith and jth
     * position of an array.
     *
     * @param a Array of objects.
     * @param i First index
     * @param j Second index
     * @param <E> Generic type
     */
    private static <E extends Comparable> void swap(E[] a, int i, int j) {
        E t;

        t    = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * Selection sort for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    public static <E extends Comparable> E[] selection(E[] a) {
        int min;

        for (int i = 0; i < a.length; i++) {

            min = i;

            for (int j = i; j < a.length; j++) {

                if (a[j].compareTo(a[min]) < 0) {

                    min = j;
                }
            }

            if (min != i) {

                swap(a, min, i);
            }
        }

        return a;
    }

    /**
     * Quick sort for Comparable objects.
     * Two options available, using the
     * Lumoto partition scheme, or the
     * Hoare partition scheme.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    public static <E extends Comparable> E[] quick(E[] a, String partitionScheme) {

        if (partitionScheme.equals(LUMOTO_PARTITION_SCHEME)) {

            return quickLumoto(a, 0, a.length-1);

        } else if (partitionScheme.equals(HOARE_PARITION_SCHEME)) {

            return quickHoare(a, 0, a.length-1);

        }

        String message = "Partition scheme: " + partitionScheme + " unrecognized. " +
                         "Please use \"" + LUMOTO_PARTITION_SCHEME +
                         "\" or \"" + HOARE_PARITION_SCHEME+ "\"";

        throw new RuntimeException(message);
    }

    /**
     * Quick sort using Hoare partition
     * scheme for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    private static <E extends Comparable> E[] quickHoare(E[] a, int l, int r) {

        if (l < r) {

            int pivot;

            pivot = Util.rand(l, r);

            int left  = l;
            int right = r;

            while (left <= right) {

                while (a[left].compareTo(a[pivot]) < 0) {

                    left++;
                }

                while (a[right].compareTo(a[pivot]) > 0) {

                    right--;
                }

                if (left <= right) {
                    swap(a, left, right);
                    left++;
                    right--;
                }
            }

            pivot = left;

            quickHoare(a, l, pivot-1);
            quickHoare(a, pivot, r);
        }

        return a;
    }

    /**
     * Quick sort using Lumoto partition
     * scheme for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    private static <E extends Comparable> E[] quickLumoto(E[] a, int l, int r) {

        if (l < r) {

            int pivot = r;
            int i     = l - 1;

            for (int j = l; j <= r-1; j++) {

                if (a[j].compareTo(a[pivot]) < 0) {

                    i++;
                    swap(a, i, j);
                }

            }

            if (a[r].compareTo(a[i+1]) < 0) {

                swap(a, i+1, r);
            }

            pivot = i + 1;

            quickLumoto(a, l, pivot-1);
            quickLumoto(a, pivot+1, r);
        }

        return a;
    }

    /**
     * Merge sort for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    public static <E extends Comparable> E[] merge(E[] a) {

        return merge(a, 0, a.length-1);
    }

    /**
     * Merge sort for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    private static <E extends Comparable> E[] merge(E[] a, int low, int high) {

        if (low < high) {
            int mid = low + (high - low) / 2;
            int i;
            int j;
            int k;
            E[] b;

            merge(a, low, mid);
            merge(a, mid+1, high);

            i = low;
            j = mid+1;
            k = low;

            b = (E[]) new Comparable[a.length * 2];

            while (i <= mid && j <= high) {

                if (a[i].compareTo(a[j]) < 0) {
                    b[k] = a[i];
                    i++;

                } else {
                    b[k] = a[j];
                    j++;
                }

                k++;
            }

            while (i <= mid) {
                b[k] = a[i];
                k++;
                i++;
            }

            while (j <= high) {
                b[k] = a[j];
                k++;
                j++;
            }

            for (int m = low; m < k; m++) {
                a[m] = b[m];
            }
        }

        return a;
    }

}

