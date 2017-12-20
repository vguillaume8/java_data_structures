import vectors.DoublyLinkedList;

public class Application {

  public static void main(String[] args) {
    DoublyLinkedList<Object> list;
    Integer[] array = {0, 1, 2, 4, 5};

    list = new DoublyLinkedList<Object>(array);

    while (list.length() > 0) {
      list.remove();
    }

  display(list);

  System.out.println(list.empty());

  }

  public static void display(DoublyLinkedList<Object> list) {
    System.out.format("size: %d\nlist: %s\n", list.length(), list.toString());
  }
}
