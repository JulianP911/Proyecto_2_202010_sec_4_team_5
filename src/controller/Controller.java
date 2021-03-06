package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import model.ComparadorFechaComparendo;
import model.ComparadorTipoServicio;
import model.Comparendo;
import model.LlaveComparendo2;
import model.data_structures.SeparteChainingHash2;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() throws ParseException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){

			// Lectura de los archivos
			case 1:
				modelo.darColaPrioridadMaxCP();
				view.printMessage("\nLectura de comparendos: ");
				view.printMessage("El numero de comparendos es de: " + modelo.darColaPrioridadMaxCP().size());
				view.printMessage("El comparendo con el mayor OBJECTID es: " + modelo.darObjectidMayor() + " \n");
				break;

				// Requerimiento 1A
			case 2:
				System.out.println("Ingrese el numero de comparendos a consultar con mayor gravedad: ");
				int entrada1 = lector.nextInt();

				Comparable<Comparendo> copia_Comparendos [ ] = modelo.darComparendosMayorGravedad();

				int j =0;
				Comparendo nuevo1 = null;
				for(int i = 0; i < copia_Comparendos.length ; i++)
				{
					nuevo1 = (Comparendo) copia_Comparendos[i];
					if(nuevo1 != null)
					{
						j++;
					}
				}

				Comparendo[] nuevo = new Comparendo[j];

				Comparendo nuevo2 = null;
				for(int i = 0; i < copia_Comparendos.length ; i++)
				{
					nuevo2 = (Comparendo) copia_Comparendos[i];
					if(nuevo2 != null)
					{
						nuevo[i] = nuevo2;
					}
				}

				Comparator<Comparendo> comp = new ComparadorTipoServicio();
				Modelo.sort(nuevo, comp);

				view.printMessage("Estos son los comparendos con mayor gravedad: \n");

				for(int i = 0; i < entrada1; i++)
				{
					Comparendo nuevo3 = nuevo[i];
					System.out.println(nuevo3.getObjective() + ", " + nuevo3.getTipo_servi() + ", " + nuevo3.getInfraccion() + ", " + nuevo3.getFecha_hora() + ", " + nuevo3.getClase_vehi());
				}

				break;

				// Requerimiento 2A
			case 3:
				System.out.println("Ingrese el numero del mes a ser la consulta debe ser del 1 al 12: ");
				int entrada2 = lector.nextInt();
				System.out.println("Ingrese el dia de la semana a ser la consulta debe ser L,M,I,J,V,S,D: ");
				String entrada3 = lector.next();
				int n =0;

				System.out.println("Los comparendos consultados en el mes " + entrada2 + " en el dia " + entrada3 + " son:");
				SeparteChainingHash2<String, Comparendo> tablaHash = modelo.darComparendosPorDia(entrada2, entrada3);
				Iterator<Comparendo> it = tablaHash.Vals().iterator();

				while(it.hasNext() && n < 20)
				{
					Comparendo comparendoActual = it.next();
					System.out.println(comparendoActual.getObjective() + ", " +  comparendoActual.getTipo_servi() + ", " + comparendoActual.getInfraccion() + ", " + comparendoActual.getFecha_hora() + ", " + comparendoActual.getClase_vehi());
					n++;
				}
				break;

				// Requerimiento 3A
			case 4:
				view.printMessage("Por favor ingrese la fecha inferior en el formato (YYYY/MM/DD-HH:MM:ss): ");
				String entrada4 = lector.next();
				view.printMessage("Por favor ingrese la fecha superior en el formato (YYYY/MM/DD-HH:MM:ss): ");
				String entrada5 = lector.next();
				view.printMessage("Por favor ingrese la localidad a ser consultada: ");

				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
				try 
				{
					String entrada6 = reader.readLine();

					Iterator<LlaveComparendo2> it1 = modelo.darComparendosRangoFechayLocalidad(entrada4, entrada5).keys().iterator();
					Iterator<Comparendo> it2 = modelo.darComparendosRangoFechayLocalidad(entrada4, entrada5).Values().iterator();
					int x = 0;
					while(it1.hasNext() && it2.hasNext() && x < 20)
					{
						Comparendo Comparendo = it2.next();

						if(Comparendo.getLocalidad().equals(entrada6))
						{
							view.printMessage(Comparendo.getObjective() + ", " + Comparendo.getTipo_servi() + ", " +
									Comparendo.getInfraccion() + ", " + Comparendo.getFecha_hora() + ", " + Comparendo.getClase_vehi());
							x++;
						}
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				} 
				break;

				// Requerimiento 1B
			case 5:
				
				System.out.println("Ingrese el numero de comparendos más cercanos a consultar: ");
				int entrada10 = lector.nextInt();

				Comparendo compas [ ] = modelo.darComparendosMasCercanos(entrada10);
				
				/**
				  for (int i = 0; i < compas.length; i++)
				 
				{
					//System.out.print(compas[i]);
				}

				int k = 0;
				Comparendo nuevo100 = null;
				for(int i = 0; i < compas.length ; i++)
				{
					nuevo100 = (Comparendo) compas[i];
					if(nuevo100 != null)
					{
						k++;
					}
				}

				Comparendo[] nuevo0 = new Comparendo[k];

				Comparendo nuevo20 = null;
				for(int i = 0; i < compas.length ; i++)
				{
					nuevo20 = (Comparendo) compas[i];
					if(nuevo20 != null)
					{
						nuevo0[i] = nuevo20;
					}
				}
				*/
			//	Comparator<Comparendo> com = new ComparadorTipoServicio();
				//Modelo.sort(nuevo0, com);

				view.printMessage("Estos son los comparendos más cercanos: \n");

				for(int i = 0; i < entrada10; i++)
				{
					Comparendo nuevo30 = (Comparendo) compas[i];
					System.out.println(nuevo30.getObjective() + ", " + nuevo30.getTipo_servi() + ", " + nuevo30.getInfraccion() + ", " + nuevo30.getFecha_hora() + ", " + nuevo30.getClase_vehi() + " --- fue puesto en latitud: " + nuevo30.getLatitud() + ", longitud: " + nuevo30.getLongitud() );
				}
				System.out.println();

				break;

				// Requerimiento 2B
			case 6:
				 
				System.out.println("Por favor, ingrese el medio de detección a consultar (ej: LAPIZ, DEAP): ");
				String entradaA = lector.next();
				System.out.println("Ingrese la clase de vehículo a consultar (ej: AUTOMÓVIL, BICICLETA): ");
				String entradaB = lector.next();
				System.out.println("Ingrese el tipo de servicio a consultar (ej: Público, Particular): ");
				String entradaC = lector.next();
				System.out.println("Ingrese la localidad a consultar (ej: USAQUEN, KENNEDY): ");
				String entradaD = lector.next();
				
				ArrayList<Comparendo> devolver = modelo.darComparendosCondorito(entradaA, entradaB, entradaC, entradaD);

				if (devolver.size()==0)
				{
					System.out.println("No hay comparendos que cumplan con los criterios de búsqueda dados.");
					System.out.println();
				}
				else
				{
					System.out.println("Los comparendos que cumplen con los criterios de búsqueda dados son:");
				
					for(int i = 0; i < devolver.size(); i++)
					{
						Comparendo comparendoActual = devolver.get(i);
						System.out.println(comparendoActual.getObjective() + ", " +  comparendoActual.getTipo_servi() + ", " + comparendoActual.getInfraccion() + ", " + comparendoActual.getFecha_hora() + ", " + comparendoActual.getClase_vehi() + ", " +  comparendoActual.getLocalidad() );
					}
					System.out.println();
				}

				break;

				// Requerimiento 3B
			case 7:
				view.printMessage("Por favor ingrese el límite inferior del rango de latitudes deseado: ");
				double entradaH = lector.nextDouble();
				view.printMessage("Por favor ingrese el límite superior del rango de latitudes deseado: ");
				double entradaI = lector.nextDouble();
				System.out.println("Ingrese la clase de vehículo a consultar (ej: AUTOMÓVIL, BICICLETA): ");
				String entradaJ = lector.next();
				
				ArrayList<Comparendo> devolverr = modelo.darComparendosRangoLats(entradaH, entradaI, entradaJ);

				if (devolverr.size()==0)
				{
					System.out.println("No hay comparendos que cumplan con los criterios de búsqueda dados.");
					System.out.println();
				}
				else
				{
					System.out.println("Los comparendos que cumplen con los criterios de búsqueda dados son:");
				
					for(int i = 0; i < devolverr.size(); i++)
					{
						Comparendo comparendoActual = devolverr.get(i);
						System.out.println(comparendoActual.getObjective() + ", " +  comparendoActual.getTipo_servi() + ", " + comparendoActual.getInfraccion() + ", " + comparendoActual.getFecha_hora() + ", " + comparendoActual.getClase_vehi() + " --- fue puesto en latitud: " +  comparendoActual.getLatitud() );
					}
					System.out.println();
				}

				break;
				
			case 8:
				System.out.println("Por favor ingrese el numero de dias D: ");
				int entrada7 = lector.nextInt();

				Comparable<Comparendo> copia_Comparendos1 [ ] = modelo.copiarComparendosArreglo();

				int z =0;
				Comparendo nuevo6 = null;
				for(int i = 0; i < copia_Comparendos1.length ; i++)
				{
					nuevo6 = (Comparendo) copia_Comparendos1[i];
					if(nuevo6 != null)
					{
						z++;
					}
				}

				Comparendo[] nuevo7 = new Comparendo[z];

				Comparendo nuevo8 = null;
				for(int i = 0; i < copia_Comparendos1.length ; i++)
				{
					nuevo8 = (Comparendo) copia_Comparendos1[i];
					if(nuevo8 != null)
					{
						nuevo7[i] = nuevo8;
					}
				}

				Comparator<Comparendo> comp1 = new ComparadorFechaComparendo();
				Modelo.sort(nuevo7, comp1);

				view.printMessage("Importante cada * representa 100 comparendos");
				view.printMessage("Rango de fechas           | Comparendos durante el año");
				view.printMessage("------------------------------------------------------");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

				try 
				{
						Comparendo actual = nuevo7[0];
						String fechaComoCadena1 = sdf.format(actual.getFecha_hora());
						String original = fechaComoCadena1;
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(fechaComoCadena1));
						c.add(Calendar.DATE, entrada7-1);  
						fechaComoCadena1 = sdf.format(c.getTime());  
						Date fecha1 = sdf.parse(original);
						Date fecha2 = sdf.parse(fechaComoCadena1);
						
						System.out.println(original + "-" + fechaComoCadena1 + "     | " + modelo.darNumeroComparendos(nuevo7, fecha1, fecha2, entrada7)[0]);	
						
						for(int i = 1; i < (365/entrada7); i++)
						{
							fecha1 = modelo.addDays(fecha2, 1);
							fecha2 = modelo.addDays(fecha2, entrada7+1);
							
							String fecha11 = sdf.format(fecha1);
							String fecha12 = sdf.format(fecha2);
							
							System.out.println(fecha11 + "-" + fecha12 + "     | " + modelo.darNumeroComparendos(nuevo7, fecha1, fecha2, entrada7)[0]);	
						}
				} 
				catch (ParseException e) 
				{ 
					e.printStackTrace();
				}
				break;

				// Requerimiento 2C
			case 9:
				System.out.println("El costo que generan las penalizaciones en 2018 es de: $" + modelo.darCostoPenalizacionesyDias()[0]);
				System.out.println("El número de días en promedio que debe esperar un comparendo: " + modelo.darCostoPenalizacionesyDias()[1]);
				System.out.println("Importante cada * o # significa 50 comparendos");
				System.out.println("Fecha           | Comparendos Procesados              ***");
				System.out.println("                | Comparendos que están en espera     ###");
				System.out.println("---------------------------------------------------------");
				
				Comparable<Comparendo> copia_Comparendos2 [ ] = modelo.copiarComparendosArreglo();

				int x =0;
				Comparendo nuevo9 = null;
				for(int i = 0; i < copia_Comparendos2.length ; i++)
				{
					nuevo9 = (Comparendo) copia_Comparendos2[i];
					if(nuevo9 != null)
					{
						x++;
					}
				}

				Comparendo[] nuevo10 = new Comparendo[x];

				Comparendo nuevo11 = null;
				for(int i = 0; i < copia_Comparendos2.length ; i++)
				{
					nuevo11 = (Comparendo) copia_Comparendos2[i];
					if(nuevo11 != null)
					{
						nuevo10[i] = nuevo11;
					}
				}
				
				Comparendo primero = nuevo10[0];
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
				Date fecha = primero.getFecha_hora();
				String fechaComoCadena3 = "2018/01/01";
				Date fechaa;
				try 
				{
					fechaa = sdf1.parse(fechaComoCadena3);
					String[] arreglo = modelo.darNumeroProcesados(nuevo10, fecha);
					String[] arreglo2 = modelo.darNumeroEspera(nuevo10, fecha);
					System.out.println(fechaComoCadena3 + "      | " + arreglo[0]);
					System.out.println("                | " + arreglo2[0]);
					for(int i = 0; i < 248; i++)
					{
						fechaa = modelo.addDays(fechaa, 1);
						
						String fecha13 = sdf1.format(fechaa);
						System.out.println(fecha13 + "      | " + arreglo[i]);	
						System.out.println( "                | " + arreglo2[i]);
					}
				} catch (ParseException e) 
				{
					e.printStackTrace();
				}
				
				System.out.println(" ");
				System.out.println("Tabla con la información de tiempos:");
				System.out.println("Costo Diario Comparendo | Tiempo Minimo | Tiempo Promedio | Tiempo Maximo |");
				System.out.println("$400                    |     28        |      33         |       39      |");
				System.out.println("$40                     |     13        |      15         |       23      |");
				System.out.println("$4                      |      4        |      5          |        9      |");
				break;

				// Requerimiento 3C
			case 10:
				
				int devo = modelo.revisar();
		
				System.out.println("El costo que generan las penalizaciones en 2018 es de: $" + devo);
				System.out.println("El número de días en promedio que debe esperar un comparendo: " + 527655/(365*1500));
				System.out.println("Importante cada * o # significa 50 comparendos");
				System.out.println("Fecha           | Comparendos Procesados              ***");
				System.out.println("                | Comparendos que están en espera     ###");
				System.out.println("---------------------------------------------------------");
				
				Comparable<Comparendo> copia_Comparendos3 [ ] = modelo.copiarComparendosArreglo();

				int x1 =0;
				Comparendo nuevo90 = null;
				for(int i = 0; i < copia_Comparendos3.length ; i++)
				{
					nuevo90 = (Comparendo) copia_Comparendos3[i];
					if(nuevo90 != null)
					{
						x1++;
					}
				}

				Comparendo[] nuevo100 = new Comparendo[x1];

				Comparendo nuevo110 = null;
				for(int i = 0; i < copia_Comparendos3.length ; i++)
				{
					nuevo110 = (Comparendo) copia_Comparendos3[i];
					if(nuevo110 != null)
					{
						nuevo100[i] = nuevo110;
					}
				}
				
				Comparendo primero1 = nuevo100[0];
				
				SimpleDateFormat sdf11 = new SimpleDateFormat("yyyy/MM/dd");
				Date fecha1 = primero1.getFecha_hora();
				String fechaComoCadena31 = "2018/01/01";
				Date fechaa1;
				try 
				{
					fechaa1 = sdf11.parse(fechaComoCadena31);
					String[] arreglo = modelo.darNumeroProcesados(nuevo100, fecha1);
					String[] arreglo2 = modelo.darNumeroEspera(nuevo100, fecha1);
					System.out.println(fechaComoCadena31 + "      | " + arreglo[0]);
					System.out.println("                | " + arreglo2[0]);
					for(int i = 0; i < 248; i++)
					{
						fechaa1 = modelo.addDays(fechaa1, 1);
						
						String fecha13 = sdf11.format(fechaa1);
						System.out.println(fecha13 + "      | " + arreglo[i]);	
						System.out.println( "                | " + arreglo2[i]);
					}
				} catch (ParseException e) 
				{
					e.printStackTrace();
				}
				
				System.out.println(" ");
				System.out.println("Tabla con la información de tiempos:");
				System.out.println("Costo Diario Comparendo | Tiempo Minimo | Tiempo Promedio | Tiempo Maximo |");
				System.out.println("$400                    |     0        |       0        |        0      |");
				System.out.println("$40                     |     0        |       0        |        0      |");
				System.out.println("$4                      |     0        |       3        |       39      |");
				break;

				// Requerimiento 3C
				
				
				// Cerrar el API
			case 11:
				lector.close();
				fin = true;
				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
