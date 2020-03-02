
package techquiz.dto;

public class examsampleinfo {
    private String examid;
    private String examtitle;
    

    public examsampleinfo(String examid, String examtitle) {
        this.examid = examid;
        this.examtitle = examtitle;
    }

   
    
    public examsampleinfo() {
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public String getExamtitle() {
        return examtitle;
    }

    public void setExamtitle(String examtitle) {
        this.examtitle = examtitle;
    }
    
    
}
