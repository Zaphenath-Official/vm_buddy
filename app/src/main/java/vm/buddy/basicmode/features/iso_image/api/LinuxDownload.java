package vm.buddy.basicmode.features.iso_image.api;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.util.function.BiConsumer;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.application.Platform;

public class LinuxDownload {

    /**
     * Downloads a Linux ISO image from the given URL and saves it to the user's Downloads directory.
     * @param downloadUrl The direct URL to the Linux ISO file.
     * @param fileName The name to save the file as (e.g., "linux-lite.iso").
     * @throws IOException if download fails.
     */
    @SuppressWarnings("deprecation")
    public static void downloadLinuxIso(String downloadUrl, String fileName) throws IOException {
        String userHome = System.getProperty("user.home");
        String isoDirPath = userHome + File.separator + "VMBuddy" + File.separator + "ISOs";
        File isoDir = new File(isoDirPath);
        if (!isoDir.exists()) {
            isoDir.mkdirs();
        }
        File targetFile = new File(isoDir, fileName);

        // Open connection to get file size
        var connection = new URL(downloadUrl).openConnection();
        int fileSize = connection.getContentLength();

        try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
             FileOutputStream fileOutputStream = new FileOutputStream(targetFile)) {
            byte[] dataBuffer = new byte[4096];
            int bytesRead;
            long totalRead = 0;
            int lastPercent = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 4096)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                totalRead += bytesRead;
                if (fileSize > 0) {
                    int percent = (int) (totalRead * 100 / fileSize);
                    if (percent != lastPercent && percent % 5 == 0) { // Print every 5%
                        System.out.println("Download progress: " + percent + "%");
                        lastPercent = percent;
                    }
                }
            }
        }
        System.out.println("Download complete: " + targetFile.getAbsolutePath());
    }

    /**
     * Downloads a Linux ISO asynchronously with progress and cancellation support.
     * @param downloadUrl The direct URL to the ISO file.
     * @param fileName The name to save the file as (e.g., "ubuntu.iso").
     * @param progressCallback (progress [0-1], percentText) -> void, called on JavaFX thread.
     * @param cancelFlag AtomicBoolean, set to true to cancel download.
     * @param onComplete Runnable, called on JavaFX thread when done or cancelled.
     */
    public static void downloadLinuxISOAsync(String downloadUrl, String fileName, BiConsumer<Double, String> progressCallback, AtomicBoolean cancelFlag, Runnable onComplete) {
        new Thread(() -> {
            String userHome = System.getProperty("user.home");
            String isoDirPath = userHome + File.separator + "VMBuddy" + File.separator + "ISOs";
            File isoDir = new File(isoDirPath);
            if (!isoDir.exists()) {
                isoDir.mkdirs();
            }
            File targetFile = new File(isoDir, fileName);
            try {
                var connection = new URL(downloadUrl).openConnection();
                int fileSize = connection.getContentLength();
                try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                     FileOutputStream fileOutputStream = new FileOutputStream(targetFile)) {
                    byte[] dataBuffer = new byte[4096];
                    int bytesRead;
                    long totalRead = 0;
                    while ((bytesRead = in.read(dataBuffer, 0, 4096)) != -1) {
                        if (cancelFlag.get()) {
                            targetFile.delete();
                            Platform.runLater(onComplete);
                            return;
                        }
                        fileOutputStream.write(dataBuffer, 0, bytesRead);
                        totalRead += bytesRead;
                        if (fileSize > 0) {
                            double progress = (double) totalRead / fileSize;
                            int percent = (int) (progress * 100);
                            Platform.runLater(() -> progressCallback.accept(progress, percent + "%"));
                        }
                    }
                }
                Platform.runLater(() -> progressCallback.accept(1.0, "100%"));
                Platform.runLater(onComplete);
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(onComplete);
            }
        }, "LinuxISODownloadThread").start();
    }
}
