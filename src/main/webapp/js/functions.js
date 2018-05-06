var facultati = [];
var serii = [];
var grupe = [];

var email = "";
var idAplicatie = "";
var fName  = "";

function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    m = checkTime(m);
    if(document.getElementById('time') != null) {
        document.getElementById('time').innerHTML =
            h + ":" + m;
        var t = setTimeout(startTime, 500);
    }
}

function checkTime(i) {
    if (i < 10) {i = "0" + i};
    return i;
}

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

function addNewsForm(idSerie) {
    var node = document.getElementById('news');
    var br = document.createElement("br");

    element = document.getElementById("newsrow");
    if(element != null) {
        element.outerHTML = "";
        delete element;
    } else {

        var form = document.createElement("form");
        form.setAttribute('method',"post");
        form.setAttribute('action',"/AddNewsProcess");
        form.setAttribute('id',"temp_form");

        var trow = document.createElement("tr");
        trow.setAttribute('id',"newsrow")
        var tdata = document.createElement("td");
        tdata.style.backgroundColor = "#2c2c2c";
        tdata.style.padding = "8pt";
        tdata.style.textAlign = "center";

        var divtitle = document.createElement("div");

        var label = document.createElement("p");
        label.innerHTML = "Title: ";
        label.style.display = "inline";
        label.style.marginLeft = "5pt";

        var title = document.createElement("INPUT");
        title.setAttribute("type", "text");
        title.setAttribute("name","news_title");
        title.size = "60";

        divtitle.appendChild(label);
        divtitle.appendChild(title);
        form.appendChild(divtitle);

        var inpserie = document.createElement("INPUT");
        inpserie.setAttribute("type", "hidden");
        inpserie.setAttribute("name","news_serie");
        inpserie.setAttribute("value",idSerie);

        form.appendChild(inpserie);

        var text = document.createElement("textarea");
        text.rows = "12";
        text.cols = "100";
        text.style.marginTop = "5pt";
        text.setAttribute("name","news_body");

        form.appendChild(text);
        form.appendChild(br);

        var inp = document.createElement("INPUT");
        inp.setAttribute("type", "submit");
        inp.setAttribute("name","add");
        inp.setAttribute("value","Add News");
        inp.style.marginTop = "5pt";

        form.appendChild(inp);

        tdata.appendChild(form);
        trow.appendChild(tdata);

        node.parentNode.insertBefore(trow, node.nextSibling);

    }
}

function editNews(type,id) {

    var element = document.getElementById("temp_form_edit");

    var form = document.createElement("form");
    form.setAttribute('method',"post");
    form.setAttribute('action',"/EditNewsProcess");
    form.setAttribute('id',"temp_form_edit");

    if(type==1) {
        form.style.display = "inline";
        var td = document.getElementById("title_"+id);
        var td_title = document.getElementById("title_name_"+id);
        var a_title = document.getElementById("news_edit_"+id);

        if(td_title!= null) {
            var title = td_title.textContent;

            td_title.outerHTML = "";
            delete td_title;
            a_title.outerHTML = "";
            delete a_title;


            var input = document.createElement("INPUT");
            input.setAttribute("type", "text");
            input.setAttribute("name","news_title_temp");
            input.setAttribute("id","news_title_temp");
            input.setAttribute("value",title);
            input.size = "40";

            var inpserie = document.createElement("INPUT");
            inpserie.setAttribute("type", "hidden");
            inpserie.setAttribute("name","news_id_temp");
            inpserie.setAttribute("id","news_id_temp");
            inpserie.setAttribute("value",id);

            var inptype = document.createElement("INPUT");
            inptype.setAttribute("type", "hidden");
            inptype.setAttribute("name","news_type");
            inptype.setAttribute("id","news_type");
            inptype.setAttribute("value",1);

            form.appendChild(input);
            form.appendChild(inpserie);
            form.appendChild(inptype);

            var inp = document.createElement("INPUT");
            inp.setAttribute("type", "submit");
            inp.setAttribute("name","edit");
            inp.setAttribute("value","Save");
            inp.style.marginLeft = "5pt";
            inp.style.padding = "0pt";

            form.appendChild(inp);

            td.appendChild(form);

        }
    } else if(type == 2) {
        var td = document.getElementById("body_"+id);
        var a_body = document.getElementById("news_edit_body_"+id);
        var text = document.getElementById("news_body_"+id)

        if(td!= null) {
            a_body.outerHTML = "";
            delete a_body;

            td.innerHTML = "";

            var input = document.createElement("textarea");
            input.setAttribute("name","news_body_temp");
            input.setAttribute("id","news_body_temp");
            input.innerHTML = text.value;
            input.rows = "15";
            input.cols = "125";

            var inpserie = document.createElement("INPUT");
            inpserie.setAttribute("type", "hidden");
            inpserie.setAttribute("name","news_id_temp");
            inpserie.setAttribute("id","news_id_temp");
            inpserie.setAttribute("value",id);

            var inptype = document.createElement("INPUT");
            inptype.setAttribute("type", "hidden");
            inptype.setAttribute("name","news_type");
            inptype.setAttribute("id","news_type");
            inptype.setAttribute("value",2);

            form.appendChild(input);
            form.appendChild(inpserie);
            form.appendChild(inptype);

            var inp = document.createElement("INPUT");
            inp.setAttribute("type", "submit");
            inp.setAttribute("name","edit");
            inp.setAttribute("value","Save");
            inp.style.marginTop = "7pt";

            var center = document.createElement("div");
            center.style.textAlign = "center";
            center.appendChild(inp);

            form.appendChild(center);

            td.appendChild(form);

        }
    }

}

function deleteNews(id) {
    if(confirm("Sunteti sigur ca doriti sa stergeti anuntul?") == true)
    window.location.href = '/EditNewsProcess?id='+id;
}

function deleteOra(id) {
    if(confirm("Sunteti sigur ca doriti sa stergeti ora?") == true)
        window.location.href = '/DeleteOraProcess?id_ora='+id;
}