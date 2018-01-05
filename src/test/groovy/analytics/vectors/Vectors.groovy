package analytics.vectors

import commons.Java8Util
import commons.Util
import spock.lang.Specification
import structures.vectors.ArrayList


class Vectors extends Specification {

    def "number of shifts to create an ArrayList of size n"() {
        setup:
        java.util.ArrayList<Number[]> points = new java.util.ArrayList<Number[]>();
        structures.vectors.ArrayList<Integer> arrayList = new structures.vectors.ArrayList<Integer>();
        Integer[] point

        when:

        for (int i = 0; i < length; i++) {

            arrayList.insert(i, 0);

            point    = new Integer[2]
            point[0] = arrayList.size()
            point[1] = arrayList.shifts()

            points.add(point)
        }

        then:
        Java8Util.generateCSV(experimentName, points)
        Util.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "",
                "",
                false,
                false,
                true,
                false,
                true,
                false
        )


        where:

        length | experimentName                                   | plotTitle
        10000  | "number_of_shifts_to_create_arraylist_of_size_n" | "number_of_shifts_to_create_arraylist_of_size_n"

    }

    def "Size of internal array vs. size of input array"() {
        setup:
        structures.vectors.ArrayList<Object> arrayList
        java.util.ArrayList<Number[]> points;
        Number[] point

        when:
        points = new java.util.ArrayList<Number[]>()

        // Insert n elements into the ArrayList
        arrayList = new structures.vectors.ArrayList<Integer>()

        for (int i = 1; i <= length; i++) {
            arrayList.insert(i);

            point = new Integer[2]
            point[0] = arrayList.size()
            point[1] = arrayList.internalSize()

            points.add(point)
        }

        then:
        Java8Util.generateCSV(experimentName, points)
        Util.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "A(n)",
                false,
                false,
                false,
                false,
                true,
                false
        )


        where:
        length    | experimentName                           | plotTitle
        10000     | "internal_array_size_vs_actual_elements" | "internal_array_size_vs_actual_elements"
    }

    def "How many memory allocations when building an ArrayList"() {
        setup:
        structures.vectors.ArrayList<Object> arrayList
        java.util.ArrayList<Number[]> points;
        Number[] point

        when:
        points = new java.util.ArrayList<Number[]>()

        // Insert n elements into the ArrayList
        for (int i = 1; i <= length; i++) {
            arrayList = new structures.vectors.ArrayList<Integer>()

            for (int j = 1; j <= i; j++) {
                arrayList.insert(j);
            }

            point = new Integer[2]
            point[0] = arrayList.size()
            point[1] = arrayList.allocations()

            points.add(point)
        }

        then:
        Java8Util.generateCSV(experimentName, points)
        Util.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "A(n)",
                false,
                false,
                false,
                false,
                true,
                true
        )


        where:
        length    | experimentName                       | plotTitle
        1000      | "memory_allocations_for_large_lists" | "memory_allocations_to_build_array_list_of_size_n"
        500       | "memory_allocations_for_small_list"  | "memory_allocations_to_build_array_list_of_size_n"
    }

}


