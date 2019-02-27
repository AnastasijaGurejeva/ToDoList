package ToDoList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** This Class holds ID for ToDoTasks by corresponding Project
*   Because project contains only ID, that means Exactly Same task might be
*   assigned to different Projects.
 */

public class Project implements Serializable {
    private String projectName;
    private List<Integer> listOfTasksID;

    public Project(String projectName) {
        this.projectName = projectName;
        listOfTasksID = new ArrayList<>();
    }

    public String getProjectName() {
        return projectName;
    }

    public void addTaskToProject(Integer taskID) {
        listOfTasksID.add(taskID);
    }

    public void removeTaskByID(Integer iD) {
        listOfTasksID.remove(iD);
    }

    public List <Integer> getListOfTasks() {
        return listOfTasksID;
    }



}
