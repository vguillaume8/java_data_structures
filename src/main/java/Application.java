import vectors.Stack;

public class Application {

  public static void main(String[] args) {

    Integer[] array = {1, 2, 3};
    Stack<Integer> stack = new Stack<Integer>(array);

    System.out.println(stack.size());

    stack.pop();
    stack.pop();

    System.out.println(stack.top());

  }

}
