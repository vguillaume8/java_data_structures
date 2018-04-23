package algorithms.sorting;

/**
 * Implementation of mergeSort sort.
 *
 * @author Jabari Dash
 * @param <E> Generic type.
 */
public final class MergeSort<E extends Comparable <E>> extends SortingAlgorithm<E> {

    /**
     * Constructs InsertionSort object.
     */
    public MergeSort() {
        super();

        functions.put(DEFAULT, MergeSort::mergeSort);
    }

    /**
     * Merge sort for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    public static <E extends Comparable> int mergeSort(E[] a) {

        return mergeSort(a, 0, a.length-1, 0);
    }

    /**
     * Merge sort for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    private static <E extends Comparable> int mergeSort(E[] a, int low, int high, int c) {

        if (low < high) {
            int mid = low + (high - low) / 2;
            int i;
            int j;
            int k;
            E[] b;

            c += mergeSort(a, low, mid, 0);
            c += mergeSort(a, mid+1, high, 0);

            i = low;
            j = mid+1;
            k = low;

            b = (E[]) new Comparable[a.length * 2];

            while (i <= mid && j <= high) {

                c++;

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

        return c;
    }
}
