package il.ac.telhai.ds.hash;
import il.ac.telhai.ds.linkedlist.DLinkedList;
@SuppressWarnings({"unchecked","rawtypes"})

public class HashTable<V> {
	public static final int DEF_MAX_HASH_SIZE = 10;
	int size;
	int added;
	DLinkedList[] arr;


	/**
	 * constructor
	 * constructs a hash-table of max-size "DEF_MAX_HASH_SIZE".
	 */
	public HashTable() {
		size = DEF_MAX_HASH_SIZE;
		added = 0;
		arr = new DLinkedList[size];
	}


	/**
	 * constructs a hash-table of size 'hashSize'.
	 * @param hashSize, the size of the constructed hash-table.
	 */

	public HashTable (int hashSize) {
		size = hashSize;
		added = 0;
		arr = new DLinkedList[size];
	}
	
	/**
	 * @param val
	 * @return true if the hash-table contains val, otherwise return false
	 */
	public boolean contains(V val) {
		if(isEmpty())return false;
		int k = getHashCode(val);
		if(arr[k] == null)return false;
		if(arr[k].isEmpty())return false;
		DLinkedList<V> list =  arr[k];
		list.goToBeginning();
		V p = list.getCursor();
		while(list.hasNext() && !p.equals(val)) {
			p = list.getNext();
		}

		return p.equals(val);
	}

	/**
	 * Add val to the hash-table.
	 * 
	 * @param val
	 * @return If the val has already existed in the the hash-table, it will not be
	 *         added again. Return true if the val was added successfully. Otherwise
	 *         return false.
	 */
	public boolean add(V val) {
		int k = getHashCode(val);
		if(arr[k] == null) arr[k] = new DLinkedList<V>();
		if(contains(val))return false;
		arr[k].insert(val);
		added +=1;
		return true;
	}

	/**
	 * Remove val from the hash-table.
	 * 
	 * @param val
	 * @return Return true if the val was removed successfully. Otherwise return
	 *         false.
	 */
	public boolean remove(V val) {
		if( !contains(val) ){return false;}
		int k = getHashCode(val);
		arr[k].remove(val);
		added -=1;
		return true;
	}

	/**
	 * clear all the data in the hash-table
	 */
	public void clear() {
		added = 0;
		arr = null;
	}

	/**
	 * @return true if the hash-table is empty, otherwise return false.
	 */
	public boolean isEmpty() {
		return added == 0;
	}

	private int getHashCode(V v){
		return Math.abs((v.hashCode() %size));
	}
}