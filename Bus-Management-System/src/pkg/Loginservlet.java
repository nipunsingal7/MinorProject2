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
		int flag1=0;
	
  try{Connection c1=Database.databaseconnection();
	       
	     
		
		
		if("User Login".equals(button))
		{ 
			PreparedStatement stmt=c1.prepareStatement("select * from users where username=? AND password=?"); 
		    stmt.setString(1,username);
		    stmt.setString(2, passwd); 
			ResultSet rs=stmt.executeQuery();
		   
			while(rs.next())
		   {
			   flag1++;
		   }
		   
		   if(flag1==0)
		    {  c1.close();
			   out.println("<br>Incorrect username or password");		        
		       request.getRequestDispatcher("main.html").include(request, response);
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
			PreparedStatement stmt1=c1.prepareStatement("select * from admin where username=? AND password=?"); 
		    stmt1.setString(1,username);
		    stmt1.setString(2, passwd); 
			ResultSet rs=stmt1.executeQuery();
		   
			while(rs.next())
		   {
			   flag1++;
		   }
		   
		   if(flag1==0)
		    {  c1.close();
			   out.println("<br>Incorrect username or password");		        
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
		
		
		if("Sign Up".equals(button))
		{ request.getRequestDispatcher("signup.html").forward(request,response);}
		
		
	 }
		
		catch(Exception e)
	    {out.println("Exception caught"+" "+e);    }
  
	}

}
