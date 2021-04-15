package pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pkg.SignupValidator;
import pkg.OtpVerification;
import pkg.Database;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Servlet implementation class Newpassword1
 */
@WebServlet("/Newpassword1")
public class Newpassword1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Newpassword1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String button=request.getParameter("buttons2");
		String password1=request.getParameter("password");
		String password2=request.getParameter("password1");
		String otp=request.getParameter("otp");
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
	    HttpSession s9=request.getSession(false);
	    
	if(s9!=null)
	    
	{
		   try {  Connection con=Database.databaseconnection();
		          Statement stm=con.createStatement();
		          ResultSet rs;
		       
		      
		           
		            if(button.equals("Cancel"))
		               {  s9.invalidate();
		            	   con.close();
			               request.getRequestDispatcher("main.html").forward(request, response);
		               }
		 

		          if(button.equals("Submit"))
		             {
			 
			              if(password2.equals(password1))
				              {  
					
			            	       if(SignupValidator.passwdvalidate(password1)!=1)
					                  { 
						                String type=(String)s9.getAttribute("type");
						                String email=(String)s9.getAttribute("email");
						                SimpleDateFormat ftm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						
					                     
						                if("user".equals(type))
						                   {
							                  rs=stm.executeQuery("select * from users where email='"+email+"'");
	                                          rs.next();
	                                          Timestamp dt=rs.getTimestamp("DTime");	    	    	 
			    	    	                  String str=ftm.format(dt);
			    	    	                  Date date1=ftm.parse(str);
			    	    	 
			    	    	 
			    	    	                  if(OtpVerification.timevalidation(date1)!=1)
			    	    	                     {
			    	    		                   
			    	    	                	    int count=(int)s9.getAttribute("count");
			    	    		                    count++;
			    	    		                    s9.setAttribute("count", count);
			    	    		 
			    	    		 
			    	    		                    if(otp.equals(rs.getString("otp")))
			    	    		                       {
			    	    			 
			    	    		                    	  Statement stmt1=con.createStatement();
			    	    			                      stmt1.executeUpdate("update users set password='"+password1+"' where email='"+email+"' ");			    	    		
			    	    			                      s9.invalidate();
			    	    		                      	  con.close();
			    	    			                      request.getRequestDispatcher("main.html").include(request,response);
					    	    	                     out.println("<br><font color=green>Password successfully changed</font>"); 
			    	    		                        }
			    	    		 
			    	    		                    
			    	    		                    else
			    	    		                      { 
			    	    			                       if(count<3)
			    	    		                              {con.close();
			    	    			                    	   request.getRequestDispatcher("newpassword.html").include(request,response);
				    	    	                                out.println("<br><font color=red>Wrong OTP</font>");
			    	    		                               }
			    	    			
			    	    			                       else
			    	    			                          {s9.invalidate();
			    	    			                          con.close();
			    	    			                    	   request.getRequestDispatcher("main.html").include(request,response);
				    	    	                              out.println("<br><font color=red>Wrong OTP limit exceeded</font>");
				    	    	                              }
			    	    		 
			    	    		                      }
			    	    	                     }
			    	    	 
			    	    	         
			    	    	               else
			    	    	                 {con.close();
			    	    	                  s9.invalidate(); 
			    	    	                  request.getRequestDispatcher("forgotpasswd2.html").include(request,response);
			    	    	                  out.println("<br><font color=red>OTP expired. Proceed again</font>"); 
			    	    	                 }
			    	    	 
						              }
						
						      
						       else
						         {
							        rs=stm.executeQuery("select * from conductorcredential where email='"+email+"'");
	                                rs.next();
	                                Timestamp dt=rs.getTimestamp("DTime");	    	    	 
			    	    	        String str=ftm.format(dt);
			    	    	        Date date1=ftm.parse(str);
			    	    	 
			    	    	 
			    	    	       if(OtpVerification.timevalidation(date1)!=1)
			    	    	          {
			    	    		         int count=(int)s9.getAttribute("count");
			    	    		         count++;
			    	    		         s9.setAttribute("count", count);
			    	    		 
			    	    		 
			    	    		         if(otp.equals(rs.getString("otp")))
			    	    		           {
			    	    		        	 Statement stmt1=con.createStatement();
			    	    		        	 stmt1.executeUpdate("update conductorcredential set password='"+password1+"' where email='"+email+"' ");			    	    			 
			    	    		        	 s9.invalidate();
			    	    		        	 con.close();
			    	    		        	 request.getRequestDispatcher("main.html").include(request,response);
			    	    		        	 out.println("<br><font color=green>Password successfully changed</font>"); 
			    	    		           }
			    	    		 
			    	    		         
			    	    		          else
			    	    		            { 
			    	    		        	    if(count<3)
			    	    		        	      {con.close();
			    	    		        	    	request.getRequestDispatcher("newpassword.html").include(request,response);
			    	    		        	       out.println("<br><font color=red>Wrong OTP</font>");
			    	    		        	      }
			    	    			
			    	    		        	     else
			    	    		        	       {con.close();
			    	    		        	    	 s9.invalidate();
			    	    		        	    	 request.getRequestDispatcher("main.html").include(request,response);
			    	    		        	       out.println("<br><font color=red>Wrong OTP limit exceeded</font>");
			    	    		        	       }
			    	    		 
			    	    		             }
			    	    	           }
			    	    	 
			    	    	          else
			    	    	           {con.close();
			    	    	           s9.invalidate();  
			    	    	           request.getRequestDispatcher("forgotpasswd2.html").include(request,response);
			    	    	             out.println("<br><font color=red>OTP expired. Proceed again</font>"); 
			    	    	            }
							
							
						            }
						
					    
					
					           }
					
					
			            	    else
			            	     {  
			            	    	con.close(); 
			            	    	request.getRequestDispatcher("newpassword.html").include(request,response);
			            	    	out.println("<br><font color=red>invalid password</font><br><br>* Minimum password length of 8<br>" + 
			            	    			"* Must contain upper and lower case characters<br>" + 
			            	    			"* Must contain numbers and special characters");
			            	     }
					
					
				       }
			 
			 
				
			            else
			            {  
			            	con.close();
			            	request.getRequestDispatcher("newpassword.html").include(request, response);
			            	out.println("<br><font color=red>Password does not matched. Again fill the password</font>");
			            }	
				 
			 
		        }
		
		 
          }  catch(Exception e)
             {s9.invalidate();
        	  request.getRequestDispatcher("forgotpasswd2.html").include(request, response);
        	  out.println("<br><font color=red>"+e+"</font>");}
  
		 
	}
	
	else
	{request.getRequestDispatcher("forgotpasswd2").include(request, response);
	out.println("<br><font color=red>Session expired. Try again</font>");
	}		
	
}

}
