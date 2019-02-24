package ToDoList;

import java.time.LocalDate;

public class ToDoTask {
    private String taskTitle;
    private String taskDetails;
    private boolean taskStatus;
    private int taskID;
    private LocalDate taskDueDate;
    private static int countID = 0;



    public ToDoTask(String taskTitle, String taskDetails, LocalDate taskDueDate) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskDueDate = taskDueDate;
        this.taskStatus = false;
        this.countID++;
        taskID = this.countID;
    }

    public ToDoTask(String taskTitle, LocalDate taskDueDate) {
        this (taskTitle, "", taskDueDate);
    }

    public int getTaskID() {
        return taskID;
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

    public void setTaskDueDate(LocalDate newTaskDueDate) {
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

    private boolean isTaskDone() {
        return taskStatus;
    }

    public String taskStatus() {
        if(isTaskDone()) {
            return "Done";
        } else {
            return "Not done";
        }
    }

    public void changeTaskStatus() {
        if(isTaskDone()) {
            taskStatus = false;
        } else {
            taskStatus = true;
        }
    }
}


