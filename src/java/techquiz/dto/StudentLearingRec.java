
package techquiz.dto;

public class StudentLearingRec {
    private String email,lecId,crsId;
    private int watchedMin;

    @Override
    public String toString() {
        return "StudentLearingRec{" + "email=" + email + ", lecId=" + lecId + ", crsId=" + crsId + ", watchedMin=" + watchedMin + '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLecId() {
        return lecId;
    }

    public void setLecId(String lecId) {
        this.lecId = lecId;
    }

    public String getCrsId() {
        return crsId;
    }

    public void setCrsId(String crsId) {
        this.crsId = crsId;
    }

    public int getWatchedMin() {
        return watchedMin;
    }

    public void setWatchedMin(int watchedMin) {
        this.watchedMin = watchedMin;
    }
    
    
}
