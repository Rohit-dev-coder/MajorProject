<%@page import="techquiz.dto.tfDTO"%>
<%@page import="techquiz.dto.fupsDTO"%>
<%@page import="techquiz.dto.mcqDTO"%>
<%@page import="techquiz.dto.QuestionDTO"%>
<%
    String userid = (String) session.getAttribute("username");
    String usertype = (String) session.getAttribute("usertype");
    if (userid == null || usertype == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }

    if (usertype.equalsIgnoreCase("student")) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    QuestionDTO obj = (QuestionDTO) request.getAttribute("obj");
%>



<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label for="questionno"><b>QuestionNo:</b></label>
            <%=obj.getQid()%>
        </div>
    </div>
</div>
<div class="form-row">
    <div class="col-md-10">
        <div class="form-group">
            <label for="question"><b>Question:</b></label>
            <textarea class="form-control" rows="3" disabled="true" id="question"><%=obj.getQuestion()%></textarea>            
        </div>
    </div>
    <div class="col-md-2">
        <button class="btn btn-danger" onclick="enableedit0(this)" value="question">EDIT</button>
        <button class="btn btn-info" onclick="saveedit(this)" value="question" id="savequestion" hidden="true">SAVE</button>
    </div>

</div>
<%
    if (obj.getqType().equalsIgnoreCase("mcq")) {
        mcqDTO answer = (mcqDTO) request.getAttribute("answer");
%>
<div class="row">
    <div class="col-md-3 ">
        <div class="form-group">
            <label for="question 1">Option 1</label>
            <textarea class="form-control" rows="3" id="option1" disabled="true"><%=answer.getOption1()%></textarea>
        </div>
        <div>
            <button class="btn-sm btn-danger" onclick="enableedit0(this)" value="option1">EDIT</button>
            <button class="btn-sm btn-info" onclick="saveeditoptions(this)" value="option1" id="saveoption1" hidden="true">SAVE</button>
        </div>
    </div>
    <div class="col-md-3 ">
        <div class="form-group">
            <label for="question 2">Option 2</label>
            <textarea class="form-control" rows="3" id="option2" disabled="true" ><%=answer.getOption2()%></textarea>
        </div>
        <div>
            <button class="btn-sm btn-danger" onclick="enableedit0(this)" value="option2">EDIT</button>
            <button class="btn-sm btn-info" onclick="saveeditoptions(this)" value="option2" id="saveoption2" hidden="true">SAVE</button>
        </div>
    </div>
    <div class="col-md-3 ">
        <div class="form-group">
            <label for="question 3">Option 3</label>
            <textarea class="form-control" rows="3" id="option3" disabled="true" ><%=answer.getOption3()%></textarea>
        </div>
        <div>
            <button class="btn-sm btn-danger" onclick="enableedit0(this)" value="option3">EDIT</button>
            <button class="btn-sm btn-info" onclick="saveeditoptions(this)" value="option3" id="saveoption3" hidden="true">SAVE</button>
        </div>
    </div>
    <div class="col-md-3 ">
        <div class="form-group">
            <label for="question 4">Option 4</label>
            <textarea class="form-control" rows="3" id="option4" disabled="true" ><%=answer.getOption4()%></textarea>
        </div>
        <div>
            <button class="btn-sm btn-danger" onclick="enableedit0(this)" value="option4">EDIT</button>
            <button class="btn-sm btn-info" onclick="saveeditoptions(this)" value="option4" id="saveoption4" hidden="true">SAVE</button>
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <label for="question 4">Correct Answer: </label>
            <select id="correctansmcq" onchange="showsavebtn(this)">
                <option disabled="true" selected="true" ><%=answer.getCanswer()%></option>
                <option value="option1">Option1</option>
                <option value="option2">Option2</option>
                <option value="option3">Option3</option>
                <option value="option4">Option4</option>
            </select>
        </div>

    </div>
    <div class="col-md-2">
        <button class="btn-sm btn-info" onclick="saveeditanswerdd(this)" value="correctansmcq" id="savecorrectansmcq" hidden="true">SAVE</button>
    </div>  
</div>
<% } else if (obj.getqType().equalsIgnoreCase("fups")) {
    fupsDTO answer = (fupsDTO) request.getAttribute("answer");
%>
<div class="row">
    <div class="col-md-4 ">
        <div class="form-group">
            <label for="correctansoneword">Answer :</label>
            <textarea class="form-control" rows="2" disabled="true" id="cansoneword"><%=answer.getCanswer()%></textarea>
        </div>
    </div>
    <div class="col-md-2">
        <button class="btn btn-danger" onclick="enableedit0(this)" value="cansoneword">EDIT</button>
        <button class="btn btn-info" onclick="saveeditfups(this)" value="cansoneword" id="savecansoneword" hidden="true">SAVE</button>
    </div>

</div>
<% } else if (obj.getqType().equalsIgnoreCase("tf")) {
    tfDTO answer = (tfDTO) request.getAttribute("answer");
%>
<div class="row">
    <div class="col-md-4 ">
        <div class="form-group">
            <label for="correctanstf">Answer :</label>
            <select id="correctanstf" onchange="showsavebtn(this)">
                <option disabled="true" selected="true"><%=answer.getCanswer()%></option>
                <option value="true">True</option>
                <option value="false">False</option>
            </select>
        </div>
    </div>
    <div class="col-md-2">
        <button class="btn-sm btn-info" onclick="saveeditanswertf(this)" value="correctanstf" id="savecorrectanstf" hidden="true">SAVE</button>
    </div>
</div>
<% }%>
<div class="resultafter" style="color: lightgreen">

</div>
<script type="text/javascript">
    var id;
    var ref;
    function enableedit0(x) {
        var qry = x.innerHTML;
        ref = x;
        if (qry === "EDIT") {
            id = x.getAttribute("value");
            $("#" + id).attr('disabled', false);
            x.innerHTML = "CANCLE";
            $("#save" + id).attr('hidden', false);
        } else if (qry === "CANCLE") {
            var id = x.getAttribute("value");
            $("#" + id).attr('disabled', true);
            x.innerHTML = "EDIT";
            $("#save" + id).attr('hidden', true);
        }
    }
    function saveedit() {
        var question = $('textarea#question').val();
        var data = {
            code: "updateques",
            data: question,
            qid: "<%= obj.getQid()%>"
        }
        $.post("EditQuestionsControllerServlet", data, processresponseforEditexam);
    }
    function saveeditfups(x){
        var canswer = $('textarea#cansoneword').val();
        var data = {
            code: "updatecansfups",
            data: canswer,
            qid: "<%= obj.getQid()%>"
        }
        $.post("EditQuestionsControllerServlet", data, processresponseforEditexam);
    }
    
    function saveeditoptions(x) {
        var id = x.getAttribute("value");
        var vv = $('textarea#' + id).val();
        var data = {
            code: "mcqoptionupdate",
            data: vv,
            qid: "<%= obj.getQid()%>",
            option: id
        }
        $.post("EditQuestionsControllerServlet", data, processresponseforEditexam);
    }
    function saveeditanswerdd(x) {
        var xx = document.getElementById("correctansmcq").selectedIndex;
        var yy = document.getElementById("correctansmcq").options;
        var selectedValue = yy[xx].value;
        var data = {
            code: "mcqcansupdate",
            qid: "<%= obj.getQid()%>",
            data: selectedValue
        }
        $.post("EditQuestionsControllerServlet", data, processresponseforEditexam1);
    }
    function saveeditanswertf(x) {
        var xx = document.getElementById("correctanstf").selectedIndex;
        var yy = document.getElementById("correctanstf").options;
        var selectedValue = yy[xx].value;
        var data = {
            code: "tfcansupdate",
            qid: "<%= obj.getQid()%>",
            data: selectedValue
        }
        $.post("EditQuestionsControllerServlet", data, processresponseforEditexam1);
    }

    function showsavebtn(x) {
        id = x.getAttribute("id");
        $("#save" + id).attr('hidden', false);
    }
    function processresponseforEditexam(res) {
        res = res.trim();
        $('.resultafter').html("<p>Save Succesfully</p>");
        if (res === "success") {
            setTimeout(function () {
                $('.resultafter').html("");
            }, 5000);
            id = ref.getAttribute("value");
            $("#" + id).attr('disabled', true);
            ref.innerHTML = "EDIT";
            $("#save" + id).attr('hidden', true);
            toastr["info"]("Save Successfully", "SAVED!");
        } else {
            toastr["error"]("Not Update", "Failed!");
        }
    }
    function processresponseforEditexam1(res) {
        res = res.trim();
        $('.resultafter').html("<p>Save Succesfully</p>");
        if (res === "success") {
            setTimeout(function () {
                $('.resultafter').html("");
            }, 5000);
            $("#save" + id).attr('hidden', true);
            toastr["info"]("Save Successfully", "SAVED!");
        } else {
            toastr["error"]("Not Update", "Failed!");
        }
    }
</script>