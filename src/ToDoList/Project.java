package ToDoList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Project {
    private String projectName;
    private List<ToDoTask> listOfTasks;

    public Project(String projectName) {
        this.projectName = projectName;
        listOfTasks = new ArrayList<>();
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

    public ToDoTask findTask(String description) {
        for( ToDoTask task : listOfTasks) {
            if (description.equals(task.getTaskDescription())) {
                return task;
            } else {
                return null;
            }
        }
        return null;
    }

    public void deleteTask(String description) {
        //listOfTasks.remove(findTask(description));
        Iterator<ToDoTask> it = listOfTasks.iterator();
            while(it.hasNext()) {
               if (description.equals(it.next().getTaskDescription()));
                it.remove();
            }
    }

    public void printProjectTasks() {
        listOfTasks.stream()
                .forEach(t -> System.out.println(constructTaskDetails(t)));
    }

    public String constructTaskDetails(ToDoTask task){
       return task.getTaskDescription() + " task due: " + task.getTaskDueDate() + task.isTaskDone();

    }

    public void sortTasksByDueDate() {
        List sortedListByDueDate = listOfTasks.stream()
                .sorted(Comparator.comparing(ToDoTask::getTaskDueDate))
                .collect(Collectors.toList());
    }

}
