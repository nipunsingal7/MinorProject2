package pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import pkg.Database;

/**
 * Servlet implementation class Dataservlet
 */
@WebServlet("/Dataservlet")
public class Dataservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dataservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String button=request.getParameter("button3");
		String busno=request.getParameter("busno3");
		HttpSession s2=request.getSession(false);
		response.setContentType("html");
		 PrintWriter out=response.getWriter();
		 
	if(s2!=null)
	{   
		try
    	{
			if("Back".equals(button))
		    { 
		    	request.getRequestDispatcher("dashboard1.html").forward(request, response);
		    }
			
			
	     Connection con=Database.databaseconnection();
    	 PreparedStatement stmt=con.prepareStatement("select * from bus where busno=? AND validation=?");	
	     stmt.setString(1, busno);
		 stmt.setString(2,"yes");
		 ResultSet rs=stmt.executeQuery();
					 		 
		 
		 
			if(!(rs.next()))
			{con.close();
			out.println("<font color=red><br>No bus found</font>");
			request.getRequestDispatcher("data.html").include(request, response);}
    	   
    	
    	
	        else
	        {
	        	  if("Modify/delete bus".equals(button))
        		  { 
	                   out.println("<html> <head> <title>Modify bus</title> </head> <body>"+
        		  "<form action=Dataservlet2 method=post>"+
        		  "&ensp;&emsp;&emsp; Bus no:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=text name=busno1  readonly value="+busno+" size=18/><br>"+  		   
        		  "&emsp;&emsp;&emsp;Enter Company Name:&emsp;&emsp;<input type=text name=company2 value="+rs.getString("company")+" size=18 /><br>"+
        		  "&emsp;&emsp;&emsp;Enter Model No:&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;<input type=text name=model2 value="+rs.getString("model")+" size=18 /><br>"+
        		  "&emsp;&emsp;&emsp;Enter No of seats:&emsp;&emsp;&emsp;&emsp;&ensp;<input type=text name=seats2 value="+rs.getInt("seats")+" size=18 /><br>"+
        		  "&emsp;&emsp;&emsp;Enter Starting Point:&emsp;&emsp;&emsp;&ensp;<input type=text name=starting2 value="+rs.getString("startingpoint")+" size=18 /><br>"+
        		  "&emsp;&emsp;&emsp;Enter Destination:&emsp;&emsp;&emsp;&emsp;&ensp;<input type=text name=destination2 value="+rs.getString("destination")+" size=18 /><br>"+
        		  "&emsp;&emsp;&emsp;Enter Via routes:&emsp;&emsp;&emsp;&emsp;&emsp;<input type=text name=routes2 placeholder=seperate via comma value="+rs.getString("routes")+" size=29 /><br><br>"+
        		  "&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Modify Bus />"+
        		  "&emsp;&emsp;&emsp;<input type=submit name=button2 value=Delete Bus />"+                          
        		  "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Back />"+
        		  "</form> </body> </html> ");
	        		  
	        		con.close();  
		 
        		  } 
		
	        		if("Modify/delete owner".equals(button))
	        		{  PreparedStatement stmt1=con.prepareStatement("select * from owner where busno_1=? AND validation=?");	
	       	            stmt1.setString(1, busno);
	    		        stmt1.setString(2,"yes");
	    		        ResultSet rs1=stmt1.executeQuery();

	        			
	        			
	        			out.println("<html> <head> <title>Modify owner</title> </head> <body>"+
	        			"<form action=Dataservlet2 method=post>"+
	        			"<br>&emsp;&emsp;&emsp;Enter Bus No:&emsp;&emsp;<input type=text name=busno1 value="+busno+" size=18 readonly /><br>"+
	        			"&emsp;&emsp;&emsp;Enter owner Full Name:&emsp;&emsp;<input type=text name=owner1 value="+rs1.getString("name")+" size=18/><br>"+
	        			"&emsp;&emsp;&emsp;Enter Address:&emsp;&emsp;<input type=text name=address1 value="+rs1.getString("address")+" size=18/><br>"+
	        			"&emsp;&emsp;&emsp;Enter City:&emsp;&emsp;<input type=text name=city1 value="+rs1.getString("city")+" size=18/><br>"+
	        			"&emsp;&emsp;&emsp;Enter State:&emsp;&emsp;<input type=text name=state1 value="+rs1.getString("state")+" size=18/><br>"+
	        			"&emsp;&emsp;&emsp;Enter phone no:&emsp;&emsp;<input type=text name=phone1 placeholder=start without 0 and country code value="+rs1.getString("phoneno")+" size=18/><br>"+
	        			"&emsp;&emsp;&emsp;Enter RC no:&emsp;&emsp;<input type=text name=rc1 value="+rs1.getString("rcno")+" size=18/><br>"+
	        			"&emsp;&emsp;&emsp;Enter Aadhar card no:&emsp;&emsp;<input type=text name=aadhar1 value="+rs1.getString("aadharno")+" size=18/><br>"+
	        			"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Modify Owner />"+
	        			"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Delete Owner />"+
	        			"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Back />"+

	        			"</form> </body> </html>");
	        			
	        			con.close();
	        		}
	
	
		 
					if("Modify/delete driver".equals(button))
					{
						PreparedStatement stmt3=con.prepareStatement("select * from driver where busno_2=? AND validation=?");	
	       	            stmt3.setString(1, busno);
	    		        stmt3.setString(2,"yes");
	    		        ResultSet rs3=stmt3.executeQuery();
                       
	    		        out.println("<html> <head> <title>Modify driver</title> </head> <body>"+
	    		        "<form action=Dataservlet2 method=post>"+
	    		        "<br> &emsp;&emsp;&emsp;Enter Bus No:&emsp;&emsp;<input type=text name=busno1 value="+busno+" size=18 readonly /><br>"+
	    		        "&emsp;&emsp;&emsp;Enter Driver Full Name:&emsp;&emsp;<input type=text name=driver2"+rs3.getString("name")+" size=18/><br>"+
	    		        "&emsp;&emsp;&emsp;Enter Address:&emsp;&emsp;<input type=text name=address2 value="+rs3.getString("address")+" size=18/><br>"+
	    		        "&emsp;&emsp;&emsp;Enter City:&emsp;&emsp;<input type=text name=city2 value="+rs3.getString("city")+" size=18/><br>"+
	    		        "&emsp;&emsp;&emsp;Enter State:&emsp;&emsp;<input type=text name=state2 value="+rs3.getString("state")+ "size=18/><br>"+
	    		        "&emsp;&emsp;&emsp;Enter phone no:&emsp;&emsp;<input type=text name=phone2 placeholder=start without 0 and country code value="+rs3.getString("phoneno")+" size=18/><br>"+
	    		        "&emsp;&emsp;&emsp;Enter License no:&emsp;&emsp;<input type=text name=license2 value="+rs3.getString("licenseno")+" size=20/><br>"+
	    		        "&emsp;&emsp;&emsp;Enter Aadhar card no:&emsp;&emsp;<input type=text name=aadhar2 value="+ rs3.getString("aadharno")+" size=18/><br>"+
	    		        "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Modify Driver />"+
	    		        "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Delete Driver />"+ 
	    		        "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Back />"+   
	    		      "</form> </body> </html>");
        			
        			   con.close();
					}
					
					
					
					if("Modify/delete conductor".equals(button))
					{PreparedStatement stmt4=con.prepareStatement("select * from conductor where busno_3=? AND validation=?");	
       	            stmt4.setString(1, busno);
    		        stmt4.setString(2,"yes");
    		        ResultSet rs4=stmt4.executeQuery();
						
    		        out.println("<html> <head> <title>Modify conductor</title> </head> <body>"+
    	    		        "<form action=Dataservlet2 method=post>"+
    	    		        "<br> &emsp;&emsp;&emsp;Enter Bus No:&emsp;&emsp;<input type=text name=busno1 value="+busno+" size=18 readonly /><br>"+
    	    		        "&emsp;&emsp;&emsp;Enter Conductor Full Name:&emsp;&emsp;<input type=text name=conductor3"+rs4.getString("name")+" size=18/><br>"+
    	    		        "&emsp;&emsp;&emsp;Enter Address:&emsp;&emsp;<input type=text name=address3 value="+rs4.getString("address")+" size=18/><br>"+
    	    		        "&emsp;&emsp;&emsp;Enter City:&emsp;&emsp;<input type=text name=city3 value="+rs4.getString("city")+" size=18/><br>"+
    	    		        "&emsp;&emsp;&emsp;Enter State:&emsp;&emsp;<input type=text name=state3 value="+rs4.getString("state")+ "size=18/><br>"+
    	    		        "&emsp;&emsp;&emsp;Enter phone no:&emsp;&emsp;<input type=text name=phone3 placeholder=start without 0 and country code value="+rs4.getString("phoneno")+" size=18/><br>"+
    	    		        "&emsp;&emsp;&emsp;Enter E-mail Id:&emsp;&emsp;<input type=text name=email3 value="+rs4.getString("email")+" size=20 /><br>"+
    	    		        "&emsp;&emsp;&emsp;Enter Aadhar card no:&emsp;&emsp;<input type=text name=aadhar3 value="+ rs4.getString("aadharno")+" size=18/><br>"+
    	    		        "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Modify Conductor />"+
    	    		        "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Delete Conductor />"+ 
    	    		        "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Back />"+   
    	    		      "</form> </body> </html>");
            			
            			   con.close();
						
					}
		 
	        }
      }
		
		catch(Exception e)
		{   request.getRequestDispatcher("data.html").include(request, response);
			out.println("<br><font color=red>No data found</font>");
	    }		 
	}
	
	else
	{ 
		 out.println("<br><font color=red>Login first....</font>");
		  request.getRequestDispatcher("main.html").include(request, response);
	 }
	
		
		
		
		
		
		
	}

}
