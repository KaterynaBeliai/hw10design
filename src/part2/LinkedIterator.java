package part2;

import java.util.Iterator;
import java.util.NoSuchElementException;
// Textbook queue iterator, moved into a separate file.
final class LinkedIterator<Item> implements Iterator<Item> {
    private QueueNode<Item> current;
    LinkedIterator(QueueNode<Item> first) { current = first; }
    public boolean hasNext() { return current != null; }
    public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        Item item = current.item;
        current = current.next;
        return item;
    }
}
