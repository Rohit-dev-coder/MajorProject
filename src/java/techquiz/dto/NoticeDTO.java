
package techquiz.dto;

public class NoticeDTO {
    private String message;
    private String date;
    private String fname;
    private String sname;
    private String email;
    private String nid;

    public NoticeDTO() {
    }

    @Override
    public String toString() {
        return "NoticeDTO{" + "message=" + message + ", date=" + date + ", fname=" + fname + ", sname=" + sname + '}';
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getMessage() {
        return message;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

   
    

    public void setMessage(String message) {
        this.message = message;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
