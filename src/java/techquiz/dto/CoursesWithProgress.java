/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dto;

public class CoursesWithProgress {
    private CorsesDTO c;
    private int min;

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    
    public CorsesDTO getC() {
        return c;
    }

    public void setC(CorsesDTO c) {
        this.c = c;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
    
    
    
}
