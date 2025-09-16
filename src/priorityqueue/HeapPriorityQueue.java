package priorityqueue;

/*
 */

import interfaces.Entry;
//import tree.BinaryTreeTex;
import tree.LinkedBinaryTree;
import utils.Timer;
import utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K extends Comparable<K>, V extends Comparable<V> > extends AbstractPriorityQueue<K, V> {

	protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

	public ArrayList<K> keys() {
		// TODO
		return null;

	}

	public ArrayList<V> values() {
		// TODO
		return null;
	}
	/**
	 * Creates an empty priority queue based on the natural ordering of its keys.
	 */
	public HeapPriorityQueue() {
		super();
	}

	/**
	 * Creates an empty priority queue using the given comparator to order keys.
	 *
	 * @param comp comparator defining the order of keys in the priority queue
	 */
	public HeapPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	/**
	 * Creates a priority queue initialized with the respective key-value pairs. The
	 * two arrays given will be paired element-by-element. They are presumed to have
	 * the same length. (If not, entries will be created only up to the length of
	 * the shorter of the arrays)
	 *
	 * @param keys   an array of the initial keys for the priority queue
	 * @param values an array of the initial values for the priority queue
	 */
	public HeapPriorityQueue(K[] keys, V[] values) {
		// TODO
	}

	// protected utilities
	protected int parent(int j) {
		// TODO
		return 0;
	}

	protected int left(int j) {
		// TODO
		return 0;

	}

	protected int right(int j) {
		// TODO
		return 0;
	}

	protected boolean hasLeft(int j) {
		// TODO
		return false;
	}

	protected boolean hasRight(int j) {
		// TODO
		return false;

	}

	/** Exchanges the entries at indices i and j of the array list. */
	protected void swap(int i, int j) {
		// TODO
	}

	/**
	 * Moves the entry at index j higher, if necessary, to restore the heap
	 * property.
	 */
	protected void upheap(int j) {
		// TODO
		// keep doing until satisfied
		// get the parent of j
		// compare keys of current position j with parent(j)
		// if heap order satisfied -> done
		// else bubble up

	}

	/*
	heapsort in place
	 */
	static<V extends Comparable<V> > void heapsort(V[] arr) {
		// TODO

	}
	protected static<V extends Comparable<V> > void downheap(V[] arr, int start, int end) {
		// TODO
		// while not at the bottom tree (leaf)
		// if key_parent >= key_child stop
		// find smallest child and swap
	}

	/**
	 * Moves the entry at index j lower, if necessary, to restore the heap property.
	 */
	protected void downheap(int j) {
		// TODO
		// while not at the bottom tree (leaf)
		// if key_parent >= key_child stop
		// find smallest child and swap

	}

	/** Performs a bottom-up construction of the heap in linear time. */
	protected void heapify() {
		// TODO
	}

	// public methods

	/**
	 * Returns the number of items in the priority queue.
	 *
	 * @return number of items
	 */
	@Override
	public int size() {
		return heap.size();
	}

	/**
	 * Returns (but does not remove) an entry with minimal key.
	 *
	 * @return entry having a minimal key (or null if empty)
	 */
	@Override
	public Entry<K, V> min() {
		return heap.get(0);
	}

	/**
	 * Inserts a key-value pair and return the entry created.
	 *
	 * @param key   the key of the new entry
	 * @param value the associated value of the new entry
	 * @return the entry storing the new key-value pair
	 * @throws IllegalArgumentException if the key is unacceptable for this queue
	 */
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		// TODO
		return null;
	}

	/**
	 * Removes and returns an entry with minimal key.
	 *
	 * @return the removed entry (or null if empty)
	 */
	@Override
	public Entry<K, V> removeMin() {
		// TODO
		return null;
	}

	public String toString() {
		return heap.toString();
	}



	public static void main(String[] args) {
	}


}
