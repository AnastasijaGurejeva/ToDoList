package ToDoList;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {
    private HashMap<Integer, ToDoTask> allTasks;
    private HashMap<String, Project> allProjects;

    public Library() {
        allTasks = new HashMap<>();
        allProjects = new HashMap<>();
    }

    /**
     * when a task is created it is added to allTasks HashMap;
     * @param newTask
     */

    public void addTask(ToDoTask newTask) {
        allTasks.put(newTask.getTaskID(), newTask);
    }

    /**
     * when a new Project is assigned to the task, it is added to allProjects HashMap;
     * @param newProject
     */

    private void addProject(Project newProject) {
        allProjects.put(newProject.getProjectName(), newProject);
    }

    public HashMap<Integer, ToDoTask> getAllTasks(){
        return allTasks;
    }

    public HashMap<String, Project> getAllProjects(){
        return allProjects;
    }

    private boolean ifProjectExist(String projectName) {
        if (allProjects.containsKey(projectName)) {
        return true;
        } else
        return false;
    }

    /**
     * task ID is is passed to a Project ArrayList.
     * when a new Project is assigned to the task, it is added to allProjects HashMap;
     * @param task ToDoTask we want to assign to the Project
     * @param projectName Existing or new Project Name
     */

    public void assignTaskToProject(ToDoTask task, String projectName) {
        if (ifProjectExist(projectName)) {
            allProjects.get(projectName).addTaskToProject(task.getTaskID());
        } else {
            Project newProject = new Project(projectName);
            newProject.addTaskToProject(task.getTaskID());
            addProject(newProject);
        }
    }


    private void removeTaskFromProject(ToDoTask task, String projectName) {
        Integer taskID = task.getTaskID();
        allProjects.get(projectName).removeTaskByID(taskID);
    }

    public void reassignTasksProject(ToDoTask task, String projectName, String newProjectName) {
        assignTaskToProject(task, newProjectName);
        removeTaskFromProject(task, projectName);
    }

    /**
     * Task is deleted from all tasks HashMap.
     * Tasks ID is removed from Project's ArrayList.
     * All project are checked if containing particular task ID, as it is allowed to assign one task to few Projects
     */

    public void deleteTask(ToDoTask task) {
        int taskId = task.getTaskID();
        allTasks.remove(taskId);

        Collection<Project> list = allProjects.values();
        for (Project p : list) {
            p.removeTaskByID(taskId);
        }
    }


    private String constructDetails(ToDoTask task) {
        return "No: " + task.getTaskID() + " Task Title: " + task.getTaskTitle().toUpperCase() + "\t \t  Task details: "
                + task.getTaskDetails() + "\n \t  Due date: " + task.getTaskDueDate()
                + "\t  Task status: " + task.taskStatus();
    }

    public void printList() {
        allTasks.values().stream()
                .forEach(t -> System.out.println(constructDetails(t)));
    }

    public void sortByProject(String projectName) {
        if (ifProjectExist(projectName)) {
            System.out.println("Task of project: " + projectName );
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

}


