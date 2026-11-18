package part1;

// Textbook nested Node moved into its own file as required by the assignment.
final class ChainNode<Key, Value> {
    Key key;
    Value val;
    ChainNode<Key, Value> next;
    ChainNode(Key key, Value val, ChainNode<Key, Value> next) {
        this.key = key;
        this.val = val;
        this.next = next;
    }
}
