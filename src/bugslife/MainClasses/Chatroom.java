package bugslife.MainClasses;

public class Chatroom {

    private static User1 user1;
    private static User2 user2;
    
    public Chatroom() {
        User1.username1 = "User 1";     // edit here to get the user name
        User2.username2 = "User 2";     // edit here to get the assignee name
        user1 = new User1();
        user2 = new User2();
    }
    
    public void displayChatroom() {
        user1.setLocation(300, 200);    // set the position of the user1 window
        user2.setLocation(800, 200);    // set the position of the user2 window
        user1.setVisible(true);         // display window of user1
        user2.setVisible(true);         // display window of user2
    }
    
}
