package stacksqueues;

import interfaces.Queue;

public class ArrayQueue<E> implements Queue<E> {

	private static int CAPACITY = 1000;
	private E[] data;
	private int front = 0;
	private int size = 0;

	public ArrayQueue(int capacity) {
		// TODO
	}

	public ArrayQueue() {
		this(CAPACITY);
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
	public void enqueue(E e) {
		// TODO

	}

	@Override
	public E first() {
		// TODO
		return null;
	}

	@Override
	public E dequeue() {
		// TODO
		return null;
	}

	public String toString() {
		// TODO
		return null;
	}

	public static void main(String[] args) {
		Queue<Integer> qq = new ArrayQueue<>();
		System.out.println(qq);

		int N = 10;
		for(int i = 0; i < N; ++i) {
			qq.enqueue(i);
		}
		System.out.println(qq);

		for(int i = 0; i < N/2; ++i) qq.dequeue();
		System.out.println(qq);

	}
}
