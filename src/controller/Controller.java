package controller;

import java.util.Comparator;
import java.util.Scanner;

import model.ComparadorTipoServicio;
import model.Comparendo;
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

	public void run() 
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
				view.printMessage("\nLectura de comparendorendos: ");
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
			
		    // Requerimiento 1B
			case 3:
				break;
				
			// Requerimiento 1C
			case 4:
				break;
			
			// Requerimiento 2A
			case 5:
				break;
			
			// Requerimiento 2B
			case 6:
				break;
				
			// Requerimiento 2C
			case 7:
				break;
			
			// Requerimiento 3A
			case 8:
				break;
			
			// Requerimiento 3B
			case 9:
				break;
			
			// Requerimiento 3C
			case 10:
				break;
			
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
