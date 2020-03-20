<%
    response.setDateHeader("Expires", 0);
    String userid = (String) session.getAttribute("username");
    String utype = (String) session.getAttribute("usertype");
    if (userid == null || utype == null) {

        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
%>

<div class="container-fluid dashboardbgimg">
    <div id="fb-root"></div>
    <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v6.0"></script>
    <div class="col-md-12 mx-auto">
        <div class="resultBox">
            <div class="row">
                <div class="col-md-6">
                    <div class="fb-page"
                         data-href="https://www.facebook.com/Department-of-Computer-science-engineering-BUIT-Bhopal-462026-102785731253473"
                         data-tabs="timeline" 
                         data-width="500px" 
                         data-height="" 
                         data-small-header="true" 
                         data-adapt-container-width="true" 
                         data-hide-cover="false" 
                         data-show-facepile="false">
                        <blockquote cite="https://www.facebook.com/Department-of-Computer-science-engineering-BUIT-Bhopal-462026-102785731253473" class="fb-xfbml-parse-ignore">
                            <a href="https://www.facebook.com/Department-of-Computer-science-engineering-BUIT-Bhopal-462026-102785731253473">
                                Department of Computer science &amp; engineering, BUIT, Bhopal 462026<br>
                                Please: Reload page or check your connection
                            </a>
                        </blockquote>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="fb-page"
                         data-href="https://www.facebook.com/ndtv"
                         data-tabs="timeline" 
                         data-width="500" 
                         data-height="" 
                         data-small-header="true" 
                         data-adapt-container-width="true" 
                         data-hide-cover="false" 
                         data-show-facepile="false">
                        <blockquote cite="https://www.facebook.com/ndtv" class="fb-xfbml-parse-ignore">
                            <a href="https://www.facebook.com/ndtv">NDTV</a></blockquote></div>
                </div>

            </div>
        </div>
    </div>

