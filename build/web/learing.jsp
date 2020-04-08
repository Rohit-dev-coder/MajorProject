<%@page import="techquiz.dto.StudentLearingRec"%>
<%
    response.setDateHeader("Expires", 0);
    String userid = (String) session.getAttribute("username");
    String utype = (String) session.getAttribute("usertype");
    if (userid == null || utype == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    if (utype.equalsIgnoreCase("student")) {
        String vref = (String) request.getAttribute("vref");
        String ltitle = (String) request.getAttribute("ltitle");
        StudentLearingRec slr = (StudentLearingRec) request.getAttribute("slr");
        int lastmin = slr.getWatchedMin();
        int lectotaltime = (Integer)request.getAttribute("lectt");
//        System.out.println("Last min :"+lastmin);
        
%>
<div class="container-fluid dashboardbgimg">
    <div class="col-md-12 mx-auto">
        <div class="resultBox" style="padding: 10px">
            <div class="row">
                <div class="col-md-2" style="margin-bottom: 5px">
                    <button class="btn btn-info" onclick="backLec()">Back</button>
                </div>
                <div class="col-md-8">
                    <p style="font-size: 20px; font-weight: bold; text-align: center"><%=ltitle%></p>
                </div>

                <hr>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div id="player">
                    </div>
                </div>
                <script>
                    var vref = "<%=vref%>";
                    var lastmin = "<%=lastmin%>";
                    var lastmininsec = lastmin * 60;

                    if (typeof youtube_api_init === 'undefined') {
                        var tag = document.createElement('script');
                        tag.src = "https://www.youtube.com/iframe_api";
                        var firstScriptTag = document.getElementsByTagName('script')[0];
                        firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
                    }
                    if (typeof youtube_api_init !== 'undefined') {
                        onYouTubeIframeAPIReady();
                    }
                    var player;
                    function onYouTubeIframeAPIReady() {
                        player = new YT.Player('player', {
                            height: '450',
                            width: '1000',
                            events: {
                                'onReady': onPlayerReady,
                                'onStateChange': onPlayerStateChange
                            }

                        });
                    }


                    // 4. The API will call this function when the video player is ready.
                    var yt = 0;
                    function onPlayerReady(event) {
                        yt = 1;
                        event.target.cueVideoById(vref, lastmininsec);
                    }

                    window.onresize = function () {

                    };

                    // 5. The API calls this function when the player's state changes.
                    //    The function indicates that when playing a video (state=1),
                    //    the player should play for six seconds and then stop.
//                    var done = false;
                    function onPlayerStateChange(event) {
                        if (event.data === YT.PlayerState.PLAYING && !done) {
//                            setTimeout(stopVideo, 6000);
//                            done = true;
                        }
                    }
                    function stopVideo() {
                        player.stopVideo();

                    }


                    function playFullscreen() {
                        player.playVideo();//won't work on mobile

                        var requestFullScreen = iframe.requestFullScreen || iframe.mozRequestFullScreen || iframe.webkitRequestFullScreen;
                        if (requestFullScreen) {
                            requestFullScreen.bind(iframe)();
                        }
                    }


                    WatchTotalTime = 0;
                    var totalLecTime = <%= lectotaltime %>;
                    var countsec = 0;
                    videoduration = setInterval(function () {
                        var xs = player.getPlayerState();
                        if (xs === 1) {
                            countsec = 0;                            
                            WatchTotalTime++;
                        } else if (xs === 2) {
                            countsec++;
                            if (countsec >= 30) {
                                countsec = 0;
                                backLec();
                            }
                            console.log(Math.round(WatchTotalTime / 60));                            
                        }

                    }, 1000);


                    function updateRec() {
                        wtt = Math.round(WatchTotalTime / 60) + Number(lastmin);
                        console.log(WatchTotalTime);
                        console.log("Total wtt " + wtt);
                        if(wtt > totalLecTime){
                            wtt = totalLecTime;
                        }
                        var data = {
                            data: wtt,
                            code: 'UpdateRecord',
                            crsid: "<%= slr.getCrsId()%>",
                            lecid: "<%= slr.getLecId()%>"
                        };
                        $.post("LearningControllerServlet", data, function (rt) {
                            toastr["success"]("Progress Saved", "success!");
                        });
                    }
                    function backLec() {
                        clearInterval(videoduration);
                        updateRec();
                        var data = {
                            data: "<%= slr.getCrsId()%>",
                            code: 'lecturesList'
                        };
                        $.post("LearningControllerServlet", data, processresponse);
                    }
                    var youtube_api_init = 1;
                </script>
            </div>
        </div>
    </div>
</div>
</div>


<% }%>