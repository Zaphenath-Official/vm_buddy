package vm.buddy.basic_mode.services.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class InstallerController {

    @FXML
    private ImageView installer_dir_iconBtn;

    @FXML
    private TextField installer_dir_textfield;

    @FXML
    private Pane installer_outter_pane; // The parent pane for the ripple effect

    @FXML
    public void initialize() {
        // Add a click event handler to the directory icon button
        installer_dir_iconBtn.setOnMouseClicked( _ -> handleDirectoryChooser());
    }

    
    /**
     * Opens a directory chooser when the directory icon button is clicked.
     */
    private void handleDirectoryChooser() {
        // Create a DirectoryChooser
        DirectoryChooser directoryChooser = new DirectoryChooser();

        // Set the initial directory to the user's home directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Show the directory chooser
        Stage stage = (Stage) installer_outter_pane.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);

        // If a directory is selected, update the text field
        if (selectedDirectory != null) {
            installer_dir_textfield.setText(selectedDirectory.getAbsolutePath());
        }
    }
}