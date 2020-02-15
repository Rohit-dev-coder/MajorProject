/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dto;


public class resultDTO {
    String emailid;
    String examid;
    int totalque;
    int totalattempt;
    int rightans;
    int wrongans;
    int unattempt;
    int totalmarks;
    double percentage;  

    public resultDTO(String emailid, String examid, int totalque, int totalattempt, int rightans, int wrongans, int unattempt, int totalmarks) {
        this.emailid = emailid;
        this.examid = examid;
        this.totalque = totalque;
        this.totalattempt = totalattempt;
        this.rightans = rightans;
        this.wrongans = wrongans;
        this.unattempt = unattempt;
        this.totalmarks = totalmarks;
    }

    @Override
    public String toString() {
        return "resultDTO{" + "emailid=" + emailid + ", examid=" + examid + ", totalque=" + totalque + ", totalattempt=" + totalattempt + ", rightans=" + rightans + ", wrongans=" + wrongans + ", unattempt=" + unattempt + ", totalmarks=" + totalmarks + ", percentage=" + percentage + '}';
    }

    
    public resultDTO() {
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public int getTotalque() {
        return totalque;
    }

    public void setTotalque(int totalque) {
        this.totalque = totalque;
    }

    public int getTotalattempt() {
        return totalattempt;
    }

    public void setTotalattempt(int totalattempt) {
        this.totalattempt = totalattempt;
    }

    public int getRightans() {
        return rightans;
    }

    public void setRightans(int rightans) {
        this.rightans = rightans;
    }

    public int getWrongans() {
        return wrongans;
    }

    public void setWrongans(int wrongans) {
        this.wrongans = wrongans;
    }

    public int getUnattempt() {
        return unattempt;
    }

    public void setUnattempt(int unattempt) {
        this.unattempt = unattempt;
    }

    public int getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(int totalmarks) {
        this.totalmarks = totalmarks;
    }

    public void calculatePercentage(){
        int tm = this.totalmarks/this.totalque;   
        tm = tm*this.rightans;
        this.percentage = ((double)tm/this.totalmarks) * 100;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
    
    
    
}
