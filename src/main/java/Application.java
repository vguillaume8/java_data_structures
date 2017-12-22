import structures.vectors.ArrayList;
import structures.vectors.LinkedList;
import structures.vectors.Queue;
import structures.vectors.Stack;
import structures.vectors.auxiliary.LinearDataStructure;

public class Application<T> {

  public static void main(String[] args) {
    int size = 10;
    Object[] array = initArray(size);

    Stack<Object>      stack      = new Stack<>(array);
    Queue<Object>      queue      = new Queue<>(array);
    LinkedList<Object> linkedList = new LinkedList<>(array);
    ArrayList<Object>  arrayList  = new ArrayList<>(array);


    display(stack);
    display(queue);
    display(linkedList);
    display(arrayList);
  }

  public static void display(LinearDataStructure<Object> structure) {
    System.out.println(structure.getClass().getSimpleName() + ": " + structure.size());
    System.out.println(structure.toString() + "\n");
  }

  public static Object[] initArray(int size) {
    Object[] array = new Object[size];

    for (int i = 0; i < size; i++) {
      array[i] = i+1;
    }

    return array;
  }

}
