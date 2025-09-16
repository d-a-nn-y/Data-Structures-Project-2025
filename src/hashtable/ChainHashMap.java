package hashtable;

import interfaces.Entry;
import interfaces.Position;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {



	// a fixed capacity array of UnsortedTableMap that serve as buckets
	private UnsortedTableMap<K, V>[] table; // initialized within createTable

	/** Creates a hash table with capacity 11 and prime factor 109345121. */
	public ChainHashMap() {
		super();
		//createTable();
	}

	/** Creates a hash table with given capacity and prime factor 109345121. */
	public ChainHashMap(int cap) {
		super(cap);
		//createTable();
	}

	/** Creates a hash table with the given capacity and prime factor. */
	public ChainHashMap(int cap, int p) {
		super(cap, p);
		//createTable();
	}

	/** Creates an empty table having length equal to current capacity. */
	@Override
	@SuppressWarnings({ "unchecked" })
	protected void createTable() {
		table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
	}

	@Override
	public double loadFactor() {
		return (double) n / capacity;
	}

	@Override
	public int numCollisions() {
		return 0;
	}
	/**
	 * Returns value associated with key k in bucket with hash value h. If no such
	 * entry exists, returns null.
	 *
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return associate value (or null, if no such entry)
	 */
	@Override
	protected V bucketGet(int h, K k) {
		UnsortedTableMap<K, V> bucket = table[h];
		if (bucket == null) return null; // No bucket at this index
		return bucket.get(k);
	}

	/**
	 * Associates key k with value v in bucket with hash value h, returning the
	 * previously associated value, if any.
	 *
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @param v the value to be associated
	 * @return previous value associated with k (or null, if no such entry)
	 */


	@Override
	protected V bucketPut(int h, K k, V v) {
		UnsortedTableMap<K, V> bucket = table[h];
		if (bucket == null) {
			bucket = new UnsortedTableMap<>();
			table[h] = bucket;
		}
		int oldSize = bucket.size();
		V oldValue = bucket.put(k, v);
		n += (bucket.size() - oldSize); // Update size if a new entry was added
		return oldValue;

	}


	/**
	 * Removes entry having key k from bucket with hash value h, returning the
	 * previously associated value, if found.
	 *
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return previous value associated with k (or null, if no such entry)
	 */
	@Override
	protected V bucketRemove(int h, K k) {
		UnsortedTableMap<K, V> bucket = table[h];
		if (bucket == null) return null; // No bucket at this index
		int oldSize = bucket.size();
		V removedValue = bucket.remove(k);
		n -= (oldSize - bucket.size()); // Update size if an entry was removed
		return removedValue;
	}

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> entries = new ArrayList<>();
		for (int h = 0; h < table.length; h++) {
			if (table[h] != null) {
				for (Entry<K, V> entry : table[h].entrySet()) {
					entries.add(entry);
				}
			}
		}
		return entries;
	}

	public String toString() {
		return entrySet().toString();
	}

	public static void main(String[] args) {
		ChainHashMap<Integer, String> m = new ChainHashMap<Integer, String>();
		m.put(1, "One");
		m.put(10, "Ten");
		m.put(11, "Eleven");
		m.put(20, "Twenty");

		System.out.println("m: " + m);
		//System.out.println("m.get(11): " + m.get(11));
		m.remove(11);
		System.out.println("m: " + m);
	}
}
