package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Delete")
public class DeleteContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the ID parameter from the request
        String idParameter = request.getParameter("id");
        int id = 0;

        try {
            id = Integer.parseInt(idParameter);
        } catch (NumberFormatException e) {
            // Handle invalid or missing ID parameter
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid or missing 'id' parameter.");
            return;
        }

        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/GestionContact";
        String dbUser = "root";
        String dbPassword = "123456qbc";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Create a SQL query to delete the row with the given ID
            String deleteQuery = "DELETE FROM contacts WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);

            // Execute the delete query
            int rowsDeleted = preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connection.close();

            if (rowsDeleted > 0) {
                // Row was deleted successfully
                response.sendRedirect("getContacts");
            } else {
                // Row with the given ID was not found
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().println("No row found with ID " + id);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("An error occurred while deleting the row.");
        }
    }
}
