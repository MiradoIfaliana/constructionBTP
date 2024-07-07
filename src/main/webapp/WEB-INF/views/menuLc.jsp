<link rel="stylesheet" href="/assets/css/all.min.css">
<link rel="stylesheet" href="/assets/css/all.css">
<nav class="menuL">
    <img onclick="activemenuL()" src="/assets/images/logo.jpg" alt="logo" class="rounded-logo">
    <p class="nom-top-menuL">Home Renovation</p>
    <div class="manuL-a">
        <a onclick="popomenu(0)"  class="menu-popo"><i class="fas fa-store-alt" style="color: rgb(245, 245, 245);"></i> Type maison</a>
            <ul class="menu-mere">
                <li><a href="/maisonc/listetypemaison">liste type maison</a></li>
            </ul>
        <a onclick="popomenu(1)"  class="menu-popo"><i class="fas fa-receipt" style="color: rgb(245, 245, 245);"></i> Devis</a>
            <ul class="menu-mere">
                <li><a href="/maisonc/mylistedevis">Mes devis</a></li>
            </ul>
    </div>
</nav>
<script >///redirect 
    function popomenu(a){
        var listmenu= document.querySelectorAll(".menu-mere");
        listmenu.item(a).classList.toggle("active");
    }
    function activemenuL(){
        var listmenu= document.querySelector(".menuL");
        listmenu.classList.toggle("desactive");
        var menuT=document.querySelector(".menuT");
        menuT.classList.toggle("desactive-header");
    }
</script>