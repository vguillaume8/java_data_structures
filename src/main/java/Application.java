import structures.vectors.DoublyLinkedList;
import structures.vectors.LinkedList;

public class Application {

  public static void main(String[] args) {

    Integer[] array = {1, 2, 3};
    LinkedList<Integer> list = new DoublyLinkedList<Integer>(array);

    for (Integer i : list) {
      System.out.println(i);
    }

  }

}
