package com.example.gcalsamaven;

import java.io.*;

public class User implements java.io.Serializable {

    private boolean isExisting;

    public boolean isExisting() {
        return isExisting;
    }

    public void setExisting(boolean existing) {
        isExisting = existing;
    }

    private boolean periodA;
    private boolean periodB;
    private boolean periodC;
    private boolean periodD;
    private boolean periodE;
    private boolean periodF;
    private boolean periodG;
    private boolean periodH;
    private boolean periodAdvisory;
    private String nameA;
    private String nameB;
    private String nameC;
    private String nameD;
    private String nameE;
    private String nameF;
    private String nameG;
    private String nameH;
    private String nameAdvisory;

    public User(boolean periodA, boolean periodB, boolean periodC, boolean periodD, boolean periodE, boolean periodF, boolean periodG, boolean periodH, boolean periodAdvisory, String nameA, String nameB, String nameC, String nameD, String nameE, String nameF, String nameG, String nameH, String nameAdvisory) {
        this.periodA = periodA;
        this.periodB = periodB;
        this.periodC = periodC;
        this.periodD = periodD;
        this.periodE = periodE;
        this.periodF = periodF;
        this.periodG = periodG;
        this.periodH = periodH;
        this.periodAdvisory = periodAdvisory;
        this.nameA = nameA;
        this.nameB = nameB;
        this.nameC = nameC;
        this.nameD = nameD;
        this.nameE = nameE;
        this.nameF = nameF;
        this.nameG = nameG;
        this.nameH = nameH;
        this.nameAdvisory = nameAdvisory;
        this.isExisting = false;
    }

    //serialize and deserialize method reference: https://www.geeksforgeeks.org/serialization-in-java/

    public void serialize() throws IOException {
        String filename = System.getProperty("user.home") + "/Desktop/" + "Compressed" + ".txt";
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);

        // Method for serialization of object
        out.writeObject(this);

        out.close();
        file.close();
    }

    public User deserialize() throws IOException, ClassNotFoundException, FileNotFoundException {
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(System.getProperty("user.home") + "/Desktop/" + "Compressed" + ".txt");
            ObjectInputStream in = new ObjectInputStream(file);
            //what happens if the file is not found?

            // Method for deserialization of object
            User user = (User) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            return user;
        }
        catch (FileNotFoundException e){
            System.out.println("File Not Found Exception");
            return null;
        }
    }


    public boolean isPeriodA() {
        return periodA;
    }

    public void setPeriodA(boolean periodA) {
        this.periodA = periodA;
    }

    public boolean isPeriodB() {
        return periodB;
    }

    public void setPeriodB(boolean periodB) {
        this.periodB = periodB;
    }

    public boolean isPeriodC() {
        return periodC;
    }

    public void setPeriodC(boolean periodC) {
        this.periodC = periodC;
    }

    public boolean isPeriodD() {
        return periodD;
    }

    public void setPeriodD(boolean periodD) {
        this.periodD = periodD;
    }

    public boolean isPeriodE() {
        return periodE;
    }

    public void setPeriodE(boolean periodE) {
        this.periodE = periodE;
    }

    public boolean isPeriodF() {
        return periodF;
    }

    public void setPeriodF(boolean periodF) {
        this.periodF = periodF;
    }

    public boolean isPeriodG() {
        return periodG;
    }

    public void setPeriodG(boolean periodG) {
        this.periodG = periodG;
    }

    public boolean isPeriodH() {
        return periodH;
    }

    public void setPeriodH(boolean periodH) {
        this.periodH = periodH;
    }

    public boolean isPeriodAdvisory() {
        return periodAdvisory;
    }

    public void setPeriodAdvisory(boolean periodAdvisory) {
        this.periodAdvisory = periodAdvisory;
    }

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public String getNameD() {
        return nameD;
    }

    public void setNameD(String nameD) {
        this.nameD = nameD;
    }

    public String getNameE() {
        return nameE;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE;
    }

    public String getNameF() {
        return nameF;
    }

    public void setNameF(String nameF) {
        this.nameF = nameF;
    }

    public String getNameG() {
        return nameG;
    }

    public void setNameG(String nameG) {
        this.nameG = nameG;
    }

    public String getNameH() {
        return nameH;
    }

    public void setNameH(String nameH) {
        this.nameH = nameH;
    }

    public String getNameAdvisory() {
        return nameAdvisory;
    }

    public void setNameAdvisory(String nameAdvisory) {
        this.nameAdvisory = nameAdvisory;
    }
}
