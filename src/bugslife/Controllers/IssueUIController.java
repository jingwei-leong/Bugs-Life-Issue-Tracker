package bugslife.Controllers;

import bugslife.MainClasses.BugsLife;
import bugslife.MainClasses.Project;
import bugslife.MainClasses.Issue;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class IssueUIController implements Initializable {

    @FXML
    private TableView<Issue> table;
    @FXML
    private TableColumn<Issue, Integer> issueIDF;
    @FXML
    private TableColumn<Issue, String> issueTitleF;
    @FXML
    private TableColumn<Issue, String> statusF;
    @FXML
    private TableColumn<Issue, String> tagF;
    @FXML
    private TableColumn<Issue, Integer> priorityF;
    @FXML
    private TableColumn<Issue, String> issueTimeF;
    @FXML
    private TableColumn<Issue, String> assigneeF;
    @FXML
    private TableColumn<Issue, String> createdByF;
    @FXML
    private TextField searchBar;
    @FXML
    private Label searchLabel;
    @FXML
    private AnchorPane graphPrompt;

    private BugsLife b = new BugsLife();
    private Project p = ProjectUIController.selectedProject;
    private ObservableList<Issue> list = FXCollections.observableArrayList();
    private ObservableList<Issue> searchList = FXCollections.observableArrayList();
    public static Issue selectedIssue;
    public static String searchKeywordC;
    @FXML
    private RadioButton firstOption;
    @FXML
    private ToggleGroup options;
    @FXML
    private RadioButton secondOption;
    @FXML
    private RadioButton thirdOption;
    @FXML
    private JFXButton applyButton;
    @FXML
    private JFXButton viewChartButton;
    @FXML
    private Label promptLabel;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Issue.issueCount = p.getLastIssueNum();
        for (Issue i : p.getIssuesList()) {
            list.add(i);
        }
        issueIDF.setCellValueFactory(new PropertyValueFactory<Issue, Integer>("id"));
        issueTitleF.setCellValueFactory(new PropertyValueFactory<Issue, String>("title"));
        statusF.setCellValueFactory(new PropertyValueFactory<Issue, String>("status"));
        tagF.setCellValueFactory(new PropertyValueFactory<Issue, String>("tag"));
        priorityF.setCellValueFactory(new PropertyValueFactory<Issue, Integer>("priority"));
        issueTimeF.setCellValueFactory(new PropertyValueFactory<Issue, String>("issueTime"));
        assigneeF.setCellValueFactory(new PropertyValueFactory<Issue, String>("assignee"));
        createdByF.setCellValueFactory(new PropertyValueFactory<Issue, String>("createdBy"));
        table.setItems(list);
        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                try {
                    selectingIssue();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        searchBar.setOnKeyReleased(e -> {
            searchLabel.setText("");
            if (e.getCode() == KeyCode.ENTER) {
                if (!searchBar.getText().isEmpty()) {
                    searchKeywordC = searchBar.getText();
                    searchList.addAll(p.searchIssue(searchKeywordC));
                    table.getItems().clear();
                    table.setItems(searchList);
                } else {
                    searchLabel.setText("Please type in at least one keyword");
                    table.getItems().clear();
                    for (Issue i : p.getIssuesList()) {
                        list.add(i);
                    }
                    table.setItems(list);
                }
            }
        });

    }

    @FXML
    private void backToProjectUI(MouseEvent event) throws Exception {
        b.changeScene("bugslife/FXML/ProjectUI.fxml");
    }

    @FXML
    private void createNewIssue(ActionEvent event) throws Exception {
        b.changeScene("bugslife/FXML/CreateIssueUI.fxml");
    }

    @FXML
    private void close(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void enterSearchBar(MouseEvent event) {
        if (!searchBar.isVisible()) {
            searchBar.setVisible(true);
        } else {
            searchBar.setVisible(false);
            searchLabel.setText("");
        }
    }

    @FXML
    private void openGraphOption(ActionEvent event) {
        if (!graphPrompt.isVisible()) {
            graphPrompt.setVisible(true);
            viewChartButton.setText("Close Chart Option");
        } else {
            graphPrompt.setVisible(false);
            promptLabel.setVisible(false);
            viewChartButton.setText("View Chart Option");
        }
    }

    @FXML
    private void applyGraphOption(ActionEvent event) throws Exception {
        if (graphPrompt.isVisible()) {
            promptLabel.setVisible(false);
            if (options.getSelectedToggle() != null) {
                if (options.getSelectedToggle().equals(firstOption)) {
                    graphPrompt.setVisible(false);
                    b.changeScene("bugslife/FXML/IssueFreqPerTagPie.fxml");
                } else if (options.getSelectedToggle().equals(secondOption)) {
                    graphPrompt.setVisible(false);
                    b.changeScene("bugslife/FXML/IssueFreqPerStatusPie.fxml");
                } else if (options.getSelectedToggle().equals(thirdOption)) {
                    graphPrompt.setVisible(false);
                    b.changeScene("bugslife/FXML/IssueFreqTimeStampLine.fxml");
                }
            } else {
                promptLabel.setVisible(true);
            }
        }
    }

    public void selectingIssue() throws Exception {
        if (table.getSelectionModel().getSelectedItem() != null) {
            selectedIssue = table.getSelectionModel().getSelectedItem();
            b.changeScene("bugslife/FXML/IssuePageUI.fxml");
        }
    }


}
