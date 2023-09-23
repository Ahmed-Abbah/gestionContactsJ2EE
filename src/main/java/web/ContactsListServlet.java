package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/getContacts")
public class ContactsListServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/GestionContact";
        String dbUser = "root";
        String dbPassword = "123456qbc";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Create a SQL statement
            Statement statement = connection.createStatement();

            // Execute a SQL query to retrieve all rows from your table
            String query = "SELECT * FROM contacts";
            ResultSet resultSet = statement.executeQuery(query);

            // Create an ArrayList to store the results
            ArrayList<User> dataList = new ArrayList<>();

            // Iterate through the result set and add each row to the ArrayList
            while (resultSet.next()) {
                User user = new User(
                		resultSet.getString("id"),
                		resultSet.getString("nom"),
                		resultSet.getString("prenom"),
                		resultSet.getString("email"),
                		resultSet.getString("adresse"),
                		resultSet.getString("telephone")
                		);
                
                // Add more columns as needed
                dataList.add(user);
                
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

            // Get the session object
            HttpSession session = request.getSession();

            // Set the ArrayList as a session attribute
            session.setAttribute("dataList", dataList);

            // Redirect to another JSP page
            response.sendRedirect("index.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("An error occurred while retrieving data.");
        }
    }
}
