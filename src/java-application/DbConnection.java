import java.sql.*;
import java.io.*;

public class DbConnection 
{
	public static void main(String[] args)
	{
		Connection con = null;
		Statement stmt = null;
		String query = "SELECT * FROM employees;";
		try
		{	
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:E:/data/database/reg.db");
			System.out.println("Connection with database Successfull!");
			stmt = con.createStatement();
			stmt.execute(query);
			System.out.println("Fetching Successfull!");
			
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
}
