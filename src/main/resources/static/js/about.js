// var overview = document.getElementById('overview');
// var features = document.getElementById('features');
// var demo = document.getElementById('demo');

// var hierarchies = document.getElementById('hierarchies');
// var triggers = document.getElementById('triggers');
// var parameters = document.getElementById('parameters');
// var mappings = document.getElementById('mappings');
// var featuresController = document.getElementById('featuresController');
// var keyboard = document.getElementById('keyboard');
// var controlScenes = document.getElementById('controlScenes');

// var algorithmic = document.getElementById('algorithmic');
// var demoController = document.getElementById('demoController');

// var featuresSections = [hierarchies, triggers, parameters, mappings, featuresController, keyboard, controlScenes];
// var demoSections = [algorithmic, demoController]; 

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