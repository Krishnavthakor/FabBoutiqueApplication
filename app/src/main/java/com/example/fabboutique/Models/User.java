package com.example.fabboutique.Models;

public class User {
    public String firstName,lastName,email,password;
    public long telephoneno;
    public User()
    {

    }
    public User(String firstName,String lastName,String email,long telephoneno,String password)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.telephoneno=telephoneno;
        this.password=password;
    }

    public String getUsername() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public long getTelephoneNo() {
        return this.telephoneno;
    }
    public void setUsername(String email) {
         this.email=email;
    }
    public void setPassword(String password) {
         this.password=password;
    }
    public void setFirstName(String firstName) {
         this.firstName=firstName;
    }
    public void setLastName(String lastName) {
         this.lastName=lastName;
    }
    public void setTelephoneNo(String telephoneNo) {
         this.telephoneno=telephoneno;
    }
}
