package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.animations.Shaker;

public class AddItemController {

    public static int userId;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView addButton;


    @FXML
    private Label noTaskLabel;


    @FXML
    void initialize() {

        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Added clicked");
            addButton.setDisable(true);


            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));


//                AddItemFormController addItemFormController = new AddItemFormController();
//                addItemFormController.setUserId(getUserId());

                AddItemController.userId = getUserId();

                FadeTransition rootTransition = new FadeTransition(Duration.millis(2000), formPane);
                rootTransition.setFromValue(0f);
                rootTransition.setToValue(1f);
                rootTransition.setCycleCount(1);
                rootTransition.setAutoReverse(false);
                rootTransition.play();

                rootAnchorPane.getChildren().setAll(formPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setUserId(int userId) {
        this.userId = userId;
        //System.out.println("AddItemController setUserId: "+ this.userId);
    }
    public int getUserId(){
        //System.out.println("AddItemController getUserId: "+this.userId);
        return userId;

    }
}
