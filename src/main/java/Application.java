import structures.vectors.*;
import structures.trees.*;
import structures.graphs.*;
import structures.sets.*;
import structures.maps.*;

/**
 *
 */
public final class Application {

  public static void main(String[] args) throws Exception {

  // TODO - https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html

    Integer[] a = {4, 3, 2, 1};
    Integer[] b = {1, 2, 3, 4};

    ArrayList<Integer> one = new ArrayList<>(a);
    ArrayList<Integer> two = new ArrayList<>(b);

    System.out.println(one.equals(two));
  }

}
