import structures.commons.DynamicArray;
import structures.vectors.*;
import structures.trees.*;
import structures.graphs.*;
import structures.sets.*;
import structures.maps.*;

import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 */
public final class Application {

  public static void main(String[] args) throws Exception {

  // TODO - https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html

    Integer[] ordered = IntStream.range(0, 10)
                                 .boxed()
                                 .toArray(Integer[]::new);

    // Integer array of 10 random
    // numbers between 1 and 9 inclusive
    Integer[] random = new Random()
                          .ints(1, 10)
                          .limit(10)
                          .boxed()
                          .toArray(Integer[]::new);

    Stack<Integer> s1 = new ArrayStack<>(random);
    Stack<Integer> s2 = new ArrayStack<>(random);
    Stack<Integer> s3 = new LinkedStack<>(random);

    System.out.println(s1.equals(s2));


  }

}
