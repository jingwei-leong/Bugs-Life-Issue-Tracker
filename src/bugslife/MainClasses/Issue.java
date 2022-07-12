package bugslife.MainClasses;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Issue {

    public transient static Integer issueCount = 0;
    protected transient static String sortType;
    private Integer id;
    private String title;
    private Integer priority;
    private String status;
    private String tag;
    private String descriptionText;
    private String createdBy;
    private String assignee;
    private long issueTime;
    ArrayList<Comment> comments = new ArrayList<>();
    List<Update> historyIssue = new ArrayList<>();

    public Issue(String issueTitle, String issueDescrip, String tag, Integer priority, String status, String creatorUser, String assigneeUser) {
        issueCount++;
        long time = Instant.now().getEpochSecond();
        this.id = issueCount;
        this.priority = priority;
        this.title = issueTitle;
        this.descriptionText = issueDescrip;
        this.createdBy = creatorUser;
        this.assignee = assigneeUser;
        this.tag = tag;
        this.status = status;
        this.issueTime = Instant.now().getEpochSecond();
        historyIssue.add(new Update(issueTitle, issueTime));
    }

    public void addComment(String user, String text) {
        Comment c = new Comment(user, text);
        comments.add(c);
        long time = Instant.now().getEpochSecond();
        historyIssue.add(new Update(text, user, time, 'c'));
    }

    public void addReaction(int commentNum, String s) {
        comments.get(commentNum - 1).increaseReactionCount(s);
        long time = Instant.now().getEpochSecond();
        historyIssue.add(new Update(commentNum, s, time, 'r'));
    }

    public String getCommentPage() {
        String s = "Comments\n-------------\n";
        if (!comments.isEmpty()) {
            for (Comment c : comments) {
                s += c.toString();
            }
        } else {
            s += "No Comments :(";
        }
        return s;
    }

    public void updateTitle(String newTitle) {
        long time = Instant.now().getEpochSecond();
        String oldTitle = this.title;
        this.title = newTitle;
        historyIssue.add(new Update(oldTitle, newTitle, time, 't'));
    }

    public void updateDescription(String newDescription) {
        long time = Instant.now().getEpochSecond();
        String oldDescription = this.descriptionText;
        this.descriptionText = newDescription;
        historyIssue.add(new Update(oldDescription, newDescription, time, 'd'));
    }

    public void updateTag(String newTag) {
        long time = Instant.now().getEpochSecond();
        String oldTag = this.tag;
        this.tag = newTag;
        historyIssue.add(new Update(oldTag, newTag, time, 'g'));
    }

    public void updateAssignee(String newAssignee) {
        long time = Instant.now().getEpochSecond();
        String oldAssignee = this.assignee;
        this.assignee = newAssignee;
        historyIssue.add(new Update(oldAssignee, newAssignee, time, 'a'));
    }

    public void updatePriority(String newPriority) {
        long time = Instant.now().getEpochSecond();
        int oldPriority = this.priority;
        this.priority = Integer.parseInt(newPriority);
        historyIssue.add(new Update(oldPriority, this.priority, time, 'p'));
    }

    public void updateStatus(String newStatus) {
        long time = Instant.now().getEpochSecond();
        String oldStatus = this.status;
        this.status = newStatus;
        historyIssue.add(new Update(oldStatus, newStatus, time, 's'));
    }

    public void updateComment(int Id, String newComment) {
        long time = Instant.now().getEpochSecond();
        this.getComment(Id - 1).text = newComment;
        historyIssue.add(new Update(Id, newComment, time, 'u'));
    }

    public String showHistory() {
        String s = "Activities History\n"
                + "-------------------------------------------------------------------------------------------------\nLatest\n--------\n";
        for (int i = historyIssue.size() - 1; i >= 0; i--) {
            Update u = historyIssue.get(i);
            if (u.getTitleU() != null) {
                s += (i + 1) + "# Created new issue with the title of \"" + u.getTitleU() + "\"\nat " + u.getTimeU() + "\n";
            } else if (u.getC() == 't') {
                s += (i + 1) + "# Changed issue title\nfrom \"" + u.getOldTitle() + "\" to \"" + u.getNewTitle() + "\"\nat " + u.getTimeU() + "\n";
            } else if (u.getC() == 'd') {
                s += (i + 1) + "# Changed issue description\nfrom \"" + u.getOldDes() + "\" to \"" + u.getNewDes() + "\"\nat " + u.getTimeU() + "\n";
            } else if (u.getC() == 's') {
                s += (i + 1) + "# Changed issue status\nfrom \"" + u.getOldStatus() + "\" to \"" + u.getNewStatus() + "\"\nat " + u.getTimeU() + "\n";
            } else if (u.getC() == 'a') {
                s += (i + 1) + "# Changed issue assignee\nfrom \"" + u.getOldAssignee() + "\" to \"" + u.getNewAssignee() + "\"\nat " + u.getTimeU() + "\n";
            } else if (u.getC() == 'g') {
                s += (i + 1) + "# Changed issue tag\nfrom \"" + u.getOldTag() + "\" to \"" + u.getNewTag() + "\"\nat " + u.getTimeU() + "\n";
            } else if (u.getC() == 'r') {
                s += (i + 1) + "# \""+u.getReactType()+"\" reaction added to Comment ID: "+u.getCommentIdU()+"\nat " + u.getTimeU() + "\n";
            } else if (u.getCommentU() != null) {
                s += (i + 1) + "# Updated issue Comment ID: " + u.getCommentIdU() + "\nComment updated: " + u.getCommentU() + "\nat " + u.getTimeU() + "\n";
            } else if (u.getCommentTextU() != null) {
                s += (i + 1) + "# Created new comment \"" + u.getCommentTextU() + "\" by " + u.getCommentUserU() + "\nat " + u.getTimeU() + "\n";
            } else if (u.getC() == 'p') {
                s += (i + 1) + "# Changed issue priority\nfrom \"" + u.getOldPriority() + "\" to \"" + u.getNewPriority() + "\"\nat " + u.getTimeU() + "\n";
            }
            s += "--------------------------------------------------------------------------------------------------\n";
        }
        s += "Oldest\n---------\n--------------------------------------------------------------------------------------------------\n";
        return s;
    }

    public boolean canSetStatus(String s) {
        boolean flag = false;
        if (this.getStatus().equalsIgnoreCase("Open") && (s.equalsIgnoreCase("In Progress") || s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("Resolved"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("In Progress") && (s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("Resolved") || s.equalsIgnoreCase("Open"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("Resolved") && (s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("Reopened"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("Reopened") && (s.equalsIgnoreCase("Resolved") || s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("In Progress"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("Closed") && s.equalsIgnoreCase("Reopen")) {
            flag = true;
        }
        return flag;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public int getId() {
        return id;
    }

    public Comment getComment(int i) {
        return comments.get(i);
    }

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getTag() {
        return tag;
    }

    public String getStatus() {
        return status;
    }

    public int getLastCommentNum() {
        return comments.size();
    }

    public String getIssueTime() {
        return formatDate(issueTime);
    }

    private static String formatDate(long date) {
        // convert seconds to milliseconds
        Date systemDate = new java.util.Date(date * 1000L);
        // the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        String formattedDate = sdf.format(systemDate);
        return formattedDate;
    }

}
