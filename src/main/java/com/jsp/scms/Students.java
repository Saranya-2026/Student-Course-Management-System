package com.jsp.scms;
import java.sql.Date;
public class Students {
    private int id;
    private String name;
    private String email;
    private Date dob;
    public Students() {
    }
    public Students(int id, String name, String email, Date dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
 
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + "]";
    }
}