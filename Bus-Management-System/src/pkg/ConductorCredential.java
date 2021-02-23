package pkg;

import java.util.Random;

import java.sql.*;
import pkg.Database;
import pkg.Mailclass;

public class ConductorCredential {
	static Random rm =new Random();
	static String id;
	static String passwd;


	public static void credentials(String mailid, String busno)
	{ identity();
      password();
      String text1="Your id:"+" "+id+" "+"and password:"+" "+passwd;
     
      
      try
		{ Connection con=Database.databaseconnection();
		  PreparedStatement stmt1=con.prepareStatement("insert into conductorcredential values(?,?,?,?,?)");
		  stmt1.setString(1,id);
		  stmt1.setString(2,passwd);
		  stmt1.setString(3,mailid);
		  stmt1.setString(4,"yes");
		  stmt1.setString(5,busno);
		  stmt1.executeUpdate();
		  
		  con.close();
		
		}
	    
	    catch(Exception e)
	    {System.out.println("error:"+" "+e);}

      
      Mailclass.sendmail(mailid,text1);
	}
	
	
	public static void identity()
	{
		int rno=(rm.nextInt(10000)%90000)+10000;
		    id= String.valueOf(rno);
		  
		    try
			{Connection con=Database.databaseconnection();
			  PreparedStatement stmt=con.prepareStatement("select * from conductorcredential where id=?");
			  stmt.setString(1,id);
			  ResultSet rs=stmt.executeQuery();
			  
			  if(rs.next())
			  {
				  
				  identity();
				
			  }
			  con.close();
			
			}
		    
		    catch(Exception e)
		    {System.out.println("error:"+" "+e);}

	}
	
	public static void password()
	{
		String Uletters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lletters = "abcdefghijklmnopqrstuvwxyz";
	    String sletters = "!@#$";
	    String numbers = "1234567890";
		char[] a=new char[8];
	    
			for(int i=0;i<2;i++)
			{ a[i+0]=Uletters.charAt(rm.nextInt(26));
			a[i+2]=lletters.charAt(rm.nextInt(26));   
			a[i+4]=sletters.charAt(rm.nextInt(4));     
			a[i+6]=numbers.charAt(rm.nextInt(10));
		   }
			
			passwd=String.valueOf(a);
			
		
	}
	
	

}
