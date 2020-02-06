
package techquiz.dto;


public class tfDTO {
    private String qid;
    private String canswer;

    public tfDTO(String qid, String canswer) {
        this.qid = qid;
        this.canswer = canswer;
    }

    public tfDTO() {
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getCanswer() {
        return canswer;
    }

    public void setCanswer(String canswer) {
        this.canswer = canswer;
    }
    
    
}
