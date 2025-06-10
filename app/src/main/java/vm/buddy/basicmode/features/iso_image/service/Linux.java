package vm.buddy.basicmode.features.iso_image.service;

public class Linux {

    /*
     * Returns the absolute path to the ISO file in the user's Downloads directory.
     * @param fileName The name of the ISO file (e.g., "linux-lite-6.6-64bit.iso")
     * @return The absolute path to the ISO file in Downloads
     * 
     * By default the iso is loacted in downloads
     */
    public static String getIsoPathInDownloads(String fileName) {
        String userHome = System.getProperty("user.home");
        return userHome + "\\Downloads\\" + fileName;
    }
}
