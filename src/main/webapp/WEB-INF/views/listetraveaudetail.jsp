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
    Traveaudetailtarif_v[] traveaudetailtarif_vs=(Traveaudetailtarif_v[])request.getAttribute("traveaudetailtarif_vs");
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
        <h1>Les types travaux :</h1>
        <% 
        if(traveaudetailtarif_vs!=null){
            for(Traveaudetailtarif_v traveaudetailtarif_v:traveaudetailtarif_vs){%>
                <div class="d-list" >
                    <p><strong>Designation :</strong> <%=traveaudetailtarif_v.getDesignationd()%></p>
                    <p><strong>code :</strong> <%=traveaudetailtarif_v.getCodetravaud()%> </pp>
                    <p><strong>Tarif unitaire :</strong> <%=(traveaudetailtarif_v.getTarifunitaire())%></p>
                    <div style="display: flex;">
                    <form action="/admin/tomodifetravauxdetail" method="post">
                        <input type="hidden" value="<%=traveaudetailtarif_v.getId_travaudetail()%>" name="id_travaudetail" />
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