
package techquiz.dto;


public class testdetail {
    String examId;
    String examTitle;
    String examDateTime;
    int totalQuestion;
    int totalMarks;
    String email;
    String teachername;
    String status;

    public testdetail() {
    }

    public testdetail(String examId, String examTitle, String examDateTime, int totalQuestion, int totalMarks, String email, String teachername) {
        this.examId = examId;
        this.examTitle = examTitle;
        this.examDateTime = examDateTime;
        this.totalQuestion = totalQuestion;
        this.totalMarks = totalMarks;
        this.email = email;
        this.teachername = teachername;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getExamDateTime() {
        return examDateTime;
    }

    public void setExamDateTime(String examDateTime) {
        this.examDateTime = examDateTime;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
