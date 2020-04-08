
package techquiz.dto;

public class LecturesDTO {
    private String crsId,lecId,lecTitle,lecRef;
    private int lecTime;

    public String getCrsId() {
        return crsId;
    }

    @Override
    public String toString() {
        return "LecturesDTO{" + "crsId=" + crsId + ", lecId=" + lecId + ", lecTitle=" + lecTitle + ", lecRef=" + lecRef + ", lecTime=" + lecTime + '}';
    }

    public void setCrsId(String crsId) {
        this.crsId = crsId;
    }

    public String getLecId() {
        return lecId;
    }

    public void setLecId(String lecId) {
        this.lecId = lecId;
    }

    public String getLecTitle() {
        return lecTitle;
    }

    public void setLecTitle(String lecTitle) {
        this.lecTitle = lecTitle;
    }

    public String getLecRef() {
        return lecRef;
    }

    public void setLecRef(String lecRef) {
        this.lecRef = lecRef;
    }

    public int getLecTime() {
        return lecTime;
    }

    public void setLecTime(int lecTime) {
        this.lecTime = lecTime;
    }
    
    
}
