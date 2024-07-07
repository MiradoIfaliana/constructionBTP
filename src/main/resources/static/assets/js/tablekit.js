var dataTable;
var isInit={select:false};
function first(url,method,offset_limit,idtable,addcolumn,columnaddobj,createdRowCallback) {
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
                    dataTable=getdataTable(dataTable,idtable,datas,colonnes,headers,addcolumn,columnaddobj,createdRowCallback)
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

function prev(url,method,offset_limit,idtable,addcolumn,columnaddobj,createdRowCallback) {
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
                    dataTable=getdataTable(dataTable,idtable,datas,colonnes,headers,addcolumn,columnaddobj,createdRowCallback)
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

function next(url,method,offset_limit,idtable,addcolumn,columnaddobj,createdRowCallback) {
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
                    dataTable=getdataTable(dataTable,idtable,datas,colonnes,headers,addcolumn,columnaddobj,createdRowCallback)
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
function addPdf(idtable, nblastexclu){
    // Sauvegarde du contenu initial

    var element = document.getElementById(idtable);
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
    if(nblastexclu!=null){
        element.querySelectorAll('tr').forEach(function(row) {
            var cells = row.querySelectorAll('th, td');
            for (var i = cells.length - 1; i >= cells.length - nblastexclu; i--) {
                cells[i].parentNode.removeChild(cells[i]);
            }
        });
    }
    html2pdf().from(element).set(options).save();
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

function isDateValid(dateString) {
    // Expression régulière pour vérifier le format de date 'YYYY-MM-DD'
    var regex = /^\d{4}-\d{2}-\d{2}$/;
    return regex.test(dateString);
}
function getdataTable(dataTable,idtable,datas,colonnes,headers,addcolumn,columnaddobj,createdRowCallback){
    if (dataTable) {
        dataTable.destroy();
    }
    //numero(id) an'le colonne inclu anaty exportation
    idxexport=[]
    for(let i=0;i<colonnes.length;i++){
        idxexport.push(i);
    }
    //raha nanampy colonne specifique @ le tableau
    idx={idx:0};
    if(addcolumn==true){
        for(var i=0;i<columnaddobj.length;i++){
            colonnes.push(null); // Ajoute une colonne vide pour le contenu HTML
            headers.push(''); // Ajoute un titre pour la colonne
            idx.idx=idx.idx+1;
        }
    }
    // creer le config anamboarana an'le datatable
    var tableconfig={
        data: datas, 
        columns: colonnes.map(function(col,index) {
            if (index === colonnes.length - idx.idx && addcolumn==true) { // Si c'est la dernière colonne
                var currentIdx =columnaddobj.length- idx.idx;
                idx.idx--;
                return { // columnaddobj --> donnee momba an'e colonne suplementaire apina
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
            if(columnaddobj!=null){
                for(let i=columnaddobj.length;i>0;i--){
                    var cellidx=colonnes.length-i;
                    var cell = $(row).find('td').eq(cellidx);
                    cell.css({'width':'1px'}); //akelezina le colonne supplementaire
                }
            }
        }
    };
    //jerena hoe nanisy fonction fandokona couleur an'le colonne ve izy
    if (createdRowCallback) {
        tableconfig.createdRow = function(row, data, dataIndex) {
            createdRowCallback(row, data, dataIndex, colonnes);
        };
    }
    //intialisena am'zay le dataTable
    var table = $('#' + idtable).DataTable(tableconfig);
    //creer champ de filtrage : raha misy colonne number--->champ min value et max value
    //                          rahe misy colonne date-----> champ date min et date max
    var estdate=false;
    var estnumber=false;
    if (isInit.select === false) {
        //var columnSelect = $('#column-select');
        //creer-na aloha le champ de selection
        var selectnumber=$('<select>').attr( {id: 'select-column-number',name:'select-column-number'});
        var selectdate=$('<select>').attr( {id: 'select-column-date',name:'select-column-date'});
        //columnSelect.empty(); // Supprimer les anciennes options
        for (var i = 0; i < headers.length; i++) {
            // Vérifier si la colonne est de type nombre ou float
            var dataType = typeof datas[0][colonnes[i]];
            if (dataType === 'number' || dataType === 'float') {
                var option = $('<option>').val(i).text(headers[i]);
                selectnumber.append(option);
                estnumber=true;
            }
            // Vérifier si la colonne est de type date
            //datas[0][colonnes[i]] instanceof Date
            if (dataType === 'string' &&  isDateValid(datas[0][colonnes[i]])) {
                var optiondate = $('<option>').val(i).text(headers[i]);
                selectdate.append(optiondate);
                estdate=true;
            }
        }
        isInit.select = true;
    }
    //raha nisy le type number
    if(estnumber==true){
        //<label for="max-value">Max:</label>
        var labelnumbermin = $('<label>').attr({for: 'min-value'}).text('Min:').css({'margin-left': '20px','font-weight': 'bold','padding':'10px'});
        var minNumberInput = $('<input>').attr({type: 'number', id: 'min-value',name:'min-value'});
        var labelnumbermax = $('<label>').attr({for: 'max-value'}).text('Max:').css({'margin-left': '20px','font-weight': 'bold','padding':'10px'});
        var maxNumberInput = $('<input>').attr({type: 'number', id: 'max-value',name:'max-value'});
        var labelnumberselect = $('<label>').attr({for: 'select-column-number'}).text('Colonne:').css({'margin-left': '20px','font-weight': 'bold','padding':'10px'});
        var filtrenumber=$('#filter-by-number').append(labelnumbermin).append(minNumberInput).append(labelnumbermax).append(maxNumberInput).append(labelnumberselect).append(selectnumber);
        $('#min-value, #max-value, #select-column-number').on('input change', function() {
            table.draw();
        });
    }
    //raha nisy le type date
    if(estdate==true){
        var labeldatemin = $('<label>').attr({for: 'min-date'}).text('Min:').css({'margin-left': '20px','font-weight': 'bold','padding':'10px'});;
        var minDateInput = $('<input>').attr({type: 'date', id: 'min-date',name:'min-date'});
        var labeldatemax = $('<label>').attr({for: 'max-date'}).text('Max:').css({'margin-left': '20px','font-weight': 'bold','padding':'10px'});;
        var maxDateInput = $('<input>').attr({type: 'date', id: 'max-date',name:'max-date'});
        var labeldateselect = $('<label>').attr({for: 'select-column-date'}).text('Colonne:').css({'margin-left': '20px','font-weight': 'bold','padding':'10px'});;
        var filtredate=$('#filter-by-date').append(labeldatemin).append(minDateInput).append(labeldatemax).append(maxDateInput).append(labeldateselect).append(selectdate);
        $('#min-date, #max-date, #select-column-date').on('input change', function() {
            table.draw();
        });
    }
    // Appliquer le filtrage personnalisé
    $.fn.dataTable.ext.search.push(
        function(settings, data, dataIndex) {
            var min = parseFloat($('#min-value').val()) || 0;
            var max = parseFloat($('#max-value').val()) || Infinity;
            var columnIdx = parseInt($('#select-column-number').val());
            var value = parseFloat(data[columnIdx]) || 0;

            return value >= min && value <= max;
        }
    );
    $.fn.dataTable.ext.search.push(
        function(settings, data, dataIndex) {
            var minDate = $('#min-date').val();
            var maxDate = $('#max-date').val();
            var columnIdxDate = parseInt($('#select-column-date').val());
            var dateValue = data[columnIdxDate];

            var minDateObj = minDate ? new Date(minDate) : null;
            var maxDateObj = maxDate ? new Date(maxDate) : null;

            if (minDateObj && maxDateObj && dateValue) {
                var currentDate = new Date(dateValue);
                return currentDate >= minDateObj && currentDate <= maxDateObj;
            } else {
                return true;
            }
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


    // Générer dynamiquement les options de la liste déroulante pour choisir la colonne
    // if(isInit.select==false){
    //     var // = $('#column-select');
    //     columnSelect.empty(); // Supprimer les anciennes options
    //     for (var i = 0; i < headers.length; i++) {
    //         var option = $('<option>').val(i).text(headers[i]);
    //         columnSelect.append(option);
    //     }
    //     isInit.select=true;
    // }