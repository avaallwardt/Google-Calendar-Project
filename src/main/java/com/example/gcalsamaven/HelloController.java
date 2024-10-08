package com.example.gcalsamaven;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HelloController extends Thread {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Stage stage;

    @FXML
    private Label welcomeText;



    @FXML
    private Button welcomeButton;

    @FXML
    private CheckBox checkboxA;
    @FXML
    private CheckBox checkboxB;
    @FXML
    private CheckBox checkboxC;
    @FXML
    private CheckBox checkboxD;
    @FXML
    private CheckBox checkboxE;
    @FXML
    private CheckBox checkboxF;
    @FXML
    private CheckBox checkboxG;
    @FXML
    private CheckBox checkboxH;
    @FXML
    private CheckBox checkboxAdvisory;

    @FXML
    private TextField inputA;
    @FXML
    private TextField inputB;
    @FXML
    private TextField inputC;
    @FXML
    private TextField inputD;
    @FXML
    private TextField inputE;
    @FXML
    private TextField inputF;
    @FXML
    private TextField inputG;
    @FXML
    private TextField inputH;
    @FXML
    private TextField inputAdvisory;
    @FXML
    private TextField inputEmail;

    @FXML
    private AnchorPane scene1;

    @FXML
    private Button generateButton;
    @FXML
    private Button nukeButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button saveButton;

    @FXML
    private Label errorLabel;

    private GCalHandler gCalHandler;

    @FXML
    private Button returnHome;



    public boolean checkForErrors(){
        int numCheckboxes = 0;
        int numInputs = 0;
        if(checkboxA.isSelected()){
            numCheckboxes++;
            System.out.println(inputA.getText());
            if(!inputA.getText().equals("") && !(inputA.getText() == null)){
                numInputs++;
            }
        }
        if(checkboxB.isSelected()){
            numCheckboxes++;
            if(!inputB.getText().equals("") && !(inputB.getText() == null)){
                numInputs++;
            }
        }
        if(checkboxC.isSelected()){
            numCheckboxes++;
            if(!inputC.getText().equals("") && !(inputC.getText() == null)){
                numInputs++;
            }
        }
        if(checkboxD.isSelected()){
            numCheckboxes++;
            if(!inputD.getText().equals("") && !(inputD.getText() == null)){
                numInputs++;
            }
        }
        if(checkboxE.isSelected()){
            numCheckboxes++;
            if(!inputE.getText().equals("") && !(inputE.getText() == null)){
                numInputs++;
            }
        }
        if(checkboxF.isSelected()){
            numCheckboxes++;
            if(!inputF.getText().equals("") && !(inputF.getText() == null)){
                numInputs++;
            }
        }
        if(checkboxG.isSelected()){
            numCheckboxes++;
            if(!inputG.getText().equals("") && !(inputG.getText() == null)){
                numInputs++;
            }
        }
        if(checkboxH.isSelected()){
            numCheckboxes++;
            if(!inputH.getText().equals("") && !(inputH.getText() == null)){
                numInputs++;
            }
        }
        if(checkboxAdvisory.isSelected()){
            numCheckboxes++;
            if(!inputAdvisory.getText().equals("") || !(inputAdvisory.getText() == null)){
                numInputs++;
            }
        }

        if(numCheckboxes == 0 && numInputs == 0){
            errorLabel.setText("Please select and enter a name for at least one class.");
            return true;
        }
        else if(numCheckboxes == 0){
            errorLabel.setText("Please select at least one class.");
            return true;
        }
        else if(numInputs == 0){
            errorLabel.setText("Please name all selected classes.");
            return true;
        }
        else if(numCheckboxes > numInputs){
            errorLabel.setText("Please name all selected classes.");
            return true;
        }
        return false;
    }

    @FXML
    protected void clearErrorText(){
        errorLabel.setText("");
    }

    @FXML
    protected void onGenerateButtonClick() throws IOException, GeneralSecurityException {
        clearErrorText();
        if(checkForErrors()){
            return; // if there are errors, do not proceed
        }
        if(checkboxA.isSelected()){
            gCalHandler.findPeriodAClasses(inputA.getText());
        }
        if(checkboxB.isSelected()){
            gCalHandler.findPeriodBClasses(inputB.getText());
        }
        if(checkboxC.isSelected()){
            gCalHandler.findPeriodCClasses(inputC.getText());
        }
        if(checkboxD.isSelected()){
            gCalHandler.findPeriodDClasses(inputD.getText());
        }
        if(checkboxE.isSelected()){
            gCalHandler.findPeriodEClasses(inputE.getText());
        }
        if(checkboxF.isSelected()){
            gCalHandler.findPeriodFClasses(inputF.getText());
        }
        if(checkboxG.isSelected()){
            gCalHandler.findPeriodGClasses(inputG.getText());
        }
        if(checkboxH.isSelected()){
            gCalHandler.findPeriodHClasses(inputH.getText());
        }
        if(checkboxAdvisory.isSelected()){
            gCalHandler.findAdvisories(inputAdvisory.getText());
        }
        gCalHandler.populatePeriodXClasses();
        errorLabel.setText("Classes populated.");
    }

    @FXML
    protected void onNukeButtonClick() throws IOException, GeneralSecurityException {
        if(errorLabel.getText().equals("Removing all events made by this app is permanent. Click Nuke Events again to proceed.")){
            clearErrorText();
            // remove events
            gCalHandler.findGCalSAEvents();
        }
        else{
            errorLabel.setText("Removing all events made by this app is permanent. Click Nuke Events again to proceed.");
        }
    }

    @FXML
    public void onRefreshButtonClick() throws IOException, GeneralSecurityException {
        clearErrorText();
        if(checkForErrors()){
            return;
        }
        // clear current GCalSA events
        gCalHandler.findGCalSAEvents();
        // add selected events
        onGenerateButtonClick();
    }

    @FXML
    public void onSaveSelectedButtonClick() throws IOException, GeneralSecurityException {
        clearErrorText();
        if(checkForErrors()){
            return;
        }
        User user = new User(false, false, false, false, false, false, false, false, false, "", "", "", "", "", "", "", "", "");
        clearErrorText();
        if(checkForErrors()){
            return; // if there are errors, do not proceed
        }
        if(checkboxA.isSelected()){
            user.setPeriodA(true);
            user.setNameA(inputA.getText());
        }
        if(checkboxB.isSelected()){
            user.setPeriodB(true);
            user.setNameB(inputB.getText());
        }
        if(checkboxC.isSelected()){
            user.setPeriodC(true);
            user.setNameC(inputC.getText());
        }
        if(checkboxD.isSelected()){
            user.setPeriodD(true);
            user.setNameD(inputD.getText());
        }
        if(checkboxE.isSelected()){
            user.setPeriodE(true);
            user.setNameE(inputE.getText());
        }
        if(checkboxF.isSelected()){
            user.setPeriodF(true);
            user.setNameF(inputF.getText());
        }
        if(checkboxG.isSelected()){
            user.setPeriodG(true);
            user.setNameG(inputG.getText());
        }
        if(checkboxH.isSelected()){
            user.setPeriodH(true);
            user.setNameH(inputH.getText());
        }
        if(checkboxAdvisory.isSelected()){
            user.setPeriodAdvisory(true);
            user.setNameAdvisory(inputAdvisory.getText());
        }
        user.setExisting(true);
        // should we save it if there is nothing selected
        Data.setUser(user);
        user.serialize();
        errorLabel.setText("Selections saved as a file.");
    }

    Runnable runnable = () -> {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                PopUp.display();
            }
        });
    };
    private Thread thread = new Thread(runnable);
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();



    public void initialize() throws IOException, GeneralSecurityException, ClassNotFoundException, FileNotFoundException {
        gCalHandler = new GCalHandler();
        gCalHandler.prepareService();
        // fill in user info here
        if(Data.getUser().isExisting())
        {
            Data.setUser(Data.getUser().deserialize());
            //User user = Data.getUser().deserialize();
            User user = Data.getUser();
            if(user == null){
                errorLabel.setText("An existing calendar does not exist.");
                return;
            }
            addDeserializedInfo(user);
            errorLabel.setText("Existing calendar loaded.");
        }
        executor.schedule(thread, 1, TimeUnit.MINUTES);
    }


    @FXML
    public void addDeserializedInfo(User user) throws IOException{
        if(user.isPeriodA()){
            checkboxA.setSelected(true);
            inputA.setText(user.getNameA());
        }
        else{
            checkboxA.setSelected(false);
            inputA.setText("");
        }

        if(user.isPeriodB()){
            checkboxB.setSelected(true);
            inputB.setText(user.getNameB());
        }
        else{
            checkboxB.setSelected(false);
            inputB.setText("");
        }

        if(user.isPeriodC()){
            checkboxC.setSelected(true);
            inputC.setText(user.getNameC());
        }
        else{
            checkboxC.setSelected(false);
            inputC.setText("");
        }

        if(user.isPeriodD()){
            checkboxD.setSelected(true);
            inputD.setText(user.getNameD());
        }
        else{
            checkboxD.setSelected(false);
            inputD.setText("");
        }

        if(user.isPeriodE()){
            checkboxE.setSelected(true);
            inputE.setText(user.getNameE());
        }
        else{
            checkboxE.setSelected(false);
            inputE.setText("");
        }

        if(user.isPeriodF()){
            checkboxF.setSelected(true);
            inputF.setText(user.getNameF());
        }
        else{
            checkboxF.setSelected(false);
            inputF.setText("");
        }

        if(user.isPeriodG()){
            checkboxG.setSelected(true);
            inputG.setText(user.getNameG());
        }
        else{
            checkboxG.setSelected(false);
            inputG.setText("");
        }

        if(user.isPeriodH()){
            checkboxH.setSelected(true);
            inputH.setText(user.getNameH());
        }
        else{
            checkboxH.setSelected(false);
            inputH.setText("");
        }

        if(user.isPeriodAdvisory()){
            checkboxAdvisory.setSelected(true);
            inputAdvisory.setText(user.getNameAdvisory());
        }
        else{
            checkboxAdvisory.setSelected(false);
            inputAdvisory.setText("");
        }

    }


    @FXML
    protected void onHelloButtonClick() throws IOException {
        //directionsText.setVisible(false);
        //directionsTitle.setVisible(false);
        //System.out.println(inputPeriod1.getCharacters());

        //welcomeText.setText("Welcome to JavaFX Application!");
        //directionsText.setText("Directions\n" + "This app adds your classes and advisory into your google calendar. Click the checkbox next to the classes you want to add and enter a name for the class. Either load an existing calendar profile (if the schedule has changed) or create a new calendar profile.");

    }

    @FXML
    public void checkA()  {
        clearErrorText();
        if(checkboxA.isSelected()){
            inputA.setEditable(true);
        }
        else{
            inputA.setText("");
            inputA.setEditable(false);
        }
    }
    @FXML
    public void checkB()  {
        clearErrorText();
        if(checkboxB.isSelected()){
            inputB.setEditable(true);
        }
        else{
            inputB.setText("");
            inputB.setEditable(false);
        }
    }
    @FXML
    public void checkC()  {
        clearErrorText();
        if(checkboxC.isSelected()){
            inputC.setEditable(true);
        }
        else{
            inputC.setText("");
            inputC.setEditable(false);
        }
    }
    @FXML
    public void checkD()  {
        clearErrorText();
        if(checkboxD.isSelected()){
            inputD.setEditable(true);
        }
        else{
            inputD.setText("");
            inputD.setEditable(false);
        }
    }
    @FXML
    public void checkE()  {
        clearErrorText();
        if(checkboxE.isSelected()){
            inputE.setEditable(true);
        }
        else{
            inputE.setText("");
            inputE.setEditable(false);
        }
    }
    @FXML
    public void checkF()  {
        clearErrorText();
        if(checkboxF.isSelected()){
            inputF.setEditable(true);
        }
        else{
            inputF.setText("");
            inputF.setEditable(false);
        }
    }
    @FXML
    public void checkG()  {
        clearErrorText();
        if(checkboxG.isSelected()){
            inputG.setEditable(true);
        }
        else{
            inputG.setText("");
            inputG.setEditable(false);
        }
    }
    @FXML
    public void checkH()  {
        clearErrorText();
        if(checkboxH.isSelected()){
            inputH.setEditable(true);
        }
        else{
            inputH.setText("");
            inputH.setEditable(false);
        }
    }
    @FXML
    public void checkAdvisory()  {
        clearErrorText();
        if(checkboxAdvisory.isSelected()){
            inputAdvisory.setEditable(true);
        }
        else{
            inputAdvisory.setText("");
            inputAdvisory.setEditable(false);
        }
    }


    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        AnchorPane scene2 = FXMLLoader.load(getClass().getResource("second-screen.fxml"));
        scene1.getChildren().removeAll();
        scene1.getChildren().setAll(scene2);
    }



    // everything in scene builder needs the @FXML
}