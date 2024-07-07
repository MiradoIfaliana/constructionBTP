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
    DevisMeredetailclient[] devisMeredetailclients=(DevisMeredetailclient[])request.getAttribute("devisMeredetailclients");
    String typemaison="";
    String nomfinition="";
    if(devisMeredetailclients!=null){
        if(devisMeredetailclients.length>0){
            typemaison=devisMeredetailclients[0].getNomtypemaison();
            nomfinition=devisMeredetailclients[0].getNomfinition();
        }
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
        <h1>Les devis :</h1>
        <p><strong>Type maison : </strong><%=typemaison%></p>
        <p><strong>Finition : </strong><%=nomfinition%></p>
        <div class="dM-list">
        <% 
        if(devisMeredetailclients!=null){
            int i=0;
            %>
            <table class="table-devis" id="table-id" class="display">
            <thead>
                <tr >
                    <th class="th-devis">N o</th>
                    <th class="th-devis">Designation</th>
                    <th class="th-devis">Unite</th>
                    <th class="th-devis">Quantite</th>
                    <th class="th-devis">PU ar</th>
                    <th class="th-devis">Total ar</th>
                    <th class="th-devis">Taux augementation</th>
                </tr>
            </thead>
            <%
            for(DevisMeredetailclient devisMeredetailclient:devisMeredetailclients){%>

                        <tbody>
                            <tr>
                                <th><%=devisMeredetailclient.getCodetravau()%></th>
                                <th><%=devisMeredetailclient.getDesignation()%></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th><%=(devisMeredetailclient.getTauxaugment()*100)%>%</th>
                            </tr><%
                        for(Devisdetailclient dvclient: devisMeredetailclient.getDevisdetailclients()){%>
                                <tr>
                                    <td><%=dvclient.getCodetravaud()%></td>
                                    <td><%=dvclient.getDesignationd()%></td>
                                    <td><%=dvclient.getUnite()%></td>
                                    <td class="td-chiffre"><%=dvclient.getQuantite()%></td>
                                    <td class="td-chiffre" ><%=dvclient.getPu_s()%></td>
                                    <td class="td-chiffre"><%=dvclient.getTariftotal_s()%></td>
                                    <td></td>
                                </tr><%
                        }%>
                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th>Total:</th>
                                <th class="td-chiffre"><%=devisMeredetailclient.getTotal_s()%></th>
                                </th>
                            </tr>
                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th>Total finition (+<%=(devisMeredetailclient.getTauxaugment()*100)%>%):</th>
                                <th class="td-chiffre"><%=devisMeredetailclient.getTotalfinition_s()%></th>
                                </th>
                            </tr>
                        </tbody>
                <%
                i++;
            }%>
            </table><%
        }else{%>
            <p>aucun</p><%
        }
        %>
        
        </div>
    </section>
<!-- Footer -->
    <jsp:include page="footer.jsp" />
</div>
</body>

</html>