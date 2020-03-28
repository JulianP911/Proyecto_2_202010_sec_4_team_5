package model;

import java.util.Date;
import java.util.Objects;

/**
 * Clase LlaveComparendo que tiene los tres atributos mencionados en el taller
 * @author Julian Padilla - Pablo Pastrana
 */
public final class LlaveComparendo implements Comparable<LlaveComparendo>
{
	/**
	 * Fecha en la cual fue tomado el comparendo
	 */
	private Date fecha_hora;

	/**
	 * Clase de vehiculo al cual se le aplico el comparendo
	 */
	private  String clase_vehi;

	/**
	 * Infraccion impuesta al vehiculo
	 */
	private String infraccion;
	
	// Metodo Constructor
	
	public LlaveComparendo(Date pFecha, String pClase_Vehi, String pInfraccion) 
	{
		fecha_hora = pFecha;
		clase_vehi = pClase_Vehi;
		infraccion = pInfraccion;
	}
	
	public Date getFecha_Hora()
	{
		return fecha_hora;
	}
	
	public String getClase_Vehi()
	{
		return clase_vehi;
	}
	
	public String getInfraccion()
	{
		return infraccion;
	}

	/**
	 * HashCode para ser utilisado en la tabla de hash
	 */
	@Override
	public int hashCode()
	{
		int hash = 17;
		hash = 31 * hash + fecha_hora.hashCode();
		hash = 31 * hash + clase_vehi.hashCode();
		hash = 31 * hash + infraccion.hashCode();
		return hash;
	}

	/**
	 * Equals para verificar si dos llaves son iguales o no
	 */
	public boolean equals(LlaveComparendo pLLaveComparendo) 
	{
		if (this == pLLaveComparendo) 
		{
			return true;
		}
		if (pLLaveComparendo == null) 
		{
			return false;
		}
		if (getClass() != pLLaveComparendo.getClass()) 
		{
			return false;
		}
		final LlaveComparendo other = (LlaveComparendo) pLLaveComparendo;
		if (!Objects.equals(this.fecha_hora, other.fecha_hora)) 
		{
			return false;
		}
		if (!Objects.equals(this.clase_vehi, other.clase_vehi)) 
		{
			return false;
		}
		if (!Objects.equals(this.infraccion, other.infraccion)) 
		{
			return false;
		}
		return true;
	}

	/**
	 * CompareTo para verificar si estan organisados
	 */
	@Override
	public int compareTo(LlaveComparendo pObjeto) 
	{
		int resultado = 0;
		
		if(this.fecha_hora.after(pObjeto.fecha_hora))
		{
			resultado = -1;
		}
		else if(this.fecha_hora.equals(pObjeto.fecha_hora))
		{
			resultado = 0;
		}
		else if(this.fecha_hora.before(pObjeto.fecha_hora))
		{
			resultado = 1;
		}

		return resultado;
	}
}
