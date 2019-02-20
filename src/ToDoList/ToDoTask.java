package ToDoList;

import java.time.LocalDate;

public class ToDoTask {
    private String taskTitle;
    private String taskDetails;
    private LocalDate taskDueDate;
    private boolean taskStatus;
    private int taskID;

    private static int countID = 0;

    public ToDoTask(String taskTitle, String taskDetails, LocalDate taskDueDate) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskDueDate = taskDueDate;
        this.taskStatus = false;
        this.countID++;
        taskID = this.countID;
    }

    public int getTaskID(){
        return taskID;
    }

    public ToDoTask(String taskTitle) {
        this (taskTitle, "", null);
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(LocalDate taskDueDate) {
        if (taskDueDate.isAfter(LocalDate.now())) {
            this.taskDueDate = taskDueDate;
        } else {
            System.out.println("The date is set before today's date");
        }
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public boolean isTaskDone() {
        return taskStatus;
    }

    public void changeTaskStatus() {
        if(isTaskDone()) {
            taskStatus = false;
        } else {
            taskStatus = true;
        }
    }
}


