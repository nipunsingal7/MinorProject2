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
import pkg.SignupValidator;
/**
 * Servlet implementation class Forgotpassword
 */
@WebServlet("/Forgotpassword")
public class Forgotpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forgotpassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String button=request.getParameter("buttons");
		String password=request.getParameter("password");
		String password1=request.getParameter("password1");
		 HttpSession s5=request.getSession(false);
		
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
	
		
	
		if(s5!=null)
          {   
			  String type=(String)s5.getAttribute("type");
			
		
			  
			  
			  if(type.equals("user"))
			{
				 if(button.equals("Back"))
				 {
					 request.getRequestDispatcher("dashboard.html").forward(request, response);
				 }
				 
				 
				 if(button.equals("Submit"))
				 {
					if(password.equals(password1))
					{  
						if(SignupValidator.passwdvalidate(password)!=1)
						{String username=(String)s5.getAttribute("username");
						
						try {Connection con=Database.databaseconnection();
							 Statement stmt=con.createStatement();
							 stmt.executeUpdate("update users set password='"+password+"' where username='"+username+"' ");
							 con.close();
						
							 request.getRequestDispatcher("dashboard.html").include(request, response);
							 out.println("<font color=green>Password successfully changed</font>");
						}
						
						catch(Exception e)
						   {
							request.getRequestDispatcher("dashboard.html").include(request, response);
							out.println("<font color=red>error occured: "+e+"</font>");
							}
						
						}
						
						
						else
						{
							request.getRequestDispatcher("forgotpasswd.html").include(request,response);
							   out.println("<br><font color=red>invalid password</font><br><br>* Minimum password length of 8<br>" + 
							  		"* Must contain upper and lower case characters<br>" + 
							  		"* Must contain numbers and special characters");
						}
						
						
					}
					
					else
					{  request.getRequestDispatcher("forgotpasswd.html").include(request, response);
					   out.println("<br><font color=red>Password does not matched. Again fill the password</font>");
					 }	
					 
				 }
				
			}
			
			
			
			if(type.equals("admin"))
			{
				if(button.equals("Back"))
				 {
					 request.getRequestDispatcher("dashboard1.html").forward(request, response);
				 }
				 
				 
				 if(button.equals("Submit"))
				 {
					 if(password.equals(password1))
						{
							String username1=(String)s5.getAttribute("username");
							
							try {Connection con=Database.databaseconnection();
								 Statement stmt=con.createStatement();
								 stmt.executeUpdate("update admin set password='"+password+"' where username='"+username1+"' ");
								 con.close();
							
								 request.getRequestDispatcher("dashboard1.html").include(request, response);
								 out.println("<font color=green>Password successfully changed</font>");
							}
							
							catch(Exception e)
							   {
								request.getRequestDispatcher("dashboard1.html").include(request, response);
								out.println("<font color=red>error occured: "+e+"</font>");
								}
						}
						
						else
						{  request.getRequestDispatcher("forgotpasswd.html").include(request, response);
						   out.println("<br><font color=red>Password does not matched. Again fill the password</font>");
						 }	
						 
					 
				 }
				
			}
			
			
			
			if(type.equals("staff"))
			{
				if(button.equals("Back"))
				 {
					 request.getRequestDispatcher("dashboard2.html").forward(request, response);
				 }	 
		    	 
		    	 
		    	 if(button.equals("Submit"))
				 {
		    		 if(password.equals(password1))
						{
		    			 
		    			 if(SignupValidator.passwdvalidate(password)!=1)
		    			   
		    			 { String username2=(String)s5.getAttribute("username");
							
							try {Connection con=Database.databaseconnection();
								 Statement stmt=con.createStatement();
								 stmt.executeUpdate("update conductorcredential set password='"+password+"' where id='"+username2+"' ");
								 con.close();
							
								 request.getRequestDispatcher("dashboard2.html").include(request, response);
								 out.println("<font color=green>Password successfully changed</font>");
							}
							
							catch(Exception e)
							   {
								request.getRequestDispatcher("dashboard2.html").include(request, response);
								out.println("<font color=red>error occured: "+e+"</font>");
								}
		    			 }
		    			 
		    			 
		    			 else
							{
								request.getRequestDispatcher("forgotpasswd.html").include(request,response);
								   out.println("<br><font color=red>invalid password</font><br><br>* Minimum password length of 8<br>" + 
								  		"* Must contain upper and lower case characters<br>" + 
								  		"* Must contain numbers and special characters");
							}
							
		    			 
		    			 
						}
						
						else
						{  request.getRequestDispatcher("forgotpasswd.html").include(request, response);
						   out.println("<br><font color=red>Password does not matched. Again fill the password</font>");
						 }	
						 
		    		 
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
