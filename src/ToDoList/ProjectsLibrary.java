package ToDoList;

import java.util.*;

public class ProjectsLibrary {
    private List<ToDoTask> unfilteredTasks;
    private HashMap<String, Project> library;

    public ProjectsLibrary() {
        unfilteredTasks = new ArrayList<>();
        library = new HashMap<>();
    }

    //when task is created it is automatically added to unfilteredTasks library;
    private void addTask(ToDoTask newTask) {
        unfilteredTasks.add(newTask);
    }

    private void addProject(Project newProject) {
        library.put(newProject.getProjectName(), newProject);
    }

    private boolean ifTaskExist(String taskTitle) {
        for (ToDoTask task : unfilteredTasks) {
            if (task.getTaskTitle().equals(taskTitle)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean ifProjectExist(String projectName) {
        Set<String> keys = library.keySet();
        for (String key : keys) {
            if (projectName.equals(key)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private ToDoTask findTask(String taskTitle) {
        if (ifTaskExist(taskTitle)) {
            Iterator<ToDoTask> it = unfilteredTasks.iterator();
            while (it.hasNext()) {
                ToDoTask t = it.next();
                if (taskTitle.equals(t.getTaskTitle())) {
                    return t;
                }
            }
        }
        System.out.println("Task doesn't exist");
        return null;// throw exception??
    }

}