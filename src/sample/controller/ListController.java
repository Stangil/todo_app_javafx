package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import sample.Database.DatabaseHandler;
import sample.model.Task;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class ListController {



    @FXML
    private ImageView listRefreshButton;

    @FXML
    private ListView<Task> listTask;

    @FXML
    private TextField listTaskField;

    @FXML
    private TextField listDescriptionField;

    @FXML
    private Button listSaveTaskButton;

    private ObservableList<Task> tasks;
    private ObservableList<Task> refreshedTasks;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {
        tasks = FXCollections.observableArrayList();

        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(AddItemController.userId);

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDateCreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));
            tasks.addAll(task);

           // System.out.println("User TAsks: " + resultSet.getString("task"));
        }

        listTask.setItems(tasks);
        listTask.setCellFactory(CellController -> new CellController());
        listRefreshButton.setOnMouseClicked(event -> {
            try {
                refreshList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        listSaveTaskButton.setOnAction(event -> addNewTask());

    }

    public void refreshList()throws Exception{

        refreshedTasks = FXCollections.observableArrayList();
        databaseHandler = new DatabaseHandler();

        ResultSet resultSet = databaseHandler.getTasksByUser(AddItemController.userId);

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDateCreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));
            refreshedTasks.addAll(task);

            listTask.setItems(refreshedTasks);
            listTask.setCellFactory(CellController -> new CellController());

            // System.out.println("User TAsks: " + resultSet.getString("task"));
        }
    }
    public void addNewTask(){
            if(!listTaskField.getText().equals("")&&!listDescriptionField.getText().equals("")){
                Task myNewTask = new Task();

                Calendar calendar = Calendar.getInstance();
                java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

                myNewTask.setUserId(AddItemController.userId);
                myNewTask.setTask(listTaskField.getText().trim());
                myNewTask.setDescription(listDescriptionField.getText().trim());
                myNewTask.setDateCreated(timestamp);
                databaseHandler.insertTask(myNewTask);
                listTaskField.setText("");
                listDescriptionField.setText("");

                try {
                    initialize();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }
}


