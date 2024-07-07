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
    Listedevisclient_v[] listedevisclient_vs=(Listedevisclient_v[])request.getAttribute("listedevisclient_vs");
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
        <h1 id="title-section">Les devis :</h1>
        <% 
        if(listedevisclient_vs!=null){
            for(Listedevisclient_v listedevisclient_v:listedevisclient_vs){%>
                <div class="d-list" >
                    <p><strong>Ref_devis :</strong> <%=listedevisclient_v.getRef_devis()%></p>
                    <p><strong>Type maison:</strong> <%=listedevisclient_v.getNomtypemaison()%></p>
                    <p><strong>Finition :</strong> <%=listedevisclient_v.getNomfinition()%></p>
                    <p><strong>Lieu :</strong> <%=listedevisclient_v.getLieu()%></p>
                    <p><strong>Date travaux :</strong> <%=listedevisclient_v.getDatedebut()%> - <%=listedevisclient_v.getDatehfin()%></p>
                    <p><strong>creer le :</strong> <%=listedevisclient_v.getDatecreation()%></p>
                    <p><strong>Montant total :</strong> <%=listedevisclient_v.getTotalapaye_s()%> Ar</p>
                    <p><strong>Montant effectue :</strong> 
                        <strong style="color:<%=listedevisclient_v.getColor()%>;">
                            <%=listedevisclient_v.getPayetotal_s()%> Ar
                        </strong>
                    </p>
                    <p><strong>Pourcentage payé :</strong> 
                        <strong style="color:<%=listedevisclient_v.getColor()%>;">
                            <%=listedevisclient_v.getpourcentagePaye_s()%>%
                        </strong>
                    </p>
                    
                    <div style="display: flex;">
                    <form action="/admin/detaildevis" method="post">
                        <input type="hidden" value="<%=listedevisclient_v.getId_clienttravaux()%>" name="id_clienttravaux" />
                        <button id="mybutton">voir detail</button>
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

</body>

</html>