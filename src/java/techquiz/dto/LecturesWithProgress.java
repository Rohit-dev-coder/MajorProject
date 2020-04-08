/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dto;

public class LecturesWithProgress {
    private LecturesDTO ld;
    private int stdprog;

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    
    public LecturesDTO getLd() {
        return ld;
    }

    public void setLd(LecturesDTO ld) {
        this.ld = ld;
    }

    public int getStdprog() {
        return stdprog;
    }

    public void setStdprog(int stdprog) {
        this.stdprog = stdprog;
    }
    
    
}
