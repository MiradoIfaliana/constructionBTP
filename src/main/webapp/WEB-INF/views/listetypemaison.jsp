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
    Typemaison[] typemaisons=(Typemaison[])request.getAttribute("typemaisons");
%>
<body>
<!-- menu Left -->
<div class="block-body">
    <!-- Header avec menus -->
    <jsp:include page="menuTc.jsp" />
    <div id="menuL" class="block-menuL">
        <jsp:include page="menuLc.jsp" />
    </div>
    <section class="content">
        <% if(request.getAttribute("msg")!=null){ %>
            <p style="color:rgb(20, 91, 16);"><%=request.getAttribute("msg") %></p>
        <% } %>
        <h1>Type maison :</h1>
        <% if(request.getAttribute("erreur")!=null){ %>
            <p style="color:red;"><%=request.getAttribute("erreur") %></p>
        <% } %>
        <% 
            if(typemaisons!=null){%>
                <form action="/maisonc/choixfinition" method="post">
                <button id="mybutton" >suivant</button>
                <div class="d-line"><%
                for(int i=0;i<typemaisons.length;i++){
                    if(i%3==0 && i>0){%>
                        </div>
                        <div class="d-line"><%
                    }%>
                    <div class="d-col" >
                        <p><strong>Type maison</strong> : <%=typemaisons[i].getNomtypemaison() %></p>
                        <p><strong>Description</strong> : <%=typemaisons[i].getDescriptions() %></p>
                        <p><strong>Duree : </strong> : <%=typemaisons[i].getDuree_j() %> jours</p>
                        <input type="radio" value="<%=typemaisons[i].getId_typemaison()%>" name="id_typemaison">
                    </div>
                
                <%}%></form></div><%
        }
        %>
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