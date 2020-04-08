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
public class CorsesDTO {
    private String crsId,crsTitle;
    private int crsDuration;

    public CorsesDTO() {
    }

    @Override
    public String toString() {
        return "CorsesDTO{" + "crsId=" + crsId + ", crsTitle=" + crsTitle + ", crsDuration=" + crsDuration + '}';
    }
    
    public String getCrsId() {
        return crsId;
    }

    public void setCrsId(String crsId) {
        this.crsId = crsId;
    }

    public String getCrsTitle() {
        return crsTitle;
    }

    public void setCrsTitle(String crsTitle) {
        this.crsTitle = crsTitle;
    }

    public int getCrsDuration() {
        return crsDuration;
    }

    public void setCrsDuration(int crsDuration) {
        this.crsDuration = crsDuration;
    }
    
    
}
