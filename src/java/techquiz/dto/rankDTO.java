
package techquiz.dto;

public class rankDTO {
    private String email;
    private double per;

    public rankDTO(String email, double per) {
        this.email = email;
        this.per = per;
    }

    public rankDTO() {
    }

    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPer() {
        return per;
    }

    public void setPer(double per) {
        this.per = per;
    }
    
    
}
