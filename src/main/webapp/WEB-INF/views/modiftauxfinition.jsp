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
    Typefinition_v typefinition_v=(Typefinition_v)request.getAttribute("typefinition_v");
    String nomfinition="";
    double tauxaugment=0 ; 
    int id_typefinition=0 ; 
    if(typefinition_v!=null){
        nomfinition=typefinition_v.getNomfinition();
        tauxaugment=typefinition_v.getTauxaugment()*100;
        id_typefinition=typefinition_v.getId_typefinition();
    }
%>
<body>
<!-- menu Left -->
<div class="block-body">
    <!-- Header avec menus -->
    <jsp:include page="menuT.jsp" />
    <div id="menuL" class="block-menuL">
        <jsp:include page="menuL.jsp" />
    </div>  
    <section class="content">
        <form action="/admin/modiftauxfinition" method="post">
            <% if(request.getAttribute("msg")!=null){ %>
                <p style="color:rgb(20, 91, 16);"><%=request.getAttribute("msg") %></p>
            <% } %>
            <p><strong>Finition:</strong><%=nomfinition%></p>
            <input type="hidden" id="id_typefinition" name="id_typefinition" value="<%=id_typefinition%>" />
            <label for="tauxaugment">Taux finition : </label><br>
            <input type="text" id="tauxaugment" name="tauxaugment" value="<%=tauxaugment%>" /><br>
            <button id="mybutton">Valider</button>
            <% if(request.getAttribute("erreur")!=null){ %>
                <p style="color:red;"><%=request.getAttribute("erreur") %></p>
            <% } %>
        </form>    
    </section>
<!-- Footer -->
    <jsp:include page="footer.jsp" />
</div>

<script >
//var bt=document.querySelector(".ok");
//bt.addEventListener("click" , function(){
//    document.querySelector(".custom-popo").classList.toggle("active")
//})
</script>
</body>

</html>