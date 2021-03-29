package pkg;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import pkg.Database;
import pkg.SignupValidator;
import pkg.ConductorCredential;

/**
 * Servlet implementation class Busservlet
 */
@WebServlet("/Addservlet")
public class Addservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addservlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String button=request.getParameter("button2");
		HttpSession s2=request.getSession(false);
		response.setContentType("html");
		 PrintWriter out=response.getWriter();
		 
	if(s2!=null)
	{
		try
    	{Connection con=Database.databaseconnection();
    	String busno=request.getParameter("busno1").trim();	
    	    if("Back".equals(button))
    	    {   con.close();
    	    	request.getRequestDispatcher("dashboard1.html").forward(request, response);
    	    }
			
			
			if("Add Bus".equals(button))
			{
				
				String company=request.getParameter("company1").trim();
				String model=request.getParameter("model1").trim();
				int seats=Integer.parseInt(request.getParameter("seats1"));
	            String[] day=new String[7];
				day[0]=request.getParameter("days1");
	            day[1]=request.getParameter("days2");
	            day[2]=request.getParameter("days3");
	            day[3]=request.getParameter("days4");
	            day[4]=request.getParameter("days5");
	            day[5]=request.getParameter("days6");
	            day[6]=request.getParameter("days7");
	            
				PreparedStatement stmt=con.prepareStatement("insert into bus(busno,company,model,seats,validation) values(?,?,?,?,?)");	
			    stmt.setString(1, busno);
			    stmt.setString(2, company);
			    stmt.setString(3, model);
			    stmt.setInt(4, seats);
			    stmt.setString(5, "yes");
			   
			    stmt.executeUpdate();
			    
			    PreparedStatement stmt0=con.prepareStatement("insert into busroute values(?,?,?,?,?)");
			    stmt0.setString(1, busno);
			    stmt0.setString(2, request.getParameter("starting1").trim());
			    stmt0.setString(3, request.getParameter("atime"));
			    stmt0.setInt(4,1);
			    stmt0.setString(5,"yes");
			    stmt0.addBatch();
			  
			    stmt0.setString(1, busno);
			    stmt0.setString(2, request.getParameter("destination1").trim());
			    stmt0.setString(3, request.getParameter("atime0"));
			    stmt0.setInt(4,2);
			    stmt0.setString(5,"yes");
			    stmt0.addBatch();
			    
			    stmt0.setString(1, busno);
			    stmt0.setString(2, request.getParameter("stop1").trim());
			    stmt0.setString(3, request.getParameter("atime1"));
			    stmt0.setInt(4,3);
			    stmt0.setString(5,"yes");
			    stmt0.addBatch();
			    
			    stmt0.setString(1, busno);
			    stmt0.setString(2, request.getParameter("stop2").trim());
			    stmt0.setString(3, request.getParameter("atime2"));
			    stmt0.setInt(4,4);
			    stmt0.setString(5,"yes");
			    stmt0.addBatch();
			    
			    stmt0.setString(1, busno);
			    stmt0.setString(2, request.getParameter("stop3").trim());
			    stmt0.setString(3, request.getParameter("atime3"));
			    stmt0.setInt(4,5);
			    stmt0.setString(5,"yes");
			    stmt0.addBatch();
			    
			    stmt0.setString(1, busno);
			    stmt0.setString(2, request.getParameter("stop4").trim());
			    stmt0.setString(3, request.getParameter("atime4"));
			    stmt0.setInt(4,6);
			    stmt0.setString(5,"yes");
			    stmt0.addBatch();
			    
			    stmt0.executeBatch();
			    
			    PreparedStatement stmt01=con.prepareStatement("insert into days values(?,?,?,?)");
			    
			    for(int z=0;z<7;z++)
			    {
			    	stmt01.setString(1, busno);
				    stmt01.setString(2, day[z]);
				    stmt01.setString(3,"yes");
				    stmt01.setInt(4,z+1);
				    stmt01.addBatch();
			    }
			    
			    stmt01.executeBatch();
			    
			    con.close();
			    request.getRequestDispatcher("addbus.html").include(request, response);
			    out.println("<br><font color=green>bus added</font>");
		    }
			
			
			
			if("Add Owner".equals(button))
			{ String phone01=request.getParameter("phone1").trim();
				
				PreparedStatement stmt1=con.prepareStatement("select * from owner where busno_1=? AND validation=?");
				 stmt1.setString(1, busno);
				 stmt1.setString(2, "yes");
				 ResultSet rs=stmt1.executeQuery();
				
				 Statement stm =con.createStatement();
		           ResultSet rsa=stm.executeQuery("select phoneno from owner where phoneno='"+phone01+"' AND validation='yes'");
				 
				 if(rs.next())
				 {   con.close();
					 request.getRequestDispatcher("addowner.html").include(request, response);
						out.println("<br><font color=red>First delete the owner data to add new one or you can modify the existing data</font>");
				 }
				 
				 
				 else if(SignupValidator.phonecheck(phone01)==1)
				 {
					 request.getRequestDispatcher("addowner.html").include(request,response);
						out.println("<br><font color=red>Invalid phone number</font>");
				 }
				 
				 else if(rsa.next())
				 {   con.close();
				 request.getRequestDispatcher("addowner.html").include(request, response);
					out.println("<br><font color=red>phone no already exist</font>");
			 }
				 
				 else
				 {PreparedStatement stmt2=con.prepareStatement("insert into owner values(?,?,?,?,?,?,?,?,?)");
				 stmt2.setString(1,request.getParameter("owner1").trim());
				 stmt2.setString(2,request.getParameter("address1").trim());
				 stmt2.setString(3,request.getParameter("city1").trim());
				 stmt2.setString(4,request.getParameter("state1").trim());
				 stmt2.setString(5,request.getParameter("phone1").trim());
				 stmt2.setString(6,request.getParameter("rc1").trim());				 
				 stmt2.setString(7,request.getParameter("aadhar1").trim());
				 stmt2.setString(8,busno);
				 stmt2.setString(9, "yes");
					
				   stmt2.executeUpdate();
				    con.close();
				    request.getRequestDispatcher("dashboard1.html").include(request, response);
				    out.println("<br><font color=green>owner added</font>");
					 
				 }	 
				 
				
				
			}
			
			
			
			if("Add Driver".equals(button))
			{  String phone02=request.getParameter("phone2").trim();
				PreparedStatement stmt3=con.prepareStatement("select * from driver where busno_2=? AND validation=?");
				 stmt3.setString(1, busno);
				 stmt3.setString(2, "yes");
				 ResultSet rs1=stmt3.executeQuery();
				 
				 Statement stm1 =con.createStatement();
		           ResultSet rsa1=stm1.executeQuery("select phoneno from driver where phoneno='"+phone02+"' AND validation='yes'");
				 
				 
				 if(rs1.next())
				 {   con.close();
					 request.getRequestDispatcher("adddriver.html").include(request, response);
						out.println("<br><font color=red>First delete the driver data to add new one or you can modify the existing data</font>");
				 }
				 
				 
				 else if(SignupValidator.phonecheck(phone02)==1)
				 {
					 request.getRequestDispatcher("adddriver.html").include(request,response);
						out.println("<br><font color=red>Invalid phone number</font>");
				 }
				 
				 else if(rsa1.next())
				 {   con.close();
				 request.getRequestDispatcher("adddriver.html").include(request, response);
					out.println("<br><font color=red>phone no already exist</font>");
			 }

				 
				 else
				 {PreparedStatement stmt4=con.prepareStatement("insert into driver values(?,?,?,?,?,?,?,?,?)");
				 stmt4.setString(1,request.getParameter("driver2").trim());
				 stmt4.setString(2,request.getParameter("address2").trim());
				 stmt4.setString(3,request.getParameter("city2").trim());
				 stmt4.setString(4,request.getParameter("state2").trim());
				 stmt4.setString(5,request.getParameter("phone2").trim());
				 stmt4.setString(6,request.getParameter("license2").trim());				 
				 stmt4.setString(7,request.getParameter("aadhar2").trim());
				 stmt4.setString(8,busno);
				 stmt4.setString(9, "yes");
					
				   stmt4.executeUpdate();
				    con.close();
				    request.getRequestDispatcher("dashboard1.html").include(request, response);
				    out.println("<br><font color=green>driver added</font>");
					 
				 }	 
				 
				
				
			}
			
			
			
			if("Add Conductor".equals(button))
			{    String emailid=request.getParameter("email3").trim();
				 String phone03=request.getParameter("phone3").trim();
			
			     PreparedStatement stmt5=con.prepareStatement("select * from conductor where busno_3=? AND validation=?");
				 stmt5.setString(1, busno);
				 stmt5.setString(2, "yes");
				 ResultSet rs2=stmt5.executeQuery();
				 
				 PreparedStatement stmt7=con.prepareStatement("select * from conductor where email=? AND validation=?");
				 stmt7.setString(1,emailid);
				 stmt7.setString(2, "yes");
				 ResultSet rs3=stmt7.executeQuery();
				 

				 Statement stm2 =con.createStatement();
		           ResultSet rsa2=stm2.executeQuery("select phoneno from conductor where phoneno='"+phone03+"' AND validation='yes'");
				 
				 
				 if(rs2.next())
				 {   con.close();
					 request.getRequestDispatcher("addconductor.html").include(request, response);
						out.println("<br><font color=red>First delete the conducter data to add new one or you can modify the existing data</font>");
				 }
				 
				 else if(SignupValidator.phonecheck(phone03)==1)
				 {
					 request.getRequestDispatcher("addconductor.html").include(request,response);
						out.println("<br><font color=red>Invalid phone number</font>");
				 }
				 
				 else if(rsa2.next())
				 {   con.close();
				 request.getRequestDispatcher("addconductor.html").include(request, response);
					out.println("<br><font color=red>phone no already exist</font>");
			 }
				 
				 else if(SignupValidator.emailcheck(emailid)==1)
				 {
					 request.getRequestDispatcher("addconductor.html").include(request,response);
						out.println("<br><font color=red>invalid e-mail id</font>");
				 }	
				 
				 else if(rs3.next())
				 {
					 request.getRequestDispatcher("addconductor.html").include(request,response);
						out.println("<br><font color=red>e-mail already there </font>");

				 }

				 else
				 {PreparedStatement stmt6=con.prepareStatement("insert into conductor values(?,?,?,?,?,?,?,?,?)");
				 stmt6.setString(1,request.getParameter("conductor3").trim());
				 stmt6.setString(2,request.getParameter("address3").trim());
				 stmt6.setString(3,request.getParameter("city3").trim());
				 stmt6.setString(4,request.getParameter("state3").trim());
				 stmt6.setString(5,request.getParameter("phone3").trim());
				 stmt6.setString(6,emailid);				 
				 stmt6.setString(7,request.getParameter("aadhar3").trim());
				 stmt6.setString(8,busno);
				 stmt6.setString(9, "yes");
				 				 
				 stmt6.executeUpdate();
				    con.close();
				    
				    ConductorCredential.credentials(emailid,busno);	
				    
				    request.getRequestDispatcher("dashboard1.html").include(request, response);
				    out.println("<br><font color=green>Conductor added and mail send</font>");
					 
				 }	 
				 
				 
				
				
			}
			
			
			
			
		 }
			
			catch(Exception e)
			{request.getRequestDispatcher("dashboard1.html").include(request, response);
				out.println("<br><font color=red>error catched:"+" "+e+" "+"</font>");
		    }
			
		}
		
		 else
		{ 
			 
			  request.getRequestDispatcher("main.html").include(request, response);
			  out.println("<br><font color=red>Login first....</font>");
		 }
		
		
		
		
	}

}
