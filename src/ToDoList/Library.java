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

    public void reassignTasksProject(String taskTitle, String projectName, String newProjectName) {
        ToDoTask t = findTask(taskTitle);
        if (t == null) {
            System.out.println("Task doesn't exist");
            return;
        }

        if (!ifProjectExist(projectName)) {
            System.out.println("Project doesn't exist");
            return;
        }

        assignTaskToProject(findTask(taskTitle), newProjectName);
        removeTaskFromProject(taskTitle, projectName);

    }

    // Deletes task from all Task collection and
    // all Id of this task from projects containing the task

    public void deleteTask(String taskTitle) {
        ToDoTask t = findTask(taskTitle);
        if (t == null) {
            System.out.println("Task doesn't exist");
            return;
        }

        int taskId = t.getTaskID();
        allTasks.remove(taskId);

        Collection<Project> list = allProjects.values();
        for (Project p : list) {
            p.removeTaskByID(taskId);
        }
    }

    private void removeTaskFromProject(String taskTitle, String projectName) {
        ToDoTask t = findTask(taskTitle);
        if (t == null) {
            System.out.println("Task doesn't exist");
            return;
        }

        if (!ifProjectExist(projectName)) {
            System.out.println("Project doesn't exist");
            return;
        }

        Integer taskID = t.getTaskID(); //might need to handle exception
        allProjects.get(projectName).removeTaskByID(taskID);
    }

    public void changeTaskStatus(String taskTitle) { //doesn't belong here???
        ToDoTask t = findTask(taskTitle);
        if (t == null) {
            System.out.println("Task doesn't exist");
            return;
        }
        t.changeTaskStatus();
    }

    public String constructDetails(ToDoTask task) {
        return "Task Title: " + task.getTaskTitle() + " Task details: "
                + task.getTaskDetails() + " Due date: " + task.getTaskDueDate()
                + " Task status: " + task.isTaskDone();
    }

    public void printList() {
        allTasks.values().stream()
                .forEach(t -> System.out.println(constructDetails(t)));
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






}


