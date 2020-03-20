
package techquiz.dto;

public class chatmessageDTO {
    private String utype;
    private String uemail;
    private String datetime;
    private String message;

    public chatmessageDTO() {
    }

    public String getUtype() {
        return utype;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "chatmessageDTO{" + "utype=" + utype + ", uemail=" + uemail + ", datetime=" + datetime + ", message=" + message + '}';
    }
    
    
}
