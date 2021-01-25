package pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import java.sql.*;
import pkg.Database;
import pkg.SignupValidator;
/**
 * Servlet implementation class Signupservlet
 */
@WebServlet("/Signupservlet")
public class Signupservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signupservlet() {
        super();
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
		String fname=request.getParameter("firstname").trim();
		String lname=request.getParameter("lastname").trim();
		String username=request.getParameter("username1");
		String passw=request.getParameter("password1");
		
		
		if(fname.equals("") || lname.equals("") || username.equals("") || passw.equals(""))
		{out.println("<br> All fields are required. They cannot be empty");
		request.getRequestDispatcher("signup.html").include(request,response);}
		
		
		else if(SignupValidator.namevalidate(username)==1)
		{request.getRequestDispatcher("signup.html").include(request,response);
		 out.println("<br>invalid username<br><br> * username should contain 8 to 30 characters <br> "+
		          "* username should start with alphabetic characters only<br>"+
				  " * username can only have alphabets, digits and underscore. No white space allowed");
		}
		
		else if(SignupValidator.passwdvalidate(passw)==1)
		{ request.getRequestDispatcher("signup.html").include(request,response);
		   out.println("<br>invalid password<br><br>* Minimum password length of 8<br>" + 
		  		"* Must contain upper and lower case characters<br>" + 
		  		"* Must contain numbers and special characters");
		}
		
		else  {
			
			   try {  int flag=0;
				      Connection con=Database.databaseconnection();
				      PreparedStatement stmt =con.prepareStatement("select username from users where username=?");
				      stmt.setString(1,request.getParameter("username1"));
				      ResultSet rs=stmt.executeQuery();
				      
				      while(rs.next())
				      {flag++;}
				      
				      if(flag>0)
				      {out.println("<br>Username already exists");		        
				       con.close();  
				      request.getRequestDispatcher("signup.html").include(request, response);}
				      
				      
				      else
				       {
				    	  PreparedStatement stmt1=con.prepareStatement("insert into users(firstname,lastname,username,password) values(?,?,?,?)");
				    	   stmt1.setString(1,fname);
				    	   stmt1.setString(2,lname);
				    	   stmt1.setString(3,username);
				    	   stmt1.setString(4,passw);
				    	   stmt1.executeUpdate();
				    	  
				    	   con.close();
				    	   
						  request.getRequestDispatcher("main.html").forward(request, response);

				    	   
				         }
				      
				   
			        }
			   
			   catch(Exception e)
			   {out.println("error caught"+" "+e);}
			   
			
			
			
			
		}
		
		
	}

}
