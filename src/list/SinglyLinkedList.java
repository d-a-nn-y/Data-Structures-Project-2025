package list;

import interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods
        public E getElement() {
            return element;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setNext(Node<E> n) {
            next = n;
        }


    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)

    /**
     * The last node of the list
     */
    private Node<E> tail = null;               // last node of the list (or null if empty)

    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() { }              // constructs an initially empty list

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        Node<E> node = head;
        for (int i = 0; i < position; i++) {
            node = node.getNext();
        }
        return node.getElement();
    }

    @Override
    public void add(int position, E e) {
        Node<E> newest = new Node<>(e, null);
        if (position == 0) { // insert at the head
            newest.setNext(head);
            head = newest;
            if (size == 0) tail = head; // if list was empty, new node is also the tail
        } else {
            Node<E> prev = head;
            for (int i = 1; i < position; i++) {
                prev = prev.getNext();
            }
            newest.setNext(prev.getNext());
            prev.setNext(newest);
            if (newest.getNext() == null) tail = newest; // if new node is added at the end, update the tail
        }
        size++;
    }


    @Override
    public void addFirst(E e) {
        head = new Node<E>(e, head); // create and link a new node
        size++;

    }

    @Override
    public void addLast(E e) {
        Node<E> newest = new Node<E>(e, null); // node will eventually be the tail
        Node<E> last = head;
        if(last == null) {
            head = newest;
        }
        else {
            while (last.getNext() != null) { // advance to the last node
                last = last.getNext();
            }
            last.setNext(newest); // new node after existing tail
        }
        size++;
    }

    @Override
    public E remove(int position) {
        if (position == 0) return removeFirst();
        Node<E> prev = head;
        for (int i = 1; i < position; i++) {
            prev = prev.getNext();
        }
        Node<E> current = prev.getNext();
        prev.setNext(current.getNext());
        if (current == tail) tail = prev; // if last element is removed update the tail
        size--;
        return current.getElement();
    }

    @Override
    public E removeFirst() {
        if (isEmpty( )) return null; // nothing to remove
        E answer = head.getElement( );
        head = head.getNext( ); // will become null if list had only one node
        size--;
        if (size == 0)
            tail = null; // special case as list is now empty
        return answer;

    }

    @Override
    public E removeLast() {
        if (isEmpty()) return null; // nothing to remove
        if (size == 1) return removeFirst(); // handle the case when there's only one element

        Node<E> prev = head;
        while (prev.getNext().getNext() != null) { // find the second last node
            prev = prev.getNext();
        }
        E element = tail.getElement();
        tail = prev; // update the tail to the second last node
        tail.setNext(null); // remove the reference to the last node
        size--;
        return element;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        private Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = (E) curr.getElement();
            curr = curr.getNext();
            return res;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            s.append(curr.getElement());
            if (curr.getNext() != null) s.append(", ");
            curr = curr.getNext();
        }
        s.append("]");
        return s.toString();
    }

    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next; //store next node
            current.next = prev; //reverse pointer

            prev = current;  // move prev to current
            current = next;  //move current to next node
        }

        head = prev; // head is new front
    }

    public SinglyLinkedList<E> recursiveCopy() {
        SinglyLinkedList<E> copy = new SinglyLinkedList<>();
        copy.head = recursiveCopyHelper(this.head); // Start recursion from head
        copy.size = this.size; // Copy size
        return copy;
    }

    private Node<E> recursiveCopyHelper(Node<E> current) {
        if (current == null) {
            return null; // Base case: End of list
        }
        Node<E> newNode = new Node<>(current.getElement(), null); // Create new node
        newNode.setNext(recursiveCopyHelper(current.getNext())); // Recursively copy the rest
        return newNode;
    }


    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);
        ll.reverse();
        System.out.println(ll);

        System.out.println("Original List: " + ll);
        SinglyLinkedList<Integer> copy = ll.recursiveCopy();
        System.out.println("Copied List: " + copy);

        // Check if lists are independent
        ll.addFirst(8);
        System.out.println("After changing og list:");
        System.out.println("Original List: " + ll);
        System.out.println("Copied List: " + copy);
    }
}
