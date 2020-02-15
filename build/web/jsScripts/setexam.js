var examdate,examtitle,examtime,totalmarks,totalquestion;

function removeerrormsg(x) {
    var temp = x.getAttribute('id');
    if ($('#' + temp).hasClass("errorbox")) {
        $('#' + temp).removeClass('errorbox');
    }
}



function setExam() {
    examtitle = $('#examtitle').val();
    examdate = $('#examdate').val();
    examtime = $('#examtime').val();
    totalquestion = $('#totalquestion').val();
    totalmarks = $('#totalmarks').val();
    
    console.log(examtitle+" "+examdate+" "+examtime+" "+totalmarks+" "+totalquestion);
    if (validateDetails()) {
        var data = {
            code: "paperdetails", 
            examtitle: examtitle,
            examdate: examdate,
            examtime: examtime,
            tquestion: totalquestion,
            tmarks: totalmarks
        };
        alert(data);
        $.post("SetQuestionsControllerServlet", data, processresponseforsetexam);
    }
    else {
        toastr["error"]("Fields Requireds", "Error!");
    }
}
function processresponseforsetexam(responseText) {
    responseText = responseText.trim();
    clearDataResult();
    console.log(responseText);
    if(responseText === "saveexam"){
        toastr["info"]("Save Successfully", "SAVED!");
        setTimeout(onloadmyfunction,3000);
    }
    else{
        $(".data-result").append(responseText);
    }
    
}


function clearSetExamForm() {
    $('#examtime').val('');
    $('#examtitle').val('');
    $('#examdate').val('');
    $('#totalmarks').val('');
    $('#totalquestion').val('');
}


function validateDetails() {
    var result = true;
    if (examtitle === "") {
        $('#examtitle').addClass("errorbox");
        toastr["error"]("Title Requireds", "Error!");
        result = false;
    }
    if (examdate === "") {
        $('#examdate').addClass("errorbox");
        toastr["error"]("Date Requireds", "Error!");
        result = false;
    }
    if (examtime === "") {
        $('#examtime').addClass("errorbox");
        toastr["error"]("Time Requireds", "Error!");
        result = false;
    }
    if (totalquestion === "") {
        $('#totalquestion').addClass("errorbox");
        toastr["error"]("Total Question Requireds for further processing", "Error!");
        result = false;
    }
    if (totalmarks === "") {
        $('#totalmarks').addClass("errorbox");
        toastr["error"]("Title Marks Requireds for futher processing", "Error!");
        result = false;
    }
    return result;
}




function showanswercol(){
    var x = document.getElementById("ddquestiontype").selectedIndex;
    var y = document.getElementById("ddquestiontype").options;
//    alert("Index: " + y[x].index + " is " + y[x].value);
    var selectedValue = y[x].value;
    if (selectedValue === 'mcq'){
        $(".mcqcorrectanscol").show();
        $(".onewordanscol").hide();
        $(".tfanscol").hide();
    }
    else if (selectedValue === 'fups'){
        $(".mcqcorrectanscol").hide();
        $(".onewordanscol").show();
        $(".tfanscol").hide();
    }
    else if (selectedValue === 'tf')
    {
        $(".mcqcorrectanscol").hide();
        $(".onewordanscol").hide();
        $(".tfanscol").show();
    }   
}

function cancleexam(){
    var data = {
        code: "cancleexam"
    };
    $.post("SetQuestionsControllerServlet", data, processresponseforsetexam);
}



var qtype,question,option1,option2,option3,option4,cansow,canstf,cansmcq;
function nextque(){
    qtype = $('#ddquestiontype').val();
    question = $('#question').val();
    option1 = $('#option1').val();
    option2 = $('#option2').val();
    option3 = $('#option3').val();
    option4 = $('#option4').val();
    cansmcq = $('#correctansmcq').val();
    cansow = $('#correctanswerow').val();
    canstf = $('#correctanstf').val();
    
    var data;
    if(validatequestion()){
        
        if(qtype === "mcq")
        {
            data = {
                qtype: qtype,
                code: "nextque",
                question: question,
                option1: option1,
                option2: option2,
                option3: option3,
                option4: option4,
                cans: cansmcq
            };
        }
        else if(qtype === "fups")
        {
            data = {
                qtype: qtype,
                code: "nextque",
                question: question,
                cans: cansow
            };
        }
        else if(qtype === "tf")
        {
            data = {
                qtype: qtype,
                code: "nextque",
                question: question,
                cans: canstf
            };
        }       
        $.post("SetQuestionsControllerServlet", data, processresponseforsetexam);
    }
    else{
        toastr["error"]("Please Fill the fields", "Error!");
    }
    
}

function saveexam(){
    data = {
        code: "saveexam"
    }
    $.post("SetQuestionsControllerServlet", data, processresponseforsetexam);    
}



function validatequestion(){
    var result = true;
    if (qtype === null) {
        $('#ddquestiontype').addClass("errorbox");
        toastr["error"]("TYPE REQUIRED", "Error!");
        result = false;
    }
    else{
        if(question === ""){
            $('#question').addClass("errorbox");
            toastr["error"]("Title Requireds", "Error!");
            result = false;
        }
        if(qtype === "mcq"){
            if(option1 === ""){
                $('#option1').addClass("errorbox");
                toastr["error"]("Title Requireds", "Error!");
                result = false;
            }
            if(option2 === ""){
                $('#option2').addClass("errorbox");
                toastr["error"]("Title Requireds", "Error!");
                result = false;
            }
            if(option3 === ""){
                $('#option3').addClass("errorbox");
                toastr["error"]("Title Requireds", "Error!");
                result = false;
            }
            if(option4 === ""){
                $('#option4').addClass("errorbox");
                toastr["error"]("Title Requireds", "Error!");
                result = false;
            }
            if(cansmcq === null)
            {
                $('#correctansmcq').addClass("errorbox");
                toastr["error"]("Title Requireds", "Error!");
                result = false;
            }
        }
        else if(qtype === "fups")
        {
            if(cansow === "")
            {
                $('#correctanswerow').addClass("errorbox");
                toastr["error"]("Title Requireds", "Error!");
                result = false;
            }
        }
        else if(qtype === 'tf')
        {
            if(canstf === null)
            {
                $('#correctanstf').addClass("errorbox");
                toastr["error"]("Title Requireds", "Error!");
                result = false;
            }
        }
    }
    return result;
}

