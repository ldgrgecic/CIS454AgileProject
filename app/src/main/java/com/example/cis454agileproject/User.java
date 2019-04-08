package com.example.cis454agileproject;

// Creation of all users
public class User {
    private int userID;
    private int location; // should be a zipcode
    private float timeBank; // all users start at 0
    private String name;
    private String email;
    private String username;
    private String password;
    private String description;


    // Create user with all fields
    public User (String name, int userID, String email, String username, String password, String description,
                 int location, float timeBank){
        setName(name);
        setUserID(userID);
        setLocation(location);
        setTimeBank(timeBank);
        setEmail(email);
        setUsername(username);
        setPassword(password);
        this.description = description;
    }

    // Setters and getters for most fields

    public String getName(){ return this.name; }
    public void setName(String name){ this.name = name; }

    public int getUserID(){ return this.userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getUsername(){ return this.username; }
    public void setUsername(String username){ this.username = username; }

    public String getPassword(){ return this.password; }
    public void setPassword(String password){ this.password = password; }

    public int getLocation(){ return this.location; }
    public void setLocation(int location) { this.location = location; }

    public float getTimeBank(){ return this.timeBank; }
    public void setTimeBank(float timeBank) { this.timeBank = timeBank; }

    public String getEmail(){ return this.email; }
    public void setEmail(String email) { this.email = email; }

    public String getDescription(){ return this.description; }
    public void setDescription(String description) { this.description = description; }
}


