package bugslife.Controllers;

import bugslife.MainClasses.BugsLife;
import bugslife.MainClasses.MainPage;
import bugslife.MainClasses.Project;
import bugslife.MainClasses.Issue;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class IssueFreqTimeStampLineController implements Initializable {

    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private Button button;
    @FXML
    private TextField yearLabel;
    @FXML
    private Label promptLabel;
    //private TreeMap<String,Integer> tmap = new TreeMap<String, Integer>();
    private String[] month = {"Jan", "Feb", "Mac", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};

    private BugsLife bug = new BugsLife();
    private MainPage mainP = bug.getM();
    private String year;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        lineChart.getData().clear();
        promptLabel.setText("");
        try {
            year = yearLabel.getText();
            Integer.parseInt(year);
            int[] issueFreq = freqIssuePerMonth(year);
            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
            for (int i = 0; i < 12; i++) {
                series.getData().add(new XYChart.Data<>(month[i], issueFreq[i]));
            }
            System.out.println("here");
            lineChart.getData().add(series);
        } catch (NumberFormatException ex) {
            if (year.isEmpty()) {
                promptLabel.setText("Please fill in the year!");
            } else {
                promptLabel.setText("Invalid input!");
            }
        }
    }

    public int[] freqIssuePerMonth(String year) {
        int firstDashIndex;
        String month;
        int[] issueFreq = new int[12];
        Project p = ProjectUIController.selectedProject;
        List<String> list2 = new ArrayList<>();
        List<Issue> issue = p.getIssuesList();
        for (int i = 0; i < issue.size(); i++) {
            String fullTimeStamp = issue.get(i).getIssueTime();
            firstDashIndex = fullTimeStamp.indexOf("-");
            if (fullTimeStamp.substring(0, firstDashIndex).equals(year)) {
                month = fullTimeStamp.substring(firstDashIndex + 1, firstDashIndex + 3);
                if (month.equals("01")) {
                    issueFreq[0] += 1;
                } else if (month.equals("02")) {
                    issueFreq[1] += 1;
                } else if (month.equals("03")) {
                    issueFreq[2] += 1;
                } else if (month.equals("04")) {
                    issueFreq[3] += 1;
                } else if (month.equals("05")) {
                    issueFreq[4] += 1;
                } else if (month.equals("06")) {
                    issueFreq[5] += 1;
                } else if (month.equals("07")) {
                    issueFreq[6] += 1;
                } else if (month.equals("08")) {
                    issueFreq[7] += 1;
                } else if (month.equals("09")) {
                    issueFreq[8] += 1;
                } else if (month.equals("10")) {
                    issueFreq[9] += 1;
                } else if (month.equals("11")) {
                    issueFreq[10] += 1;
                } else if (month.equals("12")) {
                    issueFreq[11] += 1;
                }
            }
        }
        return issueFreq;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void backToIssueUI(MouseEvent event) throws Exception {
        bug.changeScene("bugslife/FXML/IssueUI.fxml");
    }

}
