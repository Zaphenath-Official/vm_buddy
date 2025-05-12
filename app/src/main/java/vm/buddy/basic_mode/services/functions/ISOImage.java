package vm.buddy.basic_mode.services.functions;

import vm.buddy.basic_mode.services.controllers.DownloadInstallBoxController;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

import javafx.concurrent.Task;

public class ISOImage {

    /**
     * Handles the process of downloading an ISO image.
     *
     * @param isoName    The name of the ISO image.
     * @param controller The controller for the download/install box UI.
     * @throws IOException If an error occurs during the download.
     */
    public void downloadISO(String isoName, DownloadInstallBoxController controller) throws IOException {
        //create a Task to run the download process on a background thread
        Task<Void> task = new Task<>() {
            @Override
            protected Void call () throws Exception{
                try{
                                // Get the download URL
                    String downloadUrl = getDownloadUrl(isoName);

                    // Define the download path
                    String downloadPath = getDefaultDownloadDirectory() + isoName.toLowerCase().replace(" ", "_") + ".iso";

                    
                    // Update the UI and perform the download
                    controller.setStatus("Preparing to download...");
                    controller.setProgress(0.0);

                    // Introduce a delay of 10 seconds
                    Thread.sleep(10000);

                    // Update the UI
                    controller.setStatus("Downloading " + isoName + "...");
                    controller.setProgress(0.0);

                    // Download the ISO image
                    downloadFile(downloadUrl, downloadPath, controller);

                    // Update the UI
                    controller.setStatus("Completed!");
                    controller.setProgress(1.0);
                }
                catch(Exception e){
                    controller.setStatus("Error :" + e.getMessage());
                    throw e;
                }
                return null;
            } 
        };
       
        //run the Task on background thread
        new Thread(task).start();
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
     * Maps ISO image names to their respective download URLs.
     *
     * @param isoName The name of the ISO image.
     * @return The download URL for the ISO image.
     */
    private String getDownloadUrl(String isoName) {
        switch (isoName) {
            case "Windows 11 22.26.01":
                return "https://example.com/windows11.iso";
            case "Kali Linux 22.26.01":
                return "https://example.com/kalilinux.iso";
            case "MacOS 22.26.01":
                return "https://example.com/macos.iso";
            default:
                throw new IllegalArgumentException("Unknown ISO image: " + isoName);
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
