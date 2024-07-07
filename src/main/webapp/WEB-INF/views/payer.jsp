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
    Payementclient_v payementclient_v=(Payementclient_v)request.getAttribute("payementclient_v");
    int id_clienttravaux =0;
    String nomtypemaison="";
    String nomfinition="";
    String totalapaye="";
    String payetotal="";
    String restepaye="";
    if(payementclient_v!=null){
        id_clienttravaux =payementclient_v.getId_clienttravaux();
        nomtypemaison=payementclient_v.getNomtypemaison();
        nomfinition=payementclient_v.getNomfinition();
        totalapaye=payementclient_v.getTotalapaye_s();
        payetotal=payementclient_v.getPayetotal_s();
        restepaye=payementclient_v.getRestepaye_s();
    }
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
            <p id="msg" style="color:rgb(20, 91, 16);"></p>
            <p id="erreur" style="color:rgb(238, 42, 42);"></p>
            <p><label> Type maison : </label><%=nomtypemaison%></p>
            <p><label> Finition : </label><%=nomfinition%></p>
            <p id="apaye"><label> Total a paye :  </label><%=totalapaye%> ar</p>
            <p id="effectue"><label> Paye effectuer : </label><%=payetotal%> ar</p>
            <p id="reste"><label> Reste : </label><%=restepaye%> ar</p>
            <input type="hidden" id="id_clienttravaux" name="id_clienttravaux" value="<%=id_clienttravaux%>" />
            <label for="montant">Montant a paye : </label><br>
            <input type="text" id="montant" name="montant" /><br>
            <label for="datepaye">Date paye : </label><br>
            <input type="date" id="datepaye" name="datepaye" /><br>
            <button onclick="sendpaye()" id="mybutton">Valider</button>
            <% if(request.getAttribute("erreur")!=null){ %>
                <p style="color:red;"><%=request.getAttribute("erreur") %></p>
            <% } %> 
    </section>

<!-- Footer -->
    <jsp:include page="footer.jsp" />
</div>

<script >
    // montant/ datepaye /id_clienttravaux 
    function sendpaye(){
        var xhr = new XMLHttpRequest();
                var id_clienttravaux=document.getElementById("id_clienttravaux").value;
                var montant=document.getElementById("montant").value;
                var datepaye=document.getElementById("datepaye").value;
                var dataurl="?id_clienttravaux="+id_clienttravaux+"&montant="+montant+"&datepaye="+datepaye+"";
                console.log(dataurl);
                xhr.open("POST", "/api_1/payer"+dataurl, true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            var responseObj = JSON.parse(xhr.responseText);
                            // Extraire les données et les libellés
                            var totalapaye = responseObj.totalapaye;
                            var payetotal = responseObj.payetotal;
                            var restepaye = responseObj.restepaye;
                            var erreur = responseObj.erreur;
                            var msg = responseObj.msg;

                            var apaye=document.getElementById("apaye");
                            var effectue=document.getElementById("effectue");
                            var reste=document.getElementById("reste");
                            var msgE=document.getElementById("msg");
                            var erreurE=document.getElementById("erreur");
                            msgE.innerHTML=msg;
                            erreurE.innerHTML=erreur;

                            apaye.innerHTML="<p id=\"apaye\"><label> Total a paye :  </label>"+totalapaye+" ar</p>";
                            effectue.innerHTML="<p id=\"effectue\"><label> Paye effectuer : </label>"+payetotal+" ar</p>";
                            reste.innerHTML="<p id=\"reste\"><label> Reste : </label>"+restepaye+" ar</p>";
                        } else {
                            console.error(xhr.responseText);
                            throw xhr.error;
                        }
                    }
                };
                xhr.send();
    } 
    ////payementclient_v,msg,erreur
//var bt=document.querySelector(".ok");
//bt.addEventListener("click" , function(){
//    document.querySelector(".custom-popo").classList.toggle("active")
//})
</script>
</body>

</html>