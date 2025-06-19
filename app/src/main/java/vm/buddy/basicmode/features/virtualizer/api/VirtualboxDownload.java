package vm.buddy.basicmode.features.virtualizer.api;

import javafx.concurrent.Task;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.function.BiConsumer;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.application.Platform;

public class VirtualboxDownload {
    public static class DownloadTask extends Task<Void> {
        private final String downloadUrl;
        private final String fileName;
        private final BiConsumer<Double, String> progressCallback;
        private volatile boolean cancelled = false;

        public DownloadTask(String downloadUrl, String fileName, BiConsumer<Double, String> progressCallback) {
            this.downloadUrl = downloadUrl;
            this.fileName = fileName;
            this.progressCallback = progressCallback;
        }

        @Override
        protected Void call() throws Exception {
            String userHome = System.getProperty("user.home");
            String downloadsDirPath = userHome + File.separator + "VMBuddy" + File.separator + "Virtualizers" + File.separator + "Downloads";
            File downloadsDir = new File(downloadsDirPath);
            if (!downloadsDir.exists()) {
                downloadsDir.mkdirs();
            }
            File targetFile = new File(downloadsDir, fileName);

            HttpURLConnection connection = (HttpURLConnection) new URL(downloadUrl).openConnection();
            int fileSize = connection.getContentLength();

            try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(targetFile)) {
                byte[] dataBuffer = new byte[4096];
                int bytesRead;
                long totalRead = 0;
                int lastPercent = 0;
                while ((bytesRead = in.read(dataBuffer, 0, 4096)) != -1) {
                    if (isCancelled() || cancelled) {
                        targetFile.delete();
                        updateMessage("Cancelled");
                        if (progressCallback != null) progressCallback.accept(0.0, "Cancelled");
                        return null;
                    }
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    totalRead += bytesRead;
                    if (fileSize > 0) {
                        double progress = (double) totalRead / fileSize;
                        int percent = (int) (progress * 100);
                        updateProgress(progress, 1.0);
                        updateMessage(percent + "%");
                        if (progressCallback != null && percent != lastPercent) {
                            progressCallback.accept(progress, percent + "%");
                            lastPercent = percent;
                        }
                    }
                }
            }
            updateProgress(1.0, 1.0);
            updateMessage("Download complete");
            if (progressCallback != null) progressCallback.accept(1.0, "100%");
            return null;
        }

        @Override
        protected void cancelled() {
            cancelled = true;
        }
    }

    /**
     * Downloads the VirtualBox installer from the given URL and saves it to the user's Downloads directory.
     * @param downloadUrl The direct URL to the VirtualBox .exe file.
     * @param fileName The name to save the file as (e.g., "VirtualBox.exe").
     * @throws IOException if download fails.
     */
    public static void downloadVirtualBox(String downloadUrl, String fileName) throws IOException {
        String userHome = System.getProperty("user.home");
        String downloadsDirPath = userHome + File.separator + "VMBuddy" + File.separator + "Virtualizers" + File.separator + "Downloads";
        File downloadsDir = new File(downloadsDirPath);
        if (!downloadsDir.exists()) {
            downloadsDir.mkdirs();
        }
        File targetFile = new File(downloadsDir, fileName);

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
     * Downloads the VirtualBox installer asynchronously with progress and cancellation support.
     * @param downloadUrl The direct URL to the VirtualBox .exe file.
     * @param fileName The name to save the file as (e.g., "VirtualBox.exe").
     * @param progressCallback (progress [0-1], percentText) -> void, called on JavaFX thread.
     * @param cancelFlag AtomicBoolean, set to true to cancel download.
     * @param onComplete Runnable, called on JavaFX thread when done or cancelled.
     */
    public static void downloadVirtualBoxAsync(String downloadUrl, String fileName, BiConsumer<Double, String> progressCallback, AtomicBoolean cancelFlag, Runnable onComplete) {
        new Thread(() -> {
            String userHome = System.getProperty("user.home");
            String downloadsDirPath = userHome + File.separator + "VMBuddy" + File.separator + "Virtualizers" + File.separator + "Downloads";
            File downloadsDir = new File(downloadsDirPath);
            if (!downloadsDir.exists()) {
                downloadsDir.mkdirs();
            }
            File targetFile = new File(downloadsDir, fileName);
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
        }, "VirtualBoxDownloadThread").start();
    }

    // New method to get a DownloadTask for UI integration
    public static DownloadTask createDownloadTask(String downloadUrl, String fileName, BiConsumer<Double, String> progressCallback) {
        return new DownloadTask(downloadUrl, fileName, progressCallback);
    }
}
