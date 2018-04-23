import algorithms.commons.Output;
import algorithms.sorting.*;
import structures.vectors.*;
import dev.*;
import util.Arrays;
import util.Util;

/**
 * Main class that contains
 * main method. It is used for
 * development. In the final
 * executable jar, main will have
 * an empty body and simply exit
 * with an exit code of 0. The jar
 * is meant to be used as a library
 * rather than a runnable program
 * with a given sequence of events.
 *
 * @author Jabari Dash
 */
public final class Application extends Main {

  public static void main(String[] args) throws Exception {

  // TODO - https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html

    int len = 10000;
    int min = 0;
    int max = 5;

    Integer[] ascending  = Arrays.array(len, min, max, true, true);
    Integer[] descending = Arrays.array(len, min, max, true, false);
    Integer[] random     = Arrays.array(len, min, max, false, false);


    SortingAlgorithm algorithm = new MergeSort();
    Output output;

    println(random);

    output = algorithm.sort(random);

    println(random);

    println(output);

    println(Util.x);
  }

}
