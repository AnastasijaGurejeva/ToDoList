package ToDoList;

import java.util.*;

public class Library {
    private HashMap<Integer, ToDoTask> allTasks;
    private HashMap<String, Project> allProjects;

    public Library() {
        allTasks = new HashMap<>();
        allProjects = new HashMap<>();
    }

    //when task is created it is added to allTasks table;
    public void addTask(ToDoTask newTask) {
        allTasks.put(newTask.getTaskID(), newTask);
    }

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
        Set<String> keys = allProjects.keySet();
        for (String key : keys) {
            if (projectName.equals(key)) {
                return true;
            }
        }
        return false;
    }


    //task ID is given to a project List.
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

    public void reassignTasksProject(ToDoTask task, String projectName, String newProjectName) {
        assignTaskToProject(task, newProjectName);
        removeTaskFromProject(task, projectName);
    }

    private void removeTaskFromProject(ToDoTask task, String projectName) {
        Integer taskID = task.getTaskID();
        allProjects.get(projectName).removeTaskByID(taskID);
    }

    // Deletes task from all Task collection and
    // all Id of this task from projects containing the task

    public void deleteTask(ToDoTask task) {
        int taskId = task.getTaskID();
        allTasks.remove(taskId);

        Collection<Project> list = allProjects.values();
        for (Project p : list) {
            p.removeTaskByID(taskId);
        }
    }


    public String constructDetails(ToDoTask task) {
        return "No: " + task.getTaskID() + " Task Title: " + task.getTaskTitle().toUpperCase() + "\n \t Task details: "
                + task.getTaskDetails() + "\n \t Due date: " + task.getTaskDueDate()
                + "\n \t Task status: " + task.taskStatus();
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


