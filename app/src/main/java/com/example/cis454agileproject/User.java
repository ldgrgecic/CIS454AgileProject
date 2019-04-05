package com.example.cis454agileproject;

// Creation of all users
public class User {
    private int userID;
    private int location; // should be a zipcode
    private float timeBank; // all users start at 0
    private String email;
    private String password;
    private String description;
    private String type; // "Provider" vs "Requester" vs "Both", here for now if needed


    // Create user with all fields
    public User (int userID, String email, String password, String description,
                 int location, float timeBank, String type){
        setUserID(userID);
        setLocation(location);
        setTimeBank(timeBank);
        setEmail(email);
        setPassword(password);
        setDescription(description);
        setType(type);
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

    public void setPassword(String password) { this.password = password; }

    public String getDescription(){
        return description;
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


