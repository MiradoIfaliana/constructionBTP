function searchTable(idinput,idtable) {
    // Récupérer le champ de recherche et la table
    var input = document.getElementById(idinput);
    var filter = input.value.toUpperCase();
    var table = document.getElementById(idtable);
    var rows = table.getElementsByTagName("tr");
    console.log(filter)
    // Pour chaque ligne de la table, vérifier si elle correspond à la recherche
    for (var i = 1; i < rows.length; i++) {
      var cells = rows[i].getElementsByTagName("td");
      var found = false;
      for (var j = 0; j < cells.length; j++) {
        var cell = cells[j];
        if (cell) {
          var textValue = cell.textContent || cell.innerText;
          console.log(textValue)
          if (textValue.toUpperCase().indexOf(filter) > -1) {
            found = true;
            break;
          }
        }
      }
      // Afficher ou masquer la ligne en fonction du résultat de la recherche
      if (found) {
        rows[i].style.display = "";
      } else {
        rows[i].style.display = "none";
      }
    }
  }
  
  // Écouter les changements dans le champ de recherche
  
  