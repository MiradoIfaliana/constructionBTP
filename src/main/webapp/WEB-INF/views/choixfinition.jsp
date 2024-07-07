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
    Typefinition[] typefinitions=(Typefinition[])request.getAttribute("typefinitions");
    Lieu[] lieus=(Lieu[])request.getAttribute("lieus");
    String id_typemaison=(String)request.getAttribute("id_typemaison");
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
            <h1>Type finition :</h1>
            <% if(request.getAttribute("erreur")!=null){ %>
                <p style="color:red;"><%=request.getAttribute("erreur") %></p>
                <% } %>
            <% 
                if(typefinitions!=null){%>
                    <form action="/maisonc/creerdevis" method="post">
                    <button style="margin-top:1px;margin-bottom:20px;" id="mybutton">valider</button><br>
                    <input type="hidden" value="<%=id_typemaison%>" name="id_typemaison">
                    <label for="text">Nom travaux: </label><br>
                    <input type="text" id="text" name="nomtraveaux"><br>
                    <label for="date">Date debut travaux: </label><br>
                    <input type="date" id="date" value="<%=id_typemaison%>" name="datedebut"><br>
                    <label for="lieu">Lieu de livraison:</label><br>
                    <select id="lieu" name="id_lieu">
                        <%
                        if(lieus!=null){
                            for(Lieu lieu:lieus){%>
                                <option value="<%=lieu.getId_lieu()%>" ><%=lieu.getLieu()%></option><%  
                            }
                        }
                        %>
                    </select><br>
                    <div class="d-line"><%
                    for(int i=0;i<typefinitions.length;i++){
                        if(i%3==0 && i>0){%>
                            </div>
                            <div class="d-line"><%
                        }%>
                        <div class="d-col" >
                            <p><strong>Type finition</strong> : <%=typefinitions[i].getNomfinition() %></p>
                            <p><strong>Description</strong> : <%=typefinitions[i].getDescriptions() %></p>
                            <input type="radio" value="<%=typefinitions[i].getId_typefinition()%>" name="id_typefinition">
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