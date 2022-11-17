<#import "navbar.ftl" as n>

<#macro page song_src song_name song_author>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="/static/css/style.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/mediaelement/4.2.6/mediaelementplayer.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/mediaelement-plugins/2.5.0/speed/speed.min.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/mediaelement-plugins/2.5.0/skip-back/skip-back.min.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/mediaelement-plugins/2.5.0/jump-forward/jump-forward.min.css'>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <title>MusicApp</title>

        <style>
            .navbar {
                padding: 0.7rem 1rem;
            }
            .bg-light {
                background: #dbdcde!important;
            }
            .container-player {
                width: 100%;
                margin: 0 auto;
                height: 100%;
            }

            .mejs__container {
                margin-top: 10px;
            }
            .mejs__controls {
                display: block;
            }
            .mejs__controls, .mejs__container {
                background: none !important;
            }
            .mejs__time {
                color: #777;
                font-weight: normal;
            }

            .podcast {
                background: #dbdcde;
                padding: 0 10px;
                border-radius: 3px;
            }
            .podcast__episode_title {
                font-size: 18px;
                font-weight: bold;
                line-height: 1.5;
                margin-bottom: 0;
                padding-bottom: 0;
                margin-top: 0;
                color: #333;
            }
            .podcast__title {
                display: flex;
                flex-direction: row;
                justify-content: center;
                margin-top: 0px;
                margin-bottom: 0px;
                text-transform: uppercase;
                font-size: 11px;
                color: #555;
            }
            .podcast__title i {
                font-style: normal;
                font-weight: normal;
                text-transform: capitalize;
                margin-left: 10px;
            }
            .podcast__meta {
                background: #dbdcde;
                margin: -22px -10px -10px -10px;
                padding: 0 10px 15px 10px;
                display: flex;
                justify-content: space-between;
                border-bottom-left-radius: 3px;
                border-bottom-right-radius: 3px;
            }
            .podcast__meta .artwork {
                margin-right: 0;
                transform: translateY(-20px);
            }
            .podcast__meta .artwork > img {
                border-radius: 3px;
                width: 100px;
                height: 100px;
                box-shadow: 0 4px 6px 0 rgba(0, 0, 0, 0.1);
                margin-bottom: -20px;
            }

            .mejs-prepended-buttons {
                display: flex;
                margin-bottom: 10px;
            }

            .mejs-appended-buttons {
                display: none;
                justify-content: space-evenly;
            }

            .mejs-speed-button, .mejs__speed-button {
                display: none;
            }

            .mejs__play > button {
                background: transparent url("https://cdn.jsdelivr.net/gh/ivorpad/images-src/mediaplayer-sprite-gray.svg") no-repeat;
            }

            .mejs__pause > button {
                background: transparent url("https://cdn.jsdelivr.net/gh/ivorpad/images-src/mediaplayer-sprite-gray.svg") no-repeat;
                background-position: -20px 0;
            }

            .mejs__mute > button {
                background: transparent url("https://cdn.jsdelivr.net/gh/ivorpad/images-src/mediaplayer-sprite-gray.svg") no-repeat;
                background-position: -60px 0;
                display: none;

            }

            .mejs__unmute > button {
                background: transparent url("https://cdn.jsdelivr.net/gh/ivorpad/images-src/mediaplayer-sprite-gray.svg") no-repeat;
                background-position: -40px 0;
                display: none;

            }

            .mejs-skip-back-button > button,
            .mejs__skip-back-button > button {
                background: transparent url("https://cdn.jsdelivr.net/gh/ivorpad/images-src/skipback.svg");
                display: none;
            }

            .mejs-jump-forward-button > button, .mejs__jump-forward-button > button {
                background: url("https://cdn.jsdelivr.net/gh/ivorpad/images-src/jumpforward.svg") no-repeat;
                display: none;
            }

            .mejs__button > button {
                color: #555;
            }

            .mejs__horizontal-volume-slider {
                display: none !important;

            }

            .mejs__button > button {
                margin: 0px 0px;
            }

            .mejs__time-rail {
                padding-top: 0;
                height: 20px;
            }

            .mejs__time {
                height: 10px;
                padding: 5px 5px;
            }

            .mejs__controls {
                height: 25px;
            }

            .mejs__button {
                height: 20px;
            }

        </style>
    </head>
    <body>
    <@n.nav song_src song_name song_author />

    <div class="container mt-5">
        <#nested>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="/static/js/main.js"></script>

    <script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-2c7831bb44f98c1391d6a4ffda0e1fd302503391ca806e7fcc7b9b87197aec26.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/mediaelement/4.2.6/mediaelement-and-player.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/mediaelement-plugins/2.5.0/skip-back/skip-back.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/mediaelement-plugins/2.5.0/jump-forward/jump-forward.min.js'></script>
    <script src='https://cdn.jsdelivr.net/gh/ivorpad/mediaelement-changespeed/changespeed.js'></script>
    <script id="rendered-js" >
        "use strict";

        const options = {
            defaultSpeed: '1.00',
            speeds: ['1.25', '1.50', '2.00', '0.75'],
            loop: true,
            skipBackInterval: 15,
            jumpForwardInterval: 15,
            features: [
                "playpause",
                "progress",
                "current",
                "duration",
                "skipback",
                "changespeed",
                "volume",
                "jumpforward"] };



        new MediaElementPlayer(
            document.querySelector("audio"),
            options);


        // Separate the audio controls so I can style them better.
        (() => {
            const elementTop = document.createElement('div');
            // const elementBottom = document.createElement('div');
            elementTop.classList.add('mejs-prepended-buttons');
            //elementBottom.classList.add('mejs-appended-buttons');

            const controls = document.querySelector('.mejs__controls');
            controls.prepend(elementTop);
            //controls.append(elementBottom);

            const controlsChildren = Array.from(controls.childNodes).filter(v => v.className.startsWith("mejs_"));

            controlsChildren.slice(0, 3).forEach(elem => {
                elementTop.append(elem);
            });

            // controlsChildren.slice(3, controlsChildren.length).forEach(elem => {
            //     elementBottom.append(elem);
            // });
        })();
        //# sourceURL=pen.js
    </script>

    </body>
    </html>
</#macro>