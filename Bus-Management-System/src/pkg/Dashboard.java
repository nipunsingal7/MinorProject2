package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pkg.Database;
import java.sql.*;
import java.util.Date;
import java.util.Enumeration;
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
			 SimpleDateFormat ftm=new SimpleDateFormat("yyyy-MM-dd");
			 Date d1=new Date();
			 String currentdate=ftm.format(d1);
			 Date d2=ftm.parse(currentdate);
			 
			 Date date2=ftm.parse(request.getParameter("date1"));
			 
			 if(date2.before(d2))
			 { con.close();
	        	request.getRequestDispatcher("dashboard.html").include(request, response);
			    out.println("<br><font color=red>You cannot book for previous dates</font>");
				 
			 }
			 
			 else
			 
		  {  String start=request.getParameter("starting").trim();
			 String dest=request.getParameter("destination").trim();
			 
			
			 
			 
             SimpleDateFormat ftm1=new SimpleDateFormat("EEEE");
             String sdate=ftm1.format(date2);
			 
             s1.setAttribute("date",date2);
             s1.setAttribute("from",start);
             s1.setAttribute("to",dest);
            
		   	
		   PreparedStatement stmt=con.prepareStatement("select t1.bus_no,t1.route,t1.AVTime,t2.route,t2.AVTime,t3.days from busroute t1,busroute t2, days t3 where t1.route=? AND t2.route=? AND t3.days=? AND t1.bus_no=t2.bus_no AND t1.number<t2.number AND t1.validation='yes' AND t2.validation='yes' AND t3.busno_6=t2.bus_no");
           stmt.setString(1,start);	
           stmt.setString(2,dest);
           stmt.setString(3,sdate);
           ResultSet rs=stmt.executeQuery();
          
           if(rs.next())
           
           { out.println("<html> <head> <title>Search Bus</title> </head> <body><form action=Bookticket method=post>"+
  		  "&ensp;&emsp;&emsp;Enter No.of Passengers:&emsp;&emsp;<input type=number name=passenger min=1 max=15 size=5 /><br><br><br>"+
  		 "&emsp;&emsp;&emsp;<input type=submit name=button2 value=Select_seat />"+                          
		  "&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button2 value=Back /><br><br><br>"+
  		 
         "<style>table,th,td{border: 1px solid black; border-collapse: collapse} th,td{ padding: 15px}</style>"+
		  "<table><tr><th>BusNo</th><th>StartingPoint</th><th>DepartureTime</th><th>Destination</th><th>ArrivalTime</th><th>Journey Days</th><th>Select The Bus</th></tr>"+
		  
		  "<tr><td>"+rs.getString("t1.bus_no")+"</td><td>"+rs.getString("t1.route")+"</td><td>"+rs.getString("t1.AVTime")+"</td><td>"+rs.getString("t2.route")+"</td><td>"+rs.getString("t2.AVTime")+"</td>"+
		  "<td>"+rs.getString("t3.days")+"</td><td><input type=radio name=rbutton value="+rs.getString("t1.bus_no")+" /></td></tr>");
            
           while(rs.next())
             {
        	   out.println("<tr><td>"+rs.getString("t1.bus_no")+"</td><td>"+rs.getString("t1.route")+"</td><td>"+rs.getString("t1.AVTime")+"</td><td>"+rs.getString("t2.route")+"</td><td>"+rs.getString("t2.AVTime")+"</td><td>"+rs.getString("t3.days")+
        	   "</td><td><input type=radio name=rbutton value="+rs.getString("t1.bus_no")+" /></td></tr>");
             }
           
           out.println("</table></form></body></html>");
           con.close();
		 }
           
           else
           {con.close();
        	request.getRequestDispatcher("dashboard.html").include(request, response);
		    out.println("<br><font color=red>No bus found</font>");
        	   
           }
           
	  }     
           
		 }
		 
		 
		 
		 
		 if("Logout".equals(button))
		 {   con.close();
			 s1.invalidate();
			 request.getRequestDispatcher("main.html").include(request, response);
			 out.println("<br><font color=green>Successfully logout</font>");
		 }
		 
		 
		 if("Change Password".equals(button))
		 {   con.close();
		 request.getRequestDispatcher("forgotpasswd.html").forward(request, response); 
		 }
		 
	  }
	 
	  catch(Exception e)
	   {request.getRequestDispatcher("dashboard.html").include(request, response);
		out.println("<br><br><font color=red>enter all details</font>"); }
		
	 
	 }	 
		
	 else
	 { 
		 
		  request.getRequestDispatcher("main.html").include(request, response);
		  out.println("<br><font color=red>Login first....</font>");
	 }
	 
		
		
		
		
	}

}
