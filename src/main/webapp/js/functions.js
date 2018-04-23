var facultati = [];
var serii = [];
var grupe = [];

var email = "";
var idAplicatie = "";
var fName  = "";

function setidAplicatie(id) {
    idAplicatie = id;
}

function addFacultate(idFacultate,Nume) {
    var facultate = {idfacultate : idFacultate, nume: Nume};
    facultati.push(facultate);
}

function addSerie(idSerie,Nume,idFacultate) {
    var serie = {idserie: idSerie, nume: Nume, idfacultate : idFacultate};
    serii.push(serie);
}

function addGrupa(idGrupa,Nume,idSerie) {
    var serie = {idgrupa: idGrupa, nume: Nume, idserie : idSerie};
    grupe.push(serie);
}

function addTabelSerie(sel) {
    var node = document.getElementById("serii");
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }

    var title = document.createElement("b");
    title.innerHTML = "Serii :";
    title.style.textAlign = "center";
    node.appendChild(title);

    var tbl = document.createElement("table");
    tbl.style.marginLeft = "auto";
    tbl.style.marginRight = "auto";
    tbl.style.border = "solid 1px #62635f";
    tbl.style.borderCollapse = "collapse";
    tbl.style.marginTop = "3pt";

    var i = 0;

    var row = document.createElement("tr");

    for (var j = 0; j < serii.length; j++) {
        if(i > 3) {
            tbl.appendChild(row);
            row = document.createElement("tr");
            i = 0;
        }

        if(sel.options[sel.selectedIndex].value == serii[j].idfacultate) {

            var cell2 = document.createElement("td");
            cell2.innerHTML = serii[j].nume;
            cell2.style.padding = "5pt";
            cell2.style.border = "solid 1px #62635f";
            cell2.style.backgroundColor = "#1d1d1d";

            row.appendChild(cell2);
            i++;
        }

    }

    if(i > 0) {
        tbl.appendChild(row);
    }

    node.appendChild(tbl);

    var form = document.createElement("form");
    form.setAttribute('method',"post");
    form.setAttribute('action',"../SerieProcess");
    form.setAttribute('id',"addserie");

    node.appendChild(form);

    node = document.getElementById("addserie");

    var input = document.createElement("INPUT");
    input.setAttribute("type", "text");
    input.setAttribute("name","serieadd");
    input.style.marginTop = "8pt";

    node.appendChild(input);

    var input = document.createElement("INPUT");
    input.setAttribute("type", "hidden");
    input.setAttribute("name","idfac");
    input.setAttribute("value",sel.options[sel.selectedIndex].value)

    node.appendChild(input);

    var input = document.createElement("INPUT");
    input.setAttribute("type", "submit");
    input.setAttribute("name","add");
    input.setAttribute("value","Add");
    input.style.marginLeft = "3pt";

    node.appendChild(input);

}

function addSelectSerie(sel) {

    var clean = document.getElementById("tdgrupe");
    while (clean.firstChild) {
        clean.removeChild(clean.firstChild);
    }

    var node = document.getElementById("alegefac");

    node.innerHTML = "Alege seria: ";

    var node2 = document.getElementById("selectfac");
    while (node2.firstChild) {
        node2.removeChild(node2.firstChild);
    }

    var select = document.createElement("SELECT");
    select.setAttribute("id", "sfacultate");
    select.setAttribute("onchange", "addTabelGrupa(this)");

    var option = document.createElement("option");
    option.setAttribute("disabled", "");
    option.setAttribute("value", "");
    option.setAttribute("selected", "");
    option.innerHTML = "--";

    select.appendChild(option);

    for (var j = 0; j < serii.length; j++) {

        if(sel.options[sel.selectedIndex].value == serii[j].idfacultate) {
            var option = document.createElement("option");
            option.setAttribute("value", serii[j].idserie);
            option.innerHTML = serii[j].nume;
            select.appendChild(option);
        }
    }

    node2.appendChild(select);

}

function addTabelGrupa(sel) {
    var node = document.getElementById("tdgrupe");
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }

    var title = document.createElement("b");
    title.innerHTML = "Grupe :";
    title.style.textAlign = "center";
    node.appendChild(title);

    var tbl = document.createElement("table");
    tbl.style.marginLeft = "auto";
    tbl.style.marginRight = "auto";
    tbl.style.border = "solid 1px #62635f";
    tbl.style.borderCollapse = "collapse";
    tbl.style.marginTop = "3pt";

    var i = 0;

    var row = document.createElement("tr");

    for (var j = 0; j < grupe.length; j++) {
        if(i > 3) {
            tbl.appendChild(row);
            row = document.createElement("tr");
            i = 0;
        }

        if(sel.options[sel.selectedIndex].value == grupe[j].idserie) {

            var cell2 = document.createElement("td");
            cell2.innerHTML = grupe[j].nume;
            cell2.style.padding = "5pt";
            cell2.style.border = "solid 1px #62635f";
            cell2.style.backgroundColor = "#1d1d1d";

            row.appendChild(cell2);
            i++;
        }

    }

    if(i > 0) {
        tbl.appendChild(row);
    }

    node.appendChild(tbl);

    var form = document.createElement("form");
    form.setAttribute('method',"post");
    form.setAttribute('action',"../GrupaProcess");
    form.setAttribute('id',"addgrupa");

    node.appendChild(form);

    node = document.getElementById("addgrupa");

    var input = document.createElement("INPUT");
    input.setAttribute("type", "text");
    input.setAttribute("name","grupadd");
    input.style.marginTop = "8pt";

    node.appendChild(input);

    var input = document.createElement("INPUT");
    input.setAttribute("type", "hidden");
    input.setAttribute("name","idserie");
    input.setAttribute("value",sel.options[sel.selectedIndex].value)

    node.appendChild(input);

    var input = document.createElement("INPUT");
    input.setAttribute("type", "submit");
    input.setAttribute("name","add");
    input.setAttribute("value","Add");
    input.style.marginLeft = "3pt";

    node.appendChild(input);

}

function addTempApp(Id,mail,idApp,formName) {
    var element = document.getElementById("temp");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    }

    email = mail;
    idAplicatie = idApp;

    var node = document.getElementById("app"+Id);

    var row = document.createElement("tr");

    var cell = document.createElement("td");
    cell.setAttribute("colspan", "5");
    cell.style.backgroundColor = "#1d1d1d";
    cell.style.paddingTop = "6pt";
    cell.style.paddingBottom = "6pt";
    cell.style.textAlign = "left";

    row.id = "temp";

    cell.id = "temp2";
    row.appendChild(cell);

    node.parentNode.insertBefore(row, node.nextSibling);

    addTempFacInfo(formName);

}

function addTempAppInfo(Id,Facultate,Serie,Grupa,Email,Act) {
    var element = document.getElementById("temp");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    }


    var node = document.getElementById("app"+Id);

    var row = document.createElement("tr");

    var cell = document.createElement("td");
    cell.style.textAlign = "left";
    cell.setAttribute("colspan", "5");
    cell.innerHTML = "<b>Facultate : &nbsp;&nbsp;</b>"+ Facultate +"<br/>" + "<b>Serie : &nbsp;&nbsp;</b>"+ Serie +"<br/>" + "<b>Grupa : &nbsp;&nbsp;</b>"+ Grupa +"<br/>" + "<b>Email : &nbsp;&nbsp;</b>"+ Email +"<br/>" + "<b>Act : &nbsp;&nbsp;</b> <a class='ui' href='" + Act + "'>" + Act + "</a><br/>";
    cell.style.backgroundColor = "#1d1d1d";
    cell.style.padding = "3pt";
    cell.style.paddingLeft = "6pt";

    cell.id = "temp2";
    row.id = "temp";

    row.appendChild(cell);

    node.parentNode.insertBefore(row, node.nextSibling);

}


function addTempFacInfo(formName) {

    var node = document.getElementById("temp2");

    var form = document.createElement("form");
    form.setAttribute('method',"post");
    form.setAttribute('action',formName);
    form.setAttribute('id',"temp_form");

    fName = formName;

    node.appendChild(form);

    var node = document.getElementById("temp_form");

    var select = document.createElement("SELECT");
    select.setAttribute("id", "sfacultate");
    select.setAttribute("onchange", "addTempSerieInfo(this)");
    select.style.marginLeft = "5pt";

    var option = document.createElement("option");
    option.setAttribute("disabled", "");
    option.setAttribute("value", "");
    option.setAttribute("selected", "");
    option.innerHTML = "--";

    select.appendChild(option);

    for (var j = 0; j < facultati.length; j++) {

        var option = document.createElement("option");
        option.setAttribute("value", facultati[j].idfacultate);
        option.innerHTML = facultati[j].nume;
        select.appendChild(option);

    }

    node.appendChild(select);

}

function addTempSerieInfo(sel) {

    element = document.getElementById("sgrupa");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    }

    element = document.getElementById("sserie");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    }

    cleanTemp();

    var node = document.getElementById("temp_form");

    var select = document.createElement("SELECT");
    select.setAttribute("id", "sserie");
    select.setAttribute("onchange", "addTempGrupaInfo(this)");
    select.style.marginLeft= "5pt";

    var option = document.createElement("option");
    option.setAttribute("disabled", "");
    option.setAttribute("value", "");
    option.setAttribute("selected", "");
    option.innerHTML = "--";

    select.appendChild(option);

    for (var j = 0; j < serii.length; j++) {

        if(sel.options[sel.selectedIndex].value == serii[j].idfacultate) {
            var option = document.createElement("option");
            option.setAttribute("value", serii[j].idserie);
            option.innerHTML = serii[j].nume;
            select.appendChild(option);
        }

    }

    node.appendChild(select);

}

function addTempGrupaInfo(sel) {

    element = document.getElementById("sgrupa");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    }

    cleanTemp();

    var node = document.getElementById("temp_form");

    var select = document.createElement("SELECT");
    select.setAttribute("id", "sgrupa");
    select.setAttribute("onchange", "addTempButton()");
    select.style.marginLeft  = "5pt";

    var option = document.createElement("option");
    option.setAttribute("disabled", "");
    option.setAttribute("value", "");
    option.setAttribute("selected", "");
    option.innerHTML = "--";

    select.appendChild(option);

    for (var j = 0; j < grupe.length; j++) {

        if(sel.options[sel.selectedIndex].value == grupe[j].idserie) {
            var option = document.createElement("option");
            option.setAttribute("value", grupe[j].idgrupa);
            option.innerHTML = grupe[j].nume;
            select.appendChild(option);
        }

    }

    node.appendChild(select);

}

function addTempButton() {

    cleanTemp();

    var node = document.getElementById("temp_form");

    var inp = document.createElement("INPUT");
    inp.setAttribute("type", "submit");
    inp.setAttribute("name","add");
    if(fName.localeCompare("../UpdateFacProcess") == 0)
        inp.setAttribute("value","Update");
    else
        inp.setAttribute("value","Send Invite");
    inp.setAttribute("id","appadd");
    inp.style.marginLeft = "5pt";

    node.appendChild(inp);

    var input = document.createElement("INPUT");
    var input2 = document.createElement("INPUT");
    var input3 = document.createElement("INPUT");
    var input4 = document.createElement("INPUT");
    var input5 = document.createElement("INPUT");
    var select1 = document.getElementById("sfacultate");
    var select2 = document.getElementById("sserie");
    var select3 = document.getElementById("sgrupa");

    input.setAttribute("type", "hidden");
    input.setAttribute("name","idfacultate");
    input.setAttribute("id","idfacultate");
    input.setAttribute("value",select1.options[select1.selectedIndex].value);

    node.appendChild(input);

    input2.setAttribute("type", "hidden");
    input2.setAttribute("name","idserie");
    input2.setAttribute("id","idserie");
    input2.setAttribute("value",select2.options[select2.selectedIndex].value);

    node.appendChild(input2);

    input3.setAttribute("type", "hidden");
    input3.setAttribute("name","idgrupa");
    input3.setAttribute("id","idgrupa");
    input3.setAttribute("value",select3.options[select3.selectedIndex].value);

    node.appendChild(input3);

    input4.setAttribute("type", "hidden");
    input4.setAttribute("name","email");
    input4.setAttribute("id","email");
    input4.setAttribute("value",email);

    node.appendChild(input4);

    input5.setAttribute("type", "hidden");
    input5.setAttribute("name","idaplicatie");
    input5.setAttribute("id","idaplicatie");
    input5.setAttribute("value",idAplicatie);

    node.appendChild(input5);

}

function cleanTemp() {

    var element = document.getElementById("appadd");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    }

    element = document.getElementById("idgrupa");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    }


    element = document.getElementById("idserie");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    }

    element = document.getElementById("idfacultate");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    }

    element = document.getElementById("idaplicatie");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    }

    element = document.getElementById("email");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    }

}