package vm.buddy.basicmode.features.local_vm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class HomePageController {
    @FXML private Pane progressBox;
    @FXML private ProgressBar progressBar;
    @FXML private Button proceedButton;
    @FXML private Button cancelButton;
    @FXML private Label progressPercentLabel;
    @FXML private Label progressStatusLabel;
    public AnchorPane home_screen;
    public AnchorPane virtual_machine_images_screen;
    public AnchorPane logs_screen;
    public AnchorPane snapshots_screen;
    public AnchorPane template_screen;
    public Pane cloudVM_screen;
    public AnchorPane addNewVMController;
    @FXML private HBox dashboardCloudHBox;
    @FXML private HBox dashboardImageRepoHBox;
    @FXML private HBox dashboardTemplateHBox;
    @FXML private HBox dashboardSnapshotHBox;
    @FXML private HBox dashboardLogHBox;
    @FXML
    public HBox dashboard1HomeHBox;
    @FXML private HBox dashboard1LogoutHBox;
    @FXML private HBox dashboard1UserHBox;
    @FXML private Button addNewVMButton;

    @FXML
    public void initialize() {
        setAllScreensInvisible();
        if (home_screen != null) home_screen.setVisible(true);
        resetDashboardColors();
        if (dashboard1HomeHBox != null) dashboard1HomeHBox.setStyle("-fx-background-color: orange; -fx-background-radius: 100; -fx-border-color: grey; -fx-border-radius: 100;");
        if (addNewVMButton != null) {
            addNewVMButton.setOnAction( _ -> onAddNewVMButtonClicked());
        }
    }

    public void showProgressBox(String statusText) {
        progressBox.setVisible(true);
        progressBar.setProgress(0);
        progressPercentLabel.setText("0%");
        progressStatusLabel.setText(statusText);
        proceedButton.setDisable(true);
        cancelButton.setDisable(false);
    }

    public void hideProgressBox() {
        progressBox.setVisible(false);
    }

    public void updateProgress(double progress, String percentText) {
        progressBar.setProgress(progress);
        progressPercentLabel.setText(percentText);
    }

    public void setProceedButtonEnabled(boolean enabled) {
        proceedButton.setDisable(!enabled);
    }

    public void setCancelButtonEnabled(boolean enabled) {
        cancelButton.setDisable(!enabled);
    }

    public void setCancelButtonHandler(javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        cancelButton.setOnAction(handler);
    }

    @FXML
    private void onCancelButtonClicked() {
        // This will be set by AddNewVMController via a callback
    }

    public enum Screen {
        HOME, IMAGE_REPO, LOGS, SNAPSHOTS, TEMPLATES, CLOUD_VM, ADD_NEW_VM
    }

    public void showScreen(Screen screen) {
        setAllScreensInvisible();
        resetDashboardColors();
        switch (screen) {
            case HOME:
                if (home_screen != null) home_screen.setVisible(true);
                if (dashboard1HomeHBox != null) dashboard1HomeHBox.setStyle("-fx-background-color: orange; -fx-background-radius: 100; -fx-border-color: grey; -fx-border-radius: 100;");
                break;
            case IMAGE_REPO:
                if (virtual_machine_images_screen != null) virtual_machine_images_screen.setVisible(true);
                if (dashboardImageRepoHBox != null) dashboardImageRepoHBox.setStyle("-fx-background-color: orange; -fx-background-radius: 100; -fx-border-color: grey; -fx-border-radius: 100;");
                break;
            case LOGS:
                if (logs_screen != null) logs_screen.setVisible(true);
                if (dashboardLogHBox != null) dashboardLogHBox.setStyle("-fx-background-color: orange; -fx-background-radius: 100; -fx-border-color: grey; -fx-border-radius: 100;");
                break;
            case SNAPSHOTS:
                if (snapshots_screen != null) snapshots_screen.setVisible(true);
                if (dashboardSnapshotHBox != null) dashboardSnapshotHBox.setStyle("-fx-background-color: orange; -fx-background-radius: 100; -fx-border-color: grey; -fx-border-radius: 100;");
                break;
            case TEMPLATES:
                if (template_screen != null) template_screen.setVisible(true);
                if (dashboardTemplateHBox != null) dashboardTemplateHBox.setStyle("-fx-background-color: orange; -fx-background-radius: 100; -fx-border-color: grey; -fx-border-radius: 100;");
                break;
            case CLOUD_VM:
                if (cloudVM_screen != null) cloudVM_screen.setVisible(true);
                if (dashboardCloudHBox != null) dashboardCloudHBox.setStyle("-fx-background-color: orange; -fx-background-radius: 100; -fx-border-color: grey; -fx-border-radius: 100;");
                break;
            case ADD_NEW_VM:
                if (addNewVMController != null) addNewVMController.setVisible(true);
                break;
        }
    }

    @FXML
    private void onDashboardCloudClicked(MouseEvent event) {
        showScreen(Screen.CLOUD_VM);
    }

    @FXML
    private void onDashboardImageRepoClicked(MouseEvent event) {
        showScreen(Screen.IMAGE_REPO);
    }

    @FXML
    private void onDashboardTemplateClicked(MouseEvent event) {
        showScreen(Screen.TEMPLATES);
    }

    @FXML
    private void onDashboardSnapshotClicked(MouseEvent event) {
        showScreen(Screen.SNAPSHOTS);
    }

    @FXML
    private void onDashboardLogClicked(MouseEvent event) {
        showScreen(Screen.LOGS);
    }

    @FXML
    private void onDashboard1HomeClicked(MouseEvent event) {
        showScreen(Screen.HOME);
    }

    @FXML
    private void onAddNewVMButtonClicked() {
        showScreen(Screen.ADD_NEW_VM);
    }

    @FXML
    private void onDashboard1LogoutClicked(MouseEvent event) {
        resetDashboardColors();
        if (dashboard1LogoutHBox != null) dashboard1LogoutHBox.setStyle("-fx-background-color: orange; -fx-background-radius: 100; -fx-border-color: grey; -fx-border-radius: 100;");
    }

    @FXML
    private void onDashboard1UserClicked(MouseEvent event) {
        resetDashboardColors();
        if (dashboard1UserHBox != null) dashboard1UserHBox.setStyle("-fx-background-color: orange; -fx-background-radius: 100; -fx-border-color: grey; -fx-border-radius: 100;");
    }

    // Make these methods public so they can be called from initialize and showScreen
    public void setAllScreensInvisible() {
        if (home_screen != null) home_screen.setVisible(false);
        if (virtual_machine_images_screen != null) virtual_machine_images_screen.setVisible(false);
        if (logs_screen != null) logs_screen.setVisible(false);
        if (snapshots_screen != null) snapshots_screen.setVisible(false);
        if (template_screen != null) template_screen.setVisible(false);
        if (cloudVM_screen != null) cloudVM_screen.setVisible(false);
        if (addNewVMController != null) addNewVMController.setVisible(false);
    }

    public void resetDashboardColors() {
        for (HBox hbox : new HBox[]{dashboardCloudHBox, dashboardImageRepoHBox, dashboardTemplateHBox, dashboardSnapshotHBox, dashboardLogHBox, dashboard1HomeHBox, dashboard1LogoutHBox, dashboard1UserHBox}) {
            if (hbox != null) hbox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.18); -fx-background-radius: 100; -fx-border-color: grey; -fx-border-radius: 100;");
        }
    }
}
