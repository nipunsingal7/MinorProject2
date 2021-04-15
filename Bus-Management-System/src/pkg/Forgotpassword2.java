package pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pkg.ConductorCredential;

import pkg.Database;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import pkg.OtpVerification;
import pkg.Mailclass;

/**
 * Servlet implementation class Forgotpasswd2
 */
@WebServlet("/Forgotpassword2")
public class Forgotpassword2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forgotpassword2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String button=request.getParameter("button1");
		String email=request.getParameter("username1");
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
		 
		 if(button.equals("Back"))
		    {
			  request.getRequestDispatcher("main.html").forward(request, response);
		    }
		 
		 else if(email!=null)
		   {  
			   SimpleDateFormat ftm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   try { Connection con=Database.databaseconnection();
			   
		                if(button.equals("User Password"))
		                  {
		    	            Statement stm=con.createStatement();
		    	            ResultSet rs=stm.executeQuery("select * from users where email='"+email+"' AND validation='yes' ");
		    	     
		    	     
		    	             if(rs.next())
		    	               {   
		    	            	 HttpSession s9=request.getSession();
		    	            	 s9.setMaxInactiveInterval(360);
		    	            	 s9.setAttribute("email",email);
		    	            	 s9.setAttribute("type","user");
		    	            	 s9.setAttribute("count",0);
		    	    	 
		    	            	 Timestamp dt=rs.getTimestamp("DTime");	    	    	 
		    	            	 
		    	     
		    	            	  if(dt!=null)
		    	            	   {  
		    	            		   String str=ftm.format(dt);
			    	            	   Date date1=ftm.parse(str);
		    	            		  
		    	            		   if(OtpVerification.timevalidation(date1)==1)
		    	            		    {
		    	            			   con.close();
		    	            			   ConductorCredential.otp("user", email);		    	    	 
				    	    	   
		    	            			   request.getRequestDispatcher("newpassword.html").forward(request, response);
		    	            		    }
		    	    		 
		    	            		   else
		    	            		   {
		    	            			   Mailclass.sendmail(email,"Your One-time-password (OTP) is  "+rs.getString("otp"));
		    	            			   con.close();
		    	            			   request.getRequestDispatcher("newpassword.html").forward(request, response);
		    	            		   }
		    	    		 
		    	            	   }
		    	    	 
		    	            	   else
		    	            	    {con.close();
		    	            	     ConductorCredential.otp("user", email);		    	    	 		    	    	 
		    	            	      request.getRequestDispatcher("newpassword.html").forward(request, response);
		    	            	    }
		    	    	  
		    	               }
		    	     
		    	             else
		    	             {con.close();
		    	             request.getRequestDispatcher("forgotpasswd2.html").include(request, response);
		    	             out.println("<br><font color=red>Email id not found</font>");
		    	             }
		    	
		                  }
		 
		           
		           
		                if(button.equals("Staff Password"))
		                {
		                	Statement stm1=con.createStatement();
		                	ResultSet rs1=stm1.executeQuery("select * from conductorcredential where email='"+email+"' AND validation='yes' ");
			    	        
			    	     
		                	 if(rs1.next())
		                	  {   
			    	    	   HttpSession s9=request.getSession();
				   		       s9.setMaxInactiveInterval(360);
				   		       s9.setAttribute("email",email);
				   		       s9.setAttribute("type","staff");
				   		       s9.setAttribute("count",0); 
				   		     
			    	    	 
				   		       Timestamp dt1=rs1.getTimestamp("DTime");	    	    	 
				   		       
				   		        if(dt1!=null)
				   		          { 
				   		        	   String str1=ftm.format(dt1);
						   		       Date date2=ftm.parse(str1);
				   		        	
				   		        	 if(OtpVerification.timevalidation(date2)==1)
				   		        	   { 
				   		        		 con.close();
				   		        		 ConductorCredential.otp("staff", email);		    	    	 
					    	    	 
				   		        		 request.getRequestDispatcher("newpassword.html").forward(request, response);
				   		        		
				   		        	   }
			    	    		 
				   		        	 
				   		        	 else
				   		        	  {
				   		        		 Mailclass.sendmail(email,"Your One-time-password (OTP) is  "+rs1.getString("otp"));
				   		        		 con.close();
				   		        		 request.getRequestDispatcher("newpassword.html").forward(request, response);
				   		        		
				   		        	  }
			    	    		 
				   		          }
			    	    	 
				   		        else
				   		        {con.close();
				   		        ConductorCredential.otp("staff", email);		    	    	 			    	    	 
				   		        request.getRequestDispatcher("newpassword.html").forward(request, response);
				   		        } 
				   		        
		                	  }
			    	     
		                	 else
		                	 {con.close();
		                	 request.getRequestDispatcher("forgotpasswd2.html").include(request, response);
		                	 out.println("<br><font color=red>Email id not found</font>");
		                	 }

		        	  
		    	
		                }
			   }
		   
		      catch(Exception e)
		      { 
		    	  request.getRequestDispatcher("forgotpasswd2.html").include(request, response);
		    	  out.println("<br><font color=red>"+e+"</font>");
		      }
		 
		 }
		 
		else
		 {request.getRequestDispatcher("forgotpasswd2.html").forward(request, response);
		  out.println("<br><font color=red>Please enter email id</font>");
		 }
		
	}

}
