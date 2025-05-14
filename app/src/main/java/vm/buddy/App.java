package vm.buddy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/basic_mode/pages/ui/installer.fxml"));
        Parent root = loader.load();

        // Set up the stage and scene
        primaryStage.setTitle("VM Buddy");
        primaryStage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("/basic_mode/assets/icons/ico_favicon.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
