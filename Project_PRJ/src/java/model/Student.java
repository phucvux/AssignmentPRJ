/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author CucLe
 */
public class Student {
    private int sid;
    private int sname;
    private User user;
    private ArrayList<Checkstatus> status = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    public ArrayList<Checkstatus> getStatus() {
        return status;
    }

    public void setStatus(ArrayList<Checkstatus> status) {
        this.status = status;
    }
    
    

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSname() {
        return sname;
    }

    public void setSname(int sname) {
        this.sname = sname;
    }
    
    
}
