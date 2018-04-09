package structures.functional.vectors

import structures.vectors.Queue
import spock.lang.Specification
import spock.lang.Unroll

class QueueSpec extends Specification {

    /**
     * https://www.geeksforgeeks.org/iterative-method-to-find-height-of-binary-tree/
     * @return
     */
    @Unroll
    def "Height of Binary Search Tree iteratively"() {
        setup:
        BinarySearchTree<Integer> tree

        when:
        tree = new BinarySearchTree<Integer>(input)

        then:
        tree.size()   == input.size()
        tree.height() == height

        where:
        height || input
        3      || [4, 2, 1, 3, 7, 8]
        5      || [1, 2, 3, 4, 5]
        3      || [3, 2, 7, 1, 4, 9]
    }

    /**
     * https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
     * @return
     */
    @Unroll
    def "Breadth First Search on a graph"() {
        setup:
        Graph  graph
        String output

        when:
        graph = new Graph(input)
        output = graph.breadthFirstSearch(start)

        then:
        output == BFS

        where:
        start || input                                               || BFS
        2     || [ [0:1],[0:2],[1:2],[2:0],[2:3],[3:3] ]             ||"2 0 3 1"
        1     || [ [1:2],[1:3],[2:4],[2:5],[3:5],[4:5],[4:6],[5:6] ] || "1 2 3 4 5 6"
    }

    /**
     * https://www.geeksforgeeks.org/reverse-individual-words/
     * @return
     */
    @Unroll
    def "Stack using queues to reverse individual words in a string"() {
        setup:
        StringBuilder stringBuilder
        Stack<String> stack
        String        stdout
        String        c
        int           length

        when:
        stringBuilder = new StringBuilder()
        stack         = new Stack<String>()
        length        = input.length()

        for (int i = 0; i < length; i++) {
            c = input.charAt(i).toString()

            if (c != " ") {
                stack.push(c)

            } else {

                while (!stack.empty()) {
                    stringBuilder.append(stack.pop())
                }

                stringBuilder.append(" ")
            }
        }

        while (!stack.empty()) {
            stringBuilder.append(stack.pop())
        }

        stdout = stringBuilder.toString()

        then:
        stdout == output

        where:
        input                     || output
        "Hello there"             || "olleH ereht"
        "Hello my name is Jabari" || "olleH ym eman si irabaJ"
        "Hello"                   || "olleH"
        ""                        || ""
        "H"                       || "H"
    }

    private class BinarySearchTree<T extends Comparable> {
        private Node<T> root
        private int     size

        int height() {
            Queue<Node<T>> queue
            Node<T>        node
            int            height
            int            count

            if (root == null) {
                return 0
            }

            queue  = new Queue<Node<T>>()
            queue.insert(root)

            height = 0

            while (true) {
                count = queue.size()

                if (count == 0) {
                    return height
                }

                height++

                while (count > 0) {
                    node = queue.remove()

                    if (node.left != null) {
                        queue.insert(node.left)
                    }

                    if (node.right != null) {
                        queue.insert(node.right)
                    }

                    count--
                }
            }
        }

        BinarySearchTree(List<T> values) {
            for (T value : values) {
                insert(value)
            }
        }

        void insert(T value) {
            if (root == null) {
                root = new Node<T>(value)
            } else {
                root.insert(value)
            }

            size++
        }

        int size() {
            return this.size
        }

        private class Node<T extends Comparable> {
            T       value
            Node<T> left
            Node<T> right

            Node(T value) {
                this.value = value
                this.left  = null
                this.right = null
            }

            void insert(T v) {

                if (v < value) {
                    if (left == null) {
                        left = new Node<T>(v)

                    } else {
                        left.insert(v)
                    }
                } else if (v > value) {
                    if (right == null) {
                        right = new Node<T>(v)
                    } else {
                        right.insert(v)
                    }
                }
            }
        }
    }

    private class Graph {
        private int             numberOfVerticies
        private List<Integer>[] adjacencyMatrix

        Graph(List<Map<Integer, Integer>> input) {

            this.numberOfVerticies = input.size()
            this.adjacencyMatrix   = new ArrayList<Integer>[this.numberOfVerticies]

            for (Map<Integer, Integer> map: input) {

                for (Map.Entry<Integer, Integer> entry : map) {
                    this.addEdge(entry.key, entry.value)
                }
            }
        }

        Graph addEdge(int v, int w) {

            if (adjacencyMatrix[v] == null) {
                adjacencyMatrix[v] = new ArrayList<Integer>()
            }

            adjacencyMatrix[v].add(w)

            return this
        }

        String breadthFirstSearch(int s) {
            StringBuilder  output
            boolean[]      visited
            Queue<Integer> queue

            output  = new StringBuilder()
            queue   = new Queue<Integer>()
            visited = new boolean[this.numberOfVerticies]

            for (int i = 0; i < visited.length; i++) {
                visited[i] = false
            }

            visited[s] = true
            queue.insert(s)

            while (!queue.empty()) {
                s = queue.remove()
                output.append(s + " ")

                for (Integer i : adjacencyMatrix[s]) {
                    if (!visited[i]) {
                        visited[i] = true
                        queue.insert(i)
                    }
                }
            }

            return output.toString().substring(0, output.length()-1)
        }
    }

    /**
     * https://www.geeksforgeeks.org/implement-stack-using-queue/
     *
     * @param <K> Generic type
     */
    private final class Stack <K> {
        private Queue<K> inQueue
        private Queue<K> tempQueue

        Stack() {
            inQueue   = new Queue<K>()
            tempQueue = new Queue<K>()
        }

        boolean empty() {
            return inQueue.empty()
        }

        void push(K element) {
            inQueue.insert(element)
        }

        K pop() {
            K value

            if (empty()) {
                throw new Exception("Cannot pop() empty stack")
            }

            while (inQueue.size() != 1) {
                tempQueue.insert(inQueue.remove())
            }

            value = inQueue.remove()

            while (!tempQueue.empty()) {
                inQueue.insert(tempQueue.remove())
            }

            return value
        }
    }

}
