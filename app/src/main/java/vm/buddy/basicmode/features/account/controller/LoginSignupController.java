// src/main/java/vm/buddy/basicmode/features/account/view/LoginSignupController.java
package vm.buddy.basicmode.features.account.controller;

import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class LoginSignupController implements Initializable {

    @FXML private StackPane cardFlipperContainer;
    @FXML private VBox signup_card;
    @FXML private VBox login_card;
    @FXML private Label loginTriggerLabel;
    @FXML private Label error_message;
    @FXML private Label error_message1;
    @FXML private Label signupTriggerLabel;
    @FXML private Pane glassCard;
    @FXML private TextField signupFullName;
    @FXML private TextField signupPassword;
    @FXML private TextField signupConfirmPassword;
    @FXML private Button signupBtn;
    @FXML private TextField loginUsername;
    @FXML private TextField loginPassword;
    @FXML private Button loginBtn;
    @FXML private Pane loaderPane;

    private boolean isLoginVisible = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load fonts
        String[] fonts = {
            "/basicmode/features/account/resources/fonts/Cookie-Regular.ttf",
            "/basicmode/features/account/resources/fonts/Aubrey-Regular.ttf",
            "/basicmode/features/account/resources/fonts/ZenLoop-Regular.ttf",
            "/basicmode/features/account/resources/fonts/FuunnelDisplay-VariableFont_wght.ttf"
        };
        for (String fontPath : fonts) {
            Font.loadFont(getClass().getResourceAsStream(fontPath), 14);
        }

        // Glass effect
        glassCard.setEffect(new BoxBlur(5, 5, 1));

        // Initial card visibility
        signup_card.setVisible(true);
        login_card.setVisible(false);
    }

    
    @FXML
    private void flipToLogin() {
        if (!isLoginVisible) flipCard(true);
    }

    @FXML
    private void flipToSignup() {
        if (isLoginVisible) flipCard(false);
    }

    // --- Card Flipping ---
    private void flipCard(boolean toLogin) {
        RotateTransition rotation = new RotateTransition(Duration.millis(500), cardFlipperContainer);
        rotation.setAxis(Rotate.Y_AXIS);
        rotation.setFromAngle(0);
        rotation.setToAngle(toLogin ? -90 : 90);
        rotation.setInterpolator(Interpolator.EASE_OUT);

        rotation.setOnFinished(_ -> {
            signup_card.setVisible(!toLogin);
            login_card.setVisible(toLogin);

            RotateTransition rotation2 = new RotateTransition(Duration.millis(500), cardFlipperContainer);
            rotation2.setAxis(Rotate.Y_AXIS);
            rotation2.setFromAngle(toLogin ? 90 : -90);
            rotation2.setToAngle(0);
            rotation2.setInterpolator(Interpolator.EASE_IN);
            rotation2.play();
        });
        isLoginVisible = toLogin;
        rotation.play();
    }

    // --- Loader ---
    private void showLoaderFor10Seconds(Runnable afterLoader) {
        loaderPane.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(10));
        pause.setOnFinished(_ -> {
            loaderPane.setVisible(false);
            if (afterLoader != null) afterLoader.run();
        });
        pause.play();
    }

    //validate whether all textfields hve values
    private boolean validateFields(List<TextField> fields, Button button) {
    boolean hasError = false;
    // Remove error styles
    fields.forEach(f -> f.getStyleClass().remove("error-border"));
    button.getStyleClass().remove("error-border");
    // Only clear the error message for the visible card
    if (isLoginVisible) {
        error_message1.setVisible(false);
    } else {
        error_message.setVisible(false);
    }

    // Check for empty fields
    for (TextField field : fields) {
        if (field.getText() == null || field.getText().trim().isEmpty()) {
            field.getStyleClass().add("error-border");
            hasError = true;
        }
    }
    if (hasError) {
        button.getStyleClass().add("error-border");
        // Only show the error message for the visible card
        if (isLoginVisible) {
            error_message1.setVisible(true);
            error_message1.setText("Fill all fields");
        } else {
            error_message.setVisible(true);
            error_message.setText("Fill all fields");
        }
    }
    return !hasError;
}

    private void clearFields(List<TextField> fields) {
        fields.forEach(TextField::clear);
    }

    // --- Signup ---
    @FXML
    private void onSignupClicked() {
        List<TextField> signupFields = Arrays.asList(signupFullName, signupPassword, signupConfirmPassword);

        boolean valid = validateFields(signupFields, signupBtn);

        // Additional check: passwords must match
        if (valid && !signupPassword.getText().equals(signupConfirmPassword.getText())) {
            signupConfirmPassword.getStyleClass().add("error-border");
            signupBtn.getStyleClass().add("error-border");
            error_message.setVisible(true);
            error_message.setText("Passwords don't match");
            return;
        }

        if (!valid) return;

        // All fields filled and passwords match, print values
        System.out.println("Signup Full Name: " + signupFullName.getText());
        System.out.println("Signup Password: " + signupPassword.getText());
        System.out.println("Signup Confirm Password: " + signupConfirmPassword.getText());

        showLoaderFor10Seconds(() -> clearFields(signupFields));
    }

    // --- Login ---
    @FXML
    private void onLoginClicked() {
        List<TextField> loginFields = Arrays.asList(loginUsername, loginPassword);

        boolean valid = validateFields(loginFields, loginBtn);

        if (!valid) return;

        // All fields filled, print values
        System.out.println("Login Username: " + loginUsername.getText());
        System.out.println("Login Password: " + loginPassword.getText());

        showLoaderFor10Seconds(() -> clearFields(loginFields));
    }
}