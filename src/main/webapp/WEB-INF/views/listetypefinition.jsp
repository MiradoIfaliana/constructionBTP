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
    Typefinition_v[] typefinition_vs=(Typefinition_v[])request.getAttribute("typefinition_vs");
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
        <h1>Les types de finition :</h1>
        <% 
        if(typefinition_vs!=null){
            for(Typefinition_v typefinition_v:typefinition_vs){%>
                <div class="d-list" >
                    <p><strong>Finition :</strong> <%=typefinition_v.getNomfinition()%></p>
                    <p><strong>Descriptions :</strong> <%=typefinition_v.getDescriptions()%> </pp>
                    <p><strong>Taux finition :</strong> <%=(typefinition_v.getTauxaugment()*100)%>%</p>
                    <div style="display: flex;">
                    <form action="/admin/tomodiftauxfinition" method="post">
                        <input type="hidden" value="<%=typefinition_v.getId_typefinition()%>" name="id_typefinition" />
                        <button id="mybutton">modifier</button>
                    </form>
                    
                    </div>
                </div>
                <%
            }
        }else{%>
            <p>aucun devis creer</p><%
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