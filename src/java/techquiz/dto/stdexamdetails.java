
package techquiz.dto;

public class stdexamdetails {
    String examId;
    String examTitle;
    String examDateTime;
    String email;
    String status;

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
