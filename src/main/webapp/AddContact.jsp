<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Ajouter un nouveau contact</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Gestion des Contacts</a>
    
    <!-- Create a div with the "ml-auto" class to push content to the right -->
    <div class="ml-auto">
        <a class="nav-link" href="AddContact.jsp">Add New Contact</a>
    </div>
</nav>
<br>
<center><h1>Ajouter Un Nouveau Contact</h1></center>
<br>
    <div class="container mt-5">
        <form method="post" action="/GestionContact/contact">
            <div class="form-group">
                <label for="nom">Nom:</label>
                <input type="text" class="form-control" name="nom" id="nom" required>
            </div>

            <div class="form-group">
                <label for="prenom">Prénom:</label>
                <input type="text" class="form-control" name="prenom" id="prenom" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" name="email" id="email" required>
            </div>

            <div class="form-group">
                <label for="telephone">Téléphone:</label>
                <input type="tel" class="form-control" name="telephone" id="telephone" required>
            </div>

            <div class="form-group">
                <label for="adresse">Adresse:</label>
                <input type="text" class="form-control" name="adresse" id="adresse" required>
            </div>

            <br>
            <input type="submit" class="btn btn-primary" value="Ajouter le contact">
        </form>
    </div>

    <!-- Include Bootstrap JS (optional, only if needed) -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
