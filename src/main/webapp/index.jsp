<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="web.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact List</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Include DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
</head>
<body>
<%
// Redirect to another servlet


%>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Gestion des Contacts</a>
    
    <!-- Create a div with the "ml-auto" class to push content to the right -->
    <div class="ml-auto">
        <a class="nav-link" href="AddContact.jsp">Add New Contact</a>
    </div>
	</nav>

    <br>
    <center><h1>Liste de tous vos contacts</h1></center>
    <br>
    
    <div class="container mt-5">
        <table id="contactTable" class="table table-bordered">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Email</th>
                    <th>Téléphone</th>
                    <th>Adresse</th>
                    <th>Action</th>
                </tr>
            </thead>
            <% 
                ArrayList<User> userList = (ArrayList<User>) session.getAttribute("dataList");
            	if(userList == null){
            		response.sendRedirect("getContacts");
            	}
                if (userList != null) {
                    for (User user : userList) {
                %>
                <tr>
                    <td><%= user.getNom() %></td>
                    <td><%= user.getPrenom() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getTelephone() %></td>
                    <td><%= user.getAdresse() %></td>
                    <td>
                    <form action="Delete" method="get">
    					<input type="hidden" name="id" value="<%= user.getId() %>">
    					<button class="btn btn-sm btn-primary" type="submit">Supprimer</button>
					</form>
					<td>
					<td>
                        
                        <button class="btn btn-sm btn-danger" onclick="deleteRow(this)">Modif</button>
                    </td>
                </tr>
                <%
                    }
                }
                %>
                
                <% userList = null;%>
        </table>
    </div>

    <!-- Include Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Include jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Include DataTables JS -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
    
    <script>
        $(document).ready(function() {
            // Initialize DataTable
            $('#contactTable').DataTable();
        });

        function editRow(button) {
            // Implement your edit logic here
            // You can access the row data using JavaScript and update the row
            // For example, you can use a modal to edit the row's data
            alert("Edit clicked");
        }

        function deleteRow(button) {
            // Implement your delete logic here
            // You can access the row data using JavaScript and remove the row
            // For example, you can prompt the user for confirmation before deletion
            alert("Delete clicked");
        }
    </script>
</body>
</html>
