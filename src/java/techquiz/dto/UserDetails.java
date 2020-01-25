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
public class UserDetails {
    private String fname;
    private String sname;
    private String email;
    private String gender;
    private long mobile;
    private String pwd;

    public UserDetails() {
    }

    public UserDetails(String fname, String sname, String email, String gender, long mobile, String pwd) {
        this.fname = fname;
        this.sname = sname;
        this.email = email;
        this.gender = gender;
        this.mobile = mobile;
        this.pwd = pwd;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    
    
    
}
