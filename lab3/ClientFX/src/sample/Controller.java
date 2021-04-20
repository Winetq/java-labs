package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Controller {
    @FXML private Label statusLabel;
    @FXML private ProgressBar progressBar;
    @FXML private Button loading;
    @FXML private TextArea name;
    private ExecutorService executor;

    @FXML public void initialize() {
        name.setFont(Font.font("Verdana", 12));
    }

    public void onButtonClicked(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == loading) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG files", "*.jpg")); // tylko jpg

            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                Task sendFileTask = new SendFileTask(selectedFile, name.getText()); // klasa zadania
                statusLabel.textProperty().bind(sendFileTask.messageProperty()); // bindowanie labela
                progressBar.progressProperty().bind(sendFileTask.progressProperty()); // bindowanie progressbara
                executor = Executors.newSingleThreadExecutor(); // otwarcie executora
                executor.submit(sendFileTask); // uruchomienie zadania w tle (call())
                executor.shutdown(); // zamkniecie executora
            }
        }
    }
}
