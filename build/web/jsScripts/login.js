//


var username,password;
function connectUser()
{
    username=$("#email").val();
    password=$("#pwd").val();
    if(validate()===false)
    {
    // swal("Access Denied ","please enter userid/pasword!","error");
     toastr["warning"]("sorry! please enter userid/pasword!", "Error!");
     return;
     
            
    }
    
    var data={
        username:username,
        password:password
        
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

