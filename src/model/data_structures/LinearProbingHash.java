package model.data_structures;

/**
 * Clase de la tabla de hash - LinearProbing
 * @author Julian Padilla - Pablo Pastrana
 * Obtuvimos metodos de la pagina web: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/LinearProbingHashST.java.html
 * Autores de los metodos obtenidos de Algorithms 4th edition: Robert Sedgewick y Kevin Wayne.
 * @param <K> Key (llave)
 * @param <V> Value (Valor)
 */
public class LinearProbingHash<K extends Comparable<K>, V >
{
	// Atributos

	/**
	 * Constante del numero inicial de la tabla de hash
	 */
	private static final int INIT_CAPACITY = 4;

	/**
	 * Numero de parejas llave - valor de la tabla de simbolos
	 */
	private int n;           

	/**
	 * El tamaño de la tabla de hash
	 */
	private int m;          

	/**
	 * Arreglo de las llaves
	 */
	private K[] keys;      

	/**
	 * Arreglo de los valores
	 */
	private V[] vals;    

	// Metodos constructores

	/**
	 * Inicializa la tabla de hash con la capacidad inicial
	 */
	public LinearProbingHash() 
	{
		this(INIT_CAPACITY);
	} 

	/**
	 * Inicializa la tabla de hash con la capacidad inicial
	 * @param capacity Capacidad de la tabla de hash
	 */
	@SuppressWarnings("unchecked")
	public LinearProbingHash(int capacity) 
	{
		m = capacity;
		n = 0;
		keys = (K[]) new Comparable[m];
		vals = (V[]) new Comparable[m];
	}

	/**
	 * Da el tamaño de la tabla de hash
	 */
	public int size() 
	{
		return n;
	}

	/**
	 * Revisa si la tabla de hash esta vacia
	 * @return True si la tabla de hash esta vacia, de lo contrario retorna false
	 */
	public boolean isEmpty() 
	{
		return size() == 0;
	}

	/**
	 * Retorna true si la llave se encuntra dentro de la tabla de hash, de lo contrario retorna false
	 */
	public boolean contains(K key) 
	{
		if (key == null) 
		{
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return get(key) != null;
	}

	/**
	 * Funcion de hash
	 * @param key Llave para poder realizar la funcion de hash
	 * @return El valor entero que retorna la funcion de hash
	 */
	private int hash(K key) 
	{
		return (key.hashCode() & 0x7fffffff) % m;
	}

	/**
	 * Se enecarga de hacer el rehash para llenar el arreglo y no dejar espacios en este
	 * @param capacity Capacidad del arreglo ara realizar la funcion de rehash
	 */
	public void rehash(int capacity) 
	{
		LinearProbingHash<K, V> temp = new LinearProbingHash<K, V>(capacity);
		for (int i = 0; i < m; i++) 
		{
			if (keys[i] != null) 
			{
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		m    = temp.m;
	}

	/**
	 * Inserta una llave con su respectivo valor en la tabla de hash - Linear Probing
	 * @param key Llave establecido
	 * @param val Valor respectivo de la llave
	 */
	public void put(K key, V val) 
	{
		boolean agrego = false;
		if (key == null) 
		{
			throw new IllegalArgumentException("first argument to put() is null");
		}

		if (val == null)
		{
			delete(key);
			agrego = true;
		}

		if(!agrego)
		{
			// double table size if 50% full
			if (n >= m/2) rehash((int) (m*(1.75)));

			int i;
			for (i = hash(key); keys[i] != null; i = (i + 1) % m) 
			{
				if (keys[i].equals(key)) 
				{
					vals[i] = val;
					agrego = true;
				}
			}
			if(!agrego)
			{
				keys[i] = key;
				vals[i] = val;
				n++;
			}
		}
	}

	/**
	 * Obtener el valor de una llave requerida
	 * @param key Llave establecida
	 * @return El valor de la llave requerida
	 */
	public V get(K key) 
	{
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	/**
	 * Eliminar una llave de la tabla de hash - Linear Probing con su respectivo valor
	 * @param key Llave establecida
	 */
	public V delete(K key)
	{
		V element = null;
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key)) return element;

		// find position i of key
		int i = hash(key);
		while (!key.equals(keys[i]))
		{
			i = (i + 1) % m;
		}

		element = vals[i];

		// delete key and associated value
		keys[i] = null;
		vals[i] = null;

		// rehash all keys in same cluster
		i = (i + 1) % m;
		while (keys[i] != null)
		{
			// delete keys[i] and vals[i] and reinsert
			K   keyToRehash = keys[i];
			V valToRehash = vals[i];
			keys[i] = null;
			vals[i] = null;
			n--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % m;
		}

		n--;

		// halves size of array if it's 12.5% full or less
		if (n > 0 && n <= m/8) rehash((m/2)+1);

		return element;
	}

	/**
	 * Iterable para recorrer la tabla de hash - Linear Probing
	 * @return El elemento llave - valor de la tabla de hashb
	 */
	public Iterable<K> keys() 
	{
		LinkedQueue<K> queue = new LinkedQueue<K>();
		for (int i = 0; i < m; i++)
			if (keys[i] != null) queue.enqueue(keys[i]);
		return queue;
	}

	/**
	 * Iterable para recorrer la tabla de hash - Linear Probing
	 * @return El elemento valor - valor de la tabla de hashb
	 */
	public Iterable<V> values() 
	{
		LinkedQueue<V> queue = new LinkedQueue<V>();
		for (int i = 0; i < m; i++)
			if (vals[i] != null) queue.enqueue(vals[i]);
		return queue;
	}
}
