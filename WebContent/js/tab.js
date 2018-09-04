function openCity(evt, cityName) {
    // document.getElementById(cityName).parentNode.style.color = "blue";
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" w3-indigo", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " w3-indigo";
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
