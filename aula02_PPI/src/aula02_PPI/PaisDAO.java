package aula02_PPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaisDAO 
{
	//----- C R E A T E -----
	public int create(Pais pais)
	{
		//Inserir dados
		String sqlInsert = "INSERT INTO pais(Nome, Populacao, Area) VALUES(?, ?, ?)";
		try(Connection connection = ConnectionFactory.obtemConexao(); 
			PreparedStatement statement = connection.prepareStatement(sqlInsert);) 
		{
			statement.setString(1, pais.getNome());
			statement.setLong(2, pais.getPopulacao());
			statement.setDouble(3, pais.getArea());
			statement.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement statement2 = connection.prepareStatement(sqlQuery);
				ResultSet resultSet = statement2.executeQuery();) 
			{
				if(resultSet.next())
				{
					pais.setID(resultSet.getInt(1));
				}
			} 
			catch (SQLException error) 
			{
				System.out.println("Erro com codigo MySQL - CREATE - Retorno do banco de dados"+error);
			}
		} 
		catch(ClassNotFoundException error)
		{
			System.out.println("Erro ao conectar ao banco - CREATE - Inserir dados"+error);
		}
		catch (SQLException error) 
		{
			System.out.println("Erro com codigo MySQL - CREATE - Inserir dados"+error);
		} 
		return pais.getID();
	}
	
	//----- R E A D -----
	public Pais read(int id)
	{
		Pais pais = new Pais();
		pais.setID(id);
		String sqlRead = "SELECT Nome, Populacao, Area FROM Pais WHERE Pais.ID = ?";
		//Pais pais = new Pais();
		
		try(Connection connection = ConnectionFactory.obtemConexao();
			PreparedStatement statement = connection.prepareStatement(sqlRead);)
		{
			statement.setInt(1, pais.getID());
			try(ResultSet resultSet = statement.executeQuery();)
			{
				if(resultSet.next())
				{
					pais.setNome(resultSet.getString("Nome"));
					pais.setPopulacao(resultSet.getLong("Populacao"));
					pais.setArea(resultSet.getDouble("Area"));
				}
			}
			catch (SQLException error) 
			{
				System.out.println("Erro com codigo MySQL - READ - ResultSet"+error);
			} 
			
		} 
		catch (ClassNotFoundException error)
		{
			System.out.println("Erro ao conectar ao banco - READ"+error);
		}
		catch (SQLException error)
		{
			System.out.println("Erro com codigo MySQL - READ"+error);
		}
		return pais;
	}
	
	//----- U P D A T E -----
	public void update(Pais pais)
	{
		String sqlUpdate = "UPDATE Pais SET Nome = ?, Populacao = ?, Area = ? WHERE ID = ?";
		try(Connection connection = ConnectionFactory.obtemConexao();
			PreparedStatement statement = connection.prepareStatement(sqlUpdate);) 
		{
			statement.setString(1, pais.getNome());
			statement.setLong(2, pais.getPopulacao());
			statement.setDouble(3, pais.getArea());
			statement.setInt(4, pais.getID());
			statement.execute();
		} 
		catch (ClassNotFoundException error) 
		{
			System.out.println("Erro ao conectar ao banco - UPDATE"+error);
		}
		catch (SQLException error) 
		{
			System.out.println("Erro com codigo MySQL - UPDATE"+error);
		} 
	}
	
	//----- D E L E T E -----
	public void delete(int id)
	{
		Pais pais = new Pais();
		pais.setID(id);
		String sqlDelete = "DELETE FROM Pais WHERE ID = ?";
		try(Connection connection = ConnectionFactory.obtemConexao();
			PreparedStatement statement = connection.prepareStatement(sqlDelete);)
		{
			statement.setInt(1, pais.getID());
			statement.execute();
		} 
		catch (ClassNotFoundException error) 
		{
			System.out.println("Erro ao conectar ao banco - DELETE"+error);
		}
		catch (SQLException error) 
		{
			System.out.println("Erro com codigo MySQL - DELETE"+error);
		} 
	}
}
