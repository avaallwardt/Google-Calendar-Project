package com.example.gcalsamaven;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class SecondScreen {

    @FXML
    private Label quote;

    @FXML
    private AnchorPane scene2;

    @FXML
    private Label directions;

    @FXML
    private Label directionsTitle;

    @FXML
    private Button newCalendar;

    @FXML
    private Button existingCalendar;

    @FXML
    private Label title;


    private static ArrayList<String> quotes = new ArrayList<>();

    public static void fillQuotes(){
        quotes.add("“A good teacher can inspire hope, ignite the imagination, and instill a love of learning.“\n- Brad Henry");
        quotes.add("“Teachers can change lives with just the right mix of chalk and challenges.“\n- Joyce Meyer");
        quotes.add("“Teaching is the one profession that creates all other professions.“\n- Unknown");
        quotes.add("“Better than a thousand days of diligent study is one day with a great teacher.“\n- Japanese Proverb");
        quotes.add("“The function of education is to teach one to think intensively and to think critically.“ Intelligence plus character—that is the goal of true education.\n- Martin Luther King, Jr.");
        quotes.add("“One child, one teacher, one book, one pen can change the world.”\n– Malala Yousafzai.");
        quotes.add("“Children are likely to live up to what you believe of them.”\n– Lady Bird Johnson");
        quotes.add("“What the teacher is, is more important than what he teaches.”\n– Karl Menninger");
        quotes.add("“It is the supreme art of the teacher to awaken joy in creative expression and knowledge.“\n- Albert Einstein");
        quotes.add("“Teaching is not just a job. It is a human service, and it must be thought of as a mission.”\n- Dr. Ralph Tyler");
    }

    @FXML
    private Label text;

    @FXML
    public void initialize(){
        fillQuotes();
        int randomNumber = (int) (Math.random() * (9-0+1));
        quote.setText(quotes.get(randomNumber));
    }

    /*
    @FXML
    protected void onStartButtonClick() {
        fillQuotes();
        int randomNumber = (int) (Math.random() * 9);
        quote.setText(quotes.get(randomNumber));
        start.setVisible(false);
        newCalendar.setVisible(true);
        existingCalendar.setVisible(true);
        directions.setVisible(true);
        directionsTitle.setVisible(true);
    }

     */

    @FXML
    protected void onExistingClick(ActionEvent event) throws IOException {
        Data.getUser().setExisting(true);
        AnchorPane scene1 = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        scene2.getChildren().removeAll();
        scene2.getChildren().setAll(scene1);
    }

    @FXML
    protected void onNewClick(ActionEvent event) throws IOException {
        AnchorPane scene1 = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        scene2.getChildren().removeAll();
        scene2.getChildren().setAll(scene1);
    }



}
