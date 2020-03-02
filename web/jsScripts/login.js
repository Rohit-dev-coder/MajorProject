var username,password;


function connectUser(x)
{    
    var str = x.getAttribute("id");
    if(str === 'stdbtn')
    {
        username=$("#email1").val();
        password=$("#pwd1").val();
        type = 'student';
    }
    else if (str === 'tbtn'){
        username=$("#email").val();
        password=$("#pwd").val();
        type = 'teacher';
    }
            
    if(validate()===false)
    {
        toastr["warning"]("sorry! please enter userid/pasword!", "Error!");
        return ;
    }

    var tempck =  navigator.cookieEnabled;
    if(tempck === false){
        toastr["warning"]("Please Enable Cookies", "Error!");
        return;
    }
    
    var data={
        username:username,
        password:password,
        type: type
    };
    $.post("LoginControllerServlet",data,processresponse);
    
}
function processresponse(responseText) {
    responseText=responseText.trim();
    if(responseText==="error") {
       
       // swal("Access Denied","please enter valid userid/passwrod","error");
       toastr["warning"]("sorry! please enter username/password!", "Error!");
        
    }
    else if(responseText.indexOf("jsessionid")!==-1) {
        // swal("Success","Login Accepted","success");
       toastr["success"](" Login successful! Login Accepted", "success!");
        setTimeout(function(){
           window.location=responseText;
        },3000);
    }
    else if(responseText === "browsererror"){
        toastr["info"]("Some problem occured please try again later!", "Info!");
        window.location = "browsererror.html";
    }
    else {
        //swal("AcessDenied","Some problem occured please try again later","error");
          toastr["error"]("Some problem occured please try again later!", "Error!");
        
    }
}
function validate(){
    if(username ==="" || password==="") {   
       // swal("Error!","All fields are mandatory!","error");
       toastr["error"]("All fields are mandatory!", "Error!");
        return false;
    }
    else {
        return true;
    }
}

