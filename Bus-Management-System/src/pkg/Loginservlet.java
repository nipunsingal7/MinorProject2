package pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;


import java.sql.*;
import pkg.Database;
/**
 * Servlet implementation class Loginservlet
 */
@WebServlet("/Loginservlet")
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loginservlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
		String button=request.getParameter("button");
		String username=request.getParameter("username");
		String passwd=request.getParameter("password");
		response.setContentType("html");
		PrintWriter out=response.getWriter(); 
	
	
  try{Connection c1=Database.databaseconnection();
	       
	     
		
		
		if("User Login".equals(button))
		{ 
			PreparedStatement stmt=c1.prepareStatement("select * from users where username=? AND password=? AND validation=?"); 
		    stmt.setString(1,username);
		    stmt.setString(2, passwd); 
		    stmt.setString(3, "yes"); 
			ResultSet rs=stmt.executeQuery();
		   
	
		   
		   if(!(rs.next()))
		    {  c1.close();
			  request.getRequestDispatcher("main.html").include(request, response);
			  out.println("<br><font color=red>Incorrect username or password</font>");
		    }
		   
		   else
		   {   c1.close();	 
		       HttpSession s1=request.getSession();
		       s1.setAttribute("name", username);
		       s1.setMaxInactiveInterval(600);
			   request.getRequestDispatcher("dashboard.html").forward(request, response); 
		    }
		   
		 }
		
		
		if("Admin Login".equals(button))
		{ 
			PreparedStatement stmt1=c1.prepareStatement("select * from admin where username=? AND password=? AND validation=?"); 
		    stmt1.setString(1,username);
		    stmt1.setString(2, passwd); 
		    stmt1.setString(3, "yes");
			ResultSet rs1=stmt1.executeQuery();
		  
		   
		   if(!(rs1.next()))
		    {  c1.close();
			   out.println("<br><font color=red>Incorrect username or password</font>");		        
		       request.getRequestDispatcher("main.html").include(request, response);
		    }
		   
		   else
		   {   c1.close();
		      HttpSession s2=request.getSession();
	          s2.setAttribute("name", username);
	          s2.setMaxInactiveInterval(1500);
			   request.getRequestDispatcher("dashboard1.html").forward(request, response); 
		    }
		}
		
		if("Staff Login".equals(button))
		{
			PreparedStatement stmt2=c1.prepareStatement("select * from conductorcredential where username=? AND password=? AND validation=?"); 
		    stmt2.setString(1,username);
		    stmt2.setString(2, passwd); 
		    stmt2.setString(3, "yes");
			ResultSet rs3=stmt2.executeQuery();
		  
		   
		   if(!(rs3.next()))
		    {  c1.close();
			   out.println("<br><font color=red>Incorrect username or password</font>");		        
		       request.getRequestDispatcher("main.html").include(request, response);
		    }
		   
		   else
		   {   c1.close();
		      HttpSession s3=request.getSession();
	          s3.setAttribute("name", username);
	          out.println("<br><h3>Welcome"+" "+username+"</h3>");
			   request.getRequestDispatcher("dashboard2.html").include(request, response); 
		    }
			
			
			
		}
		
		if("Sign Up".equals(button))
		{ c1.close();
			request.getRequestDispatcher("signup.html").forward(request,response);}
		
		
	 }
		
		catch(Exception e)
	    {out.println("Exception caught"+" "+e);    }
  
	}

}
