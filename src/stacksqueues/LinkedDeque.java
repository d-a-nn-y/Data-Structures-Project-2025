package stacksqueues;

import interfaces.Deque;
import list.DoublyLinkedList;

public class LinkedDeque<E> implements Deque<E> {

	DoublyLinkedList<E> ll ;

	public LinkedDeque() {
		ll = new DoublyLinkedList<>();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public int size() {
		// TODO
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO
		return false;
	}

	@Override
	public E first() {
		// TODO
		return null;
	}

	@Override
	public E last() {
		// TODO
		return null;
	}

	@Override
	public void addFirst(E e) {
		// TODO
	}

	@Override
	public void addLast(E e) {
		// TODO
	}

	@Override
	public E removeFirst() {
		// TODO
		return null;
	}

	@Override
	public E removeLast() {
		// TODO
		return null;
	}

	public String toString() {
		// TODO
		return null;
	}
}
