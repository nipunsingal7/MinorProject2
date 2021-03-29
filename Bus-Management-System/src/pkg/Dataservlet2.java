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
import pkg.SignupValidator;
import pkg.Database;
/**
 * Servlet implementation class Dataservlet2
 */
@WebServlet("/Dataservlet2")
public class Dataservlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dataservlet2() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
	    	    	request.getRequestDispatcher("data.html").forward(request, response);
	    	    }
				
				
				if("Modify_Bus".equals(button))
				{	
					String[] addday=new String[7];
					addday[0]=request.getParameter("days1");
		            addday[1]=request.getParameter("days2");
		            addday[2]=request.getParameter("days3");
		            addday[3]=request.getParameter("days4");
		            addday[4]=request.getParameter("days5");
		            addday[5]=request.getParameter("days6");
		            addday[6]=request.getParameter("days7");
		  
					
					PreparedStatement stmt=con.prepareStatement("update bus set company=?,model=?,seats=? WHERE busno=?");
					stmt.setString(1,request.getParameter("company2").trim());
					stmt.setString(2,request.getParameter("model2").trim());
					stmt.setInt(3,Integer.parseInt(request.getParameter("seats2")));	
					stmt.setString(4,busno);
					stmt.executeUpdate();
					
					PreparedStatement stmt0=con.prepareStatement("update busroute set route=?,AVTime=? where bus_no=? AND number=? AND validation=?");
					stmt0.setString(1,request.getParameter("starting2").trim());
					stmt0.setString(2,request.getParameter("atime"));
					stmt0.setString(3,busno);
					stmt0.setInt(4,1);
					stmt0.setString(5,"yes");
					stmt0.addBatch();
					
					stmt0.setString(1,request.getParameter("destination2").trim());
					stmt0.setString(2,request.getParameter("atime0"));
					stmt0.setString(3,busno);
					stmt0.setInt(4,2);
					stmt0.setString(5,"yes");
					stmt0.addBatch();
					
					stmt0.setString(1,request.getParameter("stop1").trim());
					stmt0.setString(2,request.getParameter("atime1"));
					stmt0.setString(3,busno);
					stmt0.setInt(4,3);
					stmt0.setString(5,"yes");
					stmt0.addBatch();
					
					stmt0.setString(1,request.getParameter("stop2").trim());
					stmt0.setString(2,request.getParameter("atime2"));
					stmt0.setString(3,busno);
					stmt0.setInt(4,4);
					stmt0.setString(5,"yes");
					stmt0.addBatch();
					
					stmt0.setString(1,request.getParameter("stop3").trim());
					stmt0.setString(2,request.getParameter("atime3"));
					stmt0.setString(3,busno);
					stmt0.setInt(4,5);
					stmt0.setString(5,"yes");
					stmt0.addBatch();
					
					stmt0.setString(1,request.getParameter("stop4").trim());
					stmt0.setString(2,request.getParameter("atime4"));
					stmt0.setString(3,busno);
					stmt0.setInt(4,6);
					stmt0.setString(5,"yes");
					stmt0.addBatch();
					
					stmt0.executeBatch();
					
					PreparedStatement stmt02=con.prepareStatement("update days set days=? where busno_6=? AND number=?");
				    
				    for(int z=0;z<7;z++)
				    {
				    	stmt02.setString(2, busno);
					    stmt02.setString(1,addday[z] );
					    stmt02.setInt(3,z+1);
					    stmt02.addBatch();
				    }
				    
				    stmt02.executeBatch();
				    
					
					con.close();
					request.getRequestDispatcher("data.html").include(request, response);
				    out.println("<br><font color=green>Bus Modified</font>");
					
				}
	    	
				
				if("Delete_Bus".equals(button))
				{ 
				
				PreparedStatement stmt1=con.prepareStatement("update bus set validation=? WHERE busno=?");
				stmt1.setString(1,"no");
				stmt1.setString(2,busno);
				
				PreparedStatement stmt2=con.prepareStatement("update owner set validation=? WHERE busno_1=? AND validation=? ");
				stmt2.setString(1,"no");
				stmt2.setString(2,busno);
				stmt2.setString(3,"yes");
				
				PreparedStatement stmt3=con.prepareStatement("update driver set validation=? WHERE busno_2=? AND validation=?");
				stmt3.setString(1,"no");
				stmt3.setString(2,busno);
				stmt3.setString(3,"yes");
				
				PreparedStatement stmt4=con.prepareStatement("update conductor set validation=? WHERE busno_3=? AND validation=?");
				stmt4.setString(1,"no");
				stmt4.setString(2,busno);
				stmt4.setString(3,"yes");
				
				PreparedStatement stmt11=con.prepareStatement("update conductorcredential set validation=? WHERE busno_4=?");
				stmt11.setString(1,"no");
				stmt11.setString(2,busno);
				
				stmt1.executeUpdate();
				stmt2.executeUpdate();
				stmt3.executeUpdate();
				stmt4.executeUpdate();
				stmt11.executeUpdate();
				
				PreparedStatement stmt01=con.prepareStatement("update busroute set validation='no' where bus_no=?");
				stmt01.setString(1, busno);
				stmt01.executeUpdate();
				
				PreparedStatement stmt02=con.prepareStatement("update days set validation='no' where bus_no=?");
				stmt02.setString(1, busno);
				stmt02.executeUpdate();
				
				con.close();
				request.getRequestDispatcher("data.html").include(request, response);
			    out.println("<br><font color=green>Bus Deleted</font>");
					
				}
				
				
				if("Modify_Owner".equals(button))
				{ String phone0=request.getParameter("phone1").trim();
					
					Statement stm3=con.createStatement();
			     ResultSet rs2=stm3.executeQuery("select phoneno,busno_1 from owner where phoneno='"+phone0+"' AND validation='yes'");
					
					if(SignupValidator.phonecheck(phone0)==1)
					 {   con.close();
						 request.getRequestDispatcher("data.html").include(request,response);
							out.println("<br><font color=red>Invalid phone number</font>");
					 }
					
					else if(rs2.next() && !(busno.equals(rs2.getString("busno_1"))))
					   { con.close();
						request.getRequestDispatcher("data.html").include(request,response);
						out.println("<br><font color=red>phoneno already exist</font>");
						
					   }
					
					else
						{PreparedStatement stmt5=con.prepareStatement("update owner set validation=? WHERE busno_1=? AND validation=?");
					
					stmt5.setString(1,"no");
					stmt5.setString(2,busno);
					stmt5.setString(3,"yes");
					stmt5.executeUpdate();
					
					
					PreparedStatement stmt6=con.prepareStatement("insert into owner values(?,?,?,?,?,?,?,?,?)");
					stmt6.setString(1,request.getParameter("owner1"));
					stmt6.setString(2,request.getParameter("address1"));
					stmt6.setString(3,request.getParameter("city1"));
					stmt6.setString(4,request.getParameter("state1"));
					stmt6.setString(5,request.getParameter("phone1"));
					stmt6.setString(6,request.getParameter("rc1"));
					stmt6.setString(7,request.getParameter("aadhar1"));
					stmt6.setString(8,busno);
					stmt6.setString(9,"yes");
					stmt6.executeUpdate();
					
					con.close();
					request.getRequestDispatcher("data.html").include(request, response);
				    out.println("<br><font color=green>Owner Modified</font>");
				  }
				}
	    	
	    	
				
				if("Delete_Owner".equals(button))
				{ 
				 PreparedStatement stmt7=con.prepareStatement("update owner set validation=? WHERE busno_1=? AND validation=? ");
				 stmt7.setString(1,"no");
				 stmt7.setString(2,busno);
				 stmt7.setString(3,"yes");
				 
				 stmt7.executeUpdate();
				 
				    con.close();
					request.getRequestDispatcher("data.html").include(request, response);
				    out.println("<br><font color=green>Owner Deleted</font>");
				}
	    	
	    	
				
				if("Modify_Driver".equals(button))
				{ String phone01=request.getParameter("phone2").trim();
				
				Statement stm4=con.createStatement();
		     ResultSet rs3=stm4.executeQuery("select phoneno,busno_2 from driver where phoneno='"+phone01+"' AND validation='yes'");
					
					
					if(SignupValidator.phonecheck(phone01)==1)
					 {   con.close();
						 request.getRequestDispatcher("data.html").include(request,response);
							out.println("<br><font color=red>Invalid phone number</font>");
					 }
					
					else if(rs3.next() && !(busno.equals(rs3.getString("busno_2"))))
					   { con.close();
						request.getRequestDispatcher("data.html").include(request,response);
						out.println("<br><font color=red>phoneno already exist</font>");
						
					   }
					
					else
					{PreparedStatement stmt8=con.prepareStatement("update driver set validation=? WHERE busno_2=? AND validation=?");
					
					stmt8.setString(1,"no");
					stmt8.setString(2,busno);
					stmt8.setString(3,"yes");
					stmt8.executeUpdate();
					
					PreparedStatement stmt9=con.prepareStatement("insert into driver values(?,?,?,?,?,?,?,?,?)");
					stmt9.setString(1,request.getParameter("driver2"));
					stmt9.setString(2,request.getParameter("address2"));
					stmt9.setString(3,request.getParameter("city2"));
					stmt9.setString(4,request.getParameter("state2"));
					stmt9.setString(5,request.getParameter("phone2"));
					stmt9.setString(6,request.getParameter("license2"));
					stmt9.setString(7,request.getParameter("aadhar2"));
					stmt9.setString(8,busno);
					stmt9.setString(9,"yes");
					stmt9.executeUpdate();
					
					con.close();
					request.getRequestDispatcher("data.html").include(request, response);
				    out.println("<br><font color=green>Driver Modified</font>");
					}
				}
	    	
	    	
				
				if("Delete_Driver".equals(button))
				{ 
				 PreparedStatement stmt10=con.prepareStatement("update driver set validation=? WHERE busno_2=? AND validation=? ");
				 stmt10.setString(1,"no");
				 stmt10.setString(2,busno);
				 stmt10.setString(3,"yes");
				 
				 stmt10.executeUpdate();
				 
				    con.close();
					request.getRequestDispatcher("data.html").include(request, response);
				    out.println("<br><font color=green>Driver Deleted</font>");
				}
				
				
				
				if("Modify_Conductor".equals(button))
				{   String phone=request.getParameter("phone3").trim();
					String email1=request.getParameter("email3").trim();
					Statement stm1=con.createStatement();
				     ResultSet rs=stm1.executeQuery("select email,busno_3 from conductor where email='"+email1+"' AND validation='yes'");
				     
				     Statement stm2=con.createStatement();
				     ResultSet rs1=stm2.executeQuery("select phoneno,busno_3 from conductor where phoneno='"+phone+"' AND validation='yes'");
					
					if(SignupValidator.emailcheck(email1)==1)
					 {  con.close();
						 request.getRequestDispatcher("data.html").include(request,response);
							out.println("<br><font color=red>invalid e-mail id</font>");
					 }	
					
					else if(rs.next() && !(busno.equals(rs.getString("busno_3"))))
					   { con.close();
						request.getRequestDispatcher("data.html").include(request,response);
						out.println("<br><font color=red>e-mail id already exist</font>");
						
					   }
					
					else if(SignupValidator.phonecheck(phone)==1)
					 {   con.close();
						 request.getRequestDispatcher("data.html").include(request,response);
							out.println("<br><font color=red>Invalid phone number</font>");
					 }
					
					else if(rs1.next() && !(busno.equals(rs1.getString("busno_3"))))
					   { con.close();
						request.getRequestDispatcher("data.html").include(request,response);
						out.println("<br><font color=red>phoneno already exist</font>");
						
					   }
					
					else
						{
						 						
						PreparedStatement stmt11=con.prepareStatement("update conductor set validation=? WHERE busno_3=? AND validation=?");
					
					stmt11.setString(1,"no");
					stmt11.setString(2,busno);
					stmt11.setString(3,"yes");
					stmt11.executeUpdate();
					
					PreparedStatement stmt12=con.prepareStatement("insert into conductor values(?,?,?,?,?,?,?,?,?)");
					stmt12.setString(1,request.getParameter("conductor3"));
					stmt12.setString(2,request.getParameter("address3"));
					stmt12.setString(3,request.getParameter("city3"));
					stmt12.setString(4,request.getParameter("state3"));
					stmt12.setString(5,phone);
					stmt12.setString(6,email1);
					stmt12.setString(7,request.getParameter("aadhar3"));
					stmt12.setString(8,busno);
					stmt12.setString(9,"yes");
					stmt12.executeUpdate();
					
					Statement stm=con.createStatement();
					stm.executeUpdate("update conductorcredential set email='"+request.getParameter("email3")+"' where busno_4='"+busno+"'");
					
					con.close();
					request.getRequestDispatcher("data.html").include(request, response);
				    out.println("<br><font color=green>Conductor Modified</font>");
				  }
					
				}
				
				if("Delete_Conductor".equals(button))
				{
					PreparedStatement stmt13=con.prepareStatement("update conductor set validation=? WHERE busno_3=? AND validation=? ");
					 stmt13.setString(1,"no");
					 stmt13.setString(2,busno);
					 stmt13.setString(3,"yes");
					 
					 PreparedStatement stmt14=con.prepareStatement("update conductorcredential set validation=? WHERE busno_4=? ");
					 stmt14.setString(1,"no");
					 stmt14.setString(2,busno);
					
					 stmt13.executeUpdate();
					 stmt14.executeUpdate();
					 
					    con.close();
						request.getRequestDispatcher("data.html").include(request, response);
					    out.println("<br><font color=green>Conductor Deleted</font>");
					
					
				} 
	    	
	    	}
			
			
			catch(Exception e)
			{request.getRequestDispatcher("data.html").include(request, response);
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
