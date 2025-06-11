package vm.buddy.basicmode.features.image_repo.api;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.net.URL;

public class OSBoxesImageRepository {

    /**
     * Downloads a pre-made VM image from OSBoxes.org to the user's Downloads directory.
     * @param downloadUrl The direct URL to the VM image file (e.g., .vdi, .ova, .zip).
     * @param fileName The name to save the file as.
     * @throws IOException if download fails.
     */

    String[] androidImageURL = {
        "https://osboxes.org/ubuntu/Ubuntu_22.04.2_LTS_VirtualBox.ova",
        "https://osboxes.org/ubuntu/Ubuntu_20.04.5_LTS_VirtualBox.ova",
        "https://osboxes.org/ubuntu/Ubuntu_18.04.6_LTS_VirtualBox.ova"
    };
    String[] kaliImageURL = {
        "https://osboxes.org/kali/Kali_Linux_2023.2_VirtualBox.ova",
        "https://osboxes.org/kali/Kali_Linux_2022.4_VirtualBox.ova",
        "https://osboxes.org/kali/Kali_Linux_2021.4_VirtualBox.ova"
    };
    String[] ubuntuImageURL = {
        "https://osboxes.org/windows/Windows_10_22H2_VirtualBox.ova",
        "https://osboxes.org/windows/Windows_11_22H2_VirtualBox.ova"
    };
    String[] ubuntuServerURL ={
        "https://osboxes.org/ubuntu/Ubuntu_Server_22.04.2_LTS_VirtualBox.ova",
        "https://osboxes.org/ubuntu/Ubuntu_Server_20.04.5_LTS_VirtualBox.ova",
        "https://osboxes.org/ubuntu/Ubuntu_Server_18.04.6_LTS_VirtualBox.ova"
    };

    public static void downloadVMImage(String downloadUrl, String fileName) throws IOException {
        String userHome = System.getProperty("user.home");
        File downloadsDir = new File(userHome, "Downloads");
        if (!downloadsDir.exists()) {
            downloadsDir.mkdirs();
        }
        File targetFile = new File(downloadsDir, fileName);

        // Open connection to get file size
        var connection = new URL(downloadUrl).openConnection();
        int fileSize = connection.getContentLength();

        try (
            BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
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
