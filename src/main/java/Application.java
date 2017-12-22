import structures.vectors.LinkedList;
import structures.auxiliary.ChainedDataStructure;

public class Application {

  public static void main(String[] args) {

    Integer[] array = {1, 2, 3};
    ChainedDataStructure<Integer> list = new LinkedList<Integer>(array);

    for (Integer i : list) {
      System.out.println(i);
    }

  }

}
