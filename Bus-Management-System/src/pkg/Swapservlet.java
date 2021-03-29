package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Swapservlet
 */
@WebServlet("/Swapservlet")
public class Swapservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Swapservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String button=request.getParameter("button5");
		String busno1=request.getParameter("1busno");
		String busno2=request.getParameter("2busno");
		HttpSession s2=request.getSession(false);
		response.setContentType("html");
		 PrintWriter out=response.getWriter();
		 
	if(s2!=null)
	{ 
		if("Back".equals(button))
	    { 
	    	request.getRequestDispatcher("dashboard1.html").forward(request, response);
	    }
		
		try
    	{Connection con=Database.databaseconnection();
    	Statement stmt=con.createStatement();
    	Statement stmt1=con.createStatement();
    	Statement stmt2=con.createStatement();
  	
    	ResultSet rs=stmt1.executeQuery("select * from bus where busno='"+busno1+"' AND validation='yes'");
    	ResultSet rs1=stmt2.executeQuery("select * from bus where busno='"+busno2+"' AND validation='yes'");
    	
    	
    	
    	    if(rs.next() && rs1.next())	
       {  	  
    	    	
    	    	
    	 if("Swap owner".equals(button))
    	 {	
            String[] q=new String[8];  
    		
    		q[0]="create temporary table temp select * from owner where busno_1='"+busno1+"' AND validation='yes'";
    	    stmt.executeUpdate(q[0]);
    	    
    	    q[1]="create temporary table temp1 select * from owner where busno_1='"+busno2+"' AND validation='yes'";
    	    stmt.executeUpdate(q[1]);
    	    
    	    q[2]="update owner set validation='no' where busno_1='"+busno1+"' AND validation='yes'";
           stmt.executeUpdate(q[2]);
            
           q[3]="update owner set validation='no' where busno_1='"+busno2+"' AND validation='yes'";
           stmt.executeUpdate(q[3]);
           
           q[4]="update temp set busno_1='"+busno2+"' where busno_1='"+busno1+"'";
            stmt.executeUpdate(q[4]);
         
            q[5]="update temp1 set busno_1='"+busno1+"' where busno_1='"+busno2+"'";
             stmt.executeUpdate(q[5]);
           
             q[6]="insert into owner select * from temp";
             stmt.executeUpdate(q[6]);
         
             q[7]="insert into owner select * from temp1";
              stmt.executeUpdate(q[7]);
         
            con.close();
             request.getRequestDispatcher("swapstaff.html").include(request, response);
		    out.println("<br><font color=green>Owner swapped</font>");           	
    	
    	   }
    	
    	
    	 if("Swap driver".equals(button))
    	 {	
            String[] q1=new String[8];  
    		
    		q1[0]="create temporary table temp select * from driver where busno_2='"+busno1+"' AND validation='yes'";
    	    stmt.executeUpdate(q1[0]);
    	    
    	    q1[1]="create temporary table temp1 select * from driver where busno_2='"+busno2+"' AND validation='yes'";
    	    stmt.executeUpdate(q1[1]);
    	    
    	    q1[2]="update driver set validation='no' where busno_2='"+busno1+"' AND validation='yes'";
           stmt.executeUpdate(q1[2]);
            
           q1[3]="update driver set validation='no' where busno_2='"+busno2+"' AND validation='yes'";
           stmt.executeUpdate(q1[3]);
           
           q1[4]="update temp set busno_2='"+busno2+"' where busno_2='"+busno1+"'";
            stmt.executeUpdate(q1[4]);
         
            q1[5]="update temp1 set busno_2='"+busno1+"' where busno_2='"+busno2+"'";
             stmt.executeUpdate(q1[5]);
           
             q1[6]="insert into driver select * from temp";
             stmt.executeUpdate(q1[6]);
         
             q1[7]="insert into driver select * from temp1";
              stmt.executeUpdate(q1[7]);
         
            con.close();
             request.getRequestDispatcher("swapstaff.html").include(request, response);
		    out.println("<br><font color=green>Driver swapped</font>");           	
    	
    	   }
    	 
    	 
    	 
    	 if("Swap conductor".equals(button))
    	 {	
            String[] q2=new String[11];  
    		
    		q2[0]="create temporary table temp select * from conductor where busno_3='"+busno1+"' AND validation='yes'";
    	    stmt.executeUpdate(q2[0]);
    	    
    	    q2[1]="create temporary table temp1 select * from conductor where busno_3='"+busno2+"' AND validation='yes'";
    	    stmt.executeUpdate(q2[1]);
    	    
    	    q2[2]="update conductor set validation='no' where busno_3='"+busno1+"' AND validation='yes'";
           stmt.executeUpdate(q2[2]);
            
           q2[3]="update conductor set validation='no' where busno_3='"+busno2+"' AND validation='yes'";
           stmt.executeUpdate(q2[3]);
           
           q2[4]="update temp set busno_3='"+busno2+"' where busno_3='"+busno1+"'";
            stmt.executeUpdate(q2[4]);
         
            q2[5]="update temp1 set busno_3='"+busno1+"' where busno_3='"+busno2+"'";
             stmt.executeUpdate(q2[5]);
           
             q2[6]="insert into conductor select * from temp";
             stmt.executeUpdate(q2[6]);
         
             q2[7]="insert into conductor select * from temp1";
              stmt.executeUpdate(q2[7]);
              
             q2[8]="select id from conductorcredential where busno_4='"+busno1+"' AND validation='yes'"; 
             Statement stmt3=con.createStatement();
             ResultSet rs2=stmt3.executeQuery(q2[8]);
             rs2.next();
             String id=rs2.getString("id");
             
             q2[9]="update conductorcredential set busno_4='"+busno1+"' where busno_4='"+busno2+"' AND validation='yes'";
             stmt.executeUpdate(q2[9]);
            
             q2[10]="update conductorcredential set busno_4='"+busno2+"' where id='"+id+"' AND validation='yes'";
             stmt.executeUpdate(q2[10]);
             
            con.close();
             request.getRequestDispatcher("swapstaff.html").include(request, response);
		    out.println("<br><font color=green>Conductor swapped</font>");           	
    	
    	   }
    	 
    	 
    	 
    }
    	    
    	    else
    	    {con.close();
    	    request.getRequestDispatcher("swapstaff.html").include(request, response);
		    out.println("<br><font color=red>Buses not found</font>");    
    	    }
    
    	
    }
		

		catch(Exception e)
		{request.getRequestDispatcher("swapstaff.html").include(request, response);
		  out.println("<br><font color=red>No Data found</font>");
	    }
		
		
		
		
		
		
	}
		
		
	else
	{ 
		 
		  request.getRequestDispatcher("main.html").include(request, response);
		  out.println("<br><font color=red>Login first....</font>");
	 }
		
		
		
	}

}
