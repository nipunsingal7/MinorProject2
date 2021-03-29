package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Record1
 */
@WebServlet("/Record1")
public class Record1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Record1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String button=request.getParameter("button");
		String button1=request.getParameter("0button");
		
		 HttpSession s2=request.getSession(false);
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
		 
		 if(s2!=null)			 		 
	{	
		
	     if("Back".equals(button1))
		  {
			 request.getRequestDispatcher("busrecords.html").forward(request, response);
		  }	
				
	   
	   if("Back".equals(button))
		{
			request.getRequestDispatcher("dashboard1.html").forward(request, response);
		}	
		
			 
      if("Show".equals(button))	 
      {  String rbutton=request.getParameter("rbutton");
   
     
    	  try
		 {   Connection con= Database.databaseconnection();
		      Statement stmt=con.createStatement();	 
		       ResultSet rs;
		       
		       
		      
			 if("bus".equals(rbutton))
			 {
				 rs=stmt.executeQuery("select * from bus t1,busroute t2,days t3 where busno='"+request.getParameter("busbox")+"' AND bus_no='"+request.getParameter("busbox")+"' AND busno_6='"+request.getParameter("busbox")+"'");
				 
				
				 if(!(rs.next()))
				 {    con.close();
					 request.getRequestDispatcher("busrecords.html").include(request, response);
					  out.println("<br><br><font color=red>No record found</font>");				 
				 }
				  
				  
				 else
				  {
					  out.println("<html><head><title>Bus records</title></head><body><style>table,th,td {border: 2px solid black; border-collapse: collapse} th,td{padding: 10px}</style><table>"+
				       "<tr><th>BusNo</th><th>Company</th><th>Model</th><th>seats</th><th>Validation</th><th>Routes</th><th>Time</th><th>Days</th></tr> "
				       +" <tr><td>"+rs.getString("busno")+"</td><td>"+rs.getString("company")+"</td><td>"+rs.getString("model")+"</td><td>"+rs.getString("seats")+"</td><td>"+rs.getString("t1.validation")+"</td><td>"+rs.getString("route")+"</td><td>"+rs.getString("AVTime")+"</td><td>"+rs.getString("days")+"</td></tr>" );
					  
					  while(rs.next())
					  {
						 out.println(" <tr><td>"+rs.getString("busno")+"</td><td>"+rs.getString("company")+"</td><td>"+rs.getString("model")+"</td><td>"+rs.getString("seats")+"</td><td>"+rs.getString("t1.validation")+"</td><td>"+rs.getString("route")+"</td><td>"+rs.getString("AVTime")+"</td><td>"+rs.getString("t3.days")+"</td></tr>");  
					  }  
					  
					  out.println("</table><form action=Record1 method=post><br><br><br>&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=0button value=Back></form></body></html>");
					  con.close();
				  }
				 
				 
			 }
			 
			 
			 
			 
			
			  if("conductor".equals(rbutton))
			 {
				  rs=stmt.executeQuery("select * from conductor t1,conductorcredential t2 where busno_3='"+request.getParameter("busbox")+"' AND busno_4='"+request.getParameter("busbox")+"' ");
					 
					
					 if(!(rs.next()))
					 {    con.close();
						 request.getRequestDispatcher("busrecords.html").include(request, response);
						  out.println("<br><br><font color=red>No record found</font>");				 
					 }
					  
					  
					 else
					  {
						  out.println("<html><head><title>Bus records</title></head><body><style>table,th,td {border: 2px solid black; border-collapse: collapse} th,td{padding: 10px}</style><table>"+
					       "<tr><th>BusNo</th><th>ID</th><th>Name</th><th>Address</th><th>city</th><th>State</th><th>PhoneNo</th><th>Email</th><th>AadharNo</th><th>Validation</th></tr> "
					       +" <tr><td>"+rs.getString("busno_3")+"</td><td>"+rs.getString("id")+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("address")+"</td><td>"+rs.getString("city")+"</td><td>"+rs.getString("state")+"</td><td>"+rs.getString("phoneno")+"</td><td>"+rs.getString("t1.email")+"</td><td>"+rs.getString("aadharno")+"</td><td>"+rs.getString("t1.validation")+"</td></tr>" );
						  
						  while(rs.next())
						  {
							 out.println(" <tr><td>"+rs.getString("busno_3")+"</td><td>"+rs.getString("id")+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("address")+"</td><td>"+rs.getString("city")+"</td><td>"+rs.getString("state")+"</td><td>"+rs.getString("phoneno")+"</td><td>"+rs.getString("t1.email")+"</td><td>"+rs.getString("aadharno")+"</td><td>"+rs.getString("t1.validation")+"</td></tr>");  
						  }  
						  
						  out.println("</table><form action=Record1 method=post><br><br><br>&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=0button value=Back></form></body></html>");
						  con.close();
					  }
					 
			 }
			  
			  
			  
			  
			
			  if("owner".equals(rbutton))
			 {
				  rs=stmt.executeQuery("select * from owner where busno_1='"+request.getParameter("busbox")+"' ");
					 
					
					 if(!(rs.next()))
					 {    con.close();
						 request.getRequestDispatcher("busrecords.html").include(request, response);
						  out.println("<br><br><font color=red>No record found</font>");				 
					 }
					  
					  
					 else
					  {
						  out.println("<html><head><title>Bus records</title></head><body><style>table,th,td {border: 2px solid black; border-collapse: collapse} th,td{padding: 10px}</style><table>"+
					       "<tr><th>BusNo</th><th>Name</th><th>Address</th><th>city</th><th>State</th><th>PhoneNo</th><th>RcNo</th><th>AadharNo</th><th>Validation</th></tr> "
					       +" <tr><td>"+rs.getString("busno_1")+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("address")+"</td><td>"+rs.getString("city")+"</td><td>"+rs.getString("state")+"</td><td>"+rs.getString("phoneno")+"</td><td>"+rs.getString("rcno")+"</td><td>"+rs.getString("aadharno")+"</td><td>"+rs.getString("validation")+"</td></tr>" );
						  
						  while(rs.next())
						  {
							 out.println(" <tr><td>"+rs.getString("busno_1")+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("address")+"</td><td>"+rs.getString("city")+"</td><td>"+rs.getString("state")+"</td><td>"+rs.getString("phoneno")+"</td><td>"+rs.getString("rcno")+"</td><td>"+rs.getString("aadharno")+"</td><td>"+rs.getString("validation")+"</td></tr>");  
						  }  
						  
						  out.println("</table><form action=Record1 method=post><br><br><br>&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=0button value=Back></form></body></html>");
						  con.close();
					  }
					  
			 }
			  
			  
			  
			  
			
			  if("driver".equals(rbutton))
			 {
				  rs=stmt.executeQuery("select * from driver where busno_2='"+request.getParameter("busbox")+"' ");
					 
					
					 if(!(rs.next()))
					 {    con.close();
						 request.getRequestDispatcher("busrecords.html").include(request, response);
						  out.println("<br><br><font color=red>No record found</font>");				 
					 }
					  
					  
					 else
					  {
						  out.println("<html><head><title>Bus records</title></head><body><style>table,th,td {border: 2px solid black; border-collapse: collapse} th,td{padding: 10px}</style><table>"+
					       "<tr><th>BusNo</th><th>Name</th><th>Address</th><th>city</th><th>State</th><th>PhoneNo</th><th>LicenseNo</th><th>AadharNo</th><th>Validation</th></tr> "
					       +" <tr><td>"+rs.getString("busno_2")+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("address")+"</td><td>"+rs.getString("city")+"</td><td>"+rs.getString("state")+"</td><td>"+rs.getString("phoneno")+"</td><td>"+rs.getString("licenseno")+"</td><td>"+rs.getString("aadharno")+"</td><td>"+rs.getString("validation")+"</td></tr>" );
						  
						  while(rs.next())
						  {
							 out.println(" <tr><td>"+rs.getString("busno_2")+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("address")+"</td><td>"+rs.getString("city")+"</td><td>"+rs.getString("state")+"</td><td>"+rs.getString("phoneno")+"</td><td>"+rs.getString("licenseno")+"</td><td>"+rs.getString("aadharno")+"</td><td>"+rs.getString("validation")+"</td></tr>");  
						  }  
						  
						  out.println("</table><form action=Record1 method=post><br><br><br>&emsp;&emsp;&emsp;&emsp;&emsp;<input type=submit name=0button value=Back></form></body></html>");
						  con.close();
					  }
				  
			 }
			
			  
			  if(rbutton==null)
			  {
				  request.getRequestDispatcher("busrecords.html").include(request, response);
				  out.println("<br><br><font color=red>Fill detials correctly</font>");
			  }
				 
			 
			 
	   }
		 
		 catch(Exception e)
		 {
			 request.getRequestDispatcher("busrecords.html").include(request, response);
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
