package ToDoList;

import java.time.LocalDate;

public class ToDoTask {
    private String taskDescription;
    private String taskDetails;
    private LocalDate taskDueDate;

    public ToDoTask(String taskDescription, String taskDetails, LocalDate taskDueDate) {
        this.taskDescription = taskDescription;
        this.taskDetails = taskDetails;
        this.taskDueDate = taskDueDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }
}


