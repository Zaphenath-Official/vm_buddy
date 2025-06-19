package vm.buddy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vm.buddy.basicmode.features.local_vm.controller.AddNewVMController;
import vm.buddy.basicmode.features.local_vm.controller.HomePageController;




public class App extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load HomePage.fxml
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/basicmode/pages/view/HomePage.fxml"));
        Parent homePageRoot = homePageLoader.load();
        HomePageController homePageController = homePageLoader.getController();

        // Load and add all major screens to HomePage AnchorPane
        String[] screenFiles = {
            "AddNewVM.fxml", "MyVMs.fxml", "CloudVM.fxml", "ImageRepo.fxml", "Logs.fxml", "Snapshots.fxml", "Templates.fxml"
        };
        for (String fxml : screenFiles) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/basicmode/pages/view/" + fxml));
            Parent screenRoot = loader.load();
            ((javafx.scene.layout.AnchorPane) homePageRoot).getChildren().add(screenRoot);
            switch (fxml) {
                case "AddNewVM.fxml":
                    homePageController.addNewVMController = (AnchorPane) screenRoot;
                    AddNewVMController addNewVMController = loader.getController();
                    addNewVMController.setHomePageController(homePageController);
                    break;
                case "MyVMs.fxml":
                    homePageController.home_screen = (AnchorPane) screenRoot;
                    break;    
                case "CloudVM.fxml":
                    homePageController.cloudVM_screen = (Pane) screenRoot;
                    break;
                case "ImageRepo.fxml":
                    homePageController.virtual_machine_images_screen = (AnchorPane) screenRoot;
                    break;
                case "Logs.fxml":
                    homePageController.logs_screen = (AnchorPane) screenRoot;
                    break;
                case "Snapshots.fxml":
                    homePageController.snapshots_screen = (AnchorPane) screenRoot;
                    break;
                case "Templates.fxml":
                    homePageController.template_screen = (AnchorPane) screenRoot;
                    break;
            }
        }

        // After adding all screens and wiring controller fields
        // Hide all screens except home_screen
        homePageController.setAllScreensInvisible();
        if (homePageController.home_screen != null) homePageController.home_screen.setVisible(true);
        homePageController.resetDashboardColors();
        if (homePageController.dashboard1HomeHBox != null) homePageController.dashboard1HomeHBox.setStyle("-fx-background-color: orange; -fx-background-radius: 100; -fx-border-color: grey; -fx-border-radius: 100;");

        primaryStage.setTitle("VM Buddy");
        primaryStage.setScene(new Scene(homePageRoot));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}