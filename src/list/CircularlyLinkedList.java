package list;

import interfaces.List;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CircularlyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E element;
        private CircularlyLinkedList.Node<E> next;

        public Node(E e, CircularlyLinkedList.Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods
        public E getElement() {
            return element;
        }
        public CircularlyLinkedList.Node<E> getNext() {
            return next;
        }
        public void setNext(CircularlyLinkedList.Node<E> n) {
            next = n;
        }


    } //----------- end of nested Node class -----------

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        //TODO
        return null;
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        //TODO
    }

    @Override
    public E remove(int i) {
        //TODO
        return null;
    }

    public void rotate() {
        //if (tail != null) // if empty, do nothing
            //tail = tail.getNext( );
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        @Override
        public boolean hasNext() {
            // TODO
            return false;
        }

        @Override
        public E next() {
            // TODO
            return null;
        }
        //TODO
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        //TODO
        return null;
    }

    @Override
    public E removeLast() {
        //TODO
        return null;
    }

    @Override
    public void addFirst(E e) {
        //TODO
    }

    @Override
    public void addLast(E e) {
        //TODO
    }


    public String toString() {
        //TODO
        return null;
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for(int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
