package bugslife.MainClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Update {

    private transient char c;
    protected transient static int updateCount = 0;
    private int updateNumber;
    private String oldTitle, newTitle;
    private String oldDes, newDes;
    private String oldTag, newTag;
    private String oldAssignee, newAssignee;
    private String oldStatus,newStatus;
    private int oldPriority, newPriority;
    private String reactType;
    private String titleU;
    private String commentTextU;
    private String commentUserU;
    private String commentU;
    private int commentIdU;
    private long timeU;

    public Update(int id, String str, long time, char c) {    //update comment,add reaction
        this.c = c;
        if (c == 'u') {
            this.commentU = str;
        } else if (c == 'r') {
            this.reactType = str;
        }
        this.commentIdU = id;
        this.timeU = time;
        updateNumber = ++updateCount;
    }

    public Update(String first, String second, long timeU, char c) {       //create comment & updating issue title,description,tag,status,priority,assignee
        this.c = c;
        if (c == 't') {
            this.oldTitle = first;
            this.newTitle = second;
        } else if (c == 'd') {
            this.oldDes = first;
            this.newDes = second;
        } else if (c == 'a') {
            this.oldAssignee = first;
            this.newAssignee = second;
        } else if (c == 'g') {
            this.oldTag = first;
            this.newTag = second;
        }else if (c == 's') {
            this.oldStatus = first;
            this.newStatus = second;
        }else if (c == 'c') {
            this.commentTextU = first;
            this.commentUserU = second;
        }
        this.timeU = timeU;
        updateNumber = ++updateCount;
        this.commentTextU = first;
        this.commentUserU = second;
    }

    public Update(int first, int second, long time, char c) {       //update priority
        this.oldPriority = first;
        this.newPriority = second;
        this.c = c;
        this.timeU = time;
        updateNumber = ++updateCount;
    }

    public Update(String titleU, long time) {  //creating issue
        this.titleU = titleU;
        this.timeU = time;
        updateNumber = ++updateCount;
    }

    public int getUpdateNumber() {
        return updateNumber;
    }

    public String getTimeU() {
        return formatDate(timeU);
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public String getCommentTextU() {
        return commentTextU;
    }

    public String getCommentUserU() {
        return commentUserU;
    }

    public String getOldTag() {
        return oldTag;
    }

    public String getNewTag() {
        return newTag;
    }

    public String getReactType() {
        return reactType;
    }

    public String getOldAssignee() {
        return oldAssignee;
    }

    public String getNewAssignee() {
        return newAssignee;
    }

    public int getOldPriority() {
        return oldPriority;
    }

    public int getNewPriority() {
        return newPriority;
    }

    public String getOldDes() {
        return oldDes;
    }

    public String getNewDes() {
        return newDes;
    }

    public String getOldTitle() {
        return oldTitle;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public String getTitleU() {
        return titleU;
    }

    public char getC() {
        return c;
    }

    public String getCommentU() {
        return commentU;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public int getCommentIdU() {
        return commentIdU;
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
