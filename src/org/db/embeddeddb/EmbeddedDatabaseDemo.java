package org.db.embeddeddb;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.jdbc.EmbeddedDriver;

public class EmbeddedDatabaseDemo {

   public static void main(String[] args) {
      EmbeddedDatabaseDemo e =
         new EmbeddedDatabaseDemo();
      e.testDerby();
   }
   public void testDerby() {
      Connection conn = null;
      PreparedStatement pstmt;
      Statement stmt;
      ResultSet rs = null;
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
         try{
         pstmt = conn.prepareStatement("insert into person (uname,pass) values(?,?)");
         pstmt.setString(1, "Deena");
         pstmt.setString(2, "deena@123");
         pstmt.executeUpdate();
         }
         catch(Exception e)
         {
        	 System.out.println(e);
         }
         

         rs = stmt.executeQuery("select * from person where uname = 'Deepak'");

         while (rs.next()) {
            System.out.printf("%s %s\n",
            rs.getString(1), rs.getString(2));
            
            if(rs.getString(2).equals("deepak@123"))
            {
           	 System.out.println("Login Success");
            }
            else
            {
           	 System.out.println("Invalid Credintials");
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
   }
}