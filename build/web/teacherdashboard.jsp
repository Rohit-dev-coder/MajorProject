<!doctype html>
<html lang="en">
<%
        response.setDateHeader("Expires", -1);
        String userid = (String)session.getAttribute("username");
        String usertype=(String)session.getAttribute("usertype");
        if(userid == null || usertype == null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
    
        if(usertype.equalsIgnoreCase("student")){
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        else if(usertype.equalsIgnoreCase("teacher")){
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Dashboard <%=userid %></title>

    <link rel="stylesheet" href="bootstrapfiles/bootstrap.min.css">
    <script src="bootstrapfiles/jquery-3.4.1.js"></script>
    <script src="bootstrapfiles/bootstrap.bundle.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Acme|Arvo&display=swap" rel="stylesheet">
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
    <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
    <!--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>-->
    
    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
    <link rel="stylesheet" href="css/myloader.css">

    
    <script defer src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>



    <!-- myscript -->
    <script src="jsScripts/teacherdashboard.js"></script>
    <script src="jsScripts/setexam.js"></script>
    

</head>

<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark sticky-top">
        <a class="navbar-brand" href="#">TechQuiz</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">HOME</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">ABOUT US</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">FORUM</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">CONTACT US</a>
                </li>
            </ul>
            <div class="btn-group mt-2">
                <button type="button" class="btn btn-info">LOGIN</button>
                <button type="button" class="btn btn-outline-info">REGISTER</button>
            </div>

        </div>
    </nav>


    <div class="container-fluid">
        <div class="row">
            <nav class="col-md-3 col-lg-2 d-none d-md-block bg-light sidebar">
                <div class="sidebar-sticky">
                    <ul class="nav flex-column" >
                        <li class="nav-item" >
                            <span class="nav-link active" id="Profile" onclick="showContent(this)" >
                                <span data-feather="home"></span>
                                Profile
                            </span>
                        </li>
                        <li class="nav-item">
                            <span class="nav-link" id="Set-Exams" onclick="showContent(this)">
                                <span data-feather="file"></span>
                                Set Exams
                            </span>
                        </li>
                        <li class="nav-item">
                            <span class="nav-link" id="Exams-details" onclick="showContent(this)">
                                <span data-feather="check-square"></span>
                                Exams Detail
                            </span>
                        </li>
                        
                        <li class="nav-item">
                            <span class="nav-link" id="Declared-Result" onclick="showContent(this)">
                                <span data-feather="users"></span>
                                Declared Result
                            </span>
                        </li>
                        <li class="nav-item">
                            <span class="nav-link" id="Forum" onclick="showContent(this)">
                                <span data-feather="users"></span>
                                Forum
                            </span>
                        </li>
                        <li class="nav-item">
                            <span class="nav-link" id="Settings" onclick="showContent(this)">
                                <span data-feather="settings"></span>
                                Settings
                            </span>
                        </li>
                    </ul>
                    

                </div>
            </nav>
            
            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4 mybgimg">
                <div id = "user-dashboard"
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 ">
                    <!-- dynamically changed title -->
                    <!--<h1 class="h1">Profile</h1>-->

                    <div class="loader mx-auto"></div>
                    <div class="data-result">
                        
                        <!-- body of the title goes here  -->
                    </div>
                </div>

            </main>

           
        </div>
    </div>



    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
        feather.replace()
    </script>

    <!-- Graphs -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
    
</body>

</html>
<% }
%>