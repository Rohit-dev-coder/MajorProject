
package techquiz.dto;

public class answersDTO {
    private String qid;
    private String qtype;
    private String canswer;

    public answersDTO(String qid, String qtype, String canswer) {
        this.qid = qid;
        this.qtype = qtype;
        this.canswer = canswer;
    }

    public answersDTO() {
    }
    
    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public String getCanswer() {
        return canswer;
    }

    public void setCanswer(String canswer) {
        this.canswer = canswer;
    }
    
    
}
