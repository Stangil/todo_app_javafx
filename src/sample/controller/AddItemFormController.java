package sample.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import sample.Database.DatabaseHandler;
import sample.model.Task;

public class AddItemFormController {


    private int userId;

    private DatabaseHandler databaseHandler;

    @FXML
    private TextField taskField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button saveTaskButton;

    @FXML
    private Label successLabel;

    @FXML
    private Button todosButton;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();
        saveTaskButton.setOnAction(event -> {
            Task task = new Task();

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskText = taskField.getText().trim();
            String taskDescription = descriptionField.getText().trim();

            if(!taskText.equals("") && !taskDescription.equals("")){

                System.out.println("User id: " + AddItemController.userId);
                task.setUserId(AddItemController.userId);
                task.setDateCreated(timestamp);
                task.setDescription(taskDescription);
                task.setTask(taskText);

                databaseHandler.insertTask(task);

                successLabel.setVisible(true);

                FadeTransition ft = new FadeTransition(Duration.millis(2000), successLabel);
                ft.setFromValue(1.0);
                ft.setToValue(0);
                ft.setCycleCount(1);
                ft.setAutoReverse(true);

                ft.play();

                todosButton.setVisible(true);
                int taskNumber = 3;
                todosButton.setText("My Todo's: " + taskNumber);

                taskField.setText("");
                descriptionField.setText("");

                todosButton.setOnAction(event1 -> {
                    //send users to list screen
                    System.out.println("Going to list screen");
                });
            }else {
                System.out.println("Fields are blank!");
            }
        });
    }
}
