package vm.buddy.basicmode.features.local_vm.service;

import vm.buddy.basicmode.features.virtualizer.service.VirtualBox;

public class LocalMachine {

    public static void createVM(String vmName) throws Exception {
        VirtualBox.runVBoxManage("createvm", "--name", vmName, "--register");
    }

    public static void deleteVM(String vmName) throws Exception {
        VirtualBox.runVBoxManage("unregistervm", vmName, "--delete");
    }

    public static void startVM(String vmName) throws Exception {
        VirtualBox.runVBoxManage("startvm", vmName, "--type", "gui");
    }

    public static void stopVM(String vmName) throws Exception {
        VirtualBox.runVBoxManage("controlvm", vmName, "poweroff");
    }

    public static void pauseVM(String vmName) throws Exception {
        VirtualBox.runVBoxManage("controlvm", vmName, "pause");
    }

    public static void resumeVM(String vmName) throws Exception {
        VirtualBox.runVBoxManage("controlvm", vmName, "resume");
    }

    public static void configMemory(String vmName, String memoryMB) throws Exception {
        VirtualBox.runVBoxManage("modifyvm", vmName, "--memory", memoryMB);
    }

    public static void configCPU(String vmName, String cpuCount) throws Exception {
        VirtualBox.runVBoxManage("modifyvm", vmName, "--cpus", cpuCount);
    }

    public static void configDisk(String vmName, String diskPath) throws Exception {
        VirtualBox.runVBoxManage("storageattach", vmName, "--storagectl", "SATA Controller", "--port", "0", "--device", "0", "--type", "hdd", "--medium", diskPath);
    }

    public static void attachUSB(String vmName, String uuid) throws Exception {
        VirtualBox.runVBoxManage("usbfilter", "add", "0", "--target", vmName, "--name", "USB", "--action", "hold", "--vendorid", uuid);
    }

    public static void detachUSB(String vmName, String uuid) throws Exception {
        VirtualBox.runVBoxManage("usbfilter", "remove", "0", "--target", vmName);
    }

    public static void transferFileToVM(String vmName, String src, String dest) throws Exception {
        VirtualBox.runVBoxManage("guestcontrol", vmName, "copyto", src, dest, "--username", "user", "--password", "pass");
    }

    public static void transferFileFromVM(String vmName, String src, String dest) throws Exception {
        VirtualBox.runVBoxManage("guestcontrol", vmName, "copyfrom", src, dest, "--username", "user", "--password", "pass");
    }

    public static void getResourceUsage(String vmName) throws Exception {
        VirtualBox.runVBoxManage("metrics", "query", vmName);
    }

    public static void createSnapshot(String vmName, String snapshotName) throws Exception {
        VirtualBox.runVBoxManage("snapshot", vmName, "take", snapshotName);
    }

    public static void restoreSnapshot(String vmName, String snapshotName) throws Exception {
        VirtualBox.runVBoxManage("snapshot", vmName, "restore", snapshotName);
    }

    public static void backupVM(String vmName, String backupPath) throws Exception {
        VirtualBox.runVBoxManage("export", vmName, "--output", backupPath);
    }

    public static void accessLogs(String vmName) throws Exception {
        // VirtualBox logs are stored in the VM's folder
        String userHome = System.getProperty("user.home");
        String logPath = userHome + "\\VirtualBox VMs\\" + vmName + "\\Logs\\VBox.log";
        System.out.println("Log file: " + logPath);
    }

    public static void monitorVMState(String vmName) throws Exception {
        VirtualBox.runVBoxManage("showvminfo", vmName, "--machinereadable");
    }

    public static void cloneVM(String sourceVMName, String newVMName) throws Exception {
        VirtualBox.runVBoxManage("clonevm", sourceVMName, "--name", newVMName, "--register");
    }
}
