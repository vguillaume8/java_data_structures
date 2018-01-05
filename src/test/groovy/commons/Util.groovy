package commons

import com.opencsv.CSVWriter;
import java.util.ArrayList;
import java.util.List;

class Util {

    public static String testDirectory = "." + File.separator +
                                         "src" + File.separator +
                                         "test" + File.separator;

    public static String pathToPlotGenerator = testDirectory +
                                               "python" + File.separator;


    public static String dataDirectory = "." + File.separator + "data" + File.separator

    public static String csvDirectory = dataDirectory + "csv" + File.separator
    public static String pngDirectory = dataDirectory + "png" + File.separator

//------------------------------------------------------------------------------

    /**
     *
     * @param bool
     * @return
     */
    @SuppressWarnings("unused")
    static String toPythonBoolean(boolean bool) {
        return bool ? "True" : "False"
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param experimentName
     * @param plotTitle
     * @param inputDataLabel
     * @param xAxisLabel
     * @param yAxisLabel
     */
    @SuppressWarnings("unused")
    static boolean generatePlot(String experimentName,
                        String plotTitle,
                        String inputDataLabel,
                        String xAxisLabel,
                        String yAxisLabel,
                        boolean exponentialFit,
                        boolean cubicFit,
                        boolean quadraticFit,
                        boolean nLogNFit,
                        boolean linearFit,
                        boolean logarithmicFit) {

        String[] c = [
                "python",
                pathToPlotGenerator + "plot_analytics.py",
                "--input-file-name="   + experimentName,
                "--data-directory="    + dataDirectory,
                "--plot-title="        + plotTitle,
                "--input-data-label="  + inputDataLabel,
                "--x-axis-label="      + xAxisLabel,
                "--y-axis-label="      + yAxisLabel,
                "--exponential-fit="   + toPythonBoolean(exponentialFit),
                "--cubic-fit="         + toPythonBoolean(cubicFit),
                "--quadratic-fit="     + toPythonBoolean(quadraticFit),
                "--n-log-n-fit="       + toPythonBoolean(nLogNFit),
                "--linear-fit="        + toPythonBoolean(linearFit),
                "--logarithmic-fit="   + toPythonBoolean(logarithmicFit)
            ];

        StringJoiner sj = new StringJoiner(" ");

        for (String s : c) {
            sj.add(s);
        }

        String command = sj.toString();

        final Process p = Runtime.getRuntime().exec(command);

        // Run python command
        new Thread(new Runnable() {
            void run() {
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;

                try {
                    // Show the output
                    while ((line = input.readLine()) != null) {
                        println(line);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            p.waitFor();
            final int exitValue = p.waitFor();

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

    @SuppressWarnings("unused")
    static CSVWriter getCSVWriter(String filePath) {

        CSVWriter csvWriter;
        FileWriter fileWriter;
        File file

        file = new File(csvDirectory + filePath + ".csv")

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

            // check the keys
            for (int i = 0; i < a.length; i++) {

                // If ith keys in both arrays are unequal
                if (!a[i].equals(b[i])) {
                    return false;
                }
            }

            equals = true;

        }

        return equals;
    }

}
