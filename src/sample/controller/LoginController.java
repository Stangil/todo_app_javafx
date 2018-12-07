package sample.controller;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.model.User;

public class LoginController {

    private int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Button loginSignupButton;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();


        loginButton.setOnAction(event -> {

            String loginText = loginUsername.getText().trim();
            String loginPwd = loginPassword.getText().trim();

            User user = new User();
            user.setUserName(loginText);
            user.setPassword(loginPwd);

            ResultSet userRow = databaseHandler.getUser(user);

            int counter = 0;

            try {
                while (userRow.next()){
                    counter++;
                    String name = userRow.getString("firstname");
                    userId = userRow.getInt("userid");

                    System.out.println("Look its " + name + " with userId: " +userId+ "!");
                }
                if(counter == 1){
                    System.out.println("Login successful");
                    showAddItemScreen();
                }else{
                    Shaker shaker = new Shaker(loginUsername);
                    Shaker shaker1 = new Shaker(loginPassword);
                    shaker1.shake();
                    shaker.shake();
                }
            } catch (SQLException e){
               e.printStackTrace();
            }


        });


        loginSignupButton.setOnAction(event -> {
                //Take users to signup screen
            loginSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();


        });
    }

    private void showAddItemScreen(){
        //Take users to Additem screen
        loginSignupButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/additem.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        AddItemController addItemController = loader.getController();
        addItemController.setUserId(userId);

        stage.showAndWait();
    }
}

