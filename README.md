# vm buddy

[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Contributions Welcome](https://img.shields.io/badge/Contributions-Welcome-brightgreen.svg)](https://github.com/Zaphenath-Official/vm_buddy/blob/main/CONTRIBUTING.md) vm buddy is a tool designed to simplify the process of creating and managing virtual machines. It supports several popular virtualization platforms and operating systems.

## Features

### Basic Mode

* **Automated Virtualizer Installation:** Easily download and install your choice of VirtualBox, QEMU, or VMware.
* **Simplified ISO Downloading:** Fetch ISO images for macOS, Linux, and Windows directly through the application.
* **Virtual Machine Configuration:** Set up basic configurations for your virtual machines.

### Developer Mode

* **Includes all Basic Mode features.**
* **Cloud Image Support:** Utilize cloud-based virtual machine images for flexible deployments.
* **Pre-configured VM Templates:** Quickly create VMs using ready-to-use templates.
* **Image Repository Integration:** Seamlessly integrate with image repositories for easy access to VM images.
* **Scripting Language Support:** Automate VM management tasks using FXML, JSON, and JavaScript.
* **Snapshot Management:** Create, revert, and manage VM snapshots.
* **Remote Management:** Access and control your virtual machines remotely.
* **Comprehensive Logging and Monitoring:** Gain insights into VM performance and resource usage.
* **Command-Line Interface (CLI):** Interact with vm buddy through a powerful command-line interface.

## Getting Started

### Installation

You can get vm buddy through the following methods:

* **GitHub Repository:** Clone the repository from [https://github.com/Zaphenath-Official/vm\_buddy](https://github.com/Zaphenath-Official/vm\_buddy).
* **Website (Coming Soon):** Executable files for Linux and Windows will be available for download on the upcoming vm buddy website.
* **JAR File:** A JAR file will be provided for users with a compatible Java Virtual Machine (JVM) installed, making it potentially compatible with macOS and other systems.

### System Requirements (Preliminary)

* **Operating System:**
    * Windows: Aiming for support of actively supported versions (specific versions to be determined).
    * Linux and macOS: Support via the JAR file (requires a compatible JVM). Native Linux & macOS support is planned for the future.
* **RAM:** Minimum of 2GB recommended (subject to change based on further development). 4GB or more is recommended for optimal performance.
* **Disk Space:** Approximately 100MB of free disk space for the vm buddy application (additional space will be required for virtual machines and ISO images).
* **Java:** Java 24 or a compatible JVM is required to run the JAR file.

**Further details on supported operating system versions and system requirements will be provided as development progresses.**

## Usage

### Basic Mode

1.  **Launching vm buddy:** After installation, launch the vm buddy application.
2.  **Mode Selection:** On the initial screen, you'll see cards for "Basic Mode" and "Developer Mode." Click the "Proceed" button under "Basic Mode."
3.  **Homepage:** You will be taken to the homepage where you can begin creating a virtual machine.
4.  **Adding a Virtual Machine:** Click the "Add Virtual Machine" button. This will guide you through the process of:
    * Selecting your preferred virtualizer (VirtualBox, QEMU, or VMware).
    * Choosing an operating system (macOS, Linux, or Windows). You may be prompted to download the ISO image if it's not already available.
    * Configuring basic settings for your virtual machine.
5.  **Starting the Virtual Machine:** Once configured, you should be able to start your newly created virtual machine from the homepage.

### Developer Mode

1.  **Launching vm buddy:** After installation, launch the vm buddy application.
2.  **Mode Selection:** On the initial screen, click the "Proceed" button under "Developer Mode."
3.  **Account Creation (for Remote Management):** You will be prompted to create an account. This is primarily for enabling remote management, cloud image, qimage repos features etc.
4.  **Homepage:** After account creation (or login), you will be taken to the developer mode homepage.
5.  **Accessing Features:** All developer mode features are accessible through a menu bar at the top of the application.
6.  **Creating a Virtual Machine (Template or Manual):**
    * The "Add Virtual Machine" feature is prominently displayed, allowing you to create a VM with custom settings (similar to basic mode).
    * The "Templates" feature is also readily accessible, allowing you to select and deploy a pre-configured virtual machine template.
7.  **Utilizing Developer Features (Example: Taking a Snapshot):**
    * Select a running virtual machine from the homepage or a VM management list.
    * Navigate to the "Snapshot Management" option in the menu bar.
    * Click "Take Snapshot" and provide a name for the snapshot.
8.  **Utilizing Developer Features (Example: Using the CLI):**
    * Open your terminal or command prompt.
    * Use the `vmbuddy` command followed by the desired action (e.g., `vmbuddy list vms`, `vmbuddy snapshot create myvm`). (Note: The exact CLI commands will need to be documented in more detail later).

## Technologies Used

vm buddy is built using the following key technologies:

* **Java 24:** The primary programming language.
* **JavaFX 24:** For creating the user interface, with FXML used for defining the UI structure and layout.
* **Gradle:** As the build automation tool and dependency management system.
* **Virtualizer APIs:** Libraries specific to interacting with each supported virtualization platform (VirtualBox, QEMU, VMware).
* **Java Download and Install Classes/Functions:** Custom-built or standard Java libraries for handling file downloads and software installations.
* **Google API:** Utilized for providing cloud image support.
* **Scripting Languages (Developer Mode):**
    * **FXML:** Can be used within scripts for defining user interface elements or extensions.
    * **JSON:** For data serialization and configuration within scripts.
    * **JavaScript:** The primary scripting language supported in Developer Mode.

## Contributions

Contributions to vm buddy are highly welcomed and appreciated! We encourage the community to get involved in making this tool even better.

### Reporting Issues and Suggesting Features

If you encounter any issues with vm buddy or have ideas for new features, please reach out through one of the following channels:

* **Discord:** [VM Buddy Discord Server](https://discord.gg/nJGGmvR7)
* **GitHub Issues:** [vm\_buddy Issues](https://github.com/Zaphenath-Official/vm\_buddy/issues)

### Contributing Code

To contribute code to vm buddy, please follow this process:

1.  **Initial Communication:** First, get in touch with the project maintainer (Zaphenath-Official) via the [VM Buddy Discord Server](https://discord.gg/nJGGmvR7) or [vm\_buddy Issues](https://github.com/Zaphenath-Official/vm\_buddy/issues) to discuss your intended contribution. Please provide details about what you plan to contribute. This helps to avoid duplicated effort and ensures that the contribution aligns with the project's goals.
2.  **Fork the Repository:** Once you've discussed your contribution, fork the vm buddy repository on GitHub.
3.  **Create a New Branch:** Create a new branch in your forked repository for your contribution (`git checkout -b feature/your-feature-name`).
4.  **Make Your Changes:** Implement your changes, ensuring they adhere to the following guidelines:
    * **Retain Naming Conventions:** Follow the existing naming conventions used throughout the codebase.
    * **Good Indentation:** Ensure your code is well-indented for readability.
    * **Clear Documentation:** Provide clear and concise documentation (comments) for your code, explaining its purpose and functionality.
5.  **Commit Your Changes:** Commit your changes with a descriptive commit message (`git commit -am 'Add some feature'`).
6.  **Push to the Branch:** Push your branch to your forked repository (`git push origin feature/your-feature-name`).
7.  **Open a Pull Request:** Submit a pull request from your branch to the main repository.

### Review and Merging

Project maintainers will review your pull request. This may involve feedback and requests for changes. Once the contribution is approved and tested, it will be merged into the main branch.

### Areas Welcoming Contributions

We particularly welcome contributions in the following areas:

* **User Interface (UI):** Enhancements to the look and feel of the application.
* **Better Image Repository Sites:** Integration with more online image repositories for easier OS selection.
* **Remote Management:** Improvements and расширения to the remote management capabilities.
* **User Experience (UX):** Suggestions and implementations that improve the overall user experience.

Thank you for your interest in contributing to vm buddy!

## License

[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This project is licensed under the [MIT License](LICENSE). See the `LICENSE` file for more information.

**Regarding Commercial Use of Derivative Works:**

While the MIT license grants broad permissions, including the right to create and distribute modified versions of this software, the author kindly requests that any significantly modified software based on "vm buddy" not be commercially sold. The intent is to encourage the open and free use of derivative works within the community, while reserving potential commercialization rights for the original "vm buddy" project. We appreciate your understanding and respect for this intention.

---