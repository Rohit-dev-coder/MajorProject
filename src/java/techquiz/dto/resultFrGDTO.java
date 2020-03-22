/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dto;

/**
 *
 * @author PC
 */
public class resultFrGDTO {
    private String etitle;
    private double percentage;

    public resultFrGDTO(String etitle, double percentage) {
        this.etitle = etitle;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "resultFrGDTO{" + "etitle=" + etitle + ", percentage=" + percentage + '}';
    }

    public resultFrGDTO() {
    }

    public String getEtitle() {
        return etitle;
    }

    public void setEtitle(String etitle) {
        this.etitle = etitle;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
    
}
