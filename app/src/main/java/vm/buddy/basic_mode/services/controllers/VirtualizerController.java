package vm.buddy.basic_mode.services.controllers;

import javafx.event.ActionEvent;
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
import vm.buddy.basic_mode.services.functions.Virtualizer;

import java.io.IOException;

public class VirtualizerController {

    @FXML
    private RadioButton virtualbox_option;

    @FXML
    private Label virtualbox_label;

    @FXML
    private RadioButton vmware_option;

    @FXML
    private Label vmware_label;

    @FXML
    private RadioButton qemu_option;

    @FXML
    private Label qemu_label;

    @FXML
    private Button download_button;

    private final Virtualizer virtualizer = new Virtualizer();

    @FXML
    public void initialize() {
        // Create a ToggleGroup and assign it to the radio buttons
        ToggleGroup virtualizerGroup = new ToggleGroup();
        virtualbox_option.setToggleGroup(virtualizerGroup);
        vmware_option.setToggleGroup(virtualizerGroup);
        qemu_option.setToggleGroup(virtualizerGroup);

        // sets a default selection
        virtualbox_option.setSelected(true);
    }

    @FXML
    public void handleDownloadAndInstall(ActionEvent event) {
        try {
            // Get the selected virtualizer name
            String selectedVirtualizer = getSelectedVirtualizer();
            if (selectedVirtualizer == null) {
                System.out.println("No virtualizer selected.");
                return;
            }

            System.out.println("Selected Virtualizer: " + selectedVirtualizer);

            // Load the download/install box UI
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/basic_mode/widgets/features/ui/download_install_box.fxml"));
            Pane root = loader.load();

            // Get the controller for the download/install box
            DownloadInstallBoxController controller = loader.getController();

            // Set up the stage for the popup
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Download and Install Virtualizer");
            stage.setScene(new Scene(root));
            stage.show();

            // Calls the downloadAndInstall function in the Virtualizer class
            new Thread(() -> {
                try {
                    virtualizer.downloadAndInstall(selectedVirtualizer, controller);
                } catch (Exception e) {
                    controller.setStatus("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSelectedVirtualizer() {
        if (virtualbox_option.isSelected()) {
            return virtualbox_label.getText();
        } else if (vmware_option.isSelected()) {
            return vmware_label.getText();
        } else if (qemu_option.isSelected()) {
            return qemu_label.getText();
        }
        return null; // No selection
    }
}
