package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contact")
public class AddContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Define your database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/GestionContact";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456qbc";

    public AddContactServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String adresse = request.getParameter("adresse");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create a database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Insert the data into the database
            String insertQuery = "INSERT INTO contacts (nom, prenom, telephone,adresse,email) VALUES (?, ?, ?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(5, email);
            preparedStatement.setString(4, adresse);
            preparedStatement.setString(3, telephone);
            
//            preparedStatement.setString(4, telephone);
            preparedStatement.executeUpdate();

            // Close the database connection
            connection.close();
            
            response.sendRedirect("getContacts");

            // Display a success message
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Display an error message in case of an exception
            out.println("Error: " + e.getMessage());
        }
    }

    // doGet method is not needed for this example
}
