package pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pkg.Database;
import java.sql.*;
/**
 * Servlet implementation class TicketValidate
 */
@WebServlet("/TicketValidate")
public class TicketValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketValidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String vbutton=request.getParameter("vbutton");
		HttpSession s3=request.getSession(false);
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
	
	 if(s3!=null)
			 
		 
	 {  
		 
		 if("Back".equals(vbutton))
	 
	     {   
		   request.getRequestDispatcher("dashboard2.html").forward(request, response);
		   
	      }
	 
	 
	    if("Validate".equals(vbutton))
        {  
	    	try {  String ticketid=request.getParameter("htext");
	    		
	    		   Connection con=Database.databaseconnection();
	    		   
	    		   Statement stmt=con.createStatement();
	    		   stmt.executeUpdate("update ticket set validation='no' where ticket_no='"+ticketid+"'");
	    		
	    		     con.close();
	    		   
	    		   request.getRequestDispatcher("dashboard2.html").include(request, response);
	    		   out.println("<br><br><font color=green>Validate success</font>");
	    		
	    		
	    		
	         	}
	    	
	    	catch(Exception e)
	    	{request.getRequestDispatcher("dashboard2.html").include(request, response);
		 	out.println("<br><br><font color=red>"+e+"</font>");
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
