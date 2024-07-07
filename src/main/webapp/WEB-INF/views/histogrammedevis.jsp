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
%>
<body>
<!-- menu Left -->
<div class="block-body">
    <!-- Header avec menus -->
    <jsp:include page="menuT.jsp" />
    <div id="menuLc" class="block-menuL">
        <jsp:include page="menuL.jsp" />
    </div>
    <section class="content">
            <div style="text-align: right;">
                <p id="devistotal"></p>
                <p id="payetotal"></p>
            </div>
            <label for="mois">Annee : </label>
            <input type="number" onchange="choixYM()" step="1" value="2024" min="1960" max="2090" name="mois_annee" id="mois_annee" />
            <% if(request.getAttribute("erreur")!=null){ %>
                <p style="color:red;"><%=request.getAttribute("erreur") %></p>
            <% } %>
        <div class="chart-container">
            <canvas id="histogram-chart"></canvas>
        </div>
    </section>
<!-- Footer -->
    <jsp:include page="footer.jsp" />
</div>
<script src="/assets/js/chart.js"></script>
<script >
    var chartmy;
    function chart(labels_,datas){
        const dataHistogram = {
            labels: labels_,
            datasets: [{
                label: 'devis d\'histogramme',
                data: datas,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        };
        if(chartmy){
            chartmy.destroy();
        }
        chartmy=new Chart(document.getElementById('histogram-chart'), {
            type: 'bar',
            data: dataHistogram,
            options: {
                indexAxis: 'x'
            }
        });
    }
   
    function choixYM(){
        var mois_annee= document.getElementById("mois_annee").value;
        var annee=new Date().getFullYear();
        if(mois_annee==null){ mois_annee=new Date().getFullYear(); }
        else if(mois_annee==''){ mois_annee=new Date().getFullYear(); }
        else{
            tab=mois_annee.split('-');
            annee=tab[0];
        }
        setdata(annee);
    }
    function setdata(annee,mois){
        var xhr = new XMLHttpRequest();
                xhr.open("GET", "/api_1/histogrammedevis?annee="+annee, true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            var responseObj = JSON.parse(xhr.responseText);
                            // Extraire les données et les libellés
                            var datas = responseObj.datas;
                            var labels = responseObj.labels;
                            var devistotal = responseObj.devistotal;
                            var payetotal = responseObj.payetotal;
                            var element=document.getElementById("devistotal");
                            element.innerHTML="<strong >Devis Total :</strong>"+devistotal+" ar";
                            var element=document.getElementById("payetotal");
                            element.innerHTML="<strong >Paye Total :</strong>"+payetotal+" ar";
                            chart(labels,datas);
                        } else {
                            console.error(xhr.responseText);
                            throw xhr.error;
                        }
                    }
                };
                xhr.send();
    }
    choixYM();

</script>
</body>

</html>