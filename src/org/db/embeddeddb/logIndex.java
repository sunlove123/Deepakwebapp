package org.db.embeddeddb;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class logIndex
 */
@WebServlet("/logIndex")
public class logIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		boolean flag = false;
		if(user.equals("test") && password.equals("test"))
		{
			flag = true;
		}
	      if(flag)
	      {
	    	  response.sendRedirect("FinalSuccess.html");
	      }
	      else
	      {
	    	  response.sendRedirect("FinalFailure.html");
	      }
	}

}
