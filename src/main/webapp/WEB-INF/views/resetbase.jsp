<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.projet.eval.models.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>BTP</title>
    <link rel="stylesheet" href="/assets/css/template.css">
</head>
<%
    String[] tablenames=(String[])request.getAttribute("tablenames");
%>
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
        <h1>Data table to reset </h1>
        <div >
            <form action="/base/resetbase" method="post">
                <%
                if(tablenames!=null){
                    for(int i=0;i<tablenames.length;i++){%>    
                        <input type="checkbox" id="spams" value="<%=tablenames[i]%>" name="tablename<%=i%>" ><label for="spams"> <%=tablenames[i]%> </label><br>
                    <%}
                }%>
                <button id="mybutton" >Valider</button>
            </form>
            <% if(request.getAttribute("erreur")!=null){ %>
                <p style="color:red;"><%=request.getAttribute("erreur") %></p>
            <% } %>
        </div>
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