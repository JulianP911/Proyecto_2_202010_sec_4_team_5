package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
	    /**
	     * Imprime los mensajes iniciales que se le muestran al usuario
	     */
		public void printMenu()
		{
			System.out.println("Proyecto_2_202010_sec_4_team_5");
			System.out.println("1. Lectura/carga de los comparendos del 2018 (formato JSON) en el formato actualizado.");
			System.out.println("Requerimientos Parte A");
			System.out.println("2. 1A – Obtener los M comparendos con mayor gravedad");
			System.out.println("3. 2A – Buscar los comparendos por mes y día de la semana");
			System.out.println("4. 3A – Buscar los comparendos que tienen una fecha-hora en un rango y que son de una localidad dada");
			System.out.println("Requerimientos Parte B");
			System.out.println("5. 1B – Buscar los M comparendos más cercanos a la estación de policía");
			System.out.println("6. 2B – Buscar los comparendos por medio detección, clase de vehículo, tipo de servicio y localidad");
			System.out.println("7. 3B – Busca los comparendos que tienen una latitud en un rango dado y que involucraron un tipo de vehículo particular");
			System.out.println("Requerimientos Parte C");
			System.out.println("8. 1C – Visualizar Datos en una Tabla ASCII");
			System.out.println("9. 2C – El costo de los tiempos de espera hoy en día (Cola)");
			System.out.println("10. 3C – El costo de los tiempos de espera usando el nuevo sistema");
			System.out.println("11. Cerrar");
		}

		/**
		 * Imprime mensajes de tipo string
		 * @param mensaje Mensaje correspondiente
		 */
		public void printMessage(String mensaje) 
		{
			System.out.println(mensaje);
		}		
		
		/**
		 * Imprime mensajes de tipo modelo, sacando la informacion
		 * @param modelo Modelo clase
		 */
		public void printModelo(Modelo modelo)
		{
			System.out.println(modelo);
		}
}
