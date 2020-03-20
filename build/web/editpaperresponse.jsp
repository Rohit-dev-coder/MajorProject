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
    </div>
    <div class="col-md-3 ">
        <div class="form-group">
            <label for="question 2">Option 2</label>
            <textarea class="form-control" rows="3" id="option2" disabled="true" ><%=answer.getOption2()%></textarea>
        </div>
    </div>
    <div class="col-md-3 ">
        <div class="form-group">
            <label for="question 3">Option 3</label>
            <textarea class="form-control" rows="3" id="option3" disabled="true" ><%=answer.getOption3()%></textarea>
        </div>
    </div>
    <div class="col-md-3 ">
        <div class="form-group">
            <label for="question 4">Option 4</label>
            <textarea class="form-control" rows="3" id="option4" disabled="true" ><%=answer.getOption4()%></textarea>
        </div>
    </div>
    <div class="col-md-8">
        <div class="form-group">
            <label for="question 4">Correct Answer: </label>
            <select id="correctansmcq">
                <option disabled="true" selected="true"><%=answer.getCanswer()%></option>
                <option value="option1">Option1</option>
                <option value="option2">Option2</option>
                <option value="option3">Option3</option>
                <option value="option4">Option4</option>
            </select>
        </div>
    </div>
</div>
<% } else if (obj.getqType().equalsIgnoreCase("fups")) {
    fupsDTO answer = (fupsDTO) request.getAttribute("answer");
%>
<div class="row">
    <div class="col-md-8 ">
        <div class="form-group">
            <label for="correctansoneword">Answer :</label>
            <textarea class="form-control" rows="2" disabled="true" ><%=answer.getCanswer()%></textarea>
        </div>
    </div>
</div>
<% } else if (obj.getqType().equalsIgnoreCase("tf")) {
    tfDTO answer = (tfDTO) request.getAttribute("answer");
%>
<div class="row">
    <div class="col-md-8 ">
        <div class="form-group">
            <label for="correctanstf">Answer :</label>
            <select id="correctanstf">
                <option disabled="true" selected="true"><%=answer.getCanswer()%></option>
                <option value="true">True</option>
                <option value="false">False</option>
            </select>
        </div>
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
        }
    }
</script>