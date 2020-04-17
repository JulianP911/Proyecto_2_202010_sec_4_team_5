package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import model.Comparendo;
import model.LlaveComparendo;
import model.LlaveComparendo2;
import model.data_structures.LinearProbingHash;
import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;
import model.data_structures.RedBlackBST;
import model.data_structures.SeparteChainingHash2;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo 
{
	public static String PATH = "./data/comparendos_DEI_2018_Bogotá_D.C_50000_.geojson";
	//	public static String PATH = "./data/comparendos_DEI_2018_Bogotá_D.C.geojson";

	/**
	 * Lista de comparendos
	 */
	private List<Comparendo> datos1;

	/**
	 * Metodo que hace la carga de los datos comparendos
	 * @return Una lista con los comparendos leidos
	 */
	public List<Comparendo> cargarDatos() 
	{
		List<Comparendo> datos = new ArrayList<Comparendo>();

		JsonReader reader;
		try 
		{
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			for(JsonElement e: e2) 
			{
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();
				String cambio1 = s.replaceFirst("T", " ");
				String cambio2 = cambio1.replaceAll("Z", "");
				Date FECHA_HORA = parser.parse(cambio2); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRACCION").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();
				String MUNICIPIO = e.getAsJsonObject().get("properties").getAsJsonObject().get("MUNICIPIO").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo nuevo = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, MUNICIPIO, longitud, latitud);
				datos.add(nuevo);
			}

		} 
		catch (FileNotFoundException | ParseException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return datos;			
	}

	/**
	 * Convierte la lista de objetos cargados a ColaCP
	 */
	public MaxColaCP<Comparendo> darColaPrioridadMaxCP()
	{
		MaxColaCP<Comparendo> colaPrioridad = new MaxColaCP<Comparendo>();
		datos1 = cargarDatos();
		shuffle(datos1);

		Iterator<Comparendo> it = datos1.iterator();
		while(it.hasNext())
		{
			for(int i = 0; i < datos1.size(); i++)
			{
				Comparendo elementoActual = it.next();
				colaPrioridad.insert(new Comparendo(elementoActual.getObjective(), elementoActual.getFecha_hora(), elementoActual.getDes_infrac(), elementoActual.getMedio_dete(), elementoActual.getClase_vehi(), elementoActual.getTipo_servi(), elementoActual.getInfraccion(), elementoActual.getLocalidad(), elementoActual.getMunicipio(), elementoActual.getLongitud(), elementoActual.getLatitud()));
			}
		}

		return colaPrioridad;
	}

	/**
	 * Convierte la lista de objetos cargados a HeapCP
	 */
	public MaxHeapCP<Comparendo> darMaxHeapCP()
	{
		MaxHeapCP<Comparendo> colaPrioridad = new MaxHeapCP<Comparendo>();
		datos1 = cargarDatos();
		shuffle(datos1);

		Iterator<Comparendo> it = datos1.iterator();
		while(it.hasNext())
		{
			for(int i = 0; i < datos1.size(); i++)
			{
				Comparendo elementoActual = it.next();
				colaPrioridad.insert(new Comparendo(elementoActual.getObjective(), elementoActual.getFecha_hora(), elementoActual.getDes_infrac(), elementoActual.getMedio_dete(), elementoActual.getClase_vehi(), elementoActual.getTipo_servi(), elementoActual.getInfraccion(), elementoActual.getLocalidad(), elementoActual.getMunicipio(), elementoActual.getLongitud(), elementoActual.getLatitud()));
			}
		}

		return colaPrioridad;
	}

	/**
	 * Desorganiza la lista y la vuelve totalmente en desorden utilizando Random
	 * @param list Lista de comparendos
	 */
	public static void shuffle(List<Comparendo> list) 
	{
		Random random = new Random(); 
		int count = list.size() - 1;
		for (int i = count; i > 1; i--) 
		{
			Collections.swap(list, i, random.nextInt(i));
		} 
	}

	/**
	 * Muestra la informacion con el mayor OBJECTID encontrado en la cola de prioridad MaxCP
	 * @return El comparendo con mayor objectid encontrado recorriendo la cola de prioridad MaxCP
	 */
	public String darObjectidMayor()
	{
		String mensaje = " ";
		Comparendo actual = datos1.get(0);

		Iterator<Comparendo> it = darColaPrioridadMaxCP().iterator();
		while(it.hasNext())
		{
			Comparendo elemento = it.next();
			if(elemento.getObjective() > actual.getObjective())
			{
				actual = elemento;
			}
		}

		mensaje = actual.getObjective() + ", " + actual.getFecha_hora() + ", " + actual.getInfraccion() + ", " + 
				actual.getClase_vehi() + ", " + actual.getTipo_servi() + ", " +  actual.getLocalidad() + ", " +
				actual.getMunicipio();

		return mensaje;
	}

	/**
	 * Da un arreglo de comparendos que se sometio a una cola de prioridad teneindo en cuenta los parametros
	 * @return Un arreglo con comparendos de mayor gravedad
	 */
	public Comparable<Comparendo>[] darComparendosMayorGravedad()
	{
		MaxColaCP<Comparendo> colaPrioridad = darColaPrioridadMaxCP();
		MaxColaCP<Comparendo> colaMayorGravedad = new MaxColaCP<>();

		Iterator<Comparendo> it1 = colaPrioridad.iterator();
		while(it1.hasNext())
		{
			Comparendo elemento = it1.next();
			if(elemento.getTipo_servi().equals("Público"))
			{
				colaMayorGravedad.insert(elemento);
			}
			else if(elemento.getTipo_servi().equals("Oficial"))
			{
				Comparendo actual = new Comparendo(elemento.getObjective(), elemento.getFecha_hora(), elemento.getDes_infrac(), elemento.getMedio_dete(), elemento.getClase_vehi(), "Poficial", elemento.getInfraccion(), elemento.getLocalidad(), elemento.getMunicipio(), elemento.getLongitud(), elemento.getLatitud());
				colaMayorGravedad.insert(actual);
			}

			else if(elemento.getTipo_servi().equals("Particular"))
			{
				colaMayorGravedad.insert(elemento);
			}
		}

		@SuppressWarnings("unchecked")
		Comparable<Comparendo>[] nuevo = (Comparable<Comparendo>[]) new Comparable[datos1.size()];

		int j = 0;
		Iterator<Comparendo> it = colaMayorGravedad.iterator();
		while(it.hasNext())
		{
			for(int i = 0; i < datos1.size(); i++)
			{
				Comparendo elementoActual = it.next();

				nuevo[j] = new Comparendo(elementoActual.getObjective(), elementoActual.getFecha_hora(), elementoActual.getDes_infrac(), elementoActual.getMedio_dete(), elementoActual.getClase_vehi(), elementoActual.getTipo_servi(), elementoActual.getInfraccion(), elementoActual.getLocalidad(), elementoActual.getMunicipio(), elementoActual.getLongitud(), elementoActual.getLatitud());
				j++;
			}
		}

		return nuevo;
	}
	
	/**
	 * Convierte la lista de objetos cargados en una tabla de hash - Linear Probing
	 */
	public LinearProbingHash<LlaveComparendo, Comparendo> darTablaHashLinearProbing()
	{
		LinearProbingHash<LlaveComparendo, Comparendo> tablaLinearProbing = new LinearProbingHash<LlaveComparendo, Comparendo>();
		datos1 = cargarDatos();

		Iterator<Comparendo> it = datos1.iterator();
		while(it.hasNext())
		{
			for(int i = 0; i < datos1.size(); i++)
			{
				Comparendo elementoActual = it.next();
				tablaLinearProbing.put(new LlaveComparendo(elementoActual.getFecha_hora(), elementoActual.getClase_vehi(), elementoActual.getInfraccion()), new Comparendo(elementoActual.getObjective(), elementoActual.getFecha_hora(), elementoActual.getDes_infrac(), elementoActual.getMedio_dete(), elementoActual.getClase_vehi(), elementoActual.getTipo_servi(), elementoActual.getInfraccion(), elementoActual.getLocalidad(), elementoActual.getMunicipio(), elementoActual.getLongitud(), elementoActual.getLatitud()));
			}
		}

		return tablaLinearProbing;
	}
	
	/**
	 * Se utiliza una tabla de hash con encademaniento sencillo se recorre se obtiene la llava del dia y el valor correspondiente
	 * @param pMes Mes que ingresa el usuario como parametro
	 * @param pDia Dia que ingresa el usuario como parametro
	 * @return Tabla de hash con los valores guardados respectivamente
	 */
	public SeparteChainingHash2<String, Comparendo> darComparendosPorDia(int pMes, String pDia)
	{
		Calendar calendar = Calendar.getInstance();
		LinearProbingHash<LlaveComparendo, Comparendo> tablaLinearProbing = darTablaHashLinearProbing();
		SeparteChainingHash2<String, Comparendo> tablaSeparateChaining = new SeparteChainingHash2<String, Comparendo>();
		
		Iterator<Comparendo> it = tablaLinearProbing.values().iterator();
		while(it.hasNext())
		{
			Comparendo elementoActual = it.next();
			calendar.setTime(elementoActual.getFecha_hora());
			int dayofWeek = calendar.get(Calendar.DAY_OF_WEEK);
			int month1 = calendar.get(Calendar.MONTH);
			
			if(pDia.equals("L") && dayofWeek == 2)
			{
				if(month1 == 0 && pMes == 1)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
				else if(month1 == 1 && pMes == 2)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
				else if(month1 == 2 && pMes == 3)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
				else if(month1 == 3 && pMes == 4)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
				else if(month1 == 4 && pMes == 5)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
				else if(month1 == 5 && pMes == 6)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
				else if(month1 == 6 && pMes == 7)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
				else if(month1 == 7 && pMes == 8)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
				else if(month1 == 8 && pMes == 9)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
				else if(month1 == 9 && pMes == 10)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
				else if(month1 == 10 && pMes == 11)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
				else if(month1 == 11 && pMes == 12)
				{
					tablaSeparateChaining.put("L", elementoActual);
				}
			}
			else if(pDia.equals("M") && dayofWeek == 3)
			{
				if(month1 == 0 && pMes == 1)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
				else if(month1 == 1 && pMes == 2)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
				else if(month1 == 2 && pMes == 3)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
				else if(month1 == 3 && pMes == 4)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
				else if(month1 == 4 && pMes == 5)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
				else if(month1 == 5 && pMes == 6)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
				else if(month1 == 6 && pMes == 7)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
				else if(month1 == 7 && pMes == 8)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
				else if(month1 == 8 && pMes == 9)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
				else if(month1 == 9 && pMes == 10)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
				else if(month1 == 10 && pMes == 11)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
				else if(month1 == 11 && pMes == 12)
				{
					tablaSeparateChaining.put("M", elementoActual);
				}
			}
			else if(pDia.equals("I") && dayofWeek == 4)
			{
				if(month1 == 0 && pMes == 1)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
				else if(month1 == 1 && pMes == 2)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
				else if(month1 == 2 && pMes == 3)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
				else if(month1 == 3 && pMes == 4)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
				else if(month1 == 4 && pMes == 5)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
				else if(month1 == 5 && pMes == 6)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
				else if(month1 == 6 && pMes == 7)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
				else if(month1 == 7 && pMes == 8)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
				else if(month1 == 8 && pMes == 9)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
				else if(month1 == 9 && pMes == 10)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
				else if(month1 == 10 && pMes == 11)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
				else if(month1 == 11 && pMes == 12)
				{
					tablaSeparateChaining.put("I", elementoActual);
				}
			}
			else if(pDia.equals("J") && dayofWeek == 5)
			{
				if(month1 == 0 && pMes == 1)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
				else if(month1 == 1 && pMes == 2)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
				else if(month1 == 2 && pMes == 3)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
				else if(month1 == 3 && pMes == 4)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
				else if(month1 == 4 && pMes == 5)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
				else if(month1 == 5 && pMes == 6)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
				else if(month1 == 6 && pMes == 7)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
				else if(month1 == 7 && pMes == 8)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
				else if(month1 == 8 && pMes == 9)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
				else if(month1 == 9 && pMes == 10)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
				else if(month1 == 10 && pMes == 11)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
				else if(month1 == 11 && pMes == 12)
				{
					tablaSeparateChaining.put("J", elementoActual);
				}
			}
			else if(pDia.equals("V") && dayofWeek == 6)
			{
				if(month1 == 0 && pMes == 1)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
				else if(month1 == 1 && pMes == 2)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
				else if(month1 == 2 && pMes == 3)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
				else if(month1 == 3 && pMes == 4)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
				else if(month1 == 4 && pMes == 5)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
				else if(month1 == 5 && pMes == 6)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
				else if(month1 == 6 && pMes == 7)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
				else if(month1 == 7 && pMes == 8)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
				else if(month1 == 8 && pMes == 9)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
				else if(month1 == 9 && pMes == 10)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
				else if(month1 == 10 && pMes == 11)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
				else if(month1 == 11 && pMes == 12)
				{
					tablaSeparateChaining.put("V", elementoActual);
				}
			}
			else if(pDia.equals("S") && dayofWeek == 7)
			{
				if(month1 == 0 && pMes == 1)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
				else if(month1 == 1 && pMes == 2)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
				else if(month1 == 2 && pMes == 3)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
				else if(month1 == 3 && pMes == 4)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
				else if(month1 == 4 && pMes == 5)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
				else if(month1 == 5 && pMes == 6)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
				else if(month1 == 6 && pMes == 7)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
				else if(month1 == 7 && pMes == 8)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
				else if(month1 == 8 && pMes == 9)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
				else if(month1 == 9 && pMes == 10)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
				else if(month1 == 10 && pMes == 11)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
				else if(month1 == 11 && pMes == 12)
				{
					tablaSeparateChaining.put("S", elementoActual);
				}
			}
			else if(pDia.equals("D") && dayofWeek == 1)
			{
				if(month1 == 0 && pMes == 1)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
				else if(month1 == 1 && pMes == 2)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
				else if(month1 == 2 && pMes == 3)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
				else if(month1 == 3 && pMes == 4)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
				else if(month1 == 4 && pMes == 5)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
				else if(month1 == 5 && pMes == 6)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
				else if(month1 == 6 && pMes == 7)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
				else if(month1 == 7 && pMes == 8)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
				else if(month1 == 8 && pMes == 9)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
				else if(month1 == 9 && pMes == 10)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
				else if(month1 == 10 && pMes == 11)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
				else if(month1 == 11 && pMes == 12)
				{
					tablaSeparateChaining.put("D", elementoActual);
				}
			}
		}
		return tablaSeparateChaining;
	}
	
	/**
	 * Convierte la lista de objetos cargados a un arbol rojo - negro
	 */
	public RedBlackBST<LlaveComparendo2, Comparendo> darArbolRedBlackLlave2()
	{
		RedBlackBST<LlaveComparendo2, Comparendo> arbolRedBlack = new RedBlackBST<LlaveComparendo2, Comparendo>();
		datos1 = cargarDatos();

		Iterator<Comparendo> it = datos1.iterator();
		while(it.hasNext())
		{
			for(int i = 0; i < datos1.size(); i++)
			{
				Comparendo elementoActual = it.next();
				LlaveComparendo2 llaveActual = new LlaveComparendo2(elementoActual.getFecha_hora());
				Comparendo comparendoActual = new Comparendo(elementoActual.getObjective(), elementoActual.getFecha_hora(), elementoActual.getDes_infrac(), elementoActual.getMedio_dete(), elementoActual.getClase_vehi(), elementoActual.getTipo_servi(), elementoActual.getInfraccion(), elementoActual.getLocalidad(), elementoActual.getMunicipio(), elementoActual.getLongitud(), elementoActual.getLatitud());
				arbolRedBlack.put(llaveActual, comparendoActual);
			}
		}

		return arbolRedBlack;
	}
	
	/**
	 * Retorna un arbol rojo - negro con los comparendos que cumple con las fechas dentro de los rangos
	 * @param pFechaInf Fecha inferior ingresada por el usuario
	 * @param pFechaSup Fecha superior ingresa por el usuario
	 * @return Arbol ROjo-Negro con los comparendos
	 */
	public RedBlackBST<LlaveComparendo2, Comparendo> darComparendosRangoFechayLocalidad(String pFechaInf, String pFechaSup)
	{
		String fechaInf = pFechaInf.replace('-', ' ');
		String fechaSup = pFechaSup.replace('-', ' ');
		
		SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		RedBlackBST<LlaveComparendo2, Comparendo> rangoComparendosFecha = new RedBlackBST<LlaveComparendo2, Comparendo>();
		try 
		{
			Date fechaInf1 = parser.parse(fechaInf);
			Date fechaSup1 = parser.parse(fechaSup);
			
			Iterator<LlaveComparendo2> it1 = darArbolRedBlackLlave2().keys().iterator();
			Iterator<Comparendo> it2 = darArbolRedBlackLlave2().Values().iterator();
			
			while(it1.hasNext() && it2.hasNext())
			{
				LlaveComparendo2 llaveComparendo =  it1.next();
				Comparendo Comparendo = it2.next();
				
				if(llaveComparendo.getFecha_Hora().after(fechaInf1)  && llaveComparendo.getFecha_Hora().before(fechaSup1))
				{
					rangoComparendosFecha.put(llaveComparendo, Comparendo);
				}
			}
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		return rangoComparendosFecha;
	}

	// Metodos de Ordenamientos - MergeSort

	/**
	 * Ordena los elmenentos de acuerdo con el criterio de comparacion
	 * @param a Arreglo de comparendos
	 * @param comp Comparador por el cual se va a organizar
	 */
	public static <E> void sort(E[] a, Comparator<? super E> comp) 
	{
		mergeSort(a, 0, a.length - 1, comp);
	}

	/**
	 * Hace las fusiones de los sub arreglos ordenados para crear un arreglo totalmente ordenado
	 * @param a Arreglo de comparendos
	 * @param from Posicion inical del arreglo
	 * @param to Posicion final del arreglo
	 * @param comp Comparador por el cual se va a organizar
	 */
	private static <E> void mergeSort(E[] a, int from, int to, Comparator<? super E> comp) 
	{
		if (from == to)
			return;
		int mid = (from + to) / 2;
		// Sort the first and the second half
		mergeSort(a, from, mid, comp);
		mergeSort(a, mid + 1, to, comp);
		merge(a, from, mid, to, comp);
	}

	/**
	 * Fusion de los arreglos para ser utilizados por mergeSort
	 * @param a Arreglo de comparendos
	 * @param from Posicion inical del arreglo
	 * @param mid Posicion de la mitad del arreglo
	 * @param to Posicion final del arreglo
	 * @param comp Comparador por el cual se va a organizar
	 */
	@SuppressWarnings("unchecked")
	private static <E> void merge(E[] a, int from, int mid, int to, Comparator<? super E> comp) 
	{
		int n = to - from + 1;
		Object[] values = new Object[n];

		int fromValue = from;

		int middleValue = mid + 1;

		int index = 0;

		while (fromValue <= mid && middleValue <= to) {
			if (comp.compare(a[fromValue], a[middleValue]) < 0) {
				values[index] = a[fromValue];
				fromValue++;
			} else {
				values[index] = a[middleValue];
				middleValue++;
			}
			index++;
		}

		while (fromValue <= mid) {
			values[index] = a[fromValue];
			fromValue++;
			index++;
		}
		while (middleValue <= to) {
			values[index] = a[middleValue];
			middleValue++;
			index++;
		}

		for (index = 0; index < n; index++)
			a[from + index] = (E) values[index];
	}
}
