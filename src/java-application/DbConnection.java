

import java.sql.*;
import java.io.*;

/*=====================================================================================================================*/

interface employee
{
	// methods related to general access
	void showGuests();
	void addGuest();  // allots room limited to 464 rooms 
	void modifyGuest(); // for modifying the details of guest like changing room alloted  or name
	void checkOutGuest(); // updates the database to show that given room is now available 
	// special access methods(admin specific)
	void addEmployee();
	void removeEmployee();
}

class Admin implements employee
{

	@Override
	public void showGuests() {
		
		
	}

	@Override
	public void addGuest() {
		
		
	}

	@Override
	public void modifyGuest() {
		
		
	}

	@Override
	public void checkOutGuest() {
		
		
	}

	@Override
	public void addEmployee() {
		
		
	}

	@Override
	public void removeEmployee() {
		
		
	}
	
}

class Receptionist implements employee
{

	@Override
	public void showGuests() {
		
		
	}

	@Override
	public void addGuest() {
		
		
	}

	@Override
	public void modifyGuest() {
		System.out.println("You are not authorized for this!");
		
	}

	@Override
	public void checkOutGuest() {
		
		
	}

	@Override
	public void addEmployee() {
		System.out.println("You are not authorized for this!");
	}
	@Override
	public void removeEmployee() {
		System.out.println("You are not authorized for this!");
		
	}
	
}
/*=====================================================================================================================*/

public class DbConnection 
{
	public static void main(String[] args)
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM employees;";
		String designation = null, name, password;
		try
		{	
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:E:/data/database/reg.db");
			System.out.println("Connection with database Successfull!");
			stmt = con.createStatement();
			stmt.execute(query);
			System.out.println("Fetching Successfull!");
			
			System.out.println("please enter your name");
			 name = br.readLine();

			System.out.println("please enter your password");
			 password = br.readLine();

/*-------------------------------------------------------------------------------------------------------------------------------------*/			
/* now search a record in database with these condition if the resultset is not null then proceed for this prepared statement is useful*/
			
//			PreparedStatement pstmt = con.prepareStatement("SELECT designation FROM employees WHERE name = ? and password = ?");
			pstmt = con.prepareStatement("SELECT designation FROM employees WHERE name = ? and password = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, password);
/*-------------------------------------------------------------------------------------------------------------------------------------*/
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				designation = rs.getString(1);
				System.out.println("    logged in as : " + designation + "\n" + "\n");
			}
			
/*-------------------------------------------------------------------------------------------------------------------------------------*/
			
			
			System.out.println("==================DashBoard==================" + "\n" + "1. show the list of guests" + "\n" + "2. checkIn new guest"+ "\n" + "3. modify guest details" + "\n" + "4. checkOut the guest" + "\n" + "5. exit" + "\n" + "6. add a new employee"+ "\n" + "7. remove a existing employee"+"\n"+ "===============================================");
			System.out.println("\n" + "\n" + "choose an action from above");
			String option = br.readLine();
			int value = Integer.valueOf(option);
			
			switch(value)
			{
			case 1:     
				System.out.println("u chose1");
				break;
			case 2:
			    System.out.println("u chose 2");
			    break;
			case 3:
			    System.out.println("u chose 3");
			    break;
			case 4:
			    System.out.println("u chose 4");
			    break;
			case 5:
			    System.out.println("u chose 5");
			    break;
			case 6:
				 System.out.println("u chose 6");
				 break;
			case 7:
				System.out.println("u chose 7");
				break;
			default:
			    System.out.println("NOT A VALID OPTION!");
			}
			
			
			
			if (designation.equals("admin"))
			{
				System.out.println("Welcome"+ name + " (Admin)!");
				Admin admin = new Admin();
			}
			else if (designation.equals("receptionist"))
			{
				System.out.println("                  Welcome " + name + " !" + "\n" + "\n");
				Receptionist recp = new Receptionist();
				
				
			}
			else 
			{
				System.out.println("Denied!");
			}

		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		finally
		{
			try 
			{
				stmt.close();
				con.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
