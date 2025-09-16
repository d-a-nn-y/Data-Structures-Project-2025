package list;

import interfaces.List;
import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {
    //---------------- nested Node class ----------------
    private static class Node<E> {
        private E element; // reference to the element stored at this node
        private Node<E> prev; // reference to the previous node in the list
        private Node<E> next; // reference to the subsequent node in the list
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }
        public E getElement() { return element; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> p) { prev = p; }
        public void setNext(Node<E> n) { next = n; }
    }

    private Node<E> head; // header sentinel
    private Node<E> tail; // trailer sentinel
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<>(null, null, null); // create header
        tail = new Node<>(null, head, null); // trailer is preceded by header
        head.setNext(tail);
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // create and link a new node
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        if (position < 0 || position >= size) throw new IndexOutOfBoundsException("Invalid position");
        Node<E> node = head.getNext(); // skip the head sentinel
        for (int i = 0; i < position; i++) {
            node = node.getNext();
        }
        return node.getElement();
    }

    @Override
    public void add(int position, E e) {
        if (position < 0 || position > size) throw new IndexOutOfBoundsException("Invalid position");
        if (position == size) { // add to the end
            addBetween(e, tail.getPrev(), tail);
        } else {
            Node<E> succ = head.getNext();
            for (int i = 0; i < position; i++) {
                succ = succ.getNext();
            }
            addBetween(e, succ.getPrev(), succ);
        }
    }

    @Override
    public E remove(int position) {
        if (position < 0 || position >= size) throw new IndexOutOfBoundsException("Invalid position");
        Node<E> node = head.getNext();
        for (int i = 0; i < position; i++) {
            node = node.getNext();
        }
        return remove(node);
    }

    private class DoublyLinkedListIterator implements Iterator<E> {
        private Node<E> current = head.getNext(); // start at the first element, not the head sentinel

        @Override
        public boolean hasNext() {
            return current != tail; // check if we have reached the tail sentinel
        }

        @Override
        public E next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            E element = current.getElement();
            current = current.getNext();
            return element;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    public E first() {
        if (isEmpty()) return null;
        return head.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getPrev().getElement();
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) return null; // nothing to remove
        return remove(head.getNext());
    }

    @Override
    public E removeLast() {
        if (isEmpty()) return null; // nothing to remove
        return remove(tail.getPrev());
    }

    @Override
    public void addLast(E e) {
        addBetween(e, tail.getPrev(), tail);
    }

    @Override
    public void addFirst(E e) {
        addBetween(e, head, head.getNext());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> node = head.getNext(); // skip the head sentinel
        while (node != tail) { // until the tail sentinel
            sb.append(node.getElement());
            if (node.getNext() != tail) sb.append(", ");
            node = node.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public void reverseInplace() {
        if (size <= 1) return; // nothing to reverse if the list is empty or has one element

        Node<E> current = head.getNext(); // start after the head sentinel
        Node<E> prev = null;
        Node<E> next = null;

        while (current != tail) {
            next = current.getNext();
            current.setNext(prev);
            current.setPrev(next);
            prev = current;
            current = next;
        }

        // Swap head and tail sentinels
        Node<E> temp = head;
        head = tail;
        tail = temp;

        // Adjust head and tail sentinels
        head.setPrev(null);
        head.setNext(prev); // prev is the last non-sentinel node after reversal
        tail.setNext(null);
        tail.getPrev().setNext(tail);
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>();
        for (Integer i : arr) dl.addLast(i);
        System.out.println("forward list: " + dl);
        dl.reverseInplace();
        System.out.println("reverse list: " + dl);
    }
}
