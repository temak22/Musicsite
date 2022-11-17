

// serialNumber.addEventListener('mouseover', (e) => {
//     if (e.target && e.relatedTarget && e.target.closest('div') !== e.relatedTarget.closest('div') && e) {
//         serialNumber.style.display = 'none';
//         playButton.style.display = 'block';
//     }
// }, false)
//
// playButton.addEventListener('mouseout', (e) => {
//     if (e.target && e.relatedTarget && e.target.closest('div') !== e.relatedTarget.closest('div') && e) {
//         playButton.style.display = 'none';
//         serialNumber.style.display = 'block';
//     }
// }, false)

var serialNumberList = document.getElementsByClassName("serialNumber");
var playButtonList = document.getElementsByClassName("playButton");
var i;
for (i = 0; i < serialNumberList.length; i++) {
    let playButton = playButtonList[i];
    serialNumberList[i].onmouseover = function(e) {
        if (e.target && e.relatedTarget && e.target.closest('div') !== e.relatedTarget.closest('div') && e) {
            this.style.display = 'none';
            playButton.style.display = 'block';
        }
    }
    serialNumberList[i].onmouseleave = function(e) {
        if (e.target && e.relatedTarget && e.target.closest('div') !== e.relatedTarget.closest('div') && e) {
            this.style.display = 'block';
            playButton.style.display = 'none';
        }
    }
}



var albumImgList = document.getElementsByClassName("albumImg");
var playButtonImgList = document.getElementsByClassName("playButtonImg");

for (i = 0; i < albumImgList.length; i++) {
    let playButton = playButtonImgList[i];
    albumImgList[i].onmouseover = function(e) {
        if (e.target && e.relatedTarget && e.target.closest('div') !== e.relatedTarget.closest('div') && e) {
            this.style.display = 'none';
            playButton.style.display = 'block';
        }
    }
    albumImgList[i].onmouseleave = function(e) {
        if (e.target && e.relatedTarget && e.target.closest('div') !== e.relatedTarget.closest('div') && e) {
            this.style.display = 'block';
            playButton.style.display = 'none';
        }
    }
}

function play() {
    // document.querySelector('audio').setAttribute("src", "/static/mp3/Kendrick_Lamar_-_DNA.mp3");
    document.querySelector('.mejs__play').firstElementChild.click();
}



