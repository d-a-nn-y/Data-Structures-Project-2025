package tree;

import interfaces.Entry;
import interfaces.Position;
import utils.MapEntry;

import java.io.IOException;
//import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * An implementation of a sorted map using a binary search tree.
 */

public class TreeMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {


	protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();

	/** Constructs an empty map using the natural ordering of keys. */
	public TreeMap() {
		super(); // the AbstractSortedMap constructor
		tree.addRoot(null); // create a sentinel leaf as root
	}

	/**
	 * Constructs an empty map using the given comparator to order keys.
	 *
	 * @param comp comparator defining the order of keys in the map
	 */
	public TreeMap(Comparator<K> comp) {
		super(comp); // the AbstractSortedMap constructor
		tree.addRoot(null); // create a sentinel leaf as root
	}

	/**
	 * Returns the number of entries in the map.
	 *
	 * @return number of entries in the map
	 */
	@Override
	public int size() {
		return (tree.size() - 1) / 2; // only internal nodes have entries
	}

	protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) throws IOException {
		return tree.restructure(x);
	}

	/**
	 * Rebalances the tree after an insertion of specified position. This version of
	 * the method does not do anything, but it can be overridden by subclasses.
	 *
	 * @param p the position which was recently inserted
	 */
	protected void rebalanceInsert(Position<Entry<K, V>> p) throws IOException {
		// LEAVE EMPTY
	}

	/**
	 * Rebalances the tree after a child of specified position has been removed.
	 * This version of the method does not do anything, but it can be overridden by
	 * subclasses.
	 *
	 * @param p the position of the sibling of the removed leaf
	 */
	protected void rebalanceDelete(Position<Entry<K, V>> p) throws IOException {
		// LEAVE EMPTY
	}

	/**
	 * Rebalances the tree after an access of specified position. This version of
	 * the method does not do anything, but it can be overridden by a subclasses.
	 *
	 * @param p the Position which was recently accessed (possibly a leaf)
	 */
	protected void rebalanceAccess(Position<Entry<K, V>> p) throws IOException {
		// LEAVE EMPTY
	}

	/** Utility used when inserting a new entry at a leaf of the tree */
	private void expandExternal(Position<Entry<K, V>> p, Entry<K, V> entry) {
		tree.set(p,entry);
		tree.addLeft(p, null);
		tree.addRight(p, null);
	}

	protected Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
		return tree.parent(p);
	}

	protected Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
		return tree.left(p);
	}

	protected Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
		return tree.right(p);
	}

	protected Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
		return tree.sibling(p);
	}

	protected boolean isRoot(Position<Entry<K, V>> p) {
		return tree.isRoot(p);
	}

	protected boolean isExternal(Position<Entry<K, V>> p) {
		return tree.isExternal(p);
	}

	protected boolean isInternal(Position<Entry<K, V>> p) {
		return tree.isInternal(p);
	}

	protected void set(Position<Entry<K, V>> p, Entry<K, V> e) {
		tree.set(p, e);
	}

	protected Entry<K, V> remove(Position<Entry<K, V>> p) {
		return tree.remove(p);
	}

	/**
	 * Returns the position in p's subtree having the given key (or else the
	 * terminal leaf).
	 *
	 * @param key a target key
	 * @param p   a position of the tree serving as root of a subtree
	 * @return Position holding key, or last node reached during search
	 */
	public Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> p, K key) {
		if (isExternal(p)) {
			return p;
		}

		int comp = compare(key, p.getElement());
		if (comp == 0) {
			return p;
		} else if (comp < 0) {
			return treeSearch(left(p), key);
		} else {
			return treeSearch(right(p), key);
		}
	}

	/**
	 * Returns position with the minimal key in the subtree rooted at Position p.
	 *
	 * @param p a Position of the tree serving as root of a subtree
	 * @return Position with minimal key in subtree
	 */
	protected Position<Entry<K, V>> treeMin(Position<Entry<K, V>> p) {
		Position<Entry<K,V>> current = p;
		while(isInternal(current)){
			current = left(current);
		}
		return parent(current);
	}

	/**
	 * Returns the position with the maximum key in the subtree rooted at p.
	 *
	 * @param p a Position of the tree serving as root of a subtree
	 * @return Position with maximum key in subtree
	 */
	protected Position<Entry<K, V>> treeMax(Position<Entry<K, V>> p) {
		Position<Entry<K,V>> current = p;
		while(isInternal(current)){
			current = right(current);
		}
		return parent(current);
	}

	/**
	 * Returns the value associated with the specified key, or null if no such entry
	 * exists.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the associated value, or null if no such entry exists
	 */
	@Override
	public V get(K key) throws IllegalArgumentException, IOException {

		Position<Entry<K,V>> p = treeSearch(root(), key);
		rebalanceAccess(p);
		if (isExternal(p)) {
			return null;
		}
		return p.getElement().getValue();
	}

	/**
	 * Associates the given value with the given key. If an entry with the key was
	 * already in the map, this replaced the previous value with the new one and
	 * returns the old value. Otherwise, a new entry is added and null is returned.
	 *
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with the key (or null, if no such
	 *         entry)
	 */
	@Override
	public V put(K key, V value) throws IllegalArgumentException, IOException {

		Entry<K,V> newEntry = new MapEntry<>(key, value);
		Position<Entry<K,V>> p = treeSearch(root(), key);
		if(isExternal(p)){
			expandExternal(p, newEntry);
			rebalanceInsert(p);
			return null;
		} else{
			V old = p.getElement().getValue();
			set(p, newEntry);
			rebalanceAccess(p);
			return old;
		}

	}

	/**
	 * Removes the entry with the specified key, if present, and returns its
	 * associated value. Otherwise does nothing and returns null.
	 *
	 * @param key the key whose entry is to be removed from the map
	 * @return the previous value associated with the removed key, or null if no
	 *         such entry exists
	 */
	@Override
	public V remove(K key) throws IllegalArgumentException, IOException {

		Position<Entry<K,V>> p = treeSearch(root(), key);
		if(isExternal(p)){
			rebalanceAccess(p);
			return null;
		} else{
			V old = p.getElement().getValue();
			if(isInternal(left(p)) && isInternal(right(p))){
				Position<Entry<K,V>> replacement = treeMax(left(p));
				set(p, replacement.getElement());
				p = replacement;
			}
			Position<Entry<K,V>> leaf = (isExternal(left(p)) ?
					left(p) : right(p));
			Position<Entry<K, V>> sib = sibling(leaf);
			remove(leaf);
			remove(p);
			rebalanceDelete(sib);
			return old;
		}
	}

	// additional behaviors of the SortedMap interface

	/**
	 * Returns the entry having the least key (or null if map is empty).
	 *
	 * @return entry with least key (or null if map is empty)
	 */
	@Override
	public Entry<K, V> firstEntry() {
		if(isEmpty()) { return null; }
		return treeMin(root()).getElement();
	}

	/**
	 * Returns the entry having the greatest key (or null if map is empty).
	 *
	 * @return entry with greatest key (or null if map is empty)
	 */
	@Override
	public Entry<K, V> lastEntry() {
		if(isEmpty()) { return null; }
		return treeMax(root()).getElement();
	}

	/**
	 * Returns the entry with least key greater than or equal to given key (or null
	 * if no such key exists).
	 *
	 * @return entry with least key greater than or equal to given (or null if no
	 *         such entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {

		Position<Entry<K,V>> p = treeSearch(root(), key);
		if(isInternal(p)){ return p.getElement(); }
		while(!isRoot(p)){
			if(p == left(parent(p))){
				return parent(p).getElement();
			} else{
				p = parent(p);
			}
		}
		return null;
	}

	/**
	 * Returns the entry with greatest key less than or equal to given key (or null
	 * if no such key exists).
	 *
	 * @return entry with greatest key less than or equal to given (or null if no
	 *         such entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {

		Position<Entry<K,V>> p = treeSearch(root(), key);
		if(isInternal(p)){ return p.getElement(); }
		while(!isRoot(p)){
			if(p == right(parent(p))){
				return parent(p).getElement();
			} else{
				p = parent(p);
			}
		}
		return null;
	}

	/**
	 * Returns the entry with greatest key strictly less than given key (or null if
	 * no such key exists).
	 *
	 * @return entry with greatest key strictly less than given (or null if no such
	 *         entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {


		Position<Entry<K,V>> p = treeSearch(root( ), key);

		if (isInternal(p) && isInternal(left(p))) {
			return treeMax(left(p)).getElement( );
		}
		while (!isRoot(p)) {
			if (p == right(parent(p))) {
				return parent(p).getElement( );
			}
			else {
				p = parent(p);
			}
		}
		return null;
	}

	/**
	 * Returns the entry with least key strictly greater than given key (or null if
	 * no such key exists).
	 *
	 * @return entry with least key strictly greater than given (or null if no such
	 *         entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {


		Position<Entry<K,V>> p = treeSearch(root( ), key);

		if (isInternal(p) && isInternal(right(p))) {
			return treeMin(right(p)).getElement( );
		}
		while (!isRoot(p)) {
			if (p == left(parent(p))) {
				return parent(p).getElement( );
			}
			else {
				p = parent(p);
			}
		}
		return null;

	}

	// Support for iteration

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>(size());
		for(Position<Entry<K,V>> p : tree.inorder()){
			if(isInternal(p)){
				buffer.add(p.getElement());
			}
		}
		return buffer;
	}



	@Override
	public double loadFactor() {
		return 0;
	}

	@Override
	public int numCollisions() {
		return 0;
	}


	public String toString() {
		return tree.toString();
	}

	/**
	 * Returns an iterable containing all entries with keys in the range from
	 * <code>fromKey</code> inclusive to <code>toKey</code> exclusive.
	 *
	 * @return iterable with keys in desired range
	 * @throws IllegalArgumentException if <code>fromKey</code> or
	 *                                  <code>toKey</code> is not compatible with
	 *                                  the map
	 */
	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
		if(compare(fromKey,toKey) < 0){
			subMapRecurse(fromKey,toKey,root(), buffer);
		}
		return buffer;
	}

	// utility to fill subMap buffer recursively (while maintaining order)
	private void subMapRecurse(K fromKey, K toKey, Position<Entry<K, V>> p,
							   ArrayList<Entry<K, V>> buffer) {
		if(isInternal(p)){
			if(compare(p.getElement(), fromKey) < 0){
				subMapRecurse(fromKey,toKey,right(p),buffer);
			} else{
				subMapRecurse(fromKey,toKey,left(p),buffer);
				if(compare(p.getElement(), toKey) < 0){
					buffer.add(p.getElement());
					subMapRecurse(fromKey,toKey,right(p),buffer);
				}
			}
		}
	}

	protected void rotate(Position<Entry<K, V>> p) {
		tree.rotate(p);
	}

	protected Position<Entry<K,V>> root() { return tree.root(); }

	public String toBinaryTreeString() {
		BinaryTreePrinter< Entry<K, V> > btp = new BinaryTreePrinter<>(this.tree);
		return btp.print();
	}

	public static void main(String[] args) throws IOException {
		// LAB 11
		// Q2.
		TreeMap <Integer , Integer > bst = new TreeMap <>();
		Random rnd = new Random();
		int n_max = 50;
		int n = 20;
		rnd.ints(1, n_max)
				.limit(n)
				.distinct()
				.boxed()
				.forEach(x -> {
					try {
						bst.put(x, x);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
		System.out.println(bst.tree.inorder());

		// Q3.
		TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
		rnd = new Random();
		n_max = 1000;
		n = 100;
		rnd.ints(1, n_max)
				.limit(n)
				.distinct()
				.boxed()
				.forEach(x -> {
					try {
						bst.put(x, x);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
		// ensure we put() a node which doesn't already exist in the tree
		// ensure we remove() a node which does exist in the tree
		int n_trials = 10000;
		for(int i = 0; i < n_trials; ++i) {
			var keyset = treeMap.keySet();
			List<Integer> target = new ArrayList<>();
			keyset.forEach(target::add);
			if(treeMap.size() < n_max && rnd.nextFloat() > 0.5) {
				while(true) {
					Integer x = rnd.nextInt(n_max);
					if(!target.contains(x)) {
						treeMap.put(x,x);
						break;
					}
				}
			} else {
				if(treeMap.size() == 0) continue;
				Integer x = target.get(rnd.nextInt(target.size()));
				treeMap.remove(x);
			}
		}
		System.out.println(treeMap);
		System.out.println("Height: " + treeMap.tree.height(treeMap.root()));
	}


}
