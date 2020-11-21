var input = document.getElementById('myInput');

var levelsDiv = document.getElementById('levelsList');
var scoreTitleDiv = document.createElement('div');
var voiceTitleDiv = document.createElement('div');
var sceneTitleDiv = document.createElement('div');
var scoreDiv = document.createElement('div');
var voiceDiv = document.createElement('div');
var sceneDiv = document.createElement('div');
var scoreUL = document.createElement('ul');
var voiceUL = document.createElement('ul');
var sceneUL = document.createElement('ul');
scoreUL.setAttribute("class", "list-group");
voiceUL.setAttribute("class", "list-group");
sceneUL.setAttribute("class", "list-group");
var all = false;
var filter, levelName, i, j, levelTitle, levelsList, levelListed, levelLink, levelPath, levelName, scoreId, voiceId, sceneId, levelScore, levelVoice;

function onLoaded() {
    searchLevels();
    scoreUL.setAttribute("class", "level-ul");
    voiceUL.setAttribute("class", "level-ul");
    sceneUL.setAttribute("class", "level-ul");
}

function searchLevels() {
    all = false;
    filter = input.value.toUpperCase();

    clearAllNodes();

    scoreDiv.appendChild(scoreTitleDiv);
    scoreDiv.appendChild(scoreUL);
    
    voiceDiv.appendChild(voiceTitleDiv);
    voiceDiv.appendChild(voiceUL);
    
    sceneDiv.appendChild(sceneTitleDiv);
    sceneDiv.appendChild(sceneUL);

    if (input.value.length != 0) {
        fillAllLevels();
    }

}

function viewAllLevels() {
    all = true;

    input.value = '';

    clearAllNodes();

    scoreDiv.appendChild(scoreTitleDiv);
    scoreDiv.appendChild(scoreUL);
    
    voiceDiv.appendChild(voiceTitleDiv);
    voiceDiv.appendChild(voiceUL);
    
    sceneDiv.appendChild(sceneTitleDiv);
    sceneDiv.appendChild(sceneUL);

    fillAllLevels();
}

function createLevelTitle(levelDiv, levelTitleDiv, title) {
    if (levelDiv.hasChildNodes()) {
        levelTitle = document.createElement("p");
        levelTitle.setAttribute("class", "level-title");
        levelTitle.innerHTML = title;
        levelTitleDiv.appendChild(levelTitle);
    }
}

function fillAllLevels() {
    fillLevelsDiv(filter, "score", scoreList, scoreDiv, scoreUL);
    fillLevelsDiv(filter, "voice", voiceList, voiceDiv, voiceUL);
    fillLevelsDiv(filter, "scene", sceneList, sceneDiv, sceneUL);
}

function fillLevelsDiv(txtin, level, list, levelDiv, levelUL) {
    
    levelDiv.setAttribute("class", "level-div col-md-4");

    for (i = 0; i < list.length; i++) {
        if (level == "score") {
            scoreId = list[i][0];
            levelName = list[i][1];
        }

        if (level == "voice") {
            scoreId = list[i][0];
            voiceId = list[i][1];
            levelName = list[i][2];
            levelScore = list[i][3];
        }

        if (level ==  "scene") {
            scoreId = list[i][0];
            voiceId = list[i][1];
            sceneId = list[i][2];
            levelName = list[i][3];
            levelScore = list[i][4];
            levelVoice = list[i][5];
        }

        if (all) {
            createLevelDiv(level, levelUL);
        } else {
            if (levelName.toUpperCase().indexOf(txtin) > -1) {
                createLevelDiv(level, levelUL);
            }
        }
    }
        switch (level) {
            case "score":
                createLevelTitle(levelDiv, scoreTitleDiv, "SCORES");
                break;
            case "voice":
                createLevelTitle(levelDiv, voiceTitleDiv, "VOICES");
                break;
            case "scene":
                createLevelTitle(levelDiv, sceneTitleDiv, "SCENES");
                break;
        }
    levelsDiv.appendChild(levelDiv);
}

function clearNodes(node) {
    while (node.hasChildNodes()) {
        node.removeChild(node.lastChild);
    }
}

function clearAllNodes() {
    clearNodes(levelsDiv);
    clearNodes(scoreDiv);
    clearNodes(voiceDiv);
    clearNodes(sceneDiv);
    clearNodes(scoreTitleDiv);
    clearNodes(voiceTitleDiv);
    clearNodes(sceneTitleDiv);
    clearNodes(scoreUL);
    clearNodes(voiceUL);
    clearNodes(sceneUL);
}

function createLevelDiv(level, levelUL) {
    levelListed = document.createElement("li");
    levelListed.setAttribute("class", "list-group-item");
    
    levelLink = document.createElement("a");
    levelLink.setAttribute("class", "");
    
    levelPath = document.createElement("a");
    levelPath.setAttribute("class", "level-path");
    switch (level) {
        case "score":
            levelLink.setAttribute("href", "/" + username + "/score/" + levelName + "/" + scoreId);
            break;
        case "voice":
            levelLink.setAttribute("href", "/" + username + "/score/" + levelScore + "/" + levelName + "/" + scoreId + "-" + voiceId);
            levelPath.innerHTML = "<span><a href='/" + username + "/score/" + levelScore + "/" + scoreId + "'>" + levelScore + "</a></span>";
            break;
        case "scene":
            levelLink.setAttribute("href", "/" + username + "/score/" + levelScore + "/" + levelVoice + "/" + levelName + "/" + scoreId + "-" + voiceId + "-" + sceneId);
            levelPath.innerHTML = "<span><a href='/" + username + "/score/" + levelScore + "/" + scoreId + "'>" + levelScore + "</a></span> <span class='arrow'>></span> <span><a href='/" + username + "/score/" + levelScore + "/" + levelName + "/" + scoreId + "-" + voiceId + "'>" + levelVoice + "</a></span>";;
            break;
    }
    levelLink.innerHTML = levelName;
    levelListed.appendChild(levelLink);
    levelListed.appendChild(levelPath);
    levelUL.appendChild(levelListed);
}