package com.example.phhm.model;

public class Userdata {
    public String  Name,Email,Username,Password,Confirmpass,Account;
   public String Phone;

    public Userdata() {
    }

    public Userdata(String name, String email, String phone, String username, String password, String confirmpass, String account) {
       Name=name;
       Email=email;
       Phone=phone;
       Username=username;
       Password=password;
       Confirmpass=confirmpass;
       Account=account;
    }
}

