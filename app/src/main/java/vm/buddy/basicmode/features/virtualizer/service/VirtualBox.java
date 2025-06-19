package vm.buddy.basicmode.features.virtualizer.service;

/* Here the logic to handle virtualbox e.g creating VMs, deleting VMs etc. */

import java.io.File;
import java.io.IOException;

public class VirtualBox {

    /**
     * Installs VirtualBox silently using the given installer path.
     * Shows output of the installation process.
     * Does not create a desktop shortcut.
     * @param installerPath Full path to the VirtualBox installer .exe
     * @throws IOException if installation fails
     * @throws InterruptedException if the process is interrupted
     */
    public static void installVirtualBoxSilently(String installerPath) throws IOException, InterruptedException {
        File installer = new File(installerPath);
        if (!installer.exists()) {
            System.err.println("Installer not found: " + installerPath);
            return;
        }

        // Set custom install directory
        String userHome = System.getProperty("user.home");
        String customInstallDir = userHome + File.separator + "VMBuddy" + File.separator + "Virtualizers" + File.separator + "installs" + File.separator + "VirtualBox";
        // Create the directory if it doesn't exist
        File customDir = new File(customInstallDir);
        if (!customDir.exists()) {
            customDir.mkdirs();
        }

        // --silent: silent install
        // --no-shortcut: do not create desktop/start menu shortcuts
        // --path: custom install directory (if supported by installer)
        System.out.println("Running: " + installerPath + " --silent --no-shortcut --path " + customInstallDir);
        ProcessBuilder pb = new ProcessBuilder(installerPath, "--silent", "--no-shortcut", "--path", customInstallDir);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        // Show output
        try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("VirtualBox installed successfully.");
            System.out.println("Custom install path: " + customInstallDir);
        } else {
            System.err.println("VirtualBox installation failed with exit code: " + exitCode);
        }
    }

    public static void runVBoxManage(String... args) throws IOException, InterruptedException {
        String userHome = System.getProperty("user.home");
        String vboxManagePath = userHome + File.separator + "VMBuddy" + File.separator + "Virtualizers" + File.separator + "installs" + File.separator + "VirtualBox" + File.separator + "VBoxManage.exe";
        // Build the command
        String[] command = new String[args.length + 1];
        command[0] = vboxManagePath;
        System.arraycopy(args, 0, command, 1, args.length);

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            System.err.println("VBoxManage failed with exit code: " + exitCode);
        }
    }
}
