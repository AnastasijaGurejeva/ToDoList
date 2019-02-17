package ToDoList;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Project {
    private String projectName;
    private List<ToDoTask> listOfTasks;

    public Project(String projectName) {
        this.projectName = projectName;
        listOfTasks = new LinkedList<>();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void addNewTaskToProject(ToDoTask newTask) {
        listOfTasks.add(newTask);
    }

    public void deleteTask() {
        //method to delete task by providing description?
    }

    public void printProjectTasks(String projectName) {
        listOfTasks.stream()
                .forEach(t -> System.out.println(t.getTaskDescription() + " task due: " + t.getTaskDueDate()));
    }

    public void sortTasksByDueDate() {
        List sortedListByDueDate = listOfTasks.stream()
                .sorted((t1, t2) -> t1.getTaskDueDate().compareTo(t2.getTaskDueDate()))
                .collect(Collectors.toList());
    }

}
