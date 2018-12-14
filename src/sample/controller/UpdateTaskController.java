package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateTaskController {

    @FXML
    private TextField updateTaskField;

    @FXML
    private TextField updateDescriptionField;

    @FXML
    public Button updateTaskButton;


    @FXML
    void initialize() {



    }

    public void setTaskField(String task) {
        this.updateTaskField.setText(task);
    }

    public String getTask() {
        return this.updateTaskField.getText().trim();
    }

    public void setUpdateDescriptionField(String description) {
        this.updateDescriptionField.setText(description);
    }

    public String getDescription() {
        return this.updateDescriptionField.getText().trim();
    }

//    public void refreshList() throws SQLException {
//
//        System.out.println("Calling refresh list");
//
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/sample/view/list.fxml"));
//
//        try {
//            loader.load();
//
//            ListController listController = loader.getController();
//            listController.refreshList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
