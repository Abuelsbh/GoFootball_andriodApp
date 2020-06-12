package com.example.gofootball;

public class user {
    public String name,email,pass,phone,location,eWallet;
    public int ID;

    public user(String name, String email, String pass, String phone, String location, String eWallet, int ID) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.location = location;
        this.eWallet = eWallet;
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void seteWallet(String eWallet) {
        this.eWallet = eWallet;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String geteWallet() {
        return eWallet;
    }

    public int getID() {
        return ID;
    }
}

