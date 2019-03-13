/** This Class holds ID list for ToDoTasks by corresponding Project
 *  Because project contains only ID, that means Exactly Same task might be
 *  assigned to different Projects.
 **/

package ToDoList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public void addTaskIDtoProject(Integer taskID) {
        listOfTasksID.add(taskID);
    }

    public void removeTaskByID(Integer iD) {
        listOfTasksID.remove(iD);
    }

    public List <Integer> getListOfTasks() {
        return listOfTasksID;
    }



}
