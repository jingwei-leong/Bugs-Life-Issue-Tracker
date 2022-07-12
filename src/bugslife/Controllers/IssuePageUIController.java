package bugslife.Controllers;

import bugslife.MainClasses.BugsLife;
import bugslife.MainClasses.Issue;
import bugslife.MainClasses.Comment;
import bugslife.MainClasses.Project;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class IssuePageUIController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML
    private TextField textTitle;
    @FXML
    private Text desText;
    @FXML
    private TextField textDes;
    @FXML
    private Label assigneeLabel;
    @FXML
    private TextField textAssignee;
    @FXML
    private Label tagLabel;
    @FXML
    private TextField textTag;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField textStatus;
    @FXML
    private Label priorityLabel;
    @FXML
    private TextField textPriority;
    @FXML
    private Text textHistory;
    @FXML
    private Label idLabel;
    @FXML
    private Label createdByLabel;
    @FXML
    private Label createDateLabel;
    @FXML
    private Text textComment;

    private BugsLife b = new BugsLife();
    private Project p = ProjectUIController.selectedProject;
    private Issue i = IssueUIController.selectedIssue;
    @FXML
    private RadioButton editComment;
    @FXML
    private RadioButton addNewComment;
    @FXML
    private TextField commentIDEditLabel;
    @FXML
    private JFXRadioButton angry;
    @FXML
    private ToggleGroup react;
    @FXML
    private JFXRadioButton happy;
    @FXML
    private JFXRadioButton thumbsUp;
    @FXML
    private JFXRadioButton smile;
    @FXML
    private AnchorPane commentPagePrompt;
    @FXML
    private ToggleGroup toggleComment;
    @FXML
    private TextField textEditComment;
    @FXML
    private TextField textCommentIDReact;
    @FXML
    private Label reactLabel;
    @FXML
    private Label titlePrompt;
    @FXML
    private Label assigneePrompt;
    @FXML
    private Label priorityPrompt;
    @FXML
    private Label tagPrompt;
    @FXML
    private Label statusPrompt;
    @FXML
    private Label desPrompt;
    @FXML
    private Label editIdLabel;
    @FXML
    private Label addEditCommentLabel;
    
    String loginUser = LoginPageController.usernameC;
    @FXML
    private AnchorPane editDesPane;
    @FXML
    private AnchorPane editAssigneePane;
    @FXML
    private AnchorPane editStatusPane;
    @FXML
    private AnchorPane editTagPane;
    @FXML
    private AnchorPane editTitlePane;
    @FXML
    private AnchorPane editPriorityPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Comment.commentCount = i.getLastCommentNum();
        idLabel.setText("Issue #" + i.getId());
        createDateLabel.setText("Created on: " + i.getIssueTime());
        createdByLabel.setText("Created By: " + i.getCreatedBy());
        titleLabel.setText(i.getTitle());
        statusLabel.setText("Status: " + i.getStatus());
        priorityLabel.setText("Priority: " + i.getPriority());
        tagLabel.setText("Tag: " + i.getTag());
        assigneeLabel.setText("Assignee: " + i.getAssignee());
        desText.setText("Description:\n" + i.getDescriptionText());
        if (loginUser.equals(i.getAssignee()) ) {
            editStatusPane.setVisible(true);
        }
        else if (loginUser.equals(i.getCreatedBy())) {
            editAssigneePane.setVisible(true);
            editDesPane.setVisible(true);
            editStatusPane.setVisible(true);
            editTagPane.setVisible(true);
            editTitlePane.setVisible(true);
            editPriorityPane.setVisible(true);
        }
        
        editComment.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                commentIDEditLabel.setVisible(true);
            }
        });
        addNewComment.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                commentIDEditLabel.setVisible(false);
            }
        });
    }

    @FXML
    private void close(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void editDescription(MouseEvent event) {
        if (!textDes.isVisible()) {
            desText.setVisible(false);
            textDes.setText("");
            textDes.setVisible(true);
        } else {
            desPrompt.setText("");
            if (!textDes.getText().isEmpty()) {
                textDes.setVisible(false);
                i.updateDescription(textDes.getText());
                desText.setText("Description: \n" + i.getDescriptionText());
                desText.setVisible(true);
                textHistory.setText(i.showHistory());
            } else {
                desPrompt.setText("Please do not leave blank!");
            }
        }
    }

    @FXML
    private void editAssignee(MouseEvent event) {
        if (!textAssignee.isVisible()) {
            assigneeLabel.setVisible(false);
            textAssignee.setText("");
            textAssignee.setVisible(true);
        } else {
            assigneePrompt.setText("");
            if (!textAssignee.getText().isEmpty()) {
                textAssignee.setVisible(false);
                i.updateAssignee(textAssignee.getText());
                assigneeLabel.setText("Assignee: " + i.getAssignee());
                assigneeLabel.setVisible(true);
                textHistory.setText(i.showHistory());
            } else {
                assigneePrompt.setText("Please do not leave blank!");
            }
        }
    }

    @FXML
    private void editStatus(MouseEvent event) {
        if (!textStatus.isVisible()) {
            statusLabel.setVisible(false);
            textStatus.setText("");
            textStatus.setVisible(true);
        } else {
            statusPrompt.setText("");
            if (!textStatus.getText().isEmpty() && i.canSetStatus(textStatus.getText())) {
                textStatus.setVisible(false);
                i.updateStatus(textStatus.getText());
                statusLabel.setText("Status: " + i.getStatus());
                statusLabel.setVisible(true);
                textHistory.setText(i.showHistory());
            } else if (textStatus.getText().isEmpty()) {
                statusPrompt.setText("Please do not leave blank!");
            } else {
                statusPrompt.setText("Please enter a valid status!");
            }
        }
    }

    @FXML
    private void editTag(MouseEvent event) {
        if (!textTag.isVisible()) {
            tagLabel.setVisible(false);
            textTag.setText("");
            textTag.setVisible(true);
        } else {
            tagPrompt.setText("");
            if (!textTag.getText().isEmpty()) {
                textTag.setVisible(false);
                i.updateTag(textTag.getText());
                tagLabel.setText("Tag: " + i.getTag());
                tagLabel.setVisible(true);
                textHistory.setText(i.showHistory());
            } else {
                tagPrompt.setText("Please do not leave blank!");
            }
        }
    }

    @FXML
    private void editPriority(MouseEvent event) {
        if (!textPriority.isVisible()) {
            priorityLabel.setVisible(false);
            textPriority.setText("");
            textPriority.setVisible(true);
        } else {
            priorityPrompt.setText("");
            if (!textPriority.getText().isEmpty()) {
                textPriority.setVisible(false);
                i.updatePriority(textPriority.getText());
                priorityLabel.setText("Priority: " + textPriority.getText());
                priorityLabel.setVisible(true);
                textHistory.setText(i.showHistory());
            } else {
                priorityPrompt.setText("Please do not leave blank!");
            }
        }
    }

    @FXML
    private void editTitle(MouseEvent event) {
        if (!textTitle.isVisible()) {
            titleLabel.setVisible(false);
            textTitle.setText("");
            textTitle.setVisible(true);
        } else {
            titlePrompt.setText("");
            if (!textTitle.getText().isEmpty()) {
                textTitle.setVisible(false);
                i.updateTitle(textTitle.getText());
                titleLabel.setText(i.getTitle());
                titleLabel.setVisible(true);
            } else {
                titlePrompt.setText("Please do not leave blank!");
            }
        }
    }

    @FXML
    private void backToIssueUI(MouseEvent event) throws Exception {
        b.changeScene("bugslife/FXML/IssueUI.fxml");
    }

    @FXML
    private void showHistory(Event event) {
        textHistory.setText(i.showHistory());
    }

    @FXML
    private void showComment(Event event) {
        textComment.setText(i.getCommentPage());
    }

    @FXML
    private void modifyCommentPage(ActionEvent event) {
        if (!commentPagePrompt.isVisible()) {
            commentPagePrompt.setVisible(true);
            commentIDEditLabel.setVisible(false);
            angry.setSelected(false);
            smile.setSelected(false);
            thumbsUp.setSelected(false);
            happy.setSelected(false);
            editComment.setSelected(false);
            addNewComment.setSelected(false);
            textCommentIDReact.setText("");
            textEditComment.setText("");
            editIdLabel.setText("");
            reactLabel.setText("");
            addEditCommentLabel.setText("");
        }
    }

    @FXML
    private void backToIssuePageUI(ActionEvent event) {
        if (commentPagePrompt.isVisible()) {
            boolean cannotEnd = false;
            if (toggleComment.getSelectedToggle() != null) {
                addEditCommentLabel.setText("");
                if (textEditComment.getText().isEmpty()) {
                    cannotEnd = true;
                    addEditCommentLabel.setText("Please do not leave blank!");
                } else if (toggleComment.getSelectedToggle().equals(addNewComment)) {
                    i.addComment(LoginPageController.usernameC, textEditComment.getText());
                    textHistory.setText(i.showHistory());
                    textComment.setText(i.getCommentPage());
                    commentPagePrompt.setVisible(false);
                } else if (toggleComment.getSelectedToggle().equals(editComment)) {
                    try {
                        int commentId = Integer.parseInt(commentIDEditLabel.getText());
                        if (commentId >= 1 && commentId <= i.getComments().size() && loginUser.equals(i.getComment(commentId - 1).getUser())) {
                            i.updateComment(Integer.parseInt(commentIDEditLabel.getText()), textEditComment.getText());
                            textHistory.setText(i.showHistory());
                            textComment.setText(i.getCommentPage());
                            commentIDEditLabel.setText("");
                            commentPagePrompt.setVisible(false);
                        } else if (!loginUser.equals(i.getComment(commentId - 1).getUser())) {
                            cannotEnd = true;
                            editIdLabel.setText("You cannot edit the comment");
                        } else {
                            cannotEnd = true;
                            editIdLabel.setText("Please enter a valid Id!");
                        }
                    } catch (Exception ex) {
                        cannotEnd = true;
                        editIdLabel.setText("Please enter a valid Id!");
                    }
                }
            }
            if (react.getSelectedToggle() != null && !cannotEnd) {
                int commentIDReact;
                try {
                    commentIDReact = Integer.parseInt(textCommentIDReact.getText());
                    if (react.getSelectedToggle().equals(angry) && commentIDReact >= 1 && commentIDReact <= i.getComments().size()) {
                        i.addReaction(commentIDReact, "angry");
                        textHistory.setText(i.showHistory());
                        textComment.setText(i.getCommentPage());
                        textCommentIDReact.setText("");
                        commentPagePrompt.setVisible(false);
                    } else if (react.getSelectedToggle().equals(smile) && commentIDReact >= 1 && commentIDReact <= i.getComments().size()) {
                        i.addReaction(commentIDReact, "smile");
                        textHistory.setText(i.showHistory());
                        textComment.setText(i.getCommentPage());
                        textCommentIDReact.setText("");
                        commentPagePrompt.setVisible(false);
                    } else if (react.getSelectedToggle().equals(happy) && commentIDReact >= 1 && commentIDReact <= i.getComments().size()) {
                        i.addReaction(commentIDReact, "happy");
                        textHistory.setText(i.showHistory());
                        textComment.setText(i.getCommentPage());
                        textCommentIDReact.setText("");
                        commentPagePrompt.setVisible(false);
                    } else if (react.getSelectedToggle().equals(thumbsUp) && commentIDReact >= 1 && commentIDReact <= i.getComments().size()) {
                        i.addReaction(commentIDReact, "thumbsUp");
                        textHistory.setText(i.showHistory());
                        textComment.setText(i.getCommentPage());
                        textCommentIDReact.setText("");
                        commentPagePrompt.setVisible(false);
                    } else {
                        reactLabel.setText("Please enter a valid Id!");
                    }
                } catch (Exception ex) {
                    reactLabel.setText("Please enter a valid Id!");
                }

            }

        }
    }

    @FXML
    private void closeCommentPagePrompt(MouseEvent event) {
        if (commentPagePrompt.isVisible()) {
            commentPagePrompt.setVisible(false);
        }
    }

    @FXML
    private void cleartoggles(MouseEvent event) {
        commentIDEditLabel.setVisible(false);
        angry.setSelected(false);
        smile.setSelected(false);
        thumbsUp.setSelected(false);
        happy.setSelected(false);
        editComment.setSelected(false);
        addNewComment.setSelected(false);
        textCommentIDReact.setText("");
        textEditComment.setText("");
        commentIDEditLabel.setText("");
        editIdLabel.setText("");
        reactLabel.setText("");
    }

}
