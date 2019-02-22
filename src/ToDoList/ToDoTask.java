package ToDoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ToDoTask {
    private String taskTitle;
    private String taskDetails;
    private boolean taskStatus;
    private int taskID;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private LocalDate taskDueDate;
    private static int countID = 0;



    public ToDoTask(String taskTitle, String taskDetails, String taskDueDate) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskDueDate = LocalDate.parse(taskDueDate,formatter);
        this.taskStatus = false;
        this.countID++;
        taskID = this.countID;
    }

    public ToDoTask(String taskTitle) {
        this (taskTitle, "",  "31/12/2019");
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

    public void setTaskDueDate(String newTaskDueDate) {
        LocalDate taskDueDate = LocalDate.parse(newTaskDueDate,formatter);
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
            System.out.println("Not done");
        } else {
            taskStatus = true;
            System.out.println("Done");
        }
    }
}


