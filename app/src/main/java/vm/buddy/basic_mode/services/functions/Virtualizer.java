package vm.buddy.basic_mode.services.functions;

import javafx.concurrent.Task;
import vm.buddy.basic_mode.services.controllers.DownloadInstallBoxController;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class Virtualizer {

    /**
     * Handles the entire process of downloading and installing a virtualizer using a background thread.
     *
     * @param virtualizerName The name of the virtualizer.
     * @param controller      The controller for the download/install box UI.
     */
    public void downloadAndInstall(String virtualizerName, DownloadInstallBoxController controller) {
        // Create a Task to run the download and install process on a background thread
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try {
                    // Update the UI for download preparation
                    controller.setStatus("Preparing to download " + virtualizerName + "...");
                    controller.setProgress(0.1);

                    // Introduce a delay of 10 seconds
                    Thread.sleep(10000);

                    // Call the downloadVirtualizer function
                    controller.setStatus("Downloading " + virtualizerName + "...");
                    controller.setProgress(0.2);
                    downloadVirtualizer(virtualizerName, controller);

                    // Call the installVirtualizer function
                    controller.setStatus("Installing " + virtualizerName + "...");
                    controller.setProgress(0.6);
                    
                    String downloadPath = getDefaultDownloadDirectory() + virtualizerName.toLowerCase().replace(" ", "_") + "-installer.exe";
                    installVirtualizer(downloadPath);

                    // Update the UI for completion
                    controller.setStatus("Installation completed!");
                    controller.setProgress(1.0);
                } catch (Exception e) {
                    controller.setStatus("Error: " + e.getMessage());
                    throw e;
                }
                return null;
            }
        };

        // Run the task on a background thread
        new Thread(task).start();
    }

    /**
     * Downloads a virtualizer installer from the given URL.
     *
     * @param virtualizerName The name of the virtualizer.
     * @param controller      The controller for the download/install box UI.
     * @throws IOException If an error occurs during the download.
     */
    public void downloadVirtualizer(String virtualizerName, DownloadInstallBoxController controller) throws IOException {
        // Get the download URL
        String downloadUrl = getDownloadUrl(virtualizerName);

        // Define the download path
        String downloadPath = getDefaultDownloadDirectory() + virtualizerName.toLowerCase().replace(" ", "_") + "-installer.exe";

        // Call the downloadFile function
        downloadFile(downloadUrl, downloadPath, controller);
    }

    /**
     * Downloads a file from the given URL.
     *
     * @param downloadUrl The URL of the file to download.
     * @param savePath    The local path where the file will be saved.
     * @param controller  The controller for the download/install box UI.
     * @throws IOException If an error occurs during the download.
     */
    private void downloadFile(String downloadUrl, String savePath, DownloadInstallBoxController controller) throws IOException {
        System.out.println("Downloading from: " + downloadUrl);
        try (BufferedInputStream in = new BufferedInputStream(URI.create(downloadUrl).toURL().openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
                
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long totalBytesRead = 0;
            long fileSize = 1024 * 1024 * 100; // Example file size (100 MB)

            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                totalBytesRead += bytesRead;

                // Update progress
                double progress = (double) totalBytesRead / fileSize;
                controller.setProgress(Math.min(progress, 1.0));
            }
        }
        System.out.println("Download completed. File saved to: " + savePath);
    }

    /**
     * Installs the virtualizer using the downloaded installer.
     *
     * @param installerPath The local path to the installer file.
     * @throws IOException          If an error occurs during the installation.
     * @throws InterruptedException If the installation process is interrupted.
     */
    public void installVirtualizer(String installerPath) throws IOException, InterruptedException {
        System.out.println("Installing from: " + installerPath);
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(installerPath); // Assumes the installer is executable
        processBuilder.inheritIO(); // Redirects output to the console
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Installation completed successfully.");
        } else {
            System.out.println("Installation failed with exit code: " + exitCode);
        }
    }

    /**
     * Uninstalls the virtualizer using the uninstaller.
     * 
     * @param uninstallerPath The local path to the uninstaller file or command.
     * @throws IOException          If an error occurs during the uninstallation.
     * @throws InterruptedException If the uninstallation process is interrupted.
     */
    public void uninstallVirtualizer(String uninstallerPath) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(uninstallerPath); // Assumes the uninstaller is executable
        processBuilder.inheritIO(); // Redirects output to the console
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        if (exitCode == 0) {
        } else {
        }
    }

    /**
     * Maps virtualizer names to their respective download URLs.
     *
     * @param virtualizerName The name of the virtualizer.
     * @return The download URL for the virtualizer.
     */
    private String getDownloadUrl(String virtualizerName) {
        switch (virtualizerName) {
            case "VirtualBox 22.26.01":
                return "https://example.com/virtualbox-installer.exe";
            case "VMWare 22.26.01":
                return "https://example.com/vmware-installer.exe";
            case "QEMU 22.26.01":
                return "https://example.com/qemu-installer.exe";
            default:
                throw new IllegalArgumentException("Unknown virtualizer: " + virtualizerName);
        }
    }

    /**
     * Gets the default system download directory.
     *
     * @return The default download directory path.
     */
    private String getDefaultDownloadDirectory() {
        return System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
    }
}
