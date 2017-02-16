package org.db.embeddeddb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.derby.jdbc.EmbeddedDriver;

/**
 * Servlet implementation class logIndex
 */
@WebServlet("/logIndex")
public class logIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
	      PreparedStatement pstmt;
	      Statement stmt;
	      ResultSet rs = null;
	      boolean flag = false;
	      String createSQL = "create table person (uname varchar(30) not null, pass varchar(30),constraint primary_key primary key (uname))";

	      try {
	         Driver derbyEmbeddedDriver = new EmbeddedDriver();
	         DriverManager.registerDriver(derbyEmbeddedDriver);
	         conn = DriverManager.getConnection("jdbc:derby:testdb1;create=true");
	         conn.setAutoCommit(false);
	         stmt = conn.createStatement();
	         stmt.execute(createSQL);

	         pstmt = conn.prepareStatement("insert into person (uname,pass) values(?,?)");
	         pstmt.setString(1, "Deepak");
	         pstmt.setString(2, "deepak@123");
	         pstmt.executeUpdate();

	         pstmt = conn.prepareStatement("insert into person (uname,pass) values(?,?)");
	         pstmt.setString(1, "Deena");
	         pstmt.setString(2, "deena@123");
	         pstmt.executeUpdate();
	         
	         rs = stmt.executeQuery("select * from person where uname = '"+request.getParameter("username")+"'");
	         
	         System.out.println(request.getParameter("uid") + ":::::::" + request.getParameter("password"));

	         while (rs.next()) {
	            System.out.printf("%s %s\n",
	            rs.getString(1), rs.getString(2));
	            System.out.println("res pwd" + rs.getString(2));
	            if(rs.getString(2).equals(request.getParameter("password")))
	            {
	           	 flag = true;
	            }
	            
	         }

	         stmt.execute("drop table person");

	         conn.commit();
	         

	      } catch (SQLException ex) {
	         System.out.println("in connection" + ex);
	      }

	      try {
	         DriverManager.getConnection
	            ("jdbc:derby:;shutdown=true");
	      } catch (SQLException ex) {
	         if (((ex.getErrorCode() == 50000) &&
	            ("XJ015".equals(ex.getSQLState())))) {
	               System.out.println("Derby shut down normally");
	         } else {
	            System.err.println("Derby did not shut down normally");
	            System.err.println(ex.getMessage());
	         }
	      }
	      System.out.println(flag);
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
