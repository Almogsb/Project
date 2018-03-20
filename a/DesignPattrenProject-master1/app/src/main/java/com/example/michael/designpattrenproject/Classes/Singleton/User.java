package com.example.michael.designpattrenproject.Classes.Singleton;

/**
 * Created by Michael on 13/01/2018.
 */

public class User {
    private static final User ourInstance = new User();
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public static User getInstance() {
        return ourInstance;
    }

    private User() {

    }
}
