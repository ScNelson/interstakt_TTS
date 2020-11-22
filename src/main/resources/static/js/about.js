function onLoaded() {
    if (currSection != "about") {
        document.getElementById(currSection).setAttribute("class", "list-group-item-heading active");
    }
    if (currSubSection != null) {
        document.getElementById(currSubSection).setAttribute("class", "list-group-item-text active");
    } else {
        fillOverview();
    }
}

function fillOverview() {

}