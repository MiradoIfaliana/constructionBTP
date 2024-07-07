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
Maisontravauxcsv[] maisontravauxcsvs=(Maisontravauxcsv[])request.getAttribute("maisontravauxcsvs");
Deviscsv[] deviscsvs=(Deviscsv[])request.getAttribute("deviscsvs");
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
        <h1>Import csv</h1>
        <% if(request.getAttribute("erreur")!=null){ %>
            <p style="color:red;"><%=request.getAttribute("erreur") %></p>
        <% } %>
        <form action="/import/importtravauxdevis" method="post" enctype="multipart/form-data">
            
            <label for="maison_travaux">Maison travaux :</label><br>
            <input type="file" id="maison_travaux" name="maison_travaux" /><br>

            <label for="devis">Devis :</label><br>
            <input type="file" id="devis" name="devis" /><br>

            <button id="mybutton" >Valider</button>
        </form>
        
        <%
        if(maisontravauxcsvs!=null){%>
            <table style="font-size:50%;width:90%;" id="table-id" class="display">
                <thead>
                    <tr>
                        <th> id_maisontravauxcsv </th>
                        <th> type_maison </th> 
                        <th> descriptions </th> 
                        <th> surface </th> 
                        <th> code_travaux </th> 
                        <th> type_travaux </th> 
                        <th> unite </th> 
                        <th> prix_unitaire </th> 
                        <th> quantite </th> 
                        <th> duree_travaux </th> 
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for(Maisontravauxcsv maisontravauxcsv:maisontravauxcsvs){ %>
                            <tr>
                                <td><%=maisontravauxcsv.getId_maisontravauxcsv()%> </td>
                                <td><%=maisontravauxcsv.getType_maison()%> </td> 
                                <td><%=maisontravauxcsv.getDescriptions()%> </td> 
                                <td><%=maisontravauxcsv.getSurface()%> </td> 
                                <td><%=maisontravauxcsv.getCode_travaux()%> </td> 
                                <td><%=maisontravauxcsv.getType_travaux()%> </td> 
                                <td><%=maisontravauxcsv.getUnite()%> </td> 
                                <td><%=maisontravauxcsv.getPrix_unitaire()%> </td> 
                                <td><%=maisontravauxcsv.getQuantite()%> </td> 
                                <td><%=maisontravauxcsv.getDuree_travaux()%> </td> 
                            </tr><% 
                        } %>
                </tbody>
            </table><%
        }%>
        <%
        if(deviscsvs!=null){%>
            <table style="font-size:50%;width:90%;" id="table-id" class="display">
                <thead>
                    <tr>
                        <th>id_deviscsv</th>
                        <th>client</th>
                        <th>ref_devis</th>
                        <th>type_maison</th>
                        <th>finition</th>
                        <th>taux_finition</th>
                        <th>date_devis</th>
                        <th>date_debut</th>
                        <th>lieu</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for(Deviscsv deviscsv:deviscsvs){ %>
                            <tr>
                                <td><%=deviscsv.getId_deviscsv()%></td>
                                <td><%=deviscsv.getClient()%></td>
                                <td><%=deviscsv.getRef_devis()%></td>
                                <td><%=deviscsv.getType_maison()%></td>
                                <td><%=deviscsv.getFinition()%></td>
                                <td><%=deviscsv.getTaux_finition()%></td>
                                <td><%=deviscsv.getDate_devis()%></td>
                                <td><%=deviscsv.getDate_debut()%></td>
                                <td><%=deviscsv.getLieu()%></td>
                            </tr><% 
                        } %>
                </tbody>
            </table><%
        }%>
    </section>
    <jsp:include page="footer.jsp" />
</div>
</body>

</html>