package vm.buddy.basicmode.features.local_vm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vm.buddy.basicmode.features.virtualizer.api.VirtualboxDownload;
import vm.buddy.basicmode.features.iso_image.api.LinuxDownload;
import javafx.application.Platform;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

public class AddNewVMController {
    @FXML
    private Button downloadVirtualBoxBtn;
    @FXML
    private Button downloadLinuxIsoBtn;

    private HomePageController homePageController;
    private AtomicBoolean downloadCancelFlag = new AtomicBoolean(false);

    public void setHomePageController(HomePageController controller) {
        this.homePageController = controller;
    }

    @FXML
    private void onDownloadVirtualBox() {
        String url = "https://download.virtualbox.org/virtualbox/7.0.18/VirtualBox-7.0.18-162988-Win.exe";
        String fileName = "VirtualBox-7.0.18-162988-Win.exe";
        downloadCancelFlag.set(false);
        if (homePageController != null) {
            Platform.runLater(() -> homePageController.showProgressBox("Downloading VirtualBox..."));
        }
        BiConsumer<Double, String> progressCallback = (progress, percentText) -> {
            if (homePageController != null) {
                homePageController.updateProgress(progress, percentText);
            }
        };
        Runnable onComplete = () -> {
            if (homePageController != null) {
                homePageController.setProceedButtonEnabled(true);
                homePageController.setCancelButtonEnabled(false);
            }
        };
        // Wire up cancel button
        if (homePageController != null) {
            homePageController.setCancelButtonHandler(_ -> {
                downloadCancelFlag.set(true);
                Platform.runLater(() -> homePageController.hideProgressBox());
            });
        }
        VirtualboxDownload.downloadVirtualBoxAsync(url, fileName, progressCallback, downloadCancelFlag, onComplete);
    }

    @FXML
    private void onDownloadLinuxIso() {
        String url = "https://sourceforge.net/projects/linux-lite/files/latest/download";
        String fileName = "linux-lite-latest-version-64bit.iso";
        downloadCancelFlag.set(false);
        if (homePageController != null) {
            Platform.runLater(() -> homePageController.showProgressBox("Downloading Linux ISO..."));
        }
        BiConsumer<Double, String> progressCallback = (progress, percentText) -> {
            if (homePageController != null) {
                homePageController.updateProgress(progress, percentText);
            }
        };
        Runnable onComplete = () -> {
            if (homePageController != null) {
                homePageController.setProceedButtonEnabled(true);
                homePageController.setCancelButtonEnabled(false);
            }
        };
        if (homePageController != null) {
            homePageController.setCancelButtonHandler(_ -> {
                downloadCancelFlag.set(true);
                Platform.runLater(() -> homePageController.hideProgressBox());
            });
        }
        LinuxDownload.downloadLinuxISOAsync(url, fileName, progressCallback, downloadCancelFlag, onComplete);
    }
}
