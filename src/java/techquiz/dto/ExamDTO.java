
package techquiz.dto;


public class ExamDTO {
    private String examId;
    private String examTitle;
    private String examDateTime;
    private int totalQuestion;
    private int totalMarks;
    private String email;
    private String status;

    public ExamDTO(String examId, String examTitle, String examDateTime, int totalQuestion, int totalMarks, String email) {
        this.examId = examId;
        this.examTitle = examTitle;
        this.examDateTime = examDateTime;
        this.totalQuestion = totalQuestion;
        this.totalMarks = totalMarks;
        this.email = email;
    }

    public ExamDTO() {
    }

    @Override
    public String toString() {
        return "ExamDAO{" + "examId=" + examId + ", examTitle=" + examTitle + ", examDateTime=" + examDateTime + ", totalQuestion=" + totalQuestion + ", totalMarks=" + totalMarks + ", email=" + email + '}';
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
