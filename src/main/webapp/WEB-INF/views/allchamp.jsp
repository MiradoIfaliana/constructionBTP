<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.projet.eval.models.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Tsakitsaky</title>
    <link rel="stylesheet" href="/assets/css/template.css">
</head>
<body>
<!-- menu Left -->
<div class="block-body">
    <!-- Header avec menus -->
    <jsp:include page="menuT.jsp" />
    <div id="menuL" class="block-menuL">
        <jsp:include page="menuL.jsp" />
    </div>
    <!-- contenu --> 
    <section class="content">
        <h1>Export csv</h1>
        <form action="/allchamp" method="post" enctype="multipart/form-data">
            <label for="mail">mail : </label>
            <input type="email" id="email" name="email" /><br>
            <label for="mail">mail multiple : </label>
            <input type="email" id="email" name="email" multiple /><br>
            <label for="search">search : </label>
            <input type="search" id="search" name="search" /><br>
            <label for="tel">tel : </label>
            <input type="tel" id="tel" name="tel" /><br>
            <label for="url">url : </label>
            <input type="url" id="url" name="url" /><br>
            <label for="age ">age (1 a 10 step 2) : </label>
            <input type="number" name="age" id="age" min="1" max="10" step="2" /><br>
            <label for="change ">change (0 a 1 step 0.01) : </label>
            <input type="number" name="change" id="centimes" min="0" max="1" step="0.01" /><br>

            <label for="price">Prix maximal : </label>
            <input type="range" name="price"
            id="price"
            min="50000"
            max="500000"
            step="100"
            value="250000" />
            <output class="price-output" for="price"></output><br>

            <label for="datetime: ">datetime: </label>
            <input type="datetime-local" name="datetime" id="datetime" /><br>
            <label for="month: ">month: </label>
            <input type="month" name="month" id="month" /><br>
            <label for="time: ">time: </label>
            <input type="time" name="time" id="time" /><br>
            <label for="week: ">week: </label>
            <input type="week" name="week" id="week" /><br>

            <label for="monDate">Quand êtes-vous disponible cet été&nbsp;? (step 7)</label>
            <input
            type="date"
            name="monDate"
            min="2013-06-01"
            max="2013-08-31"
            step="7"
            id="monDate" /> <br>
            
            <label for="color">color :</label>
            <input type="color" name="color" id="color" /><br>
            
            <label for="spams">checkbox1 :</label><br>
            <input type="checkbox" id="spams" value="koko1" name="newsletter1" checked><br>
            <input type="checkbox" id="spams" value="koko2" name="newsletter1" ><br>
            <input type="checkbox" id="spams" value="koko3" name="newsletter1" checked><br>

            <label for="prefix">radio 3 input :</label><br>
            <input type="radio" name="prefix" value="M." checked> Monsieur<br>
            <input type="radio" name="prefix" value="Mme"> Madame<br>
            <input type="radio" name="prefix" value="Melle"> Mademoiselle<br>

            <label for="message">Votre message :</label>
            <textarea id="message" name="message" rows="10" cols="33">
            Texte par défaut.
            </textarea><br>
            <button id="mybutton" >Valider</button>
        </form>
            <% if(request.getAttribute("erreur")!=null){ %>
                <p style="color:red;"><%=request.getAttribute("erreur") %></p>
            <% } %>
    </section>
    <jsp:include page="footer.jsp" />
</div>
<script>
    
    const price = document.querySelector("#price");
    const output = document.querySelector(".price-output");

    output.textContent = price.value;

    price.addEventListener("input", function () {
    output.textContent = price.value;
    });
</script>
</body>

</html>