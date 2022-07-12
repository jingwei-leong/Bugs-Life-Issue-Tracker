package bugslife.MainClasses;

public class React {
    private String reaction;
    private Integer count = 0;

    public React(String reaction) {
        this.reaction = reaction;
    }
    public void increaseCount(){
        count++;
    }

    public String getReaction() {
        return reaction;
    }

    public int getCount() {
        return count;
    }
    
}
