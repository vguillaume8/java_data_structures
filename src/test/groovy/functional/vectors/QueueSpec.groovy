package functional.vectors

import structures.vectors.Queue
import spock.lang.Specification
import spock.lang.Unroll

class QueueSpec extends Specification {

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
}
