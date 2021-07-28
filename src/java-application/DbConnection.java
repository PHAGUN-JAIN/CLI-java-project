
import java.sql.*;
import java.io.*;
/*====================================================================================================================================*/




class Connect
{
	void showGuest()
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM guests;";
		try
		{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:E:/data/database/reg.db");
			System.out.println("Connection with database Successfull!");
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			System.out.println("  ID   | NAME   | ROOM-NO  | STATUS   ");
			while(rs.next())
			{
				System.out.println("  " + rs.getInt(1) + "  | " + rs.getString(2) + "  | " + rs.getInt(3) + "  | " + rs.getString(4));
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try
			{
				rs.close();
				stmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

	void addGuest() {
		
		Connection con = null;
		PreparedStatement pstmt =null;

		try
		{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:E:/data/database/reg.db");
			
			System.out.println("Connection with database Successfull!");

			pstmt = con.prepareStatement("INSERT INTO guests values (?, ?, ?,?);");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("id given to guest ");
			int id = Integer.parseInt(br.readLine());
			
			System.out.println("Name of the guest:");
			String name = br.readLine();
			
			System.out.println("Room alloted to the guest: ");
			int room = Integer.parseInt(br.readLine());
			
			pstmt.setInt(1, id);
			pstmt.setString(2,name);
			pstmt.setInt(3, room);
			pstmt.setString(4, "checkedIn");
			boolean res = pstmt.execute();

			if(res == true)
			{
				System.out.println("Inserted!");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

	void checkOut() {
		Connection con = null;

		PreparedStatement pstmt =null;

		try
		{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:E:/data/database/reg.db");
			System.out.println("Connection with database Successfull!");

			pstmt = con.prepareStatement("UPDATE guests set status =? where room_no=?;");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Room alloted to the guest: ");
			int room = Integer.parseInt(br.readLine());
			
			pstmt.setString(1, "checkedOut");
			pstmt.setInt(2,room);

			pstmt.execute();
			System.out.println("success!");

			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

	void addEmployee() {
		Connection con = null;
		PreparedStatement pstmt =null;

		try
		{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:E:/data/database/reg.db");
			System.out.println("Connection with database Successfull!");

			pstmt = con.prepareStatement("INSERT INTO employees values (?, ?, ?, ?)");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("id given to employee ");
			int id = Integer.parseInt(br.readLine());
			
			System.out.println("Name of the employee:");
			String name = br.readLine();
			
			System.out.println("designation alloted to the employee: ");
			String designation = br.readLine();
			
			pstmt.setInt(1, id);
			pstmt.setString(2,name);
			pstmt.setString(3, designation);
			pstmt.setString(4, "default");
			pstmt.execute();
			System.out.println("Inserted!");

			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}





/*====================================================================================================================================*/

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
	Connect con = new Connect();
	@Override
	public void showGuests() {
				
		con.showGuest();
		
//		System.out.println("method under construction");
	}

	@Override
	public void addGuest() {
		con.addGuest();
		
	}

	@Override
	public void modifyGuest() {
		System.out.println("method under construction");
		
	}

	@Override
	public void checkOutGuest() {
		
		con.checkOut();
	}

	@Override
	public void addEmployee() {
		
		con.addEmployee();
	}

	@Override
	public void removeEmployee() {
		System.out.println("method under construction");
	}
	
}

class Receptionist implements employee
{
	Connect con = new Connect();

	@Override
	public void showGuests() {
		
		con.showGuest();
		
	}

	@Override
	public void addGuest() {
		
		con.addGuest();
	}

	@Override
	public void modifyGuest() {
		System.out.println("You are not authorized for this!");
		
	}

	@Override
	public void checkOutGuest() {
		
		con.checkOut();
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
/*====================================================================================================================================*/

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
			rs.close();
			pstmt.close();
			con.close();
/*-------------------------------------------------------------------------------------------------------------------------------------*/
			
			
			System.out.println("==================DashBoard==================" + "\n" + "1. show the list of guests" + "\n" + "2. checkIn new guest"+ "\n" + "3. modify guest details" + "\n" + "4. checkOut the guest" + "\n" + "5. exit" + "\n" + "6. add a new employee"+ "\n" + "7. remove a existing employee"+"\n"+ "===============================================");
			System.out.println("\n" + "\n" + "choose an action from above");
			String option = br.readLine();
			int value = Integer.valueOf(option);
			
			switch(value)
			{
			case 1:     

			case 2:

			case 3:

			case 4:

			case 5:

			case 6:

			case 7:
				System.out.println("\n"+"processing...................");
				break;
			default:
			    System.out.println("NOT A VALID OPTION!");
			}
			
			
/*-------------------------------------------------------------------------------------------------------------------------------------*/
			
			if (designation.equals("admin"))
			{
				System.out.println("                  Welcome "+ name + " (Admin)!");
				Admin admin = new Admin();
				
				if(value ==1)
				{
					admin.showGuests();
				}
				else if(value == 2) 
				{
					admin.addGuest();
				}
				else if(value == 3)
				{
					admin.modifyGuest();
				}
				else if(value ==4)
				{
					admin.checkOutGuest();
				}
				else if(value == 5)
				{
					System.exit(0);
				}
				else if(value == 6)
				{
					admin.addEmployee();
				}
				else if(value == 7)
				{
					admin.removeEmployee();
				}
				else 
				{
					System.out.println("Unexpected behaviour...");
				}
			}
			else if (designation.equals("receptionist"))
			{
				System.out.println("                  Welcome " + name + " !" + "\n" + "\n");
				Receptionist recp = new Receptionist();
				
				if(value ==1)
				{
					recp.showGuests();
				}
				else if(value == 2) 
				{
					recp.addGuest();
				}
				else if(value == 3)
				{
					recp.modifyGuest();
				}
				else if(value ==4)
				{
					recp.checkOutGuest();
				}
				else if(value == 5)
				{
					System.exit(0);
				}
				else if(value == 6)
				{
					recp.addEmployee();
				}
				else if(value == 7)
				{
					recp.removeEmployee();
				}
				else 
				{
					System.out.println("Unexpected behaviour...");
				}
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
/*-------------------------------------------------------------------------------------------------------------------------------------*/
//		finally
//		{
//			try 
//			{
//				stmt.close();
//				pstmt.close();
//				con.close();
//			}
//			catch (SQLException e) 
//			{
//				e.printStackTrace();
//			}
//		}
/*-------------------------------------------------------------------------------------------------------------------------------------*/

	}
}
/*-------------------------------------------------------------------------------------------------------------------------------------*/
