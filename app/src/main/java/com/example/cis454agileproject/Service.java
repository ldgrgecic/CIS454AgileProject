package com.example.cis454agileproject;

public class Service {

    private String poster;
    private String title;
    private double payment;
    private String location;

    public Service(String poster, String title, double payment, String location){
        setPoster(poster);
        setTitle(title);
        setLocation(location);
        setPayment(payment);
    }

    public String getPoster(){ return this.poster; }
    public void setPoster(String poster) { this.poster = poster; }

    public String getTitle(){ return this.title; }
    public void setTitle(String title) { this.title = title; }

    public double getPayment() { return this.payment; }
    public void setPayment(double payment) { this.payment = payment; }

    public String getLocation(){ return this.location; }
    public void setLocation(String location){ this.location = location; }

}
