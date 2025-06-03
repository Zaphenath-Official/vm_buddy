# VM Buddy

[![License](https://img.shields.io/badge/License-MIT-yellow.svg)] -  (https://opensource.org/licenses/MIT)

[![Contributions Welcome](https://img.shields.io/badge/Contributions-Welcome-brightgreen.svg)]
(https://github.com/Zaphenath-Official/vm_buddy/blob/main/CONTRIBUTING.md)

VM Buddy is a virtualization management tool designed to simplify the process of creating and managing virtual machines. For now it's available for *Windows* OS only and supports virtualization platforms like *VirtualBox*, *AWS* cloud virtual machines,*OSBoxes* image repositories and *Linux/Unix* ISO images.

## Features
* **Local Virtual Machine Management:** Manage Virtual machines that you've already manually setup in your local machine
* **Cloud Virtual Machine Management:** Create, access and manage your cloud virtual machines on AWS and work on them offline.
* **Online Image Repositories:** Have access to latest VM images already created for you thru automated download and setup.
* **Virtual Machine templates:** VM Buddy has pre-configured VM templates ready to be used.

### Basic Mode

* **Automated Virtualizer Installation:** Easily download and install your choice of VirtualBox.
* **Simplified ISO Downloading:** Fetch ISO images for Linux, directly through the application.
* **Virtual Machine Configuration:** Set up basic configurations for your virtual machines.
* **Includes all basic virtual machine features.** I.E create,delete,start,pause resume,stop a VM,file transfer, add peripheral devvices,Event notification and create snapshots.
* **Cloud Image Support:** Utilize cloud-based virtual machine images for flexible deployments.
* **Pre-configured VM Templates:** Quickly create VMs using ready-to-use templates.
* **Image Repository Integration:** Seamlessly integrate with image repositories for easy access to VM images.

## Getting Started
* *Download and install VM Buddy*
* *You'll be directed to SignIn or create an account on AWS if you don't have one*
* *Choose the operation mode you want to work under (basic mode or developer mode)*
* *After chooing the operation mode you'll be redirected to the home screen where you can access all VM Buddy features under that mode.*

### Installation

You can get VM Buddy through the following methods:

* **GitHub Repository:** Clone the repository from [https://github.com/Zaphenath-Official/vm\_buddy](https://github.com/Zaphenath-Official/vm\_buddy).
* **Website (Coming Soon):** Executable files for *Windows* will be available for download on the upcoming vm buddy website.
* **JAR File:** A JAR file will be provided for users with a compatible *Java Virtual Machine (JVM)* installed, making it potentially compatible with *Linux* and  *macOS* and other systems.

### System Requirements (Preliminary)

* **Operating System:**
    * Windows: Aiming for support of actively supported versions (specific versions to be determined).
    * Linux and macOS: Support via the JAR file (requires a compatible JVM). Native Linux & macOS support is planned for the future.
* **RAM:** Minimum of 2GB recommended (subject to change based on further development). 4GB or more is recommended for optimal performance.
* **Disk Space:** Approximately 100MB of free disk space for the vm buddy application (additional space will be required for virtual machines and ISO images).
* **Java:** Java 24 or a compatible JVM is required to run the JAR file.

**Further details on supported operating system versions and system requirements will be provided as development progresses.**

## Usage
* *VM Buddy offers a unified and centralised hub, automated and user-friendly experience for virtualization management*

### Basic Mode

1.  **Launching vm buddy:** After installation, launch the vm buddy application.
2.  **Account Creation (AWS):** You will be prompted to create an account. This is primarily for enabling cloud image, image repos features etc.
3.  **Copy your access id, region and access token on AWs:** When on AWS cloud platform create a IMA user and copy your user id, access token and region.These will be used to manage VMs on AWS. Then go back to VM Buddy
2.  **Mode Selection:** On the initial screen, click the "Proceed" button under "Basic Mode."
4.  **Homepage:** After account creation (or login), you will be taken to the homepage.
5.  **Accessing Features:** All features are accessible through a dashboard on the left of the application.
6.  **Creating a Virtual Machine (Template or Manual):**
    * The "Add Virtual Machine" feature is prominently displayed on the activity bar on the right, allowing you to create a VM with custom settings.
    * The "Templates" feature is also readily accessible on the activity bar, allowing you to select and deploy a pre-configured virtual machine template.
    * For cloud VMs and Image repositories access them through the dashboard and you can select your choice.
7.  **Utilizing Basic Features (Example: Taking a Snapshot):**
    * Select a running virtual machine from the homepage througn the checkbox.
    * Navigate to the "Snapshot Management" option in the dashboard.
    * Click "Take Snapshot" and provide a name for the snapshot.

## Technologies Used

vm buddy is built using the following key technologies:

* **Java 24:** The primary programming language.
* **JavaFX 24:** For creating the user interface, with FXML used for defining the UI structure and layout.
* **Gradle:** As the build automation tool and dependency management system.
* **VirtualBox SDK APIs:** Libraries specific to interacting with VirtualBox.
* **AWs API:** Utilized for providing cloud image support.
* **SQLite DB:** Utilized for storing ddata e.g user preferences,file location paths, user account details.

## Contributions

Contributions to vm buddy are highly welcomed and appreciated! We encourage the community to get involved in making this tool even better.

### Reporting Issues and Suggesting Features

If you encounter any issues with vm buddy or have ideas for new features, please reach out through one of the following channels:

* **Discord:** [VM Buddy Discord Server](https://discord.gg/myzJewcu)
* **GitHub Issues:** [vm\_buddy Issues](https://github.com/Zaphenath-Official/vm\_buddy/issues)
* **WhatsApp:** [VM Buddy WhatsApp Channel](https://whatsapp.com/channel/0029VbA8uQnGJP8ACG3YyX1v)

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

Thank you for your interest in contributing to vm buddy!

## License

[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This project is licensed under the [MIT License](LICENSE). See the `LICENSE` file for more information.

**Regarding Commercial Use of Derivative Works:**

While the MIT license grants broad permissions, including the right to create and distribute modified versions of this software, we kindly requests that any significantly modified software based on "vm buddy" not be commercially sold. The intent is to encourage the open and free use of derivative works within the community. We appreciate your understanding and respect for this intention.

---