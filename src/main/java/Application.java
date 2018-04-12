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

    List<Integer> a = new ArrayList<>();
    List<Integer> b = new LinkedList<>();

    for (int i = 0; i < 10; i++) {
      a.insert(i, 0);
      b.insert(i, 0);
    }

    System.out.println(a.equals(b));

  }

}
