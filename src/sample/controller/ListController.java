package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.model.Task;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ListController {


    @FXML
    private ListView<Task> listTask;

    @FXML
    private TextField listTaskField;

    @FXML
    private TextField listDescriptionField;

    @FXML
    private Button listSaveTaskButton;

    private ObservableList<Task> tasks;

    @FXML
    void initialize() {
        Task myTask = new Task();
        myTask.setTask("Eat lunch");
        myTask.setDescription("Go to Wendy's");
        myTask.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));

        Task myTask2 = new Task();
        myTask2.setTask("Eat dinner");
        myTask2.setDescription("Go to Burger's");
        myTask2.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));


        tasks = FXCollections.observableArrayList();
        tasks.addAll(myTask, myTask2);

        listTask.setItems(tasks);
        listTask.setCellFactory(CellController -> new CellController());


    }
}


