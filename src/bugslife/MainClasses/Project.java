package bugslife.MainClasses;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project {

    public transient static Integer projectCount = 0;
    private Integer id;
    private String name;
    ArrayList<Issue> issues = new ArrayList<>();
    private long projectTime;

    public Project(String projectName) {
        this.name = projectName;
        id = ++projectCount;
        projectTime = Instant.now().getEpochSecond();
    }

    public void createIssue(String issueTitle, String issueDescrip, String tag, int priority, String creatorUser, String assigneeUser) {
        Update.updateCount = 0; //reset static updateCount before creating new issue
        issues.add(new Issue(issueTitle, issueDescrip, tag, priority, "Open", creatorUser, assigneeUser));
    }

    public String[] printProject() {
        String str = this.getProjectId() + "," + this.getName() + "," + this.issues.size();
        String[] print = str.split(",", 0);
        return print;
    }

    /**
     * search issueTitle,issueDescription,issueComment for matching keywords
     *
     * @param searchString keyword to be searched
     * @return ArrayList<Issue>
     */
    public List<Issue> searchIssue(String searchString) {
        List<Issue> match = new ArrayList<>();
        for (Issue i : issues) {
            if (isContain(i.getTitle(), searchString) || isContain(i.getDescriptionText(), searchString)
                    || isCommentContain(i.getComments(), searchString) || isContain(i.getTag(), searchString)) {
                match.add(i);
            }
        }
        return match;
    }

    private boolean isCommentContain(ArrayList<Comment> comment, String searchString) {   //to check for exact string in an ArrayList<Comment>(local method)
        for (Comment c : comment) {
            if (isContain(c.getText(), searchString)) {
                return true;
            }
        }
        return false;
    }

    private boolean isContain(String fullText, String searchString) {                 //to check for exact string in the source string(local method)
        String pattern = "\\b" + searchString + "\\b";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);               //make the keyword case insensitive
        Matcher m = p.matcher(fullText);
        return m.find();
    }

    public static void setProjectCount(Integer projectCount) {
        Project.projectCount = projectCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIssues(ArrayList<Issue> issues) {
        this.issues = issues;
    }

    public void setProjectTime(long projectTime) {
        this.projectTime = projectTime;
    }

    public List<Issue> getIssuesList() {
        return issues;
    }

    public Integer getId() {
        return id;
    }

    public int getIssueCount() {
        return issues.size();
    }

    public Issue getIssue(int issueId) {
        return issues.get(issueId - 1);
    }

    public int getLastIssueNum() {
        return issues.size();
    }

    public int getProjectId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProjectTime() {
        return formatDate(projectTime);
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
