
package techquiz.dto;


public class EnrollDTO {
    private String examid;
    private String email;
    private String status;

    public EnrollDTO(String examid, String email, String status) {
        this.examid = examid;
        this.email = email;
        this.status = status;
    }

    public EnrollDTO() {
    }
    
    

    @Override
    public String toString() {
        return "EnrollDTO{" + "examid=" + examid + ", email=" + email + ", status=" + status + '}';
    }
    
    

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
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
