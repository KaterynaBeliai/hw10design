package part2;

// Adapted from Algorithms, 4th edition, Robert Sedgewick and Kevin Wayne.
// Source: https://algs4.cs.princeton.edu/ (Sedgewick and Wayne).
// Packages and separate helper files added; textbook modular hash retained.
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private QueueNode<Item> first;    // beginning of queue
    private QueueNode<Item> last;     // end of queue
    private int n;               // number of elements on queue

    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    public void enqueue(Item item) {
        QueueNode<Item> oldlast = last;
        last = new QueueNode<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    public Iterator<Item> iterator()  {
        return new LinkedIterator<Item>(first);
    }

}
