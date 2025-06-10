package vm.buddy.basicmode.features.iso_image.api;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.net.URL;

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
        File downloadsDir = new File(userHome, "Downloads");
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
}
