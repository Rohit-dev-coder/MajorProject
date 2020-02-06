
package techquiz.dto;


public class QuestionDTO {
    private String ExamId;
    private String question;
    private String qType;
    private String qid;

    public QuestionDTO(String ExamId, String question, String qType, String qid) {
        this.ExamId = ExamId;
        this.question = question;
        this.qType = qType;
        this.qid = qid;
    }

    public QuestionDTO() {
    }

    public String getExamId() {
        return ExamId;
    }

    public void setExamId(String ExamId) {
        this.ExamId = ExamId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getqType() {
        return qType;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }
    
    
    
}
