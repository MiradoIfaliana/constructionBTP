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
    <jsp:include page="menuTc.jsp" />
    <div id="menuL" class="block-menuL">
        <jsp:include page="menuLc.jsp" />
    </div>    
    <section class="content">
        <h1>Mes devis :</h1>
        <% 
        if(listedevisclient_vs!=null){
            for(Listedevisclient_v listedevisclient_v:listedevisclient_vs){%>
                <div class="d-list" >
                    <p><strong>Ref_devis :</strong> <%=listedevisclient_v.getRef_devis()%></p>
                    <p><strong>Type maison:</strong> <%=listedevisclient_v.getNomtypemaison()%></p>
                    <p><strong>Finition :</strong> <%=listedevisclient_v.getNomfinition()%></p>
                    <p><strong>Lieu :</strong> <%=listedevisclient_v.getLieu()%></p>
                    <p><strong>Date travaux :</strong> <%=listedevisclient_v.getDatedebut()%> a <%=listedevisclient_v.getDatehfin()%></p>
                    <p><strong>creer le :</strong> <%=listedevisclient_v.getDatecreation()%></p>
                    <div style="display: flex;">
                    <form action="/maisonc/detaildevis" method="post">
                        <input type="hidden" value="<%=listedevisclient_v.getId_clienttravaux()%>" name="id_clienttravaux" />
                        <button id="mybutton">voir detail</button>
                    </form>
                    <button class="bt-topdf" onclick="topdf(<%=listedevisclient_v.getId_clienttravaux()%>)">pdf</button>
                    <form action="/maisonc/topayer" method="post">
                        <input type="hidden" value="<%=listedevisclient_v.getId_clienttravaux()%>" name="id_clienttravaux" />
                        <button style="margin-left: 20px;" id="mybuttonpaye">payer</button>
                    </form>
                    </div>
                </div>
                <%
            }
        }else{%>
            <p>aucun devis creer</p><%
        }
        %>
    <div id="elementTemp">
    </section>
<!-- Footer -->
    <jsp:include page="footer.jsp" />
</div>
<script src="/assets/js/html2pdf.bundle.min.js" ></script>
<script>

    function topdf(id_clienttravaux){
        var xhr = new XMLHttpRequest();
                xhr.open("POST", "/api_1/detaildevis?id_clienttravaux="+id_clienttravaux, true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            var responseObj = JSON.parse(xhr.responseText);
                            // Extraire les données et les libellés
                            var datM = responseObj.devisMeredetailclients;
                            var payements=responseObj.payements;
                            var payetotal=responseObj.payetotal;
                            var table=`
                            <p><strong>Type maison:</strong>`+datM[0].nomtypemaison+`</p>
                            <p><strong>Type finition:</strong>`+datM[0].nomfinition+`</p>
                            <p><strong>Client :</strong>`+datM[0].numero+`</p>
                            <table class=\"table-devis\" id=\"table-id\" class=\"display\">
                                <thead>
                                    <tr >
                                        <th class=\"th-devis\">N o</th>
                                        <th class=\"th-devis\">Designation</th>
                                        <th class=\"th-devis\">Unite</th>
                                        <th class=\"th-devis\">Quantite</th>
                                        <th class=\"th-devis\">PU ar</th>
                                        <th class=\"th-devis\">Total ar</th>
                                        <th class=\"th-devis\">Taux augementation</th>
                                    </tr>
                                </thead>
                            `;

                                for(var i=0;i<datM.length;i++){
                                    table=table+`
                                        <tbody>
                                            <tr>
                                                <th>`+datM[i].codetravau+`</th>
                                                <th>`+datM[i].designation+`</th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th>`+(datM[i].tauxaugment*100)+`%</th>
                                            </tr>
                                    `;
                                    var dcl=datM[i].devisdetailclients;
                                    for(var j=0;j<dcl.length;j++){
                                        table=table+`
                                        <tr>
                                            <td>`+dcl[j].codetravaud+`</td>
                                            <td>`+dcl[j].designationd+`</td>
                                            <td>`+dcl[j].unite+`</td>
                                            <td class=\"td-chiffre\">`+dcl[j].quantite+`</td>
                                            <td class=\"td-chiffre\">`+dcl[j].pu_s+`</td>
                                            <td class=\"td-chiffre\">`+dcl[j].tariftotal_s+`</td>
                                            <td></td>
                                        </tr>`;
                                    }
                                    table=table+`<tr>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th>Total:</th>
                                        <th class=\"td-chiffre\">`+datM[i].total_s+`</th>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                        <th>Total finition (`+(datM[i].tauxaugment*100)+`%):</th>
                                        <th class=\"td-chiffre\">`+datM[i].totalfinition_s+`</th>
                                        </th>
                                    </tr>
                                    </tbody>`;
                                }
                            table=table+`</table>`;
                            var table2=`<p><strong>Payement effectué:</strong></p>
                                        <table class=\"table-id\" class=\"display\" >
                                            <tr>
                                                <th class=\"th-devis\" >Ref_paiement</th>
                                                <th class=\"th-devis\" >Date</th>
                                                <th class=\"th-devis\" >Montant</th>
                                            </tr>`;
                            if(payements!=0 && payements!='0' && payements!="0"){
                                for(var u=0;u<payements.length;u++){
                                    table2=table2+`
                                    <tr>
                                        <td>`+payements[u].ref_paiement+`</td>
                                        <td class=\"td-chiffre\">`+payements[u].datepaye+`</td>
                                        <td class=\"td-chiffre\">`+payements[u].montant_s+`</td>
                                    </tr>`;
                                }
                                
                            }
                            table2=table2+`<tr><td></td><th>TOTAL : </th><th>`+payetotal+`</th></tr></table>`;
                            var tablefinal=table+""+table2;
                            const options={
                                filename:'file.pdf',
                                image:{type:'jpeg',quality:0.98},
                                html2canvas:{ scale:2,logging:true,dpi:192,letterRendering:true },
                                jsPDF:{
                                    format:'a3',
                                    orientation:'portrait',
                                    unit:'mm'
                                }
                            };
                            html2pdf().from(tablefinal).set(options).save();

                        } else {
                            console.error(xhr.responseText);
                            throw xhr.error;
                        }
                    }
                };
                xhr.send();
    }
        
</script>
</body>

</html>