package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import model.data_structures.MaxColaCP;

class MaxColaCPTest 
{
	/**
	 * Cola de prioridad de tipo intger
	 */
	private MaxColaCP<Integer> colaPrioridad1;
	
	/**
	 * Cola de prioridad de tipo String
	 */
	private MaxColaCP<String> colaPrioridad2;
	
	/**
	 * Cola de prioridad de tipo double
	 */
	private MaxColaCP<Double> colaPrioridad3;

	/**
	 * Escenario 1: Crea una cola de prioridad de tipo integer
	 */
	@Before
	public void setupEscenario1( )
	{
		colaPrioridad1 = new MaxColaCP<Integer>();
		colaPrioridad1.insert(1);
		colaPrioridad1.insert(2);
		colaPrioridad1.insert(3);
		colaPrioridad1.insert(4);
		colaPrioridad1.insert(5);
	}
	
	/**
	 * Escenario 2: Crea una cola de prioridad de tipo String
	 */
	@Before
	public void setupEscenario2( )
	{
		colaPrioridad2 = new MaxColaCP<String>();
		colaPrioridad2.insert("Hola");
		colaPrioridad2.insert("Amigo");
		colaPrioridad2.insert("Juan");
		colaPrioridad2.insert("Como");
		colaPrioridad2.insert("Estas");
		colaPrioridad2.insert("Hoy");
	}
	
	/**
	 * Escenario 3: Crea una cola de prioridad de tipo String
	 */
	@Before
	public void setupEscenario3( )
	{
		colaPrioridad3 = new MaxColaCP<Double>();
	}

	/**
	 * Prueba 1: Test del metodo constructor de la cola de prioridad
	 */
	@Test
	void testMaxColaCP() 
	{
		colaPrioridad1 = new MaxColaCP<Integer>();
		colaPrioridad2 = new MaxColaCP<String>();
		colaPrioridad3 = new MaxColaCP<Double>();
	}

	/**
	 * Prueba 2: Test para verificar si la la cola de prioridad esta vacia
	 */
	@Test
	void testIsEmpty() 
	{
		setupEscenario3();
		assertTrue(colaPrioridad3.isEmpty());
		colaPrioridad3.insert(12.3);
		assertFalse(colaPrioridad3.isEmpty());
	}

	/**
	 * Prueba 3: Test para verificar el tamaño de las colas de prioridad para los tres escenarios
	 */
	@Test
	void testSize() 
	{
		setupEscenario1();
		assertEquals(5, colaPrioridad1.size());
		
		setupEscenario2();
		assertEquals(6, colaPrioridad2.size());
	
		setupEscenario3();
		assertEquals(0, colaPrioridad3.size());
	}

	/**
	 * Prueba 4: Test verifica que el elemeto mayor de la cola de prioridad sea el correcto
	 */
	@Test
	void testMax() 
	{
		setupEscenario1();
		assertEquals(5, colaPrioridad1.delMax().intValue());
		
		setupEscenario2();
		assertEquals("Juan", colaPrioridad2.delMax());
	}

	/**
	 * Prueba 5: Test insertar elemento en la cola de prioridad
	 */
	@Test
	void testInsert()
	{
		setupEscenario1();
		colaPrioridad1.insert(6);
		colaPrioridad1.insert(7);
		assertEquals(7, colaPrioridad1.size());
		
		setupEscenario2();
		colaPrioridad2.insert("Bien");
		colaPrioridad2.insert("Mal");
		assertEquals(8, colaPrioridad2.size());
		
		setupEscenario3();
		colaPrioridad3.insert(12.1);
		assertEquals(1, colaPrioridad3.size());
	}

	/**
	 * Prueba 6: Test para eliminar el elemento max de la cola de priorida
	 */
	@Test
	void testDelMax() 
	{
		setupEscenario1();
		colaPrioridad1.delMax();
		assertEquals(4, colaPrioridad1.size());
		
		setupEscenario2();
		colaPrioridad2.delMax();
		colaPrioridad2.delMax();
		assertEquals(4, colaPrioridad1.size());	
	}

	/**
	 * Prueba 7: Test para el iterador de la cola de prioridad
	 */
	@Test
	void testIterator() 
	{
		MaxColaCP<String> colaPrioridad4 = new MaxColaCP<String>();
		colaPrioridad4.insert("orange");

		String[] expected = {"orange", "kiwi", "apple"};
		int i = 0;

		for (String fruit : colaPrioridad4) 
		{
			assertEquals(expected[i], fruit);
		}
	}
}