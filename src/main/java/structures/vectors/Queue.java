package structures.vectors;

import structures.vectors.auxiliary.ChainedDataStructure;
import java.util.EmptyStackException;

public class Queue<T> extends ChainedDataStructure<T> {

    public Queue() {
        this.init();
    }

    public Queue(T[] values) {
        this.init(values);
    }

    public Queue(int length, T value) {
        this.init(length, value);
    }

    @Override
    public void insert(T value) {
        if (this.empty()) {
            this.head(new ChainedDataStructure.Node<T>(value, null, null));
        } else {
            this.head().insert(value);
        }

        this.incrementSize();
    }

    @Override
    public T remove() {
        if (this.empty()) {
            throw new EmptyStackException();
        }

        T value = this.head().value();  // Get value from head
        this.head(this.head().next());  // Set head equal to head's next
        this.decrementSize();           // Decrement size of Queue

        return value;
    }
}