package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pkg.Database;
import java.sql.*;

/**
 * Servlet implementation class Dashboard2
 */
@WebServlet("/Dashboard2")
public class Dashboard2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String button0=request.getParameter("button0");
		 HttpSession s3=request.getSession(false);
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
	
	 if(s3!=null)
			 
		 
	 {
		 if("Logout".equals(button0))
		 {   
			 s3.invalidate();
			 request.getRequestDispatcher("main.html").include(request, response);
			 out.println("<br><font color=green>Successfully logout</font>");
		 }
		 
		 
		if("list_ticket".equals(button0))
	    {  
		 try {  Connection con=Database.databaseconnection(); 
				
				String ticketid=request.getParameter("ticketno");
			
			    SimpleDateFormat ftm=new SimpleDateFormat("yyyy-MM-dd");
				Date dt=new Date();
				String date=ftm.format(dt);
				
				
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from ticket where ticket_no='"+ticketid+"' AND validation='yes'");
		
				if(!(rs.next()))
				{
					request.getRequestDispatcher("dashboard2.html").include(request, response);
				 	out.println("<br><br><font color=red>No ticket found or already used</font>");
				}
				
				
				else
				{   String bookdate=rs.getString("bdate");
				    String busNo=(String)s3.getAttribute("busno"); 	
				 
				   if(busNo.equals(rs.getString("busno_7")))
				   {
				
				         if(bookdate.equals(date))
					    {      
				        	  
						      out.println("<html> <head> <title>Validate Ticket</title> </head> <body><style>table,th,td{border: 1px solid black; border-collapse: collapse} th,td{padding: 15px}</style>"
								+ "<table><tr><th>Name</th><th>Age</th><th>SeatNo</th><th>From</th><th>To</th></tr>"
								+ "<tr><td>"+rs.getString("name")+"</td><td>"+rs.getString("age")+"</td><td>"+rs.getString("seat_no")+"</td><td>"+rs.getString("source")+"</td><td>"+rs.getString("destination")+"</td></tr>");
				           				
						      while(rs.next())
						      {
						    	  out.println("<tr><td>"+rs.getString("name")+"</td><td>"+rs.getString("age")+"</td><td>"+rs.getString("seat_no")+"</td><td>"+rs.getString("source")+"</td><td>"+rs.getString("destination")+"</td></tr>");
						      }
						  
						   
						     out.println("</table><form action=TicketValidate  method=post><br><br><br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=vbutton value=Validate>"
						     		+ "&emsp;&emsp;&emsp;<input type=submit name=vbutton value=Back><input type=text name=htext value="+ticketid+" hidden ></form></body></html>");
						
					     }
					
					    else 
					     { request.getRequestDispatcher("dashboard2.html").include(request, response);
				 	      out.println("<br><br><font color=red>Ticket booked for date:"+bookdate+" "+"Not for today</font>");
						
					     }
				   }
				   
				   else
				   {
					   request.getRequestDispatcher("dashboard2.html").include(request, response);
				 	      out.println("<br><br><font color=red>Ticket booked for another bus</font>");
						
				   }
				   
				   
				}
				
			  }
			
			
			 catch(Exception e)
		    	{request.getRequestDispatcher("dashboard2.html").include(request, response);
			 	out.println("<br><br><font color=red>Enter the ticketid</font>");
		        }
			 
			 
		 }
		 
		 
		 
		 
	 }
	 
	 
	 
	
	 else
	 { 
		 
		  request.getRequestDispatcher("main.html").include(request, response);
		  out.println("<br><font color=red>Login first....</font>");
	 }
		
		
		
	}

}
