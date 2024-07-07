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
Payementcsv[] payementcsvs=(Payementcsv[])request.getAttribute("payementcsvs");
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
        <% if(request.getAttribute("message")!=null){ %>
            <p style="color:rgb(189, 130, 29);"><%=request.getAttribute("message") %></p>
        <% } %>
        <form action="/import/importpaiment" method="post" enctype="multipart/form-data">

            <label for="paiement">Paiement :</label><br>
            <input type="file" id="paiement" name="paiement" /><br>

            <button id="mybutton" >Valider</button>
        </form>

        <%
        if(payementcsvs!=null){%>
            <table style="font-size:50%;width:90%;" id="table-id" class="display">
                <thead>
                    <tr>
                        <th>id_payementcsv</th> 
                        <th>ref_devis</th>
                        <th>ref_paiement</th>
                        <th>date_paiement</th>
                        <th>montant</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for(Payementcsv payementcsv:payementcsvs){ %>
                            <tr>
                                <td><%=payementcsv.getId_payementcsv()%></th> 
                                <td><%=payementcsv.getRef_devis()%></th>
                                <td><%=payementcsv.getRef_paiement()%></th>
                                <td><%=payementcsv.getDate_paiement()%></th>
                                <td><%=payementcsv.getMontant()%></th>
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