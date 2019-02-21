package ToDoList;

import java.util.*;

public class Library {
    private HashMap<Integer, ToDoTask> allTasks;
    private HashMap<String, Project> allProjects;

    public Library() {
        allTasks = new HashMap<>();
        allProjects = new HashMap<>();
    }

    //when task is created it is automatically added to allTasks table;
    private void addTask(ToDoTask newTask) {
        allTasks.put(newTask.getTaskID(), newTask);
    }

    private void addProject(Project newProject) {
        allProjects.put(newProject.getProjectName(), newProject);
    }

    private boolean ifTaskExist(String taskTitle) {
        Collection <ToDoTask> tasks = allTasks.values();
        for (ToDoTask task : tasks) {
            if (task.getTaskTitle().equals(taskTitle)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean ifProjectExist(String projectName) {
        Set<String> keys = allProjects.keySet();
        for (String key : keys) {
            if (projectName.equals(key)) {
                return true;
            }
        }
        return false;
    }

    private ToDoTask findTask(String taskTitle) {
        if (ifTaskExist(taskTitle)) {
            Collection <ToDoTask> tasks = allTasks.values();
            Iterator<ToDoTask> it = tasks.iterator();
            while (it.hasNext()) {
                ToDoTask t = it.next();
                if (taskTitle.equals(t.getTaskTitle())) {
                    return t;
                }
            }
        }
        System.out.println("Task doesn't exist");
        return null; // throw exception??
    }

    //task reference is given to a project.
    // If project doesn't exists, it is created automatically and added to a allProjects Table
    public void assignTaskToProject(ToDoTask task, String projectName) {
        if (ifProjectExist(projectName)) {
            allProjects.get(projectName).addTaskToProject(task.getTaskID());
        } else {
            Project newProject = new Project(projectName);
            newProject.addTaskToProject(task.getTaskID());
            addProject(newProject);
        }
    }

    public void reassignTasksProject(String taskTitle, String projectName, String newProjectName) {
        if (ifTaskExist(taskTitle) && ifProjectExist(projectName)) {
            assignTaskToProject(findTask(taskTitle), newProjectName);
            removeTaskFromProject(taskTitle, projectName);
        }
    }


    public void deleteTask(String taskTitle) { // split to few methods??
        if (ifTaskExist(taskTitle)) {
            int id = findTask(taskTitle).getTaskID(); //might need to handle exception
            allTasks.remove(id);
            // Deleting all references of this task from projects containing the task
            Collection<Project> list = allProjects.values();
            for(Project p : list) {
               List<Integer> taskids = p.getListOfTasks();
                Iterator<Integer> it =taskids.iterator();
                   while(it.hasNext()) {
                       if(it.next() == id) {
                          it.remove();
                    }
                }
            }
        } else {
            System.out.println("Task doesn't exist");
        }
    }

    private void removeTaskFromProject(String taskTitle, String projectName) {
        if (ifTaskExist(taskTitle) && ifProjectExist(projectName)) {
            Integer taskID = findTask(taskTitle).getTaskID(); //might need to handle exception
            allProjects.get(projectName).getListOfTasks().remove(taskID);
        }
    }

    public void changeTaskStatus(String taskTitle) {
        if (ifTaskExist(taskTitle)) {
            findTask(taskTitle).changeTaskStatus(); //might need to handle exception
        }
    }

    public void sortByProject(String projectName) {
        if (ifProjectExist(projectName)) {
            List<Integer> tasksID = allProjects.get(projectName).getListOfTasks();
            tasksID.stream()
                    .map(key -> allTasks.get(key))
                    .forEach(t -> System.out.println(constructDetails(t)));
        }
    }

    public void sortByDueDate() {
        allTasks.values().stream()
                .sorted(Comparator.comparing(ToDoTask::getTaskDueDate))
                .forEach(t -> System.out.println(constructDetails(t)));
    }

    public String constructDetails(ToDoTask task){
        return "Task Title: " + task.getTaskTitle() + " Task details: " + task.getTaskDetails() + " Due date: " + task.getTaskDueDate() + " Task status: " + task.isTaskDone();
    }

    public void printList(){
        allTasks.values().stream()
                .forEach(t-> System.out.println(constructDetails(t)));
    }


}


