package aula02_PPI;

import java.io.Serializable;

public class Pais implements Serializable 
{
	private static final long serialVersionUID = 1L;
	int ID;
	String Nome;
	long Populacao;
	double Area;
	
	//Inicio Getters e Setters
	public int getID() 
	{
		return ID;
	}

	public void setID(int ID)
	{
		this.ID = ID;
	}

	public String getNome() 
	{
		return Nome;
	}

	public void setNome(String Nome) 
	{
		this.Nome = Nome;
	}

	public long getPopulacao()
	{
		return Populacao;
	}

	public void setPopulacao(long populacao) 
	{
		this.Populacao = populacao;
	}

	public double getArea() 
	{
		return Area;
	}

	public void setArea(double area) 
	{
		this.Area = area;
	}
	//Fim Getters e Setters
	
	@Override
	public String toString() {
		return "[ID=" + ID + ", Nome=" + Nome + ", Populacao=" + Populacao + ", Area=" + Area + "]";
	}
}
