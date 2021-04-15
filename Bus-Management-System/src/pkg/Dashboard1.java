package pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Dashboard1
 */
@WebServlet("/Dashboard1")
public class Dashboard1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard1() {
        super();
      
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 
		 String button=request.getParameter("adbutton");
		 HttpSession s2=request.getSession(false);
		 response.setContentType("html");
		 PrintWriter out=response.getWriter();
	
	 if(s2!=null)
			 
		 
	 {	
		 if("Add Bus".equals(button))
		 {
			 request.getRequestDispatcher("addbus.html").forward(request, response);
		 }
	
		 
		 if("Modify/delete Data".equals(button))
		 {
			 request.getRequestDispatcher("data.html").forward(request, response);
		 }
	
		 
		 if("Add Owner".equals(button))
		 {
			 request.getRequestDispatcher("addowner.html").forward(request, response);
		 }
		 
		 if("Add Driver".equals(button))
		 {
			 request.getRequestDispatcher("adddriver.html").forward(request, response);
		 }
		 
		 if("Add Conductor".equals(button))
		 {
			 request.getRequestDispatcher("addconductor.html").forward(request, response);
		 }
	
		 if("Swap staff".equals(button))
		 {
			 request.getRequestDispatcher("swapstaff.html").forward(request, response);
		 }
		 
		 if("Block/Unblock user".equals(button))
		 {
			 request.getRequestDispatcher("blockuser.html").forward(request, response);
		 }
		 
		 if("Change Password".equals(button))
		 {
			 request.getRequestDispatcher("forgotpasswd.html").forward(request, response);
		 }
		
		
		 if("Logout".equals(button))
		 {   s2.invalidate();
			 request.getRequestDispatcher("main.html").include(request, response);
			 out.println("<br><font color=green>Successfully logout</font>");
		 }
		 
		 
		 if("See bus records".equals(button))
		 {
			 request.getRequestDispatcher("busrecords.html").forward(request, response);
		 }
		 
		 if("See ticket records".equals(button))
		 {
			 request.getRequestDispatcher("ticketrecords.html").forward(request, response);
		 }
		 
		 
	 }	 
		
	 else
	 { 
		 
		  request.getRequestDispatcher("main.html").include(request, response);
		  out.println("<br><font color=red>Login first....</font>");
	 }
	 
		 
	}

}
