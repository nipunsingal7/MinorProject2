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
 * Servlet implementation class Blockuser
 */
@WebServlet("/Blockuser")
public class Blockuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Blockuser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String button=request.getParameter("adbutton");
		 HttpSession s2=request.getSession(false);
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
		 
		 if(s2!=null)			 		 
	     {	
			if("Back".equals(button))
			{
				request.getRequestDispatcher("dashboard1.html").forward(request, response);
			}
			 
			 
		else
		  { 
			 try { Connection con=Database.databaseconnection();
			      Statement stmt=con.createStatement();
			      ResultSet rs=stmt.executeQuery("select * from users where username='"+request.getParameter("username")+"' ");
		          
			      if(!(rs.next()))
			      {      
			    	  con.close();
			    	  request.getRequestDispatcher("blockuser.html").include(request, response);
					  out.println("<br><br><font color=red>User not found</font>");
			    	  
			      }
			    	  
			      
			    else
			     { 	  Statement stmt1=con.createStatement();
			     
			         if("Block_User".equals(button))
			        { 
			        	 
					      stmt1.executeUpdate("update users set validation='no' where username='"+request.getParameter("username")+"' ");
					      con.close();
					      request.getRequestDispatcher("blockuser.html").include(request, response);
						  out.println("<br><br><font color=green>User Blocked</font>");
				 
			        }
			 
			        if("Unblock_User".equals(button))
			       {
			        	
					      stmt1.executeUpdate("update users set validation='yes' where username='"+request.getParameter("username")+"' ");
					      con.close();
					      request.getRequestDispatcher("blockuser.html").include(request, response);
						  out.println("<br><br><font color=green>User Unblocked</font>");
				 
			       }
			    
			   }   
			 
			}
			
			catch(Exception e)
			 {
				 request.getRequestDispatcher("blockuser.html").include(request, response);
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
