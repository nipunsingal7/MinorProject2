package pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pkg.Ticketid;
import pkg.Database;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Servlet implementation class Bookticket2
 */
@WebServlet("/Bookticket2")
public class Bookticket2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bookticket2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String button1=request.getParameter("button3");
		
		 HttpSession s1=request.getSession(false);
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
	
	 if(s1!=null)
			 
		 
	 {	
		 if("Back".equals(button1))
		 { 
			request.getRequestDispatcher("dashboard.html").forward(request,response); 
		 }	 
		 
		 
		 if("Book_ticket".equals(button1))
		 {  
	   
	    try {  
			int flag1=0,flag2=0;		
		    String text="",temptext;
		 
			 String names[]=request.getParameterValues("pname");
			 String age[]=request.getParameterValues("page");
			 String correctseats[]=request.getParameterValues("checkb");
			 
			 String[] correctnames=new String[names.length];
			 int[] correctage=new int[age.length];
			 
			 
			 for(int l=0;l<names.length;l++)
			 {  
				if(names[l]!="")
			     {
				   correctnames[flag1]=names[l];
				   flag1++;
			     }
			 
                if(age[l]!="")
                 { 
            	   correctage[flag2]=Integer.parseInt(age[l]);
            	   flag2++;
            	 }
		     }
			 
			 
			 
			 if(flag1!=flag2 || flag1!=correctseats.length)
			 {  
				 request.getRequestDispatcher("dashboard.html").forward(request, response);
				out.println("<br><br><font color=red>Fill age and name and seatNo for each and every person</font>");
			 }
			 
			 
			 else  
				  
			 {     Connection con=Database.databaseconnection();

			         
			         
			         String email=(String)s1.getAttribute("email");
			 
			          for(int h=0;h<flag1;h++)
			         {  temptext=text;
			            text=temptext+correctnames[h]+" "+"age: "+correctage[h]+" "+"seatNo: "+correctseats[h]+"\n";
				      }
			 
			          
			         String ticketid=Ticketid.tnumber(email,text);
			         
			         SimpleDateFormat ftm4=new SimpleDateFormat("yyyy-MM-dd");
					  String date1=ftm4.format((Date)s1.getAttribute("date"));
			         
			         PreparedStatement stmt1=con.prepareStatement("insert into ticket values(?,?,?,?,?,?,?,?,?)");
                     
			         for(int q=0;q<flag1;q++)
			       {
			         stmt1.setString(1,(String)s1.getAttribute("bus"));
                     stmt1.setString(2,date1);
                     stmt1.setString(3,correctnames[q]);
                     stmt1.setInt(4,correctage[q]);
                     stmt1.setInt(5,Integer.parseInt(correctseats[q]));
                     stmt1.setString(6,(String)s1.getAttribute("from"));
                     stmt1.setString(7,(String)s1.getAttribute("to"));
                     stmt1.setString(8,ticketid);
                     stmt1.setString(9,"yes");
                     stmt1.addBatch();
			       }
			          
			         stmt1.executeBatch();
			         con.close();
			         
			         request.getRequestDispatcher("dashboard.html").include(request, response);
						out.println("<br><br><font color=green>Tickets Booked. Ticket copy send to the registered email</font>"); 
			 } 
			 
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
