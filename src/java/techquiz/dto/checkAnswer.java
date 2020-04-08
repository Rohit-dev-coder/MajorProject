
package techquiz.dto;


public class checkAnswer {
    private String youranswer;
    private String canswer;

    public checkAnswer() {
    }

    public checkAnswer(String youranswer, String canswer) {
        this.youranswer = youranswer;
        this.canswer = canswer;
    }
    
    public String getYouranswer() {
        return youranswer;
    }

    public void setYouranswer(String youranswer) {
        this.youranswer = youranswer;
    }

    public String getCanswer() {
        return canswer;
    }

    public void setCanswer(String canswer) {
        this.canswer = canswer;
    }
    
    
}
