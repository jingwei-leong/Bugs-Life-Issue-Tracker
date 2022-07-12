package bugslife.Controllers;

import bugslife.MainClasses.BugsLife;
import bugslife.MainClasses.MainPage;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CreateIssueUIController implements Initializable {

    @FXML
    private TextField titleF;
    @FXML
    private TextField descriptionF;
    @FXML
    private TextField assigneeF;
    @FXML
    private TextField tagF;
    @FXML
    private TextField priorityF;
    @FXML
    private Label priorityLabel;
    @FXML
    private Label titleLabel;

    private BugsLife b = new BugsLife();
    private MainPage m = b.getM();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void createIssueBackToIssueUI(ActionEvent event) throws Exception {
        int priorityInt = 0;
        boolean proceed = true;
        String titleC = titleF.getText();
        String descriptionC = descriptionF.getText();
        String tagC = tagF.getText();
        String assigneeC = assigneeF.getText();
        priorityLabel.setText("");
        titleLabel.setText("");
        try {
            priorityInt = Integer.parseInt(priorityF.getText());
            if (priorityInt < 1 && priorityInt > 10) {
                priorityLabel.setText("Please enter only number from 1-10");
                proceed = false;
            }
        } catch (NumberFormatException ex) {
            if (!priorityLabel.getText().isEmpty()) {
                priorityLabel.setText("Please enter only number from 1-10");
                proceed = false;
            }
        }
        if (titleC.isEmpty()) {
            titleLabel.setText("You cannot leave this field blank.");
            proceed = false;
        }
        if (descriptionC.isEmpty()) {
            descriptionC = "-";
        }
        if (tagC.isEmpty()) {
            tagC = "-";
        }
        if (assigneeC.isEmpty()) {
            assigneeC = "-";
        }
        if (proceed == true) {
            ProjectUIController.selectedProject.createIssue(titleC, descriptionC, tagC, priorityInt, LoginPageController.usernameC, assigneeC);
            b.changeScene("bugslife/FXML/IssueUI.fxml");
        }
    }

    @FXML
    private void backToIssueUI(MouseEvent event) throws Exception {
        b.changeScene("bugslife/FXML/IssueUI.fxml");
    }

}
