package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JavaFX App
 */
public class App extends Application {

    private static String filepath = "demo\\src\\main\\java\\com\\example\\accounts.txt";
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        mainPage(stage);
    }

    public void mainPage(Stage stage){
        HBox mainButtons = new HBox();
        HBox exitButtonField = new HBox();
        VBox mainPage = new VBox();
        Label mainLabel = new Label("Sign up/Log in");
        Button signupBtn = new Button("Sign Up");
        Button loginBtn = new Button("Log In");
        Button exitBtn = new Button("Exit");
        mainLabel.setStyle("-fx-alignment: center; -fx-font-size: 30px; -fx-font-weight: bold;");
        signupBtn.setStyle("-fx-alignment: center; -fx-font-size: 24px; -fx-background-color: #CCCCCC; -fx-font-weight: bold;");
        loginBtn.setStyle("-fx-alignment: center; -fx-font-size: 24px; -fx-background-color: #CCCCCC; -fx-font-weight: bold;");
        exitBtn.setStyle("-fx-alignment: center; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #FF0000");
        mainButtons.setStyle("-fx-alignment: center; -fx-padding: 10px; -fx-spacing: 10px;");
        exitButtonField.setStyle("-fx-alignment: center; -fx-padding: 10px; -fx-spacing: 10px;");
        mainPage.setStyle("-fx-alignment: center; -fx-background-color: #FFFFFF;");
        mainButtons.getChildren().addAll(signupBtn, loginBtn);
        exitButtonField.getChildren().addAll(exitBtn);
        mainPage.getChildren().addAll(mainLabel, mainButtons, exitButtonField);
        exitBtn.setOnAction(e -> {
            Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
            exitAlert.setTitle("Exit?");
            exitAlert.setHeaderText("");
            exitAlert.setContentText("Are you sure you want to exit?");
            exitAlert.setResizable(false);
            Optional<ButtonType> action = exitAlert.showAndWait();
            if(action.isPresent() && action.get() == ButtonType.OK){
                System.exit(1);
            }
        });
        signupBtn.setOnAction(e -> {
            signupPage(stage);
        });
        loginBtn.setOnAction(e -> {
            loginPage(stage);
        });

        scene = new Scene(mainPage, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void signupPage(Stage stage){
        VBox signupPage = new VBox();
        HBox buttons = new HBox();
        Label title = new Label("Sign Up");
        Label lUsername = new Label("Username: ");
        Label lName = new Label("Name: ");
        Label lSurname = new Label("Surname: ");
        Label lPassword = new Label("Password: ");
        TextField tUsername = new TextField();
        TextField tName = new TextField();
        TextField tSurname = new TextField();
        PasswordField tPassword = new PasswordField();
        GridPane grid = new GridPane();
        Button signupBtn = new Button("Sign Up");
        Button cancelBtn = new Button("Cancel");

        
        signupPage.setStyle("-fx-alignment: center;");
        signupBtn.setStyle("-fx-background-color: #4CAF50; -fx-font-size: 14px;");
        cancelBtn.setStyle("-fx-background-color: #f44336; -fx-font-size: 14px;");
        buttons.setStyle("-fx-alignment: center; -fx-font-size: 14px; -fx-padding: 10px; -fx-spacing: 10px;");
        grid.setStyle("-fx-alignment: center; -fx-padding: 10px;");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-alignment: center;");

        tUsername.setPromptText("Username");
        tName.setPromptText("Name");
        tSurname.setPromptText("Surname");
        tPassword.setPromptText("Password");
        
        grid.add(lUsername, 0, 0); grid.add(tUsername, 1, 0); grid.add(lName, 0, 1); grid.add(tName, 1, 1);
        grid.add(lSurname, 0, 2); grid.add(tSurname, 1, 2); grid.add(lPassword, 0, 3); grid.add(tPassword, 1, 3);


        buttons.getChildren().addAll(signupBtn, cancelBtn);
        signupPage.getChildren().addAll(title, grid, buttons);

        signupBtn.setOnAction(e -> {
            if(!tUsername.getText().isEmpty() && !tName.getText().isEmpty() && !tSurname.getText().isEmpty() && !tPassword.getText().isEmpty()){
                signup(stage, tUsername.getText(), tName.getText(), tSurname.getText(), tPassword.getText());
                Alert successfulSignup = new Alert(Alert.AlertType.INFORMATION);
                successfulSignup.setTitle("Successful!");
                successfulSignup.setHeaderText("");
                successfulSignup.setContentText("Successfully signed up!");
                successfulSignup.showAndWait();
                mainPage(stage);
            }
        });
        cancelBtn.setOnAction(e -> {
            cancel(stage);
        });

        

        scene = new Scene(signupPage, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    
    public void loginPage(Stage stage){
        VBox loginPage = new VBox();
        Label title = new Label("Log In");
        Label lUsername = new Label("Username: ");
        Label lPassword = new Label("Password: ");
        TextField tName = new TextField();
        PasswordField tPassword = new PasswordField();
        GridPane grid = new GridPane();
        HBox buttons = new HBox();
        Button okBtn = new Button("Ok");
        Button cancelBtn = new Button("Cancel");

        loginPage.setStyle("-fx-alignment: center;");
        okBtn.setStyle("-fx-background-color: #4CAF50; -fx-font-size: 14px;");
        cancelBtn.setStyle("-fx-background-color: #f44336; -fx-font-size: 14px;");
        buttons.setStyle("-fx-alignment: center; -fx-font-size: 14px; -fx-padding: 10px; -fx-spacing: 10px;");
        grid.setStyle("-fx-alignment: center; -fx-padding: 10px;");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-alignment: center;");
        tName.setPromptText("Name");
        tPassword.setPromptText("Password");

        buttons.getChildren().addAll(okBtn, cancelBtn);
        grid.add(lUsername, 0, 0); grid.add(tName, 1, 0); grid.add(lPassword, 0, 1); grid.add(tPassword, 1, 1);
        loginPage.getChildren().addAll(title, grid, buttons);
        okBtn.setOnAction(e ->{
            login(tName.getText(), tPassword.getText());
        });
        cancelBtn.setOnAction(e ->{
            cancel(stage);
        });

        scene = new Scene(loginPage, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    // FILE ACTIONS
    public void signup(Stage stage, String username, String name, String surname, String password){
        String textToAppend = "username: \"" + username + "\" password: \"" + password + "\" name: \"" + name + "\" surname: \"" + surname + "\",\n";
        try{
            FileWriter writer = new FileWriter(filepath, true); // true = append mode
            writer.write(textToAppend);
            writer.close();
        } catch(IOException e){
            System.out.println("Error occured while writing to file. TERMINATING...");
            System.exit(1);
        }
    }
    public void login(String name, String password){
        String accounts = "";
        try{
            File file = new File(filepath);
            Scanner reader = new Scanner(file);
                    while (reader.hasNextLine()) {
                        accounts += reader.nextLine() + "\n";
                    }
                    reader.close();
        } catch(FileNotFoundException e){
            System.out.println("FILE NOT FOUND. TERMINATING...");
            System.exit(1);
        }
        Pattern userAndPass = Pattern.compile("username: \"([A-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+)\" password: \"([A-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+)\"");
        Matcher matcher = userAndPass.matcher(accounts);
        boolean loggedIn = false;
        while(matcher.find()){
            if(matcher.group(1).equals(name) && matcher.group(2).equals(password)){
                Alert logged = new Alert(Alert.AlertType.INFORMATION);
                logged.setTitle("You logged in bro.");
                logged.setContentText("Welcome, " + name);
                logged.setResizable(false);
                logged.showAndWait();
                loggedIn = true;
                break;
            }else{
                System.out.println("Not matching: " + matcher.group(1) + " : " + matcher.group(2));
            }
        }
        if(!loggedIn){
            Alert wrongInfo = new Alert(Alert.AlertType.ERROR);
            wrongInfo.setTitle("Wrong username/password");
            wrongInfo.setHeaderText("");
            wrongInfo.setContentText("You snitch you ain't gettin in that easily...");
            wrongInfo.setResizable(false);
            wrongInfo.showAndWait();
        }
    }


    // Cancel popup
    public void cancel(Stage stage){
        Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
        cancelAlert.setTitle("Cancel?");
        cancelAlert.setHeaderText("");
        cancelAlert.setContentText("Are you sure you want to cancel?");
        cancelAlert.setResizable(false);
        Optional<ButtonType> result = cancelAlert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            mainPage(stage);
        }
    }


    public static void main(String[] args) {
        launch();
    }
}