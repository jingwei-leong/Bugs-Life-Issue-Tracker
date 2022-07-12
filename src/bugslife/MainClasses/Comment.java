package bugslife.MainClasses;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Comment {

    public transient static int commentCount = 0;
    private int commentId;
    protected String text;
    private List<React> react = new ArrayList<>(Arrays.asList(new React("angry"), new React("happy"), new React("thumbsUp"), new React("smile")));
    private long timestamp;
    private String user;

    public Comment(String user, String text) {
        this.text = text;
        this.user = user;
        this.timestamp = Instant.now().getEpochSecond();
        commentCount++;
        commentId = commentCount;
    }

    public void increaseReactionCount(String s) {
        int index = getIndex(s);
        React r = react.get(index);
        r.increaseCount();
    }

    public String getAllReactionsData() {
        String s = "\nReactions\n";
        for (int i = 0; i < react.size(); i++) {
            if (react.get(i).getCount() != 0) {
                s += react.get(i).getCount() + " people react with " + react.get(i).getReaction() + "\n";
            }
        }
        s += "--------------------------------------------------------------------------------------------------\n";
        return s;
    }

    public int getIndex(String s) {
        int index = 0;
        switch (s) {
            case "angry":
                index = 0;
                break;
            case "happy":
                index = 1;
                break;
            case "thumbsUp":
                index = 2;
                break;
            case "smile":
                index = 3;
                break;
        }
        return index;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getTimestamp() {
        return formatDate(timestamp);
    }

    public String toString() {
        String s = "#" + this.getCommentId() + "\tCreated on: " + this.getTimestamp() + "\tBy: " + this.getUser() + "\n"
                + this.getText() + "\n"
                + this.getAllReactionsData() + "\n";
        return s;
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
