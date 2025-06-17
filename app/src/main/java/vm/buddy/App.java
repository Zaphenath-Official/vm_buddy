package vm.buddy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import vm.buddy.basicmode.features.virtualizer.api.VirtualboxDownload;
// //import vm.buddy.basicmode.features.virtualizer.service.VirtualBox;
// import vm.buddy.basicmode.features.iso_image.api.LinuxDownload;
// import vm.buddy.basicmode.features.iso_image.service.Linux;
// import vm.buddy.basicmode.features.local_vm.service.LocalMachine;




public class App extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(
            "/basicmode/pages/view/HomePage.fxml"
        ));
        primaryStage.setTitle("VM Buddy");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
    //    // String vboxUrl = "https://download.virtualbox.org/virtualbox/7.0.18/VirtualBox-7.0.18-162988-Win.exe";
    //     String fileName = "VirtualBox-7.0.18-162988-Win.exe";
    //     try {
    //         // Download the installer
    //        // VirtualboxDownload.downloadVirtualBox(vboxUrl, fileName);

    //         // Build the full path to the downloaded installer in the Downloads directory
    //         String userHome = System.getProperty("user.home");
    //         String installerPath = userHome + "\\Downloads\\" + fileName;

    //         // Install VirtualBox silently
    //         VirtualBox.installVirtualBoxSilently(installerPath);

    //     } catch (Exception e) {
    //         System.err.println("Download or installation failed: " + e.getMessage());
    //     }

        // String isoUrl = "https://sourceforge.net/projects/linux-lite/files/latest/download";
        // String fileName = "linux-lite-latest-version-64bit.iso";
        // String vmName = "vm buddy test vm";

        // try {
        //     // Notify when download starts
        //     System.out.println("Starting download of Linux ISO: " + fileName);
            
        //     // 1. Download the ISO
        //     LinuxDownload.downloadLinuxIso(isoUrl, fileName);

        //     // 2. Get the ISO path
        //     String isoPath = Linux.getIsoPathInDownloads(fileName);
        //     System.out.println("ISO path: " + isoPath);

        //     // 3. Create the VM and attach the ISO
        //     VirtualBox.runVBoxManage("createvm", "--name", vmName, "--register");
        //     VirtualBox.runVBoxManage("modifyvm", vmName, "--memory", "2048", "--acpi", "on", "--boot1", "dvd");
        //     VirtualBox.runVBoxManage("storagectl", vmName, "--name", "SATA Controller", "--add", "sata", "--controller", "IntelAHCI");
        //     VirtualBox.runVBoxManage("storageattach", vmName, "--storagectl", "SATA Controller", "--port", "0", "--device", "0", "--type", "dvddrive", "--medium", isoPath);

        //     System.out.println("VM '" + vmName + "' created and ISO attached.");
        // } catch (Exception e) {
        //     System.err.println("Operation failed: " + e.getMessage());
        // }

        // String vmName = "vm buddy test vm";
        // try {
        //     // Start the VM in GUI mode
        //     VirtualBox.runVBoxManage("startvm", vmName, "--type", "gui");
        //     System.out.println("Started VM: " + vmName + " (Path: C:\\Users\\panea\\VirtualBox VMs\\" + vmName + ")");
        // } catch (Exception e) {
        //     System.err.println("Failed to start VM: " + e.getMessage());
        // }

        // String vmName = "vm buddy test vm";
        // try {
        //     // Stop the VM gracefully (ACPI shutdown)
        //     LocalMachine.stopVM(vmName);
        //     System.out.println("Powered off VM: " + vmName);
        // } catch (Exception e) {
        //     System.err.println("Failed to stop VM: " + e.getMessage());
        // }

        launch(args);
    }
}