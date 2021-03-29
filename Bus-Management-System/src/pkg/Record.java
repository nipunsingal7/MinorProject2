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
 * Servlet implementation class Record
 */
@WebServlet("/Record")
public class Record extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Record() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
		String button=request.getParameter("1button");
		String button1=request.getParameter("0button");
		 HttpSession s2=request.getSession(false);
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
		 
		 if(s2!=null)			 		 
	{	
		
	     if("Back".equals(button1))
		  {
			 request.getRequestDispatcher("ticketrecords.html").forward(request, response);
		  }	
				
	   
	   if("Back".equals(button))
		{
			request.getRequestDispatcher("dashboard1.html").forward(request, response);
		}	
		
			 
      if("Search".equals(button))	 
      {  String rbutton=request.getParameter("rbutton");
   
     
    	  try
		 {   Connection con= Database.databaseconnection();
		      Statement stmt=con.createStatement();	 
		       String query=null;
		      
			 if("busno".equals(rbutton))
			 {
				 query="select * from ticket where busno_7='"+request.getParameter("busbox")+"' ";
				 
			 }
			
			  if("name".equals(rbutton))
			 {
				 query="select * from ticket where name='"+request.getParameter("namebox")+"' ";
			 }
			
			  if("date".equals(rbutton))
			 {
				 query="select * from ticket where bdate='"+request.getParameter("datebox")+"' ";
			 }
			
			  if("seatno".equals(rbutton))
			 {
				 query="select * from ticket where seat_no='"+request.getParameter("seatbox")+"' ";				 
			 }
			
			 if("source".equals(rbutton))
			 {
				 query="select * from ticket where source='"+request.getParameter("sourcebox")+"' ";
			 }
			
			  if("s&d".equals(rbutton))
			 {
				 query="select * from ticket where source='"+request.getParameter("sourcebox")+"' AND destination='"+request.getParameter("destbox")+"' ";
			 }
			
			  if("ticket".equals(rbutton))
			 {
				 query="select * from ticket where ticket_no='"+request.getParameter("ticketbox")+"'";
			 }
			 
			  if("all".equals(rbutton))
			 {
				 query="select * from ticket where busno_7='"+request.getParameter("busbox")+"' AND name='"+request.getParameter("namebox")+"' AND bdate='"+request.getParameter("datebox")+"' AND seat_no='"+request.getParameter("seatbox")+"' AND  source='"+request.getParameter("sourcebox")+"' AND destination='"+request.getParameter("destbox")+"' "; 
			 }
			
			
			 ResultSet rs=stmt.executeQuery(query);			 
			 
			 if(!(rs.next()))
			 {    con.close();
				 request.getRequestDispatcher("ticketrecords.html").include(request, response);
				  out.println("<br><br><font color=red>No record found</font>");				 
			 }
			  
			  else
			  {
				  out.println("<html><head><title>ticket records</title></head><body><style>table,th,td {border: 2px solid black; border-collapse: collapse} th,td{padding: 10px}</style><table>"+
			       "<tr><th>BusNo</th><th>Date</th><th>Name</th><th>Age</th><th>SeatNo</th><th>Source</th><th>Destination</th><th>TicketNo</th><th>Validated</th></tr> "
			       +" <tr><td>"+rs.getString("busno_7")+"</td><td>"+rs.getString("bdate")+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("age")+"</td><td>"+rs.getString("seat_no")+"</td><td>"+rs.getString("source")+"</td><td>"+rs.getString("destination")+"</td><td>"+rs.getString("ticket_no")+"</td><td>"+rs.getString("validation")+"</td></tr>" );
				  
				  while(rs.next())
				  {
					 out.println(" <tr><td>"+rs.getString("busno_7")+"</td><td>"+rs.getString("bdate")+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("age")+"</td><td>"+rs.getString("seat_no")+"</td><td>"+rs.getString("source")+"</td><td>"+rs.getString("destination")+"</td><td>"+rs.getString("ticket_no")+"</td><td>"+rs.getString("validation")+"</td></tr>" );  
				  }  
				  
				  out.println("</table><form action=Record method=post><br><br><br>&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=0button value=Back></form></body></html>");
				  con.close();
			  }
			  
			 
			 
			 
		 }
		 
		 catch(Exception e)
		 {
			 request.getRequestDispatcher("ticketrecords.html").include(request, response);
			  out.println("<br><br><font color=red>Fill detials correctly</font>");
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
