package model.data_structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase de la lista-cola que implementa la interfaz ILinkedQueue
 * @author Julian Padilla - Pablo Pastrana
 * Usamos metodos del Libro Algorithms 4 edition:
 * 1. enqueue, dequeue, peek and toSting los autores son: Robert Sedgewick y Kevin Wayne.
 * @param <E> Tipo Generico
 *
 * Tomado de la cuarta edición del libro Algorithms (https://algs4.cs.princeton.edu/24pq/MaxPQ.java.html)
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class MaxHeapCP <T extends Comparable<T>> implements Iterable<T> 
{
	/**
	 * store items at indices 1 to n
	 */
	private T[] pq;     
	
	/**
	 * number of items on priority queue
	 */
	private int n;   

	/**
	 * optional comparator
	 */
	private Comparator<T> comparator; 

	/**
	 * Initializes an empty priority queue with the given initial capacity.
	 * @param  initCapacity the initial capacity of this priority queue
	 */
	@SuppressWarnings("unchecked")
	public MaxHeapCP(int capacity) 
	{
		pq = (T[]) new Comparable[capacity + 1];
		n = 0;
	}

	/**
	 * Initializes an empty priority queue.
	 */
	public MaxHeapCP() 
	{
		this(1);
	}

	/**
	 * Initializes an empty priority queue with the given initial capacity,
	 * using the given comparator.
	 * @param  initCapacity the initial capacity of this priority queue
	 * @param  comparator the order in which to compare the keys
	 */
	@SuppressWarnings("unchecked")
	public MaxHeapCP(int initCapacity, Comparator<T> comparator) 
	{
		this.comparator = comparator;
		pq = (T[]) new Comparable[initCapacity + 1];
		n = 0;
	}

	/**
	 * Initializes an empty priority queue using the given comparator.
	 * @param  comparator the order in which to compare the keys
	 */
	public MaxHeapCP(Comparator<T> comparator) 
	{
		this(1, comparator);
	}

	/**
	 * Initializes a priority queue from the array of keys.
	 * Takes time proportional to the number of keys, using sink-based heap construction.
	 * @param  keys the array of keys
	 */
	@SuppressWarnings("unchecked")
	public MaxHeapCP(T[] keys) 
	{
		n = keys.length;
		pq = (T[]) new Comparable[keys.length + 1];
		for (int i = 0; i < n; i++)
			pq[i+1] = keys[i];
		for (int k = n/2; k >= 1; k--)
			sink(k);
		assert isMaxHeap();
	}

	/**
	 * Returns true if this priority queue is empty.
	 * @return {@code true} if this priority queue is empty;
	 *         {@code false} otherwise
	 */
	public boolean isEmpty() 
	{
		return n == 0;
	}

	/**
	 * Returns the number of keys on this priority queue.
	 * @return the number of keys on this priority queue
	 */
	public int size() 
	{
		return n;
	}

	/**
	 * Returns a largest key on this priority queue.
	 * @return a largest key on this priority queue
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public T max() 
	{
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}

	// helper function to double the size of the heap array
	private void resize(int capacity)
	{
		assert capacity > n;
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Comparable[capacity];
		for (int i = 1; i <= n; i++) 
		{
			temp[i] = pq[i];
		}
		pq = temp;
	}


	/**
	 * Adds a new key to this priority queue.
	 * @param  x the new key to add to this priority queue
	 */
	public void insert(T x) 
	{
		// double size of array if necessary
		if (n == pq.length - 1) resize(2 * pq.length);

		// add x, and percolate it up to maintain heap invariant
		pq[++n] = x;
		swim(n);
		assert isMaxHeap();
	}

	/**
	 * Removes and returns a largest key on this priority queue.
	 * @return a largest key on this priority queue
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public T delMax() 
	{
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		T max = pq[1];
		exch(1, n--);
		sink(1);
		pq[n+1] = null;     // to avoid loitering and help with garbage collection
		if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
		assert isMaxHeap();
		return max;
	}

	/**
	 * The function swim in MaxHeapCp
	 * @param k Position 1 - element 1
	 */
	private void swim(int k) 
	{
		while (k > 1 && less(k/2, k)) 
		{
			exch(k, k/2);
			k = k/2;
		}
	}

	/**
	 * Helper functions to restore the heap invariant.
	 * @param k Actual Position
	 */
	private void sink(int k) 
	{
		while (2*k <= n) 
		{
			int j = 2*k;
			if (j < n && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}


	/**
	 *  Compare the elements in the MaxHeapCp
	 * @param i Position 1 - element 1
	 * @param j Position 2 - element 2
	 * @return The element than is less than the other element
	 */
	private boolean less(int i, int j)
	{
		if (comparator == null) 
		{
			return ((Comparable<T>) pq[i]).compareTo(pq[j]) < 0;
		}
		else
		{
			return comparator.compare(pq[i], pq[j]) < 0;
		}
	}

	/**
	 * Exchange elements in the array
	 * @param i Position 1 - element 1
	 * @param j Position 2 - element 2
	 */
	private void exch(int i, int j) 
	{
		T swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	/**
	 * is pq[1..n] a max heap?
	 * @return True if the MaxHeap is correct, False if the MaxHeap is incorrect
	 */
	private boolean isMaxHeap() 
	{
		for (int i = 1; i <= n; i++) 
		{
			if (pq[i] == null) return false;
		}
		for (int i = n+1; i < pq.length; i++)
		{
			if (pq[i] != null) return false;
		}
		if (pq[0] != null) return false;
		return isMaxHeapOrdered(1);
	}

	/**
	 * is subtree of pq[1..n] rooted at k a max heap?
	 * @param k Actual position
	 * @return True if the heapCp is ordened, false if the heap is not ordened
	 */
	private boolean isMaxHeapOrdered(int k) 
	{
		if (k > n) return true;
		int left = 2*k;
		int right = 2*k + 1;
		if (left  <= n && less(k, left))  return false;
		if (right <= n && less(k, right)) return false;
		return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
	}


	/***************************************************************************
	 * Iterator.
	 ***************************************************************************/

	/**
	 * Returns an iterator that iterates over the keys on this priority queue
	 * in descending order.
	 * The iterator doesn't implement {@code remove()} since it's optional.
	 * @return an iterator that iterates over the keys in descending order
	 */
	public Iterator<T> iterator() 
	{
		return new HeapIterator();
	}

	/**
	 * HeapIterator class
	 * @author Julian Padilla - Pablo Pastrana
	 */
	private class HeapIterator implements Iterator<T> 
	{

		/**
		 * create a new pq
		 */
		private MaxHeapCP<T> copy;

		/**
		 * add all items to copy of heap
		 * takes linear time since already in heap order so no keys move
		 */
		public HeapIterator() 
		{
			if (comparator == null) copy = new MaxHeapCP<T>(size());
			else                    copy = new MaxHeapCP<T>(size(), comparator);
			for (int i = 1; i <= n; i++)
				copy.insert(pq[i]);
		}

		/**
		 * has it a next an element?
		 */
		public boolean hasNext()  
		{
			return !copy.isEmpty();            
		}

		/**
		 * Remove a element from the heapCP
		 */
		public void remove()      
		{
			throw new UnsupportedOperationException();  
		}

		/**
		 * It has a next element in the HeapCp
		 */
		public T next() 
		{
			if (!hasNext()) throw new NoSuchElementException();
			return copy.delMax();
		}
	}
}