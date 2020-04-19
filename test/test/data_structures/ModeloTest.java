package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.Comparendo;
import model.LlaveComparendo;
import model.data_structures.LinearProbingHash;
import model.data_structures.LinkedQueue;
import model.data_structures.MaxHeapCP;
import model.data_structures.RedBlackBST;
import model.logic.Modelo;

/**
 * Clase de test para la clase modelo - requerimientos
 * @author Julian Padilla Molina - Pablo Pastrana
 */
public class ModeloTest 
{
	/**
	 * Cola de tipo comparendo
	 */
	private LinkedQueue<Comparendo> colaComparendos1;

	/**
	 * Cola de prioridad comparendo
	 */
	private MaxHeapCP<Comparendo> colaPriridadComparendos2;

	/**
	 * Cola de prioridad comparendo
	 */
	private LinearProbingHash<LlaveComparendo, Comparendo> tablaComparendo3;

	/**
	 * Cola de prioridad comparendo
	 */
	private RedBlackBST<LlaveComparendo , Comparendo> arbolComparendos3;

	/* 
	 * Instancia del Modelo
	 */
	public Modelo modelo;

	/**
	 * Verifica que el arreglo si se este creando
	 */
	@Test
	public void testLinkedQueue() 
	{
		colaComparendos1 = new LinkedQueue<Comparendo>();
	}

	/**
	 * Escenario 1: Crea un cola con elementos
	 */
	@Before
	public void setupEscenario1( )
	{
		colaComparendos1 = new LinkedQueue<Comparendo>();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/ss");
		String fecha1 = "2018/10/08";
		String fecha2 = "2018/04/23";
		String fecha3 = "2018/12/27";
		String fecha4 = "2018/09/19";
		Date fechaDate1 = null;
		Date fechaDate2 = null;
		Date fechaDate3 = null;
		Date fechaDate4 = null;
		
		try
		{
			fechaDate1 = formato.parse(fecha1);
			fechaDate2 = formato.parse(fecha2);
			fechaDate3 = formato.parse(fecha3);
			fechaDate4 = formato.parse(fecha4);
			
			colaComparendos1.enqueue(new Comparendo(2938267, fechaDate1, "Manejar con alcoho", "Papel y Lapiz", "Automovil", "Público", "F07", "BOSA", "BOGOTA", 43.29, 76.89));
			colaComparendos1.enqueue(new Comparendo(2019365, fechaDate2, "Conducir sin papeles", "Electronico", "Bus", "Particular", "J07", "USAQUEN", "BOGOTA", 41.98, 79.89));
			colaComparendos1.enqueue(new Comparendo(2019904, fechaDate3, "Limite de velocidad", "Elctronico", "Camioneta", "Poficial", "F", "CHAPINERO", "BOGOTA", 38.98, 65.89));
			colaComparendos1.enqueue(new Comparendo(2087134, fechaDate4, "Manejar siendo menor de edad", "Papel y Lapiz", "Automovil", "Público", "F09", "BOSA", "BOGOTA", 423.87, 98.89));

		} 
		catch (Exception ex) 
		{
			System.out.println(ex);
		}
	}

	/**
	 * Escenario 2: Crea un cola de prioridad con elementos
	 */
	@Before
	public void setupEscenario2( )
	{
		colaPriridadComparendos2 = new MaxHeapCP<Comparendo>();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/ss");
		String fecha1 = "2018/10/08";
		String fecha2 = "2018/04/23";
		String fecha3 = "2018/12/27";
		String fecha4 = "2018/09/19";
		Date fechaDate1 = null;
		Date fechaDate2 = null;
		Date fechaDate3 = null;
		Date fechaDate4 = null;
		
		try
		{
			fechaDate1 = formato.parse(fecha1);
			fechaDate2 = formato.parse(fecha2);
			fechaDate3 = formato.parse(fecha3);
			fechaDate4 = formato.parse(fecha4);
			
			colaPriridadComparendos2.insert(new Comparendo(2019134, fechaDate1, "Manejar con alcoho", "Papel y Lapiz", "Automovil", "Público", "F07", "BOSA", "BOGOTA", 43.29, 76.89));
			colaPriridadComparendos2.insert(new Comparendo(2019365, fechaDate2, "Conducir sin papeles", "Electronico", "Bus", "Particular", "J07", "USAQUEN", "BOGOTA", 41.98, 79.89));
			colaPriridadComparendos2.insert(new Comparendo(2019904, fechaDate3, "Limite de velocidad", "Elctronico", "Camioneta", "Poficial", "F", "CHAPINERO", "BOGOTA", 38.98, 65.89));
			colaPriridadComparendos2.insert(new Comparendo(2087134, fechaDate4, "Manejar siendo menor de edad", "Papel y Lapiz", "Automovil", "Público", "F09", "BOSA", "BOGOTA", 423.87, 98.89));

		} 
		catch (Exception ex) 
		{
			System.out.println(ex);
		}
	}

	/**
	 * Escenario 4: Crea un arbol rojo - negro
	 */
	@Before
	public void setupEscenario3( )
	{
		tablaComparendo3 = new LinearProbingHash<LlaveComparendo, Comparendo>();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/ss");
		String fecha1 = "2018/10/08";
		String fecha2 = "2018/04/23";
		String fecha3 = "2018/12/27";
		String fecha4 = "2018/09/19";
		Date fechaDate1 = null;
		Date fechaDate2 = null;
		Date fechaDate3 = null;
		Date fechaDate4 = null;
		
		try
		{
			fechaDate1 = formato.parse(fecha1);
			fechaDate2 = formato.parse(fecha2);
			fechaDate3 = formato.parse(fecha3);
			fechaDate4 = formato.parse(fecha4);
			
			tablaComparendo3.put(new LlaveComparendo(fechaDate1, "Paritcular", "J03") , new Comparendo(2019134, fechaDate1, "Manejar con alcoho", "Papel y Lapiz", "Automovil", "Público", "F07", "BOSA", "BOGOTA", 43.29, 76.89));
			tablaComparendo3.put(new LlaveComparendo(fechaDate2, "Publico", "I06") ,new Comparendo(2019365, fechaDate2, "Conducir sin papeles", "Electronico", "Bus", "Particular", "J07", "USAQUEN", "BOGOTA", 41.98, 79.89));
			tablaComparendo3.put(new LlaveComparendo(fechaDate3, "Paritcular", "F") ,new Comparendo(2019904, fechaDate3, "Limite de velocidad", "Elctronico", "Camioneta", "Poficial", "F", "CHAPINERO", "BOGOTA", 38.98, 65.89));
			tablaComparendo3.put(new LlaveComparendo(fechaDate4, "Poficial", "A01"), new Comparendo(2087134, fechaDate4, "Manejar siendo menor de edad", "Papel y Lapiz", "Automovil", "Público", "F09", "BOSA", "BOGOTA", 423.87, 98.89));

		} 
		catch (Exception ex) 
		{
			System.out.println(ex);
		}
	}

	/**
	 * Escenario 4: Crea un arbol rojo - negro
	 */
	@Before
	public void setupEscenario4( )
	{
		arbolComparendos3 = new RedBlackBST<LlaveComparendo, Comparendo>();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/ss");
		String fecha1 = "2018/10/08";
		String fecha2 = "2018/04/23";
		String fecha3 = "2018/12/27";
		String fecha4 = "2018/09/19";
		Date fechaDate1 = null;
		Date fechaDate2 = null;
		Date fechaDate3 = null;
		Date fechaDate4 = null;
		
		try
		{
			fechaDate1 = formato.parse(fecha1);
			fechaDate2 = formato.parse(fecha2);
			fechaDate3 = formato.parse(fecha3);
			fechaDate4 = formato.parse(fecha4);
			
			arbolComparendos3.put(new LlaveComparendo(fechaDate1, "Paritcular", "J03") , new Comparendo(2019134, fechaDate1, "Manejar con alcoho", "Papel y Lapiz", "Automovil", "Público", "F07", "BOSA", "BOGOTA", 43.29, 76.89));
			arbolComparendos3.put(new LlaveComparendo(fechaDate2, "Publico", "I06") ,new Comparendo(2019365, fechaDate2, "Conducir sin papeles", "Electronico", "Bus", "Particular", "J07", "USAQUEN", "BOGOTA", 41.98, 79.89));
			arbolComparendos3.put(new LlaveComparendo(fechaDate3, "Paritcular", "F") ,new Comparendo(2019904, fechaDate3, "Limite de velocidad", "Elctronico", "Camioneta", "Poficial", "F", "CHAPINERO", "BOGOTA", 38.98, 65.89));
			arbolComparendos3.put(new LlaveComparendo(fechaDate4, "Poficial", "A01"), new Comparendo(2087134, fechaDate4, "Manejar siendo menor de edad", "Papel y Lapiz", "Automovil", "Público", "F09", "BOSA", "BOGOTA", 423.87, 98.89));

		} 
		catch (Exception ex) 
		{
			System.out.println(ex);
		}
	}

	/**
	 * Prueba 1: Dar el comparendo con mayor object id dentro de todos los comparendos
	 */
	@Test
	void testDarObjectidMayor() 
	{
		setupEscenario1();
		modelo.darObjectidMayor();
		assertEquals(2938267, colaComparendos1.getFirst().getItem().getObjective());
		
		setupEscenario2();
		modelo.darObjectidMayor();
		assertEquals(2087134, colaPriridadComparendos2.max().getObjective());
	}

	/**
	 * Prueba 2: Dar comperndos de mayor gravedad segun los criterios establecidos
	 */
	@Test
	void testDarComparendosMayorGravedad() 
	{
		setupEscenario1();
		modelo.darComparendosMayorGravedad();
		assertEquals("Público", colaComparendos1.getFirst().getItem().getTipo_servi());
		
		setupEscenario2();
		modelo.darComparendosMayorGravedad();
		assertEquals("Público", colaPriridadComparendos2.max().getTipo_servi());
		assertEquals("Oficial", colaPriridadComparendos2.max().getTipo_servi());
	}

	/**
	 * Prueba 3: Cargar datos en un cola sencilla
	 */
	@Test
	void testCargarDatosCola() 
	{
		setupEscenario1();
		modelo.cargarDatosCola();
	}

	/**
	 * Prueba 4: Dar costo penalizaciones por los dias que han pasado
	 */
	@Test
	void testDarCostoPenalizacionesyDias() 
	{
		setupEscenario1();
		modelo.darCostoPenalizacionesyDias();
		
		setupEscenario2();
		modelo.darCostoPenalizacionesyDias();
		
		setupEscenario3();
		modelo.darCostoPenalizacionesyDias();
		
		setupEscenario4();
		modelo.darCostoPenalizacionesyDias();
	}

	/**
	 * Prueba 5: Dar arbol rojo y negro con la llaveComparendo 2
	 */
	@Test
	void testDarArbolRedBlackLlave2() 
	{
		setupEscenario4();
		modelo.darArbolRedBlackLlave2();
	}

	/**
	 * Prueba 6: Dar comparendos que se encuntren en el rango de fecha y localidad
	 */
	@Test
	void testDarComparendosRangoFechayLocalidad() 
	{
		setupEscenario1();
		modelo.darComparendosRangoFechayLocalidad("2018/10/09", "2018/12/03");
		
		setupEscenario2();
		modelo.darComparendosRangoFechayLocalidad("2018/03/09", "2018/05/23");
		
		setupEscenario3();
		modelo.darComparendosRangoFechayLocalidad("2018/07/09", "2018/07/12");
		
		setupEscenario4();
		modelo.darComparendosRangoFechayLocalidad("2018/01/09", "2018/10/13");
	}

	/**
	 * Prueba 7: Dar numero de comparendos dentro del margen de las fechas establecidas
	 */
	@Test
	void testDarNumeroComparendos() 
	{
		setupEscenario1();
		Comparendo[] comparendos = new Comparendo[100];
		Date pFecha1 = colaComparendos1.getFirst().getItem().getFecha_hora();
		Date pFecha2 = colaComparendos1.getLast().getItem().getFecha_hora();
		modelo.darNumeroComparendos(comparendos, pFecha1, pFecha2, 5);
		
		setupEscenario2();
		Comparendo[] comparendos1 = new Comparendo[100];
		Date pFecha3 = colaPriridadComparendos2.max().getFecha_hora();
		Date pFecha4 = colaPriridadComparendos2.max().getFecha_hora();
		modelo.darNumeroComparendos(comparendos1, pFecha3, pFecha4, 3);
		
		setupEscenario3();
		Comparendo[] comparendos2 = new Comparendo[100];
		Date pFecha5 = tablaComparendo3.get(new LlaveComparendo(pFecha1, "Publico", "C03")).getFecha_hora();
		Date pFecha6 = tablaComparendo3.get(new LlaveComparendo(pFecha2, "Particular", "I01")).getFecha_hora();
		modelo.darNumeroComparendos(comparendos2, pFecha5, pFecha6, 9);
		
		setupEscenario1();
		Comparendo[] comparendos3 = new Comparendo[100];
		Date pFecha7 = arbolComparendos3.get(new LlaveComparendo(pFecha3, "Publico", "C03")).getFecha_hora();
		Date pFecha8 = tablaComparendo3.get(new LlaveComparendo(pFecha4, "Particular", "I01")).getFecha_hora();
		modelo.darNumeroComparendos(comparendos3, pFecha7, pFecha8, 1);
	}

	/**
	 * Prueba 8: Dar comparendos procesados en el dia 
	 */
	@Test
	void testDarNumeroProcesados() 
	{
		setupEscenario1();
		Comparendo[] comparendos = new Comparendo[100];
		Date pFecha1 = colaComparendos1.getFirst().getItem().getFecha_hora();
		modelo.darNumeroProcesados(comparendos, pFecha1);
		
		setupEscenario2();
		Comparendo[] comparendos1 = new Comparendo[100];
		Date pFecha2 = colaPriridadComparendos2.max().getFecha_hora();
		modelo.darNumeroProcesados(comparendos1, pFecha2);
		
		setupEscenario3();
		Comparendo[] comparendos2 = new Comparendo[100];
		Date pFecha3 = tablaComparendo3.get(new LlaveComparendo(pFecha1, "Publico", "C03")).getFecha_hora();
		modelo.darNumeroProcesados(comparendos2, pFecha3);
		
		setupEscenario1();
		Comparendo[] comparendos3 = new Comparendo[100];
		Date pFecha4 = arbolComparendos3.get(new LlaveComparendo(pFecha1, "Publico", "C03")).getFecha_hora();
		modelo.darNumeroProcesados(comparendos3, pFecha4);
	}
}