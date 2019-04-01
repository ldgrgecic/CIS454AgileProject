package com.example.cis454agileproject;

// Creation of all users
public class User {
    int userID;
    int location; // should be a zipcode
    float timeBank; // all users start at 0
    String email;
    String password;
    String description;
    String type; // "Provider" vs "Requester" vs "Both", here for now if needed


    // Create user with all fields
    public User (int userID, String email, String password, String description,
                 int location, float timeBank, String type){
        this.userID = userID;
        this.location = location;
        this.timeBank = timeBank;
        this.email = email;
        this.password = password;
        this.description = description;
        this.type = type;
    }

    // Setters and getters for most fields
    public int getUserID(){
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getLocation(){
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public float getTimeBank(){
        return timeBank;
    }

    public void setTimeBank(float timeBank) {
        this.timeBank = timeBank;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription(){
        return email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


