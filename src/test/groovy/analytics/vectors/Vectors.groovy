package analytics.vectors

import commons.Util
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import com.opencsv.CSVWriter;
import java.util.ArrayList;

class Vectors extends Specification {


    @Shared String csvDirectory = Util.testDirectory + "analytics/vectors/csv/"

    @Unroll
    def "How many memory allocations when building an ArrayList"() {
        setup:
        CSVWriter csvWriter
        structures.vectors.ArrayList<Object> arrayList
        java.util.ArrayList<String[]> points;
        String[] point

        when:
        csvWriter = Util.getCSVWriter(csvDirectory + "AllocationsToBuildArrayList.csv");
        points = new ArrayList<>()

        String[] header = ["Array size", "Number of memory allocations"]

//        csvWriter.writeNext(header)

        // Insert n elements into the ArrayList
        for (int i = 0; i < length; i++) {
            arrayList = new structures.vectors.ArrayList<Integer>()

            for (int j = 0; j < i; j++) {
                arrayList.insert(j);
            }

            point = new String[2]
            point[0] = Integer.toString(arrayList.size());
            point[1] = Integer.toString(arrayList.allocations())

            points.add(point)
        }

        csvWriter.writeAll(points)

        csvWriter.close()

        then:
        true

        where:
        length | _
        100000 | _
    }

}


