package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pkg.Database;


/**
 * Servlet implementation class Bookticket
 */
@WebServlet("/Bookticket")
public class Bookticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bookticket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String button=request.getParameter("button2");
		
		 HttpSession s1=request.getSession(false);
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
	
	 if(s1!=null)
			 
		 
	 {	
		 if("Back".equals(button))
		 { 
			request.getRequestDispatcher("dashboard.html").forward(request,response); 
		 }	 
		 
		 
		 if("Select_seat".equals(button))
		 {  
			    String bus=request.getParameter("rbutton");
				
			    s1.setAttribute("bus",bus);
			    
				String src=(String)s1.getAttribute("from");
				String dest=(String)s1.getAttribute("to");
			    SimpleDateFormat ftm3=new SimpleDateFormat("yyyy-MM-dd");
			    String date=ftm3.format((Date)s1.getAttribute("date"));
		 
		       try { Connection con=Database.databaseconnection();
		               
		             int passenger=Integer.parseInt(request.getParameter("passenger"));
				       
		        
		             Statement stmt=con.createStatement();
		             ResultSet rs=stmt.executeQuery("select seats from bus where busno='"+bus+"'");
		             rs.next();
		             
		             int total_seat=rs.getInt("seats");
		             
		             int[] seat=new int[total_seat];
		             
		            Statement stmt1=con.createStatement();
		             ResultSet rs1=stmt1.executeQuery("select seat_no from ticket where busno_7='"+bus+"' AND source='"+src+"' AND destination='"+dest+"' AND bdate='"+date+"' AND validation='yes'");
		              	             
     	             
     	              while(rs1.next())
     	              {  
     	            	  seat[(rs1.getInt("seat_no"))-1]=1;
     	            	  
     	              }
		       
     	              
		                  out.println("<html> <head> <title>Select Seat</title> </head> <body><form action=Bookticket2 method=post>");  
			  		   
		            	    for(int p=1;p<=passenger;p++)
		            	      {	 
		                        out.println( "&ensp;&emsp;&emsp;Enter Passenger Name:&emsp;<input type=text name=pname size=15/>&emsp;&emsp; Age:&emsp;<input type=number name=page min=1 size=7/><br><br>");      
			  		            
		            		   }
		            		 
		                 
		            	  out.println("&emsp;&emsp;&emsp;<input type=submit name=button3 value=Book_ticket />"+                          
		      		     "&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=button3 value=Back /><br><br><br><br>"+
		            	"<h3>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; Bus Front</h3>"+
		      		     "<style>th,td{padding: 15px }</style><table><tr><th></th><th></th><th>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</th><th></th><th></th></tr><tr>");
                            
		            	  
		            	    for(int y=1;y<=total_seat;y++)
		            	    {  
		            	    	if(seat[y-1]==1)
		            	        { out.println("<td><font color=red>Booked</font></td>"); }
		            	     
		            	       if(seat[y-1]==0)
		            	       {out.println("<td>"+y+"<input type=checkbox name=checkb value="+y+" /></td>");  }
		            	       
		            	       if(y%5==0)
		            	       {out.println("</tr><tr>"); }
		            	    	  
		            	    }
		            		 
		            	   out.println("</tr></table><br><h3>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; Bus Back</h3></form></body></html>");
		   
	                         
                         con.close();
                        
	              }
		       
		       
	 
	             catch(Exception e)
	             {request.getRequestDispatcher("dashboard.html").include(request, response);
					out.println("<br><br><font color=red>enter all details correctly</font>"); 
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
