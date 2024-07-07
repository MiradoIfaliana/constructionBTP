<link rel="stylesheet" href="/assets/css/all.min.css">
<link rel="stylesheet" href="/assets/css/all.css">
<nav id="menuL" class="menuL">
    <img  src="/assets/images/logo.jpg" alt="logo" class="rounded-logo">
    <p class="nom-top-menuL">Home Renovation</p>
    <div class="manuL-a">

        <a class="menu-popo" href="/login/acceuiladmin"><i class="emotico fas fa-home"></i> Acceuil</a>
        <a onclick="popomenu(0)"  class="menu-popo"><i class="emotico fas fa-file-import" style="color: rgb(245, 245, 245);"></i> Import</a>
            <div class="menu-mere">
                <a class="sous-liste" href="/import/toimporttravauxdevis">Travaux et Devis</a>
                <a class="sous-liste" href="/import/toimportpaiment">Paimennt</a>
            </div>
        <a onclick="popomenu(1)"  class="menu-popo"><i class="emotico fas fa-trash-alt" style="color: rgb(245, 245, 245);"></i> Reset</a>
            <div class="menu-mere">
                <a class="sous-liste" href="/base/toresetbase">Data</a>
            </div>
        <a onclick="popomenu(2)"  class="menu-popo"><i class="emotico fas fa-hospital" style="color: rgb(245, 245, 245);"></i> Devis</a>
            <div class="menu-mere">
                <a class="sous-liste" href="/admin/listedevis">Devis encoure</a>
            </div>
        <a onclick="popomenu(3)"  class="menu-popo"><i class="emotico fas fa-chart-bar" style="color: rgb(245, 245, 245);"></i> Statictic</a>
            <div class="menu-mere">
                <a class="sous-liste" href="/admin/histogrammedevis">Devis histogramme</a>
            </div>
        <a onclick="popomenu(4)"  class="menu-popo"><i class="emotico fas fa-list" style="color: rgb(245, 245, 245);"></i> Types&Travaux</a>
            <div class="menu-mere">
                <a class="sous-liste" href="/admin/listetypefinition">Liste Type finition</a>
                <a class="sous-liste" href="/admin/listetraveaudetail">Liste travaux</a>
            </div>
        <a class="menu-popo" href="/login/deconnectionadmin"><i class="emotico fas fa-eject"></i> se deconnecter</a>
    </div>
</nav>
<script >///redirect
    function popomenu(a){
        var listmenu= document.querySelectorAll(".menu-mere");
        console.log( listmenu.item(a).innerHTML);
        listmenu.item(a).classList.toggle("active");
    }
    /* function activemenuL(){
        var listmenu= document.querySelector(".menuL");
        listmenu.classList.toggle("desactive");
        var menuT=document.querySelector(".menuT");
        menuT.classList.toggle("desactive-header");
    } */
</script>