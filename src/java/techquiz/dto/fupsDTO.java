
package techquiz.dto;

public class fupsDTO {
    private String qid;
    private String canswer;

    public fupsDTO(String qid, String canswer) {
        this.qid = qid;
        this.canswer = canswer;
    }

    public fupsDTO() {
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
