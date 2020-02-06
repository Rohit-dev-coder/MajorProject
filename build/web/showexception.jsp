<%-- 
    Document   : showexception
    Created on : 26 Jan, 2020, 9:19:14 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
         <link href="stylesheet/pageheader.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Exception Page</title>
    </head>
    <body>
        <br>
        <div class="candidate">
            <p>Tech_Quiz Application......</p>
        </div>
        <br>
        <div class="subcandidate">
            <p>Please Try Again later</p>
        </div>
        
        <%
            Exception ex=(Exception)request.getAttribute("exception");
            System.out.println(ex);
            out.println("<p>"+ex+"</p>");
        %>
    </body>
</html>
