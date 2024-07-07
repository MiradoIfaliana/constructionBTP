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
<body>
<!-- menu Left -->
<div class="block-body">
    <!-- Header avec menus -->
    <jsp:include page="menuTc.jsp" />
    <div id="menuL" class="block-menuL">
        <jsp:include page="menuLc.jsp" />
    </div>
    <section class="content">
        <div class="acceuil">
            <h1>Bienvenu cher client!</h1>
            <h2>CONSTRUCTION</h2>
            <h3>Home renovation</h3>
        </div>
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