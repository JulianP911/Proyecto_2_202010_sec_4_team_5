package model;

import java.util.Date;

/**
 * Clase LlaveComparendo que se basa por la fecha
 * @author Julian Padilla - Pablo Pastrana
 */
public final class LlaveComparendo2 implements Comparable<LlaveComparendo2>
{
	/**
	 * Fecha_Hora del comparendo
	 */
	private Date fecha_Hora;

	// Metodo Constructor
	
	/**
	 * Metodo constructor de la llave del comparendo
	 */
	public LlaveComparendo2(Date pFecha_Hora) 
	{
		fecha_Hora = pFecha_Hora;
	}
	
	/**
	 * Retorna la localidad del comparendo
	 * @return Localidad
	 */
	public Date getFecha_Hora()
	{
		return fecha_Hora;
	}

	/**
	 * CompareTo para verificar si estan organizados por la fecha
	 */
	@Override
	public int compareTo(LlaveComparendo2 pObjeto) 
	{
		int resultado = 0;
		
		if(this.getFecha_Hora().after(pObjeto.getFecha_Hora()))
		{
			resultado = 1;
		}
		else if(this.getFecha_Hora().equals(pObjeto.getFecha_Hora()))
		{
			resultado = 0;
		}
		else if(this.getFecha_Hora().before(pObjeto.getFecha_Hora()))
		{
			resultado = -1;
		}

		return resultado;
	}
}