package com.example.cis454agileproject;

// Creation of all users
public class User {
    private String location; // should be a zipcode
    private float timeBank; // all users start at 0
    private String name;
    private String email;
    private String password;
    private String description;

    public User (String email, String pass){
        setEmail(email);
        setPassword(pass);
    }

    // Create user with all fields
    public User (String name, String email, String password, String description, String location){
        setName(name);
        setLocation(location);
        setTimeBank(0);
        setEmail(email);
        setPassword(password);
        this.description = description;
    }

    // Setters and getters for most fields

    public String getName(){ return this.name; }
    public void setName(String name){ this.name = name; }

    public String getPassword(){ return this.password; }
    public void setPassword(String password){ this.password = password; }

    public String getLocation(){ return this.location; }
    public void setLocation(String location) { this.location = location; }

    public float getTimeBank(){ return this.timeBank; }
    public void setTimeBank(float timeBank) { this.timeBank = timeBank; }

    public String getEmail(){ return this.email; }
    public void setEmail(String email) { this.email = email; }

    public String getDescription(){ return this.description; }
    public void setDescription(String description) { this.description = description; }
}


