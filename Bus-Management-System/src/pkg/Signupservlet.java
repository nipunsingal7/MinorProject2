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
		 
	String button=request.getParameter("b1");
		
	if("Back".equals(button))
	{request.getRequestDispatcher("main.html").forward(request, response); }
		
		
    else {response.setContentType("html");
		 PrintWriter out=response.getWriter();
		 String fname=request.getParameter("firstname").trim();
		 String lname=request.getParameter("lastname").trim();
		 String email=request.getParameter("email").trim();
		 String username=request.getParameter("username1");
		 String passw=request.getParameter("password1");		
		 String phoneno=request.getParameter("phoneno").trim();
		 
		try { Connection con=Database.databaseconnection();
			 
		       Statement stmt =con.createStatement();
	           ResultSet rs=stmt.executeQuery("select username from users where username='"+username+"' AND validation='yes'");
	      
	           Statement stmt2 =con.createStatement();
	           ResultSet rs1=stmt2.executeQuery("select email from users where email='"+email+"' AND validation='yes'");
			
	           Statement stmt3 =con.createStatement();
	           ResultSet rs2=stmt3.executeQuery("select phoneno from users where phoneno='"+phoneno+"' AND validation='yes'");
			
		
		
		
		if(fname.equals("") || lname.equals("") || username.equals("") || passw.equals(""))
		{ con.close();
		 request.getRequestDispatcher("signup.html").include(request,response);
		out.println("<br><font color=red> All fields are required. They cannot be empty</font>");}
		
		
		else if(SignupValidator.namevalidate(username)==1)
		{  con.close();
			 request.getRequestDispatcher("signup.html").include(request,response);
		 out.println("<br><font color=red>invalid username</font><br><br> * username should contain 8 to 30 characters <br> "+
		          "* username should start with alphabetic characters only<br>"+
				  " * username can only have alphabets, digits and underscore. No white space allowed");
		}
		
		else if(rs.next())
		{  con.close();
	       out.println("<br><font color=red>Username already exists</font>");		        				         
	      request.getRequestDispatcher("signup.html").include(request, response);}
			
		
		else if(SignupValidator.passwdvalidate(passw)==1)
		{  con.close();
			 request.getRequestDispatcher("signup.html").include(request,response);
		   out.println("<br><font color=red>invalid password</font><br><br>* Minimum password length of 8<br>" + 
		  		"* Must contain upper and lower case characters<br>" + 
		  		"* Must contain numbers and special characters");
		}
		
		
		else if(SignupValidator.emailcheck(email)==1)
		{ con.close();
		   request.getRequestDispatcher("signup.html").include(request,response);
		out.println("<br><font color=red>invalid e-mail id</font>");
		  }
		
		else if(rs1.next())
	       {  con.close();
		     out.println("<br><font color=red>Email already exists</font>");		        				         
		      request.getRequestDispatcher("signup.html").include(request, response);
	       }
		
		else if(SignupValidator.phonecheck(phoneno)==1)
		{ con.close();
			request.getRequestDispatcher("signup.html").include(request,response);
		out.println("<br><font color=red>Invalid phone number</font>");
		}
		
		else if(rs2.next())
		{  con.close();
	       out.println("<br><font color=red>Phoneno already exists</font>");		        				         
	      request.getRequestDispatcher("signup.html").include(request, response);}
		
	
		
		else  {     
				          long phone1=Long.parseLong(phoneno);
				    	  PreparedStatement stmt1=con.prepareStatement("insert into users(firstname,lastname,email,phoneno,username,password,validation) values(?,?,?,?,?,?,?)");
				    	   stmt1.setString(1,fname);
				    	   stmt1.setString(2,lname);
				    	   stmt1.setString(3,email);
				    	   stmt1.setLong(4,phone1);
				    	   stmt1.setString(5,username);
				    	   stmt1.setString(6,passw);
				    	   stmt1.setString(7,"yes");
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
