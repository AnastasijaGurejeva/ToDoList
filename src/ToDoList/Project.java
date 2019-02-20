package ToDoList;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String projectName;
    private List<Integer> listOfTasksID;

    public Project(String projectName) {
        this.projectName = projectName;
        listOfTasksID = new ArrayList<>();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void addTaskToProject(ToDoTask task) {
        listOfTasksID.add(task.getTaskID());
    }

    public List <Integer> getListOfTasks() {
        return listOfTasksID;
    }



}
