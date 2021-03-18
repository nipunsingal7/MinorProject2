package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pkg.Database;
import java.sql.*;
/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
		 String button=request.getParameter("cbutton");
		 HttpSession s1=request.getSession(false);
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
	
	 if(s1!=null)
			 
		 
	 {	try { Connection con=Database.databaseconnection();
		 
		 if("Search Bus".equals(button))
		 {  
		   	 
		   PreparedStatement stmt=con.prepareStatement("select t1.bus_no,t1.route,t1.AVTime,t2.route,t2.AVTime from busroute t1,busroute t2 where t1.route=? AND t2.route=? AND t1.bus_no=t2.bus_no AND t1.number<t2.number AND t1.validation='yes' AND t2.validation='yes'");
           stmt.setString(1,request.getParameter("starting").trim());	
           stmt.setString(2,request.getParameter("destination").trim());
           ResultSet rs=stmt.executeQuery();
          
           if(rs.next())
           
           { out.println("<html> <head> <title>Search Bus</title> </head> <body><form action=Bookticket method=post>"+
  		  "&ensp;&emsp;&emsp; Enter Bus No:&emsp;<input type=text name=bus_no size=7/><br><br><br>"+
  		 "&emsp;&emsp;&emsp;<input type=submit name=book value=Book_ticket />"+                          
		  "&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Back /><br><br><br></form>"+
         "<style>table,th,td{border: 1px solid black; border-collapse: collapse} th,td{ padding: 15px}</style>"+
		  "<table><tr><th>BusNo</th><th>StartingPoint</th><th>DepartureTime</th><th>Destination</th><th>ArrivalTime</th></tr>"+
		  "<tr><td>"+rs.getString("t1.bus_no")+"</td><td>"+rs.getString("t1.route")+"</td><td>"+rs.getString("t1.AVTime")+"</td><td>"+rs.getString("t2.route")+"</td><td>"+rs.getString("t2.AVTime")+"</td></tr>");
             while(rs.next())
             {
        	   out.println("<tr><td>"+rs.getString("t1.bus_no")+"</td><td>"+rs.getString("t1.route")+"</td><td>"+rs.getString("t1.AVTime")+"</td><td>"+rs.getString("t2.route")+"</td><td>"+rs.getString("t2.AVTime")+"</td></tr>");
             }
           
           out.println("</body></html>");
		 }
           
           else
           {request.getRequestDispatcher("dashboard.html").include(request, response);
		    out.println("<br><font color=red>No bus found</font>");
        	   
           }
           
		 }
		 
		 
		 
		 
		 if("Logout".equals(button))
		 {   con.close();
			 s1.invalidate();
			 request.getRequestDispatcher("main.html").include(request, response);
			 out.println("<br><font color=green>Successfully logout</font>");
		 }
		 
		 
	  }
	 
	  catch(Exception e)
	   {out.println("Error catched:"+" "+e); }
		
	 
	 }	 
		
	 else
	 { 
		 out.println("<br><font color=red>Login first....</font>");
		  request.getRequestDispatcher("main.html").include(request, response);
	 }
	 
		
		
		
		
	}

}
