package tree;

import interfaces.Entry;
import interfaces.Position;

public class TreapBinaryTree<K extends Comparable<K>, V> extends BalanceableBinaryTree<K,V> {
    public TreapBinaryTree() {
        super();
    }

    @Override
    protected Node<Entry<K,V>> createNode(Entry<K,V> entry, Node<Entry<K,V>> parent, Node<Entry<K,V>> left, Node<Entry<K,V>> right) {
        return new BSTTreapNode<>(entry, parent, left, right, 0);
    }

    public int getPriority(Position<Entry<K,V>> p) {
        BSTTreapNode<Entry<K,V>> node = (BSTTreapNode<Entry<K, V>>) validate(p);
        return node.getPriority();
    }

    protected static class BSTTreapNode<E extends Comparable<E>> extends Node<E> {

        private int priority;

        public BSTTreapNode(E e, Node<E> p, Node<E> l, Node<E> r, int priority) {
            super(e, p, l, r);
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        @Override
        public String toString() {
            return super.toString() + "[" + priority + "]";
        }
    }
}
