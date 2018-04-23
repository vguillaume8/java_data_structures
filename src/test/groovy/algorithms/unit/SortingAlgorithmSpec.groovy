package algorithms.unit

import algorithms.sorting.*
import org.junit.experimental.theories.Theories
import org.junit.runner.RunWith
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Unroll
import util.Arrays
import test.Spec

abstract class SortingAlgorithmSpec extends Spec {

    @Shared SortingAlgorithm<Object> sorter;

    @Unroll
    def "Sort a pre-sorted array (descending order)"() {
        when:
        Integer[] a = Arrays.array(length,
                0,
                10,
                true,
                false)

        sorter = (SortingAlgorithm<Integer>) constructor()

        sorter.sort(a)

        then:
        Arrays.isSorted(a)

        where:

        length | _
        0      | _
        1      | _
        100    | _
        1000   | _
    }

    @Unroll
    def "Sort a pre-sorted array"() {
        when:
        Integer[] a = Arrays.array(length,
                0,
                10,
                true,
                true)

        sorter = (SortingAlgorithm<Integer>) constructor()

        sorter.sort(a)

        then:
        Arrays.isSorted(a)

        where:

        length | _
        0      | _
        1      | _
        100    | _
        1000   | _
    }

    @Unroll
    def "Sort an array of random integers"() {
        when:
        Integer[] a = Arrays.array(length,
                                    Integer.MIN_VALUE,
                                    Integer.MAX_VALUE,
                                    false,
                                    false)

        sorter = (SortingAlgorithm<Integer>) constructor()

        sorter.sort(a)

        then:
        Arrays.isSorted(a)

        where:

        length | _
        0      | _
        1      | _
        100    | _
        1000   | _
    }

    @Unroll
    def "Sort a small array"() {

        when:
        Integer[] a = input as Integer[]

        sorter = (SortingAlgorithm<Integer>) constructor();

        sorter.sort(a)

        then:
        Arrays.isSorted(a)

        where:
        input        | _
        []           | _
        [1]          | _
        [2, 1]       | _
        [3, 2, 1]    | _
        [3, 1, 2, 0] | _

    }

}

class SortingAlgorithmSpec_BubbleSort extends SortingAlgorithmSpec {

    def setup() {
        myClass = BubbleSort
    }
}

@Ignore
class SortingAlgorithmSpec_CountingSort extends SortingAlgorithmSpec {

    def setup() {
        myClass = CountingSort
    }
}

@Ignore
class SortingAlgorithmSpec_HeapSort extends SortingAlgorithmSpec {

    def setup() {
        myClass = HeapSort
    }
}

class SortingAlgorithmSpec_InsertionSort extends SortingAlgorithmSpec {

    def setup() {
        myClass = InsertionSort
    }
}

class SortingAlgorithmSpec_MergeSort extends SortingAlgorithmSpec {

    def setup() {
        myClass = MergeSort
    }
}

class SortingAlgorithmSpec_QuickSort extends SortingAlgorithmSpec {

    def setup() {
        myClass = QuickSort
    }
}

@Ignore
class SortingAlgorithmSpec_RadixSort extends SortingAlgorithmSpec {

    def setup() {
        myClass = RadixSort
    }
}

class SortingAlgorithmSpec_SelectionSort extends SortingAlgorithmSpec {

    def setup() {
        myClass = SelectionSort
    }
}