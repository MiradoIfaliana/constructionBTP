var dataTable;
function first(url,method,offset_limit,idtable,addcolumn,columnaddobj) {
    var xhr = new XMLHttpRequest();
    var offset=offset_limit.offset;
    var limit=offset_limit.limit;
    xhr.open(method, url+"?offset=" + offset + "&limit=" + limit, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var responseObj = JSON.parse(xhr.responseText);
                var datas = responseObj.data;
                var colonnes=responseObj.colonnes;
                var headers=responseObj.headers;
                console.log(datas);
                console.log(colonnes);
                console.log(headers);
                if (datas.length > 0) {
                    dataTable=getdataTable(dataTable,idtable,datas,colonnes,headers,addcolumn,columnaddobj)
                } else {
                    offset_limit.offset=0;
                }
            } else {
                console.error(xhr.responseText);
                throw xhr.error;
            }
        }
    };
    xhr.send(null);
};

function prev(url,method,offset_limit,idtable,addcolumn,columnaddobj) {
    offset_limit.offset = offset_limit.offset - offset_limit.limit;
    var offset=offset_limit.offset;
    var limit=offset_limit.limit;
    var xhr = new XMLHttpRequest();
    xhr.open(method, url+"?offset=" + offset + "&limit=" + limit, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var responseObj = JSON.parse(xhr.responseText);
                var datas = responseObj.data;
                var colonnes=responseObj.colonnes;
                var headers=responseObj.headers;
                if (datas.length > 0) {
                    dataTable=getdataTable(dataTable,idtable,datas,colonnes,headers,addcolumn,columnaddobj)
                } else {
                    offset_limit.offset=0;
                }
            } else {
                console.error(xhr.responseText);
                throw xhr.error;
            }
        }
    };
    xhr.send(null);
};

function next(url,method,offset_limit,idtable,addcolumn,columnaddobj) {
    offset_limit.offset = offset_limit.offset + offset_limit.limit;
    var offset=offset_limit.offset;
    var limit=offset_limit.limit;
    var xhr = new XMLHttpRequest();
    xhr.open(method, url+"?offset=" + offset + "&limit=" + limit, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var responseObj = JSON.parse(xhr.responseText);
                var datas = responseObj.data;
                var colonnes=responseObj.colonnes;
                var headers=responseObj.headers;
                if (datas.length > 0) {
                    dataTable=getdataTable(dataTable,idtable,datas,colonnes,headers,addcolumn,columnaddobj)
                } else {
                    offset_limit.offset = offset_limit.offset - offset_limit.limit;
                }
            } else {
                console.error(xhr.responseText);
                throw xhr.error;
            }
        }
    };
    xhr.send(null);
};

function addPdf(idtable, nblastexclu){
    // Sauvegarde du contenu initial

    var element = document.getElementById(idtable);
    var originalHtml = element.innerHTML;
    var originalstyle=element.style;
    element.style.padding='20px';
    element.style.fontSize="50%";
    element.style.width='700px';
    if(nblastexclu!=null){
        element.querySelectorAll('tr').forEach(function(row) {
            var cells = row.querySelectorAll('th, td');
            for (var i = cells.length - 1; i >= cells.length - nblastexclu; i--) {
                cells[i].parentNode.removeChild(cells[i]);
            }
        });
    }
    // Génération du PDF
    html2pdf(element).then(function() {
        // Restauration du contenu initial
        element.innerHTML = originalHtml;
        element.style=originalHtml.style;
    });
}

function replacetab(colonne, donnees, idtable) {
    var tableau = document.getElementById(idtable);
    // Supprimer toutes les lignes existantes du tableau
    while (tableau.rows.length > 1) {
        tableau.deleteRow(1);
    }
    donnees.forEach(function(objet) {
        var ligne = tableau.insertRow();
        colonne.forEach(function(attribut) {
            if (objet.hasOwnProperty(attribut)) {
                var cellule = ligne.insertCell();
                cellule.textContent = objet[attribut];
            }
        });
    });
}

function getdataTable(dataTable,idtable,datas,colonnes,headers,addcolumn,columnaddobj){
    if (dataTable) {
        dataTable.destroy();
    }
    //id des colonnes de la table inclu pour l'exportation
    idxexport=[]
    for(let i=0;i<colonnes.length;i++){
        idxexport.push(i);
    }
    idx={idx:0};
    if(addcolumn==true){
        for(var i=0;i<columnaddobj.length;i++){
            colonnes.push(null); // Ajoute une colonne vide pour le contenu HTML
            headers.push(''); // Ajoute un titre pour la colonne
            idx.idx=idx.idx+1;
        }
    }
    // Initialiser DataTables avec les données
    var table= $('#'+idtable).DataTable({
        data: datas, 
        columns: colonnes.map(function(col,index) {
            if (index === colonnes.length - idx.idx && addcolumn==true) { // Si c'est la dernière colonne
                var currentIdx =columnaddobj.length- idx.idx;
                idx.idx--;
                return { // Retourne un objet avec des données spécifiques pour le bouton de paiement
                    data: columnaddobj[currentIdx].colonne,
                    title: '',
                    render: function(data, type, row) {
                        return columnaddobj[currentIdx].contenuhtml.replace('{id}', data);
                    }
                };
            } else {
                return { data: col, title: headers[index] };
            }
        }),
        paging: false,
        dom: 'Bfrtip',
        buttons: [
            {
                extend: 'csvHtml5',
                exportOptions: {
                    columns: idxexport
                }
            },
            {
                extend: 'excelHtml5',
                exportOptions: {
                    columns: idxexport
                }
            },
            {
                extend: 'pdfHtml5',
                exportOptions: {
                    columns: idxexport
                }
            }
        ],
        //personnalisation couleur colonnee
        createdRow: function (row, data, dataIndex) {
            // Parcourir la colonne "paye" pour personnaliser la couleur
            if (data.hasOwnProperty('paye')) {
                var payeValue = parseFloat(data.paye);
                if (!isNaN(payeValue)) {
                    var cellIndex = colonnes.indexOf('paye');
                    var cell = $(row).find('td').eq(cellIndex);
                    if (payeValue <= 0) {
                        cell.css({
                            'background-color': 'red',
                            'font-weight': 'bold' 
                        });
                    } else {
                        cell.css({
                            'background-color': 'green',
                            'font-style': 'italic' 
                        });
                    }
                }
            }
        }
    });
    // Générer dynamiquement les options de la liste déroulante pour choisir la colonne
    var columnSelect = $('#column-select');
    columnSelect.empty(); // Supprimer les anciennes options

    for (var i = 0; i < headers.length; i++) {
        var option = $('<option>').val(i).text(headers[i]);
        columnSelect.append(option);
    }

    // Ajouter des écouteurs d'événements sur les champs de filtrage
    $('#min-value, #max-value, #column-select').on('input change', function() {
        table.draw();
    });

    // Appliquer le filtrage personnalisé
    $.fn.dataTable.ext.search.push(
        function(settings, data, dataIndex) {
            var min = parseFloat($('#min-value').val()) || 0;
            var max = parseFloat($('#max-value').val()) || Infinity;
            var columnIdx = parseInt($('#column-select').val());
            var value = parseFloat(data[columnIdx]) || 0;

            return value >= min && value <= max;
        }
    );
    return table;
}



// getdataTable(dataTable, idtable, datas, colonnes, headers, addcolumn, columnaddobj, 
//     function (row, data, dataIndex) {
//         // Votre fonction createdRow personnalisée
//         if (data.hasOwnProperty('paye')) {
//             var payeValue = parseFloat(data.paye);
//             if (!isNaN(payeValue)) {
//                 var cellIndex = colonnes.indexOf('paye');
//                 var cell = $(row).find('td').eq(cellIndex);
//                 if (payeValue <= 0) {
//                     cell.css({
//                         'background-color': 'red',
//                         'font-weight': 'bold'
//                     });
//                 } else {
//                     cell.css({
//                         'background-color': 'green',
//                         'font-style': 'italic'
//                     });
//                 }
//             }
//         }
//     }
// );
