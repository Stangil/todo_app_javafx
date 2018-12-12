package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.model.Task;

public class CellController extends ListCell<Task> {

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView iconImageView;

    @FXML
    private Label taskLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label dateCreatedLabel;

    @FXML
    private ImageView deleteButton;

    private FXMLLoader fxmlLoader;

    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Task myTask, boolean empty) {
        super.updateItem(myTask, empty);

        if(empty||myTask == null){
            setText(null);
            setGraphic(null);
        }else{
            if(fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/sample/view/cell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            taskLabel.setText(myTask.getTask());
            dateCreatedLabel.setText(myTask.getDateCreated().toString());
            descriptionLabel.setText(myTask.getDescription());

            setText(null);
            setGraphic(rootAnchorPane);
        }
    }
}
