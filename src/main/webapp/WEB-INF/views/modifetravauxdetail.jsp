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
    Traveaudetailtarif_v traveaudetailtarif_v=(Traveaudetailtarif_v)request.getAttribute("traveaudetailtarif_v");
    String designationd="";
    String codetravaud="";
    double tarifunitaire=0;
    int id_travaudetail=0; 
    if(traveaudetailtarif_v!=null){
        designationd=traveaudetailtarif_v.getDesignationd();
        codetravaud=traveaudetailtarif_v.getCodetravaud();
        tarifunitaire=traveaudetailtarif_v.getTarifunitaire();
        id_travaudetail=traveaudetailtarif_v.getId_travaudetail();
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
        <form action="/admin/modifetravauxdetail" method="post">
            <% if(request.getAttribute("msg")!=null){ %>
                <p style="color:rgb(20, 91, 16);"><%=request.getAttribute("msg") %></p>
            <% } %>
            <input type="hidden" id="id_travaudetail" name="id_travaudetail" value="<%=id_travaudetail%>" />
            <p><strong>Code : </strong><%=codetravaud%></p>
            <p><strong>Designation: </strong><%=designationd%></p>
            <label for="tarifunitaire">Tarif unitaire : </label><br>
            <input type="text" id="tarifunitaire" name="tarifunitaire" value="<%=tarifunitaire%>" /><br>
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