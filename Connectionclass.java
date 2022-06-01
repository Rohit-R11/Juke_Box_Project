package jukebox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectionclass {
	Connection con=null;
	public  Connection getConnection() throws ClassNotFoundException, SQLException
	{	
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","Rohit123@");	
		return con;
	}
}