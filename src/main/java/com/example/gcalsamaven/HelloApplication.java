package com.example.gcalsamaven;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SecondScreen.class.getResource("second-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 400);
        stage.setTitle("GCalSA App");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}