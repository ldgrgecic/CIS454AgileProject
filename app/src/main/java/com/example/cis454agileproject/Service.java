package com.example.cis454agileproject;

public class Service {

    private String poster;
    private String title;
    private int payment;
    private int location;

    public Service(String poster, String title, int payment, int location){
        setPoster(poster);
        setTitle(title);
        setLocation(location);
        setPayment(payment);
    }

    public String getPoster(){ return this.poster; }
    public void setPoster(String poster) { this.poster = poster; }

    public String getTitle(){ return this.title; }
    public void setTitle(String title) { this.title = title; }

    public int getPayment() { return this.payment; }
    public void setPayment(int payment) { this.payment = payment; }

    public int getLocation(){ return this.location; }
    public void setLocation(int location){ this.location = location; }

}
