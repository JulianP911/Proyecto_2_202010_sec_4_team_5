package model;

import java.util.Comparator;

/**
 * Clase comparador de fecha
 * @author Julian Padilla Molina - Pablo Pastrana
 * Implementa Comparator de tipo comparendo
 */
public class ComparadorFechaComparendo implements Comparator<Comparendo>
{

	@Override
	public int compare(Comparendo p1, Comparendo p2) 
	{
		int resultado = 0;

		if(p1.getFecha_hora().after(p2.getFecha_hora()))
		{
			resultado = 1;
		}
		else if(p1.getFecha_hora().equals(p2.getFecha_hora()))
		{
			resultado = 0;
		}
		else if(p1.getFecha_hora().before(p2.getFecha_hora()))
		{
			resultado = -1;
		}

		return resultado;
	}


}
