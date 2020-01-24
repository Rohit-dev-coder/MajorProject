
let fname,sname,email,mobile,gender,pwd,repwd;

function addUser()
{
    fname = $('#firstname').val();
    sname = $('#secondname').val();
    email = $('#email').val();
    mobile = $('#mobileno').val();
    gender = $('#gender').val();
    pwd = $('#pwd').val();
    repwd = $('#repwd').val();
    
    console.log(fname+" "+sname+" "+email+" "+mobile+" "+gender+" "+pwd+" "+repwd);
    
    if(validateUser()){
    }
}

function validateUser() {
    if (fname === "")
    {
        
    }
}