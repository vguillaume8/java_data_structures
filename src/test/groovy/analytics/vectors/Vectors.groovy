package analytics.vectors

import commons.Java8Util
import commons.Util
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import com.opencsv.CSVWriter;
import java.util.ArrayList;
import spock.lang.Ignore

class Vectors extends Specification {

    def "How many memory allocations when building an ArrayList"() {
        setup:
        structures.vectors.ArrayList<Object> arrayList
        java.util.ArrayList<Number[]> points;
        Number[] point

        when:
        points = new ArrayList<>()

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


