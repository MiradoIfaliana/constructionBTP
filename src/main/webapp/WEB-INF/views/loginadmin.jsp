<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="/assets/css/login.css">
    <link rel="stylesheet" href="/assets/css/all.min.css">
    <link rel="stylesheet" href="/assets/css/all.css">
</head>

<body>

    <div class="content">
        <section>
            <form action="/login/authentificationadmin" method="post">
                <h1 id="h1-login">Home Renovation</h1>
                <i class="far fa-user" style="font-size: 300%;" style="color: rgb(66, 66, 66);"></i> 
                <h2>Admin</h2>
                <i class="far fa-envelope"></i> <input placeholder="mail" type="email" id="mail" name="mail" /><br>

                <i class="fas fa-lock"></i> <input placeholder="password" type="password" id="pwd" name="pwd" /><br>

                <button id="mybutton">Valider</button>
                <% if(request.getAttribute("erreur")!=null){ %>
                    <p style="color:red;"><%=request.getAttribute("erreur") %></p>
                <% } %>
                <div style="width:100%;text-align:right;margin-right:20px;">
                    <a href="/login/loginclient">client</a>
                </div>
            </form>
        </section>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2024 eval. Tous droits réservés.</p>
    </footer>

</body>

</html>
