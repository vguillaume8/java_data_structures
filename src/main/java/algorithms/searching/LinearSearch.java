package algorithms.searching;

public final class LinearSearch {


    /**
     *
     * @param a
     * @param element
     * @param <E>
     * @return
     */
    public static <E> int linearSearch(E[] a, E element) {

        int index = -1;

        for (int i = 0; i < a.length; i++) {

            if (a[i].equals(element)) {

                index = i;
                break;
            }
        }

        return index;
    }

}
