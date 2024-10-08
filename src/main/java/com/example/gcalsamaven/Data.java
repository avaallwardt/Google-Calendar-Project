package com.example.gcalsamaven;

public class Data {

    private static User user = new User(false, false, false, false, false, false, false, false, false, "", "", "", "", "", "", "", "", "");

    public Data(User user){
        this.user = user;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Data.user = user;
    }
}
