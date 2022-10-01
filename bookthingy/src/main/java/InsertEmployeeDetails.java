import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import java.io.PrintWriter;
import java.sql.Connection;
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
// Fetch data
/**
 * Servlet implementation class demo3
 */
@WebServlet("/InsertBookDetails")
public class InsertEmployeeDetails extends HttpServlet {
    private static final long serialVersionUID = 1;

    String dns = "jdbc:mysql://ec2-18-117-221-239.us-east-2.compute.amazonaws.com:3306/myDB";
    String user = "smoritz";
    String password = "rootpassword";


    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertEmployeeDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String sql;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement statement1 = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        String ISBN = request.getParameter("ISBN");
        String summary = request.getParameter("summary");
        
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String titlestr = "Insert Employee Details into database";
//        String docType =
//            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
//
//        out.println(docType + //
//            "<html>\n" + //
//            "<head><title>" + titlestr + "</title></head>\n" + //
//            "<body bgcolor = \"##CCCCFF\">\n" + //
//            "<h1 align = \"center\">" + titlestr + "</h1>\n");


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }


        try {
            connection = DriverManager.getConnection(dns, user, password);
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            System.out.println("Connection Failed!:\n" + e2.getMessage());
        }
        System.out.println("SUCCESS!!!! You made it, take control     your database now!");
        System.out.println("Creating statement...");

        sql = "INSERT into bookTab (TITLE,AUTHOR,GENRE,ISBN,SUMMARY) values(?,?,?,?,?);";

        try {

            statement1 = connection.prepareStatement(sql);
            String theTitle = title;
            String theAuthor = author;
            String theGenre = genre;
            String theISBN = ISBN;
            String theSummary = summary;
            statement1.setString(1, theTitle);
            statement1.setString(2, theAuthor);
            statement1.setString(3, theGenre);
            statement1.setString(4, theISBN);
            statement1.setString(5, theSummary);

        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {

            statement1.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        out.println("Thank you for registering your details");
        out.println("</body></html>");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}