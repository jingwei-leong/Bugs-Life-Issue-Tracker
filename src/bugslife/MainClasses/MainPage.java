package bugslife.MainClasses;

import java.util.List;

public class MainPage {

    private List<Project> projects;
    private List<User> users;

    public MainPage(List<Project> projects, List<User> users) {
        this.projects = projects;
        this.users = users;
    }

    public List<Project> getProjectsList() {
        return projects;
    }

    public void addUser(String username, String password) {
        users.add(new User(username, password));
    }

    public void addProjects(String name) {
        projects.add(new Project(name));
        Issue.issueCount = 0;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Project getProject(int projectId) {
        return projects.get(projectId - 1);
    }

    public List<User> getUsers() {
        return users;
    }

    public int getLastProjectNum() {
        return projects.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getLastUserCount() {
        return users.size();
    }

}
