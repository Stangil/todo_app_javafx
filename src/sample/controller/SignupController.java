package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Database.DatabaseHandler;
import sample.model.User;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpUsername;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private TextField signUpLocation;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {

            createUser();
        });
    }

    private void createUser(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = signUpFirstName.getText();
        String lastName = signUpLastName.getText();
        String userName = signUpUsername.getText();
        String password = signUpPassword.getText();
        String location = signUpLocation.getText();
        String gender;
        if (signUpCheckBoxFemale.isSelected()){
            gender = "Female";
        } else {
            gender = "Male";
        }

        User user = new User(firstName,lastName,userName,password,location,gender);

            databaseHandler.signUpUser(user);
    }
}
