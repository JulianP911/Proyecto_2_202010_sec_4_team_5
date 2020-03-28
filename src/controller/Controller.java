package controller;

import java.util.Scanner;
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
			case 1:
				long start1 = System.currentTimeMillis();
				modelo.darColaPrioridadMaxCP();
				long end1 = System.currentTimeMillis();

				long start2 = System.currentTimeMillis();
				// TODO Concetar con el metodo con MaxHeapCP
				long end2 = System.currentTimeMillis();

				view.printMessage("El numero de comparendos es de: " + modelo.darColaPrioridadMaxCP().size() );
				view.printMessage("Tiempo de carga (seg) en MaxColaCP: " + (end1-start1)/1000.0);
				view.printMessage("Tiempo de carga (seg) en MaxHeapCP: " + (end2-start2)/1000.0);
				break;

			case 2:
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
