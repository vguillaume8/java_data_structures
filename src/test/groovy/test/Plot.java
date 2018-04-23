package test;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Reusable methods for use in test modules. We need
 * a separate file for certain methods because they
 * use the Java 8 standard, and the version of Groovy
 * that we are using for testing does not support this
 * standard.
 *
 * @author Jabari Dash
 */
public final class Plot {

    /**
     * Directory in which all tests are held
     */
    private static String testDirectory = "." +
            File.separator                    +
            "src"  + File.separator           +
            "test" + File.separator;

    /**
     * Directory in which the python plot generator scripts are held
     */
    private static String pathToPlotGenerator = testDirectory +
            "python"      +
            File.separator;

    /**
     * Directory in which all input and output data files are held
     */
    private static String dataDirectory = "." +
            File.separator                    +
            "data"                            +
            File.separator;

    /**
     * Directory in which all Plot files are held
     */
    private static String csvDirectory = dataDirectory + "csv" + File.separator;

    /**
     * Directory in which all PNG files are held
     */
    private static String pngDirectory = dataDirectory + "png" + File.separator;

    //------------------------------------------------------------------------------

    /**
     * Provided  an experiment name that points to
     * csv date, we generate a plot via
     * an external python script.
     *
     * @param experimentName      Name of the experiment and input csv file name
     * @param plotTitle           The title of the plot that will be on the image
     * @param inputDataLabel      Label that appears on the legend of the plot
     * @param xAxisLabel          X-axis label
     * @param yAxisLabel          Y-axis label
     * @param dependentVariable   The name (units) of the dependent variable (y-axis)
     * @param independentVariable The name (units) of the independent variable (x-axis)
     * @param exponentialFit      Boolean flag to decide whether or not to perform exponential fit
     * @param cubicFit            Boolean flag to decide whether or not to perform cubic fit
     * @param quadraticFit        Boolean flag to decide whether or not to perform quadratic fit
     * @param nLogNFit            Boolean flag to decide whether or not to perform n*log(n) fit
     * @param linearFit           Boolean flag to decide whether or not to perform linear fit
     * @param logarithmicFit      Boolean flag to decide whether or not to perform logarithmic fit
     */
    @SuppressWarnings("unused")
    static boolean generatePlot(String experimentName,
                                String plotTitle,
                                String inputDataLabel,
                                String xAxisLabel,
                                String yAxisLabel,
                                String dependentVariable,
                                String independentVariable,
                                boolean exponentialFit,
                                boolean cubicFit,
                                boolean quadraticFit,
                                boolean nLogNFit,
                                boolean linearFit,
                                boolean logarithmicFit) throws Exception {

        // Python command with
        // arguments separated as an array
        String[] c = {
                "python",
                pathToPlotGenerator       + "plot_analytics.py",
                "--input-file-name="      + experimentName,
                "--data-directory="       + dataDirectory,
                "--plot-title="           + plotTitle,
                "--input-data-label="     + inputDataLabel,
                "--x-axis-label="         + xAxisLabel,
                "--y-axis-label="         + yAxisLabel,
                "--dependent-variable="   + dependentVariable,
                "--independent-variable=" + independentVariable,
                "--exponential-fit="      + toPythonBoolean(exponentialFit),
                "--cubic-fit="            + toPythonBoolean(cubicFit),
                "--quadratic-fit="        + toPythonBoolean(quadraticFit),
                "--n-log-n-fit="          + toPythonBoolean(nLogNFit),
                "--linear-fit="           + toPythonBoolean(linearFit),
                "--logarithmic-fit="      + toPythonBoolean(logarithmicFit)
        };

        // TODO -
        // In the event that we want to get output from
        // python script, stdout will go to a separate file.
        // currently it is not being used. However, at some
        // point I may want the script to print out a best fit
        // string. This will go to the stdout file.

        // File to redirect stdout
        File stdout = new File(
                dataDirectory +
                        "logs"         +
                        File.separator +
                        experimentName +
                        ".stdout.log"
        );

        // File to redirect stderr
        File stderr = new File(
                dataDirectory +
                        "logs"         +
                        File.separator +
                        experimentName +
                        ".stderr.log"
        );

        // Build process
        // Start it
        // Wait for it to finish
        // Get it's exit code
        int exitCode = new ProcessBuilder(c)
                          .redirectOutput(stdout)
                          .redirectError(stderr)
                          .start()
                          .waitFor();

        // If exit code is not 0
        // print the output
        if (exitCode != 0) {

            Scanner scanner = new Scanner(stderr);

            // Print log to console
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            scanner.close();

            return false;
        }

        return true;
    }

    //------------------------------------------------------------------------------

    /**
     * Converts Java / Groovy boolean to
     * their respective string values with
     * the first letter capitalized. This makes
     * them compatible with Python when we pass
     * booleans to python via the command line.
     *
     * @param bool A boolean value
     * @return String representation of the boolean
     */
    @SuppressWarnings("unused")
    static private String toPythonBoolean(boolean bool) {
        return bool ? "True" : "False";
    }


    /**
     * Generates a random integer between two integers (inclusive).
     *
     * @param min Minimum integer in range.
     * @param max Maximum integer in range.
     * @return Random integer in range.
     */
    public static int rand(int min, int max) {
        return ThreadLocalRandom.current()
                                .nextInt(min, max + 1);
    }

    //------------------------------------------------------------------------------

    /**
     * Returns a CSVWriter object provided the path
     * to a file to write to
     *
     * @param filePath Path to output file
     * @return CSVWriter object
     */
    @SuppressWarnings("unused")
    static CSVWriter getCSVWriter(String filePath) throws IOException {

        CSVWriter csvWriter;
        FileWriter fileWriter;
        File file;

        file = new File(csvDirectory + filePath + ".csv");

        // NOTE - Do we want to check if the file
        // exists already? Or, should we just continue
        // to overwrite?
        file.createNewFile();

        fileWriter = new FileWriter(file);

        csvWriter = new CSVWriter(
                fileWriter,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END
        );

        return csvWriter;
    }

    /**
     * Generates a Plot file provided a data set.
     *
     * @param experimentName Name of output file (without extension)
     * @param values Values to write to Plot
     * @return True if and only if writing the file was successful.
     */
    public static boolean generateCSV(String experimentName, List<Number[]> values) throws IOException {
        CSVWriter csvWriter;
        String[] stringArray;
        boolean exitStatus;

        // Default to true
        exitStatus = true;

        // Get a CSVWriter for the provided experiment file
        csvWriter = getCSVWriter(experimentName);

        // Write lines to Plot
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
            System.out.println("Writing Plot was unsuccessful");

            exitStatus = false;
        }

        return exitStatus;
    }
}
