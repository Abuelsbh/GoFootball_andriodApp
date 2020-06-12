package com.example.gofootball;

public class playground {
    String name,location,size,availablehours,price,cancelationhours;

    public playground(String name, String location, String size, String availablehours, String price, String cancelationhours) {
        this.name = name;
        this.location = location;
        this.size = size;
        this.availablehours = availablehours;
        this.price = price;
        this.cancelationhours = cancelationhours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAvailablehours(String availablehours) {
        this.availablehours = availablehours;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCancelationhours(String cancelationhours) {
        this.cancelationhours = cancelationhours;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getSize() {
        return size;
    }

    public String getAvailablehours() {
        return availablehours;
    }

    public String getPrice() {
        return price;
    }

    public String getCancelationhours() {
        return cancelationhours;
    }
}
