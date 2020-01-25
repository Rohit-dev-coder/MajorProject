var fname, sname, email, mobile, gender, pwd, repwd;

function removeerrormsg(x) {
    var temp = x.getAttribute('id');
    if ($('#' + temp).hasClass("errorbox")) {
        $('#' + temp).removeClass('errorbox');
    }
}


function addUser() {
    fname = $('#firstname').val();
    sname = $('#secondname').val();
    email = $('#email').val();
    mobile = $('#mobileno').val();
    gender = $('#gender').val();
    pwd = $('#pwd').val();
    repwd = $('#repwd').val();

    // console.log(fname + " " + sname + " " + email + " " + mobile + " " + gender + " " + pwd + " " + repwd);

    if (validateUser()) {
        if (pwd !== repwd) {
            //swal("Error!","password does not match!","error");
            toastr["error"]("password does not match!", "Error!");
            return;
        } else {
            if (checkEmail(email) === false)
                return;
            var data = {
                fname: fname,
                sname: sname,
                email: email,
                mobile: mobile,
                gender: gender,
                pwd: pwd
            };
            $.post("RegistrationControllerServlet", data, processresponse);
        }
    } else {
        toastr["error"]("Fields Requireds", "Error!");
    }
}


function processresponse(responseText) {
    responseText = responseText.trim();
    if (responseText === "success") {

        //swal("success!","Registration successful! you can now login","success");
        toastr["success"]("Registration successful! you can now login", "success!");
        setTimeout(redirectUser, 3000);
    } else if (responseText === "uap") {
        // swal("Error!","sorry! the user with this Email already present","error");
        toastr["warning"]("sorry! the user with this Email already present", "Error!");

    } else {
        //  swal("Error!","Registration failed! try again","error");
        toastr["warning"]("sorry! the user with this Email already present", "Error!")

    }
}


function validateUser() {
    var result = true;
    if (fname === "") {
        document.getElementById("firstname").placeholder = "Required";
        $('#firstname').addClass("errorbox");
        result = false;
    }
    if (sname === "") {
        document.getElementById("secondname").placeholder = "Required";
        $('#secondname').addClass("errorbox");
        result = false;
    }
    if (pwd === "") {
        document.getElementById("pwd").placeholder = "Required";
        $('#pwd').addClass("errorbox");
        result = false;
    }
    if (repwd === "") {
        document.getElementById("repwd").placeholder = "Required";
        $('#repwd').addClass("errorbox");
        result = false;
    }
    if (mobile === "") {
        document.getElementById("mobileno").placeholder = "Required";
        $('#mobileno').addClass("errorbox");
        result = false;
    }
    if (email === "") {
        document.getElementById("email").placeholder = "Required";
        $('#email').addClass("errorbox");
        result = false;
    }
    return result;
}


function checkEmail() {
    var atposition = email.indexOf("@");
    var dotposition = email.lastIndexOf(".");
    if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= email.length) {
        toastr["info"]("please enter valid email", "Error!");
        return false;
    }
    return true;
}

function redirectUser() {
    window.location = "loginpage.html";
}

function clearForm() {
    $('#firstname').val('');
    $('#secondname').val('');
    $('#email').val('');
    $('#mobileno').val('');
    $('#gender').selectedIndex = 0;
    $('#pwd').val('');
    $('#repwd').val('');
    // document.getElementById("firstname").placeholder = '';
    // document.getElementById("secondname").placeholder = '';
    // document.getElementById("pwd").placeholder = '';
    // document.getElementById("repwd").placeholder = '';
    // document.getElementById("mobileno").placeholder = '';
    // document.getElementById("email").placeholder = "Ex. jonh@email.com";

}

function redirectLogin() {
    // swal('Admin!',"Redirect to Login!","success"); 
    toastr["success"]("Redirect to Login!", "Login!");
    setTimeout(function () {
        window.location = "loginpage.html";
    }, 3000);
}

function redirectRegistration() {
    //swal('Admin!',"Redirect to Registration!","success");
    toastr["success"]("Redirect to Registration!", "Registration!")
    setTimeout(function () {
        window.location = "register.html";
    }, 3000);
}