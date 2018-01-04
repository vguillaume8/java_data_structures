package commons;

import com.opencsv.CSVWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Java8Util {

    public static boolean generateCSV(String experimentName, List<Number[]> values) {
        CSVWriter csvWriter;
        String[] stringArray;
        boolean exitStatus;


        csvWriter = Util.getCSVWriter(experimentName);

        // Write lines to CSV
        for (Number[] pair : values) {

            stringArray = Arrays.stream(pair)
                    .map(Number::toString)
                    .toArray(String[]::new);

            csvWriter.writeNext(stringArray);
        }

        try {

            csvWriter.close();

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Writing CSV was unsuccessful");

            exitStatus = false;
        }

        exitStatus = true;


        return exitStatus;
    }
}
