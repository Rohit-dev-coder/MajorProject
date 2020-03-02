
package techquiz.dto;

public class stdexamdetails {
    private String examId;
    private String examTitle;
    private String examDateTime;
    private String email;
    private String status;
    private String examstatus;

    public stdexamdetails(String examId, String examTitle, String examDateTime, String email, String status) {
        this.examId = examId;
        this.examTitle = examTitle;
        this.examDateTime = examDateTime;
        this.email = email;
        this.status = status;
    }

    @Override
    public String toString() {
        return "stdexamdetails{" + "examId=" + examId + ", examTitle=" + examTitle + ", examDateTime=" + examDateTime + ", email=" + email + ", status=" + status + '}';
    }

    public String getExamstatus() {
        return examstatus;
    }

    public void setExamstatus(String examstatus) {
        this.examstatus = examstatus;
    }
    

    
    public stdexamdetails() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
