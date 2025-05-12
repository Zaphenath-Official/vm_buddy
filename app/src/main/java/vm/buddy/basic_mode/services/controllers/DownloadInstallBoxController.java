package vm.buddy.basic_mode.services.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class DownloadInstallBoxController {

    @FXML
    private Label title_label;

    @FXML
    private Label status_label;

    @FXML
    private ProgressBar progress_bar;

    public void setTitle(String title) {
        title_label.setText(title);
    }

    /**
     * Updates the status label text.
     *
     * @param status The new status text.
     */
    public void setStatus(String status) {
        Platform.runLater(() -> status_label.setText(status));
    }

    /**
     * Updates the progress bar value.
     *
     * @param progress The new progress value (0.0 to 1.0).
     */
    public void setProgress(double progress) {
        Platform.runLater(() -> progress_bar.setProgress(progress));
    }
}