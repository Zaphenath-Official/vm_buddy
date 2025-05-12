package vm.buddy.basic_mode.services.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vm.buddy.basic_mode.services.functions.ISOImage;

import java.io.IOException;

public class ISOImageController {

    @FXML
    private RadioButton windows11_option;

    @FXML
    private Label windows11_label;

    @FXML
    private RadioButton kalilinux_option;

    @FXML
    private Label kalilinux_label;

    @FXML
    private RadioButton macos_option;

    @FXML
    private Label macos_label;

    @FXML
    private Button download_button;

    private final ISOImage isoImage = new ISOImage();

    @FXML
    public void initialize() {
        // Create a ToggleGroup and assign it to the radio buttons
        ToggleGroup virtualizerGroup = new ToggleGroup();
        windows11_option.setToggleGroup(virtualizerGroup);
        kalilinux_option.setToggleGroup(virtualizerGroup);
        macos_option.setToggleGroup(virtualizerGroup);

        // sets a default selection
        windows11_option.setSelected(true);
    }

    @FXML
    private void handleDownloadISO() {
        try {
            // Get the selected ISO image name
            String selectedISO = getSelectedISO();
            if (selectedISO == null) {
                System.out.println("No ISO image selected.");
                return;
            }

            System.out.println("Selected ISO Image: " + selectedISO);

            // Load the download/install box UI
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/basic_mode/widgets/features/ui/download_install_box.fxml"));
            Pane root = loader.load();

            // Get the controller for the download/install box
            DownloadInstallBoxController controller = loader.getController();

            // Set up the stage for the popup
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Download ISO");
            stage.setScene(new Scene(root));
            stage.show();

            // Perform the download in a separate thread
            new Thread(() -> {
                try {
                    isoImage.downloadISO(selectedISO, controller);
                } catch (Exception e) {
                    controller.setStatus("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSelectedISO() {
        if (windows11_option.isSelected()) {
            return windows11_label.getText();
        } else if (kalilinux_option.isSelected()) {
            return kalilinux_label.getText();
        } else if (macos_option.isSelected()) {
            return macos_label.getText();
        }
        return null; // No selection
    }
}
