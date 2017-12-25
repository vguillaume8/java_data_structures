import structures.trees.BinarySearchTree;

import structures.vectors.Stack;
import structures.vectors.Queue;
import structures.vectors.LinkedList;
import structures.vectors.ArrayList;
import structures.vectors.interfaces.LinearDataStructure;
import java.util.Arrays;

public final class Application<T> {

  public static void main(String[] args) {
    int size = 10;

    Character[] letters = {'f', 'i', 'h', 'g', 'k', 'j', 'b', 'a', 'd', 'c', 'e'};
    Integer[] numbers = {1, 2, 3, 4, 5, 6, 7};
    Double[] doubles = {1.1, 3.1, 10.1, 0.0001, 19.0, 2.11};

//    BinarySearchTree<Comparable> tree = new BinarySearchTree<>(letters);
//    tree.display();

    Stack<Comparable>      stack      = new Stack<>(numbers);
    Queue<Comparable>      queue      = new Queue<>(numbers);
    LinkedList<Comparable> linkedList = new LinkedList<>(numbers);
    ArrayList<Comparable>  arrayList  = new ArrayList<>(numbers);

    ArrayList<LinearDataStructure<Comparable>> list = new ArrayList<>();

    list.insert(stack);
    list.insert(queue);
    list.insert(linkedList);
    list.insert(arrayList);

    Object[] array;

    for (LinearDataStructure<Comparable> structure : list) {
      array = structure.toArray(new Integer[0]);
      System.out.println(structure.getClass().getSimpleName() + "<" + ">" + ":\n" + structure);
      System.out.println(Arrays.toString(array) + "\n");
    }
  }

}
