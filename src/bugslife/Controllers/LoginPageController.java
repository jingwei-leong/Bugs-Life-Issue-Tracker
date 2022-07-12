package bugslife.Controllers;

import bugslife.MainClasses.BugsLife;
import bugslife.MainClasses.User;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginPageController {

    @FXML
    private Label wrongLabel;
    @FXML
    private TextField usernameF;
    @FXML
    private TextField passwordF;
    @FXML
    private JFXTextField userSignUp;
    @FXML
    private JFXPasswordField passwordSignUp;
    @FXML
    private Label signUpLabel;
    @FXML
    private AnchorPane signUpPane;

    private BugsLife b = new BugsLife();
    public static String usernameC, passwordC;

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

        usernameC = usernameF.getText();
        passwordC = passwordF.getText();
        if (BugsLife.canLogin(usernameC, passwordC)) {
            b.changeScene("bugslife/FXML/ProjectUI.fxml");
        } else if (usernameC.isEmpty() || passwordC.isEmpty()) {
            wrongLabel.setText("Please fill up your data");
        } else {
            wrongLabel.setText("You have entered wrong username or password!!");
        }
    }

    @FXML
    private void close(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void registerButton(ActionEvent event) {
        boolean valid = true;
        signUpLabel.setText("");
        for (User userDatabase : b.getM().getUsers()) {
            if (userSignUp.getText().equals(userDatabase.getUsername())) {
                signUpLabel.setText("Existing username, username unavailable");
                valid = false;
                break;
            }
        }
        if (userSignUp.getText().isEmpty() || passwordSignUp.getText().isEmpty()) {
                signUpLabel.setText("Please fill up the required data!");
                valid = false;
            }
        if (valid) {
            b.getM().addUser(userSignUp.getText(), passwordSignUp.getText());
            signUpPane.setVisible(false);
        }
    }

    @FXML
    private void signUpButton(ActionEvent event) {
        if (!signUpPane.isVisible()) {
            signUpLabel.setText("");
            userSignUp.setText("");
            passwordSignUp.setText("");
            signUpPane.setVisible(true);
        }
    }

    @FXML
    private void closeSignUp(MouseEvent event) {
        if (signUpPane.isVisible()) {
            signUpPane.setVisible(false);
        }
    }

}
