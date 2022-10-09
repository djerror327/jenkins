var responce;

function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            responce = this.responseText;
        }
    };
    xhttp.open("GET", "/v1/cpu", true);
    xhttp.send();
}

function createTiles(serverNme, cpu) {
    //get row element
    var row = document.getElementById('row')
        //elements
    var divRemove = document.createElement("div");
    var divCoulmn = document.createElement("div");
    var divTile = document.createElement("div");
    var h4CPU = document.createElement("h4");
    var divTileContent = document.createElement("div");
    var h3SeverName = document.createElement("h3");
    var h1Percentage = document.createElement("h1");
    var cpuHeader = document.createTextNode("CPU");
    var severName = document.createTextNode(serverNme);
    var cpuText = document.createTextNode(cpu + "%");

    // //classes
    var tile = document.createAttribute("class");

    // //set class names
    // column.value = "column";
    tile.value = "tile";

    //set classes into element
    divTile.setAttributeNode(tile);

    row.appendChild(divRemove);
    divRemove.appendChild(divCoulmn);
    divCoulmn.appendChild(divTile);
    divTile.appendChild(h4CPU);
    divTile.appendChild(cpuHeader);
    divTile.appendChild(divTileContent);
    divTileContent.appendChild(h3SeverName);
    divTileContent.appendChild(h1Percentage);
    h3SeverName.appendChild(severName);
    h1Percentage.appendChild(cpuText);
}

function setTileData() {
    var obj = JSON.parse(responce);
    var size = Object.keys(obj).length;
    var data = Object.values(obj);
    var indexes = Object.keys(obj);
    var childCount = document.getElementById("row").childElementCount;

    for (var a = 0; a < childCount; a++) {
        var myobj = document.getElementById("row");
        myobj.removeChild(myobj.childNodes[0]);
    }

    for (var a = 0; a < size; a++) {
        createTiles(indexes[a], data[a]);
    }
}

var intervalId = window.setInterval(function() {
    /// call your function here
    loadDoc();
    setTileData();
}, 900);