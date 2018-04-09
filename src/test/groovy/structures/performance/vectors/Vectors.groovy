package structures.performance.vectors

import util.Plot
import util.Util
import spock.lang.Shared
import spock.lang.Specification

class Vectors extends Specification {

    @Shared int numberOfKeys = 7500
    @Shared int interval     = 25

    def "avg_cost_to_remove"() {
        setup:
        java.util.ArrayList<Number[]> points = new java.util.ArrayList<Number[]>();
        structures.vectors.ArrayList<Integer> arrayList = new structures.vectors.ArrayList<Integer>();
        Integer[] point
        int s1
        int s2

        when:

        // Build array list of size n
        for (int i = 0; i < length; i+=interval) {
            arrayList.insert(1);
        }


        while (arrayList.size() > 0) {
            point    = new Integer[2]

            s1 = arrayList.shifts()

            point[0] = arrayList.size()

            arrayList.remove(Plot.rand(0, arrayList.size()-1));

            s2 = arrayList.shifts()

            point[1] = Math.abs(s2-s1);

            points.add(point)
        }

        then:
        Plot.generateCSV(experimentName, points)
        Plot.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "S(n)",
                "S(n)",
                "n",
                false,
                false,
                false,
                false,
                true,
                false
        )


        where:

        length        | experimentName      | plotTitle
        numberOfKeys  | "avg_cost_to_remove_from_arraylist" | "avg_cost_to_remove_from_arraylist"

    }

    def "avg_cost_to_insert"() {
        setup:
        java.util.ArrayList<Number[]> points = new java.util.ArrayList<Number[]>();
        structures.vectors.ArrayList<Integer> arrayList = new structures.vectors.ArrayList<Integer>();
        Integer[] point
        int s1
        int s2

        when:
        arrayList.insert(0);


        for (int i = 0; i < length; i+=interval) {

            s1 = arrayList.shifts()

            arrayList.insert(i, Plot.rand(0, arrayList.size()-1));

            s2 = arrayList.shifts()

            point    = new Integer[2]
            point[0] = arrayList.size()
            point[1] = Math.abs(s2-s1);

            points.add(point)
        }

        then:
        Plot.generateCSV(experimentName, points)
        Plot.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "S(n)",
                "S(n)",
                "n",
                false,
                false,
                false,
                false,
                true,
                false
        )


        where:

        length        | experimentName      | plotTitle
        numberOfKeys  | "avg_cost_to_insert_into_arraylist" | "avg_cost_to_insert_into_arraylist"

    }

    def "number of shifts to create an ArrayList of size n when prepending to the ArrayList"() {
        setup:
        java.util.ArrayList<Number[]> points = new java.util.ArrayList<Number[]>();
        structures.vectors.ArrayList<Integer> arrayList = new structures.vectors.ArrayList<Integer>();
        Integer[] point

        when:
        arrayList.insert(0);


        for (int i = 0; i < length; i+=interval) {

            arrayList.insert(i, 0);

            point    = new Integer[2]
            point[0] = arrayList.size()
            point[1] = arrayList.shifts()

            points.add(point)
        }

        then:
        Plot.generateCSV(experimentName, points)
        Plot.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "S(n)",
                "S(n)",
                "n",
                false,
                false,
                true,
                false,
                true,
                false
        )


        where:

        length        | experimentName                                                  | plotTitle
        numberOfKeys  | "shifts_required_when_inserting_first_index" | "shifts_required_when_inserting_first_index"

    }

    def "number of shifts to create an ArrayList of size n when inserting at the back of the ArrayList"() {
        setup:
        java.util.ArrayList<Number[]> points = new java.util.ArrayList<Number[]>();
        structures.vectors.ArrayList<Integer> arrayList = new structures.vectors.ArrayList<Integer>();
        Integer[] point

        when:
        arrayList.insert(0);


        for (int i = 0; i < length; i+=interval) {

            arrayList.insert(i, arrayList.size()-1);

            point    = new Integer[2]
            point[0] = arrayList.size()
            point[1] = arrayList.shifts()

            points.add(point)
        }

        then:
        Plot.generateCSV(experimentName, points)
        Plot.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "S(n)",
                "S(n)",
                "n",
                false,
                false,
                true,
                false,
                true,
                false
        )


        where:

        length        | experimentName           | plotTitle
        numberOfKeys  | "shifts_required_when_inserting_last_index" | "shifts_required_when_inserting_last_index"

    }

    def "number of shifts to create an ArrayList of size n when inserting at random indicies"() {
        setup:
        java.util.ArrayList<Number[]> points = new java.util.ArrayList<Number[]>();
        structures.vectors.ArrayList<Integer> arrayList = new structures.vectors.ArrayList<Integer>();
        Integer[] point

        when:
        arrayList.insert(0);


        for (int i = 0; i < length; i+=interval) {

            arrayList.insert(i, Plot.rand(0, arrayList.size()-1));

            point    = new Integer[2]
            point[0] = arrayList.size()
            point[1] = arrayList.shifts()

            points.add(point)
        }

        then:
        Plot.generateCSV(experimentName, points)
        Plot.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "S(n)",
                "S(n)",
                "n",
                false,
                false,
                true,
                false,
                true,
                false
        )


        where:

        length        | experimentName                                                                     | plotTitle
        numberOfKeys  | "shifts_required_when_inserting_rand_index" | "shifts_required_when_inserting_rand_index"

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

        for (int i = 1; i <= length; i+=interval) {
            arrayList.insert(i);

            point = new Integer[2]
            point[0] = arrayList.size()
            point[1] = arrayList.internalSize()

            points.add(point)
        }

        then:
        Plot.generateCSV(experimentName, points)
        Plot.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "L(n)",
                "L(n)",
                "n",
                false,
                false,
                false,
                false,
                true,
                false
        )


        where:
        length       | experimentName                           | plotTitle
        numberOfKeys | "internal_array_size_vs_actual_elements" | "internal_array_size_vs_actual_elements"
    }

    def "How many memory allocations when building an ArrayList"() {
        setup:
        structures.vectors.ArrayList<Object> arrayList
        java.util.ArrayList<Number[]> points;
        Number[] point

        when:
        points = new java.util.ArrayList<Number[]>()

        // Insert n elements into the ArrayList
        for (int i = 1; i <= length; i+=interval) {
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
        Plot.generateCSV(experimentName, points)
        Plot.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "k",
                "k",
                "n",
                false,
                false,
                false,
                false,
                true,
                true
        )

        where:
        length       | experimentName                       | plotTitle
        numberOfKeys | "memory_allocations_for_large_lists" | "memory_allocations_to_build_array_list_of_size_n"
    }

}


