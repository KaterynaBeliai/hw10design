package part1;

// Adapted from Algorithms, 4th edition, Robert Sedgewick and Kevin Wayne.
// Source: https://algs4.cs.princeton.edu/ (Sedgewick and Wayne).
// Packages and separate helper files added; textbook modular hash retained.
public class SequentialSearchST<Key, Value> {
    private int n;           // number of key-value pairs
    private ChainNode<Key, Value> first;      // the linked list of key-value pairs

    public SequentialSearchST() {
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (ChainNode<Key, Value> x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        for (ChainNode<Key, Value> x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new ChainNode<Key, Value>(key, val, first);
        n++;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        first = delete(first, key);
    }

    // delete key in linked list beginning at ChainNode<Key, Value> x
    // warning: function call stack too large if table is large
    private ChainNode<Key, Value> delete(ChainNode<Key, Value> x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for (ChainNode<Key, Value> x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }

}
