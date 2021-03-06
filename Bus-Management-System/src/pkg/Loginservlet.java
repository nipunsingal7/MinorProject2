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
import java.util.Enumeration;

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
		   {   	 
		       HttpSession s1=request.getSession();
		       s1.setMaxInactiveInterval(600);
		       s1.setAttribute("username",username);
		       s1.setAttribute("type","user");
		       s1.setAttribute("email",rs.getString("email"));
		       c1.close();
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
			   		        
		       request.getRequestDispatcher("main.html").include(request, response);
		       out.println("<br><font color=red>Incorrect username or password</font>");
		    }
		   
		   else
		   {   c1.close();
		      HttpSession s2=request.getSession();
	          s2.setAttribute("username", username);
	          s2.setAttribute("type", "admin");
	          s2.setMaxInactiveInterval(1500);
			   request.getRequestDispatcher("dashboard1.html").forward(request, response); 
		    }
		}
		
		if("Staff Login".equals(button))
		{
			PreparedStatement stmt2=c1.prepareStatement("select * from conductorcredential where id=? AND password=? AND validation=?"); 
		    stmt2.setString(1,username);
		    stmt2.setString(2, passwd); 
		    stmt2.setString(3, "yes");
			ResultSet rs3=stmt2.executeQuery();
		  
		   
		   if(!(rs3.next()))
		    {  c1.close();
			   		        
		       request.getRequestDispatcher("main.html").include(request, response);
		       out.println("<br><font color=red>Incorrect username or password</font>");
		    }
		   
		   else
		   {   
		      HttpSession s3=request.getSession();
	          s3.setAttribute("username", username);
	          s3.setAttribute("type", "staff");
	          s3.setAttribute("busno",rs3.getString("busno_4"));
	          c1.close();
			   request.getRequestDispatcher("dashboard2.html").forward(request, response); 
		    }
			
			
			
		}
		
		if("Sign Up".equals(button))
		{ c1.close();
			request.getRequestDispatcher("signup.html").forward(request,response);}
		
	
		
		
		if("Forgot Password".equals(button))
		{ 
			c1.close();
			request.getRequestDispatcher("forgotpasswd2.html").forward(request,response);						
		}
		
		
	 }
  
  
		
		catch(Exception e)
	    {out.println("Exception caught"+" "+e);    }
  
	}

}
