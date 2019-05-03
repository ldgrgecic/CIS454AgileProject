package com.example.cis454agileproject;

public class Service {
    private String serviceId;
    private String posterId;
    private String poster;
    private String title;
    private double payment;
    private String location;

    // empty service creation
    public Service(){}

    // create service with all fields
    public Service(String posterId, String poster, String title, double payment, String location){
        setPoster(poster);
        setPosterId(posterId);
        setTitle(title);
        setLocation(location);
        setPayment(payment);
    }

    // setters and getters for all fields
    public String getServiceId(){ return this.serviceId;}
    public void setServiceId(String serviceId){this.serviceId = serviceId; }
    public String getPosterId(){ return this.posterId; }
    public void setPosterId(String posterId){ this.posterId = posterId; }

    public String getPoster(){ return this.poster; }
    public void setPoster(String poster) { this.poster = poster; }

    public String getTitle(){ return this.title; }
    public void setTitle(String title) { this.title = title; }

    public double getPayment() { return this.payment; }
    public void setPayment(double payment) { this.payment = payment; }

    public String getLocation(){ return this.location; }
    public void setLocation(String location){ this.location = location; }

}
