package commons;

import com.opencsv.CSVWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Reusable methods for use in test modules. We need
 * a separate file for certain methods because they
 * use the Java 8 standard, and the version of Groovy
 * that we are using for testing does not support this
 * standard.
 *
 * TODO - Perhaps force gradle to use a version of Groovy that supports Java 8+
 *
 * @author Jabari Dash
 */
public class Java8Util {

    /**
     * Generates a random integer between two integers (inclusive).
     *
     * @param min Minimum integer in range.
     * @param max Maximum integer in range.
     * @return Random integer in range.
     */
    public static int rand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Generates a CSV file provided a data set.
     *
     * @param experimentName Name of output file (without extension)
     * @param values Values to write to CSV
     * @return True if and only if writing the file was successful.
     */
    public static boolean generateCSV(String experimentName, List<Number[]> values) {
        CSVWriter csvWriter;
        String[] stringArray;
        boolean exitStatus;

        // Default to true
        exitStatus = true;

        // Get a CSVWriter for the provided experiment file
        csvWriter = Util.getCSVWriter(experimentName);

        // Write lines to CSV
        for (Number[] pair : values) {

            // TODO - Find explanation to this code, forgot why it works
            stringArray = Arrays.stream(pair)
                                .map(Number::toString)
                                .toArray(String[]::new);

            // Write the line
            csvWriter.writeNext(stringArray);
        }

        try {

            csvWriter.close();

        // If anything went wrong, consider
        // the write unsuccessful
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Writing CSV was unsuccessful");

            exitStatus = false;
        }


        return exitStatus;
    }
}
