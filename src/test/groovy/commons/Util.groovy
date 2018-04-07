package commons

import com.opencsv.CSVWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Reusable methods throughout test modules.
 *
 * @author Jabari Dash
 */
class Util {

    /**
     * Directory in which all tests are held
     */
    public static String testDirectory = "."    + File.separator +
                                         "src"  + File.separator +
                                         "test" + File.separator;

    /**
     * Directory in which the python plot generator scrips are held
     */
    public static String pathToPlotGenerator = testDirectory +
                                               "python" + File.separator;

    /**
     * Directory in which all input and output data files are held
     */
    public static String dataDirectory = "."    + File.separator +
                                         "data" + File.separator

    /**
     * Directory in which all CSV files are held
     */
    public static String csvDirectory = dataDirectory + "csv" + File.separator

    /**
     * Directory in which all PNG files are held
     */
    public static String pngDirectory = dataDirectory + "png" + File.separator

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
    static String toPythonBoolean(boolean bool) {
        return bool ? "True" : "False"
    }

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
                        boolean logarithmicFit) {

        // Python command with
        // arguments separated as an array
        String[] c = [
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
            ];

        StringJoiner sj = new StringJoiner(" ");

        // Concatenate each argument
        // using the string joined
        for (String s : c) {
            sj.add(s);
        }

        // Convert the command to a string
        String command = sj.toString();

        // Create a new process with the command
        final Process p = Runtime.getRuntime().exec(command);

        // Run python command
        new Thread(new Runnable() {
            void run() {
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;

                try {
                    // Pipe output of process to stdout
                    while ((line = input.readLine()) != null) {
                        println(line);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {

            // Wait for process to end
            // TODO - Why call twice?go
            p.waitFor();

            // Get the exit value
            final int exitValue = p.waitFor();

            // non-zero exit status indicates success
            if (exitValue != 0) {

                System.out.println("Failed to execute the following command: " + command + " due to the following error(s):");

                // Show error message
                try {
                    InputStream stream = p.getErrorStream()

                    final BufferedReader b = new BufferedReader(new InputStreamReader(stream));


                    String line;

                    if ((line = b.readLine()) != null) {
                        println(line);
                    }

                } catch (final IOException e) {
                    e.printStackTrace();
                }

                throw new Exception("Plot creation was unsuccessful")
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true
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
    static CSVWriter getCSVWriter(String filePath) {

        CSVWriter csvWriter;
        FileWriter fileWriter;
        File file

        file = new File(csvDirectory + filePath + ".csv")

        // NOTE - Do we want to check if the file
        // exists already? Or, should we just continue
        // to overwrite?
        file.createNewFile()

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
     * Determines whether or not a {@code Comparable[]}  is sorted
     * in ascending order.
     *
     * @param array Array to verify.
     * @return True if and only if the above condition is met.
     */
    static boolean isSorted(Comparable[] array) {

        for (int i = 0; i < array.length-1; i++) {

            // If ith value is greater than value at i+1,
            // then the array is not in strictly ascending order
            if (array[i].compareTo(array[i+1]) > 0) {
                return false;
            }
        }

        // If we got to the end,
        // the array is sorted
        return true;
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether two arrays are equal to each other. For them to
     * be equal, their length's must be equal, they must be of the same
     * class type, and each value in the ith position of each array must
     * be equal.
     *
     * @param a First array to compare
     * @param b Second array to compare
     * @return True if and only if the above conditions are met
     */
    @SuppressWarnings("unchecked")
    static boolean equals(Object[] a, Object[] b) {
        boolean equals = false;

        if (a.length == b.length) {

            // check the elements
            for (int i = 0; i < a.length; i++) {

                // If ith elements in both arrays are unequal
                if (!a[i].equals(b[i])) {
                    return false;
                }
            }

            equals = true;

        }

        return equals;
    }

}
