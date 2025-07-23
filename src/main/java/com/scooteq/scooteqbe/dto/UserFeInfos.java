package com.scooteq.scooteqbe.dto;

public class UserFeInfos {
    String username;
    String role;
    public UserFeInfos(String username,String role){
        this.username= username;
        this.role = role;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername(){
        return username;
    }
    public void setRole(String role){
        this.role=role;
    }
    public String getRole(){
        return role;
    }
}
